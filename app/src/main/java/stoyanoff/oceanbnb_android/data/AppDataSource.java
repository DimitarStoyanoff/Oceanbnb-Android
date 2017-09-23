package stoyanoff.oceanbnb_android.data;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import stoyanoff.oceanbnb_android.data.models.AccessTokenResponse;
import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.data.models.Ship;
import stoyanoff.oceanbnb_android.data.models.User;
import stoyanoff.oceanbnb_android.data.models.UserCruise;

/**
 * Created by L on 23/09/2017.
 */

public interface AppDataSource {

    interface UserInfoCallback{
        void getUserInfo(User userInfo);
        void onDataNotAvailable();
    }

    interface OnLoginListener{
        void loginResponse(AccessTokenResponse accessTokenResponse);
        void onDataNotAvailable();
    }

    interface CodeCallback{
        void getResponseCode(int responseCode);
        void onDataNotAvailable();
    }

    interface UserCruisesCallback{
        void getUserCruises(List<UserCruise> userCruises);
        void onDataNotAvailable();
    }

    interface AllCruisesCallback{
        void getCruises(List<Cruise> cruises);
        void onDataNotAvailable();
    }

    interface CruiseDetailsCallback{
        void onCruiseLoaded(Cruise cruise);
        void onDataNotAvailable();
    }

    interface AllShipsCallback{
        void getShips(List<Ship> ships);
        void onDataNotAvailable();
    }

    interface ShipDetailsCallback{
        void onShipLoaded(Ship ship);
        void onDataNotAvailable();
    }

    void registerUser(@NotNull String email, @NotNull String password,
                      @NotNull final CodeCallback codeCallback);

    void emailLogin(@NotNull String email, @NotNull String password,
                    @NotNull final OnLoginListener onLoginListener);

     void getProfileInfo(@NotNull AppDataSource.UserInfoCallback userInfoCallback);

     void getUserCruises(@NotNull AppDataSource.UserCruisesCallback userCruisesCallback);

    void getAllCruises(@NotNull AppDataSource.AllCruisesCallback allCruisesCallback);

    void getCruiseById(@NotNull int cruiseId,
                       @NotNull AppDataSource.CruiseDetailsCallback cruiseDetailsCallback);

    void addUserToCruise(@NotNull int userId, @NotNull int cruiseId,
                         @NotNull AppDataSource.CodeCallback codeCallback);

    void removeUserFromCruise(@NotNull int userId, @NotNull int cruiseId,
                              @NotNull AppDataSource.CodeCallback codeCallback);

    void getShipById(@NotNull int shipId,
                     @NotNull AppDataSource.ShipDetailsCallback shipDetailsCallback);

    void getAllShips(@NotNull AppDataSource.AllShipsCallback allShipsCallback);
}
