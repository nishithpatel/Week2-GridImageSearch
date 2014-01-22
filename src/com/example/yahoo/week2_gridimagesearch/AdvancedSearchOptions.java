package com.example.yahoo.week2_gridimagesearch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdvancedSearchOptions extends Activity {
	private Spinner spImgSizeOptions;
	private ArrayList<String> imgSizeOptions;
	private ArrayAdapter<String> imgSizeOptionsAdapter;
	
	private Spinner spColorFilterOptions;
	private ArrayList<String> colorFilterOptions;
	private ArrayAdapter<String> colorFilterOptionsAdapter;
	
	private Spinner spImgTypeOptions;
	private ArrayList<String> imgTypeOptions;
	private ArrayAdapter<String> imgTypeOptionsAdapter;
	
	private EditText etSiteFilter;
	
	private ArrayList<String> savedOptions;
	private Button btnSave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advanced_search_options);
		
		spImgSizeOptions = (Spinner) findViewById(R.id.spImgSizeOptions);
		spColorFilterOptions = (Spinner) findViewById(R.id.spColorFilterOptions);
		spImgTypeOptions = (Spinner) findViewById(R.id.spImgTypeOptions);
		btnSave = (Button) findViewById(R.id.btnSave);
		etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
		savedOptions = new ArrayList<String>();
		
		addImgSizeOptions();
		addColorFilterOptions();
		addImgTypeOptions();
		
		/*
		readItems();
		
		int imgSizeSpinnerPos = imgSizeOptionsAdapter.getPosition(savedOptions.get(0));
		int colorFilterSpinnerPos = colorFilterOptionsAdapter.getPosition(savedOptions.get(1));
		int imgTypeSpinnerPos = imgTypeOptionsAdapter.getPosition(savedOptions.get(2));
		

		//set the default according to value
		spImgSizeOptions.setSelection(imgSizeSpinnerPos);
		spColorFilterOptions.setSelection(colorFilterSpinnerPos);
		spImgTypeOptions.setSelection(imgTypeSpinnerPos);
		etSiteFilter.setText(savedOptions.get(3));
		
		savedOptions.clear();
		*/
	}
	
	public void addImgSizeOptions() {
		//spImgSizeOptions = (Spinner) findViewById(R.id.spImgSizeOptions);
		imgSizeOptions = new ArrayList<String>();		
		imgSizeOptions.add("Small");
		imgSizeOptions.add("Medium");
		imgSizeOptions.add("Large");
		imgSizeOptions.add("Extra-Large");
		imgSizeOptionsAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, imgSizeOptions);
		spImgSizeOptions.setAdapter(imgSizeOptionsAdapter);

	}
	
	public void addColorFilterOptions() {
		//spColorFilterOptions = (Spinner) findViewById(R.id.spColorFilterOptions);
		colorFilterOptions = new ArrayList<String>();		
		colorFilterOptions.add("Black");
		colorFilterOptions.add("Blue");
		colorFilterOptions.add("Brown");
		colorFilterOptions.add("Grey");
		colorFilterOptions.add("Green");
		colorFilterOptions.add("Red");
		colorFilterOptionsAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, colorFilterOptions);
		spColorFilterOptions.setAdapter(colorFilterOptionsAdapter);

	}
	
	public void addImgTypeOptions() {
		//spImgTypeOptions = (Spinner) findViewById(R.id.spImgTypeOptions);
		imgTypeOptions = new ArrayList<String>();		
		imgTypeOptions.add("Faces");
		imgTypeOptions.add("Photo");
		imgTypeOptions.add("Clip Art");
		imgTypeOptions.add("Line Art");
		imgTypeOptionsAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, imgTypeOptions);
		spImgTypeOptions.setAdapter(imgTypeOptionsAdapter);

	}
	
	public void onSave(View v) {
		
		Toast.makeText(AdvancedSearchOptions.this, "OnClickListener : " + 
		                "\nImage Size : "+ String.valueOf(spImgSizeOptions.getSelectedItem()) + 
		                "\nColor Filter : "+ String.valueOf(spColorFilterOptions.getSelectedItem())+
		                "\nImage Type : "+ String.valueOf(spImgTypeOptions.getSelectedItem())+
		                "\nSite Filter : "+ etSiteFilter.getText().toString(),
					Toast.LENGTH_SHORT).show();
		
		savedOptions.add(String.valueOf(spImgSizeOptions.getSelectedItem()));
		savedOptions.add(String.valueOf(spColorFilterOptions.getSelectedItem()));
		savedOptions.add(String.valueOf(spImgTypeOptions.getSelectedItem()));
		savedOptions.add(etSiteFilter.getText().toString());
		writeItems();
		savedOptions.clear();
	}
	
	private void readItems() {
		
		File fileDir = getFilesDir();
		File configFile = new File(fileDir, "savedOptions.txt");
		try {
			savedOptions = new ArrayList<String>(FileUtils.readLines(configFile));
		} catch (IOException io) {
			savedOptions = new ArrayList<String>();
		}
	}
	
	private void writeItems() {
		
		File fileDir = getFilesDir();
		File configFile = new File(fileDir, "savedOptions.txt");
		configFile.delete();
		try {
			FileUtils.writeLines(configFile, savedOptions);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.advanced_search_options, menu);
		return true;
	}

}