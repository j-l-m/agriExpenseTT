package uwi.dcit.AgriExpenseTT.newmodels;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

/**
 * Soil Amendment List
 */

public class SoilAmmendList extends ListGenerator {

    private final String truck = DHelper.qtf_soilAmendment_truck;
    private final String bag = DHelper.qtf_soilAmendment_bag;

    private ArrayList<String> list;

    @Override
    public ArrayList<String> generateList() {
        list = new ArrayList<String>();
        list.add(truck);
        list.add(bag);
        return list;
    }
}
