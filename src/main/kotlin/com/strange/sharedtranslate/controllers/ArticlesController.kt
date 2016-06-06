package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.entities.Article
import com.strange.sharedtranslate.services.impl.ArticleMongoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
/**
 * All articles page
 *
 * Created by Zakhar_Kliap on 17-May-16.
 */
@Controller
class ArticlesController @Autowired constructor(val articleService: ArticleMongoService) {

    @RequestMapping(path = arrayOf("/articles/{page}/{size}", "/articles"))
    fun articles() = "base"

    @RequestMapping("/articles/content")
    fun articlesContent() = "articles-page"

    @RequestMapping("/articles/data/{page}/{size}")
    @ResponseBody
    fun articlesData(@PathVariable page: Int, @PathVariable size: Int): List<Article> {
        return articleService.findAll(PageRequest(page - 1, size)).toList()
    }

}

