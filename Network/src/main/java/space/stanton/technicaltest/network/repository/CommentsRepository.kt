package space.stanton.technicaltest.network.repository

import io.reactivex.Single
import space.stanton.technicaltest.network.model.Comments

interface CommentsRepository {
    fun getComments(postId: String): Single<Comments>
}