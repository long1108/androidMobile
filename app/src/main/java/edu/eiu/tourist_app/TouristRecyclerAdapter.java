package edu.eiu.tourist_app;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import edu.eiu.tourist_app.datamodel.WikipediaPage;

public class TouristRecyclerAdapter extends RecyclerView.Adapter<TouristRecyclerAdapter.TouristViewHolder> {
    private List<WikipediaPage> touristSites;

    public TouristRecyclerAdapter (List<WikipediaPage> items) {
        this.touristSites = items;
    }

    @NonNull
    @Override
    public TouristViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.tourist_list_item, parent, false);
        TouristViewHolder viewHolder = new TouristViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TouristViewHolder holder, int position) {
        WikipediaPage site = touristSites.get(position);
        holder.bindView(site);
    }

    @Override
    public int getItemCount() {
        return touristSites.size();
    }

    public static class TouristViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout view;
        private TextView textView;
        private ImageView imageView;

        public TouristViewHolder(View textView) {
            super(textView);
            this.view = (ConstraintLayout)textView;
            this.textView = view.findViewById(R.id.textView);
            this.imageView = view.findViewById(R.id.imageView);
        }

        private void bindView(WikipediaPage touristItem) {
            textView.setText(touristItem.getTitle());
            if(touristItem.getThumbnail() != null && !TextUtils.isEmpty(touristItem.getThumbnail().getSource())){
                Glide.with(view)
                        .load(touristItem.getThumbnail().getSource())
                        .apply(RequestOptions.circleCropTransform()
                                .error(android.R.drawable.stat_notify_error)
                                .placeholder(android.R.drawable.btn_star_big_on))
                        .into(this.imageView);
            } else {
                Glide.with(view)
                        .load(R.drawable.error)
                        .into(this.imageView);
            }
        }
    }

}
