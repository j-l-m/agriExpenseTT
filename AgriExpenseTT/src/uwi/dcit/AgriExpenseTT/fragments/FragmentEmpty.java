package uwi.dcit.AgriExpenseTT.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import uwi.dcit.AgriExpenseTT.HireLabour;
import uwi.dcit.AgriExpenseTT.NewCycle;
import uwi.dcit.AgriExpenseTT.NewPurchase;
import uwi.dcit.AgriExpenseTT.R;
import uwi.dcit.AgriExpenseTT.helpers.GAnalyticsHelper;

public class FragmentEmpty extends Fragment{
    private static final String TAG = "FragmentEmpty";
    protected boolean isLabour = false;
    View view;
    private String type;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Retrieve Empty Fragment Type
        type = getArguments().getString("type");
        if (type != null)Log.d(TAG, "Received type: " + type + " from parent");
        else Log.d(TAG, "No Type received");

        // Retrieve category (only applicable to purchases atm)
        String category = getArguments().getString("category");
        if (category != null)Log.d(TAG, "Received category: " + category + "from parent");
        else Log.d(TAG, "No category received");

        // Retrieve the UI elements from the view of the fragment
        view = inflater.inflate(R.layout.fragment_empty_resourcelist, container, false);
		TextView desc = (TextView)view.findViewById(R.id.tv_empty_desc);
        final Button button = (Button) view.findViewById(R.id.AddResButton);

        // Setting up the text for the page depending on the type
        switch (type) {
            case "purchase":
                if (category == null) {
                    desc.setText(R.string.add_purchase_instruct);
                    button.setText(R.string.add_purchase);
                } else {
                    desc.setText(String.format(getString(R.string.add_purchase_cat_instruct), category));
                    button.setText(R.string.add_purchase);
                }
                break;
            case "cycle":
                desc.setText(R.string.add_cycle_instruct);
                button.setText(R.string.add_cycle);
                break;
            case "labour":
                desc.setText(R.string.add_labour_instruct);
                button.setText(R.string.add_labour);
                this.isLabour = true;
                break;
            default:
                button.setText(R.string.add_purchase);
                break;
        }

        // Assign button behaviour based on type received
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                addResource();
                }
            });
		return view;
	}


    private void addResource() {// Adds functionality to the add cycle or Add purchase button
        Intent intent = null;
        if (type.equals("cycle")) {
            intent = new Intent(getActivity().getApplicationContext(), NewCycle.class);
        } else if (type.equals("purchase")) {
            intent = new Intent(getActivity().getApplicationContext(), NewPurchase.class);
        }else if (type.equals(("labour"))){
            intent = new Intent(getActivity().getApplicationContext(), HireLabour.class);
        }
        startActivity(intent);
    }

}
