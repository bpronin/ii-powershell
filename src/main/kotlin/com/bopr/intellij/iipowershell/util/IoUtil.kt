package com.bopr.intellij.iipowershell.util

import com.intellij.openapi.util.SystemInfo
import com.intellij.util.io.exists
import java.io.BufferedReader
import java.nio.file.Path
import java.util.concurrent.TimeUnit

fun findAbsolutePath(path: Path): Path {
    if (path.exists()) return path

    System.getenv("PATH")?.run {
        split(if (SystemInfo.isWindows) ";" else ":").forEach { root ->
            Path.of(root).resolve(path).run { if (exists()) return this }
        }
    }

    return path
}

fun detectPowerShellVersion(path: Path): String? {
    val signature = "i-am-really-the-powershell"
    val process = try {
        ProcessBuilder(
            path.toString(), "-Command",
            "Write-Host('$signature');", "Write-Host(\$PSVersionTable.PSVersion);"
        )
            .redirectErrorStream(true)
            .start()
    } catch (e: Exception) {
        return null
    }

    val reader = BufferedReader(process.inputStream.reader())
    try {
        if (signature != reader.readLine()) return null
        return reader.readLine()
    } finally {
        reader.close()
        process.waitFor(3, TimeUnit.SECONDS)
    }
}