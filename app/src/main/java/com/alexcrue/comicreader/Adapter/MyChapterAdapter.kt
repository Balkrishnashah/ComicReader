package com.alexcrue.comicreader.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.alexcrue.comicreader.Common.Common
import com.alexcrue.comicreader.Interface.IRecyclerClick
import com.alexcrue.comicreader.Model.Chapter
import com.alexcrue.comicreader.R
import com.alexcrue.comicreader.ViewComicActivity
import java.lang.StringBuilder

class MyChapterAdapter(internal var context: Context,
                       internal var chapterList: List<Chapter>):RecyclerView.Adapter<MyChapterAdapter.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_chapter_number.text = StringBuilder(chapterList[position].Name!!)

        holder.setClick(object : IRecyclerClick{
            override fun onClick(view: View, pos: Int) {
                Common.selected_chapter = chapterList[pos]
                Common.chapter_index = pos

                context.startActivity(Intent(context, ViewComicActivity::class.java))
            }

        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.chapter_item, parent, false)
        return MyChapterAdapter.MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return chapterList.size
    }


//    override fun onBindViewHolder(holder: MyComicAdapter.MyViewHolder, position: Int) {
//        holder.txt_chapter_number. = StringBuilder(chapterList[position].Name!!)
//    }



    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
        override fun onClick(v: View?) {
            iRecyclerClick.onClick(v!!,adapterPosition)
        }

        lateinit var txt_chapter_number: TextView
        internal lateinit var iRecyclerClick: IRecyclerClick

        fun setClick(iRecyclerClick: IRecyclerClick){
            this.iRecyclerClick = iRecyclerClick
        }

        init {
            txt_chapter_number = itemView.findViewById(R.id.txt_chapter_number)
            itemView.setOnClickListener(this)
        }

    }



}