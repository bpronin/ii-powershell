package com.bopr.intellij.iipowershell.language

import java.util.ResourceBundle

object Resources {

    private var bundle: ResourceBundle = ResourceBundle.getBundle("values.strings")

    @JvmStatic
    fun string(key: String): String = bundle.getString(key)
}