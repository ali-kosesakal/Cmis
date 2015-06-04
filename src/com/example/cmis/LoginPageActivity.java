package com.example.cmis;


import android.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class LoginPageActivity extends ActionBarActivity {
	Button connect;
	EditText user , password ; 
	String folders=" "; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginpage);
		
		
		final Intent intent = new Intent(this,CmisConnection.class);
		
		 connect = (Button)findViewById(R.id.btnConnect);
		 

		 connect.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi") @Override
			public void onClick(View v) {
			
				requestSession();
			}
		});
		
		 
	}

		
	public void requestSession(){
		String userName,Password;
		user = (EditText) findViewById(R.id.editUser_Name);
		password = (EditText)findViewById(R.id.editPassword);
		
		userName = user.getText().toString();
		Password = password.getText().toString();
		
		Bundle b = new Bundle();
		
		b.putString("userName", userName);
		b.putString("password", Password);
		new CmisConnection(this).execute(b);
		
	}
	
}
