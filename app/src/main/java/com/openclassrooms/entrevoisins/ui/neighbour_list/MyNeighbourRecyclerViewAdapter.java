package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.databinding.FragmentNeighbourBinding;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentNeighbourBinding binding = FragmentNeighbourBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.bind(neighbour);
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public FragmentNeighbourBinding binding;

        public ViewHolder(FragmentNeighbourBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Neighbour neighbour) {
            binding.itemListName.setText(neighbour.getName());
            Glide.with(binding.itemListAvatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding.itemListAvatar);
            binding.itemListDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                }
            });
        }
    }
}
