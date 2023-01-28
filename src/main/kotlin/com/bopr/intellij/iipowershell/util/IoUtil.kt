package com.bopr.intellij.iipowershell.util

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.util.SystemInfo.isWindows
import com.intellij.util.io.exists
import com.intellij.util.io.isFile
import java.io.BufferedReader
import java.nio.file.InvalidPathException
import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.isExecutable
import kotlin.io.path.pathString

private val log = Logger.getInstance("IoUtils")

val EMPTY_PATH: Path = Path.of("")

private fun Path.internalCanExecute(): Boolean {
    environmentalPath().let {
        return it.isFile() && it.isExecutable()
    }
}

fun Path.canExecute(): Boolean {
    if (internalCanExecute()) {
        return true
    }

    if (extension.isEmpty()) {
        return path("$pathString.exe").internalCanExecute()
    }

    return false
}

fun Path.environmentalPath(): Path {
    if (exists()) return this

    System.getenv("PATH")?.let { variable ->
        val items = variable.split(if (isWindows) ";" else ":")
        items.forEach { item ->
            path(item).resolve(this).let { absolutePath ->
                if (absolutePath.exists()) return absolutePath
            }
        }
    }

    return this
}

fun path(path: String?): Path {
    return try {
        Path.of(path?.trim() ?: "")
    } catch (e: InvalidPathException) {
        log.debug(e)
        EMPTY_PATH
    }
}

fun detectPowerShellVersion(path: Path): String? {
    val signature = "i-am-really-powershell"
    val process = try {
        ProcessBuilder(
            path.toString(), "-Command",
            "Write-Host('$signature');",
            "Write-Host(\$PSVersionTable.PSVersion);"
        )
            .redirectErrorStream(true)
            .start()
    } catch (e: Exception) {
        log.debug(e)
        return null
    }

    val reader = BufferedReader(process.inputStream.reader())
    try {
        if (signature != reader.readLine()) return null
        return reader.readLine()
    } finally {
        reader.close()
        process.waitFor()
    }
}