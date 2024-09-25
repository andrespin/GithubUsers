package andrespin.githubusers.presentation.repo.adapter

import andrespin.githubusers.databinding.ItemContentBinding
import andrespin.githubusers.domain.entity.ContentItem
import andrespin.githubusers.presentation.repo.RepoFragment
import andrespin.githubusers.presentation.repo.RepoIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

@Suppress("IMPLICIT_CAST_TO_ANY")
class ContentAdapter(
    private val fragment: RepoFragment,
    private val web: WebView,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<ContentViewHolder>() {

    private var list: List<ContentItem> = arrayListOf()

    fun setData(data: List<ContentItem>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContentViewHolder(
            ItemContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                setItemClickedEvent(list[this.layoutPosition])
            }
        }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size

    private fun setItemClickedEvent(item: ContentItem) =
        when (item.type) {
            "dir" -> {
                openDirectory(item)
            }
            "file" -> {
                showDataOnWebView(item)
            }
            else -> {
                doNothing()
            }
        }

    private fun openDirectory(item: ContentItem) = fragment.lifecycleScope.launch {
        sendOpenDirectoryIntent(item)
    }

    private suspend fun sendOpenDirectoryIntent(item: ContentItem) =  fragment.model.intent.emit(
        RepoIntent.OpenDir(item._links.self)
    )

    private fun showDataOnWebView(item: ContentItem) {
        web.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        web.loadUrl(item._links.html)
    }

    private fun doNothing() {
        // Просто заглушка, чтобы не портить симметрию :)
    }

}