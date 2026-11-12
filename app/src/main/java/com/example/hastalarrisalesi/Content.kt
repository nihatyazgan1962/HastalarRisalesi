package com.example.hastalarrisalesi

import java.util.Calendar

data class Category(
    val id: String,
    val name: String,
    val icon: String,
    val description: String
)

val categories = listOf(
    Category("sabir", "Sabırsızlar & Tahammül", "⏳", "Acıya sabredemeyen, telaş gösteren ve sabrını geçmişe veya geleceğe dağıtarak tüketenler."),
    Category("vehim", "Vehim & Merak", "💭", "Hastalığın kendisinden ziyade evham, vesvese ve lüzumsuz merakla acısını büyütenler."),
    Category("ibadet", "İbadetten Geri Kalanlar", "🤲", "Namaz, zikir ve hayır yapamadığı için kederlenen; menfi ibadet sırrını arayanlar."),
    Category("olum", "Ölüm & Ecel Endişesi", "🕊️", "Ecelden korkan, vefatı ayrılık zanneden veya gençliğinde hastalananlar."),
    Category("sukur", "Şükür & Nefis Terbiyesi", "🌿", "Sıhhat lezzetini unutan, nefsin gururunu kırmak ve hakiki şükre ermek isteyenler."),
    Category("yalnizlik", "Yalnızlar & Refakatçiler", "🤝", "Gurbetteki kimsesizler, müzmin felç/uzuv rahatsızlığı yaşayanlar, çocuklar ve refakatçiler.")
)

data class Section(
    val id: String,
    val title: String,
    val profile: String,
    val profileDesc: String,
    val categoryId: String,
    val body: String,
    val audioRes: String? = null
) {
    val categoryName: String get() = categories.find { it.id == categoryId }?.name ?: ""
    val moods: List<String> get() = listOf(profile, categoryName)
}

data class Dua(
    val title: String,
    val text: String
)

val moods = categories.map { it.name }

