# NearBarber Jetpack Compose ile Sesli Asistan Uygulaması


Bu proje, **Jetpack Compose** kullanılarak geliştirilen bir Android uygulamasıdır. Kullanıcıların sesli komutlarla etkileşim kurabileceği, kategori ve mağaza verilerini Firebase üzerinden alabileceği bir sistem sunar. Ayrıca görüntü ve metin tabanlı sohbet işlevi de bulunmaktadır.

---

## Özellikler

- 🔊 **Sesli Komut Tanıma** (SpeechRecognizer API)
- 💬 **ChatBot** Desteği (Metin ve Görsel Girdili)
- 🧠 MVVM Mimarisi
- ☁️ **Firebase Realtime Database** Entegrasyonu
- 📍 Google Maps Entegrasyonu (Yakındaki kuaförleri harita üzerinde gösterme)
- 🧭 **Navigation Component** ile Sayfa Geçişleri

---

## Kullanılan Teknolojiler

- **Kotlin**
- **Jetpack Compose**
- **StateFlow & ViewModel** (Android Architecture Components)
- **Firebase Realtime Database**
- **SpeechRecognizer API**
- **Google Maps API**


## Google Harita Aktivitesi
Uygulama, Google Maps API kullanarak, yakındaki kuaförlerin harita üzerinde gösterilmesini sağlar.
---

## ChatBot Teknolojisi

Bu projede sohbet cevaplarını oluşturmak için **Google Gemini AI** teknolojisi kullanılmıştır. Kullanıcının metin veya görsel girdilerine göre doğal dilde yanıtlar üretir.

---

## Proje Dosya Yapısı (Örnek)

```
├── ChatViewModel.kt
├── DashboardViewModel.kt
├── DashboardRepository.kt
├── ResultsRepository.kt
├── MainActivity.kt
├── DashboardScreen.kt
├── SpeechRecognizerHelper.kt
├── VoiceInputWithSpeechRecognizer.kt
├── VoiceInputButton.kt
```

---

## ChatViewModel.kt
Kullanıcıdan gelen prompt'ları işler, metin veya görsel girdilere göre `ChatData` üzerinden yanıtlar alır.

```kotlin
class ChatViewModel : ViewModel() {
    ... // State yönetimi, metin/görsel prompt gönderme, yanıt alma
}
```

---

## DashboardViewModel.kt
Firebase'den kategori ve banner verilerini çeken ViewModel.

```kotlin
class DashboardViewModel {
    private val repository = DashboardRepository()

    fun loadCategory(): LiveData<MutableList<CategoryModel>> = repository.loadCategory()

    fun loadBanner(): LiveData<MutableList<BannerModel>> = repository.loadBanner()
}
```

---

## DashboardRepository.kt
Firebase bağlantılarını kurar ve verileri dinler.

```kotlin
class DashboardRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadCategory(): LiveData<MutableList<CategoryModel>> { ... }

    fun loadBanner(): LiveData<MutableList<BannerModel>> { ... }
}
```

---

## ResultsRepository.kt
Alt kategori, popüler ve en yakın mağazaları Firebase üzerinden çeker.

```kotlin
class ResultsRepository {
    fun loadSubCategory(id: String): LiveData<MutableList<CategoryModel>> { ... }
    fun loadPopular(id: String): LiveData<MutableList<StoreModel>> { ... }
    fun loadNearest(id: String): LiveData<MutableList<StoreModel>> { ... }
}
```

---

## MainActivity.kt & DashboardScreen.kt
Navigasyon ve ekran yapısının temelini oluşturur.

```kotlin
class MainActivity : ComponentActivity() {
    private val uriState = MutableStateFlow("")
    override fun onCreate(savedInstanceState: Bundle?) {
        setContent {
            val navController = rememberNavController()
            DashboardNavGraph(navController = navController, uriState = uriState)
        }
    }
}
```

```kotlin
@Composable
fun DashboardScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = colorResource(id = R.color.grey))

    val viewModel = DashboardRepository()
    val categories = remember { mutableListOf<CategoryModel>() }
    val banners = remember { mutableListOf<BannerModel>() }
    var showCategoryLoading by remember { mutableStateOf(true) }
    var showBannerLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadBanner().observeForever {
            banners.clear(); banners.addAll(it); showBannerLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadCategory().observeForever {
            categories.clear(); categories.addAll(it); showCategoryLoading = false
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("chat_screen") },
                containerColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.btn_2),
                    contentDescription = "Chat",
                    tint = Color.Black
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.grey))
                .padding(paddingValues = innerPadding)
        ) {
            item { TopBar() }
            item { Banner(banners, showBannerLoading) }
            item { CategorySection(categories, showCategoryLoading) }
        }
    }
}
```

---

## Sesli Komutlar

### SpeechRecognizerHelper.kt

```kotlin
class SpeechRecognizerHelper(
    private val context: Context,
    private val onResult: (String) -> Unit
) {
    ... // Konuşma tanıma işlemleri ve sonuçları döndürme
}
```

### VoiceInputWithSpeechRecognizer.kt

```kotlin
@Composable
fun VoiceInputWithSpeechRecognizer(
    onVoiceResult: (String) -> Unit
) {
    ... // Ses tanıma başlatıcı ikon ve izin kontrolü
}
```

### VoiceInputButton.kt

```kotlin
@Composable
fun VoiceInputButton(
    onStartVoiceInput: () -> Unit
) {
    ... // Mikrofon izin kontrolü ve tetikleyici buton
}
```

---

## Notlar

- Bu proje  **Kotlin + Jetpack Compose** kullanılarak geliştirilmiştir.
- Firebase bağlantıları gerçek zamanlı veri dinleme üzerine kuruludur.
- Sesli asistan özelliği, `SpeechRecognizer` API'si ile çalışır.

---

## Ekran Görüntüleri

> Ekran görüntüleri veya demo videosu README'ye eklenebilir.

---

Yeni modüller veya katkılar için PR gönderebil