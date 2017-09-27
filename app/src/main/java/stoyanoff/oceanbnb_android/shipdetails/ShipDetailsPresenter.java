package stoyanoff.oceanbnb_android.shipdetails;

import org.jetbrains.annotations.NotNull;

import stoyanoff.oceanbnb_android.data.AppDataSource;
import stoyanoff.oceanbnb_android.data.AppRepository;
import stoyanoff.oceanbnb_android.data.models.Ship;

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
        getShipInfo();
    }

    @Override
    public void getShipInfo() {
        appRepository.getShipById(shipId, new AppDataSource.ShipDetailsCallback() {
            @Override
            public void onShipLoaded(Ship ship) {
                if(shipDetailsView.isActive()){
                    shipDetailsView.showShipDetails(ship);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if(shipDetailsView.isActive()) shipDetailsView.showError();
            }
        });
    }
}
