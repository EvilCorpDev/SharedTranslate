package com.strange.sharedtranslate.services.parser

import com.google.gson.Gson
import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by Zakhar_Kliap on 26-Apr-16.
 */

class TextParser(val fileName: String) {

    val gson = Gson()

    fun parseText(): String {
        val textLines = Files.readAllLines(Paths.get(getFile(fileName)))
        val toList = textLines.flatMap { it.split('.') }.map { it + "."}
        return gson.toJson(toList)
    }

    fun getFile(fileName: String): URI? {
        val classLoader = Thread.currentThread().contextClassLoader
        return classLoader.getResource(fileName).toURI()
    }
}
