package me.sam.test.jetpack.paging

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import me.sam.test.jetpack.R
import me.sam.test.jetpack.paging.model.CatModel

/**
 * desc:
 * date: 2020/05/11
 * @author: dongsen
 */
class CatAdapter : PagedListAdapter<CatModel, CatAdapter.ViewHolder>(DiffCallback) {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catModel = getItem(position)
        catModel?.apply{
            Glide.with(mContext)
                    .load(url)
                    .centerCrop()
                    .into(holder.image)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.catImage)
    }

    object DiffCallback : DiffUtil.ItemCallback<CatModel>() {
        override fun areItemsTheSame(oldItem: CatModel, newItem: CatModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CatModel, newItem: CatModel): Boolean {
            return oldItem.id == newItem.id
        }

    }

}
