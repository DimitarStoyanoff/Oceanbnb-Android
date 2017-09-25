package stoyanoff.oceanbnb_android.shipdetails;

import stoyanoff.oceanbnb_android.data.models.Ship;
import stoyanoff.oceanbnb_android.util.BasePresenter;
import stoyanoff.oceanbnb_android.util.BaseView;

/**
 * Created by L on 24/09/2017.
 */

public interface ShipDetailsContract {

    interface View extends BaseView<Presenter>{
        void showError();
        void showShipDetails(Ship ship);
    }

    interface Presenter extends BasePresenter{
        void getShipInfo();
    }
}
