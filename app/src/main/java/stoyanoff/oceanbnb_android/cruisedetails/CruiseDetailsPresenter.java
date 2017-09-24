package stoyanoff.oceanbnb_android.cruisedetails;

import org.jetbrains.annotations.NotNull;

import stoyanoff.oceanbnb_android.data.AppRepository;

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
}
