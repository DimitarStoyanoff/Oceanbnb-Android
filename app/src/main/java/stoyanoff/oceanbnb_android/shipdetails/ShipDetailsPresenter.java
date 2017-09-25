package stoyanoff.oceanbnb_android.shipdetails;

import org.jetbrains.annotations.NotNull;

import stoyanoff.oceanbnb_android.data.AppRepository;

/**
 * Created by L on 24/09/2017.
 */

public class ShipDetailsPresenter implements ShipDetailsContract.Presenter {

    private final AppRepository appRepository;
    private final ShipDetailsContract.View shipDetailsView;
    private int shipId;

    public ShipDetailsPresenter(int shipId,@NotNull AppRepository appRepository,
                                @NotNull ShipDetailsContract.View shipDetailsView) {
        this.appRepository = appRepository;
        this.shipDetailsView = shipDetailsView;
        this.shipId = shipId;
        shipDetailsView.setPresenter(this);
        start();
    }

    public void start() {

    }
}
