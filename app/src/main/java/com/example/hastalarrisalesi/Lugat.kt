package com.example.hastalarrisalesi

data class LugatEntry(
    val word: String,
    val root: String,
    val meaning: String,
    val origin: String? = null
)

/**
 * Hastalar Risalesi (Yirmibeşinci Lem'a) lügatçesi.
 * Osmanlıca, Arapça ve Farsça kökenli kelimelerin Risale-i Nur ıstılahındaki manalarını içerir.
 */
val hastalarLugat = listOf(
    LugatEntry("âfiyet", "afiyet", "Beden ve ruh sağlığı, esenlik, hastalık ve sıkıntılardan uzak olma hali.", "Arapça"),
    LugatEntry("acz", "acz", "Güçsüzlük, kuvvetsizlik, insanın yaratıcısı karşısındaki çaresizliği.", "Arapça"),
    LugatEntry("ahkâm", "hüküm", "Hükümler, kanunlar, emir ve esaslar.", "Arapça"),
    LugatEntry("ahsen-i takvîm", "ahsen", "En güzel kıvam ve surette yaratılış, insanın yüksek manevi fıtratı.", "Arapça"),
    LugatEntry("akreb", "akreb", "En yakın, pek yakın olan.", "Arapça"),
    LugatEntry("amel", "amel", "İş, davranış, dinin emirlerine uygun yapılan ibadet ve hayırlar.", "Arapça"),
    LugatEntry("asabî", "asabi", "Sinir sistemiyle ilgili; çabuk öfkelenen, telaşlı.", "Arapça"),
    LugatEntry("a'zâ", "uzuv", "Organlar, vücudun azaları ve parçaları.", "Arapça"),
    LugatEntry("bâd-i hevâ", "bad-i heva", "Boşu boşuna, yok yere, havadan, zahmetsizce zayi olarak.", "Farsça"),
    LugatEntry("bâki", "baki", "Ebedî, devamlı, sonu olmayan, Cenâb-ı Hakk'ın bir ismi.", "Arapça"),
    LugatEntry("bâkî", "baki", "Ölümsüz, daimi, sonsuz olan.", "Arapça"),
    LugatEntry("bîçâre", "biçare", "Çaresiz, zavallı, acınacak durumda olan.", "Farsça"),
    LugatEntry("bîvefa", "bivefa", "Vefasız, sadakatsiz, güvenilmez olan.", "Farsça"),
    LugatEntry("cây-i hayret", "hayret", "Hayret edilecek durum, şaşılacak yer ve hal.", "Farsça"),
    LugatEntry("Cenâb-ı Hak", "cenab-ı hak", "Sonsuz şeref, yücelik ve kudret sahibi Yüce Allah.", "Arapça"),
    LugatEntry("cihâzât", "cihazat", "Cihazlar, donanımlar, insana verilen maddi ve manevi kabiliyetler.", "Arapça"),
    LugatEntry("cilve", "cilve", "Görünme, tecelli, ilahi isim ve sıfatların eserde belirmesi.", "Arapça"),
    LugatEntry("çendan", "çendan", "Her ne kadar, gerçi, gerçi öyle ise de.", "Farsça"),
    LugatEntry("dâimî", "daimi", "Sürekli, aralıksız, devamlı olan.", "Arapça"),
    LugatEntry("dalâlet", "dalalet", "Doğru yoldan sapma, inançsızlık, batıl yollara girme.", "Arapça"),
    LugatEntry("darb-ı mesel", "darb-ı mesel", "Atasözü, ibretli ve meşhur söz.", "Arapça"),
    LugatEntry("dehşetli", "dehşetli", "Büyük korku ve ürperti veren, sarsıcı.", "Arapça"),
    LugatEntry("dergâh", "dergah", "Cenab-ı Hakk'ın huzuru, manevi sığınak, kabul makamı.", "Farsça"),
    LugatEntry("derman", "derman", "İlaç, çare, dertten kurtulma vesilesi.", "Farsça"),
    LugatEntry("destan", "destan", "Dillerde anlatılan meşhur hikâye, ibretli söz.", "Farsça"),
    LugatEntry("devâ", "deva", "İlaç, derman, manevi ve maddi şifa çaresi.", "Arapça"),
    LugatEntry("ebedî", "ebedi", "Sonsuz, sonu olmayan, ebede ait.", "Arapça"),
    LugatEntry("ecel", "ecel", "Hayatın sona ermesi için takdir edilen muayyen vakit, ölüm anı.", "Arapça"),
    LugatEntry("ednâ", "edna", "En aşağı, en küçük, en basit derece.", "Arapça"),
    LugatEntry("ef'âl", "fiil", "Fiiller, işler, Cenab-ı Hakk'ın kâinattaki tasarruf ve icraatları.", "Arapça"),
    LugatEntry("ehl-i gaflet", "gaflet", "Ahiretten ve yaratılış gayesinden habersiz, dünyaya dalmış kimseler.", "Arapça"),
    LugatEntry("ehl-i îmân", "iman", "İnananlar, Allah'a ve ahirete hakkıyla iman eden mü'minler.", "Arapça"),
    LugatEntry("ehl-i sabır", "sabır", "Başa gelen bela ve hastalıklara tahammül edip şükredenler.", "Arapça"),
    LugatEntry("ehl-i sefâhet", "sefahet", "Dinen yasak olan zevk ve eğlencelere dalmış kimseler.", "Arapça"),
    LugatEntry("elem", "elem", "Acı, keder, dert, ruh ve bedenin duyduğu ıstırap.", "Arapça"),
    LugatEntry("envâ'", "nevi", "Çeşitler, türlü türlü türler ve kısımlar.", "Arapça"),
    LugatEntry("esbâb", "sebeb", "Sebepler, vasıtalar, perdeler.", "Arapça"),
    LugatEntry("Esmâ-i Hüsnâ", "esma-i hüsna", "Allah'ın en güzel isimleri (Rahîm, Şâfî, Hakîm, Rezzâk vb.).", "Arapça"),
    LugatEntry("evhâm", "vehim", "Kuruntular, asılsız şüpheler, zihinde büyütülen hayali korkular.", "Arapça"),
    LugatEntry("evrâd", "vird", "Düzenli okunan dualar, virdler, zikirler.", "Arapça"),
    LugatEntry("fânî", "fani", "Geçici, ölümlü, yok olmaya mahkûm olan.", "Arapça"),
    LugatEntry("fenâ", "fena", "Yokluk, geçicilik, fani dünyanın son bulması.", "Arapça"),
    LugatEntry("firâk", "firak", "Ayrılık, dostlardan ve sevdiklerinden uzak düşme acısı.", "Arapça"),
    LugatEntry("fıtrî", "fıtri", "Yaratılıştan gelen, tabii, yapmacıksız.", "Arapça"),
    LugatEntry("gaflet", "gaflet", "Dikkatsizlik, manevi uyuşukluk, ahireti ve Allah'ın huzurunda olduğunu unutma hali.", "Arapça"),
    LugatEntry("gayb", "gayb", "Görünmeyen, duyularla algılanamayan manevi ve ilahi âlem.", "Arapça"),
    LugatEntry("Hakîm", "hakim", "Her şeyi hikmetle, yerli yerinde ve faydalı yaratan Allah.", "Arapça"),
    LugatEntry("hakîkat", "hakikat", "Bir şeyin aslı, gerçeği, doğru ve sabit olan ilahi gerçek.", "Arapça"),
    LugatEntry("hâlât", "halat", "Haller, durumlar, keyfiyetler.", "Arapça"),
    LugatEntry("Hâlık", "halık", "Yaratan, her şeyi yoktan var eden Yüce Allah.", "Arapça"),
    LugatEntry("Hâlık-ı Rahîm", "halık", "Yarattıklarına sonsuz merhamet ve şefkat gösteren Yaratıcı.", "Arapça"),
    LugatEntry("hasenât", "hasene", "İyilikler, sevaplı ameller, hayırlar.", "Arapça"),
    LugatEntry("hâtırât", "hatırat", "Kalbe ve akla gelen düşünceler, ilhamlar, hatıralar.", "Arapça"),
    LugatEntry("havf", "havf", "Korku, Allah korkusu, tehlikeden çekinme.", "Arapça"),
    LugatEntry("hayat-ı fâniye", "hayat", "Geçici ve ölümlü dünya hayatı.", "Arapça"),
    LugatEntry("hayat-ı ebediye", "hayat", "Sonsuz, ölümsüz ahiret hayatı.", "Arapça"),
    LugatEntry("hırz-ı cân", "hırz", "Can gibi korunacak, hayat kadar kıymetli muhafaza.", "Arapça/Farsça"),
    LugatEntry("hikmet", "hikmet", "Her işin bir gaye, fayda ve nizam içinde yapılması; derin ilahi sır.", "Arapça"),
    LugatEntry("hulle", "hulle", "Cennet elbisesi, süslü ve kıymetli kumaş.", "Arapça"),
    LugatEntry("hürmet", "hürmet", "Saygı, tazim, hürmet gösterme.", "Arapça"),
    LugatEntry("hususan", "hususan", "Özellikle, bilhassa, en başta.", "Arapça"),
    LugatEntry("ıstırâb", "ıstırap", "Acı, sancı, ruh ve bedenin çektiği eziyet.", "Arapça"),
    LugatEntry("ibâdet", "ibadet", "Kulluk vazifesi, Allah'ın rızasını kazanmak için yapılan taat.", "Arapça"),
    LugatEntry("icmâlen", "icmal", "Özet olarak, kısaca, ana hatlarıyla.", "Arapça"),
    LugatEntry("i'tizâr", "itizar", "Özür dileme, mazeret beyan etme.", "Arapça"),
    LugatEntry("ihsân", "ihsan", "İyilik, lütuf, ikram, Allah'ın kuluna bağışı.", "Arapça"),
    LugatEntry("ihsâs", "ihsas", "Hissettirme, duyurma, farkına vardırılma.", "Arapça"),
    LugatEntry("ihtar", "ihtar", "Uyarı, hatırlatma, kalbe gelen ikaz.", "Arapça"),
    LugatEntry("ilticâ", "iltica", "Sığınma, korunmak için Allah'ın dergâhına başvurma.", "Arapça"),
    LugatEntry("iltifât", "iltifat", "İlgi gösterme, gönül alıcı muamele, şefkatli yöneliş.", "Arapça"),
    LugatEntry("îmân", "iman", "Allah'a ve O'nun bildirdiği esaslara kalpten inanıp tasdik etme.", "Arapça"),
    LugatEntry("in'âm", "inam", "Nimetlendirme, iyilik ve ihsanda bulunma.", "Arapça"),
    LugatEntry("inâyet", "inayet", "İlahi yardım, lütuf ve himaye.", "Arapça"),
    LugatEntry("inkisâr", "inkisar", "Kırılma, gönül kırgınlığı, acizlikten doğan hüzün.", "Arapça"),
    LugatEntry("irtihâl", "irtihal", "Göç etme, bu dünyadan ahiret yurduna göçüş, vefat.", "Arapça"),
    LugatEntry("istisnâ", "istisna", "Kural dışı tutma, genel kurala uymayan durum.", "Arapça"),
    LugatEntry("iyâdetü'l-marîz", "iyadet", "Hasta ziyareti, hastanın hal ve hatırını sorma sünneti.", "Arapça"),
    LugatEntry("kâfirâne", "kafirane", "İnkârcılara, imansızlara yakışır şekilde.", "Farsça"),
    LugatEntry("kat'î", "kat'i", "Kesin, şüphesiz, apaçık.", "Arapça"),
    LugatEntry("keşfiyât-ı sâdıka", "keşfiyat", "Doğru ve hakiki manevi keşifler, velilerin ilhamları.", "Arapça"),
    LugatEntry("kıymetdâr", "kıymetdar", "Çok değerli, paha biçilmez.", "Farsça"),
    LugatEntry("küllî", "külli", "Geniş, kapsamlı, bütüncül, büyük çaplı.", "Arapça"),
    LugatEntry("lâyemût", "layemut", "Ölümsüz, hiç ölmeyecekmiş gibi zanneden.", "Arapça"),
    LugatEntry("lem'a", "lema", "Parıltı, şule, ışıltı; esere ad olan nurlu hakikat.", "Arapça"),
    LugatEntry("lezzet-i hakikiye", "lezzet", "Gerçek, kalıcı ve manevi tat, zevali olmayan sevinç.", "Arapça"),
    LugatEntry("mâdem", "madem", "Mademki, madem öyle, madem hakikat böyledir.", "Farsça"),
    LugatEntry("mâhir", "mahir", "Usta, hünerli, işinin ehli sanatkâr.", "Arapça"),
    LugatEntry("mâlik", "malik", "Mülkün gerçek sahibi, her şeyin mutlak tasarruf edicisi Allah.", "Arapça"),
    LugatEntry("ma'nevî", "manevi", "Maddi olmayan, ruha, kalbe ve ahirete bakan yön.", "Arapça"),
    LugatEntry("maraz", "maraz", "Hastalık, illet, ruhi veya bedeni rahatsızlık.", "Arapça"),
    LugatEntry("masûm", "masum", "Günahsız, tertemiz, sübyan çocuk.", "Arapça"),
    LugatEntry("mazhar", "mazhar", "Bir şeyin tecellisine, lütfuna veya görünmesine ayna olan.", "Arapça"),
    LugatEntry("medâr", "medar", "Dayanak, sebep, vesile, kaynak.", "Arapça"),
    LugatEntry("menfî ibâdet", "menfi", "Hastalık ve musibetler karşısında sabır ve acz ile yapılan riyasız manevi ibadet.", "Arapça"),
    LugatEntry("merhamet", "merhamet", "Acıma, şefkat gösterme, esirgeme duygusu.", "Arapça"),
    LugatEntry("merhem", "merhem", "Yaraya sürülen iyileştirici ilaç; teselli edici manevi reçete.", "Arapça"),
    LugatEntry("meşakkat", "meşakkat", "Zahmet, sıkıntı, güçlük, eziyet.", "Arapça"),
    LugatEntry("meşrû", "meşru", "Dine, akla ve kanuna uygun, helal olan.", "Arapça"),
    LugatEntry("meyvedâr", "meyvedar", "Meyve veren, neticeli, sevap kazandıran.", "Farsça"),
    LugatEntry("muattal", "muattal", "İşsiz, boşta kalmış, kullanılmayan, işlemez hale gelmiş.", "Arapça"),
    LugatEntry("mugayyebât", "mugayyebat", "Görünmeyenler, geleceğe ait bilinmeyen sırlar.", "Arapça"),
    LugatEntry("muhabbet", "muhabbet", "Sevgi, samimi bağlılık, ilahi aşk.", "Arapça"),
    LugatEntry("muhâlif", "muhalif", "Aykırı, zıt, genel teamüle uymayan.", "Arapça"),
    LugatEntry("muhâfaza", "muhafaza", "Koruma, saklama, gözetme.", "Arapça"),
    LugatEntry("muhterem", "muhterem", "Saygıdeğer, hürmete layık olan.", "Arapça"),
    LugatEntry("mükâfât", "mükafat", "Ödül, hayırlı amelin güzel karşılığı.", "Arapça"),
    LugatEntry("mülk", "mülk", "Mal, mülk, varlık, insanın bedeni ve kâinat.", "Arapça"),
    LugatEntry("mülhid", "mülhid", "Dinsiz, inkârcı, imansız kimse.", "Arapça"),
    LugatEntry("mümkinât", "mümkinat", "Olması mümkün olan yaratılmışlar âlemi.", "Arapça"),
    LugatEntry("münâcât", "münacat", "Allah'a yalvarış, yakarış, samimi dua.", "Arapça"),
    LugatEntry("münasebetdâr", "münasebet", "İlişkili, bağlantılı, alakalı.", "Arapça/Farsça"),
    LugatEntry("mürşid", "mürşid", "Doğru yolu gösteren, irşad eden rehber ve nâsih.", "Arapça"),
    LugatEntry("müsbet ibâdet", "müsbet", "Namaz, oruç gibi kulun kendi iradesiyle yaptığı doğrudan ibadetler.", "Arapça"),
    LugatEntry("musîbet", "musibet", "Afet, bela, hastalık, insanın başına gelen sıkıntı.", "Arapça"),
    LugatEntry("musîbet-zede", "musibet", "Musibete, felakete veya hastalığa uğramış kişi.", "Arapça/Farsça"),
    LugatEntry("müsvedde-i evvel", "müsvedde", "İlk taslak, ilk karalama nüshası.", "Arapça"),
    LugatEntry("müşevveş", "müşevveş", "Karışık, dağınık, düzensiz kalmış.", "Arapça"),
    LugatEntry("nâfi'", "nafi", "Faydalı, yararlı, şifalı olan.", "Arapça"),
    LugatEntry("nakış", "nakış", "İşleme, sanat eseri, Cenab-ı Hakk'ın varlıklardaki sanatlı yaratılışı.", "Arapça"),
    LugatEntry("nâsih", "nasih", "Nasihat eden, öğüt veren, doğruyu gösteren.", "Arapça"),
    LugatEntry("nefs", "nefs", "Kişinin kendisi, bencil duyguları ve dünyevi arzuları.", "Arapça"),
    LugatEntry("nefs-i emmâre", "nefs", "Kötülüğü emreden, gururlu ve günaha meyilli ham nefis.", "Arapça"),
    LugatEntry("nev'", "nevi", "Tür, çeşit, kısım.", "Arapça"),
    LugatEntry("nev'-i beşer", "beşer", "İnsan türü, insanlık, insanoğlu.", "Arapça"),
    LugatEntry("nimet", "nimet", "İyilik, lütuf, sağlanan fayda ve rızık.", "Arapça"),
    LugatEntry("nurânî", "nurani", "Nurlu, aydınlık, ruha huzur veren.", "Arapça"),
    LugatEntry("nüzul", "nüzul", "İnme, felç, uzuvların hareketten kalması hastalığı.", "Arapça"),
    LugatEntry("ömür", "ömür", "Hayat süresi, insanın dünyada geçirdiği sınırlı sermaye.", "Arapça"),
    LugatEntry("rahmet", "rahmet", "Merhamet, acıma, Allah'ın kullarına olan sonsuz şefkat ve lütfu.", "Arapça"),
    LugatEntry("Rahîm", "rahim", "Yarattıklarına şefkat eden, mü'minleri ahirette ödüllendiren Allah.", "Arapça"),
    LugatEntry("rahat", "rahat", "Huzur, dinlenme, sıkıntısız olma hali.", "Arapça"),
    LugatEntry("reçete", "reçete", "Hastalara çare ve ilaç bildiren manevi yol haritası.", "İtalyanca"),
    LugatEntry("Rezzâk", "rezzak", "Bütün canlıların rızkını veren Allah.", "Arapça"),
    LugatEntry("riyâ", "riya", "Gösteriş, ibadeti başkalarına beğendirmek için yapma ikiyüzlülüğü.", "Arapça"),
    LugatEntry("riyâsız", "riyasız", "Samimi, gösterişten uzak, ihlaslı.", "Türkçe/Arapça"),
    LugatEntry("rivâyât-ı sahîha", "rivayet", "Güvenilir, sağlam kaynaklı hadis ve rivayetler.", "Arapça"),
    LugatEntry("rızâ", "rıza", "Razı olma, hoşnutluk, Allah'ın takdirine boyun eğme.", "Arapça"),
    LugatEntry("saâdet", "saadet", "Mutluluk, bahtiyarlık, cennet huzuru.", "Arapça"),
    LugatEntry("sâbir", "sabir", "Sabreden, belalara tahammül gösteren kul.", "Arapça"),
    LugatEntry("sabır", "sabır", "Acıya, hastalığa ve günahlara karşı metanetle direnme.", "Arapça"),
    LugatEntry("safâ", "safa", "Neşe, eğlence, zevk ve kederden uzak olma hali.", "Arapça"),
    LugatEntry("sâir", "sair", "Diğer, başka, geriye kalan.", "Arapça"),
    LugatEntry("san'at", "sanat", "Ustalık, incelik, Cenab-ı Hakk'ın yaratışındaki harika incelik.", "Arapça"),
    LugatEntry("san'atkâr", "sanatkar", "Sanat sahibi, her şeyi harika yaratan Yüce Sâni'.", "Arapça/Farsça"),
    LugatEntry("Sâni'", "sani", "Her şeyi sanatla, mükemmel biçimde yaratan Allah.", "Arapça"),
    LugatEntry("Sâni'-i Zülcelâl", "sani", "Sonsuz celal ve azamet sahibi olan Yüce Sanatkâr Yaratıcı.", "Arapça"),
    LugatEntry("sefâhet", "sefahet", "Dinin yasakladığı zevk ve eğlencelere düşkünlük.", "Arapça"),
    LugatEntry("semâvî", "semavi", "Göğe ait, gökten inen, ilahi kaynaklı.", "Arapça"),
    LugatEntry("sermâye", "sermaye", "İnsanın elindeki ana varlık; ömür ve sıhhat sermayesi.", "Farsça"),
    LugatEntry("sevâb", "sevap", "Hayırlı davranışlara ve sabırlara Allah'ın vadettiği mükafat.", "Arapça"),
    LugatEntry("sıhhat", "sıhhat", "Sağlık, afiyet, bedenin sağlam ve hastalıksız olması.", "Arapça"),
    LugatEntry("sûret", "suret", "Biçim, şekil, görünüş, tarz.", "Arapça"),
    LugatEntry("sür'at", "sürat", "Hız, çabukluk, telaşsız akış.", "Arapça"),
    LugatEntry("Şâfî", "şafi", "Maddi ve manevi her türlü derde hakiki şifayı veren Allah.", "Arapça"),
    LugatEntry("şâkir", "şakir", "Şükreden, nimetin kadrini bilip Allah'a teşekkür eden kul.", "Arapça"),
    LugatEntry("şefkat", "şefkat", "Karşılıksız sevgi, merhamet ve koruma duygusu.", "Arapça"),
    LugatEntry("şekvâ", "şekva", "Şikâyet, sızlanma, halinden şikâyetçi olma.", "Arapça"),
    LugatEntry("şekvâkârâne", "şekva", "Sürekli sızlanarak, şikâyet edercesine.", "Farsça"),
    LugatEntry("şifâ", "şifa", "İyileşme, hastalıktan kurtulma, sağlığa kavuşma.", "Arapça"),
    LugatEntry("şuâ'", "şua", "Işın, nur parıltısı, güneşin veya nurun yaydığı çizgi.", "Arapça"),
    LugatEntry("şükür", "şükür", "Verilen nimeti bilip, nimeti verene kalple, dille ve amelle teşekkür etme.", "Arapça"),
    LugatEntry("tahammül", "tahammül", "Kaldırma, dayanma, gücünün yettiği kadar direnme.", "Arapça"),
    LugatEntry("tahammülsüz", "tahammülsüz", "Acıya ve zorluğa sabredemeyen, tez canlı.", "Türkçe"),
    LugatEntry("tasarruf", "tasarruf", "İdare etme, dilediği gibi kullanma, mülkünde tasarruf etme.", "Arapça"),
    LugatEntry("tashih", "tashih", "Düzeltme, hatayı giderme.", "Arapça"),
    LugatEntry("tashihât", "tashihat", "Düzeltmeler, tashihler.", "Arapça"),
    LugatEntry("te'lif", "telif", "Eser yazma, kaleme alma, kitap meydana getirme.", "Arapça"),
    LugatEntry("tecellî", "tecelli", "Görünme, belirme, Allah'ın isim ve sıfatlarının varlıklarda yansıması.", "Arapça"),
    LugatEntry("tedkîkât", "tedkikat", "Derinlemesine incelemeler, araştırmalar.", "Arapça"),
    LugatEntry("tefekkür", "tefekkür", "Düşünme, Allah'ın kâinattaki harika sanatlarını ibretle akıl etme.", "Arapça"),
    LugatEntry("teşekkî", "teşekki", "Şikâyet etme, sızlanma.", "Arapça"),
    LugatEntry("teşekkür", "teşekkür", "Şükran bildirme, nimete minnettarlık gösterme.", "Arapça"),
    LugatEntry("tesellî", "teselli", "Avunma, acıyı hafifleten manevi destek ve moral.", "Arapça"),
    LugatEntry("tevekkül", "tevekkül", "Sebeplere sarıldıktan sonra neticeyi Allah'a havale edip güvenme.", "Arapça"),
    LugatEntry("tevahhuş", "tevahhuş", "Korku, yalnızlık ve ürperti duyma, yabancılık hissetme.", "Arapça"),
    LugatEntry("ubûdiyet", "ubudiyet", "Kulluk, Allah'a karşı acz ve fakrını hissedip ibadet etme şuuru.", "Arapça"),
    LugatEntry("uhrevî", "uhrevi", "Ahirete ait, ebedi hayata bakan.", "Arapça"),
    LugatEntry("ücret", "ücret", "Karşılık, mükâfat, amelin bedeli.", "Arapça"),
    LugatEntry("ümitvâr", "ümitvar", "Ümitli, geleceğe ve rahmete güvenen.", "Farsça"),
    LugatEntry("vehim", "vehim", "Asılsız kuruntu, gerçekte olmayanı var sanma, vesvese.", "Arapça"),
    LugatEntry("vird", "vird", "Belirli zamanlarda düzenli okunan zikir ve dua.", "Arapça"),
    LugatEntry("vücûd", "vücud", "Beden; aynı zamanda varlık, var olma durumu.", "Arapça"),
    LugatEntry("zâhirî", "zahiri", "Görünüşteki, dış yüzdeki, kabuk mesabesindeki.", "Arapça"),
    LugatEntry("zeval", "zeval", "Sona erme, kaybolup gitme, batma, bitiş.", "Arapça"),
    LugatEntry("zîhayat", "zihayat", "Hayat sahibi, canlı varlık.", "Arapça/Farsça"),
    LugatEntry("ziyâde", "ziyade", "Daha fazla, çok, aşkın.", "Arapça"),
    LugatEntry("ziyâ", "ziya", "Işık, aydınlık, nur.", "Arapça"),
    LugatEntry("zâyi'", "zayi", "Boşa giden, kaybolan, elden çıkan.", "Arapça"),
    LugatEntry("Zülcelâl", "zülcelal", "Sonsuz büyüklük, celal ve haşmet sahibi olan Yüce Allah.", "Arapça")
)

