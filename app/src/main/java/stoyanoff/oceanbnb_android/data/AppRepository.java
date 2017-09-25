package stoyanoff.oceanbnb_android.data;

import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import stoyanoff.oceanbnb_android.data.models.AccessTokenResponse;
import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.data.models.CruiseUser;
import stoyanoff.oceanbnb_android.data.models.Ship;
import stoyanoff.oceanbnb_android.data.models.User;
import stoyanoff.oceanbnb_android.data.models.UserCruise;
import stoyanoff.oceanbnb_android.data.remote.RetrofitServices;
import stoyanoff.oceanbnb_android.util.Constants;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by L on 23/09/2017.
 */

public class AppRepository implements AppDataSource {

    private static AppRepository INSTANCE = null;
    private final RetrofitServices mRetrofitServices;
    private final SharedPreferences mSharedPreferences;

    private AppRepository(@NotNull RetrofitServices retrofitServices,
                          @NotNull SharedPreferences sharedPreferences) {
        mRetrofitServices = checkNotNull(retrofitServices);
        mSharedPreferences = checkNotNull(sharedPreferences);
    }

    public static AppRepository getInstance(@NotNull RetrofitServices retrofitServices,
                                            @NotNull SharedPreferences sharedPreferences){
        if(INSTANCE == null){
            INSTANCE = new AppRepository(retrofitServices,sharedPreferences);
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }


    @Override
    public void registerUser(@NotNull String email, @NotNull String password,
                             @NotNull final CodeCallback codeCallback) {
        mRetrofitServices.registerUser(email, password, new CodeCallback() {
            @Override
            public void getResponseCode(int responseCode) {
                codeCallback.getResponseCode(responseCode);
            }

            @Override
            public void onDataNotAvailable() {
                codeCallback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void emailLogin(@NotNull String email, @NotNull String password,
                           @NotNull final OnLoginListener onLoginListener) {
        mRetrofitServices.getAccessTokenFromEmail(email, password, new OnLoginListener() {
            @Override
            public void loginResponse(AccessTokenResponse accessTokenResponse) {
                String token = "Bearer " + accessTokenResponse.getToken();
                mSharedPreferences.edit().putString(Constants.USER_ACCESS_TOKEN,token).commit();
                mSharedPreferences.edit().putString(Constants.USER_ACCESS_TOKEN,accessTokenResponse.getUserName()).commit();
                onLoginListener.loginResponse(accessTokenResponse);
            }

            @Override
            public void onDataNotAvailable() {
                onLoginListener.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getProfileInfo(@NotNull final UserInfoCallback userInfoCallback) {
        mRetrofitServices.getProfileInfo(mSharedPreferences.getString(Constants.USER_ACCESS_TOKEN, null),
                new UserInfoCallback() {
                    @Override
                    public void getUserInfo(User userInfo) {
                        userInfoCallback.getUserInfo(userInfo);
                        mSharedPreferences.edit().putString(Constants.USER_ID_PREF,Integer.toString(userInfo.getUserId())).commit();
                    }

                    @Override
                    public void onDataNotAvailable() {
                        userInfoCallback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void getUserCruises(@NotNull final UserCruisesCallback userCruisesCallback) {
        mRetrofitServices.getUserCruises(mSharedPreferences.getString(Constants.USER_ACCESS_TOKEN, null),
                new UserCruisesCallback() {
                    @Override
                    public void getUserCruises(List<UserCruise> userCruises) {
                        userCruisesCallback.getUserCruises(userCruises);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        userCruisesCallback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void getCruiseUsers(int cruiseId, @NotNull final CruiseUsersCallback cruiseUsersCallback) {
        mRetrofitServices.getCruiseUsers(mSharedPreferences.getString(Constants.USER_ACCESS_TOKEN, null),
                cruiseId, new CruiseUsersCallback() {
                    @Override
                    public void onUsersLoaded(List<CruiseUser> cruiseUsers) {
                        cruiseUsersCallback.onUsersLoaded(cruiseUsers);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        cruiseUsersCallback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void getAllCruises(@NotNull final AllCruisesCallback allCruisesCallback) {
        mRetrofitServices.getAllCruises(mSharedPreferences.getString(Constants.USER_ACCESS_TOKEN, null),
                new AllCruisesCallback() {
                    @Override
                    public void getCruises(List<Cruise> cruises) {
                        allCruisesCallback.getCruises(cruises);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        allCruisesCallback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void getCruiseById(int cruiseId,
                              @NotNull final CruiseDetailsCallback cruiseDetailsCallback) {
        mRetrofitServices.getCruiseById(mSharedPreferences.getString(Constants.USER_ACCESS_TOKEN, null),
               checkNotNull(cruiseId), new CruiseDetailsCallback() {
                    @Override
                    public void onCruiseLoaded(Cruise cruise) {
                        cruiseDetailsCallback.onCruiseLoaded(cruise);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        cruiseDetailsCallback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void addUserToCruise(int cruiseId,
                                @NotNull final CodeCallback codeCallback) {
        mRetrofitServices.addUserToCruise(mSharedPreferences.getString(Constants.USER_ACCESS_TOKEN, null),
                mSharedPreferences.getString(Constants.USER_ID_PREF,null), checkNotNull(cruiseId), new CodeCallback() {
                    @Override
                    public void getResponseCode(int responseCode) {
                        codeCallback.getResponseCode(responseCode);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        codeCallback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void removeUserFromCruise(int cruiseId,
                                     @NotNull final CodeCallback codeCallback) {
        mRetrofitServices.removeUserFromCruise(mSharedPreferences.getString(Constants.USER_ACCESS_TOKEN, null),
                mSharedPreferences.getString(Constants.USER_ID_PREF,null), checkNotNull(cruiseId), new CodeCallback() {
                    @Override
                    public void getResponseCode(int responseCode) {
                        codeCallback.getResponseCode(responseCode);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        codeCallback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void getShipById(int shipId, @NotNull final ShipDetailsCallback shipDetailsCallback) {
        mRetrofitServices.getShipById(mSharedPreferences.getString(Constants.USER_ACCESS_TOKEN, null),
                checkNotNull(shipId), new ShipDetailsCallback() {
                    @Override
                    public void onShipLoaded(Ship ship) {
                        shipDetailsCallback.onShipLoaded(ship);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        shipDetailsCallback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void getAllShips(@NotNull final AllShipsCallback allShipsCallback) {
        mRetrofitServices.getAllShips(mSharedPreferences.getString(Constants.USER_ACCESS_TOKEN, null),
                new AllShipsCallback() {
                    @Override
                    public void getShips(List<Ship> ships) {
                        allShipsCallback.getShips(ships);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        allShipsCallback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public boolean isUserLoggedIn() {

        return mSharedPreferences.getString(Constants.USER_ACCESS_TOKEN, null) != null;
    }

    @Override
    public void removeUserFromPreferences() {
        mSharedPreferences.edit().remove(Constants.USER_ACCESS_TOKEN).commit();
        mSharedPreferences.edit().remove(Constants.USER_ID_PREF).commit();
        mSharedPreferences.edit().remove(Constants.USER_NAME_PREF).commit();
    }
}
