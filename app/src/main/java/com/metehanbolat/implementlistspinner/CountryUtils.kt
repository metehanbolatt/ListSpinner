package com.metehanbolat.implementlistspinner

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun countryList(context: Context): MutableList<Country> {
    val jsonFileString = getJsonDataFromAsset(context = context, "Countries.json")
    val type = object : TypeToken<List<Country>>() {}.type
    return Gson().fromJson(jsonFileString, type)
}

fun getJsonDataFromAsset(
    context: Context,
    fileName: String
): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    } catch (exp: IOException) {
        exp.printStackTrace()
        return null
    }
    return jsonString
}

fun localeToEmoji(
    countryCode: String
) : String {
    val firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6
    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}