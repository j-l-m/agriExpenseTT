package uwi.dcit.AgriExpenseTT.newmodels;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

/**
 * Created by Jason on 11/23/2017.
 */

public class ChemicalQuantifierList extends ListGenerator {

    private static final String ml = DHelper.qtf_chemical_ml;
    private static final String L = DHelper.qtf_chemical_L;
    private static final String oz = DHelper.qtf_chemical_oz;
    private static final String g = DHelper.qtf_chemical_g;
    private static final String kg = DHelper.qtf_chemical_kg;

    private ArrayList<String> list;

    @Override
    public ArrayList<String> generateList() {
        list = new ArrayList<String>();
        list.add(ml);
        list.add(L);
        list.add(oz);
        list.add(g);
        list.add(kg);
        return list;
    }
}
