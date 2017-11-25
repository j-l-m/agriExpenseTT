package uwi.dcit.AgriExpenseTT.newmodels;

import java.util.ArrayList;
import java.util.HashMap;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

/**
 * Created by Jason on 11/22/2017.
 */

public class CategoryList extends ListGenerator {


    public static final String plantMaterial = DHelper.cat_plantingMaterial;
    public static final String chemical = DHelper.cat_chemical;
    public static final String fertilizer = DHelper.cat_fertilizer;
    public static final String soilAmendment = DHelper.cat_soilAmendment;
    public static final String labour = DHelper.cat_labour;
    public static final String other = DHelper.cat_other;



    private ArrayList<String> list;

    @Override
    public ArrayList<String> generateList() {
        list = new ArrayList<String>();
        list.add(plantMaterial);
        list.add(chemical);
        list.add(fertilizer);
        list.add(soilAmendment);
       // list.add(labour);
        list.add(other);
        return list;
    }


    private static HashMap<String, String> genMap(){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(plantMaterial, plantMaterial);
        hashMap.put(chemical, chemical);
        hashMap.put(fertilizer, fertilizer);
        hashMap.put(soilAmendment, soilAmendment);
        hashMap.put(labour, labour);
        hashMap.put(other, other);

        return hashMap;
    }

    public static boolean isCategory(String category){
        HashMap<String, String> hashMap = genMap();
        return (hashMap.get(category) != null);
    }

    public static HashMap<String, String> getCategoryMap(){
        return genMap();
    }
}
