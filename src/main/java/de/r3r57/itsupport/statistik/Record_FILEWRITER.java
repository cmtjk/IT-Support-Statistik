package de.r3r57.itsupport.statistik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class Record_FILEWRITER {

	private File myFile, mySource, myTarget, myBackupDir, myTempFile;
	private String myFilePath, tempLine;
	private BufferedWriter in;
	private BufferedReader out;
	private DateFormat dateFormat, dateFormatFile;
	private boolean deleted;

	public Record_FILEWRITER() {

		dateFormat = new SimpleDateFormat("HH:mm:ss");
		dateFormatFile = new SimpleDateFormat("ddMMyy_HH-mm-ss");

		myFilePath = new File("").getAbsolutePath();
		myFile = new File(myFilePath + "/data/");
		myBackupDir = new File(myFilePath + "/backup/");

		// Überprüfen auf Existenz
		if (!myFile.exists()) {
			myFile.mkdir();
		}
		if (!myBackupDir.exists()) {
			myBackupDir.mkdir();
		}

		myFile = new File(myFilePath + "/data/statistik.dat");

	}

	//
	public boolean writeToFile(String daten, int count, String theDate) {
		try {
			String currentDate = theDate + " " + dateFormat.format(new Date())
					+ " # ";
			daten = currentDate + daten;
			in = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(myFile, true), "UTF-8"));
			for (int i = 0; i < count; i++) {
				in.write(daten);
				in.newLine();
			}
			in.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERROR: While writing to file",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteString(String selectedItem) throws IOException {

		myTempFile = new File(myFilePath + "/data/statistik.tmp");

		out = new BufferedReader(new InputStreamReader(new FileInputStream(
				myFile), "UTF-8"));
		in = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
				myTempFile), "UTF-8"));

		deleted = false;

		while ((tempLine = out.readLine()) != null) {

			if (!deleted && tempLine.equals(selectedItem)) {
				deleted = true;

			} else {
				in.write(tempLine);
				in.newLine();

			}
		}
		in.close();
		out.close();
		myFile.delete();
		myTempFile.renameTo(myFile);

		return true;
	}

	public boolean backupFile() throws IOException {
		mySource = myFile;
		myTarget = new File(myFilePath + "/backup/Statistik_"
				+ dateFormatFile.format(new Date()) + ".dat");
		return copyFile(mySource, myTarget);
	}

	private boolean copyFile(File in, File out) {
		FileChannel inChannel = null;
		FileChannel outChannel = null;

		try {
			inChannel = new FileInputStream(in).getChannel();
			outChannel = new FileOutputStream(out).getChannel();
			inChannel.transferTo(0, inChannel.size(), outChannel);
			if (inChannel != null)
				inChannel.close();
			if (outChannel != null)
				outChannel.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

		return true;
	}
}
