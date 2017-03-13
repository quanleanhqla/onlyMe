package com.example.quanla.quannet.database;

import com.example.quanla.quannet.R;
import com.example.quanla.quannet.database.models.Function;

import java.util.ArrayList;

/**
 * Created by QuanLA on 3/13/2017.
 */

public class DbContext {
    private ArrayList<Function> arrayList;

    public static final DbContext instance = new DbContext();

    public ArrayList<Function> allFunctions(){
        arrayList = new ArrayList<>();

        arrayList.add(new Function("Search", R.drawable.ic_search_white_24px));
        arrayList.add(new Function("Top", R.drawable.ic_thumb_up_white_24px));
        arrayList.add(new Function("New", R.drawable.ic_fiber_new_white_24px));
        arrayList.add(new Function("Add", R.drawable.ic_add_to_queue_white_24px));

        return arrayList;
    }
}
