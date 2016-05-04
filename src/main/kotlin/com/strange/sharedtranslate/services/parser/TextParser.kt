package com.strange.sharedtranslate.services.parser

import java.io.File

/**
 * Created by Zakhar_Kliap on 26-Apr-16.
 */

class TextParser(val file: File) {

    fun parseText(): List<String> {
        val textLines = file.readLines()
        return textLines.flatMap { it.split('.') }.map { it + "."}
    }
}
