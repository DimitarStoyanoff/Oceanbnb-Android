package stoyanoff.oceanbnb_android.shipdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import stoyanoff.oceanbnb_android.R;
import stoyanoff.oceanbnb_android.data.models.Ship;
import stoyanoff.oceanbnb_android.util.Constants;
import stoyanoff.oceanbnb_android.util.Injection;

/**
 * Created by L on 24/09/2017.
 */

public class FragmentShipDetails extends Fragment implements ShipDetailsContract.View{

    private ShipDetailsContract.Presenter presenter;
    private TextView shipNameTextView;
    private TextView yearBuiltTextView;
    private TextView capacityTextView;
    private TextView crewCountTextView;
    private TextView weightTextView;
    private TextView lengthTextView;
    private TextView beamTextView;


    public static FragmentShipDetails newInstance(){
        return new FragmentShipDetails();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int shipId = getActivity().getIntent().getIntExtra(Constants.SHIP_ID_EXTRA,0);
        new ShipDetailsPresenter(shipId,Injection.provideAppRepository(getContext()),this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ship_details,container,false);
        shipNameTextView = (TextView) view.findViewById(R.id.fragment_ship_details_name_text_view);
        yearBuiltTextView = (TextView) view.findViewById(R.id.fragment_ship_details_year_built_text_view);
        capacityTextView = (TextView) view.findViewById(R.id.fragment_ship_details_passenger_capacity_text_view);
        crewCountTextView = (TextView) view.findViewById(R.id.fragment_ship_details_crew_count_text_view);
        weightTextView = (TextView) view.findViewById(R.id.fragment_ship_details_weight_text_view);
        lengthTextView = (TextView) view.findViewById(R.id.fragment_ship_details_length_text_view);
        beamTextView = (TextView) view.findViewById(R.id.fragment_ship_details_beam_text_view);


        return view;
    }

    @Override
    public void setPresenter(ShipDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.ship_details_fragment_no_data_toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showShipDetails(Ship ship) {
        shipNameTextView.setText(ship.getShipName());
        yearBuiltTextView.setText(Integer.toString(ship.getYearBuilt()));
        capacityTextView.setText(Integer.toString(ship.getPassengerCapacity()));
        crewCountTextView.setText(Integer.toString(ship.getCrewCount()));
        weightTextView.setText(Integer.toString(ship.getWeightInTons()));
        lengthTextView.setText(Integer.toString(ship.getLengthInMeters()));
        beamTextView.setText(Integer.toString(ship.getBeamInMeters()));
    }
}
