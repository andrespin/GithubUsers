package andrespin.githubusers.presentation.repo.adapter

import andrespin.githubusers.R
import andrespin.githubusers.databinding.ItemContentBinding
import andrespin.githubusers.domain.entity.ContentItem
import androidx.recyclerview.widget.RecyclerView

class ContentViewHolder(
    private val vb: ItemContentBinding
) : RecyclerView.ViewHolder(vb.root) {

    fun bind(content: ContentItem) {
        vb.txtContentName.text = content.name
        if (content.type == "dir")
            vb.imgContentType.setImageResource(R.drawable.ic_folder)
        else if (content.type == "file")
            vb.imgContentType.setImageResource(R.drawable.ic_file)
    }
}
