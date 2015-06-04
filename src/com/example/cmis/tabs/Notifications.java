package com.example.cmis.tabs;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Session;

import com.example.cmis.sessiontasks.NotificationTasks;

import android.R;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Notifications extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications);
		TextView tv = (TextView)findViewById(R.id.textview);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras().getBundle("bundle");
		Session session = (Session) bundle.getSerializable("Session");
		new NotificationTasks(this, session).execute();
	}
	
public void writeFolders(String result){
	TextView tv = (TextView)findViewById(R.id.textview);
	tv.setText(result);
}


}
