package space.stanton.technicaltest.postlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltest.Constants
import space.stanton.technicaltest.R
import space.stanton.technicaltest.network.model.PostsItem
import space.stanton.technicaltest.postdetails.PostDetailActivity

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private val viewModel: PostListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_updated_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.posts.observe(requireActivity()) {
            updateUI(it.data)
        }

        viewModel.getPosts()
    }

    private fun updateUI(data: ArrayList<PostsItem>?) {
        activity?.findViewById<RecyclerView>(R.id.postsList)?.adapter =
            PostAdapter(data, onItemClick = { id ->
                startActivity(
                    Intent(activity, PostDetailActivity::class.java)
                        .putExtra(Constants.POST_ID, id)
                )
            })
    }
}