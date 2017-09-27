package stoyanoff.oceanbnb_android.cruises;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import stoyanoff.oceanbnb_android.data.AppDataSource;
import stoyanoff.oceanbnb_android.data.AppRepository;
import stoyanoff.oceanbnb_android.data.models.Cruise;

/**
 * Created by L on 24/09/2017.
 */

public class CruisesPresenter implements CruisesContract.Presenter {

    private final AppRepository appRepository;
    private final CruisesContract.View cruisesView;

    public CruisesPresenter(@NotNull AppRepository appRepository,
                            @NotNull CruisesContract.View cruisesView) {
        this.appRepository = appRepository;
        this.cruisesView = cruisesView;
        cruisesView.setPresenter(this);
        start();
    }

    public void start() {
        loadCruises();
    }

    @Override
    public void loadCruises() {
        appRepository.getAllCruises(new AppDataSource.AllCruisesCallback() {
            @Override
            public void getCruises(List<Cruise> cruises) {
                if(cruisesView.isActive())
                    cruisesView.showCruises(cruises);
            }

            @Override
            public void onDataNotAvailable() {
                if(cruisesView.isActive())
                    cruisesView.showNoDataText();
            }
        });
    }

    @Override
    public void loadUserSpecificCruises() {
        appRepository.getUserCruises(new AppDataSource.UserCruisesCallback() {
            @Override
            public void getUserCruises(List<Cruise> userCruises) {
                if(cruisesView.isActive())
                    cruisesView.showCruises(userCruises);
            }

            @Override
            public void onDataNotAvailable() {
                if(cruisesView.isActive())
                    cruisesView.showNoDataText();
            }
        });
    }

    @Override
    public void openCruiseInfo(Cruise cruise) {
        if(cruisesView.isActive())
        cruisesView.showCruiseDetails(cruise);
    }
}
