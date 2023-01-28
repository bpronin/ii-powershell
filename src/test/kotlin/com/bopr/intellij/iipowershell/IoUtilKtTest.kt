package com.bopr.intellij.iipowershell

import com.bopr.intellij.iipowershell.util.canExecute
import com.bopr.intellij.iipowershell.util.environmentalPath
import com.bopr.intellij.iipowershell.util.path
import org.junit.Assert.*
import org.junit.Test

class IoUtilKtTest {

    @Test
    fun testFindAbsolutePath() {
        val path = path("C:/Program Files/PowerShell/7/pwsh.exe")
        assertEquals(path, path.environmentalPath())
        assertEquals(path, path("pwsh.exe").environmentalPath())
        assertEquals(path("pwsh"), path("pwsh").environmentalPath())
        assertEquals(path("C:/pwsh"), path("C:/pwsh").environmentalPath())
    }

    @Test
    fun testCanExecute() {
        assertTrue(path("C:/Program Files/PowerShell/7/pwsh.exe").canExecute())
        assertTrue(path("C:/Program Files/PowerShell/7/pwsh").canExecute())
        assertTrue(path("pwsh.exe").canExecute())
        assertTrue(path("pwsh").canExecute())
        assertFalse(path("C:/Program Files/").canExecute())
        assertFalse(path("C:/Program Files/PowerShell/7/pwsh.").canExecute())
        assertFalse(path("C:/Program Files/PowerShell").canExecute())
        assertFalse(path("pwsh2").canExecute())
        assertFalse(path("pwsh.").canExecute())
        assertFalse(path("*.*").canExecute())
    }

}