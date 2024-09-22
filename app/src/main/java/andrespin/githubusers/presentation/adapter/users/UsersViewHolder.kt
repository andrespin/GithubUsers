package andrespin.githubusers.presentation.adapter.users

import andrespin.githubusers.databinding.ItemUserBinding
import andrespin.githubusers.domain.entity.UsersData

import androidx.recyclerview.widget.RecyclerView

class UsersViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root){

    fun bind(data: UsersData.Item) {
        data.avatar_url
        data.score
        data.login
    }
}
