package andrespin.githubusers.presentation.main.adapter

import andrespin.githubusers.R
import andrespin.githubusers.databinding.ItemDataBinding
import andrespin.githubusers.domain.entity.ReposAndUsersData
import andrespin.githubusers.presentation.main.MainFragment
import andrespin.githubusers.presentation.main.adapter.repos_and_users.DataViewHolder
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(private val fragment: MainFragment) : RecyclerView.Adapter<DataViewHolder>() {

    private var list: List<ReposAndUsersData> = arrayListOf()

    fun setData(data: List<ReposAndUsersData>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ItemDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                setItemClickedEvent(list[this.layoutPosition], fragment)
            }
        }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size

    private fun setItemClickedEvent(item: ReposAndUsersData, fragment: MainFragment) =
        if (item.areUsers) {
            openBrowser(item, fragment)
        } else {
            navigateToRepoFragment(item, fragment)
        }

    private fun openBrowser(item: ReposAndUsersData, fragment: MainFragment) {
        val webIntent = Intent(Intent.ACTION_VIEW)
        webIntent.data = Uri.parse(item.html_url_user)
        fragment.requireActivity().startActivity(webIntent)
    }

    private fun navigateToRepoFragment(item: ReposAndUsersData, fragment: MainFragment) {
        val bundle = bundleOf("full_name_repo" to item.full_name_repo)
        fragment.findNavController().navigate(R.id.action_main_to_repo, bundle)
    }
}

