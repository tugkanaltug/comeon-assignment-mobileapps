package comeon.demo.data.games

import comeon.demo.data.RemoteDataSource
import comeon.demo.util.performGetOperation
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: GamesDao
) {

    fun request() = performGetOperation(
        databaseQuery = { localDataSource.get() },
        networkCall = { remoteDataSource.games() },
        saveCallResult = { localDataSource.insert(it) }
    )
}