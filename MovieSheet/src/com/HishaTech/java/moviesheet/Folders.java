package com.HishaTech.java.moviesheet;

import java.io.File;
import java.io.FileFilter;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import com.HishaTech.java.moviesheet.db.model.movieFolder;
import com.HishaTech.java.moviesheet.db.table.Table_MovieFolder;

public class Folders {

	public static void getList(String Path, TextArea taName) {

		File dir = new File(Path);

		File[] subDirs = dir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});

		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				for (File subDir : subDirs) {

					movieFolder mf = new movieFolder();
					mf.setFolderName(removePath(subDir.getPath()));
					mf.setURL("");

					if (!Table_MovieFolder.checkExists(mf)) {
						Table_MovieFolder.insertRecord(mf);
					}

					Platform.runLater(new Runnable() {
						@Override
						public void run() {

							taName.appendText(removePath(subDir.getPath())
									+ "\n");
						}
					});

				}
				return null;

			}

		};

		Thread t = new Thread(task);
		t.setDaemon(true);
		t.start();

	}

	private static String removePath(String FullPath) {

		String rtnValue = "";

		int index = FullPath.lastIndexOf(File.separator);

		rtnValue = FullPath.substring(index + 1);

		return rtnValue;
	}
}
