package stoyanoff.oceanbnb_android.data;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import stoyanoff.oceanbnb_android.data.models.AccessTokenResponse;
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

    void registerUser(@NotNull String name, @NotNull String email, @NotNull String password,
                      @NotNull final CodeCallback codeCallback);

    void emailLogin(@NotNull String email, @NotNull String password,
                    @NotNull final OnLoginListener onLoginListener);
}
