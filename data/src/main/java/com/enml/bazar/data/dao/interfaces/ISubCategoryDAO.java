package com.enml.bazar.data.dao.interfaces;

import com.enml.bazar.data.GetCallback;
import com.enml.bazar.data.model.interfaces.ICategory;
import com.enml.bazar.data.model.interfaces.ISubCategory;

import java.util.List;

public interface ISubCategoryDAO {
    void getSubCategories(ICategory category, GetCallback<List<ISubCategory>> callback);

    void loadSubCategories();
}
