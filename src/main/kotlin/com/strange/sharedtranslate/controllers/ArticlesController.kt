package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.services.impl.ArticleMongoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by Zakhar_Kliap on 17-May-16.
 */
@Controller
class ArticlesController @Autowired constructor(val articleService: ArticleMongoService) {

    @RequestMapping(path = arrayOf("/articles/{page}/{size}", "/articles"))
    fun allTexts() = "base"

    @RequestMapping("/articles/content")
    fun allTextsContent() = "articles-page"

    @RequestMapping("/articles/data/{page}/{size}")
    @ResponseBody
    fun allTextsData(@PathVariable page: Int, @PathVariable size: Int) = articleService.findAll(PageRequest(page, size)).toList()

}