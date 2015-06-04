package com.example.cmis;

import android.util.Log;

public class PathHolder {
	private String currentPath;
	private String previousPath;
	
	public PathHolder(){
		currentPath = "/users/ali";
		previousPath = null;
	}

	public String getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}

	public String getPreviousPath() {
		return previousPath;
	}

	public void setPreviousPath(String previousPath) {
		this.previousPath = previousPath;
	}
	
	public void addPath(String path){
		previousPath = currentPath;
		currentPath = currentPath+"/"+path;
		Log.d("currentPath", previousPath+ "-"+ currentPath);
	}
	
	public void removePath(){
		currentPath = previousPath;
		if(previousPath.equals("/users/ali")){
			previousPath = null;
		}else{
			String[] words = previousPath.split("/");
			previousPath = "";
			for(int i = 1; i < (words.length-1);i++){
				previousPath =previousPath+"/"+words[i];
			}
			Log.d("çıktı", previousPath+"-"+currentPath+"\n"+
			words[0]+words[1]+words[2]+"----"+words.length);
		}
	}
}
