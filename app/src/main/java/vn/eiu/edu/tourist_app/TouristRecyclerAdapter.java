package vn.eiu.edu.tourist_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TouristRecyclerAdapter extends RecyclerView.Adapter<TouristRecyclerAdapter.TouristHolder> {
    List<String> touristSites;

    public TouristRecyclerAdapter(List<String> touristSites) {
        this.touristSites = touristSites;
    }

    @NonNull
    @Override
    public TouristHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); // virtual read xml
        TextView v = (TextView) inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        TouristHolder viewHolder = new TouristHolder(v); //

//        TextView v2 = (TextView) LayoutInflater
//                .from(parent.getContext())
//                .inflate(android.R.layout.simple_list_item_1, parent, false);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TouristHolder holder, int position) {
        String site = touristSites.get(position);
        holder.bindView(site);
    }

    @Override
    public int getItemCount() {
        return touristSites.size();
    }

    public static class TouristHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public TouristHolder(View itemView) {
            super(itemView);
            this.textView = (TextView)itemView;
        }

        public void bindView(String touristItems) {
            textView.setText(touristItems);
        }
    }
}
