package com.example.sharingapp;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

import java.util.List;

/**
 * Displays a list of all "Available" items
 */
public class AvailableItemsFragment extends ItemsFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater,container, savedInstanceState);
        super.setVariables(R.layout.available_items_fragment, R.id.my_available_items);
        super.setAdapter(AvailableItemsFragment.this);

        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Item> filterItems() {
        String status = "Available";
        return item_list.filterItemsByStatus(status);
    }
}
