package com.alexcrue.comicreader

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.alexcrue.comicreader.Adapter.MyComicAdapter
import com.alexcrue.comicreader.Adapter.MySliderAdapter
import com.alexcrue.comicreader.Common.Common
import com.alexcrue.comicreader.Interface.IBannerLoadDoneListener
import com.alexcrue.comicreader.Interface.IComicLoadDoneListener
import com.alexcrue.comicreader.Model.Comic
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import ss.com.bannerslider.Slider
import com.alexcrue.comicreader.Service.PicassoImagerLoadingService
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), IBannerLoadDoneListener, IComicLoadDoneListener {
    override fun onComicLoadDoneListener(ComicList: List<Comic>) {
//        slider.setAdapter(MyComicAdapter(Comic))
        alertDialog.dismiss()

        Common.ComicList = ComicList
        recycle_comic.adapter = MyComicAdapter(baseContext, ComicList)
        textComic.text = StringBuilder("New COMIC (").append(ComicList.size).append(")")

        if(swipe_to_refresh.isRefreshing){
            swipe_to_refresh.isRefreshing = false
        }
    }


    override fun onBannerLoadDoneListener(banner: List<String>) {
        slider.setAdapter(MySliderAdapter(banner))
    }

    //database
    lateinit var banner_ref:DatabaseReference
    lateinit var comic_ref:DatabaseReference

    //Listener for banner
    lateinit var iBannerLoadDoneListener: IBannerLoadDoneListener
    lateinit var iComicLoadDoneListener: IComicLoadDoneListener

    lateinit var alertDialog: SpotsDialog

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iBannerLoadDoneListener = this
        iComicLoadDoneListener = this

        alertDialog = SpotsDialog.Builder().setContext(this)
            .setCancelable(false)
            .setMessage("Please Wait...")
            .build() as SpotsDialog

        banner_ref = FirebaseDatabase.getInstance().getReference("Banners")
        comic_ref = FirebaseDatabase.getInstance().getReference("Comic")


        swipe_to_refresh.setColorSchemeColors(R.color.colorPrimary, R.color.colorPrimaryDark)
        swipe_to_refresh.setOnRefreshListener {
            loadBanners()
            loadComic()
        }

        swipe_to_refresh.post{
            loadBanners()
            loadComic()
        }

        Slider.init(PicassoImagerLoadingService())

        recycle_comic.setHasFixedSize(true)
        recycle_comic.layoutManager = GridLayoutManager(this, 2)
    }

    private fun loadComic(){

        alertDialog.show()

        comic_ref.addListenerForSingleValueEvent(object : ValueEventListener{
            val comic_load : MutableList<Comic> = ArrayList<Comic>()
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@MainActivity, ""+p0.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {

                for(comicSnapShot in p0.children){
                    val comic = comicSnapShot.getValue(Comic::class.java)
                    comic_load.add(comic!!)
                }

                iComicLoadDoneListener.onComicLoadDoneListener(comic_load)

            }

        })

    }
    private fun loadBanners(){
     banner_ref.addListenerForSingleValueEvent(object : ValueEventListener{
         override fun onCancelled(p0: DatabaseError) {
             Toast.makeText(this@MainActivity, ""+p0.message, Toast.LENGTH_SHORT).show()
         }

         override fun onDataChange(p0: DataSnapshot) {
             val banner_list = ArrayList<String>()
             for(banner in p0.children)
             {
                 val image = banner.getValue(String::class.java)
                     banner_list.add(image!!)

             }

             iBannerLoadDoneListener.onBannerLoadDoneListener(banner_list)
         }
     })
    }
}
