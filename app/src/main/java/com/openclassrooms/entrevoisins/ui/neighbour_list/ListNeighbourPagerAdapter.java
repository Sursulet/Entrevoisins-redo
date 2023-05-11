package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class ListNeighbourPagerAdapter extends FragmentStateAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm, Lifecycle lifecycle) {
        super(fm,lifecycle);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return NeighbourFragment.newInstance();
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getItemCount() { return 1; }
}