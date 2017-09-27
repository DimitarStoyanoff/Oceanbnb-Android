package stoyanoff.oceanbnb_android.profile;


import stoyanoff.oceanbnb_android.data.models.User;
import stoyanoff.oceanbnb_android.util.BasePresenter;
import stoyanoff.oceanbnb_android.util.BaseView;

/**
 * Created by L on 24/09/2017.
 */

public class ProfileContract {

    interface View extends BaseView<Presenter> {
        void showProfileInfo(User user);
        boolean isActive();
        void showErrorMessage(String message);
        void showLoginScreen();
    }

    interface Presenter extends BasePresenter {
        void logout();
        void loadProfile();
    }
}
