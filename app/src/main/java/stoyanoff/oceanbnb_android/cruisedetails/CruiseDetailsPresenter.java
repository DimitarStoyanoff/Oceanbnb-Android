package stoyanoff.oceanbnb_android.cruisedetails;

import org.jetbrains.annotations.NotNull;

import stoyanoff.oceanbnb_android.data.AppDataSource;
import stoyanoff.oceanbnb_android.data.AppRepository;
import stoyanoff.oceanbnb_android.data.models.Cruise;

/**
 * Created by L on 24/09/2017.
 */

public class CruiseDetailsPresenter implements CruiseDetailsContract.Presenter {

    private final AppRepository appRepository;
    private final CruiseDetailsContract.View cruiseDetailsView;

    public CruiseDetailsPresenter(@NotNull AppRepository appRepository,
                                  @NotNull CruiseDetailsContract.View cruiseDetailsView) {
        this.appRepository = appRepository;
        this.cruiseDetailsView = cruiseDetailsView;
        cruiseDetailsView.setPresenter(this);
        start();
    }

    public void start() {

    }

    @Override
    public void getCruiseDetails(int cruiseId) {
        appRepository.getCruiseById(cruiseId, new AppDataSource.CruiseDetailsCallback() {
            @Override
            public void onCruiseLoaded(Cruise cruise) {
                if(cruiseDetailsView.isActive()) cruiseDetailsView.showCruiseDetails(cruise);
            }

            @Override
            public void onDataNotAvailable() {
                if(cruiseDetailsView.isActive()) cruiseDetailsView.showNoDataAvailable();
            }
        });
    }
}
