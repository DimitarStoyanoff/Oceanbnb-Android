package stoyanoff.oceanbnb_android.rollcall;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import stoyanoff.oceanbnb_android.data.AppDataSource;
import stoyanoff.oceanbnb_android.data.AppRepository;
import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.data.models.CruiseUser;

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
        appRepository.getCruiseUsers(cruise.getCruiseId(), new AppDataSource.CruiseUsersCallback() {
            @Override
            public void onUsersLoaded(List<CruiseUser> cruiseUsers, boolean isUserAdded) {
               if(rollCallView.isActive()) rollCallView.showUsers(cruiseUsers,isUserAdded);
            }

            @Override
            public void onDataNotAvailable() {
                if(rollCallView.isActive()) rollCallView.showNoDataText();
            }
        });
    }

    @Override
    public void openUserInfo(CruiseUser user) {
        if(rollCallView.isActive()) rollCallView.showUserDetails(user);
    }

    @Override
    public void addUserToCruise() {
        appRepository.addUserToCruise(cruise.getCruiseId(), new AppDataSource.CodeCallback() {
            @Override
            public void getResponseCode(int responseCode) {
                if(responseCode == 200){
                    if(rollCallView.isActive()) loadRollCallUsers();
                }else{
                    if(rollCallView.isActive()) rollCallView.showNoDataText();
                }
            }

            @Override
            public void onDataNotAvailable() {
                if(rollCallView.isActive()) rollCallView.showNoDataText();
            }
        });
    }

    @Override
    public void removeUserFromCruise() {
        appRepository.removeUserFromCruise(cruise.getCruiseId(), new AppDataSource.CodeCallback() {
            @Override
            public void getResponseCode(int responseCode) {
                if(responseCode == 200){
                    if(rollCallView.isActive()) loadRollCallUsers();
                }else{
                    if(rollCallView.isActive()) rollCallView.showNoDataText();
                }
            }

            @Override
            public void onDataNotAvailable() {
                if(rollCallView.isActive()) rollCallView.showNoDataText();
            }
        });
    }
}
