package com.example.cmis.sessiontasks;

import java.util.ArrayList;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Session;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.cmis.CmisResult;
import com.example.cmis.adapters.Folders;
import com.example.cmis.tabs.Rootfolders;

public class RootFolderTasks extends AsyncTask<Void, Void, CmisResult<List<Folders>>>{
	private Activity activity;
	private Session session;
	private String Tag = "RootFolderTasks";

	public  RootFolderTasks(Activity activity, Session session) {
		this.activity = activity;
		this.session = session;
	}
	
	@Override
	protected CmisResult<List<Folders>> doInBackground(Void... params) {
		Exception exception = null;
		List<Folders> listfolders = new ArrayList<Folders>();
		
		try{
			Folder folder = (Folder) session.getObjectByPath("/users/ali");
			ItemIterable<CmisObject> children = folder.getChildren();
			for(CmisObject o : children){
				listfolders.add(new Folders(o.getName(), o.getType().getDisplayName()));
				Log.d("tür", o.getType().getDisplayName());
			}
		}catch(Exception e){
			exception = e;
		}
		
		return new CmisResult<List<Folders>>(exception, listfolders);
	}
	

	@Override
	protected void onPostExecute(CmisResult<List<Folders>> result) {
		
		if(result.hasException()){
			Toast.makeText(activity,result.getException().getMessage(), Toast.LENGTH_LONG).show();
			Log.e(Tag, Log.getStackTraceString(result.getException()));
		}else if (activity instanceof Rootfolders){
			((Rootfolders)activity).ListFolders(result.getDaTa());
		}
	}
	
	

}
