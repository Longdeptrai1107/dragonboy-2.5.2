param(
    [switch]$Debug,
    [ValidateRange(1024,65535)][int]$Port = 5005,
    [string]$JavaHome = ''
)
$ErrorActionPreference = 'Stop'
$candidates = @()
if ($JavaHome) { $candidates += (Join-Path $JavaHome 'bin\java.exe') }
$candidates += (Join-Path $PSScriptRoot 'toolchain\java\bin\java.exe')
if ($env:JAVA_HOME) { $candidates += (Join-Path $env:JAVA_HOME 'bin\java.exe') }
$installedJava = Get-Command java -ErrorAction SilentlyContinue
if ($installedJava) { $candidates += $installedJava.Source }
$javaExecutable = $candidates | Where-Object { $_ -and (Test-Path -LiteralPath $_ -PathType Leaf) } | Select-Object -First 1
if (-not $javaExecutable) { throw 'JDK 17+ not found. Set JAVA_HOME or pass -JavaHome.' }
$selectedJavaHome = Split-Path -Parent (Split-Path -Parent $javaExecutable)
$buildArguments = @('-NoProfile', '-ExecutionPolicy', 'Bypass', '-File', (Join-Path $PSScriptRoot 'eclipse-build.ps1'), '-JavaHome', $selectedJavaHome)
$output = 'dist'
if ($Debug) { $buildArguments += '-DebugSymbols'; $output = 'debug-dist' }
& powershell.exe @buildArguments
if ($LASTEXITCODE -ne 0) { throw "Build failed (exit $LASTEXITCODE)." }
$javaArguments = @('-Xmx512m')
if ($Debug) {
    $javaArguments += "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=127.0.0.1:$Port"
    Write-Host "Waiting for the debugger on 127.0.0.1:$Port. Attach Eclipse and resume (F8)."
}
$javaArguments += @('-cp', (Join-Path $PSScriptRoot 'lib\microemulator-2.0.4.jar'), 'org.microemu.app.Main', '--rms', 'memory', (Join-Path $PSScriptRoot "$output\DragonBoy252.jar"))
& $javaExecutable @javaArguments
exit $LASTEXITCODE
