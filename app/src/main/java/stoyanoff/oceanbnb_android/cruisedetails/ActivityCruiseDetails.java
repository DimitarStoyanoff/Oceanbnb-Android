package stoyanoff.oceanbnb_android.cruisedetails;

import android.support.v4.app.Fragment;

import stoyanoff.oceanbnb_android.util.SingleFragmentActivity;

/**
 * Created by L on 24/09/2017.
 */

public class ActivityCruiseDetails extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return FragmentCruiseDetails.newInstance();
    }
}
