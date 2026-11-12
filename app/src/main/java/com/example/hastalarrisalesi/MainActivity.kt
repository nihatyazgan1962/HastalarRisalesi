package com.example.hastalarrisalesi

import android.Manifest
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.compose.foundation.gestures.scrollBy
import java.util.Locale
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Notifier.ensureChannel(this)
        val prefs = Prefs(this)
        setContent { App(prefs) }
    }
}

private val tabs = listOf("Bugün", "Devâlar", "Lügatçe", "Dua", "Favoriler")
private val icons = listOf("☀", "📖", "📚", "🤲", "★")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(prefs: Prefs) {
    val ctx = LocalContext.current
    var dark by remember { mutableStateOf(prefs.dark) }
    var font by remember { mutableFloatStateOf(prefs.fontSize) }
    var favs by remember { mutableStateOf(prefs.favorites) }
    var tab by remember { mutableIntStateOf(0) }
    var open by remember { mutableStateOf<Section?>(null) }
    var showSettings by remember { mutableStateOf(false) }
    var notif by remember { mutableStateOf(prefs.notif) }
    var hour by remember { mutableIntStateOf(prefs.hour) }
    var rate by remember { mutableFloatStateOf(prefs.speechRate) }

    var showFihrist by remember { mutableStateOf(false) }

    BackHandler(enabled = open != null) { open = null }

    fun toggleFav(id: String) {
        favs = if (id in favs) favs - id else favs + id
        prefs.favorites = favs
    }

    val permLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        notif = granted
        prefs.notif = granted
        if (granted) Notifier.schedule(ctx, hour)
    }

    fun setNotif(on: Boolean) {
        if (on) {
            Notifier.ensureChannel(ctx)
            val needsPerm = Build.VERSION.SDK_INT >= 33 &&
                ContextCompat.checkSelfPermission(ctx, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED
            if (needsPerm) {
                permLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                notif = true; prefs.notif = true; Notifier.schedule(ctx, hour)
            }
        } else {
            notif = false; prefs.notif = false; Notifier.cancel(ctx)
        }
    }

    fun changeHour(h: Int) {
        hour = h.coerceIn(6, 22)
        prefs.hour = hour
        if (notif) Notifier.schedule(ctx, hour)
    }

    MaterialTheme(colorScheme = if (dark) darkColorScheme() else lightColorScheme()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        if (open != null) {
                            IconButton(onClick = { open = null }) {
                                Text("←", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    },
                    title = {
                        Text(
                            text = open?.title ?: "Hastalar Risalesi",
                            maxLines = 1
                        )
                    },
                    actions = {
                        TextButton(onClick = { showFihrist = true }) {
                            Text("📑 Fihrist", fontWeight = FontWeight.Bold)
                        }
                        TextButton(onClick = { showSettings = true }) { Text("⚙") }
                        TextButton(onClick = { dark = !dark; prefs.dark = dark }) {
                            Text(if (dark) "☀" else "🌙")
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    tabs.forEachIndexed { i, t ->
                        NavigationBarItem(
                            selected = tab == i && open == null,
                            onClick = { tab = i; open = null },
                            icon = { Text(icons[i]) },
                            label = { Text(t) }
                        )
                    }
                }
            }
        ) { pad ->
            Box(Modifier.padding(pad).fillMaxSize()) {
                val current = open
                if (current != null) {
                    Reader(
                        section = current,
                        font = font,
                        rate = rate,
                        isFav = current.id in favs,
                        onFont = { font = it.coerceIn(14f, 34f); prefs.fontSize = font },
                        onFav = { toggleFav(current.id) },
                        onBack = { open = null },
                        onOpenFihrist = { showFihrist = true },
                        onSelectSection = { open = it }
                    )
                } else when (tab) {
                    0 -> Today { open = it }
                    1 -> SectionList(sections, favs) { open = it }
                    2 -> LugatScreen()
                    3 -> DuaScreen(prefs)
                    else -> SectionList(sections.filter { it.id in favs }, favs, "Henüz favori eklenmedi.") { open = it }
                }
            }
        }

        if (showFihrist) {
            FihristDialog(
                currentSection = open,
                onDismiss = { showFihrist = false },
                onSelect = { sec ->
                    open = sec
                    showFihrist = false
                }
            )
        }

        if (showSettings) {
            AlertDialog(
                onDismissRequest = { showSettings = false },
                confirmButton = { TextButton(onClick = { showSettings = false }) { Text("Tamam") } },
                title = { Text("Ayarlar") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Günlük teselli bildirimi", Modifier.weight(1f))
                            Switch(checked = notif, onCheckedChange = { setNotif(it) })
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Bildirim Saati: %02d:00".format(hour), Modifier.weight(1f))
                            OutlinedButton(onClick = { changeHour(hour - 1) }) { Text("−") }
                            Spacer(Modifier.width(8.dp))
                            OutlinedButton(onClick = { changeHour(hour + 1) }) { Text("+") }
                        }
                        Column {
                            Text("Robot TTS Okuma Hızı: %.1fx".format(rate))
                            Slider(
                                value = rate,
                                onValueChange = { rate = it; prefs.speechRate = it },
                                valueRange = 0.5f..1.5f
                            )
                        }
                        Text(
                            "Orijinal ses kayıtları Merhum Nazım Gökçek'in seslendirmesidir.",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Today(onOpen: (Section) -> Unit) {
    val s = remember { todaySection() }
    var selectedCat by remember { mutableStateOf<String?>(null) }
    
    Column(
        Modifier.verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Günün Teselli Notu", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Card(
            Modifier.fillMaxWidth().clickable { onOpen(s) },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(Modifier.padding(18.dp)) {
                Text(s.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                if (s.profile.isNotEmpty()) {
                    Spacer(Modifier.height(4.dp))
                    Text(s.profile, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                }
                Spacer(Modifier.height(8.dp))
                Text(s.body.take(180) + "...", fontSize = 16.sp, lineHeight = 24.sp)
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Devamını Oku & Dinle", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    if (s.audioRes != null) {
                        Spacer(Modifier.width(8.dp))
                        SuggestionChip(onClick = { onOpen(s) }, label = { Text("🎙️ Nazım Gökçek") })
                    }
                }
            }
        }

        Text("Ruh Halinize & İhtiyacınıza Göre Devâlar", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        
        Row(
            Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { cat ->
                FilterChip(
                    selected = selectedCat == cat.id,
                    onClick = { selectedCat = if (selectedCat == cat.id) null else cat.id },
                    label = { Text("${cat.icon} ${cat.name}") }
                )
            }
        }

        selectedCat?.let { catId ->
            val cat = categories.find { it.id == catId }
            if (cat != null) {
                Text(
                    cat.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                val matched = sections.filter { it.categoryId == catId }
                matched.forEach { sec ->
                    Card(Modifier.fillMaxWidth().clickable { onOpen(sec) }) {
                        Column(Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(sec.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                                if (sec.audioRes != null) {
                                    Text("🎙️", fontSize = 14.sp)
                                }
                            }
                            Spacer(Modifier.height(4.dp))
                            Text(
                                sec.profile,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(sec.profileDesc, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }

        Text(
            "Bu uygulama manevi bir reçete ve teselli amaçlıdır; tıbbi tedavi yerine geçmez.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun SectionList(
    list: List<Section>,
    favs: Set<String>,
    empty: String = "",
    onOpen: (Section) -> Unit
) {
    if (list.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(empty) }
        return
    }
    LazyColumn(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item { Spacer(Modifier.height(6.dp)) }
        items(list, key = { it.id }) { s ->
            Card(Modifier.fillMaxWidth().clickable { onOpen(s) }) {
                Column(Modifier.padding(16.dp)) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(s.title, Modifier.weight(1f), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        if (s.audioRes != null) {
                            Text("🎙️ ", fontSize = 14.sp)
                        }
                        if (s.id in favs) {
                            Text("★", color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    if (s.profile.isNotEmpty()) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            s.profile,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    if (s.profileDesc.isNotEmpty()) {
                        Spacer(Modifier.height(2.dp))
                        Text(
                            s.profileDesc,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
        item { Spacer(Modifier.height(6.dp)) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reader(
    section: Section,
    font: Float,
    rate: Float,
    isFav: Boolean,
    onFont: (Float) -> Unit,
    onFav: () -> Unit,
    onBack: () -> Unit,
    onOpenFihrist: () -> Unit,
    onSelectSection: (Section) -> Unit
) {
    var selectedWordEntry by remember { mutableStateOf<LugatEntry?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val currentIndex = remember(section) { sections.indexOfFirst { it.id == section.id } }
    val prevSection = if (currentIndex > 0) sections[currentIndex - 1] else null
    val nextSection = if (currentIndex >= 0 && currentIndex < sections.size - 1) sections[currentIndex + 1] else null

    val scrollState = rememberScrollState()
    var isAutoScrollEnabled by remember { mutableStateOf(false) }
    var scrollSpeed by remember { mutableFloatStateOf(1f) } // 1x, 1.5x, 2x

    val coroutineScope = rememberCoroutineScope()

    // Otomatik sürekli yavaşça yukarı kaydırma döngüsü (Auto-Scroll)
    LaunchedEffect(isAutoScrollEnabled, scrollSpeed) {
        while (isAutoScrollEnabled) {
            val step = (2 * scrollSpeed).toInt().coerceAtLeast(1)
            if (scrollState.value < scrollState.maxValue) {
                scrollState.scrollBy(step.toFloat())
            } else {
                isAutoScrollEnabled = false
            }
            delay(40) // ~25 FPS akıcı ve göz yormayan kaydırma
        }
    }

    Column(Modifier.verticalScroll(scrollState).padding(20.dp)) {
        // Üst Hızlı Gezinme Çubuğu: Geri Çık, Fihrist Aç
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledTonalButton(onClick = onBack) {
                Text("← Geri Çık")
            }
            Button(onClick = onOpenFihrist) {
                Text("📑 Fihrist")
            }
        }

        Spacer(Modifier.height(10.dp))

        if (section.profile.isNotEmpty()) {
            Card(
                Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(Modifier.padding(14.dp)) {
                    Text(
                        section.profile,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        section.profileDesc,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
        }

        // Seslendirme çubuğu (Nazım Gökçek stüdyo kaydı veya TTS)
        AudioPlayerBar(
            audioResName = section.audioRes,
            text = section.body,
            rate = rate,
            onProgress = { fraction ->
                // Ses çaldıkça sayfayı orantılı olarak yavaşça yukarı kaydır
                coroutineScope.launch {
                    val target = (fraction * scrollState.maxValue).toInt()
                    if (target > scrollState.value) {
                        scrollState.animateScrollTo(target)
                    }
                }
            }
        )
        
        Spacer(Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { onFont(font - 2f) }) { Text("A−") }
            OutlinedButton(onClick = { onFont(font + 2f) }) { Text("A+") }
            
            // Otomatik Kaydırma (Auto-Scroll) Aç/Kapa Düğmesi
            FilledTonalButton(
                onClick = { isAutoScrollEnabled = !isAutoScrollEnabled }
            ) {
                Text(if (isAutoScrollEnabled) "⏸ Kaymayı Durdur" else "📜 Otomatik Kaydır")
            }

            Spacer(Modifier.weight(1f))
            OutlinedButton(onClick = onFav) { Text(if (isFav) "★ Favori" else "☆ Favori") }
        }

        if (isAutoScrollEnabled) {
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Kaydırma Hızı:", style = MaterialTheme.typography.labelMedium)
                listOf(0.5f to "Yavaş", 1f to "Normal", 1.8f to "Hızlı").forEach { (speed, label) ->
                    FilterChip(
                        selected = scrollSpeed == speed,
                        onClick = { scrollSpeed = speed },
                        label = { Text(label) }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // İnteraktif lügatçeli metin: kelimelere tıklanabilir
        LugatInteractiveText(
            text = section.body,
            fontSize = font,
            onWordClick = { entry ->
                selectedWordEntry = entry
            }
        )

        Spacer(Modifier.height(24.dp))
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        Spacer(Modifier.height(16.dp))

        // Alt Devalar Arası Geçiş Düğmeleri (Önceki Deva - Fihrist - Sonraki Deva)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (prevSection != null) {
                OutlinedButton(
                    onClick = { onSelectSection(prevSection) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("← Önceki", maxLines = 1)
                }
            } else {
                Spacer(Modifier.weight(1f))
            }

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = onOpenFihrist,
                modifier = Modifier.weight(1.1f)
            ) {
                Text("📑 Fihrist", maxLines = 1)
            }

            Spacer(Modifier.width(8.dp))

            if (nextSection != null) {
                OutlinedButton(
                    onClick = { onSelectSection(nextSection) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Sonraki →", maxLines = 1)
                }
            } else {
                Spacer(Modifier.weight(1f))
            }
        }
        Spacer(Modifier.height(16.dp))
    }

    // Kelimeye basıldığında açılan lügat alt kartı (BottomSheet)
    selectedWordEntry?.let { entry ->
        ModalBottomSheet(
            onDismissRequest = { selectedWordEntry = null },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = entry.word,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    entry.origin?.let {
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelMedium,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Lügat Manası:",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.outline
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = entry.meaning,
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = 24.sp
                )

                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = { selectedWordEntry = null },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Tamam")
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

/**
 * Fihrist Diyaloğu: Kullanıcının paylaştığı resimdeki gibi (1. Deva, 2. Deva, 3. Deva...)
 * şık bir liste halinde devaları sıralar ve tıklandığında anında o devaya geçişi sağlar.
 */
@Composable
fun FihristDialog(
    currentSection: Section?,
    onDismiss: () -> Unit,
    onSelect: (Section) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.82f)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Fihrist Başlığı (Resimdeki gibi koyu başlık alanı ve vurgu çizgisi)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Fihrist",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .width(60.dp)
                                .height(3.dp)
                                .background(MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.small)
                        )
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                // Devalar Listesi (1. Deva, 2. Deva, 3. Deva...)
                val fihristList = remember {
                    sections.map { sec ->
                        val displayTitle = when {
                            sec.title.startsWith("Mukaddeme") -> "Mukaddeme"
                            sec.title.contains("Devâ") -> {
                                val num = when {
                                    sec.title.startsWith("Birinci") -> "1"
                                    sec.title.startsWith("İkinci") -> "2"
                                    sec.title.startsWith("Üçüncü") -> "3"
                                    sec.title.startsWith("Dördüncü") -> "4"
                                    sec.title.startsWith("Beşinci") -> "5"
                                    sec.title.startsWith("Altıncı") -> "6"
                                    sec.title.startsWith("Yedinci") -> "7"
                                    sec.title.startsWith("Sekizinci") -> "8"
                                    sec.title.startsWith("Dokuzuncu") -> "9"
                                    sec.title.startsWith("Onuncu") -> "10"
                                    sec.title.startsWith("Onbirinci") -> "11"
                                    sec.title.startsWith("Onikinci") -> "12"
                                    sec.title.startsWith("Onüçüncü") -> "13"
                                    sec.title.startsWith("Ondördüncü") -> "14"
                                    sec.title.startsWith("Onbeşinci") -> "15"
                                    sec.title.startsWith("Onaltıncı") -> "16"
                                    sec.title.startsWith("Onyedinci") -> "17"
                                    sec.title.startsWith("Onsekizinci") -> "18"
                                    sec.title.startsWith("Ondokuzuncu") -> "19"
                                    sec.title.startsWith("Yirminci") -> "20"
                                    sec.title.startsWith("Yirmibirinci") -> "21"
                                    sec.title.startsWith("Yirmiikinci") -> "22"
                                    sec.title.startsWith("Yirmiüçüncü") -> "23"
                                    sec.title.startsWith("Yirmidördüncü") -> "24"
                                    sec.title.startsWith("Yirmibeşinci") -> "25"
                                    else -> ""
                                }
                                if (num.isNotEmpty()) "$num. Deva" else sec.title
                            }
                            sec.title.contains("Zeyl") -> "Zeyl"
                            else -> sec.title
                        }
                        Pair(sec, displayTitle)
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(fihristList) { (sec, label) ->
                        val isSelected = currentSection?.id == sec.id
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelect(sec) }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp, vertical = 13.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 18.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) Color(0xFFC02222) else MaterialTheme.colorScheme.onSurface
                                )
                                if (isSelected) {
                                    Text(
                                        "● Şu Anki",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFC02222)
                                    )
                                }
                            }
                            HorizontalDivider(
                                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                                thickness = 0.8.dp
                            )
                        }
                    }
                }

                // Kapat Butonu
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Kapat", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

/**
 * Metindeki kelimeleri tahlil edip lügatçede varsa hafif altı çizili/vurgulu gösteren
 * ve tıklandığında lügat karşılığını tetikleyen bileşen.
 */
@Composable
fun LugatInteractiveText(
    text: String,
    fontSize: Float,
    onWordClick: (LugatEntry) -> Unit
) {
    val highlightColor = MaterialTheme.colorScheme.primary
    val textColor = MaterialTheme.colorScheme.onBackground

    // Kelime parçalarına ayırıp AnnotatedString oluştur
    val (annotatedString, wordMap) = remember(text, highlightColor, textColor) {
        val map = mutableListOf<Pair<IntRange, LugatEntry>>()
        val annotated = buildAnnotatedString {
            val pattern = java.util.regex.Pattern.compile("([\\p{L}\\p{M}\\-’']+)|([^\\p{L}\\p{M}\\-’']+)")
            val matcher = pattern.matcher(text)

            while (matcher.find()) {
                val token = matcher.group() ?: continue
                val start = length
                val entry = findLugatEntry(token)

                if (entry != null) {
                    pushStringAnnotation(tag = "LUGAT", annotation = entry.word)
                    withStyle(
                        SpanStyle(
                            color = highlightColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(token)
                    }
                    pop()
                    map.add(Pair(start until (start + token.length), entry))
                } else {
                    append(token)
                }
            }
        }
        Pair(annotated, map)
    }

    ClickableText(
        text = annotatedString,
        style = TextStyle(
            fontSize = fontSize.sp,
            lineHeight = (fontLineHeight(fontSize)).sp,
            color = textColor
        ),
        onClick = { offset ->
            val annotation = annotatedString.getStringAnnotations(tag = "LUGAT", start = offset, end = offset)
                .firstOrNull()
            if (annotation != null) {
                val entry = wordMap.firstOrNull { offset in it.first }?.second
                    ?: findLugatEntry(annotation.item)
                if (entry != null) {
                    onWordClick(entry)
                }
            }
        }
    )
}

private fun fontLineHeight(fontSize: Float): Float = fontSize * 1.65f

@Composable
fun AudioPlayerBar(
    audioResName: String?,
    text: String,
    rate: Float,
    onProgress: (Float) -> Unit = {}
) {
    val ctx = LocalContext.current
    var isPlaying by remember { mutableStateOf(false) }
    var currentPos by remember { mutableIntStateOf(0) }
    var duration by remember { mutableIntStateOf(0) }
    var useTts by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun getRawResourceId(name: String): Int {
        return when (name) {
            "deva_1" -> R.raw.deva_1
            "deva_2" -> R.raw.deva_2
            "deva_3" -> R.raw.deva_3
            "deva_4" -> R.raw.deva_4
            "deva_5" -> R.raw.deva_5
            "deva_6" -> R.raw.deva_6
            "deva_7" -> R.raw.deva_7
            "deva_8" -> R.raw.deva_8
            "deva_9" -> R.raw.deva_9
            "deva_10" -> R.raw.deva_10
            "deva_11" -> R.raw.deva_11
            "deva_12" -> R.raw.deva_12
            "deva_13" -> R.raw.deva_13
            "deva_14" -> R.raw.deva_14
            "deva_15" -> R.raw.deva_15
            "deva_16" -> R.raw.deva_16
            "deva_17" -> R.raw.deva_17
            "deva_18" -> R.raw.deva_18
            "deva_19" -> R.raw.deva_19
            "deva_20" -> R.raw.deva_20
            "deva_21" -> R.raw.deva_21
            "deva_22" -> R.raw.deva_22
            "deva_23" -> R.raw.deva_23
            "deva_24" -> R.raw.deva_24
            "deva_25" -> R.raw.deva_25
            else -> 0
        }
    }

    val resId = remember(audioResName) {
        if (audioResName != null) {
            val direct = getRawResourceId(audioResName)
            if (direct != 0) direct else ctx.resources.getIdentifier(audioResName, "raw", ctx.packageName)
        } else 0
    }

    if (resId == 0 || useTts) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(Modifier.padding(12.dp)) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "🤖 Cihaz Metin Okuması (TTS)",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    if (resId != 0) {
                        TextButton(onClick = { useTts = false }) {
                            Text("🎙️ Nazım Gökçek'e Dön")
                        }
                    }
                }
                Spacer(Modifier.height(6.dp))
                SpeakButton(text = text, rate = rate)
            }
        }
        return
    }

    var player by remember(resId) { mutableStateOf<MediaPlayer?>(null) }

    DisposableEffect(resId) {
        val mp = try {
            val p = MediaPlayer()
            p.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            val afd = ctx.resources.openRawResourceFd(resId)
            if (afd != null) {
                p.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                afd.close()
                p.prepare()
                p
            } else {
                MediaPlayer.create(ctx, resId)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            errorMessage = "Ses başlatılamadı: ${e.localizedMessage}"
            null
        }

        player = mp
        if (mp != null) {
            duration = mp.duration
            mp.setOnCompletionListener {
                isPlaying = false
                currentPos = 0
            }
            mp.setOnErrorListener { _, what, extra ->
                isPlaying = false
                errorMessage = "Oynatma hatası (kod: $what, $extra)"
                true
            }
        } else if (errorMessage == null) {
            errorMessage = "Ses dosyası yüklenemedi (resId: $resId)"
        }
        onDispose {
            try {
                mp?.stop()
                mp?.release()
            } catch (_: Exception) {}
            player = null
            isPlaying = false
        }
    }

    LaunchedEffect(isPlaying) {
        while (isPlaying && player != null) {
            val pos = try { player?.currentPosition ?: 0 } catch (_: Exception) { 0 }
            currentPos = pos
            if (duration > 0) {
                onProgress(pos.toFloat() / duration)
            }
            delay(500)
        }
    }

    fun formatMs(ms: Int): String {
        val s = (ms / 1000) % 60
        val m = (ms / (1000 * 60))
        return "%02d:%02d".format(m, s)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(Modifier.padding(14.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("🎙️", fontSize = 22.sp)
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(
                            "Nazım Gökçek Seslendirmesi",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Orijinal Risale-i Nur Kaydı",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                TextButton(onClick = {
                    player?.pause()
                    isPlaying = false
                    useTts = true
                }) {
                    Text("Robot TTS")
                }
            }

            if (errorMessage != null) {
                Spacer(Modifier.height(6.dp))
                Text(
                    text = errorMessage ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(Modifier.height(6.dp))

            Slider(
                value = if (duration > 0) currentPos.toFloat() / duration else 0f,
                onValueChange = { frac ->
                    val newPos = (frac * duration).toInt()
                    currentPos = newPos
                    try { player?.seekTo(newPos) } catch (_: Exception) {}
                },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(formatMs(currentPos), style = MaterialTheme.typography.bodySmall)

                Button(
                    onClick = {
                        val p = player
                        if (p == null) {
                            Toast.makeText(ctx, "Ses yüklenemedi", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        try {
                            if (isPlaying) {
                                p.pause()
                                isPlaying = false
                            } else {
                                p.start()
                                isPlaying = true
                            }
                        } catch (e: Exception) {
                            errorMessage = "Oynatılamadı: ${e.localizedMessage}"
                            isPlaying = false
                        }
                    }
                ) {
                    Text(if (isPlaying) "⏸ Duraklat" else "▶ Dinle")
                }

                Text(formatMs(duration), style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

/** Metni Türkçe cihaz sesiyle okur (TTS). */
@Composable
fun SpeakButton(text: String, rate: Float) {
    val ctx = LocalContext.current
    var speaking by remember { mutableStateOf(false) }
    var ready by remember { mutableStateOf(false) }

    val tts = remember {
        var engine: TextToSpeech? = null
        engine = TextToSpeech(ctx) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val r = engine?.setLanguage(Locale("tr", "TR"))
                ready = r != TextToSpeech.LANG_MISSING_DATA && r != TextToSpeech.LANG_NOT_SUPPORTED
            }
        }
        engine
    }
    DisposableEffect(Unit) {
        onDispose { tts.stop(); tts.shutdown() }
    }

    Button(onClick = {
        if (speaking) {
            tts.stop(); speaking = false
        } else if (!ready) {
            Toast.makeText(
                ctx,
                "Türkçe ses verisi bulunamadı. Telefon ayarlarından 'Metin okuma' için Türkçe sesi indirin.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            val chunks = text.split("\n\n").map { it.trim() }.filter { it.isNotEmpty() }
            val lastId = "u${chunks.size - 1}"
            tts.setSpeechRate(rate)
            tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {}
                override fun onDone(utteranceId: String?) { if (utteranceId == lastId) speaking = false }
                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) { speaking = false }
            })
            chunks.forEachIndexed { i, c ->
                tts.speak(c, TextToSpeech.QUEUE_ADD, null, "u$i")
            }
            speaking = true
        }
    }) {
        Text(if (speaking) "⏹ Durdur" else "🔊 TTS ile Dinle")
    }
}

@Composable
fun DuaScreen(prefs: Prefs) {
    var count by remember { mutableIntStateOf(prefs.zikir) }
    LazyColumn(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Card(Modifier.fillMaxWidth().padding(top = 6.dp)) {
                Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Zikirmatik", style = MaterialTheme.typography.titleMedium)
                    Text("$count", fontSize = 56.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(onClick = { count++; prefs.zikir = count }) { Text("Say") }
                        OutlinedButton(onClick = { count = 0; prefs.zikir = 0 }) { Text("Sıfırla") }
                    }
                }
            }
        }
        items(duas) { d ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp)) {
                    Text(d.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(6.dp))
                    Text(d.text, fontSize = 18.sp, lineHeight = 28.sp)
                }
            }
        }
    }
}

/**
 * Hastalar Risalesi Lügatçe Ekranı.
 * Arama, kelime kökeni ve anlamlarını listeleyen müstakil bölüm.
 */
@Composable
fun LugatScreen() {
    var searchQuery by remember { mutableStateOf("") }
    val filteredEntries = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            hastalarLugat.sortedBy { it.word.lowercase() }
        } else {
            val q = searchQuery.trim().lowercase()
            hastalarLugat.filter {
                it.word.lowercase().contains(q) ||
                it.root.lowercase().contains(q) ||
                it.meaning.lowercase().contains(q)
            }.sortedBy { it.word.lowercase() }
        }
    }

    Column(Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Lügatçede kelime veya mana ara...") },
            singleLine = true,
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Text("✕")
                    }
                }
            }
        )

        Spacer(Modifier.height(8.dp))
        Text(
            text = "Toplam ${filteredEntries.size} kelime listeleniyor",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(Modifier.height(8.dp))

        if (filteredEntries.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Eşleşen kelime bulunamadı.")
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(filteredEntries) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = item.word,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                item.origin?.let { orig ->
                                    Surface(
                                        shape = MaterialTheme.shapes.small,
                                        color = MaterialTheme.colorScheme.primaryContainer
                                    ) {
                                        Text(
                                            text = orig,
                                            style = MaterialTheme.typography.labelSmall,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                }
                            }
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = item.meaning,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 22.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
