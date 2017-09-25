package stoyanoff.oceanbnb_android.rollcall;

import java.util.List;

import stoyanoff.oceanbnb_android.data.models.CruiseUser;
import stoyanoff.oceanbnb_android.util.BasePresenter;
import stoyanoff.oceanbnb_android.util.BaseView;

/**
 * Created by L on 25/09/2017.
 */

public interface RollCallContract {

    interface View extends BaseView<Presenter>{
        void showUsers(List<CruiseUser> users, boolean isUserAdded);
        void showNoDataText();
        void showUserDetails(CruiseUser user);
        void addUserToList(CruiseUser user);
        void removeUserFromList(CruiseUser user);
    }

    interface Presenter extends BasePresenter{
        void loadRollCallUsers();
        void openUserInfo(CruiseUser user);
        void addUserToCruise();
        void removeUserFromCruise();
    }
}
