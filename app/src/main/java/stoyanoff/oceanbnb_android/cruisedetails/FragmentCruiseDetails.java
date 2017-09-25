package stoyanoff.oceanbnb_android.cruisedetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import stoyanoff.oceanbnb_android.R;
import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.util.Constants;
import stoyanoff.oceanbnb_android.util.Injection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by L on 24/09/2017.
 */

public class FragmentCruiseDetails extends Fragment implements CruiseDetailsContract.View{

    private CruiseDetailsContract.Presenter presenter;

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
        Cruise cruise =(Cruise) getActivity().getIntent().getSerializableExtra(Constants.CRUISE_EXTRA);
        presenter.getCruiseDetails(cruise.getCruiseId());

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
}
