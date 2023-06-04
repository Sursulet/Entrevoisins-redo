package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.openclassrooms.entrevoisins.databinding.ActivityListNeighbourBinding;

public class ListNeighbourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityListNeighbourBinding binding = ActivityListNeighbourBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
