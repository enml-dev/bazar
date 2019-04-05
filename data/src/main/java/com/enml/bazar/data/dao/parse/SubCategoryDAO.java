package com.enml.bazar.data.dao.parse;

import com.enml.bazar.data.GetCallback;
import com.enml.bazar.data.dao.interfaces.ISubCategoryDAO;
import com.enml.bazar.data.model.interfaces.ICategory;
import com.enml.bazar.data.model.interfaces.ISubCategory;
import com.enml.bazar.data.model.parse.SubCategory;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class SubCategoryDAO implements ISubCategoryDAO {

    @Override
    public void getSubCategories(ICategory category, final GetCallback<List<ISubCategory>> callback) {
        ParseQuery<SubCategory> query = ParseQuery.getQuery(SubCategory.class);
        query.whereEqualTo("category", category);

        query.findInBackground(new com.parse.FindCallback<SubCategory>() {
            @Override
            public void done(List subCategoryList, ParseException e) {
                if (e != null) {
                    if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {
                        callback.done(subCategoryList, null);
                    } else {
                        callback.done(subCategoryList, e);
                    }
                } else {
                    callback.done(subCategoryList, e);
                }
            }
        });
    }
}
