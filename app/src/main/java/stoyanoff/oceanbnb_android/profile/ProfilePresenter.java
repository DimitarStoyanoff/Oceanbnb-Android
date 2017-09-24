package stoyanoff.oceanbnb_android.profile;

import org.jetbrains.annotations.NotNull;

import stoyanoff.oceanbnb_android.data.AppDataSource;
import stoyanoff.oceanbnb_android.data.AppRepository;
import stoyanoff.oceanbnb_android.data.models.User;

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
        loadProfile();
    }

    @Override
    public void logout() {
        appRepository.removeUserFromPreferences();
        profileView.showLoginScreen();
    }

    @Override
    public void loadProfile() {
        appRepository.getProfileInfo(new AppDataSource.UserInfoCallback() {
            @Override
            public void getUserInfo(User userInfo) {
                if(profileView.isActive()){
                    profileView.showProfileInfo(userInfo);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if(profileView.isActive()){
                    profileView.showErrorMessage("Data unavailable");
                }
            }
        });
    }
}
