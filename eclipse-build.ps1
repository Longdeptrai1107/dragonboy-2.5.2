param(
    [string]$JavaHome = '',
    [string]$OutputDirectory = '',
    [switch]$DebugSymbols
)
$ErrorActionPreference = 'Stop'
$javaCandidates = @()
if ($JavaHome) { $javaCandidates += (Join-Path $JavaHome 'bin\java.exe') }
$javaCandidates += (Join-Path $PSScriptRoot 'toolchain\java\bin\java.exe')
if ($env:JAVA_HOME) { $javaCandidates += (Join-Path $env:JAVA_HOME 'bin\java.exe') }
$javaCommand = Get-Command java -ErrorAction SilentlyContinue
if ($javaCommand) { $javaCandidates += $javaCommand.Source }
$javaExecutable = $javaCandidates | Where-Object { $_ -and (Test-Path -LiteralPath $_ -PathType Leaf) } | Select-Object -First 1
if (-not $javaExecutable) { throw 'JDK 17+ not found. Set JAVA_HOME or pass -JavaHome.' }
if (-not $OutputDirectory) {
    if ($DebugSymbols) { $OutputDirectory = Join-Path $PSScriptRoot 'debug-dist' }
    else { $OutputDirectory = Join-Path $PSScriptRoot 'dist' }
}
$mode = 'release'
if ($DebugSymbols) { $mode = 'debug' }
& $javaExecutable '-Dfile.encoding=UTF-8' (Join-Path $PSScriptRoot 'toolchain\StandaloneJarBuild.java') $PSScriptRoot $OutputDirectory $mode
if ($LASTEXITCODE -ne 0) { throw "Java ME JAR build failed (exit $LASTEXITCODE)." }
exit 0
