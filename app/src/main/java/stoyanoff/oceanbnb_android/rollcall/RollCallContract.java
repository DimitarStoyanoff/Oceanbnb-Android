package stoyanoff.oceanbnb_android.rollcall;

import java.util.List;

import stoyanoff.oceanbnb_android.data.models.User;
import stoyanoff.oceanbnb_android.util.BasePresenter;
import stoyanoff.oceanbnb_android.util.BaseView;

/**
 * Created by L on 25/09/2017.
 */

public interface RollCallContract {

    interface View extends BaseView<Presenter>{
        void showUsers(List<User> users);
        void showNoDataText();
        void showUserDetails(User user);
        void addUserToList(User user);
        void removeUserFromList(User user);
    }

    interface Presenter extends BasePresenter{
        void loadRollCallUsers();
        void openUserInfo(User user);
        void addUserToCruise();
        void removeUserFromCruise();
    }
}
