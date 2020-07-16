package com.alexcrue.comicreader.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.alexcrue.comicreader.Model.Comic
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alexcrue.comicreader.ChapterActivity
import com.alexcrue.comicreader.Common.Common
import com.alexcrue.comicreader.Interface.IRecyclerClick
import com.alexcrue.comicreader.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_layout.view.*

class MyComicAdapter(internal  var context: Context,
                     internal var comicList: List<Comic>) : RecyclerView.Adapter<MyComicAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.comic_layout, parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return comicList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(comicList[position].Image).into(holder.imagView)
        holder.textView.text = comicList[position].Name

        holder.setClick(object : IRecyclerClick{
            override fun onClick(view: View, position: Int) {
                context.startActivity(Intent(context, ChapterActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                Common.selected_comic = comicList[position]
            }
        })
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        override fun onClick(p0: View?) {
            iRecyclerClick.onClick(p0!!, adapterPosition)
        }

//        override fun onClick(v: View?) {
//            iRecyclerClick.onClick(v!!, adapterPosition)
//
//        }

        var imagView:ImageView
        var textView:TextView

        lateinit var iRecyclerClick: IRecyclerClick

        fun setClick(iRecyclerClick: IRecyclerClick){
            this.iRecyclerClick = iRecyclerClick
        }

        init {
            imagView = itemView.findViewById(R.id.comic_image)
            textView = itemView.findViewById(R.id.comic_name)

//            itemView.setOnClickListener(this)

            itemView.setOnClickListener(this)


        }


    }
}