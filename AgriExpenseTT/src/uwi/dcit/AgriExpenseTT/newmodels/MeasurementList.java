package uwi.dcit.AgriExpenseTT.newmodels;

import java.util.ArrayList;

/**
 * Created by Jason on 11/23/2017.
 */

public class MeasurementList extends ListGenerator {

    private static final String lb = "Lb";
    private static final String kg = "Kg";
    private static final String bag = "Bag";
    private static final String bundle = "Bundle";
    private static final String head = "Head";
    private static final String hundred = "100's";
    private static final String bundle_5lb = "5lb Bundle";

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
