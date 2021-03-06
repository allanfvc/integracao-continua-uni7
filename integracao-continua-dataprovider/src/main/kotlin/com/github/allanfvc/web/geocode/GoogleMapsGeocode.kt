package com.github.allanfvc.web.geocode

import com.github.allanfvc.entities.Location
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets


class GoogleMapsGeocode {
    private val url = "https://maps.googleapis.com/maps/api/geocode/json"

    fun getLocation(fullAdress: String): Location {
        val apiKey = System.getenv("GAPI_KEY")
        val url = URL(
            url + "?address="
                    + URLEncoder.encode(fullAdress, "UTF-8") + "&sensor=false&key="+apiKey
        )
        val conn = url.openConnection()
        val inputStream = conn.getInputStream()
        val gson = Gson()
        val response = gson.fromJson(getReturnMessage(inputStream), GoogleAPIResponse::class.java);
        inputStream.close()
        return when {
            response.status == "OK" -> response.results.get(0).geometry.location;
            else -> throw Exception("The Address is not valid.")
        }

    }

    fun getReturnMessage(inputStream: InputStream): String {
        val textBuilder = StringBuilder()
        BufferedReader(InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name()))).use { reader ->
            var c = 0
            while (reader.read().also { c = it } != -1) {
                textBuilder.append(c.toChar())
            }
        }
        return textBuilder.toString()
    }

}

class Geometry (val location: Location)

class GoogleAPIResponse (val results: Array<Result>,  val status: String? = null)

class Result (val formatted_address: String, val geometry: Geometry)
