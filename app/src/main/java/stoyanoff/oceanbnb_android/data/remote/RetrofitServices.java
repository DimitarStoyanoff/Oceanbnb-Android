package stoyanoff.oceanbnb_android.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import stoyanoff.oceanbnb_android.data.AppDataSource;
import stoyanoff.oceanbnb_android.data.models.AccessTokenResponse;
import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.data.models.CruiseUser;
import stoyanoff.oceanbnb_android.data.models.Ship;
import stoyanoff.oceanbnb_android.data.models.User;
import stoyanoff.oceanbnb_android.data.models.UserCruise;

/**
 * Created by L on 23/09/2017.
 */

public class RetrofitServices {

    private final String API_URL = "http://oceanbnb.azurewebsites.net/api/";
    private RetrofitCall service;


    public RetrofitServices(){

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
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

            String bodyString = "grant_type=password&username=" +
                    username +
                    "&password=" +
                    password;
            Call<AccessTokenResponse> call = service.getAccessTokenEmailLogin(bodyString);
            call.enqueue(new Callback<AccessTokenResponse>() {
                @Override
                public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
                    if(response.code() == 200){
                        if(response.body() != null){
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

        public void getCruiseUsers(String authorization, int cruiseId,
                                   final AppDataSource.CruiseUsersCallback cruiseUsersCallback){
            Call<List<CruiseUser>> call = service.getCruiseUsers(authorization,cruiseId);
            call.enqueue(new Callback<List<CruiseUser>>() {
                @Override
                public void onResponse(Call<List<CruiseUser>> call, Response<List<CruiseUser>> response) {
                    if(response.code() == 200 && response.body() != null){
                        cruiseUsersCallback.onUsersLoaded(response.body(),false);
                    }else {
                        cruiseUsersCallback.onDataNotAvailable();
                    }
                }

                @Override
                public void onFailure(Call<List<CruiseUser>> call, Throwable t) {
                    cruiseUsersCallback.onDataNotAvailable();
                }
            });
        }

        public void getAllCruises(String authorization,
                                  final AppDataSource.AllCruisesCallback allCruisesCallback){
            Call<List<Cruise>> call = service.getAllCruises(authorization);
            call.enqueue(new Callback<List<Cruise>>() {
                @Override
                public void onResponse(Call<List<Cruise>> call, Response<List<Cruise>> response) {
                    if(response.code() == 200 && response.body() != null){
                        allCruisesCallback.getCruises(response.body());
                    }else {
                        allCruisesCallback.onDataNotAvailable();
                    }
                }

                @Override
                public void onFailure(Call<List<Cruise>> call, Throwable t) {
                    allCruisesCallback.onDataNotAvailable();
                }
            });
        }

        public void getCruiseById(String authorization, int cruiseId,
                                  final AppDataSource.CruiseDetailsCallback cruiseDetailsCallback){
            Call<Cruise> call = service.getCruiseById(authorization,cruiseId);
            call.enqueue(new Callback<Cruise>() {
                @Override
                public void onResponse(Call<Cruise> call, Response<Cruise> response) {
                    if(response.code() == 200 && response.body() != null){
                        cruiseDetailsCallback.onCruiseLoaded(response.body());
                    }else {
                        cruiseDetailsCallback.onDataNotAvailable();
                    }
                }

                @Override
                public void onFailure(Call<Cruise> call, Throwable t) {
                    cruiseDetailsCallback.onDataNotAvailable();
                }
            });
        }

        public void addUserToCruise(String authorization, String userId, int cruiseId,
                                    final AppDataSource.CodeCallback codeCallback){
            Call<Void> call = service.addUserToCruise(authorization,userId,cruiseId);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response != null){
                        codeCallback.getResponseCode(response.code());
                    }else codeCallback.onDataNotAvailable();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    codeCallback.onDataNotAvailable();
                }
            });
        }

        public void removeUserFromCruise(String authorization, String userId, int cruiseId,
                                    final AppDataSource.CodeCallback codeCallback){
            Call<Void> call = service.removeUserFromCruise(authorization,userId,cruiseId);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response != null){
                        codeCallback.getResponseCode(response.code());
                    }else codeCallback.onDataNotAvailable();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    codeCallback.onDataNotAvailable();
                }
            });
        }

        public void getShipById(String authorization, int shipId,
                                final AppDataSource.ShipDetailsCallback shipDetailsCallback){
            Call<Ship> call = service.getShipById(authorization,shipId);
            call.enqueue(new Callback<Ship>() {
                @Override
                public void onResponse(Call<Ship> call, Response<Ship> response) {
                    if(response.code() == 200 && response.body() != null){
                        shipDetailsCallback.onShipLoaded(response.body());
                    }else {
                        shipDetailsCallback.onDataNotAvailable();
                    }
                }

                @Override
                public void onFailure(Call<Ship> call, Throwable t) {
                    shipDetailsCallback.onDataNotAvailable();
                }
            });
        }

        public void getAllShips(String authorization,
                                final AppDataSource.AllShipsCallback allShipsCallback){
            Call<List<Ship>> call = service.getAllShips(authorization);
            call.enqueue(new Callback<List<Ship>>() {
                @Override
                public void onResponse(Call<List<Ship>> call, Response<List<Ship>> response) {
                    if(response.code() == 200 && response.body() != null){
                        allShipsCallback.getShips(response.body());
                    }else {
                        allShipsCallback.onDataNotAvailable();
                    }
                }

                @Override
                public void onFailure(Call<List<Ship>> call, Throwable t) {
                    allShipsCallback.onDataNotAvailable();
                }
            });
        }

    }

