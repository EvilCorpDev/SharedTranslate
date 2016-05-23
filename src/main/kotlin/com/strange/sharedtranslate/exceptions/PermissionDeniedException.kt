package com.strange.sharedtranslate.exceptions

/**
 * Exception that will be thrown when user made denied action
 *
 * Created by Notebook on 21.05.2016.
 */
class PermissionDeniedException(val msg: String) : Throwable(msg)