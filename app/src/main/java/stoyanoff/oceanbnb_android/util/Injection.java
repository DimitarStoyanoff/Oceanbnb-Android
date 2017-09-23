package stoyanoff.oceanbnb_android.util;

import android.content.Context;
import android.preference.PreferenceManager;

import org.jetbrains.annotations.NotNull;

import stoyanoff.oceanbnb_android.data.AppRepository;
import stoyanoff.oceanbnb_android.data.remote.RetrofitServices;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by L on 23/09/2017.
 */

public class Injection {

    public static AppRepository provideAppRepository(@NotNull Context context){
        checkNotNull(context);
        return AppRepository.getInstance(new RetrofitServices(),
                PreferenceManager.getDefaultSharedPreferences(context));
    }
}
