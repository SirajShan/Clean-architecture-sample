package space.stanton.technicaltest.comments

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltest.R
import space.stanton.technicaltest.network.model.CommentsItem

/**
 * Displays a list of posts
 */
@AndroidEntryPoint
class CommentsActivity : AppCompatActivity() {

    private val viewModel: CommentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        val id = intent.getIntExtra("postId", 0)

        viewModel.comments.observe(this) {
            updateUI(it.data)
        }

        viewModel.getComments(id)

    }

    private fun updateUI(data: ArrayList<CommentsItem>?) {
        findViewById<RecyclerView>(R.id.postsList).adapter =
            CommentsAdapter(data, onItemClick = { _ ->
            })
    }
}

