package com.example.tbc_test11.presentation.screen.fromBottom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_test11.databinding.AccountRvItemBinding
import com.example.tbc_test11.presentation.model.AccountPresentation

class AccountRecyclerAdapter :
    ListAdapter<AccountPresentation, AccountRecyclerAdapter.AccountViewHolder>(DiffCallback()) {

    var itemOnClick: ((AccountPresentation) -> Unit)? = null

    inner class AccountViewHolder(private val binding: AccountRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind() = with(binding){
                val account = currentList[adapterPosition]
                tvBalance.text = account.balance.toString()
                tvFourDigits.text = "**** ".plus(account.accNumber.takeLast(4))
            }

        fun listeners(){
            binding.root.setOnClickListener {
                itemOnClick!!(currentList[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            AccountRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind()
        holder.listeners()
    }

    fun setData(data: List<AccountPresentation>){
        submitList(data)
    }

    private class DiffCallback : DiffUtil.ItemCallback<AccountPresentation>() {
        override fun areItemsTheSame(
            oldItem: AccountPresentation,
            newItem: AccountPresentation
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AccountPresentation,
            newItem: AccountPresentation
        ): Boolean {
            return oldItem == newItem
        }
    }
}