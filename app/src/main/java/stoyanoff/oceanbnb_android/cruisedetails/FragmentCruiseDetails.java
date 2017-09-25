package stoyanoff.oceanbnb_android.cruisedetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import stoyanoff.oceanbnb_android.R;
import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.rollcall.ActivityRollCall;
import stoyanoff.oceanbnb_android.shipdetails.ActivityShipDetails;
import stoyanoff.oceanbnb_android.util.Constants;
import stoyanoff.oceanbnb_android.util.Injection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by L on 24/09/2017.
 */

public class FragmentCruiseDetails extends Fragment implements CruiseDetailsContract.View{

    private CruiseDetailsContract.Presenter presenter;
    private TextView cruiseNameTextView;
    private View rollCallLayout;
    private View shipDetailsLayout;


    public static FragmentCruiseDetails newInstance(){
        return new FragmentCruiseDetails();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CruiseDetailsPresenter(Injection.provideAppRepository(getContext()),this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cruise_details,container,false);
        final Cruise cruise =(Cruise) getActivity().getIntent().getSerializableExtra(Constants.CRUISE_EXTRA);
        presenter.getCruiseDetails(cruise.getCruiseId());
        cruiseNameTextView = (TextView) view.findViewById(R.id.fragment_cruise_details_cruise_name_text_view);
        cruiseNameTextView.setText(cruise.getCruiseName());
        rollCallLayout =  view.findViewById(R.id.fragment_cruise_details_roll_call_layout);
        shipDetailsLayout = view.findViewById(R.id.fragment_cruise_details_ship_details_layout);
        rollCallLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRollCall(cruise);
            }
        });
        shipDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShipDetails(cruise.getShipId());
            }
        });

        return view;
    }

    @Override
    public void setPresenter(CruiseDetailsContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showCruiseDetails(Cruise cruise) {

    }

    @Override
    public void showNoDataAvailable() {
        Toast.makeText(getContext(), R.string.fragment_cruise_details_no_data_toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRollCall(Cruise cruise) {
        Intent rollCallIntent = new Intent(getContext(), ActivityRollCall.class);
        rollCallIntent.putExtra(Constants.CRUISE_EXTRA,cruise);
        startActivity(rollCallIntent);
    }

    @Override
    public void showShipDetails(int shipId) {
        Intent shipIntent = new Intent(getContext(), ActivityShipDetails.class);
        shipIntent.putExtra(Constants.SHIP_ID_EXTRA,shipId);
        startActivity(shipIntent);
    }
}
