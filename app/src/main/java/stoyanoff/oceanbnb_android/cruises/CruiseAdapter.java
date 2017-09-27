package stoyanoff.oceanbnb_android.cruises;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import stoyanoff.oceanbnb_android.R;
import stoyanoff.oceanbnb_android.data.models.Cruise;

/**
 * Created by L on 24/09/2017.
 */

public class CruiseAdapter extends RecyclerView.Adapter<CruiseAdapter.CruiseHolder>{

    private List<Cruise> cruiseList;
    private OnCruiseItemClickListener onCruiseItemClickListener;

    public interface OnCruiseItemClickListener{
        void onClick(Cruise cruise);
    }

    public CruiseAdapter(List<Cruise> cruiseList, OnCruiseItemClickListener onCruiseItemClickListener) {
        this.onCruiseItemClickListener = onCruiseItemClickListener;
        this.cruiseList = cruiseList;
    }

    @Override
    public CruiseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cruise, parent, false);
        return new CruiseHolder(view);
    }

    @Override
    public void onBindViewHolder(CruiseHolder holder, int position) {
        holder.bindCruiseItem(cruiseList.get(position));
    }

    @Override
    public int getItemCount() {
        return cruiseList.size();
    }

    public void setItems(List<Cruise> cruises){
        cruiseList = cruises;
        notifyDataSetChanged();
    }

    class CruiseHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Cruise cruise;
        private TextView cruiseNameTextView;

        public CruiseHolder(View itemView) {
            super(itemView);
            cruiseNameTextView = (TextView) itemView.findViewById(R.id.list_item_cruise_name_text_view);
            itemView.setOnClickListener(this);
        }

        public void bindCruiseItem(Cruise cruise){
            this.cruise = cruise;
            cruiseNameTextView.setText(cruise.getCruiseName());
        }

        @Override
        public void onClick(View v) {
            onCruiseItemClickListener.onClick(cruise);
        }
    }
}
