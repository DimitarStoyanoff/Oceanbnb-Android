package stoyanoff.oceanbnb_android.login;

import stoyanoff.oceanbnb_android.util.BasePresenter;
import stoyanoff.oceanbnb_android.util.BaseView;

/**
 * Created by L on 23/09/2017.
 */

public interface LoginContract {

     interface View extends BaseView<Presenter> {

         void showRegisterError(String message);

         void showLoginError(String message);

         void toggleLoading(boolean isOn);

         void successfulRegister();

         void successfulLogin();

         boolean isActive();

    }

     interface Presenter extends BasePresenter{

        void performLogin(String email, String password);

        void performRegister(String email, String password);
    }
}
