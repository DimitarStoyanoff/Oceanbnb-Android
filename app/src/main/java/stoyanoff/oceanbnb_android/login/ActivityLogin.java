package stoyanoff.oceanbnb_android.login;

import android.support.v4.app.Fragment;

import stoyanoff.oceanbnb_android.util.SingleFragmentActivity;

/**
 * Created by L on 23/09/2017.
 */

public class ActivityLogin extends SingleFragmentActivity {

    private FragmentLogin currentFragment;

    @Override
    protected Fragment createFragment() {
        currentFragment = FragmentLogin.newInstance();
        return currentFragment;
    }

    @Override
    public void onBackPressed() {
        currentFragment.onBackPressed();

    }
}
