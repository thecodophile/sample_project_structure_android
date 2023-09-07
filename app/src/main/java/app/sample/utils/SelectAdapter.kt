package app.sample.utils

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import app.sample.R
import app.sample.databinding.DialogItemSelectBinding


class SelectAdapter(
    private val selectList: ArrayList<Any>,
    private var selectedItem: String
) :
    RecyclerView.Adapter<SelectAdapter.ViewHolder>() {

    var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: DialogItemSelectBinding =
            DialogItemSelectBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return selectList.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (selectedItem == selectList[position]) {
            selectedItem = selectList[position] as String
            holder.binding.rlState.background = (holder.binding.root.resources.getDrawable(R.drawable.bg_yellow_rounded_rect))
            holder.binding.tvProductName.setTextColor(
                holder.binding.root.rootView.resources.getColor(
                    R.color.colorBlack
                )
            )
        } else {
            holder.binding.rlState.setBackgroundColor(
                holder.binding.root.rootView.resources.getColor(
                    R.color.transparent
                )
            )
            holder.binding.tvProductName.setTextColor(
                holder.binding.root.rootView.resources.getColor(
                    R.color.colorWhite
                )
            )
        }

        holder.binding.tvProductName.text = selectList[position] as String

        holder.binding.rlState.setOnClickListener {
            holder.binding.rlState.setBackgroundColor(it.rootView.resources.getColor(R.color.colorLightOrange))
            holder.binding.tvProductName.setTextColor(it.rootView.resources.getColor(R.color.colorChartBarDark))
            if (selectedItem != selectList[position]) {
                selectedItem = selectList[position] as String
                selectedPosition = position
            } else {
                selectedItem = ""
                selectedPosition = position
            }
            notifyDataSetChanged()
        }

    }

    fun getName() : String{
        return selectedItem
    }

    fun getPosition(): Int {
        return selectedPosition
    }

    class ViewHolder(internal val binding: DialogItemSelectBinding) :
        RecyclerView.ViewHolder(binding.root)
}