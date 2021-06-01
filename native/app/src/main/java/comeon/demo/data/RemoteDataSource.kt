package comeon.demo.data

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: Service
) : BaseDataSource() {

    suspend fun games() = getResult { service.games() }
}