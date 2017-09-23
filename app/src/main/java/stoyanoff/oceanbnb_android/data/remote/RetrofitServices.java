package stoyanoff.oceanbnb_android.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import stoyanoff.oceanbnb_android.data.AppDataSource;
import stoyanoff.oceanbnb_android.data.models.AccessTokenResponse;
import stoyanoff.oceanbnb_android.data.models.User;
import stoyanoff.oceanbnb_android.data.models.UserCruise;

/**
 * Created by L on 23/09/2017.
 */

public class RetrofitServices {

    private final String API_URL = "http://localhost:14139/api";
    private RetrofitCall service;


    public RetrofitServices(){

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(RetrofitCall.class);
    }


        public void registerUser(String username, String password,
                                 final AppDataSource.CodeCallback codeCallback){
            Call<Void> call = service.emailRegisterUser(username,password,password);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response != null){
                        codeCallback.getResponseCode(response.code());
                    }else {
                        codeCallback.onDataNotAvailable();
                    }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    codeCallback.onDataNotAvailable();
                }
            });

        }

        public void getAccessTokenFromEmail(String username, String password,
        final AppDataSource.OnLoginListener onLoginListener){

            String bodyString = "Username=" +
                    username +
                    "&Password=" +
                    password +
                    "&grant_type=password";
            Call<AccessTokenResponse> call = service.getAccessTokenEmailLogin(bodyString);
            call.enqueue(new Callback<AccessTokenResponse>() {
                @Override
                public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
                    if(response.code() == 200){
                        if(response.body() == null){
                            onLoginListener.loginResponse(response.body());
                        }else{
                            onLoginListener.onDataNotAvailable();
                        }
                    }else{
                        onLoginListener.onDataNotAvailable();
                    }
                }

                @Override
                public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                    onLoginListener.onDataNotAvailable();
                }
            });
        }

        public void getProfileInfo(String authorization,
                                   final AppDataSource.UserInfoCallback userInfoCallback){
            Call<User> call = service.getUserInfo(authorization);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.code() == 200 && response.body() != null){
                        userInfoCallback.getUserInfo(response.body());
                    }else {
                        userInfoCallback.onDataNotAvailable();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    userInfoCallback.onDataNotAvailable();
                }
            });
        }

        public void getUserCruises(String authorization,
                                   final AppDataSource.UserCruisesCallback userCruisesCallback){

            Call<List<UserCruise>> call = service.getUserCruises(authorization);
            call.enqueue(new Callback<List<UserCruise>>() {
                @Override
                public void onResponse(Call<List<UserCruise>> call, Response<List<UserCruise>> response) {
                    if(response.code() == 200 && response.body() != null){
                        userCruisesCallback.getUserCruises(response.body());
                    }else {
                        userCruisesCallback.onDataNotAvailable();
                    }
                }

                @Override
                public void onFailure(Call<List<UserCruise>> call, Throwable t) {
                    userCruisesCallback.onDataNotAvailable();
                }
            });
        }
    }