// Kaynak: https://www.erisale.com/tr/lemalar/yirmibesinci-lema-hastalar-risalesi
// Seslendirme: Merhum Nazım Gökçek (Nazım Gökçek Akademi)
val sections = listOf(
    Section(
        id = "1",
        title = "Mukaddeme (Giriş) - Yirmibeşinci Lem'a",
        profile = "Hastalara Manevi Reçete",
        profileDesc = "Hastalara bir merhem, bir teselli ve geçmiş olsun makamında yazılmıştır.",
        categoryId = "sabir",
        body = """Yirmibeşinci Lem'aTocTitle=Yirmibeşinci Lem'a (Hastalar Risalesi)Section=

Yirmi beş Devâdır

Hastalara bir merhem, bir tesellî, manevî bir reçete, bir iyâdetü'l‑marîz ve geçmiş olsun makamında yazılmıştır.

İhtar ve İ'tizar: Bu manevî reçete, bütün yazdıklarımızın fevkınde bir sür'atle Hâşiye te'lif edildiği gibi, hem umuma muhâlif olarak, tashihâta ve dikkate vakit bulmayarak, te'lifi gibi gayet sür'atle, ancak bir defa nazardan geçirildi. Demek, müsvedde‑i evvel hükmünde müşevveş kalmıştır. Kalbe fıtrî bir sûrette gelen hâtırâtı san'atla ve dikkatle bozmamak için, yeniden tedkîkàta lüzum görmedik. Okuyan zâtlar, hususan hastalar bazı nâhoş ibarelerden veyâhut ağır kelimelerden ve ifâdelerden sıkılıp gücenmesinler; bana da duâ etsinler.

﷽

اَلَّذ۪ينَ اِذَٓا اَصَابَتْهُمْ مُص۪يبَةٌ قَالُٓوا اِنَّا لِلّٰهِ وَاِنَّٓا اِلَيْهِ رَاجِعُونَ

وَالَّذ۪ي هُوَ يُطْعِمُن۪ي وَيَسْق۪ينِ ❋ وَاِذَا مَرِضْتُ فَهُوَ يَشْف۪ينِ

Şu Lem'a’da, nev'‑i beşerin on kısmından bir kısmını teşkil eden musîbet-zede ve hastalara hakîki bir tesellî ve nâfi' bir merhem olabilecek “Yirmi beş Devâ”yı icmâlen beyân ediyoruz.""",
        audioRes = null
    ),
    Section(
        id = "2",
        title = "Birinci Devâ",
        profile = "Bîçare / Çaresiz Hasta",
        profileDesc = "Hastalığı mutlak bir felaket gibi görüp ömrün ziyan olduğunu düşünen; hastalığın fani ömrü baki meyvelere çevirdiğini bilmeyenler.",
        categoryId = "sabir",
        body = """Ey bîçâre hasta! Merak etme, sabret. Senin hastalığın sana dert değil, belki bir nev'i dermandır. Çünkü; ömür bir sermâyedir, gidiyor. Meyvesi bulunmazsa zâyi' olur. Hem rahat ve gafletle olsa, pek çabuk gidiyor. Hastalık, senin o sermâyeni büyük kârlarla meyvedâr ediyor. Hem ömrün çabuk geçmesine meydân vermiyor, tutuyor, uzun ediyor; tâ meyveleri verdikten sonra bırakıp gitsin.

İşte, ömrün hastalıkla uzun olmasına işâreten bu darb‑ı mesel dillerde destandır ki; “Musîbet zamanı çok uzundur, safâ zamanı pek kısa oluyor.”""",
        audioRes = "deva_1"
    ),
    Section(
        id = "3",
        title = "İkinci Devâ",
        profile = "Sabırsız Hasta",
        profileDesc = "Acıya sabredemeyen, telaş gösteren ve sabrını geçmişe veya geleceğe dağıtarak tüketenler.",
        categoryId = "sabir",
        body = """Ey sabırsız hasta! Sabret, belki şükret. Senin bu hastalığın, ömür dakikalarını birer saat ibâdet hükmüne getirebilir.

Çünkü, ibâdet iki kısımdır. Biri müsbet ibâdettir ki; namaz, niyâz gibi ma'lûm ibâdetlerdir. Diğeri menfî ibâdetlerdir ki; hastalıklar, musîbetler vâsıtasıyla musîbet‑zede, aczini, zaafını hisseder. Hàlık-ı Rahîm’ine ilticâ eder, yalvarır. Hàlis, riyâsız, manevî bir ibâdete mazhar olur.

Evet, hastalıkla geçen bir ömür, Allah’tan şekvâ etmemek şartıyla, mü'min için ibâdet sayıldığına rivâyât‑ı sahîha vardır. Hattâ bazı sâbir ve şâkir hastaların bir dakikalık hastalığı, bir saat ibâdet hükmüne geçtiği ve bazı kâmillerin bir dakikası bir gün ibâdet hükmüne geçtiği, rivâyet-i sahîha ve keşfiyât-ı sâdıka ile sâbittir.

Senin bir dakika ömrünü, bin dakika hükmüne getirip, sana uzun ömrü kazandıran hastalıktan teşekkî değil, teşekkür et.""",
        audioRes = "deva_2"
    ),
    Section(
        id = "4",
        title = "Üçüncü Devâ",
        profile = "Tahammülsüz Hasta",
        profileDesc = "Dünyaya yalnızca keyif sürmek ve lezzet almak için gelindiğini zanneden, sıkıntıya katlanamayanlar.",
        categoryId = "sabir",
        body = """Ey tahammülsüz hasta! İnsan, bu dünyaya keyif sürmek ve lezzet almak için gelmediğine; mütemâdiyen gelenlerin gitmesi ve gençlerin ihtiyarlaşması ve mütemâdiyen zevâl ve firâkta yuvarlanması şâhiddir.

Hem insan, zîhayatın en mükemmeli, en yükseği ve cihâzâtça en zengini, belki zîhayatların sultanı hükmünde iken, geçmiş lezzetleri ve gelecek belâları düşünmek vâsıtasıyla, hayvana nisbeten en ednâ bir derecede, ancak kederli, meşakkatli bir hayat geçiriyor.

Demek insan, bu dünyaya yalnız güzel yaşamak için ve rahatla ve safâ ile ömür geçirmek için gelmemiştir. Belki azîm bir sermâye elinde bulunan insan, burada ticâret ile, ebedî, dâimî bir hayatın saâdetine çalışmak için gelmiştir. Onun eline verilen sermâye de, ömürdür. Eğer hastalık olmazsa, sıhhat ve âfiyet gaflet verir; dünyayı hoş gösterir; âhireti unutturur… Kabri ve ölümü hâtırına getirmek istemiyor… Sermâye‑i ömrünü bâd-i hevâ boş yere sarfettiriyor. Hastalık ise, birden gözünü açtırır. Vücûduna ve cesedine der ki: “Lâyemût değilsin, başıboş değilsin, bir vazifen var. Gururu bırak, seni yaratanı düşün, kabre gideceğini bil, öyle hazırlan.”

İşte hastalık bu nokta‑i nazardan hiç aldatmaz bir nâsih ve îkaz edici bir mürşiddir. Ondan şekvâ değil, belki bu cihette ona teşekkür etmek; eğer fazla ağır gelse, sabır istemek gerektir…""",
        audioRes = "deva_3"
    ),
    Section(
        id = "5",
        title = "Dördüncü Devâ",
        profile = "Şekvacı Hasta",
        profileDesc = "Halinden sürekli şikayet eden, mülk sahibinin tasarrufuna itiraz eder gibi sızlananlar.",
        categoryId = "sabir",
        body = """Ey şekvâcı hasta! Senin hakkın şekvâ değil şükürdür, sabırdır. Çünkü senin vücûdun ve a'zâ ve cihâzâtın, senin mülkün değildir. Sen onları yapmamışsın, başka tezgâhlardan satın almamışsın. Demek başkasının mülküdür. Onların Mâliki, mülkünde istediği gibi tasarruf eder.

Yirmialtıncı Söz’de denildiği gibi, meselâ; gayet zengin, gayet mâhir bir san'atkâr, güzel san'atını, kıymetdâr servetini göstermek için, miskin bir adama modellik vazifesini gördürmek maksadıyla, bir ücrete mukâbil, bir saatçik zamanda, murassa' ve gayet san'atlı diktiği bir gömleği, bir hulleyi o fakire giydirir. Onun üstünde işler ve vaziyetler verir. Hàrika envâ'‑ı san'atını göstermek için keser, değiştirir, uzaltır, kısaltır. Acaba şu ücretli miskin adam, o zâta dese: “Bana zahmet veriyorsun, eğilip kalkmakla verdiğin vaziyetten bana sıkıntı veriyorsun, beni güzelleştiren bu gömleği kesip kısaltmakla güzelliğimi bozuyorsun.” demeye hak kazanabilir mi? “Merhametsizlik, insafsızlık ettin.” diyebilir mi?

İşte, aynen bu misâl gibi; Sâni'‑i Zülcelâl, sana ey hasta! Göz, kulak, akıl, kalb gibi nurânî duygularla murassa' olarak giydirdiği cisim gömleğini, Esmâ-i Hüsnâ’sının nakışlarını göstermek için; çok hâlât içinde seni çevirir ve çok vaziyetlerde seni değiştirir. Sen açlıkla onun Rezzâk ismini tanıdığın gibi, Şâfi ismini de hastalığınla bil. Elemler, musîbetler bir kısım esmâsının ahkâmını gösterdikleri için, onlarda, hikmetten lem'alar ve rahmetten şuâlar ve o şuâât içinde çok güzellikler bulunuyor.

Eğer perde açılsa, tevahhuş ve nefret ettiğin hastalık perdesi arkasında sevimli, güzel mânâları bulursun.""",
        audioRes = "deva_4"
    ),
    Section(
        id = "6",
        title = "Beşinci Devâ",
        profile = "Hastalığın Şiddetinden Telaş Eden / Günahından Korkan Hasta",
        profileDesc = "Hastalığı sadece eziyet sanan; onun sabun gibi manevi kirleri ve günahları temizlediğini unutanlar.",
        categoryId = "vehim",
        body = """Ey maraza mübtelâ hasta! Bu zamanda tecrübemle kanâatim gelmiştir ki, hastalık bazılara bir ihsân‑ı İlâhî’dir, bir hediye-i Rahmânîdir. Bu sekiz-dokuz senedir, liyâkatsiz olduğum hâlde, bazı genç zâtlar hastalık münâsebetiyle duâ için benimle görüştüler. Dikkat ettim ki; hangi hastalıklı genci gördüm, sâir gençlere nisbeten âhiretini düşünmeye başlıyor… Gençlik sarhoşluğu yok. Gaflet içindeki hayvanî hevesâttan bir derece kendini kurtarıyor. Ben de bakıyordum, onların tahammül dâhilindeki hastalıklarını bir ihsân-ı İlâhî olduğunu ihtar ederdim. Derdim ki: “Kardeşim, senin bu hastalığının aleyhinde değilim. Hastalık için sana karşı bir şefkat hissedip acımıyorum ki, duâ edeyim. Hastalık seni tam uyandırıncaya kadar sabra çalış. Ve hastalık vazifesini bitirdikten sonra, Hàlık-ı Rahîm inşâallâh sana şifâ verir.”

Hem derdim: “Senin bir kısım emsâlin, sıhhat belâsıyla gaflete düşüp, namazı terkedip, kabri düşünmeyip, Allah’ı unutup, bir saatlik hayat‑ı dünyeviyenin zâhirî keyfi ile, hadsiz bir hayat-ı ebediyesini sarsar, zedeler, belki de harâb eder. Sen hastalık gözüyle, herhalde gideceğin bir menzilin olan kabrini ve daha arkasında uhrevî menzilleri görürsün ve onlara göre davranıyorsun. Demek senin için hastalık, bir sıhhattir; bir kısım emsâlindeki sıhhat, bir hastalıktır.”""",
        audioRes = "deva_5"
    ),
    Section(
        id = "7",
        title = "Altıncı Devâ",
        profile = "Dünya Lezzeti Kaçan / Gençlikte Hastalanan",
        profileDesc = "Gençliğini, sıhhatini zayi ettiğini ve dünyevi zevklerden mahrum kaldığını düşünüp hayıflananlar.",
        categoryId = "olum",
        body = """Ey elemden teşekkî eden hasta! Senden soruyorum: Geçmiş ömrünü düşün ve o ömürde geçmiş lezzetli safâ günleri ve belâ ve elemli vakitlerini tahattur et. Herhalde ya “Oh!” ya “Âh!” diyeceksin. Yani; ya “Elhamdülillâh şükür”, veyâhut “Vâ‑hasretâ, vâ-esefâ!” kalbin veya lisânın diyecek.

Dikkat et, sana “Oh, elhamdülillâh, şükür” dediren; senin başından geçmiş elemler, musîbetlerin düşünmesi, bir manevî lezzeti deşiyor ki, senin kalbin şükreder. Çünkü elemin zevâli, lezzettir. O elemler, o musîbetler, zevâliyle rûhta bir lezzet irsiyet bırakmış ki; düşünmekle deşilse, rûhtan bir lezzet akıyor, şükürler takattur ediyor.

Sana “Vâ‑esefâ, vâ-hasretâ!” dedirten; eski zamanda geçirdiğin lezzetli ve safâlı o hâllerdir ki, zevâlleriyle senin rûhunda dâimî bir elem irsiyet bırakıp, ne vakit düşünsen o elem yine deşiliyor, esef ve hasret akıtıyor.

Mâdem bir günlük gayr‑ı meşrû lezzet, bazen bir sene manevî elem çektiriyor. Ve muvakkat bir günlük hastalıkla gelen elem, çok günler manevî lezzet-i sevâbla beraber, zevâlindeki halâs ve kurtulmaktan gelen manevî lezzet vardır.

Senin başındaki şimdilik bu muvakkat hastalığın neticesi ve içyüzündeki sevâbı düşün, “Bu da geçer, yâ Hû!” de, şekvâ yerinde şükret.

--- Altıncı Devâ Hâşiyesi ---

Ey dünya zevkini düşünüp hastalıktan ızdırâb çeken kardeşim! Bu dünya eğer dâimî olsaydı ve yolumuzda ölüm olmasaydı ve firâk ve zevâlin rüzgârları esmeseydi ve musîbetli, fırtınalı istikbâlde manevî kış mevsimleri olmasaydı, ben de seninle beraber senin hâline acıyacaktım. Fakat mâdem dünya bir gün bize “Haydi, dışarı!” diyecek, feryâdımızdan kulağını kapayacak; o bizi dışarı kovmadan, biz bu hastalıklar îkazâtıyla şimdiden onun aşkından vazgeçmeliyiz. O bizi terketmeden, kalben onu terke çalışmalıyız.

Evet, hastalık bu mânâyı bize ihtar edip der ki: “Senin vücûdun taştan, demirden değildir. Belki dâima ayrılmağa müsâid muhtelif maddelerden terkîb edilmiştir. Gururu bırak, aczini anla, mâlikini tanı, vazifeni bil, dünyaya ne için geldiğini öğren.” kalbin kulağına gizli ihtar ediyor.

Hem mâdem dünyanın zevki, lezzeti devam etmiyor; hususan meşrû olmazsa hem devamsız, hem elemli, hem günahlı oluyor. O zevki kaybettiğinden hastalık bahânesiyle ağlama; bil'akis hastalıktaki manevî ibâdet ve uhrevî sevâb cihetini düşün, zevk almaya çalış.""",
        audioRes = "deva_6"
    ),
    Section(
        id = "8",
        title = "Yedinci Devâ",
        profile = "Sıhhatin Lezzetini Kaybettiğini Düşünen Hasta",
        profileDesc = "Hastalığın aslında sıhhatteki gizli lezzeti ve nimetin kadrini fark ettiren bir ölçü olduğunu göremeyenler.",
        categoryId = "sukur",
        body = """Ey sıhhatinin lezzetini kaybeden hasta! Senin hastalığın, sıhhatteki ni'met‑i İlâhiye’nin lezzetini kaçırmıyor; bil'akis tattırıyor, ziyâdeleştiriyor. Çünkü bir şey devam etse te'sirini kaybeder. Hattâ ehl-i hakikat müttefikan diyorlar ki: اِنَّمَا الْاَشْيَاءُ تُعْرَفُ بِاَضْدَادِهَا Yani; “Herşey zıddıyla bilinir.” Meselâ; karanlık olmazsa ışık bilinmez, lezzetsiz kalır. Soğuk olmazsa harâret anlaşılmaz, zevksiz kalır. Açlık olmazsa yemek lezzet vermez. Mide harâreti olmazsa, su içmesi zevk vermez. İllet olmazsa âfiyet zevksizdir. Maraz olmazsa sıhhat lezzetsizdir.

Mâdem Fâtır‑ı Hakîm insana her çeşit ihsânını ihsâs etmek ve herbir nev'i ni'metini tattırmak ve insanı dâima şükre sevk etmek istediğini, şu kâinâtta çeşit çeşit, hadsiz envâ'-ı ni'meti tadacak, tanıyacak derecede, gayet çok cihâzât ile insanı techiz etmesi gösteriyor ki; elbette sıhhat ve âfiyeti verdiği gibi, hastalıkları, illetleri, dertleri de verecektir.

Senden soruyorum: “Bu hastalık senin başında veya elinde veya midende olmasaydı, sen başın, elin, midenin sıhhatindeki lezzetli, zevkli ni'met‑i İlâhiye’yi hissedip şükreder miydin?” Elbette şükür değil, belki düşünmeyecektin; şuûrsuz, o sıhhati gaflete, belki sefâhete sarf ederdin.""",
        audioRes = "deva_7"
    ),
    Section(
        id = "9",
        title = "Sekizinci Devâ",
        profile = "Ahiret Ameli Yapamadığını Düşünen Hasta",
        profileDesc = "Hastalık sebebiyle ibadet edemediğini ve sevaptan mahrum kaldığını sananlar (hastalığın menfi ibadet vasfı).",
        categoryId = "ibadet",
        body = """Ey âhiretini düşünen hasta! Hastalık, sabun gibi, günahların kirlerini yıkar, temizler. Hastalıklar, keffâretü'z‑zünûb olduğu hadîs-i sahîhle sâbittir. Hem hadîste vardır ki: “Ermiş ağacı silkmekle, nasıl meyveleri düşer; îmânlı bir hastanın titremesi de öyle günahları silker.”

Günahlar, hayat‑ı ebediyede dâimî hastalıklardır; bu hayat-ı dünyeviyede dahi kalb, vicdân, rûh için manevî hastalıklardır. Sen eğer sabredip şekvâ etmezsen, şu muvakkat bir hastalık ile dâimî pek çok hastalıklardan kurtuluyorsun.

Eğer günahları düşünmüyorsan, yâhut âhireti bilmiyorsan veya Allah’ı tanımıyorsan, sende öyle dehşetli bir hastalık var ki, milyon defa sendeki bu küçük hastalıktan daha büyüktür. Ondan feryâd et.

Çünkü; bütün dünyanın mevcûdâtıyla kalbin, rûhun ve nefsin alâkadardır. Mütemâdiyen firâk ve zevâl ile o alâkalar kesilip, sende hadsiz yaralar açılır. Bâhusus Âhireti bilmediğin için, ölümü i'dâm‑ı ebedî tahayyül ettiğinden, âdeta, güyâ yara bere içinde, dünya kadar hastalıklı bir vücûdun var.

İşte en evvel, hadsiz yaralı ve hastalıklı bu büyük manevî vücûdun hadsiz hastalıklarına kat'î ilâç ve kat'î şifâ verici bir tiryâk olan îmân ilâcını aramak ve i'tikàdını düzeltmek gerektir ki, o ilâcı bulmakta en kısa yol; bu maddî hastalığın yırttığı gaflet perdesinin altında sana gösterdiği aczin ve za'fın penceresiyle, bir Kadîr‑i Zülcelâl’in kudretini ve rahmetini tanımaktır.

Evet, Allah’ı tanımayanın, dünya dolusu belâ başında vardır. Allah’ı tanıyanın dünyası nurla ve manevî sürûrla doludur; derecesine göre, îmân kuvvetiyle hisseder. Bu îmândan gelen manevî sürûr ve şifâ ve lezzet altında, cüz'î, maddî hastalıkların elemi erir, ezilir.""",
        audioRes = "deva_8"
    ),
    Section(
        id = "10",
        title = "Dokuzuncu Devâ",
        profile = "Ecelden ve Ölümden Korkan Hasta",
        profileDesc = "Hastalığın eceli getirdiğini veya yaklaştırdığını vehmeden, ölüm korkusuna kapılanlar.",
        categoryId = "olum",
        body = """Ey Hàlık’ını tanıyan hasta! Hastalıklardaki elem ve tevahhuş ve korkmak ise, hastalık bazen ölüme vesile olduğu cihetindendir. Ölüm, nazar‑ı gaflet ve zâhirî cihetinde dehşetli olduğundan, ona vesile olabilen hastalıklar korkutuyor, telâş veriyor.

Evvelâ bil ve kat'î îmân et ki: Ecel mukadderdir, tağayyür etmez. Çok ağır hastaların başında ağlayanlar ve sıhhatleri yerinde olanlar ölmüşler, o ağır hastalar şifâ bulup yaşamışlar.

Sâniyen: Ölüm, sûreten göründüğü gibi dehşetli değil. Çok risalelerde gayet kat'î, şeksiz, şüphesiz bir sûrette, Kur'ân‑ı Hakîm’in verdiği nur ile isbât etmişiz ki: Ehl-i îmân için ölüm, vazife-i hayat külfetinden bir terhistir.

Hem dünya meydânındaki imtihanda, ta'lim ve ta'limât olan ubûdiyetten bir paydostur.

Hem öteki âleme gitmiş yüzde doksan dokuz ahbab ve akrabasına kavuşmak için bir vesiledir.

Hem hakîki vatanına ve ebedî makam‑ı saâdetine girmeye bir vâsıtadır.

Hem zindân‑ı dünyadan, bostan-ı cinâna bir dâvettir.

Hem Hàlık‑ı Rahîm’inin fazlından, kendi hizmetine mukâbil ahz-ı ücret etmeye bir nöbettir.

Mâdem ölümün mâhiyeti hakikat noktasında budur; ona dehşetli bakmak değil, bil'akis rahmet ve saâdetin bir mukaddimesi nazarıyla bakmak gerektir. Hem ehlullâhın bir kısmının ölümden korkmaları, ölümün dehşetinden değildir. Belki daha fazla hayır kazanacağım diye, vazife‑i hayatın idâmesinden kazanacakları hayrat içindir.

Evet, ehl‑i îmân için ölüm rahmet kapısıdır; ehl-i dalâlet için zulümât-ı ebediye kuyusudur.""",
        audioRes = "deva_9"
    ),
    Section(
        id = "11",
        title = "Onuncu Devâ",
        profile = "Lüzumsuz Merak Eden / Vehimli Hasta",
        profileDesc = "Hastalığın kendisinden ziyade evham ve kuruntularıyla acısını iki katına çıkaranlar.",
        categoryId = "vehim",
        body = """Ey lüzumsuz merak eden hasta! Sen hastalığın ağırlığından merak ediyorsun. O merakın, senin hastalığını ağırlaştırır. Hastalığın hafifleşmesini istersen, merak etmemeye çalış. Yani; hastalığın fâidelerini, sevâbını ve çabuk geçeceğini düşün; merakı kaldır, hastalığın kökünü kes.

Evet merak, hastalığı ikileştirir. Maddî hastalığın altında, merak ile manevî bir hastalığı kalbine verir; maddî hastalık ona dayanır, devam eder. Eğer teslîmiyetle, rızâ ile, hastalığın hikmetini düşünmekle o merak gitse, o maddî hastalığın mühim bir kökü kesilir, hafifleşir, kısmen gider. Hususan evhâmla bir dirhem maddî hastalık, bazen merak vâsıtasıyla on dirhem kadar büyür. Merak kesilmesiyle, o hastalığın onda dokuzu gider.

Merak, hastalığı ziyâde ettiği gibi, Hikmet‑i İlâhiye’yi ittiham ve Rahmet-i İlâhiye’yi tenkid ve Hàlık-ı Rahîm’inden şekvâ hükmünde olduğu için, aks-i maksadıyla tokat yer, hastalığını ziyâdeleştirir. Evet nasıl ki; şükür, ni'meti ziyâdeleştirir; öyle de şekvâ; hastalığı, musîbeti tezyîd eder.

Hem merakın kendisi de bir hastalıktır. Onun ilâcı, hastalığın hikmetini bilmektir. Mâdem hikmetini, faydasını bildin; o merhemi meraka sür, kurtul. “Âh!” yerine “Oh!” de; “Vâ‑esefâ!” yerine “Elhamdülillâhi alâ külli hâl” söyle.""",
        audioRes = "deva_10"
    ),
    Section(
        id = "12",
        title = "Onbirinci Devâ",
        profile = "Gelecek ve Geçmiş Acıları Yığan Sabırsız Hasta",
        profileDesc = "Henüz gelmemiş günlerin veya geçip gitmiş saatlerin yükünü bugüne taşıyıp sabır kuvvetini tüketenler.",
        categoryId = "sabir",
        body = """Ey sabırsız hasta kardeş! Hastalık, hazır bir elemi sana vermekle beraber; evvelki hastalığından bugüne kadar, o hastalığın zevâlindeki bir lezzet‑i maneviye ve sevâbındaki bir lezzet-i rûhiye veriyor. Bugünden, belki bu saatten sonraki zamanda hastalık yok; elbette yoktan elem yok. Elem olmazsa teessür olamaz. Sen yanlış bir sûrette tevehhüm ettiğin için sabırsızlık geliyor.

Çünkü; bugünden evvel bütün hastalık zamanının maddîsi gitmekle elemi de beraber gitmiş, kendindeki sevâbı ve zevâlindeki lezzet kalmış. Sana kâr ve sürûr vermek lâzım gelirken, onları düşünüp müteellim olmak ve sabırsızlık etmek, dîvâneliktir. Gelecek günler daha gelmemişler. Onları şimdiden düşünüp, yok bir günde, yok olan bir hastalıktan, yok olan bir elemden tevehhüm ile düşünüp müteellim olmak, sabırsızlık göstermekle, üç mertebe yok yoğa vücûd rengi vermek, dîvânelik değil de nedir?

Mâdem bu saatten evvelki hastalık zamanları ise sürûr veriyor. Ve mâdem, yine bu saatten sonraki zaman ma'dûm, hastalık ma'dûm, elem ma'dûmdur. Sen, Cenâb‑ı Hakk’ın sana verdiği bütün sabır kuvvetini böyle sağa sola dağıtma; bu saatteki eleme karşı tahşid et, “Yâ Sabûr!” de, dayan.""",
        audioRes = "deva_11"
    ),
    Section(
        id = "13",
        title = "Onikinci Devâ",
        profile = "İbadet ve Evradından Mahrum Kalan Hasta",
        profileDesc = "Namaz, zikir ve virdlerini tam yapamadığı için üzülen samimi dindarlar.",
        categoryId = "ibadet",
        body = """Ey hastalık sebebiyle ibâdet ve evrâdından mahrum kalan ve o mahrumiyetten teessüf eden hasta! Bil ki, hadîsçe sâbittir ki; “Müttakì bir mü'min, hastalık sebebiyle yapamadığı dâimî virdinin sevâbını, hastalık zamanında yine kazanır.” Farzı mümkün olduğu kadar yerine getiren bir hasta, sabır ve tevekkül ile ve farzlarını yerine getirmekle, o ağır hastalık zamanında sâir sünnetlerin yerini, hem hàlis bir sûrette, hastalık tutar.

Hem hastalık, insandaki aczini, zaafını ihsâs eder. O aczin lisânıyla ve zaafın diliyle, hâlen ve kàlen bir duâ ettirir. Cenâb‑ı Hak insana hadsiz bir acz ve nihâyetsiz bir za'f vermiş, tâ ki; dâimî bir sûrette dergâh-ı İlâhiye’ye ilticâ edip niyâz etsin, duâ etsin.قُلْ مَا يَعْبَؤُ۬ا بِكُمْ رَبّ۪ي لَوْلَا دُعَٓاؤُكُمْ Yani; “Eğer duânız olmazsa ne ehemmiyetiniz var?” âyetin sırrıyla; insanın hikmet‑i hilkati ve sebeb-i kıymeti olan samîmî duâ ve niyâzın bir sebebi hastalık olduğundan, bu nokta-i nazardan şekvâ değil, Allah’a şükretmek ve hastalığın açtığı duâ musluğunu, âfiyeti kesbetmekle kapamamak gerektir.""",
        audioRes = "deva_12"
    ),
    Section(
        id = "14",
        title = "Onüçüncü Devâ",
        profile = "Hayır ve Hasenat Yapamamaktan Üzülen Hasta",
        profileDesc = "İnsanlara iyilik yapamadığını, faydalı olamadığını düşünerek kederlenenler.",
        categoryId = "ibadet",
        body = """Ey hastalıktan şekvâ eden bîçâre adam! Hastalık bazılara ehemmiyetli bir definedir, gayet kıymetdâr bir hediye‑i İlâhiye’dir. Her hasta, kendi hastalığını o nev'iden tasavvur edebilir.

Mâdem ecel vakti muayyen değil; Cenâb‑ı Hak, insanı ye's-i mutlak ve gaflet-i mutlaktan kurtarmak için, havf ve recâ ortasında ve hem dünya ve hem Âhireti muhâfaza etmek noktasında tutmak için, hikmetiyle eceli gizlemiş. Mâdem her vakit ecel gelebilir; eğer insanı gaflet içinde yakalasa, ebedî hayatına çok zarar verebilir. Hastalık gafleti dağıtır, Âhireti düşündürür, ölümü tahattur ettirir, öylece hazırlanır. Bazı öyle bir kazancı olur ki, yirmi senede kazanamadığı bir mertebeyi yirmi günde kazanıyor.

Ezcümle; arkadaşlarımızdan – Allah rahmet etsin – iki genç vardı: Biri İlamalı Sabri, diğeri İslâmköylü Vezirzâde Mustafa. Bu iki zât, talebelerim içinde kalemsiz oldukları hâlde, samîmiyette ve îmân hizmetinde en ileri safta olduklarını hayretle görüyordum. Hikmetini bilmedim. Vefâtlarından sonra anladım ki, her ikisinde de ehemmiyetli bir hastalık vardı. O hastalık irşadıyla, sâir gâfil ve ferâizi terk eden gençlere bedel, en mühim bir takvâ ve en kıymetdâr bir hizmette ve âhirete nâfi' bir vaziyette bulundular. İnşâallâh, iki senelik hastalık zahmeti, milyonlar sene hayat‑ı ebediyenin saâdetine medâr oldu. Ben, onların sıhhati için bazı ettiğim duâyı şimdi anlıyorum, dünya itibariyle bedduâ olmuş. İnşâallâh o duâm, sıhhat-i uhreviye için kabûl olunmuştur.

İşte bu iki zât, benim i'tikàdımca, on senelik bir takvâ ile elde edilecek bir kazanç kadar bir kâr buldular. Eğer ikisi, bir kısım gençler gibi sıhhat ve gençliğine güvenip, gaflet ve sefâhete atılsaydılar, ölüm de onları tarassud edip tam günahlarının pislikleri içinde yakalasaydı, o nurlar definesi yerine, kabirlerini akrepler ve yılanlar yuvası yapacaklardı.

Mâdem hastalıkların böyle menfaati var; ondan şekvâ değil, tevekkül, sabır ile, belki şükredip, Rahmet‑i İlâhiye’ye i'timâd etmektir.""",
        audioRes = "deva_13"
    ),
    Section(
        id = "15",
        title = "Ondördüncü Devâ",
        profile = "Göz, Kulak veya Bir Uzvu Sakatlanan Hasta",
        profileDesc = "Görme, işitme veya bir uzvunu kaybedip günahlara kapanma cihetiyle manevi kazancını fark edemeyenler.",
        categoryId = "yalnizlik",
        body = """Ey gözüne perde gelen hasta! Eğer ehl‑i îmânın gözüne gelen perdenin altında nasıl bir nur ve manevî bir göz olduğunu bilsen, “Yüz bin şükür Rabb-i Rahîm’ime!” dersin. Bu merhemi izâh için bir hâdise söyleyeceğim. Şöyle ki:

Bana sekiz sene kemâl‑i sadâkatle, hiç gücendirmeden hizmet eden Barlalı Süleyman’ın halasının bir vakit gözü kapandı. O sâliha kadın, bana karşı haddimden yüz derece fazla hüsn-ü zan ederek, “Gözümün açılması için duâ et” diyerek câmi kapısında beni yakaladı. Ben de, o mübârek ve meczûbe kadının salâhatini duâma şefâatçi yapıp, “Yâ Rabbî, onun salâhati hürmetine onun gözünü aç” diye yalvardım. İkinci gün Burdurlu bir göz hekimi geldi, gözünü açtı. Kırk gün sonra yine gözü kapandı. Ben çok müteessir oldum, çok duâ ettim. İnşâallâh o duâ âhireti için kabûl olmuştur. Yoksa benim o duâm, onun hakkında gayet yanlış bir bedduâ olurdu. Çünkü eceli kırk gün kalmıştı; kırk gün sonra – Allah rahmet etsin – vefât eyledi.

İşte o merhume, kırk gün Barla’nın hazînâne bağlarına rikkatli ihtiyarlık gözüyle bakmasına bedel, kabrinde, Cennet bağlarını kırk bin günlerde seyredeceğini kazandı. Çünkü îmânı kuvvetli, salâhati şiddetli idi.

Evet, bir mü'min, gözüne perde çekilse ve gözü kapalı kabre girse, derecesine göre, ehl‑i kubûrdan çok ziyâde o âlem-i nuru temâşâ edebilir. Bu dünyada nasıl çok şeyleri biz görüyoruz, kör olan mü'minler görmüyorlar; kabirde o körler, îmân ile gitmişse, o derece ehl-i kubûrdan ziyâde görür. En uzak gösteren dûrbînlerle bakar nev'inde, kabrinde, derecesine göre, Cennet bağlarını sinema gibi görüp temâşâ ederler.

İşte böyle gayet nurlu ve toprak altında iken göklerin üstündeki Cennet’i görecek ve seyredecek bir gözü, bu gözündeki perde altında, şükür ile, sabır ile bulabilirsin. İşte o perdeyi senin gözünden kaldıracak, o gözle seni baktıracak göz hekimi, Kur'ân‑ı Hakîm’dir.""",
        audioRes = "deva_14"
    ),
    Section(
        id = "16",
        title = "Onbeşinci Devâ",
        profile = "Ölümün Hakikatinden Ürken Hasta",
        profileDesc = "Vefatı yokluk veya sevdiklerinden ebedi ayrılık zannedip ayrılık hüznü yaşayanlar.",
        categoryId = "olum",
        body = """Ey âh u enîn eden hasta! Hastalığın sûretine bakıp âh eyleme; mânâsına bak, oh de. Eğer hastalığın mânâsı güzel bir şey olmasa idi, Hàlık‑ı Rahîm en sevdiği ibâdına hastalıkları vermezdi. Hâlbuki, hadîs-i sahîhte vardır ki; اَشَدُّ النَّاسِ بَلَاءً اَلْاَنْبِيَاءُ ثُمَّ الْاَوْلِيَاءُ ، اَلْاَمْثَلُ فَالْاَمْثَلُ (ev kemâ kàl). Yani; “En ziyâde musîbet ve meşakkate giriftâr olanlar, insanların en iyisi, en kâmilleridirler.” Başta Hazret‑i Eyyûb Aleyhisselâm, enbiyâlar, sonra evliyâlar ve sonra ehl-i salâhat, çektikleri hastalıklara birer ibâdet-i hàlisa, birer hediye-i Rahmâniye nazarıyla bakmışlar, sabır içinde şükretmişler; Hàlık-ı Rahîm’in rahmetinden gelen bir ameliyât-ı cerrâhiye nev'inden görmüşler.

Sen, ey âh u fîzar eden hasta! Bu nurânî kafileye iltihak etmek istersen, sabır içinde şükret. Yoksa şekvâ etsen, onlar seni kafilelerine almayacaklar. Ehl‑i gafletin çukurlarına düşersin. Karanlıklı bir yolda gideceksin.

Evet, hastalıkların bir kısmı var ki, eğer ölümle neticelense, manevî şehîd hükmünde, şehâdet gibi bir velâyet derecesine sebebiyet verir. Ezcümle; çocuk doğurmaktan gelen hastalıklar Hâşiye ve karın sancısıyla, gark ve hark ve tâun ile vefât eden, şehîd‑i manevî olduğu gibi, çok mübârek hastalıklar var ki, velâyet derecesini ölümle kazandırır.

Hem hastalık, dünya aşkını ve alâkasını hafifleştirdiğinden, vefât ile dünyadan, ehl‑i dünya için gayet elîm ve acı olan müfârakatı tahfif eder, bazen de sevdirir.""",
        audioRes = "deva_15"
    ),
    Section(
        id = "17",
        title = "Onaltıncı Devâ",
        profile = "İnsanların Merhametini Arayan / Şikayet Eden Hasta",
        profileDesc = "Allah’a tevekkülü bırakıp halini sürekli insanlara açarak acındırmaya çalışanlar.",
        categoryId = "sukur",
        body = """Ey sıkıntıdan şekvâ eden hasta! Hastalık, hayat‑ı ictimâiye-i insaniyede en mühim ve gayet güzel olan hürmet ve merhameti telkin eder. Çünkü; insanı vahşete ve merhametsizliğe sevkeden istiğnâdan kurtarıyor.

Çünkü; اِنَّ الْاِنْسَانَ لَيَطْغٰى ❋ اَنْ رَاٰهُ اسْتَغْنٰى sırrıyla, sıhhat ve âfiyetten gelen istiğnâda bulunan bir nefs‑i emmâre, şâyân-ı hürmet çok uhuvvetlere karşı hürmeti hissetmez. Ve şâyân-ı merhamet ve şefkat olan musîbet-zedelere ve hastalıklılara merhameti duymaz.

Ne vakit hasta olsa, o hastalıkta aczini ve fakrını anlar, lâyık‑ı hürmet olan ihvânlarına ihtiram eder. Ziyaretine gelen veya ona yardım eden mü'min kardeşlerine karşı hürmeti hisseder. Ve rikkat-i cinsiyeden gelen şefkat-i insaniye ve en mühim bir haslet-i İslâmiye olan, musîbet-zedelere karşı merhameti hissedip, onları nefsine kıyâs ederek, onlara tam mânâsıyla acır, şefkat eder, elinden gelse muâvenet eder. Hiç olmazsa duâ eder, hiç olmazsa şer'an sünnet olan keyfini sormak için ziyaretine gider, sevâb kazanır.""",
        audioRes = "deva_16"
    ),
    Section(
        id = "18",
        title = "Onyedinci Devâ",
        profile = "Nefsini Terbiye Edemeyen Hasta",
        profileDesc = "Sıhhat anında nefsin firavunluğuna kapılıp, hastalığın getirdiği acz ve zaafın manevi kıymetini anlamayanlar.",
        categoryId = "sukur",
        body = """Ey hastalık vâsıtasıyla hayrat yapamamaktan şekvâ eden hasta! Şükret. Hayratın en hàlisinin kapısını sana açan, hastalıktır. Hastalık, mütemâdiyen hastaya ve lillâh için hastaya bakıcılara sevâb kazandırmakla beraber, duânın makbûliyetine en mühim bir vesiledir.

Evet, hastalara bakmak, ehl‑i îmân için mühim sevâbı vardır. Hastaların keyfini sormak, fakat hastayı sıkmamak şartıyla ziyaret etmek, Sünnet-i Seniye’dir; keffâretü'z-zünûb olur. Hadîste vardır ki; “Hastaların duâsını alınız; onların duâsı makbûldür.” Bâhusus hasta, akrabadan olsa, hususan peder ve vâlide olsa, onlara hizmet mühim bir ibâdettir, mühim bir sevâbdır. Hastaların kalbini hoşnud etmek, tesellî vermek, mühim bir sadaka hükmüne geçer.

Bahtiyardır o evlâd ki; peder ve vâlidesinin hastalık zamanında, onların serîü't‑teessür olan kalblerini memnun edip hayır duâlarını alır. Evet, hayat-ı ictimâiyede en muhterem bir hakikat olan peder ve vâlidesinin şefkatlerine mukâbil, hastalıkları zamanında kemâl-i hürmet ve şefkat-i ferzendâne ile mukàbele eden o iyi evlâdın vaziyetini ve insaniyetin ulviyetini gösteren o vefâdâr levhaya karşı, hattâ melâikeler dahi “Mâşâallâh, Bârekallâh” deyip alkışlıyorlar.

Evet, hastalık zamanında, hastalık elemini hiçe indirecek gayet hoş ve ferâhlı, etrafında tezâhür eden şefkatlerden ve acımak ve merhametlerden gelen lezzetler var.

Hastanın duâsının makbûliyeti ehemmiyetli bir mes'eledir. Ben otuz‑kırk seneden beri, bendeki kulunç denilen bir hastalıktan şifâ için duâ ederdim. Ben anladım ki, hastalık duâ için verilmiş. Duâ ile duâyı, yani, duâ kendi kendini kaldırmadığından, anladım ki; duânın neticesi uhrevîdir, Hâşiye kendisi de bir nev'i ibâdettir ve hastalık ile aczini anlayıp Dergâh‑ı İlâhiye’ye ilticâ eder. Onun için, otuz senedir şifâ duâsını ettiğim hâlde duâm zâhirî kabûl olmadığından, duâyı terk etmek kalbime gelmedi.

Zîra hastalık duânın vaktidir; şifâ duânın neticesi değil. Belki Cenâb‑ı Hakîm-i Rahîm şifâ verse, fazlından verir.

Hem duâ istediğimiz tarzda kabûl olmazsa, makbûl olmadı denilmez. Hàlık‑ı Hakîm daha iyi biliyor; menfaatimize hayırlı ne ise onu verir. Bazen dünyaya ait duâlarımızı, menfaatimiz için âhiretimize çevirir, öyle kabûl eder. Her ne ise...

Hastalık sırrıyla hulûsiyet kazanan, hususan za'f ve aczden ve tezellül ve ihtiyaçtan gelen bir duâ, kabûle çok yakındır. Hastalık böyle hàlis bir duânın medârıdır. Hem dindar olan hasta, hem hastaya bakan mü'minler de bu duâdan istifade etmelidirler.""",
        audioRes = "deva_17"
    ),
    Section(
        id = "19",
        title = "Onsekizinci Devâ",
        profile = "Şükrü Unutan Hasta",
        profileDesc = "Vücudundaki yüzlerce sağlam azayı ve nimeti görmezden gelip yalnız hasta olan tek bir yerine odaklananlar.",
        categoryId = "sukur",
        body = """Ey şükrü bırakıp şekvâya giren hasta! Şekvâ bir haktan gelir. Senin bir hakkın zâyi' olmamış ki, şekvâ ediyorsun. Belki senin üstünde hak olan çok şükürler var, yapmadın. Cenâb‑ı Hakk’ın hakkını vermeden, haksız bir sûrette hak istiyorsun gibi şekvâ ediyorsun. Sen, kendinden yukarı mertebelerdeki sıhhatli olanlara bakıp şekvâ edemezsin. Belki sen, kendinden sıhhat noktasında aşağı derecelerde bulunan bîçâre hastalara bakıp şükretmekle mükellefsin. Senin elin kırık ise, kesilmiş ellere bak! Bir gözün yoksa, iki gözü de olmayan a'mâlara bak! Allah’a şükret!

Evet, ni'mette kendinden yukarıya bakıp şekvâ etmeye hiç kimsenin hakkı yoktur. Ve musîbette herkesin hakkı, kendinden musîbet noktasında daha yukarı olanlara bakmaktır ki, şükretsin. Bu sır bazı risalelerde bir temsîl ile izâh edilmiş. İcmâli şudur ki:

Bir zât, bir bîçâreyi, bir minârenin başına çıkarıyor. Minârenin her basamağında ayrı ayrı birer ihsân, birer hediye veriyor. Tam minârenin başında da en büyük bir hediyeyi veriyor. O, mütenevvi' hediyelere karşı ondan teşekkür ve minnetdârlık istediği hâlde; o hırçın adam, bütün o basamaklarda gördüğü hediyeleri unutup veyâhut hiçe sayıp, şükretmeyerek, yukarıya bakar, “Keşke bu minâre daha uzun olsaydı, daha yukarıya çıksaydım! Ne için o dağ gibi veyâhut öteki minâre gibi çok yüksek değil?” deyip şekvâya başlarsa, ne kadar bir küfran‑ı ni'mettir, bir haksızlıktır!

Öyle de; bir insan hiçlikten vücûda gelip, taş olmayarak, ağaç olmayıp, hayvan kalmayarak, insan olup, müslüman olarak, çok zaman sıhhat ve âfiyet görüp yüksek bir derece‑i ni'met kazandığı hâlde; bazı ârızalarla, sıhhat ve âfiyet gibi bazı ni'metlere lâyık olmadığı veya sû-i ihtiyarıyla veya sû-i isti'mâliyle elinden kaçırdığı veyâhut eli yetişmediği için şekvâ etmek, sabırsızlık göstermek, “Aman ne yaptım, böyle başıma geldi?” diye Rubûbiyet-i İlâhiye’yi tenkid etmek gibi bir hâlet, maddî hastalıktan daha musîbetli, manevî bir hastalıktır. Kırılmış el ile döğüşmek gibi, şikâyetiyle hastalığını ziyâdeleştirir.

Âkıl odur ki, لِكُلِّ مُص۪يبَةٍ اِنَّا لِلّٰهِ وَاِنَّٓا اِلَيْهِ رَاجِعُونَ sırrıyla teslîm olup sabretsin; tâ o hastalık vazifesini bitirsin, gitsin.""",
        audioRes = "deva_18"
    ),
    Section(
        id = "20",
        title = "Ondokuzuncu Devâ",
        profile = "Hastalığı Sadece Azap Gören Hasta",
        profileDesc = "Cenab-ı Hakk’ın Şâfî (şifa veren) isminin cilvesini ve tecellisini fark edemeyenler.",
        categoryId = "vehim",
        body = """Cemîl‑i Zülcelâl’in bütün isimleri, “Esmâü'l-Hüsnâ” tâbir-i Samedânîsiyle gösteriyor ki, güzeldirler. Mevcûdât içinde en latîf, en güzel, en câmi' âyine-i Samediyet de, hayattır. Güzelin âyinesi güzeldir. Güzelin mehâsinlerini gösteren âyine güzelleşir. O âyinenin başına o güzelden ne gelse, güzel olduğu gibi; hayatın başına dahi ne gelse, hakikat noktasında güzeldir. Çünkü; güzel olan Esmâü'l-Hüsnâ’nın güzel nakışlarını gösterir.

Hayat, dâima sıhhat ve âfiyette yeknesak gitse, nâkıs bir âyine olur. Belki bir cihette adem ve yokluğu ve hiçliği ihsâs edip sıkıntı verir, hayatın kıymetini tenzîl eder, ömrün lezzetini sıkıntıya kalbeder. Çabuk vaktimi geçireceğim diye, sıkıntıdan ya sefâhete, ya eğlenceye atılır. Hapis müddeti gibi, kıymetdâr ömrüne adâvet edip, çabuk öldürüp geçirmek istiyor.

Fakat tahavvülde ve harekette ve ayrı ayrı tavırlar içinde yuvarlanmakta olan bir hayat, kıymetini ihsâs ediyor, ömrün ehemmiyetini ve lezzetini bildiriyor. Meşakkatte ve musîbette dahi olsa, ömrün geçmesini istemiyor. “Aman güneş batmadı, ya gece bitmedi.” diye sıkıntısından “Of!. Of!.” etmiyor.

Evet, gayet zengin ve işsiz, istirahat döşeğinde herşeyi mükemmel bir efendiden sor: “Ne hâldesin?” Elbette, “Aman vakit geçmiyor; gel bir şeş beş oynayalım.” Veyâhut “Vakti geçirmek için bir eğlence bulalım” gibi müteellimâne sözleri ondan işiteceksin. Veyâhut tûl‑i emelden gelen, “Bu şeyim eksik; keşke şu işi yapsaydım.” gibi şekvâları işiteceksin.

Sen bir musîbet‑zede veya işçi ve meşakkatli bir hâlde olan bir fakirden sor: “Ne hâldesin?” Aklı başında ise diyecek ki: “Şükürler olsun Rabbime, iyiyim, çalışıyorum. Keşke çabuk güneş gitmeseydi, bu işi de bitirseydim! Vakit çabuk geçiyor; ömür durmuyor, gidiyor. Vâkıa zahmet çekiyorum; fakat bu da geçer. Herşey böyle çabuk geçiyor.” diye, ma'nen ömür ne kadar kıymetdâr olduğunu, geçmesindeki teessüfle bildiriyor.

Demek, meşakkat ve çalışmakla, ömrün lezzetini ve hayatın kıymetini anlıyor. İstirahat ve sıhhat ise, ömrü acılaştırıyor ki, geçmesini arzu ediyor.

Ey hasta kardeş! Bil ki, başka risalelerde tafsilâtıyla kat'î bir sûrette isbât edildiği gibi; musîbetlerin, şerlerin, hattâ günahların aslı ve mâyesi ademdir. Adem ise şerdir, karanlıktır. Yeknesak istirahat, sükût, sükûnet, tevakkuf gibi hâletler, ademe, hiçliğe yakınlığı içindir ki, ademdeki karanlığı ihsâs edip sıkıntı veriyor. Hareket ve tahavvül ise vücûddur, vücûdu ihsâs eder. Vücûd ise hàlis hayırdır, nurdur.

Mâdem hakikat budur; sendeki hastalık, kıymetdâr hayatı sâfîleştirmek, kuvvetleştirmek, terakkî ettirmek ve vücûdundaki sâir cihâzât‑ı insaniyeyi o hastalıklı uzvun etrafına muâvenetdârâne müteveccih etmek ve Sâni'-i Hakîm’in ayrı ayrı isimlerinin nakışlarını göstermek gibi çok vazifeler için, o hastalık senin vücûduna misâfir olarak gönderilmiştir. İnşâallâh çabuk vazifesini bitirir, gider. Ve âfiyete der ki: “Sen gel, benim yerimde dâimî kal, vazifeni gör. Bu hâne senindir, âfiyetle kal.”""",
        audioRes = "deva_19"
    ),
    Section(
        id = "21",
        title = "Yirminci Devâ",
        profile = "Derdine Derman Arayan Hasta",
        profileDesc = "Hastalığın bizzat kendisinin manevi bir ilaç ve şifa vesilesi olduğunu göremeyenler.",
        categoryId = "vehim",
        body = """Ey derdine derman arayan hasta! Hastalık iki kısımdır. Bir kısmı hakîki, bir kısmı vehmîdir. Hakîki kısmı ise, Şâfi‑i Hakîm-i Zülcelâl, küre-i arz olan eczâhâne-i kübrâsında, her derde bir devâ istif etmiş. O devâlar ise dertleri isterler. Her derde bir derman halk etmiştir. Tedâvi için ilâçları almak, isti'mâl etmek meşrûdur; fakat te'siri ve şifâyı Cenâb-ı Hak’tan bilmek gerektir. Derdi O verdiği gibi, şifâyı da O veriyor.

Hâzık, mütedeyyin hekimlerin tavsiyelerini tutmak, ehemmiyetli bir ilâçtır. Çünkü ekser hastalıklar sû‑i isti'mâlâttan, perhizsizlikten ve isrâftan ve hatîâttan ve sefâhetten ve dikkatsizlikten geliyor. Mütedeyyin hekim, elbette meşrû bir dâirede nasihat eder ve vesâyâda bulunur. Sû-i isti'mâlâttan, isrâfâttan men' eder, tesellî verir. Hasta, o vesâyâ ve o tesellîye i'timâd edip hastalığı hafifleşir; sıkıntı yerinde bir ferâhlık verir.

Amma vehmî hastalık kısmı ise, onun en müessir ilâcı, ehemmiyet vermemektir. Ehemmiyet verdikçe o büyür, şişer. Ehemmiyet vermezse küçülür, dağılır. Nasıl ki, arılara iliştikçe insanın başına üşüşürler; aldırmazsan dağılırlar. Hem karanlıkta gözüne sallanan bir ipten gelen bir hayâle ehemmiyet verdikçe büyür, hattâ bazen onu dîvâne gibi kaçırır. Ehemmiyet vermezse, âdi bir ipin yılan olmadığını görür, başındaki telâşına güler.

Bu vehmî hastalık çok devam etse, hakikate inkılâb eder. Vehham ve asabî insanlarda fenâ bir hastalıktır; habbeyi kubbe yapar, kuvve‑i maneviyesi kırılır. Hususan merhametsiz yarım hekimlere veyâhut insafsız doktorlara rastgelse, evhâmını daha ziyâde tahrîk eder. Zengin ise malı gider; yoksa ya aklı gider veya sıhhati gider.""",
        audioRes = "deva_20"
    ),
    Section(
        id = "22",
        title = "Yirmibirinci Devâ",
        profile = "Hastalığın Acısından Feryat Eden Hasta",
        profileDesc = "Çektiği ıstırabın dostların ve meleklerin şefkatine, ahirette ise büyük mükâfata vesile olduğunu unutanlar.",
        categoryId = "vehim",
        body = """Ey hasta kardeş! Senin hastalığında maddî elem var. Fakat o maddî elemin te'sirini izâle edecek ehemmiyetli bir manevî lezzet seni ihâta ediyor. Çünkü; peder ve vâliden ve akraban varsa, çoktan beri unuttuğun gayet lezzetli o şefkatleri senin etrafında yeniden uyanıp, çocukluk zamanında gördüğün o şirin nazarları yine görmekle beraber; çok gizli, perdeli kalan etrafındaki dostluklar, hastalığın câzibesiyle yine sana karşı muhabbetdârâne baktıklarından, elbette onlara karşı senin bu maddî elemin pek ucuz düşer.

Hem sen müftehirâne hizmet ettiğin ve iltifatlarını kazanmasına çalıştığın zâtlar, hastalığın hükmüyle sana merhametkârâne hizmetkârlık ettiklerinden, efendilerine efendi oldun. Hem insanlardaki rikkat‑i cinsiyeyi ve şefkat-i nev'iyeyi kendine celbettiğinden, hiçten, çok yardımcı ahbab ve şefkatli dost buldun. Hem çok meşakkatli hizmetlerden paydos emrini yine hastalıktan aldın, istirahat ediyorsun.

Elbette senin cüz'î elemin, bu manevî lezzetlere karşı seni şekvâya değil, teşekküre sevketmelidir.""",
        audioRes = "deva_21"
    ),
    Section(
        id = "23",
        title = "Yirmiikinci Devâ",
        profile = "Nüzul (Felç) Gibi Ağır ve Müzmin Hastalığa Tutulanlar",
        profileDesc = "Yatağa bağımlı, uzun süreli ve ağır bedeni kısıtlılık yaşayan hastalar.",
        categoryId = "yalnizlik",
        body = """Ey nüzûl gibi ağır hastalıklara mübtelâ olan kardeş! Evvelâ sana müjde ediyorum ki, mü'min için nüzûl mübârek sayılıyor. Bunu çoktan ehl‑i velâyetten işitiyordum, sırrını bilmezdim. Bir sırrı şöyle kalbime geliyor ki:

Ehlullâh, Cenâb‑ı Hakk’a vâsıl olmak ve dünyanın azîm manevî tehlikelerinden kurtulmak ve saâdet-i ebediyeyi te'min etmek için, iki esâsı ihtiyaren takib etmişler:

Birisi: Râbıta‑i mevttir. Yani; dünya fânî olduğu gibi, kendisi de içinde vazifedâr fânî bir misâfir olduğunu düşünmekle, hayat-ı ebedîsine o sûretle çalışmışlar.

İkincisi: Nefs‑i emmârenin ve kör hissiyatın tehlikelerinden kurtulmak için, çillelerle, riyâzetlerle nefs-i emmârenin öldürülmesine çalışmışlar.

Sizler, ey yarı vücûdunun sıhhatini kaybeden kardeş! Sen, ihtiyarsız, kısa ve kolay ve sebeb‑i saâdet olan iki esâs sana verilmiş ki, dâima senin vücûdunun vaziyeti, dünyanın zevâlini ve insanın fânî olduğunu ihtar ediyor. Daha dünya seni boğamıyor, gaflet senin gözünü kapayamıyor. Ve yarım insan vaziyetinde bir zâta, nefs-i emmâre, elbette hevesât-ı rezîle ile ve nefsânî müştehiyât ile onu aldatamaz; çabuk o nefsin belâsından kurtulur.

İşte mü'min, sırr‑ı îmân ile ve teslîmiyet ve tevekkül ile, o ağır nüzûl gibi hastalıktan, az bir zamanda, ehl-i velâyetin çilleleri gibi istifade edebilir. O vakit o ağır hastalık çok ucuz düşer.""",
        audioRes = "deva_22"
    ),
    Section(
        id = "24",
        title = "Yirmiüçüncü Devâ",
        profile = "Kimsesiz ve Garip Hastalar",
        profileDesc = "Gurbette, yanında bakacak veya teselli edecek kimsesi bulunmayan yalnız hastalar.",
        categoryId = "yalnizlik",
        body = """Ey kimsesiz, garîb, bîçâre hasta! Hastalığınla beraber kimsesizlik ve gurbet, sana karşı en katı kalbleri rikkate getirirse ve nazar‑ı şefkati celb ederse; acaba Kur'ânın bütün sûrelerinin başlarında kendini “Rahmânürrahîm” sıfatıyla bize takdim eden ve bir lem'a-i şefkatiyle umum yavrulara karşı umum vâlideleri, o hàrika şefkatiyle terbiye ettiren ve her baharda bir cilve-i rahmetiyle zemin yüzünü ni'metlerle dolduran ve ebedî bir hayattaki Cennet, bütün mehâsiniyle bir cilve-i rahmeti olan senin Hàlık-ı Rahîm’ine îmân ile intisabın ve O’nu tanıyıp hastalığın lisân-ı acziyle niyâzın, elbette senin bu gurbetteki kimsesizlik hastalığın, herşeye bedel O’nun nazar-ı rahmetini sana celb eder. Mâdem O var, sana bakar; sana herşey var.

Asıl gurbette, kimsesizlikte kalan odur ki; îmân ve teslîmiyetle O’na intisab etmesin veya intisabına ehemmiyet vermesin.""",
        audioRes = "deva_23"
    ),
    Section(
        id = "25",
        title = "Yirmidördüncü Devâ",
        profile = "Masum Çocuklar ve Hasta Çocukların Ebeveynleri",
        profileDesc = "Hastalık çeken masum sabiler ile onların acısına dayanamayan anne-babalar.",
        categoryId = "yalnizlik",
        body = """Ey masûm hasta çocuklara ve masûm çocuklar hükmünde olan ihtiyarlara hizmet eden hastabakıcılar! Sizin önünüzde mühim bir ticâret‑i uhreviye var. Şevk ve gayret ile o ticâreti kazanınız.

Masûm çocukların hastalıklarını, o nâzik vücûdlara bir idman, bir riyâzet ve ileride dünyanın dağdağalarına mukâvemet verdirmek için bir şırınga ve bir terbiye‑i Rabbâniye gibi, çocuğun hayat-ı dünyeviyesine ait çok hikmetlerle beraber ve hayat-ı rûhiyesine ve tasaffî-i hayatına medâr olacak büyüklerdeki keffâretü'z-zünûb yerine, manevî ve ileride veyâhut Âhirette terakkiyât-ı maneviyesine medâr şırıngalar nev'indeki hastalıklardan gelen sevâb, peder ve vâlidelerinin defter-i a'mâline, bilhassa sırr-ı şefkatle çocuğun sıhhatini kendi sıhhatine tercih eden vâlidesinin sahife-i hasenâtına girdiği, ehl-i hakikatçe sâbittir.

İhtiyarlara bakmak ise; hem azîm sevâb almakla beraber, o ihtiyarların – ve bilhassa peder ve vâlide ise – duâlarını almak ve kalblerini hoşnud etmek ve vefâkârâne hizmet etmek, hem bu dünyadaki saâdete, hem Âhiretin saâdetine medâr olduğu, rivâyât‑ı sahîha ile ve çok vukûât-ı tarihiye ile sâbittir. İhtiyar peder ve vâlidesine tam itâat eden bahtiyar bir veled, evlâdından aynı vaziyeti gördüğü gibi; bedbaht bir veled, eğer ebeveynini rencîde etse, azâb-ı uhrevîden başka, dünyada çok felâketlerle cezasını gördüğü, çok vukûâtla sâbittir.

Evet ihtiyarlara, masûmlara, yalnız akrabasına bakmak değil; belki ehl‑i îmân – mâdem sırr-ı îmânla uhuvvet-i hakîkiye var – onlara rastgelse, muhterem hasta ihtiyar ona muhtaç olsa, rûh u canla ona hizmet etmek İslâmiyetin muktezâsıdır.""",
        audioRes = "deva_24"
    ),
    Section(
        id = "26",
        title = "Yirmibeşinci Devâ",
        profile = "Hastalara Hizmet Eden Refakatçi ve Hekimler",
        profileDesc = "Doğrudan hastalar değil; hastalara bakan, hizmet eden, dua alan hekim, hemşire ve refakatçiler.",
        categoryId = "yalnizlik",
        body = """Ey hasta kardeşler! Siz, gayet nâfi' ve her derde devâ ve hakîki lezzetli kudsî bir tiryâk isterseniz, îmânınızı inkişaf ettiriniz. Yani; tevbe ve istiğfar ile ve namaz ve ubûdiyetle, o tiryâk‑ı kudsî olan îmânı ve îmândan gelen ilâcı isti'mâl ediniz.

Evet, dünyaya muhabbet ve alâka yüzünden, güyâ, âdeta ehl‑i gafletin dünya gibi büyük, hasta, manevî bir vücûdu vardır. Îmân ise, o dünya gibi zevâl ve firâk darbelerine, yara ve bere içinde olan o manevî vücûduna birden şifâ verip, yaralardan kurtarıp, hakîki şifâ verdiğini pek çok risalelerde kat'î isbât etmişiz. Başınızı ağrıtmamak için kısa kesiyorum.

Îmân ilâcı ise, ferâizi mümkün oldukça yerine getirmekle te'sirini gösteriyor. Gaflet ve sefâhet ve hevesât‑ı nefsâniye ve lehviyât-ı gayr-ı meşrûa, o tiryâkın te'sirini men' eder. Hastalık mâdem gafleti kaldırıyor, iştihâyı kesiyor, gayr-ı meşrû keyiflere gitmeye mâni oluyor; ondan istifade ediniz. Hakîki îmânın kudsî ilâçlarından ve nurlarından, tevbe ve istiğfar ile, duâ ve niyâz ile isti'mâl ediniz.

Cenâb‑ı Hak sizlere şifâ versin, hastalıklarınızı keffâretü'z-zünûb yapsın. Âmîn, âmîn, âmîn!

اَلْحَمْدُ لِلّٰهِ الَّذ۪ي هَدٰينَا لِهٰذَا وَمَا كُنَّا لِنَهْتَدِيَ لَوْلَٓا اَنْ هَدٰينَا اللّٰهُ لَقَدْ جَٓاءَتْ رُسُلُ رَبِّنَا بِالْحَقِّ

سُبْحَانَكَ لَا عِلْمَ لَنَٓا اِلَّا مَا عَلَّمْتَنَٓا اِنَّكَ اَنْتَ الْعَل۪يمُ الْحَك۪يمُ

اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ ، طِبِّ الْقُلُوبِ وَدَوَائِهَا وَعَافِيَةِ الْاَبْدَانِ وَشِفَائِهَا وَنُورِ الْاَبْصَارِ وَضِيَائِهَا وَعَلٰى اٰلِهِ وَصَحْبِهِ وَسَلِّمْ

وَهُوَ لِكُلِّ دَاءٍ دَوَاءٌMeâli: “Bu kitab her derde dermandır.” Tevâfukât‑ı latîfedendir ki; Re'fet Bey’in birinci tesvîdden gayet sür'atle yazdığı nüsha ile beraber, Husrev’in yazdığı diğer bir nüshada, ihtiyarsız, hiç düşünmeden satır başlarında gelen elif’leri saydık. Aynen bu وَهُوَ لِكُلِّ دَاءٍ دَوَاءٌ cümlesine tevâfuk ediyor. Hâşiye Hem bu risalenin müellifinin Said ismine, bir tek fark ile yine tevâfuk ediyor. Hâşiye Yalnız, risalenin ünvânına ait yazıdaki bir elif hesaba dâhil edilmemiştir.

Cây‑i hayrettir ki; Süleyman Rüşdü’nün yazdığı nüsha, hiç elif hâtıra gelmeden ve düşünmeden, yüz on dört elif, yüz on dört şifâ‑i kudsiyeyi tazammun eden yüz on dört suver-i Kur'âniye’nin adedine tevâfukla beraber, وَهُوَ لِكُلِّ دَاءٍ دَوَاءٌ şeddeli lâm bir sayılmak cihetiyle, yüz on dört harfine tam tamına tevâfuk ediyor.""",
        audioRes = "deva_25"
    ),
    Section(
        id = "27",
        title = "Yirmibeşinci Lem'anın Zeyli",
        profile = "Zeyl (Ek Açıklama)",
        profileDesc = "Onyedinci Mektûb olup, Mektûbat Mecmuası’na idhal edildiğinden buraya derc edilmedi.",
        categoryId = "sabir",
        body = """Onyedinci Mektûb olup, Mektûbat Mecmuası’na idhal edildiğinden buraya derc edilmedi.""",
        audioRes = null
    )
)

