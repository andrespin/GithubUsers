package andrespin.githubusers.presentation.repo.adapter

import andrespin.githubusers.R
import andrespin.githubusers.databinding.ItemContentBinding
import andrespin.githubusers.domain.entity.ContentItem
import andrespin.githubusers.presentation.repo.RepoFragment
import andrespin.githubusers.presentation.repo.RepoIntent
import andrespin.githubusers.presentation.repo.RepoViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ContentAdapter(
    private val repoFragment: RepoFragment,
    private val model: RepoViewModel,
    private val web: WebView,
    private val recyclerView: RecyclerView
)

    : RecyclerView.Adapter<ContentViewHolder>() {

    // ArrayList<ContentItem>()

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

                val pos = this.layoutPosition
                val item = list[pos]
                if (item.type == "dir") {
                    repoFragment.lifecycleScope.launch {
                        repoFragment.model.intent.emit(
                            RepoIntent.OpenDir(item._links.self)
                        )
                    }
                } else if (item.type == "file") {
                    web.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    web.loadUrl(item._links.html)
                }
            }


//                vb.imgContentType.setImageResource(R.drawable.ic_file)

            /*
            val webView: WebView = findViewById(R.id.browser)
webView.loadUrl("file:///android_asset/page.html")
             */

//            itemView.setOnClickListener {
//                val pos = this.layoutPosition
//                val item = list[pos]
//                if (item.areUsers) {
//                    val webIntent = Intent(Intent.ACTION_VIEW)
//                    webIntent.data = Uri.parse(item.html_url_user)
//                    fragment.requireActivity().startActivity(webIntent)
//                } else {
//                    itemView
//                }
//            }
        }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size

}