package com.enml.bazar.data.dao.parse;

import com.enml.bazar.data.GetCallback;
import com.enml.bazar.data.dao.interfaces.IItemDAO;
import com.enml.bazar.data.model.interfaces.IItem;
import com.enml.bazar.data.model.parse.Item;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class ItemDAO implements IItemDAO {

    @Override
    public void getItems(final GetCallback<List<IItem>> callback) {
        ParseQuery<Item> query = ParseQuery.getQuery(Item.class);

        query.findInBackground(new com.parse.FindCallback<Item>() {
            @Override
            public void done(List categoryList, ParseException e) {
                if (e != null) {
                    if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {
                        callback.done(categoryList, null);
                    } else {
                        callback.done(categoryList, e);
                    }
                } else {
                    callback.done(categoryList, e);
                }
            }
        });
    }
}
