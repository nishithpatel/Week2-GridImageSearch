package com.example.yahoo.week2_gridimagesearch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
		
		int imgSizeSpinnerPos = imgSizeOptionsAdapter.getPosition("all");
		int colorFilterSpinnerPos = colorFilterOptionsAdapter.getPosition("none");
		int imgTypeSpinnerPos = imgTypeOptionsAdapter.getPosition("all");
		

		//set the default according to value
		spImgSizeOptions.setSelection(imgSizeSpinnerPos);
		spColorFilterOptions.setSelection(colorFilterSpinnerPos);
		spImgTypeOptions.setSelection(imgTypeSpinnerPos);
		//etSiteFilter.setText(savedOptions.get(3));
		
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
		imgSizeOptions.add("all");
		imgSizeOptions.add("small");
		imgSizeOptions.add("medium");
		imgSizeOptions.add("large");
		imgSizeOptions.add("xlarge");
		imgSizeOptionsAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, imgSizeOptions);
		spImgSizeOptions.setAdapter(imgSizeOptionsAdapter);

	}
	
	public void addColorFilterOptions() {
		//spColorFilterOptions = (Spinner) findViewById(R.id.spColorFilterOptions);
		colorFilterOptions = new ArrayList<String>();		
		colorFilterOptions.add("none");
		colorFilterOptions.add("black");
		colorFilterOptions.add("blue");
		colorFilterOptions.add("brown");
		colorFilterOptions.add("gray");
		colorFilterOptions.add("green");
		colorFilterOptions.add("red");
		colorFilterOptions.add("orange");
		colorFilterOptions.add("pink");
		colorFilterOptions.add("purple");
		colorFilterOptions.add("teal");
		colorFilterOptions.add("white");
		colorFilterOptions.add("yellow");
		colorFilterOptionsAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, colorFilterOptions);
		spColorFilterOptions.setAdapter(colorFilterOptionsAdapter);

	}
	
	public void addImgTypeOptions() {
		//spImgTypeOptions = (Spinner) findViewById(R.id.spImgTypeOptions);
		imgTypeOptions = new ArrayList<String>();		
		imgTypeOptions.add("all");
		imgTypeOptions.add("face");
		imgTypeOptions.add("photo");
		imgTypeOptions.add("clipart");
		imgTypeOptions.add("lineart");
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
		Intent data = new Intent();
		data.putExtra("imageSize", String.valueOf(spImgSizeOptions.getSelectedItem()));
		data.putExtra("colorFilter", String.valueOf(spColorFilterOptions.getSelectedItem()));
		data.putExtra("imageType", String.valueOf(spImgTypeOptions.getSelectedItem()));
		data.putExtra("siteFilter", etSiteFilter.getText().toString());
		setResult(RESULT_OK, data);
		finish();
		/*
		savedOptions.add(String.valueOf(spImgSizeOptions.getSelectedItem()));
		savedOptions.add(String.valueOf(spColorFilterOptions.getSelectedItem()));
		savedOptions.add(String.valueOf(spImgTypeOptions.getSelectedItem()));
		savedOptions.add(etSiteFilter.getText().toString());
		writeItems();
		savedOptions.clear();
		*/
		
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
