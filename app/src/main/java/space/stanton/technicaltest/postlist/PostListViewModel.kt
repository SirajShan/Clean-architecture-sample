package space.stanton.technicaltest.postlist

import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import space.stanton.technicaltest.Constants
import space.stanton.technicaltest.cache.PreferenceManager
import space.stanton.technicaltest.model.Data
import space.stanton.technicaltest.model.DataState
import space.stanton.technicaltest.model.PostItem
import space.stanton.technicaltest.network.interactor.PostInteractor
import space.stanton.technicaltest.network.model.PostsItem
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(private val postInteractor: PostInteractor) :
    ViewModel(), LifecycleObserver {
    val posts = MutableLiveData<Data<ArrayList<PostsItem>>>()
    val postDetails = MutableLiveData<Data<PostsItem>>()
    private val compositeDisposable = CompositeDisposable()
    val offlinePosts = MutableLiveData<Data<ArrayList<PostsItem>>>()

    init {
        getPosts()
    }

    private fun getPosts() = compositeDisposable.add(
        postInteractor.getPosts()
            .doOnSubscribe({})
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                posts.postValue(Data(dataState = DataState.SUCCESS, data = it, message = ""))
            }, {
                println("response == error" + it.localizedMessage)

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
                println("response == error" + it.localizedMessage)

            })
    )

    fun getPostsOffline(context: Context)  {
        val settings = PreferenceManager.customPrefs(context, Constants.PREF_NAME)
        val postListString = settings.getString("postToReadOffline", null)
        if(postListString != null) {

            val gson = Gson()
            var postList: ArrayList<PostsItem> = gson.fromJson(
                postListString,
                object : TypeToken<ArrayList<PostsItem>>() {}.type
            )

            offlinePosts.postValue(Data(dataState = DataState.SUCCESS, data = postList, message = ""))
        }
    }

}