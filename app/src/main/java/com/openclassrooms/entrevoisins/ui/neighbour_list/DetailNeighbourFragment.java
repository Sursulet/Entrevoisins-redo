package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.databinding.FragmentDetailNeighbourBinding;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailNeighbourFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailNeighbourFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentDetailNeighbourBinding mBinding;
    private Long mArg;
    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    public DetailNeighbourFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailNeighbourFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailNeighbourFragment newInstance(String param1, String param2) {
        DetailNeighbourFragment fragment = new DetailNeighbourFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mArg = DetailNeighbourFragmentArgs.fromBundle(getArguments()).getId();
        }
        mApiService = DI.getNeighbourApiService();
        mNeighbour = mApiService.getNeighbours().stream().filter(neighbour -> mArg.equals(neighbour.getId())).findFirst().orElse(null);
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
        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(mBinding.detailAvatar);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}