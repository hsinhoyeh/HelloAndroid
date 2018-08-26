package org.taipei.opendata.celery

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

import org.taipei.opendata.celery.network.model.*
import org.taipei.opendata.celery.network.*


class MainActivity : AppCompatActivity() {

    private lateinit var vegeAdapter: VegeAdapter
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vegeAdapter = VegeAdapter()
        vege_list.adapter = vegeAdapter
        //vege_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        apiClient = ApiClient()

        apiClient.getVegeClient().list("resourceAquire", "c71394d5-62a1-430b-8000-e0a980687090")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ vegeAdapter.setVege(it.result.results) },
                        {
                            Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                        })
    }


    inner class VegeAdapter : RecyclerView.Adapter<VegeAdapter.VegeViewHolder>() {

        private val veges: MutableList<Vege> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VegeViewHolder {
            return VegeViewHolder(layoutInflater.inflate(R.layout.item_vege_layout, parent, false))
        }

        override fun getItemCount(): Int {
            return veges.size
        }

        override fun onBindViewHolder(holder: VegeViewHolder, position: Int) {
            holder.bindModel(veges[position])
        }

        fun setVege(data: List<Vege>) {

            veges.addAll(data)
            notifyDataSetChanged()
        }

        inner class VegeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val vegeDateText: TextView = itemView.findViewById(R.id.date)
            val marketplaceText: TextView = itemView.findViewById(R.id.markplace)
            val vegeName: TextView = itemView.findViewById(R.id.vegaName)
            val vegaPrice: TextView = itemView.findViewById(R.id.vegaPrice)
            fun bindModel(vege: Vege) {

                Log.d("TAG", "str: " + vege.toString())

                vegeDateText.text = vege.datetime
                marketplaceText.text = vege.marketplace
                vegeName.text = vege.name
                vegaPrice.text = vege.avgPrice
            }
        }
    }
}
