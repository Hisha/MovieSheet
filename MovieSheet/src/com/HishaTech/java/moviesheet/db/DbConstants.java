package com.HishaTech.java.moviesheet.db;

public class DbConstants {

	public final static String DB_NAME = "movieSheets.db";
	public final static int DB_VERSION = 1;
	public final static String Begin = "BEGIN TRANSACTION;";
	public final static String Commit = "COMMIT;";

	// ** Program Variables Table ** //
	public final static String DATABASE_MOVIE_TABLE = "movie_table";
	public final static String MOVIE_rowId = "_id";
	public final static String MOVIE_folderName = "foldername";
	public final static String MOVIE_url = "url";

	public final static String CREATE_DB = "CREATE TABLE if NOT EXISTS "
			+ DATABASE_MOVIE_TABLE + " (" + MOVIE_rowId
			+ " integer primary key not null," + MOVIE_folderName
			+ " varchar(150) not null," + MOVIE_url + " varchar(200) not null"
			+ ");";
	
	public final static String MOVIE_Table_InsertAll = MOVIE_folderName + "," + MOVIE_url;

}
