package uwi.dcit.AgriExpenseTT.newmodels;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

/**
 * Created by Jason on 11/23/2017.
 */

public class FertilizerQuantifierList extends ListGenerator {

    private static final String g = DHelper.qtf_fertilizer_g;
    private static final String kg = DHelper.qtf_fertilizer_kg;
    private static final String lb = DHelper.qtf_fertilizer_lb;
    private static final String bag = DHelper.qtf_fertilizer_bag;

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
