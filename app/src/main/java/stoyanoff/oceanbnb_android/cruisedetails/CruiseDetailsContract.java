package stoyanoff.oceanbnb_android.cruisedetails;

import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.util.BasePresenter;
import stoyanoff.oceanbnb_android.util.BaseView;

/**
 * Created by L on 24/09/2017.
 */

public interface CruiseDetailsContract {

    interface View extends BaseView<CruiseDetailsContract.Presenter>{
        void showCruiseDetails(Cruise cruise);
        void showNoDataAvailable();
        void showRollCall(Cruise cruise);
        void showShipDetails(int shipId);
    }

    interface Presenter extends BasePresenter{
        void getCruiseDetails(int cruiseId);

    }
}
