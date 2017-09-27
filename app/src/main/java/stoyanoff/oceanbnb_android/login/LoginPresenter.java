package stoyanoff.oceanbnb_android.login;

import org.jetbrains.annotations.NotNull;

import stoyanoff.oceanbnb_android.data.AppDataSource;
import stoyanoff.oceanbnb_android.data.AppRepository;
import stoyanoff.oceanbnb_android.data.models.AccessTokenResponse;

/**
 * Created by L on 23/09/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final AppRepository appRepository;
    private final LoginContract.View loginView;

    public LoginPresenter(@NotNull AppRepository appRepository,
                          @NotNull LoginContract.View loginView) {
        this.appRepository = appRepository;
        this.loginView = loginView;
        loginView.setPresenter(this);
        start();
    }

    @Override
    public void start() {
        if(appRepository.isUserLoggedIn()) loginView.successfulLogin();
    }

    @Override
    public void performLogin(String email, String password) {
        loginView.toggleLoading(true);
        appRepository.emailLogin(email, password, new AppDataSource.OnLoginListener() {
            @Override
            public void loginResponse(AccessTokenResponse accessTokenResponse) {
                if(loginView.isActive()){
                    loginView.toggleLoading(false);
                    loginView.successfulLogin();
                }
            }

            @Override
            public void onDataNotAvailable() {
                if(loginView.isActive()){
                    loginView.toggleLoading(false);
                    loginView.showLoginError("");
                }
            }
        });
    }

    @Override
    public void performRegister(String email, String password) {
        loginView.toggleLoading(true);
        appRepository.registerUser(email, password, new AppDataSource.CodeCallback() {
            @Override
            public void getResponseCode(int responseCode) {
                if(loginView.isActive()){
                    loginView.toggleLoading(false);
                    if(responseCode == 200){
                        loginView.successfulRegister();
                    }else {
                        loginView.showRegisterError("");
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {
                if(loginView.isActive()){
                    loginView.toggleLoading(false);
                    loginView.showRegisterError("");
                }
            }
        });
    }
}
