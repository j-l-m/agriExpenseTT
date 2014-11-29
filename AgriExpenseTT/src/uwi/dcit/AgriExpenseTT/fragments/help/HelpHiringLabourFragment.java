package uwi.dcit.AgriExpenseTT.fragments.help;

import uwi.dcit.AgriExpenseTT.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HelpHiringLabourFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_help_article_view_layout5, container, false);
		
		TextView txtHeading = (TextView)view.findViewById(R.id.article_heading);
		txtHeading.setText("Hire Labour");
		
		ImageView imgView1 =(ImageView)view.findViewById(R.id.article_image);
		imgView1.setImageDrawable(getResources().getDrawable(R.drawable.help_hire_labour));
		
		TextView txt1 = (TextView)view.findViewById(R.id.article_text);
		txt1.setText(getResources().getString(R.string.help_hiring_labour_1));
		
		ImageView imgView2 =(ImageView)view.findViewById(R.id.article_image_2);
		imgView2.setImageDrawable(getResources().getDrawable(R.drawable.help_hire_labour_timeframe));
		
		TextView txt2 = (TextView)view.findViewById(R.id.article_text_2);
		txt2.setText(getResources().getString(R.string.help_hiring_labour_3));
		
		ImageView imgView3 =(ImageView)view.findViewById(R.id.article_image_3);
		imgView3.setImageDrawable(getResources().getDrawable(R.drawable.help_hire_labour_cropcycle));
		
		TextView txt3 = (TextView)view.findViewById(R.id.article_text_3);
		txt3.setText(getResources().getString(R.string.help_hiring_labour_3));
		
		ImageView imgView4 =(ImageView)view.findViewById(R.id.article_image_4);
		imgView4.setImageDrawable(getResources().getDrawable(R.drawable.help_hire_labour_time));
		
		TextView txt4 = (TextView)view.findViewById(R.id.article_text_4);
		txt4.setText(getResources().getString(R.string.help_hiring_labour_4));
		
		ImageView imgView5 =(ImageView)view.findViewById(R.id.article_image_5);
		imgView5.setImageDrawable(getResources().getDrawable(R.drawable.help_hire_labour_enterdetails));
		
		TextView txt5 = (TextView)view.findViewById(R.id.article_text_5);
		txt5.setText(getResources().getString(R.string.help_hiring_labour_5));
		
		return view;
	}
}