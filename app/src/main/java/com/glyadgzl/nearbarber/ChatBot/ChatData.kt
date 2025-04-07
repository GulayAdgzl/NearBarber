
import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object ChatData {

    val api_key = "AIzaSyB5OaRdChX4tGUuorbqY5bR8hDzpa6jzP8"

    suspend fun getResponse(prompt: String): Chat {
        // Model adını doğru şekilde kullanın
        val generativeModel = GenerativeModel(
            // API versiyonu belirtmek için farklı bir constructor kullanabilirsiniz
            // veya en son sürümü kullanmak için basit constructor'ı kullanın
            modelName = "gemini-pro",
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
            modelName = "gemini-1.5-pro-vision", // "gemini-pro-vision" veya "gemini-1.5-pro" da olabilir
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