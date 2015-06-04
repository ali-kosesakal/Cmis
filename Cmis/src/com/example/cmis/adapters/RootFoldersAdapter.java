package com.example.cmis.adapters;

import java.util.List;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class RootFoldersAdapter extends BaseAdapter implements View.OnCreateContextMenuListener{
	private LayoutInflater mInflater;
	private List<Folders> mFolderList;
	
	public RootFoldersAdapter(Activity activity, List<Folders> folderList){
		mInflater = (LayoutInflater) activity.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		mFolderList = folderList;
	}
	@Override
	public int getCount() {
		return mFolderList.size();
		
	}

	@Override
	public Object getItem(int position) {
		return mFolderList.get(position);
		
	}

	@Override
	public long getItemId(int position) {
		return position;
		
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View satirView =convertView;
		if(satirView == null){
			satirView = mInflater.inflate(R.layout.line_placement, null);
		}
		
		TextView tv = (TextView)satirView.findViewById(R.id.item_name);
		ImageView image = (ImageView)satirView.findViewById(R.id.imageView);
		Folders folder = mFolderList.get(position);
		tv.setText(folder.getIsim());
		if(folder.getTur().equals("Document")){
			image.setImageResource(R.drawable.document);
		}else if(folder.getTur().equals("Folder")){
			image.setImageResource(R.drawable.folder);
		}else if(folder.getTur().equals("Image")){
			image.setImageResource(R.drawable.image);
		}else if(folder.getTur().equals("Audio")){
			image.setImageResource(R.drawable.sounds);
		}else if(folder.getTur().equals("Video")){
			image.setImageResource(R.drawable.video);
		}else if(folder.getTur().equals("up")){
			image.setImageResource(R.drawable.up);
		}
		return satirView;
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		
	}


}
