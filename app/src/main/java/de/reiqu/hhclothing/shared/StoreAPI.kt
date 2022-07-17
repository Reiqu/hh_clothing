package de.reiqu.hhclothing.shared

import android.util.Log
import de.reiqu.hhclothing.models.Item
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


class StoreAPI(private var hostname: String = "https://cms.reiqu.de"){

    private val url = "$hostname/api"

    private val retro: Retrofit = Retrofit.Builder()
        .baseUrl(Companion.URL_CLOTHING_API)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service: HHClothingItemService = retro.create(HHClothingItemService::class.java)

    val itemRequest = service.listItems()

    //TODO: Create API Calls
    //FIXME: currently this is not working
    fun getItems() {
        itemRequest.enqueue(object: Callback<List<HHClothingItem>> {
            override fun onResponse(call: Call<List<HHClothingItem>>, response: Response<List<HHClothingItem>>) {
                println(response.toString())
                val allItems = response.body()

                for (c in allItems!!)
                    Log.v(
                        "TESTING", "NAME: ${c.name} " +
                                "\n itemID: ${c.itemID} " +
                                "\n Beschr: ${c.description} " +
                                "\n ImageURL ${c.imageURL} " +
                                "\n ImageAlt ${c.imageAlternativeText} ",
                    )
            }


            override fun onFailure(call: Call<List<HHClothingItem>>, t: Throwable) {
                Log.i("dsfsdf", "on FAILURE!!!!")
            }

        })
    }



    companion object {
        const val URL_CLOTHING_API = "https://cms.reiqu.de/"
    }
}

interface HHClothingItemService {

    @Headers("Bearer: 50bb56dedcceb7e8ca1e0b0bf0eeffbe571764d5681e856b8c866f790110c77839294825d19617b913809d99a2712a2ec1cabf6c927d965b18b7dd393694eddc4de5da98e32a7f8bdb1450ba92beb3780fa0d80249d394a2df9168d2e938451efeacb1c2b628c069e7212ec8e91fd3c89278956d4fc6460afc672bb7a2f3af09")
    @GET("/api/hh-clothing-items")
    fun listItems(): Call<List<HHClothingItem>>



}

data class HHClothingItem (val itemID: Int, val name: String, val description: String, val imageURL: String, val imageAlternativeText: String)

data class HHClothingStore (val storeID: Int, val itemID: Int, val amount: Int, val updatedAt: String)

