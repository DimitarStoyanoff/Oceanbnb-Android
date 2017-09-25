package stoyanoff.oceanbnb_android.rollcall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import stoyanoff.oceanbnb_android.R;
import stoyanoff.oceanbnb_android.data.models.Cruise;
import stoyanoff.oceanbnb_android.data.models.User;
import stoyanoff.oceanbnb_android.login.ActivityLogin;
import stoyanoff.oceanbnb_android.util.Constants;
import stoyanoff.oceanbnb_android.util.Injection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by L on 25/09/2017.
 */

public class FragmentRollCall extends Fragment implements RollCallContract.View{

    private RollCallContract.Presenter presenter;
    private UserAdapter userAdapter;
    private Cruise cruise;

    public static FragmentRollCall newInstance(){
        return new FragmentRollCall();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cruise = (Cruise) getActivity().getIntent().getSerializableExtra(Constants.CRUISE_EXTRA);
        new RollCallPresenter(cruise,Injection.provideAppRepository(getContext()),this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_roll_call,container,false);



        RecyclerView usersRecyclerView =
                (RecyclerView) view.findViewById(R.id.fragment_roll_call_users_recycler_view);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userAdapter = new UserAdapter(new ArrayList<User>(), new UserAdapter.OnUserItemClickListener() {
            @Override
            public void onClick(User user) {
                presenter.openUserInfo(user);
            }
        });
        usersRecyclerView.setAdapter(userAdapter);

        return view;
    }


    @Override
    public void setPresenter(RollCallContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showUsers(List<User> users) {
        userAdapter.setItems(users);
    }

    @Override
    public void showNoDataText() {
        Toast.makeText(getContext(), R.string.fragment_roll_call_load_failed_toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserDetails(User user) {
        Intent intent = new Intent(getContext(), ActivityLogin.class);
        intent.putExtra(Constants.USER_ID_EXTRA,user.getUserId());
        startActivity(intent);
    }

    @Override
    public void addUserToList(User user) {
        userAdapter.addItem(user);
    }

    @Override
    public void removeUserFromList(User user) {
        userAdapter.removeItem(user);
    }
}
