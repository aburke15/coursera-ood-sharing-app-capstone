package com.example.sharingapp;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

import java.util.List;

/**
 * Displays a list of all "Borrowed" items
 */
public class BorrowedItemsFragment extends ItemsFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater,container, savedInstanceState);
        super.setVariables(R.layout.borrowed_items_fragment, R.id.my_borrowed_items);
        super.setAdapter(BorrowedItemsFragment.this);

        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Item> filterItems() {
        String status = "Borrowed";
        return item_list.filterItemsByStatus(status);
    }
}
