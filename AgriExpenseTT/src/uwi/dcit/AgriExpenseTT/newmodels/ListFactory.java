package uwi.dcit.AgriExpenseTT.newmodels;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import uwi.dcit.AgriExpenseTT.helpers.DbHelper;

/**
 * Created by Jason on 11/22/2017.
 */

public class ListFactory {

    //Context context;
    //DbHelper dbhelper;
    //SQLiteDatabase sqldb;

    /*
    The factory method
    */

    public static ListGenerator getListGenerator(Context context, String content, String category){

        //DbHelper dbh = new DbHelper(context);
        //SQLiteDatabase db = dbh.getWritableDatabase();
        Log.d("FACTORY",  content + " | " + category);

        if (content.equals("category")){
            return new CategoryList();
        }

        if (content.equals("measurement")){
            return new MeasurementList();
        }

        if (content.equals("land")){
            return new LandUnitList();
        }

        if (content.equals("quantifier")){
            return quantifierList(category);
        }

        if (content.equals("resource")){
            //return new ResourceList(db, dbh, content);
            return new ResourceList(context, category);
        }

        if (CategoryList.isCategory(content)){
            //return new ResourceList(db, dbh, content);
            return new ResourceList(context, category);
        }

        return null;
    }


    /*
        The quantifier used depends on the category passed in the bundle
    */
    private static ListGenerator quantifierList(String category){
        Log.d("FACTORY", "quantifierList: "+category);
        if (category.equals(CategoryList.plantMaterial)){
            return new PlantQuantifierList();
        }

        if (category.equals(CategoryList.fertilizer)){
            return new FertilizerQuantifierList();
        }

        if (category.equals(CategoryList.soilAmendment)){
            return new SoilAmmendList();
        }

        if (category.equals(CategoryList.chemical)){
            return new ChemicalQuantifierList();
        }


        return null;
    }

}
