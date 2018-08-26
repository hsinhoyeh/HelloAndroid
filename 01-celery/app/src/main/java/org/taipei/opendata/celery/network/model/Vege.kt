package org.taipei.opendata.celery.network.model


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Vege {
    @SerializedName("_id")
    @Expose
    var id : String? = null

    // 種類
    @SerializedName("種類")
    @Expose
    var kind : String? = null

    // 市場
    @SerializedName("市場")
    @Expose
    var marketplace : String? = null

    // 品名
    @SerializedName("品名")
    @Expose
    var name : String? = null

    // 平均(元/公斤)
    @SerializedName("平均(元/公斤)")
    @Expose
    var avgPrice: String? = null

    // 日期
    @SerializedName("日期")
    @Expose
    var datetime : String? = null


    override fun toString(): String {
        return "Post{" +
                "name='" + name + '\''.toString() +
                "avgPrice='" + avgPrice + '\''.toString() +
                ", marketplace='" + marketplace + '\''.toString() +
                ", date=" + datetime +
                ", id=" + id +
                '}'.toString()
    }
}