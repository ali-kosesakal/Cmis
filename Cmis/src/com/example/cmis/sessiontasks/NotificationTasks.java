package com.example.cmis.sessiontasks;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Session;

import com.example.cmis.tabs.Notifications;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class NotificationTasks extends AsyncTask<Void, Void, String>{
	private Session session;
	private Activity activity;
	private String Tag = "NotificationTasks";
	
	public  NotificationTasks(Activity activity, Session session) {
		this.activity = activity;
		this.session = session;
	}
	
	
	@Override
	protected String doInBackground(Void... params) {

		Log.d("session", "boş değil");
		String ListChilren = " ";
		Folder folder = (Folder) session.getObjectByPath("/users/ali");
		ItemIterable<CmisObject> children = folder.getChildren();
		for(CmisObject o: children){
			ListChilren += o.getName() +" \n "+ o.getType().getDisplayName();
			
	}
		return ListChilren;
	}


	@Override
	protected void onPostExecute(String result) {
		((Notifications)activity).writeFolders(result);
	}
	

}
