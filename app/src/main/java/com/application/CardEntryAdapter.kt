package com.application


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.CardEntryAdapter.*
import kotlinx.android.synthetic.main.list_layout.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CardEntryAdapter(val userList: ArrayList<User>) : RecyclerView.Adapter<ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)

    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: User) {

            itemView.txt_name.text = user.name
            itemView.txt_dob.text = getDate(user.year + "-" + user.month + "-" + user.date)
            itemView.txt_no.text = user.contactNumber


        }


        fun getDate(date: String): String {
            if (!date.equals("")) {
                var parser = SimpleDateFormat()
                if (date.contains("-")) {
                    parser = SimpleDateFormat("yyyy-MM-dd")
                } else if (date.contains("/")) {
                    parser = SimpleDateFormat("yyyy/MM/dd")
                }
                val convertedDate = parser.parse(date) as Date
                var formatter = SimpleDateFormat("dd MMM yyyy")
                if (date.endsWith("1") && !date?.endsWith("11"))
                    formatter = SimpleDateFormat("d'st', MMM yy")
                else if (date.endsWith("2") && !date?.endsWith("12"))
                    formatter = SimpleDateFormat("d'nd', MMM yy")
                else if (date.endsWith("3") && !date?.endsWith("13"))
                    formatter = SimpleDateFormat("d'rd', MMM yy")
                else
                    formatter = SimpleDateFormat("d'th', MMM yy")
                return formatter.format(convertedDate)
            } else {
                return "Date Not Available"
            }

        }
//       2019-04-21-> 21st, April 2019
    }
}