package uwi.dcit.AgriExpenseTT.newmodels;

import java.util.ArrayList;

/**
 * Created by Jason on 11/23/2017.
 */

public class LandUnitList extends ListGenerator {

    private static final String acre = "acre";
    private static final String hectare = "hectare";
    private static final String bed = "bed";

    private ArrayList<String> list;

    @Override
    public ArrayList<String> generateList() {
        list = new ArrayList<String>();
        list.add(acre);
        list.add(hectare);
        list.add(bed);
        return list;
    }
}
