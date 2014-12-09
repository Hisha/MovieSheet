package com.HishaTech.java.moviesheet.db.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class movieFolder {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty foldername = new SimpleStringProperty("");
	private SimpleStringProperty url = new SimpleStringProperty("");

	public int getID() {
		return id.get();
	}

	public void setID(int ID) {
		id.set(ID);
	}

	public String getFolderName() {
		return foldername.get();
	}

	public void setFolderName(String FolderName) {
		foldername.set(FolderName);
	}

	public String getURL() {
		return url.get();
	}

	public void setURL(String URL) {
		url.set(URL);
	}

}
