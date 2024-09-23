package andrespin.githubusers.presentation.repo.adapter

import andrespin.githubusers.databinding.ItemContentBinding
import andrespin.githubusers.domain.entity.ContentItem
import andrespin.githubusers.presentation.repo.RepoFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContentAdapter: RecyclerView.Adapter<ContentViewHolder>() {

    // ArrayList<ContentItem>()

    private var list: List<ContentItem> = arrayListOf()

    fun setData(data: List<ContentItem>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContentViewHolder(
            ItemContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
//            itemView.setOnClickListener {
//                val pos = this.layoutPosition
//                val item = list[pos]
//                if (item.areUsers) {
//                    val webIntent = Intent(Intent.ACTION_VIEW)
//                    webIntent.data = Uri.parse(item.html_url_user)
//                    fragment.requireActivity().startActivity(webIntent)
//                } else {
//                    itemView
//                }
//            }
        }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size

}