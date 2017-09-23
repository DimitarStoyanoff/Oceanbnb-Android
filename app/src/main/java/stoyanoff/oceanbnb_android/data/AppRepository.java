package stoyanoff.oceanbnb_android.data;

import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import stoyanoff.oceanbnb_android.data.remote.RetrofitServices;

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
    public void registerUser(@NotNull String name, @NotNull String email, @NotNull String password,
                             @NotNull CodeCallback codeCallback) {

    }

    @Override
    public void emailLogin(@NotNull String email, @NotNull String password,
                           @NotNull OnLoginListener onLoginListener) {

    }
}
