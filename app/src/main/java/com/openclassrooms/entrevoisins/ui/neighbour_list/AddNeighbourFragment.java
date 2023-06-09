package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.databinding.FragmentAddNeighbourBinding;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class AddNeighbourFragment extends Fragment {

    private FragmentAddNeighbourBinding mBinding;

    private NeighbourApiService mApiService;
    private String mNeighbourImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentAddNeighbourBinding.inflate(getLayoutInflater(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.toolbar.setNavigationOnClickListener(v ->
                NavHostFragment.findNavController(AddNeighbourFragment.this).navigateUp());
        init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    private void init() {
        mNeighbourImage = randomImage();
        Glide.with(this).load(mNeighbourImage).placeholder(R.drawable.ic_account)
                .apply(RequestOptions.circleCropTransform()).into(mBinding.avatar);
        mBinding.nameLyt.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mBinding.create.setEnabled(s.length() > 0);
            }
        });
        mBinding.create.setOnClickListener(view -> addNeighbour());
    }

    void addNeighbour() {
        Neighbour neighbour = new Neighbour(
                System.currentTimeMillis(),
                mBinding.nameLyt.getEditText().getText().toString(),
                mNeighbourImage,
                mBinding.addressLyt.getEditText().getText().toString(),
                mBinding.phoneNumberLyt.getEditText().getText().toString(),
                mBinding.aboutMeLyt.getEditText().getText().toString()
        );
        mApiService.createNeighbour(neighbour);
        NavHostFragment.findNavController(AddNeighbourFragment.this).navigateUp();
    }

    /**
     * Generate a random image. Useful to mock image picker
     *
     * @return String
     */
    String randomImage() {
        return "https://i.pravatar.cc/150?u=" + System.currentTimeMillis();
    }
}
