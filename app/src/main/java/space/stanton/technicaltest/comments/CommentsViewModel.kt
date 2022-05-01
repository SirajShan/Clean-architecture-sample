package space.stanton.technicaltest.comments

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import space.stanton.technicaltest.model.Data
import space.stanton.technicaltest.model.DataState
import space.stanton.technicaltest.network.interactor.CommentsInteractor
import space.stanton.technicaltest.network.model.CommentsItem
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(private val commentsInteractor: CommentsInteractor) :
    ViewModel(), LifecycleObserver {
    val comments = MutableLiveData<Data<ArrayList<CommentsItem>>>()
    private val compositeDisposable = CompositeDisposable()

    fun getComments(postId: Int) = compositeDisposable.add(
        commentsInteractor.getComments(postId = postId)
            .doOnSubscribe({})
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                comments.postValue(Data(dataState = DataState.SUCCESS, data = it, message = ""))
            }, {
                println("response == error" + it.localizedMessage)

            })
    )
}