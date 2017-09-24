package stoyanoff.oceanbnb_android.shipdetails;

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

public class FragmentShipDetails extends Fragment implements ShipDetailsContract.View{

    public static FragmentShipDetails newInstance(){
        return new FragmentShipDetails();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ship_details,container,false);


        return view;
    }

    @Override
    public void setPresenter(ShipDetailsContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
