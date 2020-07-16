package com.alexcrue.comicreader.Common

import com.alexcrue.comicreader.Model.Chapter
import com.alexcrue.comicreader.Model.Comic
import java.lang.StringBuilder

object Common {
    fun formatString(name: String?): String {

        val finalResult = StringBuilder(if(name!!.length > 15)name.substring(0, 15)+"..." else name!!)
        return finalResult.toString()
    }

    var chapter_index: Int = -1
    lateinit var selected_chapter: Chapter
    lateinit var chapterList:List<Chapter>
    var ComicList: List<Comic> = ArrayList<Comic>()
    var selected_comic: Comic? =null


}