package com.bopr.intellij.iipowershell.util

import com.intellij.openapi.util.JDOMExternalizerUtil
import com.intellij.util.io.systemIndependentPath
import org.jdom.Element
import java.nio.file.Path

fun writePath(element: Element, fieldName: String, path: Path) {
    JDOMExternalizerUtil.writeField(element, fieldName, path.systemIndependentPath)
}

fun readPath(element: Element, fieldName: String): Path {
    return path(JDOMExternalizerUtil.readField(element, fieldName, ""))
}
