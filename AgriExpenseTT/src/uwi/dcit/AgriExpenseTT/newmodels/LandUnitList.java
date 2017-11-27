package uwi.dcit.AgriExpenseTT.newmodels;

import java.util.ArrayList;

/**
 * Land Units List
 */

public class LandUnitList extends ListGenerator {

    private final String acre = "acre";
    private final String hectare = "hectare";
    private final String bed = "bed";

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
