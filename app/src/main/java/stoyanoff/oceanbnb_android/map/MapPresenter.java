package stoyanoff.oceanbnb_android.map;

import org.jetbrains.annotations.NotNull;

import stoyanoff.oceanbnb_android.data.AppRepository;

/**
 * Created by L on 24/09/2017.
 */

public class MapPresenter implements MapContract.Presenter {

    private final AppRepository appRepository;
    private final MapContract.View mapView;

    public MapPresenter(@NotNull AppRepository appRepository,@NotNull MapContract.View mapView) {
        this.appRepository = appRepository;
        this.mapView = mapView;
        mapView.setPresenter(this);
        start();
    }

    public void start() {

    }
}
