package space.stanton.technicaltest.model

import java.io.Serializable

data class Posts(val posts: ArrayList<PostItem>)

data class PostItem(val postId: String, val userId: String, val title: String, val body: String, val name: String, val username: String, val email: String) : Serializable


