package com.example.employeemanagementmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.employeemanagementmvvm.R
import com.example.employeemanagementmvvm.model.Data
import kotlinx.android.synthetic.main.employee_previewer.view.*


class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>(){

    inner class EmployeeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object: DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.employee_previewer,parent,false)
        )
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.itemView.apply {
           // Glide.with(this).load(data.profile_image).into(ivArticleImage)
            nameTV.text = data.employee_name
            ageTV.text = data.employee_age.toString()
            salaryTV.text = data.employee_salary.toString()
//            tvPublishedAt.text = article.publishedAt
            setOnClickListener {
                onItemClickListener?.let {
                    it(data)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Data) -> Unit)? = null

    fun setOnItemCLickListener(listener: (Data)-> Unit) {
        onItemClickListener = listener
    }

}