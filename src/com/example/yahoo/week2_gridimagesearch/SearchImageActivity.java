package com.example.yahoo.week2_gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class SearchImageActivity extends Activity {
	
	private EditText etSearchCriteria;
	private Button btnSearch;
	private GridView gvResults;
	private ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	private ImageResultArrayAdapter imageAdapter;
	
	private final int REQUEST_CODE = 1;
	private String imageSize;
	private String colorFilter;
	private String imageType;
	private String siteFilter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_image);
		etSearchCriteria = (EditText) findViewById(R.id.etSearchCriteria);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		gvResults = (GridView) findViewById(R.id.gvResults);
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position, long rowId) {
				Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				ImageResult imageResult = imageResults.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}
			
		});
		
	}
	
	public void onImageSearch(View v) {
		String query = etSearchCriteria.getText().toString();
		Toast.makeText(this, "Search Criteria: " + query, Toast.LENGTH_SHORT).show();
		
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" + 
		"start=" + 0 + "&v=1.0" + "&imgsz=" + Uri.encode(imageSize) + "&imgcolor=" + Uri.encode(colorFilter) + 
		"&imgtype=" + Uri.encode(imageType) + "&as_sitesearch=" + Uri.encode(siteFilter) + 
		"&q=" + Uri.encode(query), 
			new JsonHttpResponseHandler(){
				public void onSuccess(JSONObject response)
				{
					JSONArray imageJsonResults = null;
					try
					{
						imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
						imageResults.clear();
						imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
						Log.d("DEBUG", imageResults.toString());
					}
					catch (JSONException e)
					{
						e.printStackTrace();
					}
				}
			});
	}
	
	public void onOptions(MenuItem mi) {
	     // handle click from menu item
		//Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, AdvancedSearchOptions.class);
		i.putExtra("mode", 2);
		startActivityForResult(i, REQUEST_CODE);
	  }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) 
		{
		     imageSize = data.getExtras().getString("imageSize");
		     Toast.makeText(this, imageSize, Toast.LENGTH_SHORT).show();
		     
		     colorFilter = data.getExtras().getString("colorFilter");
		     Toast.makeText(this, colorFilter, Toast.LENGTH_SHORT).show();
		     
		     imageType = data.getExtras().getString("imageType");
		     Toast.makeText(this, imageType, Toast.LENGTH_SHORT).show();
		     
		     siteFilter = data.getExtras().getString("siteFilter");
		     Toast.makeText(this, siteFilter, Toast.LENGTH_SHORT).show();
		  }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_image, menu);
		return true;
	}

}
