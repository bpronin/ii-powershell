# Single line comment
#Requires requirements

[String]$hello = "Hello!"
${found a string} = @"
    Found a string
"@

<#---------------#
 # Block comment #
 #---------------#>

:label switch -Exact (100, "Hello") {
    100 {
        Call-Command -One 100 -Two "text" 6>&1 > "file.txt"
        continue
    }
    $hello {
        break label
    }
    {$_ -is [String] -and $$ -eq 10.5e8} {
        Write-Host ${found a string} | Also-Call("argument")
    }
    default {
        "No matches"
    }
}

# SIG # Begin signature block
# x+GhsHxy2QDYkrJBh8Rlcw==
# SIG # End signature block
