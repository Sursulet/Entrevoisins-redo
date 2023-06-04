package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.databinding.FragmentNeighbourBinding;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;
    private OnClickListener mListener;

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }

    interface OnClickListener {
        void onItemClick(Neighbour neighbour);

        void onDelete(Neighbour neighbour);
    }

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentNeighbourBinding binding = FragmentNeighbourBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, mListener);
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
        private FragmentNeighbourBinding binding;
        private OnClickListener mListener;

        public ViewHolder(FragmentNeighbourBinding binding, OnClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.mListener = listener;
        }

        public void bind(Neighbour neighbour) {
            binding.itemListName.setText(neighbour.getName());
            Glide.with(binding.itemListAvatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding.itemListAvatar);
            binding.itemListDeleteButton.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onDelete(neighbour);
                }
            });

            binding.getRoot().setOnClickListener(view -> {
                if (mListener != null) {
                    mListener.onItemClick(neighbour);
                }
            });
        }
    }
}
