package com.enml.bazar.data.dao.factory;

import android.content.Context;

import com.enml.bazar.data.dao.interfaces.ICategoryDAO;
import com.enml.bazar.data.dao.interfaces.IItemDAO;
import com.enml.bazar.data.dao.interfaces.ISubCategoryDAO;

public abstract class DAOFactory {
    private static DAOFactory  currentUsedStorage;

    public static void init(Context context, DAOFactoryType daoFactoryType){
        if (daoFactoryType == DAOFactoryType.PARSE){
            currentUsedStorage = new ParseDAOFactory(context);
        }
    }

    public static DAOFactory getDAOFactory(){
        return currentUsedStorage;
    }

    public abstract ICategoryDAO getCategoryDAO();

    public abstract ISubCategoryDAO getSubCategoryDAO();

    public abstract IItemDAO getItemDAO();

}
