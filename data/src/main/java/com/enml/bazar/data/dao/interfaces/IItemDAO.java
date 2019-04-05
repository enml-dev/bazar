package com.enml.bazar.data.dao.interfaces;

import com.enml.bazar.data.GetCallback;
import com.enml.bazar.data.model.interfaces.IItem;

import java.util.List;

public interface IItemDAO {
    void getItems(GetCallback<List<IItem>> callback);
}
