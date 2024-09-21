package andrespin.githubusers.data.repo

import andrespin.githubusers.data.RemoteDataSourceImpl
import andrespin.githubusers.domain.UsersRepository

class UsersRepositoryImpl(private val remoteDataSourceImpl: RemoteDataSourceImpl) : UsersRepository {

    override suspend fun getUsers(login: String) =
        remoteDataSourceImpl.getUsersData(login)

}