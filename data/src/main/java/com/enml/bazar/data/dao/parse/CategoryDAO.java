package com.enml.bazar.data.dao.parse;

import android.util.Log;

import com.enml.bazar.data.GetCallback;
import com.enml.bazar.data.dao.interfaces.ICategoryDAO;
import com.enml.bazar.data.model.interfaces.ICategory;
import com.enml.bazar.data.model.parse.Category;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class CategoryDAO implements ICategoryDAO {

    @Override
    public void getCategories(final GetCallback<List<ICategory>> callback) {
        Log.e("+++++++", String.valueOf(ParseUser.getCurrentUser()));
        ParseQuery<Category> query = ParseQuery.getQuery(Category.class);

        query.findInBackground(new com.parse.FindCallback<Category>() {
            @Override
            public void done(List categoryList, ParseException e) {
                if (e != null) {
                    if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {
                        callback.done(categoryList, null);
                    } else {
                        callback.done(categoryList, e);
                    }
                    e.printStackTrace();
                } else {
                    Log.e("********", categoryList.toString());
                    callback.done(categoryList, e);
                }
            }
        });
    }
}
