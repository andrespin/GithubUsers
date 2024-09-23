package andrespin.githubusers.presentation.main.adapter.repos_and_users

import andrespin.githubusers.databinding.ItemDataBinding
import andrespin.githubusers.domain.entity.ReposAndUsersData
import andrespin.githubusers.presentation.main.MainFragment
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
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
                    itemView
                }
            }
        }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size
}

