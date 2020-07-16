package com.alexcrue.comicreader.Interface

import com.alexcrue.comicreader.Model.Comic

interface IComicLoadDoneListener {

    fun onComicLoadDoneListener(ComicList:List<Comic>)
}