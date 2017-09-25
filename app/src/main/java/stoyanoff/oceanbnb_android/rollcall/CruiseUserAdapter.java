package stoyanoff.oceanbnb_android.rollcall;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import stoyanoff.oceanbnb_android.R;
import stoyanoff.oceanbnb_android.data.models.CruiseUser;
import stoyanoff.oceanbnb_android.util.RoundedImageView;

/**
 * Created by L on 25/09/2017.
 */

public class CruiseUserAdapter extends RecyclerView.Adapter<CruiseUserAdapter.UserHolder>{

    private List<CruiseUser> userList;
    private OnUserItemClickListener onUserItemClickListener;

    public interface OnUserItemClickListener{
        void onClick(CruiseUser user);
    }

    public CruiseUserAdapter(List<CruiseUser> userList, OnUserItemClickListener onUserItemClickListener) {
        this.userList = userList;
        this.onUserItemClickListener = onUserItemClickListener;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.bindUserItem(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setItems(List<CruiseUser> users){
        userList = users;
        notifyDataSetChanged();
    }

    public void addItem(CruiseUser user){
        userList.add(user);
        notifyItemInserted(userList.size() - 1);
    }

    public void removeItem(CruiseUser user){
        userList.remove(user);
        notifyDataSetChanged();
    }


    class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CruiseUser user;
        private TextView nameTextView;
        private TextView emailTextView;
        private TextView cityTextView;
        private RoundedImageView roundedImageView;

        public UserHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.list_item_user_name_text_view);
            emailTextView = (TextView) itemView.findViewById(R.id.list_item_user_email_text_view);
            cityTextView = (TextView) itemView.findViewById(R.id.list_item_user_city_text_view);
            roundedImageView = (RoundedImageView) itemView.findViewById(R.id.list_item_user_image_view);
            itemView.setOnClickListener(this);
        }

        public void bindUserItem(CruiseUser user){
            this.user = user;
            nameTextView.setText(user.getUserName());
            emailTextView.setText(user.getEmail());
            cityTextView.setText(user.getCity());
            if(user.getProfilePhoto() != null){
                Picasso.with(roundedImageView.getContext())
                    .load(user.getProfilePhoto()).fit()
                    .into(roundedImageView);
            }else  roundedImageView.setImageResource(R.mipmap.profile_png);
        }

        @Override
        public void onClick(View v) {
            onUserItemClickListener.onClick(user);
        }
    }
}
