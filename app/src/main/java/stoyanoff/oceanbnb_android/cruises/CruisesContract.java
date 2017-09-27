package stoyanoff.oceanbnb_android.cruises;

import java.util.List;

import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.util.BasePresenter;
import stoyanoff.oceanbnb_android.util.BaseView;

/**
 * Created by L on 24/09/2017.
 */

public interface CruisesContract {

    interface View extends BaseView<Presenter>{
        void showCruises(List<Cruise> cruises);
        void showNoDataText();
        void showCruiseDetails(Cruise cruise);

    }

    interface Presenter extends BasePresenter{
        void loadCruises();
        void loadUserSpecificCruises();
        void openCruiseInfo(Cruise cruise);
    }
}
