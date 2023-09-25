package com.android.architecture.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.architecture.R
import com.android.architecture.models.Articles

import com.bumptech.glide.Glide

class MainArticleAdapter(var contxt:Context,var newslist:ArrayList<Articles>): RecyclerView.Adapter<MainArticleAdapter.ViewHolder>(),Filterable
{
    var filterdNewsList:ArrayList<Articles> = newslist
    class ViewHolder(var view:View) : RecyclerView.ViewHolder(view){
        val imageView:ImageView = view.findViewById(R.id.imageview)
        val title:TextView = view.findViewById(R.id.title)
        val description:TextView = view.findViewById(R.id.description)
        val source:TextView = view.findViewById(R.id.src)
        val url:TextView = view.findViewById(R.id.url)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_mainadapter,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
       return filterdNewsList.size
    }
    fun clear(){
        newslist.clear()
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemlist = filterdNewsList[position]
        holder.url.setText("Click To " +itemlist.url)
        if(itemlist.url!=null){
            holder.url.setOnClickListener {
                var i = Intent()
                i.action = Intent.ACTION_VIEW
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                i.setData(Uri.parse(itemlist.url))
                contxt.startActivity(i)
            }
        }
        holder.title.setText("Title: " + itemlist.title)
        if(itemlist.description!=null) {
            holder.description.setText("Description: " + itemlist.description!!)
        }
        else{
            holder.description.setText("Description: " + "Not Found!!😊")
        }
        if(itemlist.author != null){
            holder.source.setText("Source: " + itemlist.author)
        }
        else{
            holder.source.setText("Source: Not Found 🐷")
        }
        Glide.with(holder.view)
            .load(itemlist.urlToImage)
            .into(holder.imageView)
    }

    override fun getFilter(): Filter {
        return  object: Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var charSeq = constraint.toString()
                if(charSeq.isEmpty()){
                    filterdNewsList = newslist
                }
                else{
                    var lists:ArrayList<Articles> = ArrayList()
                    for (row in newslist){
                        if(row.title.lowercase().contains(charSeq.lowercase())){
                            lists.add(row)
                        }
                    }
                    filterdNewsList = lists
                }
                var fre:FilterResults = FilterResults()
                fre.values = filterdNewsList
                return fre
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterdNewsList = results?.values as ArrayList<Articles>
                notifyDataSetChanged()
            }

        }
    }
}