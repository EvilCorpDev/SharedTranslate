package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.entities.Article
import com.strange.sharedtranslate.services.SaveService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import java.io.File
import javax.servlet.ServletContext

/**
 * Controller for uploading texts for translation into db
 *
 * Created by Zakhar_Kliap on 04-May-16.
 */
@Controller
class UploadController @Autowired constructor(val saveService: SaveService, val servletContext: ServletContext) {

    @RequestMapping(path = arrayOf("/upload"))
    fun upload() = "base"

    @RequestMapping("/upload/content")
    fun uploadContent() = "upload-page"

    @RequestMapping(path = arrayOf("/upload"), method = arrayOf(RequestMethod.POST))
    fun uploadTxt(@RequestParam(name = "file") uploadedFile: MultipartFile, @RequestParam cover: MultipartFile,
                  @RequestParam(name = "article") articleTitle: String, @RequestParam description: String = ""): String {
        val toSave = File(servletContext.getRealPath("/") + uploadedFile.originalFilename)
        uploadedFile.transferTo(toSave)
        saveService.saveArticle(articleTitle, description, cover, servletContext.getRealPath("/"))
        saveService.saveFile(toSave, articleTitle.replace(' ', '_'))
        return "base"
    }
}