package space.stanton.technicaltest.network.repository

import io.reactivex.Single
import space.stanton.technicaltest.network.model.Comments
import space.stanton.technicaltest.network.model.CommentsItem

interface CommentsRepository {
    fun getComments(postId: Int): Single<ArrayList<CommentsItem>>
}