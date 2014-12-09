package com.HishaTech.java.moviesheet.gui;

import com.HishaTech.java.moviesheet.AppConstants;
import com.HishaTech.java.moviesheet.Folders;
import com.HishaTech.java.moviesheet.Prefs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller_Main {

	static Prefs pref = new Prefs();

	@FXML
	public Button btnClearText, btnClose, btnScan;
	public Label lblEnterFolder, lblDefaultFolder;
	public TextArea taMovieFolders;
	public TextField txtMovieFolder;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	public void initialize() {

		setText();
		txtMovieFolder.requestFocus();

	}

	@FXML
	public void btnClearText_click() {

		taMovieFolders.setText("");
		txtMovieFolder.setText("");

	}

	@FXML
	public void btnScan_click() {

		if (txtMovieFolder.getText().equals("")) {

			txtMovieFolder.setText(pref.getString(AppConstants.MovieFolderKey,
					AppConstants.defaultMovieFolder));

		} else {

			pref.saveString(AppConstants.MovieFolderKey,
					txtMovieFolder.getText());

		}

		lblDefaultFolder.setText(GUIConstants.lblDefaultFolder
				+ pref.getString(AppConstants.MovieFolderKey,
						AppConstants.defaultMovieFolder) + ")");

		Folders.getList(txtMovieFolder.getText(), taMovieFolders);

	}

	@FXML
	public void btnClose_click() {

		System.exit(0);

	}

	private void setText() {

		btnClose.setText(GUIConstants.btnClose);
		btnClearText.setText(GUIConstants.btnClearText);
		btnScan.setText(GUIConstants.btnScan);
		lblEnterFolder.setText(GUIConstants.lblEnterFolder);
		lblDefaultFolder.setText(GUIConstants.lblDefaultFolder
				+ pref.getString(AppConstants.MovieFolderKey,
						AppConstants.defaultMovieFolder) + ")");

	}

}
