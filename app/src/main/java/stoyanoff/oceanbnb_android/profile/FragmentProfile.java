package stoyanoff.oceanbnb_android.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import stoyanoff.oceanbnb_android.R;
import stoyanoff.oceanbnb_android.cruises.ActivityCruises;
import stoyanoff.oceanbnb_android.data.models.User;
import stoyanoff.oceanbnb_android.login.ActivityLogin;
import stoyanoff.oceanbnb_android.util.Constants;
import stoyanoff.oceanbnb_android.util.Injection;
import stoyanoff.oceanbnb_android.util.RoundedImageView;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by L on 24/09/2017.
 */

public class FragmentProfile extends Fragment implements ProfileContract.View{

    private ProfileContract.Presenter presenter;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView genderTextView;
    private TextView cityTextView;
    private TextView descriptionTextView;
    private ImageView logoutImageView;
    private ImageView cruisesImageView;
    private RoundedImageView roundedImageView;

    public static FragmentProfile newInstance(){
        return new FragmentProfile();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ProfilePresenter(Injection.provideAppRepository(getContext()),this);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        roundedImageView =
                (RoundedImageView) view.findViewById(R.id.fragment_profile_roundedImageView);
        roundedImageView.setImageResource(R.mipmap.profile_png);

        nameTextView = (TextView) view.findViewById(R.id.fragment_profile_name_text_view);
        emailTextView = (TextView) view.findViewById(R.id.fragment_profile_email_text_view);
        genderTextView = (TextView) view.findViewById(R.id.fragment_profile_gender_text_view);
        cityTextView = (TextView) view.findViewById(R.id.fragment_profile_city_text_view);
        descriptionTextView = (TextView) view.findViewById(R.id.fragment_profile_description_text_view);
        logoutImageView = (ImageView) view.findViewById(R.id.fragment_profile_logout_image_view);
        cruisesImageView = (ImageView) view.findViewById(R.id.fragment_profile_cruises_image_view);
        cruisesImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ActivityCruises.class);
                intent.putExtra(Constants.USER_CRUISES_EXTRA,true);
                startActivity(intent);
            }
        });
        logoutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout();
            }
        });
        return view;
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }


    @Override
    public void showProfileInfo(User user) {
        nameTextView.setText(user.getUserName());
        emailTextView.setText(user.getEmail());
        genderTextView.setText(user.getGender());
        cityTextView.setText(user.getCity());
        descriptionTextView.setText(user.getDescription());
        if(user.getProfilePhoto() != null)
            Picasso.with(getContext())
                .load(user.getProfilePhoto()).fit()
                .into(roundedImageView);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginScreen() {
        Intent loginIntent = new Intent(getContext(), ActivityLogin.class);
        startActivity(loginIntent);
        getActivity().finish();
    }
}
