package space.stanton.technicaltest.postdetails

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.filterList
import space.stanton.technicaltest.Constants
import space.stanton.technicaltest.R
import space.stanton.technicaltest.cache.PreferenceManager
import space.stanton.technicaltest.comments.CommentsActivity
import space.stanton.technicaltest.network.model.PostsItem
import space.stanton.technicaltest.postlist.PostListViewModel


/**
 * Shows details of a post
 */

@AndroidEntryPoint
class PostDetailActivity : AppCompatActivity() {

    private val viewModel: PostListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        val id = intent.getIntExtra("postId", 0)
        viewModel.postDetails.observe(this) {
            updateUI(it.data!!)
        }

        viewModel.getPostsDetails(id)

    }

    private fun updateUI(post: PostsItem) {
        findViewById<TextView>(R.id.title).text = post?.title
        findViewById<TextView>(R.id.content).text = post?.body
        this@PostDetailActivity.title = post?.title
        findViewById<Button>(R.id.commentsBtn).setOnClickListener {
            startActivity(
                Intent(this, CommentsActivity::class.java)
                    .putExtra("postId", post?.id)
            )
        }

        val toggle = findViewById<Switch>(R.id.switch1)

        toggle.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->


            val settings = PreferenceManager.customPrefs(this, Constants.PREF_NAME)
            val postList = settings.getString("postToReadOffline", null)

            if(postList != null) {
                var posts: ArrayList<PostsItem> = Gson().fromJson(
                    postList,
                    object : TypeToken<ArrayList<PostsItem>>() {}.type
                )

                val newList: ArrayList<PostsItem> = posts.filter { it.id != post?.id }  as ArrayList<PostsItem>

                if(isChecked) {
                    newList.add(post)
                }
            }
        })


    }
}