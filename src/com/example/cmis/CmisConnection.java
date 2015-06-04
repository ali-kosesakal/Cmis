package com.example.cmis;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.slf4j.helpers.Util;

import com.example.cmis.tabs.Favorites;
import com.example.cmis.tabs.Rootfolders;
import com.example.cmis.tabs.Search;

import android.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;




public class CmisConnection extends AsyncTask<Bundle, Void, CmisResult<Session>>{
	private static final String TAG = "CmisConnection";
	private String username;
	private String password;
	private String url="http://192.168.2.211:8080/adys-cmis/atom11";
	private Activity activity;
	private ProgressDialog progressDialog;
	

	
	public CmisConnection (Activity activity){
		this.activity = activity;
		if(activity.getIntent().getExtras() != null){
			Bundle b = activity.getIntent().getExtras().getBundle("userName");
			retrieveBundleValues(b);
		}
	}
	public void retrieveBundleValues(Bundle b){
		username = b.getString("userName");
		password  = b.getString("password");
		
	}
	
	
	@Override
	protected void onPreExecute() {
		 progressDialog = new ProgressDialog(activity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(this.activity.getText(R.string.title_session_creations));
        progressDialog.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				dialog.dismiss();
				
			}
		});
		progressDialog.setCancelable(true);
		progressDialog.setTitle(this.activity.getText(R.string.title_session_creations));
		progressDialog.setMessage(this.activity.getText(R.string.create_session_describtions));
		progressDialog.show();
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);	
	}
	@Override
	protected CmisResult<Session> doInBackground(Bundle... params) {
		Session session = null;
		Exception exception = null;
		
		try{
			retrieveBundleValues(params[0]);
			SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put(SessionParameter.USER, username);
			parameters.put(SessionParameter.PASSWORD, password);
			parameters.put(SessionParameter.ATOMPUB_URL, url);
			parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
			//Repository repository = sessionFactory.getRepositories(parameters).get(0);
			parameters.put(SessionParameter.REPOSITORY_ID, "gridfs");
			session = sessionFactory.createSession(parameters);
			
			
		}catch(Exception e){
			exception = e;
			
		}
		
		return new CmisResult<Session>(exception, session);
	}
	@Override
	protected void onPostExecute(CmisResult<Session> result) {
		progressDialog.dismiss();
		if(result.hasException()){
			Toast.makeText(activity, result.getException().getMessage(), Toast.LENGTH_LONG).show();
			Log.e(TAG, Log.getStackTraceString(result.getException()));
		}else if(activity instanceof LoginPageActivity){
			Bundle b = new Bundle();
			b.putSerializable("Session", result.getDaTa());
			Intent opencmisactivity = new Intent(activity,OpenCmisActivity.class);
			opencmisactivity.putExtra("bundle", b);
			activity.startActivity(opencmisactivity);
		}
	}
	
	
	
	
	

}
