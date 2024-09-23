package andrespin.githubusers.presentation.main.adapter.repos_and_users

import andrespin.githubusers.R
import andrespin.githubusers.databinding.ItemDataBinding
import andrespin.githubusers.domain.entity.ReposAndUsersData
import andrespin.githubusers.presentation.main.MainFragment
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
                val pos = this.layoutPosition
                val item = list[pos]
                if (item.areUsers) {
                    val webIntent = Intent(Intent.ACTION_VIEW)
                    webIntent.data = Uri.parse(item.html_url_user)
                    fragment.requireActivity().startActivity(webIntent)
                } else {

                    //    "full_name": "andrespineda132/andrespineda132",

                    val bundle = bundleOf("full_name_repo" to item.full_name_repo)
                    fragment.findNavController().navigate(R.id.action_main_to_repo, bundle)

                    /*
                    val bundle = bundleOf("amount" to amount)
view.findNavController().navigate(R.id.confirmationAction, bundle)
                     */
                }
            }
        }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size
}

