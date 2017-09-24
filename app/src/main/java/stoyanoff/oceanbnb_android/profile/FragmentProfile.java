package stoyanoff.oceanbnb_android.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stoyanoff.oceanbnb_android.R;

/**
 * Created by L on 24/09/2017.
 */

public class FragmentProfile extends Fragment implements ProfileContract.View{

    public static FragmentProfile newInstance(){
        return new FragmentProfile();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        return view;
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {

    }
}
