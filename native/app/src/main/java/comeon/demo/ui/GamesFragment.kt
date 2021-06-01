package comeon.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import comeon.demo.R
import comeon.demo.data.Resource
import comeon.demo.data.games.Games
import comeon.demo.databinding.GamesFragmentBinding
import comeon.demo.util.autoCleared
import comeon.demo.util.getMainHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GamesFragment : Fragment() {

    private val viewModel by viewModels<GamesViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    private var binding by autoCleared<GamesFragmentBinding>()
    private var games: List<Games> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GamesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.request.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    games = it.data!!
                    val size = games.size
                    if (size > 0)
                        mainViewModel.setTitle(getString(R.string.app_name) + " ($size)")
                    initWebView()
                }

                Resource.Status.ERROR -> {
                    Snackbar.make(
                        binding.root,
                        R.string.an_error_occurred,
                        Snackbar.LENGTH_INDEFINITE
                    )
                        .setAction(R.string.try_again) {
                            viewModel.refresh()
                        }.show()
                }

                Resource.Status.LOADING ->
                    Toast.makeText(context, getString(R.string.loading), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initWebView() {
        binding.apply {
            val webSettings = webView.settings
            webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
            webSettings.javaScriptEnabled = true
            webSettings.domStorageEnabled = true
            webView.isHorizontalScrollBarEnabled = true
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    progressBar.hide()
                }
            }
            webView.addJavascriptInterface(JavaScriptInterface(), "toggleMessageHandler")
            webView.loadUrl("https://comeon-demo.web.app/")
            progressBar.show()
        }
    }

    fun navigate(game: String?) {
        for (item in games) {
            if (item.code == game) {
                findNavController().navigate(
                    GamesFragmentDirections.actionGamesFragmentToDetailFragment(
                        item
                    )
                )
            }
        }
    }

    private inner class JavaScriptInterface {
        @JavascriptInterface
        fun postMessage(game: String?) {
            getMainHandler().post {
                navigate(game)
            }
        }
    }
}