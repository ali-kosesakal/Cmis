package com.example.cmis.tabs;


import java.util.List;

import org.apache.chemistry.opencmis.client.api.Session;

import android.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.cmis.PathHolder;
import com.example.cmis.adapters.Folders;
import com.example.cmis.adapters.RootFoldersAdapter;
import com.example.cmis.sessiontasks.RetrieveChildFolderTasks;
import com.example.cmis.sessiontasks.RootFolderTasks;

public class Rootfolders extends ListActivity {
	 private Session session;
	 private ListView folderList;
	 private PathHolder path = new PathHolder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_root_folder);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras().getBundle("bundle");
		session = (Session) bundle.getSerializable("Session");
    	new RootFolderTasks(this, session).execute();
    	registerForContextMenu(folderList);
    	
		
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.root_folder_context_menu, menu);
	}


	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onContextItemSelected(item);
	}


	public void ListFolders(List<Folders> folders){
		folderList= (ListView) findViewById(android.R.id.list);
		RootFoldersAdapter adapter = new RootFoldersAdapter(Rootfolders.this, folders);
		folderList.setAdapter(adapter);
		folderList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String name = folderList.getItemAtPosition(position).toString();
				if(name.equals("Up")){
					path.removePath();
					name = path.getCurrentPath();
					new RetrieveChildFolderTasks(Rootfolders.this, session).execute(name);
					
				}else{
					path.addPath(name);
					name = path.getCurrentPath();
					new RetrieveChildFolderTasks(Rootfolders.this, session).execute(name);
				}
				
			}
		});
		
	}
	
}
