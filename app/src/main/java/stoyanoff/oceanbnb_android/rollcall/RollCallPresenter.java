package stoyanoff.oceanbnb_android.rollcall;

import org.jetbrains.annotations.NotNull;

import stoyanoff.oceanbnb_android.data.AppRepository;
import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.data.models.User;

/**
 * Created by L on 25/09/2017.
 */

public class RollCallPresenter implements RollCallContract.Presenter {

    private final Cruise cruise;
    private final AppRepository appRepository;
    private final RollCallContract.View rollCallView;

    public RollCallPresenter(@NotNull Cruise cruise, @NotNull AppRepository appRepository,
                             @NotNull RollCallContract.View rollCallView) {
        this.cruise = cruise;
        this.appRepository = appRepository;
        this.rollCallView = rollCallView;
        rollCallView.setPresenter(this);
        start();
    }

    public void start() {
        loadRollCallUsers();
    }

    @Override
    public void loadRollCallUsers() {

    }

    @Override
    public void openUserInfo(User user) {
        if(rollCallView.isActive()) rollCallView.showUserDetails(user);
    }

    @Override
    public void addUserToCruise() {

    }

    @Override
    public void removeUserFromCruise() {

    }
}
