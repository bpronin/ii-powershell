package com.bopr.intellij.iipowershell.util

import com.intellij.openapi.util.SystemInfo
import com.intellij.util.io.exists
import java.nio.file.Path

fun findAbsolutePath(path: Path): Path {
    if (path.exists()) return path

    System.getenv("PATH")?.run {
        split(if (SystemInfo.isWindows) ";" else ":").forEach { root ->
            Path.of(root).resolve(path).run { if (exists()) return this }
        }
    }

    return path
}