/**
 * Verilen kelimeyi veya çekim ekli halini lügatçede arar.
 */
fun findLugatEntry(query: String): LugatEntry? {
    val clean = query.trim().trim('“', '”', '"', '’', '\'', '.', ',', '!', '?', ';', ':', '(', ')', '[', ']', '—', '–', '-', ' ')
    if (clean.length < 2) return null
    val lower = clean.lowercase()

    // 1. Doğrudan tam eşleşme (orijinal kelime veya kök)
    hastalarLugat.firstOrNull { 
        it.word.equals(clean, ignoreCase = true) || it.root.equals(lower, ignoreCase = true) 
    }?.let { return it }

    // 2. Özel şapkalı karakterleri normalleştirerek eşleşme
    fun normalize(s: String): String {
        return s.lowercase()
            .replace("â", "a")
            .replace("î", "i")
            .replace("û", "u")
            .replace("'", "")
            .replace("’", "")
            .replace("-", "")
            .replace("‑", "")
    }

    val normQuery = normalize(clean)
    hastalarLugat.firstOrNull { 
        normalize(it.word) == normQuery || normalize(it.root) == normQuery 
    }?.let { return it }

    // 3. Çekim eki almış kelimeleri arama (ön ek veya kök başlangıcı)
    // Örnek: "hastalığın", "şekvâcı", "sabırsız", "bîçâreler"
    if (normQuery.length >= 4) {
        val candidates = hastalarLugat.filter {
            val normWord = normalize(it.word)
            val normRoot = normalize(it.root)
            normQuery.startsWith(normWord) || normQuery.startsWith(normRoot) ||
            normWord.startsWith(normQuery.take(5))
        }
        if (candidates.isNotEmpty()) {
            // En uzun kök eşleşmesini seç
            return candidates.maxByOrNull { normalize(it.word).length }
        }
    }

    return null
}

