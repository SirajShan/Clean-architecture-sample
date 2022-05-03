package space.stanton.technicaltest.postlist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import space.stanton.technicaltest.Constants
import space.stanton.technicaltest.cache.PreferenceManager
import space.stanton.technicaltest.model.Data
import space.stanton.technicaltest.model.DataState
import space.stanton.technicaltest.network.model.PostsItem
import javax.inject.Inject

@HiltViewModel
class PostListOfflineViewModel @Inject constructor(private val context: Application) :

    ViewModel() {
    val offlinePosts = MutableLiveData<Data<ArrayList<PostsItem>>>()

    init {
        getPostsOffline()
    }

    fun getPostsOffline()  {
        val settings = PreferenceManager.customPrefs(context, Constants.PREF_NAME)
        val postListString = settings.getString("postToReadOffline", null)
        if(postListString != null) {

            val gson = Gson()
            val postList: ArrayList<PostsItem> = gson.fromJson(
                postListString,
                object : TypeToken<ArrayList<PostsItem>>() {}.type
            )

            offlinePosts.postValue(Data(dataState = DataState.SUCCESS, data = postList, message = ""))
        }
    }

}