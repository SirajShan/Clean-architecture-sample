package space.stanton.technicaltest.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import space.stanton.technicaltest.network.model.Comments
import space.stanton.technicaltest.network.model.Posts
import space.stanton.technicaltest.network.model.PostsItem

interface PostsAPI {

    @GET("posts/")
    fun getPosts(): Single<ArrayList<PostsItem>>

    @GET("posts/{POST_ID}/")
    fun getPostDetails(@Path("POST_ID") postId: Int): Single<PostsItem>

    @GET("posts/{POST_ID}/comments/")
    fun getComments(@Path("id") postId: String): Single<Comments>
}