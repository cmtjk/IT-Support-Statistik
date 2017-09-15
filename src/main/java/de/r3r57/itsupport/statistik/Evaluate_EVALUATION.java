package de.r3r57.itsupport.statistik;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Evaluate_EVALUATION {

	private DateFormat dateFormat;
	private Evaluate_FILEREADER evaluate_filereader;
	private ArrayList<String[]> dataArrayList, semiSortedArrayList, semiSortedArrayList_TB4;
	private String html, tablehead, comments, tr, table, table_TB4, monthTo;
	private int overallCase, none_vormittags, overallVormittags, none_nachmittags, overallNachmittags,
			windows_vormittags, windows_nachmittags, macos_vormittags, macos_nachmittags, linux_vormittags,
			linux_nachmittags, android_vormittags, android_nachmittags, overallWindows, overallNone, overallMacos,
			overallLinux, overallAndroid, misc_vormittags, misc_nachmittags, overallMisc, overallCounter, tb4Counter;

	private Evaluate_FILEWRITER evaluate_filewriter;

	public Evaluate_EVALUATION(String[] cfgFile, File statFile, String year, String monthFrom, String monthTo)
			throws IOException {

		this.monthTo = monthTo;
		if (monthTo == null) {
			monthTo = "Alle";
		}

		dateFormat = new SimpleDateFormat("dd MMMM YYYY HH:mm:ss");
		evaluate_filereader = new Evaluate_FILEREADER(statFile, year, monthFrom, monthTo);
		dataArrayList = new ArrayList<String[]>(evaluate_filereader.readStatFile());

		semiSortedArrayList = new ArrayList<String[]>();
		semiSortedArrayList_TB4 = new ArrayList<String[]>();

		// HTML-Vorbereiten
		html = "<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset='utf-8'>" + "<style type='text/css'>"
				+ "html {overflow-y: scroll;}"
				+ "body,div,h1,h2,h3,h4,h5,h6,p,blockquote,pre,code,ul,ol,li,table,th,td,form,fieldset,legend,input,textarea {padding: 0;margin: 0;}"
				+ "h2,h3,h4,h5,h6,p,blockquote,pre,section {margin-bottom: 1em;}"
				+ "body {font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 75%; background-color: whitesmoke; }"
				+ "h1 { font-size: 150%; padding-bottom: 1em}" + "h2 { font-size: 75%; font-weight: lighter }"
				+ "header {padding: .5em; padding-bottom: .1em; margin-bottom: 1em; background-color: rgb(51,106,151); color:white}"
				+ "div {margin-bottom: 2em; margin-top: 2em;}"
				+ "div#wrapper { width: 1280px; margin:auto; background:white; padding: 1em; }"
				+ "div#comments {padding: 1em}"
				+ "div#comments p#wholeComment {background-color: rgb(255,246,204); padding: .5em}"
				+ "div#comments span#theComment {font-style: italic;}"
				+ "table {width:100%; margin-bottom: 3em; margin-top: 3em;}"
				+ "th {padding: .5em; background-color: rgb(102,144,177); color:white}"
				+ "tr#daytime { font-size: 85%; background-color: rgb(234,242,207); text-align: center}"
				+ "tr.data:hover td {background-color: rgb(255,246,204);}"
				+ "td {vertical-align:top; overflow:hidden; padding: .1em}"
				+ "tr.data:nth-child(odd) { background-color: rgb(204,218,229)}"
				+ "tr.data:nth-child(even) { background-color: #ffffff}"
				+ ".blue {background-color: rgb(102,144,177); color:white;}" + ".center {text-align: center;}"
				+ "</style>" + "</head>" + "<div id=wrapper>" + "<body>" + "<header>" + "<h1>Statistik von " + monthFrom
				+ " bis " + monthTo + " " + year + "</h1>" + "<h2>" + dateFormat.format(new Date()) + "</h2></header>";

		tablehead = "<tr>" + "<th rowspan='2'>Anfragetyp</th>" + "<th colspan='2'>BS unabhängig</th>"
				+ "<th colspan='2'>Windows</th>" + "<th colspan='2'>Mac OS / iOS</th>" + "<th colspan='2'>Linux</th>"
				+ "<th colspan='2'>Android</th>" + "<th colspan='2'>sonstige</th>" + "<th colspan='2'>&sum;</th>"
				+ "</tr>" + "<tr id=daytime>" + "<td>vormittags</td>" + "<td>nachmittags</td>" + "<td>vormittags</td>"
				+ "<td>nachmittags</td>" + "<td>vormittags</td>" + "<td>nachmittags</td>" + "<td>vormittags</td>"
				+ "<td>nachmittags</td>" + "<td>vormittags</td>" + "<td>nachmittags</td>" + "<td>vormittags</td>"
				+ "<td>nachmittags</td>" + "<td colspan=2></td>" + "</tr>";

		table = "<table><tr><th colspan='14'>RZ und TB4(tel/OTRS)</th></tr>" + tablehead;
		table_TB4 = "<tr><th colspan='14'>TB4(lokal)</th></tr>" + tablehead;

		comments = "<div id=comments><h1>Kommentare:</h1>";

		// Auswertung

		overallVormittags = 0;
		overallNachmittags = 0;
		overallCounter = 0;
		tb4Counter = 0;

		for (String casesString : cfgFile) {
			semiSortedArrayList.clear();
			semiSortedArrayList_TB4.clear();
			for (String[] singleRawDataRow : dataArrayList) {

				if (singleRawDataRow[1].equals(casesString) && singleRawDataRow[4].equals("tb4(lokal)")) {
					tb4Counter++;
					overallCounter++;
					semiSortedArrayList_TB4.add(singleRawDataRow);

					// Kommentare speichern, falls vorhanden
					if (!singleRawDataRow[3].equals("null")) {
						comments += "<p id=wholeComment>" + singleRawDataRow[0] + " | " + singleRawDataRow[1] + " | "
								+ singleRawDataRow[2] + " | " + singleRawDataRow[4] + " | " + "<span id=theComment>"
								+ singleRawDataRow[3] + "</span></p>";
					}
				} else if (singleRawDataRow[1].equals(casesString)) {
					semiSortedArrayList.add(singleRawDataRow);
					overallCounter++;

					// Kommentare speichern, falls vorhanden
					if (!singleRawDataRow[3].equals("null")) {
						comments += "<p id=wholeComment>" + singleRawDataRow[0] + " | " + singleRawDataRow[1] + " | "
								+ singleRawDataRow[2] + " | " + singleRawDataRow[4] + " | " + "<span id=theComment>"
								+ singleRawDataRow[3] + "</span></p>";
					}
				}

			}
			table += doEvaluation(semiSortedArrayList, casesString);
			table_TB4 += doEvaluation(semiSortedArrayList_TB4, casesString);
		}
		table_TB4 += "<tr>" + "<th rowspan='2'>&sum; aller Anfragen " + overallCounter + "<br/> davon TB4 " + tb4Counter
				+ "</th>" + "<th colspan='2'>" + overallNone + "</th>" + "<th colspan='2'>" + overallWindows + "</th>"
				+ "<th colspan='2'>" + overallMacos + "</th>" + "<th colspan='2'>" + overallLinux + "</th>"
				+ "<th colspan='2'>" + overallAndroid + "</th>" + "<th colspan='2'>" + overallMisc + "</th>"
				+ "<th colspan='2'></th>" + "</tr><tr>" + "<td class='center blue' colspan='6'>Vormittags("
				+ overallVormittags + ")</td><td class='center blue' colspan='6'>Nachmittags(" + overallNachmittags
				+ ")</td><td class='center blue'></td></tr>" + "</table>";

		comments += "</div>";
		html += table + table_TB4 + comments + "</body></html>";
		evaluate_filewriter = new Evaluate_FILEWRITER(html);
	}

	private String doEvaluation(ArrayList<String[]> myList, String actualCase) {
		none_vormittags = 0;
		none_nachmittags = 0;
		windows_vormittags = 0;
		windows_nachmittags = 0;
		macos_vormittags = 0;
		macos_nachmittags = 0;
		linux_vormittags = 0;
		linux_nachmittags = 0;
		android_vormittags = 0;
		android_nachmittags = 0;
		misc_vormittags = 0;
		misc_nachmittags = 0;
		tr = "";
		overallCase = 0;

		for (String[] singleRawCase : myList) {
			String[] theDate = singleRawCase[0].split(" ");
			String[] theTime = theDate[3].split(":");
			String st_hAm = theTime[0] + theTime[1];
			int int_hAm = Integer.parseInt(st_hAm);
			overallCase++;
			switch (singleRawCase[2]) {
			case "none":
				if (int_hAm <= 1259) {
					none_vormittags++;
					overallVormittags++;
				} else if (int_hAm >= 1300) {
					none_nachmittags++;
					overallNachmittags++;
				}
				overallNone++;
				break;
			case "windows":
				if (int_hAm <= 1259) {
					windows_vormittags++;
					overallVormittags++;
				} else if (int_hAm >= 1300) {
					windows_nachmittags++;
					overallNachmittags++;
				}
				overallWindows++;
				break;
			case "macos":
				if (int_hAm <= 1259) {
					macos_vormittags++;
					overallVormittags++;
				} else if (int_hAm >= 1300) {
					macos_nachmittags++;
					overallNachmittags++;
				}
				overallMacos++;
				break;
			case "linux":
				if (int_hAm <= 1259) {
					linux_vormittags++;
					overallVormittags++;
				} else if (int_hAm >= 1300) {
					linux_nachmittags++;
					overallNachmittags++;
				}
				overallLinux++;
				break;
			case "android":
				if (int_hAm <= 1259) {
					android_vormittags++;
					overallVormittags++;
				} else if (int_hAm >= 1300) {
					android_nachmittags++;
					overallNachmittags++;
				}
				overallAndroid++;
				break;
			case "misc":
				if (int_hAm <= 1259) {
					misc_vormittags++;
					overallVormittags++;
				} else if (int_hAm >= 1300) {
					misc_nachmittags++;
					overallNachmittags++;
				}
				overallMisc++;
				break;
			}

		}
		tr += "<tr class=data>" + "<td>" + actualCase + "<td>" + none_vormittags + "</td>" + "<td>" + none_nachmittags
				+ "</td>" + "<td>" + windows_vormittags + "</td>" + "<td>" + windows_nachmittags + "</td>" + "<td>"
				+ macos_vormittags + "</td>" + "<td>" + macos_nachmittags + "</td>" + "<td>" + linux_vormittags
				+ "</td>" + "<td>" + linux_nachmittags + "</td>" + "<td>" + android_vormittags + "</td>" + "<td>"
				+ android_nachmittags + "</td>" + "<td>" + misc_vormittags + "</td>" + "<td>" + misc_nachmittags
				+ "</td>" + "<td>" + overallCase + "</td>" + "</tr>";

		return tr;
	}

}
