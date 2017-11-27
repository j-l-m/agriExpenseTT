package uwi.dcit.AgriExpenseTT.newmodels;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

/**
 * List of Planting material quantifiers
 */

public class PlantQuantifierList extends ListGenerator {

    private final String seed = DHelper.qtf_plantingMaterial_seed;
    private final String seedling = DHelper.qtf_plantingMaterial_seedling;
    private final String stick = DHelper.qtf_plantingMaterial_stick;
    private final String tubes = DHelper.qtf_plantingMaterial_tubes;
    private final String heads = DHelper.qtf_plantingMaterial_heads;
    private final String slip = DHelper.qtf_plantingMaterial_slip;

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
