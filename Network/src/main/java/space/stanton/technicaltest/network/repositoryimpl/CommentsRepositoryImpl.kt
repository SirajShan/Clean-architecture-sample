package space.stanton.technicaltest.network.repositoryimpl

import io.reactivex.Single
import space.stanton.technicaltest.network.PostsAPI
import space.stanton.technicaltest.network.model.Comments
import space.stanton.technicaltest.network.repository.CommentsRepository
import javax.inject.Inject


class CommentsRepositoryImpl @Inject constructor(private val api: PostsAPI) : CommentsRepository {
    override fun getComments(postId: String): Single<Comments> = api.getComments(postId)
}