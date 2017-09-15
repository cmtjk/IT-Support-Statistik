package de.r3r57.itsupport.statistik;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Evaluate_FILEREADER {

	private String year;
	private String monthFrom;
	private String monthTo;
	private String myFilePath;
	private ArrayList<String[]> dataInArrayList;
	private File myStatFile;
	private BufferedReader myReader;
	private boolean fromNowOn;
	private String myString;

	private final String[] months = { "Alle", "Januar", "Februar", "März",
			"April", "Mai", "Juni", "Juli", "August", "September", "Oktober",
			"November", "Dezember" };

	public Evaluate_FILEREADER(File statFile, String year, String monthFrom,
			String monthTo) {

		this.myStatFile = statFile;
		this.year = year;
		this.monthFrom = monthFrom;
		this.monthTo = monthTo;
	}

	public ArrayList<String[]> readStatFile() throws IOException {
		dataInArrayList = new ArrayList<String[]>();
		fromNowOn = false;
		myString = null;

		// Jeden Monat durchlaufen
		for (String singleMonth : months) {
			myReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(myStatFile), "UTF-8"));
			if (singleMonth.equals(monthFrom)) {
				fromNowOn = true;
			}
			String date = singleMonth + " " + year;
			while (((myString = myReader.readLine()) != null) && fromNowOn) {
				String[] dataArray = myString.split(" # ");
				// String[] date = dataArray[0].split(" ");
				// if (((date[2].equals(year)) && date[1].equals(singleMonth))
				// || (date[2].equals(year) && monthFrom.equals("Alle"))) {
				if ((dataArray[0].contains(date) || (singleMonth == "Alle")
						&& dataArray[0].contains(year)))
					dataInArrayList.add(dataArray);
			}

			// }
			if (singleMonth.equals(monthTo)) {
				fromNowOn = false;
				myReader.close();
				break;
			}
			myReader.close();
		}
		return dataInArrayList;
	}

}
