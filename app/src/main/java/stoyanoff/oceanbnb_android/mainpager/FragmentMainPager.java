package stoyanoff.oceanbnb_android.mainpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stoyanoff.oceanbnb_android.R;
import stoyanoff.oceanbnb_android.cruises.FragmentCruises;
import stoyanoff.oceanbnb_android.profile.FragmentProfile;

/**
 * Created by L on 26/09/2017.
 */

public class FragmentMainPager extends Fragment {

    private FragmentProfile fragmentProfile;
    private FragmentCruises fragmentCruises;
    private TabLayout tabLayout;

    public static FragmentMainPager newInstance(){
        return new FragmentMainPager();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentProfile = FragmentProfile.newInstance();
        fragmentCruises = FragmentCruises.newInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_pager,container,false);
        final ViewPager viewPager =(ViewPager) view.findViewById(R.id.fragment_main_pager_view_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.fragment_main_pager_tabs);
        viewPager.setAdapter(new CustomViewAdapter(getActivity().getSupportFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return view;
    }


    private class CustomViewAdapter extends FragmentPagerAdapter {

        CustomViewAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragmentProfile;
                case 1:
                    return fragmentCruises;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return getString(R.string.fragment_main_pager_profile_title);
                case 1:
                    return getString(R.string.fragment_main_pager_cruises_title);
            }
            return null;
        }
    }
}
