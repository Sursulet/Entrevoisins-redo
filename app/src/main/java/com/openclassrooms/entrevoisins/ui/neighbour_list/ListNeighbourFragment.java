package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.tabs.TabLayoutMediator;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.databinding.FragmentListNeighbourBinding;

public class ListNeighbourFragment extends Fragment {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_neighbour_title, R.string.tab_favorites_title};
    private FragmentListNeighbourBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentListNeighbourBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.container.setAdapter(new ListNeighbourPagerAdapter(getChildFragmentManager(), getLifecycle()));
        new TabLayoutMediator(binding.tabs,binding.container, (tab, position) -> tab.setText(TAB_TITLES[position])).attach();

        binding.addNeighbour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ListNeighbourFragment.this)
                        .navigate(R.id.action_home_to_add_neighbour);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
