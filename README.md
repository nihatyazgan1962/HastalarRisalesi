# Hastalar Risalesi - Android (Kotlin + Jetpack Compose) v0.2

1. Android Studio'da File > Open ile bu klasörü açın.
2. Gradle sync bittiğinde (gerekirse wrapper/sürüm yükseltme önerisini kabul edin) Run ▶ ile çalıştırın.
3. Metinler app/src/main/java/.../Content.kt içinde YER TUTUCUDUR; izinli metni oraya ekleyin.
   Her bölümün 'moods' alanı, "Bugün nasılsınız?" seçimine göre hangi duygu durumunda önerileceğini belirler.

Özellikler: okuyucu, sesli okuma (Android TTS, Türkçe), günlük bildirim (WorkManager),
duygu durumuna göre öneri, dua + zikirmatik, favoriler, gece modu, çevrimdışı çalışma.
Sesli okuma için telefonda Türkçe TTS ses verisinin yüklü olması gerekir.

## Tek komutla APK (Android Studio'suz)
JDK 17 kurulu olmalı. Proje klasöründe:
- Mac/Linux: `bash apk-yap.sh`
- Windows (PowerShell): `powershell -ExecutionPolicy Bypass -File apk-yap.ps1`
SDK ve Gradle ~/gemini (Windows: %USERPROFILE%\gemini) klasörüne kurulur, APK de oraya konur:
`gemini/HastalarRisalesi-debug.apk`

Başka bir klasör için yol ekleyin: `bash apk-yap.sh /klasor/yolu` veya `powershell -ExecutionPolicy Bypass -File apk-yap.ps1 -Klasor C:\\yol\\Gemini`
