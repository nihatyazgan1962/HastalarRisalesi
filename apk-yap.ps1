# Windows (PowerShell): Android SDK + Gradle kurar, APK'yı derler.
# Her şey %USERPROFILE%\gemini klasörüne konur. Klasörü değiştirmek için $Klasor parametresi verilebilir.
param([string]$Klasor)
$ErrorActionPreference = "Stop"
if ($Klasor) { $Gemini = $Klasor } else { $Gemini = Join-Path $env:USERPROFILE "gemini" }
$Proje = Split-Path -Parent $MyInvocation.MyCommand.Path
$GradleVer = "8.7"
$Cmdline = "11076708"
$ProgressPreference = "SilentlyContinue"

if (-not (Get-Command java -ErrorAction SilentlyContinue)) {
  Write-Host "HATA: JDK 17 kurulu degil. Once JDK 17 kurun (https://adoptium.net)."
  exit 1
}

# Android SDK tespiti (Mevcut SDK varsa tekrar indirme)
if (-not $Klasor -and (Test-Path "$env:LOCALAPPDATA\Android\Sdk\platforms\android-34")) {
  $Sdk = "$env:LOCALAPPDATA\Android\Sdk"
  Write-Host ">> Mevcut Android SDK kullaniliyor: $Sdk"
} elseif (Test-Path (Join-Path $Gemini "android-sdk")) {
  $Sdk = Join-Path $Gemini "android-sdk"
} else {
  $Sdk = Join-Path $Gemini "android-sdk"
  New-Item -ItemType Directory -Force -Path $Gemini, "$Sdk\cmdline-tools" | Out-Null
  $sdkmanager = "$Sdk\cmdline-tools\latest\bin\sdkmanager.bat"
  if (-not (Test-Path $sdkmanager)) {
    Write-Host ">> Android komut satiri araclari indiriliyor..."
    Invoke-WebRequest "https://dl.google.com/android/repository/commandlinetools-win-${Cmdline}_latest.zip" -OutFile "$Gemini\cmdline.zip"
    Expand-Archive "$Gemini\cmdline.zip" -DestinationPath "$Gemini\tmp-cmdline" -Force
    if (Test-Path "$Sdk\cmdline-tools\latest") { Remove-Item "$Sdk\cmdline-tools\latest" -Recurse -Force }
    Move-Item "$Gemini\tmp-cmdline\cmdline-tools" "$Sdk\cmdline-tools\latest"
    Remove-Item "$Gemini\tmp-cmdline", "$Gemini\cmdline.zip" -Recurse -Force
  }
  Write-Host ">> SDK paketleri kuruluyor (lisanslar kabul ediliyor)..."
  ("y`n" * 30) | & $sdkmanager --licenses | Out-Null
  & $sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
}

$env:ANDROID_HOME = $Sdk
"sdk.dir=" + ($Sdk -replace '\\', '/') | Out-File -Encoding ascii "$Proje\local.properties"
Set-Location $Proje

# Gradle çalıştırma
$gradlew = Join-Path $Proje "gradlew.bat"
if (Test-Path $gradlew) {
  $gradleCmd = $gradlew
} else {
  $gradle = "$Gemini\gradle-$GradleVer\bin\gradle.bat"
  if (-not (Test-Path $gradle)) {
    Write-Host ">> Gradle indiriliyor..."
    Invoke-WebRequest "https://services.gradle.org/distributions/gradle-$GradleVer-bin.zip" -OutFile "$Gemini\gradle.zip"
    Expand-Archive "$Gemini\gradle.zip" -DestinationPath $Gemini -Force
    Remove-Item "$Gemini\gradle.zip"
  }
  $gradleCmd = $gradle
}

Write-Host ">> APK derleniyor (Release + Debug)..."
& $gradleCmd assembleRelease assembleDebug --no-daemon

New-Item -ItemType Directory -Force -Path $Gemini | Out-Null

if (Test-Path "app\build\outputs\apk\release\app-release.apk") {
  Copy-Item "app\build\outputs\apk\release\app-release.apk" "HastalarRisalesi-release.apk" -Force
  Copy-Item "app\build\outputs\apk\release\app-release.apk" "$Gemini\HastalarRisalesi-release.apk" -Force
}

if (Test-Path "app\build\outputs\apk\debug\app-debug.apk") {
  Copy-Item "app\build\outputs\apk\debug\app-debug.apk" "HastalarRisalesi-debug.apk" -Force
  Copy-Item "app\build\outputs\apk\debug\app-debug.apk" "$Gemini\HastalarRisalesi-debug.apk" -Force
}

Write-Host ""
Write-Host "TAMAM. Uretilen APK dosyalari:"
if (Test-Path "HastalarRisalesi-release.apk") {
  Write-Host "Release APK (Imzali): $Proje\HastalarRisalesi-release.apk"
}
if (Test-Path "HastalarRisalesi-debug.apk") {
  Write-Host "Debug APK: $Proje\HastalarRisalesi-debug.apk"
}
