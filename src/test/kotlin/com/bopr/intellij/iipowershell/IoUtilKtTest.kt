package com.bopr.intellij.iipowershell

import com.bopr.intellij.iipowershell.util.findAbsolutePath
import com.intellij.util.io.exists
import com.intellij.util.io.isDirectory
import com.intellij.util.io.isFile
import org.junit.Test
import java.nio.file.Path
import kotlin.io.path.isExecutable
import kotlin.io.path.name

class IoUtilKtTest {

    @Test
    fun testFindAbsolutePath() {
        val path = Path.of("C:/Windows\\")
//        val path = Path.of("pwsh.exe")
//        val path = Path.of("")
//        println("The path is: ${root.resolve(path)}")
////        val path = findAbsolutePath("C:\\Program Files\\PowerShell\\7\\pwsh.exe")
////        val path = findAbsolutePath("lndffdsn.exe")
//        val path = findAbsolutePath(Path.of(""))
        println(path)
        println(path.name)
        println(path.isFile())
        println(path.isDirectory())
        println(path.exists())
        println(path.isExecutable())
    }

}