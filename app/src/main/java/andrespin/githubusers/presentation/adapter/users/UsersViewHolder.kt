package andrespin.githubusers.presentation.adapter.users

import andrespin.githubusers.databinding.ItemUserBinding
import andrespin.githubusers.domain.Item
import andrespin.githubusers.domain.UsersData
import androidx.recyclerview.widget.RecyclerView

class UsersViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root){

    fun bind(data: Item) {

        data.avatar_url
        data.score
        data.login

    }

}


/*
class SectionsViewHolder(private val vb: ItemSectionBinding) : RecyclerView.ViewHolder(vb.root) {
    fun bind(section: String) {
        vb.txtSection.text = section
    }
}
 */