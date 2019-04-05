package com.enml.bazar.data.viewmodel;

import com.enml.bazar.data.model.interfaces.ICategory;
import com.enml.bazar.data.repositories.CategoryRepository;
import com.enml.bazar.data.repositories.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {
    public CategoryViewModel(){

    }

    public LiveData<Resource<List<ICategory>>> getCategories() {
        return CategoryRepository.getInstance().getCategories();
    }
}