val duas = listOf(
    Dua(
        title = "Hz. Eyyûb'un (A.S.) Şifa Duası",
        text = """Arapça Okunuşu:
"Ennî messeniyed-durru ve ente erhamur-râhimîn." (Enbiyâ, 83)

Türkçe Anlamı:
"Ya Rabbi! Başıma bu dert geldi, sen merhametlilerin en merhametlisisin."

Açıklama:
Hazret-i Eyyûb Aleyhisselâm'ın bu münâcatı, hem samimi bir teslimiyet hem de musibet ve hastalıklara karşı en tesirli şifa dualarındandır."""
    ),
    Dua(
        title = "Peygamber Efendimiz'in (S.A.V.) Şifa Duası",
        text = """Arapça Okunuşu:
"Allâhümme Rabben-nâs! Ezhibil-be's, işfi ente'ş-şâfî, lâ şifâe illâ şifâük, şifâen lâ yuğâdiru sekamâ."

Türkçe Anlamı:
"Ey insanların Rabbi olan Allah'ım! Bu hastalığı gider, şifa ver. Sen şifa verensin; senin şifandan başka şifa yoktur. Öyle bir şifa ver ki geride hiçbir hastalık ve elem bırakmasın." (Buhârî, Tıb, 38)"""
    ),
    Dua(
        title = "Cebrail'in (A.S.) Okuduğu Şifa Duası",
        text = """Arapça Okunuşu:
"Bismillâhi erkîke, min külli şey'in yü'zîke, min şerri külli nefsin ev aynin hâsid, Allâhu yeşfîke, bismillâhi erkîke."

Türkçe Anlamı:
"Allah'ın adıyla seni rahatsız eden her şeyden, her nefsin ve hasetçi gözün şerrinden sana şifa dilerim. Allah sana şifa versin; Allah'ın adıyla sana şifa dilerim." (Müslim, Selâm, 40)"""
    ),
    Dua(
        title = "Ağrı ve Sancılara Karşı Dua",
        text = """Arapça Okunuşu:
Sağ elini ağrıyan yere koyup 3 kere "Bismillâh" dedikten sonra 7 kere:
"Eûzü bi-izzetillâhi ve kudretihî min şerri mâ ecidü ve uhâziru."

Türkçe Anlamı:
"Hissettiğim ve çekindiğim acının şerrinden Allah'ın izzet ve kudretine sığınırım." (Müslim, Selâm, 67)"""
    ),
    Dua(
        title = "Sabır ve Ferahlık Duası",
        text = """Arapça Okunuşu:
"Rabbenâ efrığ aleynâ sabran ve sebbit ekdâmenâ." (Bakara, 250)
"Lâ ilâhe illâ ente sübhâneke innî küntü minez-zâlimîn." (Enbiyâ, 87)

Türkçe Anlamı:
"Ey Rabbimiz! Üzerimize sabır yağdır ve ayaklarımızı sabit kıl."
"Senden başka ilah yoktur, sen her noksanlıktan münezzehsin. Muhakkak ki ben nefsime zulmedenlerden oldum."""
    )
)

fun todaySection(): Section {
    val day = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
    return sections[day % sections.size]
}
