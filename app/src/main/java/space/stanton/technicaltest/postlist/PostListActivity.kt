package space.stanton.technicaltest.postlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONObject
import space.stanton.technicaltest.ApiCalls
import space.stanton.technicaltest.R
import space.stanton.technicaltest.network.model.PostsItem
import space.stanton.technicaltest.postdetails.PostDetailActivity

/**
 * Displays a list of posts
 */
@AndroidEntryPoint
class PostListActivity : AppCompatActivity() {

    private val viewModel: PostListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.posts.observe(this) {
            UpdateUI(it.data)
        }

    }

    private fun UpdateUI(data: ArrayList<PostsItem>?) {
        findViewById<RecyclerView>(R.id.postsList).adapter =
            PostAdapter(data, onItemClick = { id ->
                startActivity(
                    Intent(this, PostDetailActivity::class.java)
                        .putExtra("postId", id)
                )
            })
    }
}

