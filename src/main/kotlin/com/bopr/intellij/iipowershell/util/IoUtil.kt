package com.bopr.intellij.iipowershell.util

import com.intellij.openapi.util.SystemInfo
import com.intellij.util.io.exists
import java.nio.file.Path

fun findAbsolutePath(filename: String): Path {
    val path = Path.of(filename)
    if (path.exists()) return path

    System.getenv("PATH")?.run {
        split(if (SystemInfo.isWindows) ";" else ":").forEach { p ->
            Path.of(p, filename).run { if (exists()) return this }
        }
    }

    return path
}