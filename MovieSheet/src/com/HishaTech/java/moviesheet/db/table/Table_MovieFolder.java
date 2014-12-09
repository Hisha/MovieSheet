package com.HishaTech.java.moviesheet.db.table;

import com.HishaTech.java.moviesheet.db.DbAdapter;
import com.HishaTech.java.moviesheet.db.model.movieFolder;

public class Table_MovieFolder {

	public static boolean insertRecord(movieFolder MovieFolder) {

		boolean insertFailed = true;
		DbAdapter db = new DbAdapter();
		db.open();
		insertFailed = db.insertMovieFolder(MovieFolder);
		db.close();
		return insertFailed;
	}

	public static boolean checkExists(movieFolder MovieFolder) {

		boolean recordExists = false;

		DbAdapter db = new DbAdapter();
		db.open();
		recordExists = db.checkMovieFolderExists(MovieFolder);
		db.close();

		return recordExists;

	}

}
