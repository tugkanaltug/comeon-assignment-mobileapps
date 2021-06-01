package comeon.demo.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import comeon.demo.databinding.DetailFragmentBinding
import comeon.demo.util.autoCleared

class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    private var binding by autoCleared<DetailFragmentBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.setTitle(args.item.name)
        binding.apply {
            descriptionTextView.text = args.item.description
            root.setBackgroundColor(
                Color.parseColor(
                    args.item.theme.replace("RGB(", "#").dropLast(1)
                )
            )
        }
    }
}