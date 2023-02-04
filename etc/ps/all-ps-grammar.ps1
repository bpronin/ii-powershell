#

# Single line comment

#Requires something

<########################
# Delimited comment     #
#########################>

# Operators and punctuators
{   }   [   ]   (   )   @(   @{   $(  ; &&  ||  &   |   ,   ++  ..   ::   .  !   *   /   %   + - --

= -= += *= /= %=

>  >>  2>  2>>  3>  3>>  4>  4>> 5>  5>>  6>  6>>  *>  *>>  <

*>&1  2>&1  3>&1  4>&1  5>&1  6>&1 *>&2  1>&2  3>&2  4>&2  5>&2  6>&2

-f

-and -band -bnot -bor -bxor -not -or -xor

-as           -ccontains      -ceq
-cge          -cgt            -cle
-clike        -clt            -cmatch
-cne          -cnotcontains   -cnotlike
-cnotmatch    -contains       -creplace
-csplit       -eq             -ge
-gt           -icontains      -ieq
-ige          -igt            -ile
-ilike        -ilt            -imatch
-in           -ine            -inotcontains
-inotlike     -inotmatch      -ireplace
-is           -isnot          -isplit
-join         -le             -like
-lt           -match          -ne
-notcontains  -notin          -notlike
-notmatch     -replace        -shl
-shr          -split

# Variables
$$ $? $^ $_
$variable
$Variable23
$global:variable
$namespace:variable
$variable?
${ this is also a `variable?` }
@global:variable

# Integer literals
123
10
123L
10kb
123lPB
123LPB
0x12FFF
0x12FFFkb
+10
+10L
-0x12FFF
-20d
-20D

# Real literals
123.55
.58
1.2e5
1.2e5l
1.2E5l
1.2e5dKB
1e23
2e5dKB
+123.55
-123.55

# String literals
#" s "
#"string"

# Type literals
[string]
[int[]]
[int[,,,]]
[int[string,string[int]]]

# Expressions
if $token return $token

# SIG # Begin signature block
# MIInzAYJKoZIhvcNAQcCoIInvTCCJ7kCAQExDzANBglghkgBZQMEAgEFADB5Bgor
# ...
# x+GhsHxy2QDYkrJBh8Rlcw==
# SIG # End signature block