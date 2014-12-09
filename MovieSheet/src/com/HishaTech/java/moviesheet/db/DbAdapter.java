package com.HishaTech.java.moviesheet.db;

import java.io.File;

import com.HishaTech.java.moviesheet.db.model.movieFolder;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

public class DbAdapter {

	private static SQLiteConnection db;

	public DbAdapter() {
		db = new SQLiteConnection(new File(DbConstants.DB_NAME));
	}

	/**
	 *
	 * This method will open a connection to the database file. If the file
	 * doesn't exist it will be made. If the file does exist, the version will
	 * be checked and the database will be updated if needed.
	 *
	 * @return db (SQLiteConnection)
	 */
	public SQLiteConnection open() {

		File file = new File(DbConstants.DB_NAME);

		if (file.exists()) {

			System.out.println("Database Exists!!");

			try {
				db.open();
				UpgradeDbVersion();
			} catch (SQLiteException e) {
				System.out.println(e.toString());
			}

			return db;

		} else {

			try {
				db.open(true);
				createDB();
			} catch (SQLiteException e) {
				System.out.println(e.toString());
			}

			return db;

		}

	}

	/**
	 *
	 * This method will dispose of the existing SQLiteConnection.
	 *
	 */
	public void close() {

		db.dispose();

	}

	/**
	 *
	 * This method will create the various tables in the database.
	 *
	 */
	private void createDB() {

		try {

			db.exec(DbConstants.Begin);

			SQLiteStatement createDB = db.prepare(DbConstants.CREATE_DB);

			createDB.step();

			db.exec(DbConstants.Commit);

			createDB.dispose();

			SetDbVersion();

			System.out.println("Database Created!.");

		} catch (SQLiteException e) {

			System.out.println(e.toString());

		}
	}

	/**
	 *
	 * This method will check to see if the database is up to date, if not it
	 * will wipe the data and run the createDB method.
	 *
	 */
	private void UpgradeDbVersion() {

		if (!DbUpToDate()) {

			System.out.println("Database Out of Date.");

			try {

				db.exec("PRAGMA writable_schema = 1;");
				db.exec("delete from sqlite_master where type in ('table', 'index', 'trigger')");
				db.exec("PRAGMA writable_schema = 0;");
				db.exec("VACUUM;");
				createDB();

			} catch (Exception e) {

				System.out.println(e.toString());

			}
		}
	}

	/**
	 *
	 * This method will check the current database version verses the database
	 * version in the code.
	 *
	 * @return UpToDate (boolean)
	 */
	private boolean DbUpToDate() {

		boolean UpToDate = false;
		int dbVersion = GetDbVersion();
		if (dbVersion >= DbConstants.DB_VERSION) {
			UpToDate = true;
			System.out.println("Database Up to Date.");
		}
		return UpToDate;
	}

	/**
	 *
	 * This method will request the current database user version.
	 *
	 * @return dbVersion (int)
	 */
	private int GetDbVersion() {
		int dbVersion = 0;
		SQLiteStatement checkVer;
		try {
			checkVer = db.prepare("PRAGMA user_version;");
			while (checkVer.step()) {
				long dbVersionLong = checkVer.columnLong(0);
				dbVersion = (int) dbVersionLong;
			}
			System.out.println("Database version: " + dbVersion);
		} catch (SQLiteException e) {
			System.out.println(e.toString());
		}

		return dbVersion;
	}

	/**
	 *
	 * This method will update the database user version.
	 */
	private void SetDbVersion() {
		try {
			db.exec("PRAGMA user_version=" + DbConstants.DB_VERSION);
		} catch (SQLiteException e) {
			System.out.println(e.toString());
		}
		System.out
				.println("Database version set to: " + DbConstants.DB_VERSION);
	}

	public boolean insertMovieFolder(movieFolder MovieFolder) {

		boolean insertFailed = true;
		SQLiteStatement st = null;
		try {
			db.exec(DbConstants.Begin);
			st = db.prepare("INSERT INTO " + DbConstants.DATABASE_MOVIE_TABLE
					+ "(" + DbConstants.MOVIE_Table_InsertAll
					+ ") VALUES (?,?);");
			st.bind(1, MovieFolder.getFolderName());
			st.bind(2, MovieFolder.getURL());

			insertFailed = st.step();
			db.exec(DbConstants.Commit);
			db.dispose();
		} catch (SQLiteException e) {
			System.out.println(e.toString());
		} finally {
			st.dispose();
		}
		return insertFailed;
	}

	public boolean checkMovieFolderExists(movieFolder MovieFolder) {
		
		boolean movieFolderExists = false;
		SQLiteStatement st = null;
		try {
			st = db.prepare("SELECT COUNT(*) FROM "
					+ DbConstants.DATABASE_MOVIE_TABLE + " WHERE "
					+ DbConstants.MOVIE_folderName + " = ?;");
			st.bind(1, MovieFolder.getFolderName());
			while (st.step()) {
				if (st.columnInt(0) > 0) {
					movieFolderExists = true;
				}
			}
		} catch (SQLiteException e) {
			System.out.println(e.toString());
		} finally {
			st.dispose();
		}
		return movieFolderExists;
	}

}