package andrespin.githubusers.presentation.main.adapter.repos

import andrespin.githubusers.databinding.ItemRepoBinding
import andrespin.githubusers.databinding.ItemUserBinding
import andrespin.githubusers.domain.entity.ReposData
import androidx.recyclerview.widget.RecyclerView

class ReposViewHolder(private val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root) {

    fun bind(data: ReposData.Item) {
        data.name
        data.forks_count
        data.description
    }

}
