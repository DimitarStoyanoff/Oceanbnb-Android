package stoyanoff.oceanbnb_android.profile;

import org.jetbrains.annotations.NotNull;

import stoyanoff.oceanbnb_android.data.AppRepository;

/**
 * Created by L on 24/09/2017.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private final AppRepository appRepository;
    private final ProfileContract.View profileView;

    public ProfilePresenter(@NotNull AppRepository appRepository,
                            @NotNull ProfileContract.View profileView) {
        this.appRepository = appRepository;
        this.profileView = profileView;
        profileView.setPresenter(this);
        start();
    }

    public void start() {

    }
}
