package com.example.cmis;


import org.apache.chemistry.opencmis.client.api.Session;

import android.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.cmis.tabs.Favorites;
import com.example.cmis.tabs.Notifications;
import com.example.cmis.tabs.Rootfolders;
import com.example.cmis.tabs.Search;

@SuppressWarnings("deprecation")
public class OpenCmisActivity extends TabActivity {
	private Session session;
	private Bundle bundle;
	 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cmis);
        
        if(getIntent().getExtras() != null){
        	Log.d("intent", "bos degil");
        }else{
        	Log.d("intent","bos");
        }
        
        	bundle = getIntent().getExtras().getBundle("bundle");
        	if(bundle != null){
            	Log.d("bundle", "bos degil");
            }else{
            	Log.d("intent","bos");
            }
        	session = (Session) bundle.getSerializable("Session");
        	if(session !=null){
        		
        		Log.d("session", "bos degil");
        	}else {
        		Log.d("session", "bos");
        	}
        
        	
        Intent intentRootFodlers = new Intent(getApplicationContext(),Rootfolders.class);
        intentRootFodlers.putExtra("bundle", bundle);
        
        Intent intentFavorites = new Intent(getApplicationContext(),Favorites.class);
        intentFavorites.putExtra("bundle", bundle);
        
        Intent intentNotifications = new Intent(getApplicationContext(),Notifications.class);
        intentNotifications.putExtra("bundle", bundle);
        
        Intent intentSearch = new Intent(getApplicationContext(),Search.class);
        intentSearch.putExtra("bundle", bundle);
       
       TabHost tb = (TabHost)findViewById(android.R.id.tabhost);
        tb.setup();
        
        TabSpec sayfa1 = tb.newTabSpec("tab1");
        TabSpec sayfa2 = tb.newTabSpec("tab2");
        TabSpec sayfa3 = tb.newTabSpec("tab3");
        TabSpec sayfa4 = tb.newTabSpec("tab4");
      
        
        sayfa1.setIndicator(null,
        		getResources().getDrawable(R.drawable.box)).setContent(intentRootFodlers);
        sayfa2.setIndicator(null,
        		getResources().getDrawable(R.drawable.star)).setContent(intentFavorites);
        sayfa3.setIndicator(null,
        		getResources().getDrawable(R.drawable.flag)).setContent(intentNotifications);
        sayfa4.setIndicator(null,
        		getResources().getDrawable(R.drawable.search)).setContent(intentSearch);
        
        
        tb.addTab(sayfa1);
        tb.addTab(sayfa2);
        tb.addTab(sayfa3);
        tb.addTab(sayfa4);
        
    }
    
  
}
