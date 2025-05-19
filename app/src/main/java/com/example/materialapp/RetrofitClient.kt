import com.example.materialapp.data.remote.MaterialApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://your-api-url.com/api/"

    val materialService: MaterialApiService by lazy { // instance and in lazy mode
        Retrofit.Builder()
            .baseUrl(BASE_URL) // define the base url
            .addConverterFactory(GsonConverterFactory.create()) // convert JSON into Kotlin object
            .build()
            .create(MaterialApiService::class.java) // implement all define function in the sevice
    }
}
