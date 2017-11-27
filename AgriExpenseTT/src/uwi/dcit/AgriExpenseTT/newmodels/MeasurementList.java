package uwi.dcit.AgriExpenseTT.newmodels;

import java.util.ArrayList;

/**
 * ListGenerator for list of measurement units
 */

public class MeasurementList extends ListGenerator {

    private final String lb = "Lb";
    private final String kg = "Kg";
    private final String bag = "Bag";
    private final String bundle = "Bundle";
    private final String head = "Head";
    private final String hundred = "100's";
    private final String bundle_5lb = "5lb Bundle";

    private ArrayList<String> list;

    @Override
    public ArrayList<String> generateList() {
        list = new ArrayList<String>();
        list.add(lb);
        list.add(kg);
        list.add(bag);
        list.add(bundle);
        list.add(head);
        list.add(hundred);
        list.add(bundle_5lb);
        return list;
    }
}
