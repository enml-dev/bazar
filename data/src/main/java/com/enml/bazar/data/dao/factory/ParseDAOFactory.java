package com.enml.bazar.data.dao.factory;

import android.content.Context;

import com.enml.bazar.data.Constants;
import com.enml.bazar.data.GetCallback;
import com.enml.bazar.data.dao.interfaces.IItemDAO;
import com.enml.bazar.data.dao.interfaces.ISubCategoryDAO;
import com.enml.bazar.data.dao.parse.CategoryDAO;
import com.enml.bazar.data.dao.parse.ItemDAO;
import com.enml.bazar.data.dao.parse.SubCategoryDAO;
import com.enml.bazar.data.model.interfaces.ICategory;
import com.enml.bazar.data.model.interfaces.ISubCategory;
import com.enml.bazar.data.model.parse.Category;
import com.enml.bazar.data.model.parse.Item;
import com.enml.bazar.data.model.parse.ItemType;
import com.enml.bazar.data.model.parse.Municipality;
import com.enml.bazar.data.model.parse.Province;
import com.enml.bazar.data.model.parse.SubCategory;
import com.enml.bazar.data.utils.DbHelper;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.List;

public class ParseDAOFactory extends DAOFactory {
    private final CategoryDAO categoryDAO;
    private final SubCategoryDAO subCategoryDAO;
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

        categoryDAO = new CategoryDAO();

        subCategoryDAO = new SubCategoryDAO();

        if (DbHelper.get().isFirstTime()) {
            init();
        }
    }

    private void init() {
        categoryDAO.loadCategories();

        subCategoryDAO.loadSubCategories();
    }

    public DAOFactoryType getDaoFactoryType() {
        return daoFactoryType;
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    @Override
    public ISubCategoryDAO getSubCategoryDAO() {
        return subCategoryDAO;
    }

    @Override
    public IItemDAO getItemDAO() {
        return new ItemDAO();
    }
}
