package andrespin.githubusers.presentation.main.adapter.users

import andrespin.githubusers.databinding.ItemUserBinding
import andrespin.githubusers.domain.entity.UsersData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter : RecyclerView.Adapter<UsersViewHolder>(){

    private var list: List<UsersData.Item> = arrayListOf()

    fun setData(data: List<UsersData.Item>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsersViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                
            }
        }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

}