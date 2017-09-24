package stoyanoff.oceanbnb_android.cruises;

import org.jetbrains.annotations.NotNull;

import stoyanoff.oceanbnb_android.data.AppRepository;

/**
 * Created by L on 24/09/2017.
 */

public class CruisesController implements CruisesContract.Presenter {

    private final AppRepository appRepository;
    private final CruisesContract.View crusisesView;

    public CruisesController(@NotNull AppRepository appRepository,
                             @NotNull CruisesContract.View crusisesView) {
        this.appRepository = appRepository;
        this.crusisesView = crusisesView;
        crusisesView.setPresenter(this);
        start();
    }

    public void start() {

    }
}
