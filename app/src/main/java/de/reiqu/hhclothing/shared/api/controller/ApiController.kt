package de.reiqu.hhclothing.shared.api.controller

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import de.reiqu.hhclothing.shared.database.AppDatabase
import de.reiqu.hhclothing.shared.database.entities.Item
import de.reiqu.hhclothing.shared.database.entities.Storage
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ApiController(val remoteConfig: FirebaseRemoteConfig): VolleyCallback {
    val host: String = "https://cms.reiqu.de/api"


    fun getItems(ctx: Context): StringRequest {

        val items: MutableList<Item> = mutableListOf()


        val request: StringRequest =
            object : StringRequest(
                Method.GET,
                "$host/hh-clothing-items?populate=*",
                Response.Listener { response ->
                    Toast.makeText(ctx, "Items werden geladen..", Toast.LENGTH_SHORT).show()
                    try {
                        val jsonObject = JSONObject(response)
                        val data = jsonObject.getJSONArray("data")

                        for (i in 1 until data.length()) {

                            val dataObject: JSONObject = data.getJSONObject(i)

                            val itemID: Int = dataObject.getInt("id")

                            val attributes = dataObject.getJSONObject("attributes")
                            val image = attributes.getJSONObject("image").getJSONObject("data")
                            val imageAttributes = image.getJSONObject("attributes")
                            val imageFormatThumbnail = imageAttributes.getJSONObject("formats").getJSONObject("thumbnail")
                            val imageFormatSmall = imageAttributes.getJSONObject("formats").getJSONObject("small")
                            val imageFormatLarge = imageAttributes.getJSONObject("formats").getJSONObject("large")

                            println("imageFormatThumbnail: $imageFormatThumbnail")
                            val itemToAdd = Item(
                                uid = itemID,
                                description = attributes.getString("description"),
                                image_alt = imageAttributes.getString("alternativeText"),
                                image_id = image.getInt("id"),
                                image_small_url = "https://cms.reiqu.de${imageFormatSmall.getString("url")}",
                                image_url = "https://cms.reiqu.de${imageFormatLarge.getString("url")}",
                                name = attributes.getString("name"),
                                image_thumbnail_url = "https://cms.reiqu.de${imageFormatThumbnail.getString("url")}",
                                updated_at = attributes.getString("updatedAt"),
                                created_at = attributes.getString("createdAt"),
                            )

                            items.add(itemToAdd)
                            //AppDatabase.getInstance(ctx)?.itemDao()?.insertItem(itemToAdd)
                        }

                        onSuccess(items)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Log.e("tag", "error is " + error!!.message)
                    Toast.makeText(ctx, "Fail to update data..", Toast.LENGTH_SHORT)
                        .show()
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val header: MutableMap<String, String> = HashMap()

                    header["Authorization"] = remoteConfig.getString("bearer")
                    return header
                }

                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["populate"] = "*"
                    params["pagination[pageSize]"] = "100"
                    return params
                }
            }
        return request
    }


    fun getItemStore(ctx: Context): StringRequest {

        val storageList = mutableListOf<Storage>()

        val request: StringRequest =
            object : StringRequest(
                Method.GET,
                "$host/hh-clothing-item-stores",
                Response.Listener { response ->
                    Toast.makeText(ctx, "Item Store wird geladen..", Toast.LENGTH_SHORT).show()

                    try {
                        val jsonObject = JSONObject(response)

                        val data: JSONArray = jsonObject.getJSONArray("data")

                        println("ItemStore | data: $data")
                        println("ItemStore | data length: ${data.length()}")

                        for (i in 1 until data.length()) {
                            val dataObject: JSONObject = data.getJSONObject(i)
                            val attributes = dataObject.getJSONObject("attributes")


                            val itemToAdd = Storage(
                                updated_at = attributes.getString("updatedAt"),
                                uid = dataObject.getInt("id"),
                                created_at = attributes.getString("createdAt"),
                                item_id = attributes.getJSONObject("item").getJSONObject("data").getInt("id"),
                                amount = attributes.getInt("amount")
                            )

                            storageList.add(itemToAdd)
                            //AppDatabase.getInstance(ctx)?.storageDao()?.insertAll(itemToAdd)
                        }

                        onSuccess(storageList)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Log.e("tag", "error is " + error!!.message)
                    Toast.makeText(ctx, "Fail to update data..", Toast.LENGTH_SHORT)
                        .show()
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val header: MutableMap<String, String> = HashMap()
                    header["Authorization"] = remoteConfig.getString("bearer")
                    return header
                }
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["populate"] = "*"
                    params["pagination[pageSize]"] = "100"
                    return params
                }
            }
        return request
    }

    override fun onSuccess(items: MutableList<*>): MutableList<*> {
        println("After success: $items")
        return items
    }
}

