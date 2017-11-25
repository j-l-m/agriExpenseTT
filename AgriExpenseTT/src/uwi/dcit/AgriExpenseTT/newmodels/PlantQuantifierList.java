package uwi.dcit.AgriExpenseTT.newmodels;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

/**
 * Created by Jason on 11/23/2017.
 */

public class PlantQuantifierList extends ListGenerator {

    private static final String seed = DHelper.qtf_plantingMaterial_seed;
    private static final String seedling = DHelper.qtf_plantingMaterial_seedling;
    private static final String stick = DHelper.qtf_plantingMaterial_stick;
    private static final String tubes = DHelper.qtf_plantingMaterial_tubes;
    private static final String heads = DHelper.qtf_plantingMaterial_heads;
    private static final String slip = DHelper.qtf_plantingMaterial_slip;

    private ArrayList<String> list;

    @Override
    public ArrayList<String> generateList() {
        list = new ArrayList<String>();
        list.add(seed);
        list.add(seedling);
        list.add(slip);
        list.add(heads);
        list.add(tubes);
        list.add(stick);
        return list;
    }
}
