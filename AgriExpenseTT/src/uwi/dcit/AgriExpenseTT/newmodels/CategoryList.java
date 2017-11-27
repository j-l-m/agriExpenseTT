package uwi.dcit.AgriExpenseTT.newmodels;

import java.util.ArrayList;
import java.util.HashMap;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

/**
 * Category List class.
 */

public class CategoryList extends ListGenerator {


    private final String plantMaterial = DHelper.cat_plantingMaterial;
    private final String chemical = DHelper.cat_chemical;
    private final String fertilizer = DHelper.cat_fertilizer;
    private final String soilAmendment = DHelper.cat_soilAmendment;
    private final String labour = DHelper.cat_labour;
    private final String other = DHelper.cat_other;



    private ArrayList<String> list;

    @Override
    public ArrayList<String> generateList() {
        list = new ArrayList<String>();
        list.add(plantMaterial);
        list.add(chemical);
        list.add(fertilizer);
        list.add(soilAmendment);
        list.add(other);
        return list;
    }


    private HashMap<String, String> genMap(){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(plantMaterial, plantMaterial);
        hashMap.put(chemical, chemical);
        hashMap.put(fertilizer, fertilizer);
        hashMap.put(soilAmendment, soilAmendment);
        hashMap.put(labour, labour);
        hashMap.put(other, other);

        return hashMap;
    }


    public HashMap<String, String> getCategoryMap(){
        return genMap();
    }
}
