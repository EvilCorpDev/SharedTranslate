package com.strange.sharedtranslate.utils

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer
import org.springframework.boot.context.embedded.ErrorPage
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

/**
 * Created by Notebook on 22.05.2016.
 */
@Component
class ErrorHandler : EmbeddedServletContainerCustomizer {

    override fun customize(container: ConfigurableEmbeddedServletContainer?) {
        container?.addErrorPages(ErrorPage(HttpStatus.NOT_FOUND, "/error"));
    }


}