package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.entities.TranslationWrapper
import com.strange.sharedtranslate.services.SaveService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*

/**
 * Controller for uploading texts for translation into db
 *
 * Created by Zakhar_Kliap on 04-May-16.
 */
@Controller
class UploadTextController @Autowired constructor(val saveService: SaveService) {

    @RequestMapping(path = arrayOf("/upload"))
    fun upload(): String {
        return "base"
    }

    @RequestMapping("/upload/content")
    fun uploadContent(): String {
        return "upload-page"
    }

    @RequestMapping(path = arrayOf("/upload/data"), produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseBody
    fun uploadData(): TranslationWrapper {
        return TranslationWrapper("some string", "some author", Date())
    }

    @RequestMapping(path = arrayOf("/upload"), method = arrayOf(RequestMethod.POST))
    fun uploadTxt(@RequestParam uploadedFile: MultipartFile, @RequestParam articleTitle: String) {
        val toSave = File(uploadedFile.originalFilename)
        uploadedFile.transferTo(toSave)
        saveService.saveFile(toSave, articleTitle);
    }
}