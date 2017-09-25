package stoyanoff.oceanbnb_android.rollcall;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import stoyanoff.oceanbnb_android.R;
import stoyanoff.oceanbnb_android.data.models.User;

/**
 * Created by L on 25/09/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    private List<User> userList;
    private OnUserItemClickListener onUserItemClickListener;

    public interface OnUserItemClickListener{
        void onClick(User user);
    }

    public UserAdapter(List<User> userList, OnUserItemClickListener onUserItemClickListener) {
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

    public void setItems(List<User> users){
        userList = users;
        notifyDataSetChanged();
    }

    public void addItem(User user){
        userList.add(user);
        notifyItemInserted(userList.size() - 1);
    }

    public void removeItem(User user){
        userList.remove(user);
        notifyDataSetChanged();
    }


    class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private User user;

        public UserHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void bindUserItem(User user){
            this.user = user;
        }

        @Override
        public void onClick(View v) {
            onUserItemClickListener.onClick(user);
        }
    }
}
