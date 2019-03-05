package com.sritechsoftsolutions.googlenewsapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.indview.view.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var r=Retrofit.Builder().baseUrl("https://newsapi.org/").
            addConverterFactory(GsonConverterFactory.create()).build()

        var api:NewsApi=r.create(NewsApi::class.java)
        var call:Call<Articles> =api.getNews()
        call.enqueue(object :Callback<Articles>{
            override fun onFailure(call: Call<Articles>, t: Throwable) {

            }

            override fun onResponse(call: Call<Articles>, response: Response<Articles>) {

           var acls:Articles?=response.body()
            var articles:List<Article>?=acls?.articles
                lview.adapter=object :BaseAdapter()
                {
                    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

                        var linflater=LayoutInflater.from(this@MainActivity)
                        var view:View=linflater.inflate(R.layout.indview,null)
                        view.txttitle.text=articles?.get(position)?.title
                        view.txtdescription.text=articles?.get(position)?.description
                        Glide.with(this@MainActivity).load(articles?.get(position)?.urlToImage).into(view.imgnews)



                        return view
                    }

                    override fun getItem(position: Int): Any {
                       return 0

                    }

                    override fun getItemId(position: Int): Long {
                        return 0

                    }

                    override fun getCount(): Int {
                        return articles!!.size

                    }


                }
            }

        })

    }
}
