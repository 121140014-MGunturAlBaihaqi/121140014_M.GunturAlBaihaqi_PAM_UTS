package com.example.a121140014_pam_uts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a121140014_pam_uts.databinding.ItemUserBinding
import java.util.*
import kotlin.collections.ArrayList

class UserAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(), Filterable {

    private var filteredUserList: List<User> = userList

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemUserBinding.bind(itemView)

        fun bind(user: User) {
            binding.apply {
                userNameTextView.text = user.firstName + " " + user.lastName
                userEmailTextView.text = user.email
                Glide.with(itemView)
                    .load(user.avatar)
                    .into(userAvatarImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(filteredUserList[position])
    }

    override fun getItemCount(): Int {
        return filteredUserList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString().toLowerCase(Locale.getDefault())
                filteredUserList = if (charString.isEmpty()) {
                    userList
                } else {
                    val filteredList = ArrayList<User>()
                    for (user in userList) {
                        if (user.firstName.toLowerCase(Locale.getDefault()).contains(charString)
                            || user.lastName.toLowerCase(Locale.getDefault()).contains(charString)
                            || user.email.toLowerCase(Locale.getDefault()).contains(charString)
                        ) {
                            filteredList.add(user)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredUserList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredUserList = results?.values as List<User>
                notifyDataSetChanged()
            }
        }
    }
}
