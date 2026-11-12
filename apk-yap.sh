#!/usr/bin/env bash
# Mac / Linux: Android SDK + Gradle kurar, APK'yı derler.
# Her şey ~/gemini klasörüne konur. Klasörü değiştirmek için GEMINI'yi düzenleyin.
set -e
GEMINI="${1:-$HOME/gemini}"   # istersen: bash apk-yap.sh /klasor/yolu
PROJE="$(cd "$(dirname "$0")" && pwd)"
SDK="$GEMINI/android-sdk"
GRADLE_VER="8.7"
CMDLINE="11076708"

command -v java >/dev/null || { echo "HATA: JDK 17 kurulu değil. Önce JDK 17 kurun (https://adoptium.net)."; exit 1; }
mkdir -p "$GEMINI" "$SDK/cmdline-tools"

case "$(uname -s)" in
  Darwin) OS=mac ;;
  *) OS=linux ;;
esac

if [ ! -x "$SDK/cmdline-tools/latest/bin/sdkmanager" ]; then
  echo ">> Android komut satırı araçları indiriliyor..."
  curl -L -o "$GEMINI/cmdline.zip" "https://dl.google.com/android/repository/commandlinetools-$OS-${CMDLINE}_latest.zip"
  unzip -q -o "$GEMINI/cmdline.zip" -d "$GEMINI/tmp-cmdline"
  rm -rf "$SDK/cmdline-tools/latest"
  mv "$GEMINI/tmp-cmdline/cmdline-tools" "$SDK/cmdline-tools/latest"
  rm -rf "$GEMINI/tmp-cmdline" "$GEMINI/cmdline.zip"
fi

export ANDROID_HOME="$SDK"
echo ">> SDK paketleri kuruluyor (lisanslar kabul ediliyor)..."
yes | "$SDK/cmdline-tools/latest/bin/sdkmanager" --licenses >/dev/null || true
"$SDK/cmdline-tools/latest/bin/sdkmanager" "platform-tools" "platforms;android-34" "build-tools;34.0.0"

if [ ! -x "$GEMINI/gradle-$GRADLE_VER/bin/gradle" ]; then
  echo ">> Gradle indiriliyor..."
  curl -L -o "$GEMINI/gradle.zip" "https://services.gradle.org/distributions/gradle-$GRADLE_VER-bin.zip"
  unzip -q -o "$GEMINI/gradle.zip" -d "$GEMINI"
  rm "$GEMINI/gradle.zip"
fi

echo "sdk.dir=$SDK" > "$PROJE/local.properties"
cd "$PROJE"
echo ">> APK derleniyor..."
"$GEMINI/gradle-$GRADLE_VER/bin/gradle" assembleDebug --no-daemon

cp app/build/outputs/apk/debug/app-debug.apk "$GEMINI/HastalarRisalesi-debug.apk"
echo ""
echo "TAMAM. APK: $GEMINI/HastalarRisalesi-debug.apk"
