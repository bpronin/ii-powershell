package com.bopr.intellij.iipowershell.util

import java.util.ResourceBundle

object Resources {

    private var bundle: ResourceBundle = ResourceBundle.getBundle("values.strings")

    @JvmStatic
    fun string(key: String): String {
        try {
            return bundle.getString(key)
        } catch (e: Exception) {
            TODO("Not yet implemented")
        }
    }
}