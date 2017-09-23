package stoyanoff.oceanbnb_android.data.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import stoyanoff.oceanbnb_android.data.models.AccessTokenResponse;
import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.data.models.Ship;
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

    @POST("token")
    Call<AccessTokenResponse> getAccessTokenEmailLogin(@Body String body);

    @GET("Account/cruises")
    Call<List<UserCruise>> getUserCruises(@Header("Authorization") String authorization);

    @GET("Cruises")
    Call<List<Cruise>> getAllCruises(@Header("Authorization") String authorization);

    @GET("Cruises/{cruiseId}/details")
    Call<Cruise> getCruiseById(@Header("Authorization") String authorization,
                               @Path("cruiseId") int cruiseId);

    @FormUrlEncoded
    @POST("Cruises/AddUser")
    Call<Void> addUserToCruise(@Header("Authorization") String authorization,
                               @Field("UserId") int userId, @Field("CruiseId") int cruiseId);

    @FormUrlEncoded
    @POST("Cruises/RemoveUser")
    Call<Void> removeUserFromCruise(@Header("Authorization") String authorization,
                               @Field("UserId") int userId, @Field("CruiseId") int cruiseId);

    @GET("Ships")
    Call<List<Ship>> getAllShips(@Header("Authorization") String authorization);

    @GET("Ships/{shipId}")
    Call<Ship> getShipById(@Header("Authorization") String authorization,
                           @Path("shipId") int shipId);

}
