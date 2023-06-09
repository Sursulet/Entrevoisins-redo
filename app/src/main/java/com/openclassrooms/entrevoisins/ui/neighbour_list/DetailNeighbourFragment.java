package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.databinding.FragmentDetailNeighbourBinding;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class DetailNeighbourFragment extends Fragment {

    private FragmentDetailNeighbourBinding mBinding;
    private Long mArg;
    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArg = DetailNeighbourFragmentArgs
                    .fromBundle(getArguments())
                    .getId();
        }
        mApiService = DI.getNeighbourApiService();
        mNeighbour = mApiService.getNeighbour(mArg);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentDetailNeighbourBinding.inflate(getLayoutInflater(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.toolbar.setTitle(mNeighbour.getName());
        mBinding.toolbar.setNavigationOnClickListener(vw -> NavHostFragment.findNavController(DetailNeighbourFragment.this).navigateUp());

        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(mBinding.detailAvatar);
        mBinding.detailName.setText(mNeighbour.getName());
        mBinding.detailAddress.setText(mNeighbour.getAddress());
        mBinding.detailPhone.setText(mNeighbour.getPhoneNumber());
        mBinding.detailSocialNetwork.setText(String.format("www.facebook.fr/%s", mNeighbour.getName().toLowerCase()));
        mBinding.detailAboutMe.setText(mNeighbour.getAboutMe());

        mBinding.favorite.setImageResource(mNeighbour.isFavorite() ? R.drawable.ic_star_white_24dp : R.drawable.ic_star_border_white_24dp);
        mBinding.favorite.setOnClickListener(v -> {
            mApiService.changeStatus(mNeighbour);
            mBinding.favorite.setImageResource(mNeighbour.isFavorite() ? R.drawable.ic_star_white_24dp : R.drawable.ic_star_border_white_24dp);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}