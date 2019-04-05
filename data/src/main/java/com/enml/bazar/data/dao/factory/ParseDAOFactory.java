package com.enml.bazar.data.dao.factory;

import android.content.Context;

import com.enml.bazar.data.Constants;
import com.enml.bazar.data.dao.interfaces.IItemDAO;
import com.enml.bazar.data.dao.interfaces.ISubCategoryDAO;
import com.enml.bazar.data.dao.parse.CategoryDAO;
import com.enml.bazar.data.dao.parse.ItemDAO;
import com.enml.bazar.data.dao.parse.SubCategoryDAO;
import com.enml.bazar.data.model.parse.Category;
import com.enml.bazar.data.model.parse.Item;
import com.enml.bazar.data.model.parse.ItemType;
import com.enml.bazar.data.model.parse.Municipality;
import com.enml.bazar.data.model.parse.Province;
import com.enml.bazar.data.model.parse.SubCategory;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseDAOFactory extends DAOFactory {
    private DAOFactoryType daoFactoryType = DAOFactoryType.PARSE;

    public ParseDAOFactory(Context context){
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.enableLocalDatastore(context);

        ParseObject.registerSubclass(Item.class);
        ParseObject.registerSubclass(Category.class);
        ParseObject.registerSubclass(SubCategory.class);
        ParseObject.registerSubclass(ItemType.class);
        ParseObject.registerSubclass(Province.class);
        ParseObject.registerSubclass(Municipality.class);

        // Initialize the access to the Parse server.
        Parse.initialize(new Parse.Configuration.Builder(context).applicationId(Constants.PARSE_APP_ID)
                        .clientKey("")
                        .server(Constants.PARSE_SERVER_URL)
                        .enableLocalDataStore()// The trailing slash is important.
                        .build());
    }

    public DAOFactoryType getDaoFactoryType() {
        return daoFactoryType;
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return new CategoryDAO();
    }

    @Override
    public ISubCategoryDAO getSubCategoryDAO() {
        return new SubCategoryDAO();
    }

    @Override
    public IItemDAO getItemDAO() {
        return new ItemDAO();
    }
}
