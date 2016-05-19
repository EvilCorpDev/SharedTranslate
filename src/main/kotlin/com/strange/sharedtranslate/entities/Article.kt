package com.strange.sharedtranslate.entities

import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by Zakhar_Kliap on 17-May-16.
 */
@Document(collection = "articles")
data class Article(val title: String, val uploader: String, val cover: String = "http://res.cloudinary.com/dqgpcdccu/image/upload/v1463658927/no_image_sfqujg.png",
                   val description: String = "", val id: String? = null)