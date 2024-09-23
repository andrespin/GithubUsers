package andrespin.githubusers.presentation.main.adapter.repos_and_users

import andrespin.githubusers.databinding.ItemDataBinding
import andrespin.githubusers.domain.entity.Data
import andrespin.githubusers.domain.entity.ReposAndUsersData
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DataViewHolder(private val vb: ItemDataBinding) : RecyclerView.ViewHolder(vb.root) {

    fun bind(data: ReposAndUsersData) =
        if (data.areUsers) {
            showUserItem()
            setDataToUserItem(data.login, data.score.toString())

        } else {
            showRepoItem()
            setDataToRepoItem(data.name, data.forks_count.toString(), data.description)
        }

    private fun showUserItem() {
        vb.itemUserData.root.visibility = View.VISIBLE
        vb.itemRepoData.root.visibility = View.GONE
    }

    private fun setDataToUserItem(
        login: String?,
        score: String?
    ) {
        vb.itemUserData.txtLogin.text = login
        vb.itemUserData.txtScore.text = score
    }

    private fun setDataToRepoItem(
        name: String?,
        forksCount: String?,
        desc: String?
    ) {
        vb.itemRepoData.txtName.text = name
        vb.itemRepoData.txtForksCount.text = forksCount
        vb.itemRepoData.txtDesc.text = desc
    }

    private fun showRepoItem() {
        vb.itemUserData.root.visibility = View.GONE
        vb.itemRepoData.root.visibility = View.VISIBLE
    }

}

