# Single line comment

<########################
# Delimited comment     #
#########################>

$include = @("*.mkv", "*.mp4")

Import-Module -Name $PSScriptRoot\ffmpeg

class Track
{
    [Int]$Index
}

function Read-Time
{
    param (
        [String]$String
    )
    process {
        $timeString = $String.Trim()
        if ($timeString.split(":").count -eq 2)
        {
            $timeString = "00:$timeString"
        }
        return [TimeSpan]$timeString
    }
}

# SIG # Begin signature block
# MIInzAYJKoZIhvcNAQcCoIInvTCCJ7kCAQExDzANBglghkgBZQMEAgEFADB5Bgor
# ...
# x+GhsHxy2QDYkrJBh8Rlcw==
# SIG # End signature block