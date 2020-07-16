package com.alexcrue.comicreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexcrue.comicreader.Adapter.MyChapterAdapter
//import com.google.android.gms.common.internal.service.Common
import kotlinx.android.synthetic.main.activity_chapter.*
import com.alexcrue.comicreader.Common.Common
import com.alexcrue.comicreader.Model.Comic
import kotlinx.android.synthetic.main.chapter_item.*
import java.lang.StringBuilder
import kotlinx.android.synthetic.main.chapter_item.txt_chapter_number as txt_chapter_number1


class ChapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

        toolbar.title = Common.selected_comic!!.Name

        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_black_24dp)
        toolbar.setNavigationOnClickListener{
            finish()
        }
        recycle_chapter.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recycle_chapter.layoutManager = layoutManager
        recycle_chapter.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))

        fetchChapter(Common.selected_comic!!)

    }

    private fun fetchChapter(comic: Comic){
        Common.chapterList = comic.Chapters!!
        txt_chapter_name.text = StringBuilder("CHAPTER (").append(comic.Chapters!!.size).append(")")


        recycle_chapter.adapter = MyChapterAdapter(this, Common.chapterList)

    }
}
