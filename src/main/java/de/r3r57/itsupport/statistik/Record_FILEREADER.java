package de.r3r57.itsupport.statistik;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.JOptionPane;

public class Record_FILEREADER {

	private String myFilepath;
	private File myStatFile, myCfgFile;
	private BufferedReader myRead;
	private ObservableList<String> myObservableList;
	private ArrayList<String> optionsList;
	private DateFormat dateFormat;


	// Konstruktor
	public Record_FILEREADER() throws IOException {

		dateFormat = new SimpleDateFormat("dd MMMM yyyy");


		myFilepath = new File("").getAbsolutePath();
		myStatFile = new File(myFilepath + "/data/statistik.dat");
		myCfgFile = new File(myFilepath + "/config/config.cfg");

        System.out.println(myCfgFile);

		// Überprüfen auf Existenz mit Beenden bei Nichtexistenz
		if (!myStatFile.exists()) {
			JOptionPane.showMessageDialog(null, "statistik.dat doesn't exist",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		if (!myCfgFile.exists()) {
			JOptionPane.showMessageDialog(null, "config.cfg doesn't exist",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	// heutige statistik.dat auslesen
	public ObservableList<String> readFile() throws IOException {
		try {

			String data = null;
			String currentDate = dateFormat.format(new Date());
			myObservableList = FXCollections.observableArrayList();
			myRead = new BufferedReader(new InputStreamReader(
					new FileInputStream(myStatFile), "UTF-8"));
			while ((data = myRead.readLine()) != null) {
				if (data.startsWith(currentDate)) {
					myObservableList.add(data);
				}

			}
			myRead.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: While reading file",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return myObservableList;

	}

	// config.cfg auslesen und Inhalt als String[] zurückgeben
	public String[] read_cfgFile() throws IOException {
		String[] options = null;
		myRead = new BufferedReader(new InputStreamReader(new FileInputStream(
				myCfgFile), "UTF-8"));

		String line = null;
		optionsList = new ArrayList<String>();
		while ((line = myRead.readLine()) != null) {
			if (!line.contains("%"))
				optionsList.add(line);
		}
		myRead.close();
		return options = optionsList.toArray(new String[optionsList.size()]);
	}

	public File getFile() {
		return myStatFile;
	}

}
