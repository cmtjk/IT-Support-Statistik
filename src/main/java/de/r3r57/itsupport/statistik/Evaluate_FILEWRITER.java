package de.r3r57.itsupport.statistik;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evaluate_FILEWRITER {

	private BufferedWriter in;
	private String myFilePath;
	private File myFile;
	private DateFormat dateFormat;

	public Evaluate_FILEWRITER(String html) throws IOException {

		dateFormat = new SimpleDateFormat("ddMMYYYY_HH-mm-ss");

		myFilePath = new File("").getAbsolutePath();
		myFilePath += "/statistiken";

		myFile = new File(myFilePath);

		if (!myFile.exists()) {
			myFile.mkdir();
		}

		myFile = new File(myFilePath + "/Statistik_"
				+ dateFormat.format(new Date()) + ".html");

		in = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
				myFile), "UTF-8"));

		in.write(html);
		in.close();
		Desktop.getDesktop().open(myFile);

	}

}
