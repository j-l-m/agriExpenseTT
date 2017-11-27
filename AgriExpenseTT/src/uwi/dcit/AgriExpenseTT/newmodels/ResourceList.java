package uwi.dcit.AgriExpenseTT.newmodels;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.helpers.DbQuery;

/**
 * Resource List
 */

public class ResourceList extends ListGenerator {

    private String category;
    private ArrayList<String> list;
    private Context context;


   public ResourceList(Context context, String category){
       this.category = category;
       this.context = context;
   }


    @Override
    public ArrayList<String> generateList() {
        ArrayList <String> list = new ArrayList<>();

        DbHelper dbh = new DbHelper(this.context);
        SQLiteDatabase db = dbh.getWritableDatabase();

        list =  (ArrayList<String>) DbQuery.getResources(db, dbh, this.category, list);
        Log.d("RESOURCELIST", "size: "+ list.size());
        return list;
    }
}
