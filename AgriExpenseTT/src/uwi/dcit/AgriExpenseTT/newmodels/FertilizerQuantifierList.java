package uwi.dcit.AgriExpenseTT.newmodels;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

/**
 * List of Fertilizer Quantifiers
 */

public class FertilizerQuantifierList extends ListGenerator {

    private final String g = DHelper.qtf_fertilizer_g;
    private final String kg = DHelper.qtf_fertilizer_kg;
    private final String lb = DHelper.qtf_fertilizer_lb;
    private final String bag = DHelper.qtf_fertilizer_bag;

    private ArrayList<String> list;

    @Override
    public ArrayList<String> generateList() {
        list = new ArrayList<String>();
        list.add(g);
        list.add(kg);
        list.add(lb);
        list.add(bag);
        return list;
    }
}
