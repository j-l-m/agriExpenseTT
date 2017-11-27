package uwi.dcit.AgriExpenseTT.newmodels;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;


/**
 * List Factory class.
 * Returns an instance of a specific ListGenerator type based on the parameters provided
 */

public class ListFactory {

    /*
    The factory method
    @param context is the context of the current activity. This is needed by ListGenerators that use database queries
    @param listType is the type of list be requested.
    @param subType is the secondary type of list specified by listType. e.g. a list of planting material resources is requested
        The listType would be 'resources' and the subType would be 'planting material'
    */

    public static ListGenerator getListGenerator(Context context, String listType, String subType){

        Log.d("FACTORY",  listType + " | " + subType);
        if (subType == null) subType = listType;

        if (listType.equals("category")){
            return new CategoryList();
        }

        if (listType.equals("measurement")){
            return new MeasurementList();
        }

        if (listType.equals("land")){
            return new LandUnitList();
        }

        if (listType.equals("quantifier")){
            return quantifierList(subType);
        }

        if (listType.equals("resource")){
            return new ResourceList(context, subType);
        }

        if (isCategory(listType)){
            return new ResourceList(context, subType);
        }

        return null;
    }


    /*
        The quantifier used depends on the category of the resource
    */
    private static ListGenerator quantifierList(String category){

        Log.d("FACTORY", "quantifierList: "+category);
        if (category.equals(DHelper.cat_plantingMaterial)){
            return new PlantQuantifierList();
        }

        if (category.equals(DHelper.cat_fertilizer)){
            return new FertilizerQuantifierList();
        }

        if (category.equals(DHelper.cat_soilAmendment)){
            return new SoilAmmendList();
        }

        if (category.equals(DHelper.cat_chemical)){
            return new ChemicalQuantifierList();
        }

        return null;
    }


    /*
        isCategory() method determines if a String is a category
     */
    private static boolean isCategory(String category){
        CategoryList categoryList = new CategoryList();
        HashMap<String, String> hashMap = categoryList.getCategoryMap();
        return (hashMap.get(category) != null);
    }

}
