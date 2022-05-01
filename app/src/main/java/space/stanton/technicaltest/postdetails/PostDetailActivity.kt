package space.stanton.technicaltest.postdetails

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltest.R
import space.stanton.technicaltest.model.PostItem
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
            updateUI(it.data)
        }

        viewModel.getPostsDetails(id)

    }

    private fun updateUI(post: PostsItem?) {
        findViewById<TextView>(R.id.title).text = post?.title
        findViewById<TextView>(R.id.content).text = post?.body
        this@PostDetailActivity.title = post?.title
        findViewById<Button>(R.id.commentsBtn).setOnClickListener {
            startActivity(
                Intent(this, PostDetailActivity::class.java)
                    .putExtra("postId", post?.id)
            )
        }
    }
}