package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.openclassrooms.entrevoisins.R;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    //@BindView(R.id.tabs)
    TabLayout mTabLayout;
    //@BindView(R.id.toolbar)
    Toolbar mToolbar;
    //@BindView(R.id.container)
    ViewPager2 mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        //ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager(), getLifecycle());
        mViewPager.setAdapter(mPagerAdapter);
        //mViewPager.registerOnPageChangeCallback(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> tab.setText("OBJECT" + (position + 1))).attach();

    }

    //@OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }
}
