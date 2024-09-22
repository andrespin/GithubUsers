package andrespin.githubusers.presentation.adapter.repos

import andrespin.githubusers.databinding.ItemRepoBinding
import andrespin.githubusers.domain.entity.ReposData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ReposAdapter: RecyclerView.Adapter<ReposViewHolder>() {

    private var list: List<ReposData.Item> = arrayListOf()

    fun setData(data: List<ReposData.Item>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReposViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {

            }
        }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

}
