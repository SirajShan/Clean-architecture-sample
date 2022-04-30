package space.stanton.technicaltest.network.repository

import io.reactivex.Single
import space.stanton.technicaltest.network.model.Posts
import space.stanton.technicaltest.network.model.PostsItem

interface PostRepository {
    fun getPosts() : Single<ArrayList<PostsItem>>

    fun getPostDetails(postId: Int): Single<PostsItem>
}