package stoyanoff.oceanbnb_android.data.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import stoyanoff.oceanbnb_android.data.models.AccessTokenResponse;
import stoyanoff.oceanbnb_android.data.models.User;
import stoyanoff.oceanbnb_android.data.models.UserCruise;

/**
 * Created by L on 23/09/2017.
 */

public interface RetrofitCall {

    @GET("Account/UserInfo")
    Call<User> getUserInfo(@Header("Authorization") String authorization);

    @FormUrlEncoded
    @POST("Account/Register")
    Call<Void> emailRegisterUser(@Field("Email") String email, @Field("Password") String password,
                                 @Field("ConfirmPassword") String confirmPassword);

    @POST("api/token")
    Call<AccessTokenResponse> getAccessTokenEmailLogin(@Body String body);

    @GET("Account/cruises")
    Call<List<UserCruise>> getUserCruises(@Header("Authorization") String authorization);



}
