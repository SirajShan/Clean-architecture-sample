package space.stanton.technicaltest.postlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.stanton.technicaltest.R
import space.stanton.technicaltest.network.model.PostsItem

class PostOfflineAdapter(private val items: ArrayList<PostsItem>?, val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<PostOfflineAdapter.PostViewHolder>() {

    class PostViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.title).text = items?.get(position)?.title
        holder.itemView.findViewById<TextView>(R.id.content).text =
            items?.get(position)?.body
        holder.itemView.setOnClickListener {
            onItemClick(items?.get(position)!!.id)
        }
    }

    override fun getItemCount(): Int = items!!.size

}