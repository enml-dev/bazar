package com.enml.bazar.data.dao;

import android.util.Log;

import com.enml.bazar.data.model.Item;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class ItemDAO {

    public ItemDAO() {

    }

    public void getItems () {
        ParseQuery<Item> query = ParseQuery.getQuery(Item.class);
        query.findInBackground(new FindCallback<Item>() {
            @Override
            public void done(List<Item> objects, ParseException e) {
                for (Item object : objects) {
                    Log.d("Item name: " , object.getTitle());
                }
            }
        });
    }
}
