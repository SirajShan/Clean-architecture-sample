package space.stanton.technicaltest.network.interactor

import space.stanton.technicaltest.network.repository.CommentsRepository
import javax.inject.Inject

class CommentsInteractor @Inject constructor(private val commentsRepository: CommentsRepository) {

    fun getComments(postId: Int)= commentsRepository.getComments(postId)

}