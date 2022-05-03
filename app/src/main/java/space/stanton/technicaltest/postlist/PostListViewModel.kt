package space.stanton.technicaltest.postlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import space.stanton.technicaltest.model.Data
import space.stanton.technicaltest.model.DataState
import space.stanton.technicaltest.network.interactor.PostInteractor
import space.stanton.technicaltest.network.model.PostsItem
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(private val postInteractor: PostInteractor) :

    ViewModel() {
    val posts = MutableLiveData<Data<ArrayList<PostsItem>>>()
    val postDetails = MutableLiveData<Data<PostsItem>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        getPosts()
    }

    fun getPosts() = compositeDisposable.add(
        postInteractor.getPosts()
            .doOnSubscribe({})
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                posts.postValue(Data(dataState = DataState.SUCCESS, data = it, message = ""))
            }, {
                println("Error  ${it.localizedMessage}")

            })
    )

    fun getPostsDetails(postId: Int) = compositeDisposable.add(
        postInteractor.getPostDetails(postId)
            .doOnSubscribe({})
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                postDetails.postValue(Data(dataState = DataState.SUCCESS, data = it as PostsItem, message = ""))
            }, {
                println("Error  ${it.localizedMessage}")
            })
    )

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}