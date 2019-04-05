package com.enml.bazar.data.repositories;

import com.enml.bazar.data.GetCallback;
import com.enml.bazar.data.dao.factory.DAOFactory;
import com.enml.bazar.data.model.interfaces.ICategory;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CategoryRepository {

    private static CategoryRepository instance;

    public static CategoryRepository getInstance() {
        if (instance == null){
            instance = new CategoryRepository();
        }
        return instance;
    }

    public LiveData<Resource<List<ICategory>>> getCategories() {
        final MutableLiveData mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(Resource.loading(null));

        DAOFactory.getDAOFactory().getCategoryDAO().getCategories(new GetCallback<List<ICategory>>() {
            @Override
            public void done(List<ICategory> categoryList, Exception e) {
                mutableLiveData.setValue(Resource.success(categoryList));
            }
        });

        return mutableLiveData;
    }
}
