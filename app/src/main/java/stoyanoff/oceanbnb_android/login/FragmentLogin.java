package stoyanoff.oceanbnb_android.login;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import stoyanoff.oceanbnb_android.MainActivity;
import stoyanoff.oceanbnb_android.R;
import stoyanoff.oceanbnb_android.util.Injection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by L on 23/09/2017.
 */

public class FragmentLogin extends Fragment implements LoginContract.View{

    //State codes
    private static final int SHOWING_DEFAULT_SCREEN = 0;
    private static final int SHOWING_LOGIN_WITH_EMAIL = 1;
    private static final int SHOWING_SIGN_UP_WITH_EMAIL = 2;

    private boolean transitioning = false;

    private int currentState = SHOWING_DEFAULT_SCREEN;

    private EditText emailEditText;
    private EditText passwordEditText;

    private LinearLayout profileTextLaoyut;
    private LinearLayout passwordTextLaoyut;
    private RelativeLayout rootLayout;

    private ImageView logoImageView;

    private RelativeLayout signUpButton;
    private RelativeLayout signinButton;
    private Button signupButtonReal;
    private RelativeLayout facebookLoginImageView;
    private RelativeLayout signInButtonReal;

    private TextView forgottenPasswordTextView;

    private AnimatorSet loginInSet = new AnimatorSet();
    private AnimatorSet signUpInSet = new AnimatorSet();
    private AnimatorSet loginOutSet = new AnimatorSet();
    private AnimatorSet signUpOutset = new AnimatorSet();

    //sign up
    private EditText emailEditTextSignUp;
    private EditText PasswordEditTextSignUp;
    private EditText confirmPasswordEditTextSignUp;
    private RelativeLayout signupLayout;

    private LoginContract.Presenter presenter;

