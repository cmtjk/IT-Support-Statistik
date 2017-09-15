package de.r3r57.itsupport.statistik;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Record_GUI extends Scene {

	private final String s_version = "vers. 1.2a(JFX-Version)";
	private final String s_info = "#1.2a(JFX-Version) - 17.03.2015\n\n" + "Änderungen:\n" + "* HTML angepasst\n"
			+ "\n\n" + "#1.2(JFX-Version) - 16.02.2015\n\n" + "Änderungen:\n"
			+ "* Die meisten Aufgaben laufen jetzt in Threads (Auslesen, Auswerten, Löschen)\n"
			+ "* Prozessindikatoren für Threads hinzugefügt\n" + "\n\n" + "#1.1h(JFX-Version) - 15.02.2015\n\n"
			+ "Änderungen:\n" + "* Performance beim Auslesen weiter verbessert\n"
			+ "* Performance beim Auswerten verbessert\n" + "* Monat 'von' standartmäßig auf '2015' gesetzt\n" + "\n\n"
			+ "#1.1g(JFX-Version) - 12.02.2015\n\n" + "Änderungen:\n" + "* Performance beim Auslesen verbessert\n"
			+ "\n\n" + "#1.1f(JFX-Version) - 30.01.2015\n\n" + "Änderungen:\n" + "* Icon eingebunden\n" + "\n\n"
			+ "#1.1e(JFX-Version) - 24.01.2015\n\n" + "bis 30.01. parallel auch Strichliste führen\n\n"
			+ "Änderungen:\n" + "* Stylesheet eingebunden, um Größe der Elemente anzupassen\n"
			+ "* Bei Betriebssystemen kann nun auch 'sonstige' gewählt werden. Auswertung entsprechend angepasst.\n"
			+ "* Bei der Auswertung kann nun auch '2014' als Jahr angegeben werden\n" + "\n\n"
			+ "#1.1d(JFX-Version) - 23.01.2015\n\n" + "bis 30.01. parallel auch Strichliste führen\n\n"
			+ "Änderungen:\n" + "* Optionen gruppiert\n"
			+ "* Man sieht nun das aktuelle Datum und kann dieses ändern um Anfragen nachzutragen (ACHTUNG: vormittags/nachmittags kann (noch) nicht eingestellt werden). Nachträge sollten zur jeweiligen Zeit nachgetragen werden, bzw. kann die System-Zeit des PCs einfach umgestellt werden."
			+ "\n\n" + "#1.1c(JFX-Version) - 22.01.2015\n\n" + "bis 30.01. parallel auch Strichliste führen\n\n"
			+ "Änderungen:\n" + "* Statistik_*.html werden jetzt im Unterordner 'statistiken' gespeichert\n"
			+ "* vorläufige Möglichkeit bis zu 10 Einträge der selben Art mit einem Klick hinzuzufügen (Slider)\n"
			+ "* Nachmittags-Counter repariert\n" + "\n\n" + "#1.1b(JFX-Version) - 20.01.2015\n\n"
			+ "bis 30.01. parallel auch Strichliste führen\n\n" + "Änderungen:\n"
			+ "* Funktioniert jetzt auch auf altem PC mit Starten(nur_alter_PC).bat\n"
			+ "* Auswertung vorläuft implementiert, könnte noch buggy sein\n"
			+ "* Beschreibung für Anfragetyp steht nun nach Auswahl im Kommentarfeld\n"
			+ "* In den Optionen ist die aktuelle Größe (beim Start des Programms) der statistik.dat einsehbar (Warum? siehe Tooltip)\n"
			+ "* Tooltip hinzugefügt: 'statistik.dat <Filesize>'\n"
			+ "* Einträge sind jetzt wieder löschbar (Log -> Rechtsklick auf Eintrag -> Löschen)\n"
			+ "* Schließen des Programms sollte jetzt auch alle Threads schließen\n"
			+ "* 'Speichern' Button nach Eingabe kurzzeitig deaktiviert, um Doppelklicks zu verhindern\n"
			+ "* Der Zeit-Bug wurde behoben (REF:#2015011910008463)\n"
			+ "* Der Backup-Button gibt ein Feedback bei Erfolg\n" + "\n\n" + "#1.1a(JFX-Version) - 19.01.2015\n\n"
			+ "bis 30.01. parallel auch Strichliste führen\n\n" + "Änderungen:"
			+ "* Tooltips hinzugefügt: 'Log: Auto-Refresh'\n"
			+ "* Die BS-Auswahl ist für Anfragen ohne Unterscheidung deaktiviert und auf 'BS unabhängig' gesetzt\n"
			+ "* 'Auswertung' deaktiviert\n"
			+ "* Log aktualisiert sich nun jedesmal automatisch, wenn der Reiter 'Log' ausgewählt wird oder eine Anfrage hinzugefügt wurde (nicht clientübergreifend, deaktivierbar mit 'Log: Auto-Refresh')\n"
			+ "* Checkbox 'Log: Auto Refresh' in Optionen verschoben\n" + "\n\n" + "#1.1(JFX-Version) - 18.01.2015\n\n"
			+ "Wichtig: jre8 installieren\n" + "https://www.java.com/de/download/\n\n"
			+ "bis 30.01. parallel auch Strichliste führen\n\n" + "Änderungen\n" + "* Auswertung entfernt\n"
			+ "* Löschen von Einträgen nicht möglich\n" + "* Ändern von Anfragetypen nicht möglich\n"
			+ "* keine Tooltips";

	private GridPane mainGridPane, commentsOSPane, localSavePane;
	private ChoiceBox<String> cb_anfragen;
	private TextArea ta_comments, ta_changes;
	private RadioButton rb_none, rb_windows, rb_macos, rb_linux, rb_android, rb_misc, rb_local, rb_tel;
	private ToggleGroup bsToggleGroup, ltToggleGroup;
	private VBox bsVBox, logVBox, optionVBox, versionVBox;
	private ToggleButton tb_tb4, tb_multi;
	private Button b_save, b_refreshLog, b_backup, b_dataLocation, b_continue;
	private HBox ltHBox, logHBox;
	private Tab t_record, t_evaluate, t_info, t_log, t_options;
	private TabPane mainTabPane;
	private Record_FILEREADER record_filereader;
	private Record_FILEWRITER record_filewriter;
	private String operatingSystem, escapedKommentar, tb4_string, myFilePath;
	private Timeline timeline;
	private ListView<String> lv_log;
	private Label lv_logLABEL, l_comingSoon, l_fileSize;
	private CheckBox cb_autoRefresh;
	private String[] casesAll, casesString, descriptionString, bsNone;
	private FadeTransition lv_logFadeTransition, b_saveFadeTransition, tb_tb4FadeTransition, b_backupFadeTransition;
	private final ContextMenu contextmenu_log;
	private Slider count;
	private boolean abortGenerate;
	private ProgressIndicator pi_log, pi_evaluate;
	private ObservableList<String> myObservableList;

	private final String[] yearsString = { "2014", "2015", "2016" };
	private final String[] monthFromString = { "Alle", "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli",
			"August", "September", "Oktober", "November", "Dezember" };

	public Record_GUI() throws IOException {
		super(new VBox(), 675, 375);

		record_filereader = new Record_FILEREADER();
		record_filewriter = new Record_FILEWRITER();

		myFilePath = new File("").getAbsolutePath();

		File stylesheet = new File(myFilePath + "/config/statistic.css");
		getStylesheets().add("file:///" + stylesheet.getAbsolutePath().replace("\\", "/"));

		// ->Config Initialisieren und in Option + Beschreibung splitten
		casesAll = record_filereader.read_cfgFile();

		casesString = new String[casesAll.length];
		descriptionString = new String[casesAll.length];
		bsNone = new String[casesAll.length];
		for (int index = 0; index < casesAll.length; index++) {
			String[] splitCase = casesAll[index].split(" # ");
			casesString[index] = splitCase[0];
			bsNone[index] = splitCase[1];
			descriptionString[index] = splitCase[2];

		}

		// ProgressIndicators
		pi_log = new ProgressIndicator();
		pi_log.setScaleX(0.25);
		pi_log.setScaleY(0.25);
		pi_log.setVisible(false);

		ProgressIndicator pi_evaluate = new ProgressIndicator();
		pi_evaluate.setScaleX(0.25);
		pi_evaluate.setScaleY(0.25);
		pi_evaluate.setVisible(false);

		// TabPane
		mainTabPane = new TabPane();
		mainTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
				if (mainTabPane.getSelectionModel().getSelectedItem().equals(t_log)) {
					if (cb_autoRefresh.isSelected()) {
						logVBox.setDisable(true);
						pi_log.setVisible(true);
						refreshLog();
					}

				}
			}
		});

		// Tabs
		t_record = new Tab("Aufnehmen");
		t_evaluate = new Tab("Auswerten");
		t_info = new Tab(s_version);
		t_log = new Tab("Log");
		t_options = new Tab("Optionen");

		t_record.setDisable(true);
		t_evaluate.setDisable(true);
		t_log.setDisable(true);
		t_options.setDisable(true);

		t_info.setStyle("-fx-font-weight: bold;");

		t_options.setClosable(false);
		t_info.setClosable(false);
		t_log.setClosable(false);
		t_record.setClosable(false);
		t_evaluate.setClosable(false);

		t_evaluate.setContent(bsVBox);

		mainTabPane.getTabs().addAll(t_info, t_record, t_evaluate, t_log, t_options);

		((VBox) this.getRoot()).getChildren().addAll(mainTabPane);

		// ===============================================VERSION
		versionVBox = new VBox();
		versionVBox.setPadding(new Insets(25, 25, 25, 25));
		versionVBox.setSpacing(10);
		versionVBox.setAlignment(Pos.CENTER);

		// TextArea
		ta_changes = new TextArea(s_info);
		ta_changes.setEditable(false);
		ta_changes.setWrapText(true);
		ta_changes.setFont(Font.font("Monospaced", 14));
		ta_changes.setPrefHeight(250);

		// Continue-Button
		b_continue = new Button("Weiter");
		b_continue.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				t_info.setDisable(true);
				t_record.setDisable(false);
				t_evaluate.setDisable(false);
				t_log.setDisable(false);
				t_options.setDisable(false);
				mainTabPane.getSelectionModel().selectNext();
			}
		});

		versionVBox.getChildren().addAll(ta_changes, b_continue);

		// ================================================AUFNEHMEN
		// PANEL: Gesamtes Fenster
		mainGridPane = new GridPane();
		mainGridPane.setAlignment(Pos.CENTER);
		mainGridPane.setHgap(20);
		mainGridPane.setVgap(20);
		mainGridPane.setPadding(new Insets(25, 25, 25, 25));

		// Anfragen-ChoiceBox

		cb_anfragen = new ChoiceBox<String>(FXCollections.observableArrayList(casesString));
		cb_anfragen.setPrefWidth(450);
		cb_anfragen.getSelectionModel().selectFirst();

		cb_anfragen.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number n, Number n1) {
				ta_comments.setPromptText(descriptionString[(int) n1]);
				if (bsNone[(int) n1].equals("true")) {
					rb_none.setSelected(true);
					rb_windows.setDisable(true);
					rb_macos.setDisable(true);
					rb_linux.setDisable(true);
					rb_android.setDisable(true);
					rb_misc.setDisable(true);
				} else {
					rb_windows.setDisable(false);
					rb_macos.setDisable(false);
					rb_linux.setDisable(false);
					rb_android.setDisable(false);
					rb_misc.setDisable(false);
				}
			}
		});

		mainGridPane.add(cb_anfragen, 0, 0);

		// PANEL: Kommentar+Date-VBox und BS-Auswahl
		commentsOSPane = new GridPane();
		commentsOSPane.setHgap(25);

		// Kommentarbox
		ta_comments = new TextArea();
		ta_comments.setWrapText(true);
		ta_comments.setPromptText("Kommentar (optional)");
		ta_comments.setPrefWidth(300);

		commentsOSPane.add(ta_comments, 0, 0);
		commentsOSPane.setPrefWidth(450);

		// BS-Auswahl
		bsVBox = new VBox();

		rb_none = new RadioButton("unabhängig");
		rb_windows = new RadioButton("Windows");
		rb_macos = new RadioButton("Mac OS/iOS");
		rb_linux = new RadioButton("Linux");
		rb_android = new RadioButton("Android");
		rb_misc = new RadioButton("sonstige");

		rb_none.setSelected(true);

		if (bsNone[0].equals("true")) {
			rb_windows.setDisable(true);
			rb_macos.setDisable(true);
			rb_linux.setDisable(true);
			rb_android.setDisable(true);
			rb_misc.setDisable(true);
		}

		bsToggleGroup = new ToggleGroup();

		rb_none.setToggleGroup(bsToggleGroup);
		rb_windows.setToggleGroup(bsToggleGroup);
		rb_macos.setToggleGroup(bsToggleGroup);
		rb_linux.setToggleGroup(bsToggleGroup);
		rb_android.setToggleGroup(bsToggleGroup);
		rb_misc.setToggleGroup(bsToggleGroup);

		bsVBox.getChildren().addAll(rb_none, rb_windows, rb_macos, rb_linux, rb_android, rb_misc);

		bsVBox.setSpacing(10);
		bsVBox.setPrefSize(200, 150);
		bsVBox.setPadding(new Insets(15, 0, 0, 0));

		commentsOSPane.add(bsVBox, 1, 0);

		mainGridPane.add(commentsOSPane, 0, 1);

		// Date-Picker
		DatePicker dp_date = new DatePicker(LocalDate.now());
		mainGridPane.add(dp_date, 1, 0);

		// TitledPane + SaveButton VBox
		VBox tpSaveVBox = new VBox();
		tpSaveVBox.setSpacing(10);

		// More-Options HBox
		VBox moreOptionsVBox = new VBox();
		moreOptionsVBox.setSpacing(10);

		// Für mehrere Einträge - Button
		tb_multi = new ToggleButton("Mehrere Einträge?");
		tb_multi.setPrefWidth(150);
		tb_multi.setScaleX(0.75);
		tb_multi.setScaleY(0.75);
		tb_multi.setMaxWidth(Double.MAX_VALUE);

		tb_multi.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				tb_tb4FadeTransition = new FadeTransition(Duration.millis(750), tb_multi);
				tb_tb4FadeTransition.setFromValue(0.1);
				tb_tb4FadeTransition.setToValue(1.0);
				if (tb_multi.isSelected()) {
					count.setDisable(false);
					tb_multi.setStyle("-fx-color: red;");
					tb_tb4FadeTransition.play();
				} else if (!tb_multi.isSelected()) {
					count.setDisable(true);
					count.setValue(1);
					tb_multi.setStyle("");
					tb_tb4FadeTransition.play();
				}

			}
		});

		// TB4-ToggleButton
		tb_tb4 = new ToggleButton("In TB4?");
		tb_tb4.setPrefWidth(150);
		tb_tb4.setScaleX(0.75);
		tb_tb4.setScaleY(0.75);
		tb_tb4.setMaxWidth(Double.MAX_VALUE);

		// ->Handler
		tb_tb4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				tb_tb4FadeTransition = new FadeTransition(Duration.millis(750), tb_tb4);
				tb_tb4FadeTransition.setFromValue(0.1);
				tb_tb4FadeTransition.setToValue(1.0);
				if (tb_tb4.isSelected()) {
					rb_local.setDisable(false);
					rb_tel.setDisable(false);
					tb_tb4.setStyle("-fx-color: yellow;");
					tb_tb4FadeTransition.play();
				} else if (!tb_tb4.isSelected()) {
					rb_local.setDisable(true);
					rb_tel.setDisable(true);
					tb_tb4.setStyle("");
					tb_tb4FadeTransition.play();
				}

			}
		});

		// slider
		count = new Slider();
		count.setMin(1);
		count.setMax(10);
		count.setValue(1);
		count.setShowTickLabels(true);
		count.setShowTickMarks(true);
		count.setBlockIncrement(1);

		count.setDisable(true);
		count.setMinorTickCount(0);
		count.setMajorTickUnit(1.0);
		count.setSnapToTicks(true);

		// Lokal und Tel.

		ltHBox = new HBox();
		ltHBox.setScaleX(0.75);
		ltHBox.setScaleY(0.75);
		ltHBox.setSpacing(10);
		ltHBox.setMaxWidth(Double.MAX_VALUE);

		ltToggleGroup = new ToggleGroup();

		rb_local = new RadioButton("lokal");
		rb_tel = new RadioButton("tel/OTRS");

		rb_local.setMinWidth(75);
		rb_tel.setMinWidth(100);

		rb_local.setMaxWidth(Double.MAX_VALUE);
		rb_tel.setMaxWidth(Double.MAX_VALUE);

		rb_local.setToggleGroup(ltToggleGroup);
		rb_tel.setToggleGroup(ltToggleGroup);

		rb_local.setSelected(true);

		rb_local.setDisable(true);
		rb_tel.setDisable(true);

		ltHBox.getChildren().addAll(rb_local, rb_tel);

		moreOptionsVBox.getChildren().addAll(tb_multi, count, tb_tb4, ltHBox);

		// Weitere Einstellungen-Pane
		TitledPane moreOptionsPane = new TitledPane("weitere Optionen", moreOptionsVBox);
		moreOptionsPane.setCollapsible(false);

		// Speichern-Button
		b_save = new Button("Speichern");
		b_save.setMaxWidth(Double.MAX_VALUE);

		// ->Handler
		b_save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent evt) {
				operatingSystem = "";
				if (rb_none.isSelected())
					operatingSystem = "none";
				if (rb_windows.isSelected())
					operatingSystem = "windows";
				if (rb_macos.isSelected())
					operatingSystem = "macos";
				if (rb_linux.isSelected())
					operatingSystem = "linux";
				if (rb_android.isSelected())
					operatingSystem = "android";
				if (rb_misc.isSelected())
					operatingSystem = "misc";
				if (ta_comments.getText().isEmpty()) {
					ta_comments.setText("null");
				}
				escapedKommentar = ta_comments.getText();
				if (escapedKommentar.contains("\n")) {
					escapedKommentar = escapedKommentar.replace("\n", " ");
				}
				if (escapedKommentar.contains(" # ")) {
					escapedKommentar = escapedKommentar.replace(" # ", " ");
				}
				if (tb_tb4.isSelected()) {
					if (rb_local.isSelected()) {
						tb4_string = "tb4(lokal)";
					} else if (rb_tel.isSelected()) {
						tb4_string = "tb4(tel/OTRS)";
					}
				} else {
					tb4_string = "null";
				}
				String daten = cb_anfragen.getValue() + " # " + operatingSystem + " # " + escapedKommentar + " # "
						+ tb4_string;
				System.out.println(daten);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
				String theDate = dp_date.getValue().format(formatter).toString();

				if (record_filewriter.writeToFile(daten, (int) count.getValue(), theDate)) {
					b_save.setDisable(true);
					b_save.setText("Gespeichert!");
					b_save.setStyle("-fx-base: #b6e7c9; -fx-font-weight: bold;");
					b_saveFadeTransition = new FadeTransition(Duration.millis(1500), b_save);
					b_saveFadeTransition.setFromValue(0.1);
					b_saveFadeTransition.setToValue(1.0);
					b_saveFadeTransition.play();
					timeline.play();

					tb_multi.setSelected(false);
					tb_multi.setStyle("");
					count.setDisable(true);
					count.setValue(1);
					cb_anfragen.getSelectionModel().selectFirst();
					rb_none.fire();
					ta_comments.setText("");
					ta_comments.setPromptText("Kommentar (optional)");
				}
			}
		});

		tpSaveVBox.getChildren().addAll(moreOptionsPane, b_save);
		mainGridPane.add(tpSaveVBox, 1, 1);

		// Timer
		timeline = new Timeline(new KeyFrame(Duration.millis(1500), ae -> timerstart()));

		// =======================================================Auswertung
		VBox evaluateVBox = new VBox();
		evaluateVBox.setPadding(new Insets(25, 25, 25, 25));
		evaluateVBox.setSpacing(25);
		evaluateVBox.setAlignment(Pos.CENTER);

		// Erklärung
		Label description = new Label();
		description.setAlignment(Pos.CENTER);
		description.setWrapText(true);
		description.setText("Erstellt aus den Daten eine Übersicht im HTML-Format im Stammverzeichnis.");

		// Auswahl

		HBox yearmonthChoiceHBox = new HBox();
		yearmonthChoiceHBox.setSpacing(10);
		yearmonthChoiceHBox.setAlignment(Pos.CENTER);

		ChoiceBox<String> cb_year = new ChoiceBox<String>(FXCollections.observableArrayList(yearsString));
		ChoiceBox<String> cb_monthFrom = new ChoiceBox<String>(FXCollections.observableArrayList(monthFromString));
		ChoiceBox<String> cb_monthTo = new ChoiceBox<String>();

		cb_year.setValue("2015");
		cb_monthFrom.getSelectionModel().selectFirst();
		cb_monthTo.getSelectionModel().selectFirst();

		cb_monthFrom.setPrefWidth(125);
		cb_monthTo.setPrefWidth(125);
		cb_monthTo.setDisable(true);

		cb_monthFrom.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number n, Number n1) {
				int fromThisMonth = cb_monthFrom.getSelectionModel().getSelectedIndex();
				String[] tempStringForModel = Arrays.copyOfRange(monthFromString, fromThisMonth,
						monthFromString.length);
				cb_monthTo.setItems(FXCollections.observableArrayList(tempStringForModel));
				cb_monthTo.setDisable(false);
				cb_monthTo.getSelectionModel().selectFirst();
			}
		});

		Label l_from = new Label("von");
		Label l_to = new Label("bis");

		yearmonthChoiceHBox.getChildren().addAll(cb_year, l_from, cb_monthFrom, l_to, cb_monthTo);

		// Buttons zum Generieren und Speicherort öffnen
		HBox openGenerateHBox = new HBox();
		openGenerateHBox.setSpacing(50);
		openGenerateHBox.setPrefHeight(75);
		openGenerateHBox.setAlignment(Pos.CENTER);

		// Button Open
		Button b_openDirec = new Button("Speicherort öffnen");
		b_openDirec.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				try {
					Desktop.getDesktop().open(new File(myFilePath));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// Button generate
		Button b_generate = new Button("Generieren");
		b_generate.maxWidth(b_generate.getWidth());
		b_generate.maxHeight(b_generate.getHeight());
		b_generate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {

				new Thread() {
					public void run() {
						Platform.runLater(new Runnable() {
							public void run() {
								evaluateVBox.setDisable(true);
								pi_evaluate.setVisible(true);
							}
						});
						try {
							Evaluate_EVALUATION evaluation = new Evaluate_EVALUATION(casesString,
									new File(myFilePath + "/data/statistik.dat"), cb_year.getValue(),
									cb_monthFrom.getValue(), cb_monthTo.getValue());
						} catch (IOException ex) {
						}

						Platform.runLater(new Runnable() {
							public void run() {
								pi_evaluate.setVisible(false);
								evaluateVBox.setDisable(false);
							}
						});
					}
				}.start();

			}
		});

		openGenerateHBox.getChildren().addAll(b_openDirec, b_generate);

		evaluateVBox.getChildren().addAll(description, yearmonthChoiceHBox, openGenerateHBox);

		StackPane evaluateStackPane = new StackPane();
		evaluateStackPane.getChildren().addAll(evaluateVBox, pi_evaluate);

		// ======================================================Log-Tab
		logVBox = new VBox();
		logVBox.setPadding(new Insets(25, 25, 25, 25));
		logVBox.setSpacing(10);

		// Kopfleiste mit Refresh-Button
		logHBox = new HBox();
		logHBox.setAlignment(Pos.CENTER);
		logHBox.setSpacing(25);

		b_refreshLog = new Button("Refresh");
		b_refreshLog.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				logVBox.setDisable(true);
				pi_log.setVisible(true);
				refreshLog();

			}
		});

		// Text-Log + Label
		lv_logLABEL = new Label();

		logHBox.getChildren().addAll(lv_logLABEL, b_refreshLog);

		lv_log = new ListView<String>();
		lv_log.setPrefWidth(Double.MAX_VALUE);
		lv_log.setItems(record_filereader.readFile());
		lv_log.scrollTo(Integer.MAX_VALUE);

		// ->Kontext Menü
		contextmenu_log = new ContextMenu();
		MenuItem deleteItem_cbLog = new MenuItem("Löschen");
		deleteItem_cbLog.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String stringToDelete = lv_log.getSelectionModel().getSelectedItem();
				new Thread() {
					public void run() {
						Platform.runLater(new Runnable() {
							public void run() {
								pi_log.setVisible(true);
								logVBox.setDisable(true);
							}
						});
						try {

							record_filewriter.deleteString(stringToDelete);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Platform.runLater(new Runnable() {
							public void run() {
								try {
									lv_log.setItems(record_filereader.readFile());
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								lv_log.scrollTo(Integer.MAX_VALUE);
								lv_logLABEL.setText("Heutige Anfragen: " + lv_log.getItems().size());
								pi_log.setVisible(false);
								logVBox.setDisable(false);
							}
						});

					}
				}.start();
			}
		});

		contextmenu_log.getItems().addAll(deleteItem_cbLog);
		lv_log.setContextMenu(contextmenu_log);

		lv_logLABEL.setText("Heutige Anfragen: " + lv_log.getItems().size());

		logVBox.getChildren().addAll(logHBox, lv_log);

		StackPane logStackPane = new StackPane();
		logStackPane.getChildren().addAll(logVBox, pi_log);

		// ===========================================OPTIONEN

		optionVBox = new VBox();
		optionVBox.setPadding(new Insets(25, 25, 25, 25));
		optionVBox.setSpacing(10);
		optionVBox.setAlignment(Pos.CENTER);
		optionVBox.setPrefWidth(250);

		// Log: Auto-Refresh
		cb_autoRefresh = new CheckBox();
		cb_autoRefresh.setText("Log: Auto-Refresh");
		cb_autoRefresh.setSelected(true);
		cb_autoRefresh.setTooltip(new Tooltip("Aktiviert/Deaktiviert die automatische Aktualisierung des Log"));

		l_fileSize = new Label();
		l_fileSize.setTextAlignment(TextAlignment.CENTER);
		l_fileSize.setText(
				"Aktuelle Dateigröße (statistik.dat):\n" + record_filereader.getFile().length() / 1000 + " KBytes");
		Tooltip tt_filesize = new Tooltip(
				"Wegen des Netzlaufwerks, kann es ab einer bestimmten Größe (vermutlich ab >3MB = 50 000 Einträge = 5 Jahre IT-Support) zu Verzögerungen beim Speichern und Löschen von Einträgen kommen. Dies sollte Anfangs zumindest beobachtet werden.");
		tt_filesize.setWrapText(true);
		tt_filesize.setMaxWidth(250);
		l_fileSize.setTooltip(tt_filesize);

		// Backup-Button
		b_backup = new Button("Backup statistik.dat");
		b_backup.setMinWidth(optionVBox.getPrefWidth());
		b_backup.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				try {
					if (record_filewriter.backupFile()) {
						b_backup.setDisable(true);
						b_backup.setText("Erfolgreich!");
						b_backup.setStyle("-fx-base: #b6e7c9; -fx-font-weight: bold;");
						b_backupFadeTransition = new FadeTransition(Duration.millis(1500), b_backup);
						b_backupFadeTransition.setFromValue(0.1);
						b_backupFadeTransition.setToValue(1.0);
						b_backupFadeTransition.play();
						timeline.play();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// Speicherort-Button
		b_dataLocation = new Button("Speicherort öffnen");
		b_dataLocation.setMinWidth(optionVBox.getPrefWidth());
		b_dataLocation.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				try {
					Desktop.getDesktop().open(new File(myFilePath));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		optionVBox.getChildren().addAll(cb_autoRefresh, b_backup, b_dataLocation, l_fileSize);

		// GUI ENDE
		// Tabs füllen mainScene setzen und Window anzeigen
		t_info.setContent(versionVBox);
		t_options.setContent(optionVBox);
		t_evaluate.setContent(evaluateStackPane);
		t_record.setContent(mainGridPane);
		t_log.setContent(logStackPane);

	}

	private void timerstart() {
		b_save.setDisable(false);
		b_save.setText("Speichern");
		b_save.setStyle("");

		b_backup.setDisable(false);
		b_backup.setText("Backup statistik.dat");
		b_backup.setStyle("");

	}

	private void refreshLog() {
		new Thread() {
			public void run() {

				try {

					myObservableList = record_filereader.readFile();

				} catch (IOException e) {
					// TODO Auto-generated catch
					// block
					e.printStackTrace();
				}

				Platform.runLater(new Runnable() {
					public void run() {
						lv_log.setItems(myObservableList);
						lv_log.scrollTo(Integer.MAX_VALUE);
						lv_logLABEL.setText("Heutige Anfragen: " + lv_log.getItems().size());
						pi_log.setVisible(false);
						logVBox.setDisable(false);

					}
				});

			}

		}.start();
	}

}
