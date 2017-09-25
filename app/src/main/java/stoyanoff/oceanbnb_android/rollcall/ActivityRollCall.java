package stoyanoff.oceanbnb_android.rollcall;

import android.support.v4.app.Fragment;

import stoyanoff.oceanbnb_android.util.SingleFragmentActivity;

/**
 * Created by L on 25/09/2017.
 */

public class ActivityRollCall extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return FragmentRollCall.newInstance();
    }
}
