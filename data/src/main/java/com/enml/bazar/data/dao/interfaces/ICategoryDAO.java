package com.enml.bazar.data.dao.interfaces;

import com.enml.bazar.data.GetCallback;
import com.enml.bazar.data.model.interfaces.ICategory;

import java.util.List;

public interface ICategoryDAO {
    void getCategories(GetCallback<List<ICategory>> callback);
}
