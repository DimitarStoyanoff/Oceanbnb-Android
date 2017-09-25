package stoyanoff.oceanbnb_android.mainpager;

import android.support.v4.app.Fragment;

import stoyanoff.oceanbnb_android.util.SingleFragmentActivity;

/**
 * Created by L on 26/09/2017.
 */

public class ActivityMainPager extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return FragmentMainPager.newInstance();
    }
}
