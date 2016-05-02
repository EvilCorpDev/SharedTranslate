package com.strange.sharedtranslate.exceptions

/**
 * Throwing when app can't find some entity for performing operation
 *
 * Created by Notebook on 02.05.2016.
 */
class EntityNotFoundException(val msg: String): Throwable(msg)