package space.stanton.technicaltest.network.repositoryimpl

import io.reactivex.Single
import space.stanton.technicaltest.network.PostsAPI
import space.stanton.technicaltest.network.model.Posts
import space.stanton.technicaltest.network.model.PostsItem
import space.stanton.technicaltest.network.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(val api: PostsAPI) : PostRepository {
    override fun getPosts(): Single<ArrayList<PostsItem>> = api.getPosts()

    override fun getPostDetails(postId: Int): Single<PostsItem> = api.getPostDetails(postId)
}