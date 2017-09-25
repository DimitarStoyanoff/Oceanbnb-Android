package stoyanoff.oceanbnb_android.cruises;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import stoyanoff.oceanbnb_android.R;
import stoyanoff.oceanbnb_android.cruisedetails.ActivityCruiseDetails;
import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.util.Constants;
import stoyanoff.oceanbnb_android.util.Injection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by L on 24/09/2017.
 */

public class FragmentCruises extends Fragment implements CruisesContract.View{

    private CruisesContract.Presenter presenter;
    private CruiseAdapter cruiseAdapter;

    public static FragmentCruises newInstance(){
        return new FragmentCruises();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CruisesPresenter(Injection.provideAppRepository(getContext()),this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cruises,container,false);
        RecyclerView cruiseRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_cruises_recycler_view);
        cruiseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cruiseAdapter = new CruiseAdapter(new ArrayList<Cruise>(), new CruiseAdapter.OnCruiseItemClickListener() {
            @Override
            public void onClick(Cruise cruise) {
                presenter.openCruiseInfo(cruise);
            }
        });
        cruiseRecyclerView.setAdapter(cruiseAdapter);

        Boolean isUserCruisesList =
                (Boolean) getActivity().getIntent().getSerializableExtra(Constants.USER_CRUISES_EXTRA);
        if(isUserCruisesList != null){
            presenter.loadUserSpecificCruises();
        }else {
            presenter.loadCruises();
        }
        return view;
    }

    @Override
    public void setPresenter(CruisesContract.Presenter presenter) {
        this.presenter= checkNotNull(presenter);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showCruises(List<Cruise> cruises) {
        cruiseAdapter.setItems(cruises);
    }

    @Override
    public void showNoDataText() {
        Toast.makeText(getContext(), R.string.fragment_cuises_no_cruises_found_toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCruiseDetails(Cruise cruise) {
        Intent cruiseDetailsIntent = new Intent(getContext(), ActivityCruiseDetails.class);
        cruiseDetailsIntent.putExtra(Constants.CRUISE_EXTRA, cruise);
        startActivity(cruiseDetailsIntent);
    }
}
