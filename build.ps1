$ErrorActionPreference = 'Stop'
& powershell.exe -NoProfile -ExecutionPolicy Bypass -File (Join-Path $PSScriptRoot 'eclipse-build.ps1') @args
exit $LASTEXITCODE
