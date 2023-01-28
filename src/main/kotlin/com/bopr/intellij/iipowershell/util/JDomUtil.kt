package com.bopr.intellij.iipowershell.util

import com.intellij.openapi.util.JDOMExternalizerUtil
import com.intellij.openapi.util.io.FileUtil
import org.jdom.Element

fun writePath(element: Element, fieldName: String, path: String) {
    val systemIndependentPath = FileUtil.toSystemIndependentName(path)
    val isSystemIndependentPath = (systemIndependentPath == path).toString()
    JDOMExternalizerUtil.writeField(element, "independent_$fieldName", isSystemIndependentPath)
    JDOMExternalizerUtil.writeField(element, fieldName, systemIndependentPath)
}

fun readPath(element: Element, fieldName: String): String {
    val systemIndependentPath = JDOMExternalizerUtil.readField(element, fieldName, "")
    val isSystemIndependentPath = JDOMExternalizerUtil.readField(
        element, "independent_$fieldName"
    ).toBoolean()
    return if (isSystemIndependentPath) systemIndependentPath else FileUtil.toSystemDependentName(systemIndependentPath)
}
