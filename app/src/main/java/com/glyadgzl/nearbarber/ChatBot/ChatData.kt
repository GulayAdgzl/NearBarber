
import android.graphics.Bitmap
import androidx.core.os.BuildCompat
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object ChatData {
    val api_key = "AIzaSyCqLrT1zEbfh1uJC8IjT3kJh455U3dExkc"


    suspend fun getResponse(prompt: String): Chat {


        // Model adını doğru şekilde kullanın
        val generativeModel =GenerativeModel(
            // The Gemini 1.5 models are versatile and work with most use cases
            modelName = "gemini-1.5-flash",
            // Access your API key as a Build Configuration variable (see "Set up your API key" above)
            apiKey = api_key
        )

        try {
            // İsteği oluşturun
            val inputContent = content {
                text(prompt)
            }

            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(inputContent)
            }

            return Chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )

        } catch (e: Exception) {
            // Daha spesifik hata mesajı için hata ayrıntılarını yazdırın
            e.printStackTrace()
            return Chat(
                prompt = "Hata: ${e.message}",
                bitmap = null,
                isFromUser = false
            )
        }
    }

    suspend fun getResponseWithImage(prompt: String, bitmap: Bitmap): Chat {
        // Resim işleme için doğru modeli kullanın
        val generativeModel = GenerativeModel(
            // Resim işleme için uygun model adını kullanın
            modelName = "gemini-1.5-flash", // "gemini-pro-vision" veya "gemini-1.5-pro" da olabilir
            apiKey = api_key
        )

        try {
            // Hem resim hem de metin içeren bir içerik oluşturun
            val inputContent = content {
                image(bitmap)
                text(prompt)
            }

            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(inputContent)
            }

            return Chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )

        } catch (e: Exception) {
            // Daha spesifik hata mesajı için hata ayrıntılarını yazdırın
            e.printStackTrace()
            return Chat(
                prompt = "Hata: ${e.message}",
                bitmap = null,
                isFromUser = false
            )
        }
    }
}