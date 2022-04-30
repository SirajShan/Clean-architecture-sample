package space.stanton.technicaltest.network.interactor

import io.reactivex.Single
import space.stanton.technicaltest.network.model.Posts
import space.stanton.technicaltest.network.model.PostsItem
import space.stanton.technicaltest.network.repository.PostRepository
import javax.inject.Inject

class PostInteractor @Inject constructor(private val postRepository: PostRepository) {
    fun getPosts() : Single<ArrayList<PostsItem>> = postRepository.getPosts()

    fun getPostDetails(postId: Int) = postRepository.getPostDetails(postId)
}