import com.example.a121140014_pam_uts.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<UserResponse>
}