    public static FragmentLogin newInstance(){
        return new FragmentLogin();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        new LoginPresenter(Injection.provideAppRepository(getContext()),this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);

        facebookLoginImageView = (RelativeLayout) view.findViewById(R.id.login_with_facebook_relative_layout);

        rootLayout =(RelativeLayout) view.findViewById(R.id.root_layout);

        setUpAnimatorSets();

        signUpButton = (RelativeLayout) view.findViewById(R.id.fragment_login_sign_up);

        logoImageView = (ImageView) view.findViewById(R.id.fragment_login_logo_image);
        emailEditText = (EditText) view.findViewById(R.id.fragment_login_email_edit_text);
        passwordEditText = (EditText) view.findViewById(R.id.fragment_login_passoword_edit_text);
        signinButton = (RelativeLayout) view.findViewById(R.id.fragment_login_done_button);
        forgottenPasswordTextView =
                (TextView) view.findViewById(R.id.fragment_login_forgotten_password_text_view);
        forgottenPasswordTextView.setTranslationX(rootLayout.getWidth() * -1f);

        profileTextLaoyut = (LinearLayout) view.findViewById(R.id.profile_text_layout);
        passwordTextLaoyut = (LinearLayout) view.findViewById(R.id.password_text_layout);
        signInButtonReal = (RelativeLayout) view.findViewById(R.id.fragment_login_sign_in) ;



        signupLayout = (RelativeLayout) view.findViewById(R.id.sign_up_layout);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!transitioning){
                    currentState = SHOWING_SIGN_UP_WITH_EMAIL;

                /*signUpOutset.cancel();
                loginOutSet.cancel();
                loginInSet.cancel();*/

                    signupLayout.setVisibility(View.VISIBLE);
                    signupLayout.setTranslationX(rootLayout.getWidth()* -1f);
                    signupLayout.setTranslationY(passwordTextLaoyut.getHeight() * -2);


                    ObjectAnimator logoAnimatorY = ObjectAnimator.ofFloat(logoImageView, "translationY", -signUpButton.getHeight() * 1.3f);
                    ObjectAnimator signinButtonNotRealY = ObjectAnimator.ofFloat(signinButton, "translationY",rootLayout.getHeight());
                    ObjectAnimator facebookButtonY = ObjectAnimator.ofFloat(facebookLoginImageView, "translationY",rootLayout.getHeight());
                    ObjectAnimator signUpButtonY = ObjectAnimator.ofFloat(signUpButton, "translationY",rootLayout.getHeight());
                    ObjectAnimator logoAnimatorScaleX = ObjectAnimator.ofFloat(logoImageView, "scaleX",0.7f * logoImageView.getScaleX());
                    ObjectAnimator logoAnimatorScaleY = ObjectAnimator.ofFloat(logoImageView, "scaleY",0.7f * logoImageView.getScaleY());
                    ObjectAnimator signUpLayoutIn = ObjectAnimator.ofFloat(signupLayout, "translationX", 0);
                    ;
                    signUpInSet.playTogether(logoAnimatorY, logoAnimatorScaleX, logoAnimatorScaleY, signUpLayoutIn, signinButtonNotRealY,
                            facebookButtonY, signUpButtonY);
                    signUpInSet.start();
                }
            }
        });


        //**Signup thigns
        emailEditTextSignUp = (EditText) view.findViewById(R.id.signup_fragment_email_et);
        PasswordEditTextSignUp = (EditText) view.findViewById(R.id.signup_fragment_password_et);
        confirmPasswordEditTextSignUp = (EditText) view.findViewById(R.id.signup_fragment_password2_et);
        signupButtonReal = (Button) view.findViewById(R.id.signup_fragment_signup_button);

        setupSignUpButton();



        PasswordEditTextSignUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().length() < 6 || charSequence.toString().length() > 100){
                    PasswordEditTextSignUp.setError(getString(R.string.short_password_error));
                }else if(charSequence.toString().equals(confirmPasswordEditTextSignUp.getText().toString())){
                    confirmPasswordEditTextSignUp.setError(null);
                }else{
                    confirmPasswordEditTextSignUp.setError(getString(R.string.passwords_dont_match_error_message));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        confirmPasswordEditTextSignUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals(PasswordEditTextSignUp.getText().toString())){
                    confirmPasswordEditTextSignUp.setError(null);
                }else{
                    confirmPasswordEditTextSignUp.setError(getString(R.string.passwords_dont_match_error_message));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        emailEditTextSignUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String regExpn =
                        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                                +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                                +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                                +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

                Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(charSequence);

                if(matcher.matches()){
                    emailEditTextSignUp.setError(null);
                }else{
                    emailEditTextSignUp.setError(getString(R.string.invalid_email_error));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /**
         * Signup things end
         */


        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!transitioning) {
                    currentState = SHOWING_LOGIN_WITH_EMAIL;

               /* signUpOutset.cancel();
                loginOutSet.cancel();
                signUpInSet.cancel();*/

                    passwordTextLaoyut.setVisibility(View.VISIBLE);
                    passwordTextLaoyut.setTranslationX(rootLayout.getWidth() * -1f);
                    passwordTextLaoyut.setTranslationY(passwordTextLaoyut.getHeight() * -2);
                    profileTextLaoyut.setVisibility(View.VISIBLE);
                    profileTextLaoyut.setTranslationX(rootLayout.getWidth() * -1f);
                    profileTextLaoyut.setTranslationY(profileTextLaoyut.getHeight() * -2);
                    signInButtonReal.setVisibility(View.VISIBLE);
                    signInButtonReal.setTranslationX(rootLayout.getWidth() * -1f);
                    signInButtonReal.setTranslationY(signInButtonReal.getHeight() * -2);
                    forgottenPasswordTextView.setVisibility(View.VISIBLE);
                    forgottenPasswordTextView.setTranslationX(rootLayout.getWidth() * -1f);
                    forgottenPasswordTextView.setTranslationY(signInButtonReal.getHeight() * -2);


                    ObjectAnimator logoAnimatorY = ObjectAnimator.ofFloat(logoImageView, "translationY", -signUpButton.getHeight() * 1.3f);
                    ObjectAnimator signinButtonNotRealY = ObjectAnimator.ofFloat(signinButton, "translationY", rootLayout.getHeight());
                    ObjectAnimator facebookButtonY = ObjectAnimator.ofFloat(facebookLoginImageView, "translationY", rootLayout.getHeight());
                    ObjectAnimator signUpButtonY = ObjectAnimator.ofFloat(signUpButton, "translationY", rootLayout.getHeight());
                    ObjectAnimator logoAnimatorScaleX = ObjectAnimator.ofFloat(logoImageView, "scaleX", 0.7f * logoImageView.getScaleX());
                    ObjectAnimator logoAnimatorScaleY = ObjectAnimator.ofFloat(logoImageView, "scaleY", 0.7f * logoImageView.getScaleY());
                    ObjectAnimator editTextPasswordIn = ObjectAnimator.ofFloat(passwordTextLaoyut, "translationX", 0);
                    ObjectAnimator editTextProfileIn = ObjectAnimator.ofFloat(profileTextLaoyut, "translationX", 0);
                    ObjectAnimator signInButtonIn = ObjectAnimator.ofFloat(signInButtonReal, "translationX", 0);
                    ObjectAnimator forgottenPasswordIn = ObjectAnimator.ofFloat(forgottenPasswordTextView, "translationX", 0);

                    loginInSet.playTogether(logoAnimatorY, logoAnimatorScaleX, logoAnimatorScaleY, editTextPasswordIn, editTextProfileIn, signInButtonIn, signinButtonNotRealY,
                            facebookButtonY, signUpButtonY, forgottenPasswordIn);
                    loginInSet.start();
                }
            }
        });

        setupSignInButton();

        return view;
    }

    private void setupSignInButton(){
        signInButtonReal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.performLogin(emailEditText.getText().toString(),passwordEditText.getText().toString());
            }
        });
    }

    private void setupSignUpButton(){
        signupButtonReal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(emailEditTextSignUp.getText().toString().equals("")){
                    emailEditTextSignUp.setError(getString(R.string.signup_required_field_error));
                }else if(emailEditTextSignUp.getError() != null){

                }else if(PasswordEditTextSignUp.getText().toString().equals("")){
                    PasswordEditTextSignUp.setError(getString(R.string.signup_required_field_error));
                }else if(PasswordEditTextSignUp.getError() != null){

                }else if(confirmPasswordEditTextSignUp.getError() != null){

                }else {
                    presenter.performLogin(emailEditTextSignUp.getText().toString(),
                            PasswordEditTextSignUp.getText().toString());
                }
            }
        });
    }

    private void setUpAnimatorSets() {
        loginInSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                transitioning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                transitioning = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        loginOutSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                transitioning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                transitioning = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        signUpInSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                transitioning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                transitioning = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        signUpOutset.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                transitioning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                transitioning = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void returnFromSignUp(){
        currentState = SHOWING_DEFAULT_SCREEN;

        /*loginInSet.cancel();
        loginOutSet.cancel();
        signUpInSet.cancel();*/

        ObjectAnimator logoAnimatorY = ObjectAnimator.ofFloat(logoImageView, "translationY", 0f);
        ObjectAnimator signinButtonNotRealY = ObjectAnimator.ofFloat(signinButton, "translationY", 0f);
        ObjectAnimator facebookButtonY = ObjectAnimator.ofFloat(facebookLoginImageView, "translationY",0f);
        ObjectAnimator signUpButtonY = ObjectAnimator.ofFloat(signUpButton, "translationY", 0f);
        ObjectAnimator logoAnimatorScaleX = ObjectAnimator.ofFloat(logoImageView, "scaleX",logoImageView.getScaleX() / 0.7f);
        ObjectAnimator logoAnimatorScaleY = ObjectAnimator.ofFloat(logoImageView, "scaleY", logoImageView.getScaleY() / 0.7f);
        ObjectAnimator signupLayoutOut = ObjectAnimator.ofFloat(signupLayout, "translationX", rootLayout.getWidth() * -1f);

        signUpOutset.playTogether(logoAnimatorY, logoAnimatorScaleX, logoAnimatorScaleY, signupLayoutOut, signinButtonNotRealY,
                facebookButtonY, signUpButtonY);
        signUpOutset.start();
    }

    private void returnFromLogin(){
        currentState = SHOWING_DEFAULT_SCREEN;

        /*loginInSet.cancel();
        signUpOutset.cancel();
        signUpInSet.cancel();*/

        ObjectAnimator logoAnimatorY = ObjectAnimator.ofFloat(logoImageView, "translationY", 0f);
        ObjectAnimator signinButtonNotRealY = ObjectAnimator.ofFloat(signinButton, "translationY", 0f);
        ObjectAnimator facebookButtonY = ObjectAnimator.ofFloat(facebookLoginImageView, "translationY",0f);
        ObjectAnimator signUpButtonY = ObjectAnimator.ofFloat(signUpButton, "translationY", 0f);
        ObjectAnimator logoAnimatorScaleX = ObjectAnimator.ofFloat(logoImageView, "scaleX",logoImageView.getScaleX() / 0.7f);
        ObjectAnimator logoAnimatorScaleY = ObjectAnimator.ofFloat(logoImageView, "scaleY", logoImageView.getScaleY() / 0.7f);
        ObjectAnimator editTextPasswordOut = ObjectAnimator.ofFloat(forgottenPasswordTextView, "translationX", rootLayout.getWidth()* -1f);
        ObjectAnimator forgottenPasswordOut = ObjectAnimator.ofFloat(passwordTextLaoyut, "translationX", rootLayout.getWidth()* -1f);
        ObjectAnimator editTextProfileOut = ObjectAnimator.ofFloat(profileTextLaoyut, "translationX",rootLayout.getWidth()* -1f);
        ObjectAnimator signInButtonOut = ObjectAnimator.ofFloat(signInButtonReal, "translationX",rootLayout.getWidth()* -1f);

        loginOutSet.playTogether(logoAnimatorY, logoAnimatorScaleX, logoAnimatorScaleY, editTextPasswordOut, forgottenPasswordOut, editTextProfileOut,
                signInButtonOut, signinButtonNotRealY,
                facebookButtonY, signUpButtonY);
        loginOutSet.start();
    }

    public void onBackPressed(){
        switch (currentState) {
            case SHOWING_DEFAULT_SCREEN:
                getActivity().finish();
                break;
            case SHOWING_LOGIN_WITH_EMAIL:
                if (!transitioning) {
                    returnFromLogin();
                }
                break;
            case SHOWING_SIGN_UP_WITH_EMAIL:
                if (!transitioning){
                    returnFromSignUp();
                }
                break;
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
       this.presenter = checkNotNull(presenter);
    }

    @Override
    public void showRegisterError(String message) {
        Toast.makeText(getContext(),
                getString(R.string.registration_failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginError(String message) {
        Toast.makeText(getContext(),
                R.string.fragment_login_login_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toggleLoading(boolean isOn) {
        if(isOn){
            signInButtonReal.setOnClickListener(null);
            signupButtonReal.setOnClickListener(null);
        }else {
            setupSignInButton();
            setupSignUpButton();
        }
    }

    @Override
    public void successfulRegister() {
        presenter.performLogin(emailEditText.getText().toString(),passwordEditText.getText().toString());
    }

    @Override
    public void successfulLogin() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
