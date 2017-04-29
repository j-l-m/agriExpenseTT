package uwi.dcit.AgriExpenseTT.helpers;


import android.content.Context;
import android.content.res.Resources;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import uwi.dcit.AgriExpenseTT.R;

public class CropDataHelper {

	public HashMap<String,Integer> resources;
	public CropDataHelper(){
		super();
		resources = new HashMap<String, Integer>();
		populateResources();
	}
	private static JSONObject getCropsJSON(Context context) throws IOException {
		InputStream is = context.getResources().openRawResource(R.raw.crops);
		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		try {
			Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
			String jsonString = writer.toString();
			return new JSONObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			is.close();
		}
		return null;
	}

	public static ArrayList<String> getCrops(Context context) {
		ArrayList<String> list = new ArrayList<>();
		try {
			JSONObject cropsJson = getCropsJSON(context);
			Iterator<String> i = cropsJson.keys();
			while (i.hasNext()) {
				list.add(i.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// Used in the CycleListAdapter in the FragmentViewCycles
	public static int getCropsDrawable(Context context, String cropName) {
		String name = "crop_under_rain_solid";
		try {
			JSONObject cropsJson = getCropsJSON(context);
			if (cropsJson != null && cropsJson.has(cropName)) {
				JSONObject rec = cropsJson.getJSONObject(cropName);
				if (rec.has("image")) {
					name = rec.getString("image");
				}
			}
			Resources resources = context.getResources();
			final int id = resources.getIdentifier(name,"drawable",context.getPackageName());
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	private void populateResources(){
		resources.put("ANISE SEED",R.drawable.anise_seed);
		resources.put("BANANA",R.drawable.banana);
//		resources.put("BASIL",R.drawable);
//		resources.put("BAY LEAF",R.drawable);
		resources.put("BEET",R.drawable.beet);
//		resources.put("BHAGI",R.drawable);
		resources.put("BORA (BODI) BEAN",R.drawable.bodi_bean);
		resources.put("BREADFRUIT",R.drawable.breadfruit);
		resources.put("BREADNUT (CHATAIGNE)",R.drawable.chataigne);
		resources.put("BROCCOLI",R.drawable.broccoli);
		resources.put("CABBAGE",R.drawable.cabbage);
		resources.put("CARAILLI",R.drawable.caraillie);
		resources.put("CARAMBOLA",R.drawable.carambola);
		resources.put("CARROTS",R.drawable.carrot);
		resources.put("CASSAVA",R.drawable.cassava);
		resources.put("CAULIFLOWER",R.drawable.cauliflower);
		resources.put("CELERY",R.drawable.celery);
		resources.put("CHERRY",R.drawable.bajan_cherry);
		resources.put("CHIVE",R.drawable.chive);
//		resources.put("CHOI SUM (CHINESE CABBAGE)",R.drawable);
		resources.put("CHRISTOPHENE",R.drawable.christophene);
		resources.put("COCOA",R.drawable.cocoa);
		resources.put("COCONUT",R.drawable.coconut);
		resources.put("CORN",R.drawable.corn);
//		resources.put("COWPEA (GUB GUB)",R.drawable);
 		resources.put("CUCUMBER",R.drawable.cucumber);
		resources.put("CULANTRO (SHADON BENI / BANDANIA)",R.drawable.shadon_beni);
//		resources.put("CURRY LEAF",R.drawable);
//		resources.put("CUSH CUSH",R.drawable);
 		resources.put("DASHEEN",R.drawable.dasheen);
		resources.put("DASHEEN BUSH",R.drawable.dasheen_bush);
//		resources.put("DILL",R.drawable);
		resources.put("EDDOES",R.drawable.eddoe);
 		resources.put("EGGPLANT",R.drawable.eggplant);
		resources.put("ESCALLION",R.drawable.escallion);
//		resources.put("FENNEL",R.drawable);
		resources.put("GINGER",R.drawable.ginger);
// 		resources.put("GOLDEN APPLE",R.drawable);
		resources.put("GRAPEFRUIT",R.drawable.grapefruit);
		resources.put("GREEN FIG",R.drawable.banana);
//		resources.put("HORSERADISH",R.drawable);
 		resources.put("HOT PEPPER",R.drawable.hot_pepper);
//		resources.put("JACK BEAN",R.drawable);
//		resources.put("JHINGI",R.drawable);
//		resources.put("LAUKI",R.drawable);
// 		resources.put("LEMON",R.drawable);
//		resources.put("LEREN (TOPI TAMBU)",R.drawable);

		resources.put("LETTUCE",R.drawable.lettuce);
		resources.put("LIME",R.drawable.lime);
 		resources.put("MAIZE (CORN)",R.drawable.corn);
		resources.put("MANGO",R.drawable.mango);
//		resources.put("MARJORAM",R.drawable);
		resources.put("MINT",R.drawable.mint);
 		resources.put("NUTMEG",R.drawable.nutmeg);
		resources.put("OCHRO",R.drawable.ochro);
//		resources.put("ONIONS",R.drawable);
		resources.put("ORANGES",R.drawable.orange);
// 		resources.put("OREGANO",R.drawable);
//		resources.put("PAKCHOY",R.drawable);
//		resources.put("PARSLEY",R.drawable);
		resources.put("PAW PAW",R.drawable.paw_paw);
 		resources.put("PEANUTS",R.drawable.peanuts);
		resources.put("PIGEON PEAS",R.drawable.pigeon_pea);
		resources.put("PIMENTO PEPPER",R.drawable.pimento);
		resources.put("PINEAPPLE",R.drawable.pineapple);
 		resources.put("PLANTAIN",R.drawable.plantain);
		resources.put("PORTUGAL",R.drawable.portugal);
		resources.put("PUMPKIN",R.drawable.pumpkin);
//		resources.put("RADISH (MOORAI)",R.drawable);
		resources.put("RICE",R.drawable.rice);
//		resources.put("ROSEMARY",R.drawable);
//		resources.put("SAIJAN",R.drawable);
//		resources.put("SATPUTIYA (LOOFAH)",R.drawable);
		resources.put("SEIM",R.drawable.seim_bean);
		resources.put("SORREL",R.drawable.sorrel);
		resources.put("SOYABEAN",R.drawable.soybean);
		resources.put("SQUASH",R.drawable.squash);
//		resources.put("STRING BEAN",R.drawable);
//		resources.put("SUGARCANE",R.drawable);
		resources.put("SWEET PEPPER",R.drawable.sweet_pepper);
		resources.put("SWEET POTATO",R.drawable.sweet_potato);
//		resources.put("TANGERINE",R.drawable);
//		resources.put("TANNIA",R.drawable);
//		resources.put("TARRAGON",R.drawable);
		resources.put("THYME - FINE",R.drawable.thyme);
		resources.put("THYME - FRENCH",R.drawable.thyme);
		resources.put("THYME - SPANISH",R.drawable.thyme);
		resources.put("TOMATO",R.drawable.tomato);
		resources.put("TUMERIC (SAFFRON)",R.drawable.turmeric);
		resources.put("VINE SPINACH (POI BHAGI)",R.drawable.spinach);
		resources.put("WATERCRESS",R.drawable.watercress);
		resources.put("WATERMELON",R.drawable.watermelon);
//		resources.put("WING BEAN",R.drawable);
 		resources.put("YAM",R.drawable.yam);

	}
	public Integer getResourceId(String key){
		if(resources.containsKey(key))
			return resources.get(key);
		return R.drawable.plant;
	}
}
