package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.databinding.ActivityListNeighbourBinding;

public class ListNeighbourActivity extends AppCompatActivity {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_neighbour_title, R.string.tab_favorites_title};

    // UI Components
    private ActivityListNeighbourBinding binding;
    TabLayout mTabLayout;
    Toolbar mToolbar;
    ViewPager2 mViewPager;
    FloatingActionButton mFAB;

    ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListNeighbourBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mToolbar = binding.toolbar;
        mTabLayout = binding.tabs;
        mViewPager = binding.container;
        mFAB = binding.addNeighbour;

        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager(), getLifecycle());
        mViewPager.setAdapter(mPagerAdapter);
        //mViewPager.registerOnPageChangeCallback(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> tab.setText(TAB_TITLES[position])).attach();

        mFAB.setOnClickListener(view -> addNeighbour());
    }

    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }
}
