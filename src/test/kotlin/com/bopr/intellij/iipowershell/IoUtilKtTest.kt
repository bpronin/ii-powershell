package com.bopr.intellij.iipowershell

import com.bopr.intellij.iipowershell.util.findAbsolutePath
import com.intellij.util.io.exists
import org.junit.Test
import kotlin.io.path.isExecutable

class IoUtilKtTest {

    @Test
    fun testFindAbsolutePath() {
        val path = findAbsolutePath("pwsh.exe")
//        val path = findAbsolutePath("C:\\Program Files\\PowerShell\\7\\pwsh.exe")
//        val path = findAbsolutePath("lndffdsn.exe")
        println(path)
        println(path.exists())
        println(path.isExecutable())
    }

}