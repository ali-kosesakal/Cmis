package com.example.cmis.adapters;

public class Folders {
 private String isim;
 private String tur;
 
 public Folders(String isim, String tur) {
	 
	 this.isim = isim;
	 this.tur = tur;
 }
 @Override
 public String toString() {
     return isim;
 }

public String getIsim() {
	return isim;
}

public void setIsim(String isim) {
	this.isim = isim;
}

public String getTur() {
	return tur;
}

public void setTur(String tur) {
	this.tur = tur;
}
 
}		
