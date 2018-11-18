package application;

import java.awt.color.CMMException;
import java.time.LocalDate;

import database.AsistentDAO;
import database.ConsultatieDAO;
import database.FacturaDAO;
import database.MedicDAO;
import database.PacientDAO;
import database.RetetaDAO;
import database.UserDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.cabinetMedical.Consultatie;
import model.cabinetMedical.Consultatie.Status;
import model.cabinetMedical.Factura;
import model.cabinetMedical.Reteta;
import model.user.Medic;
import model.user.Pacient;
import model.user.User;
import model.user.User.Rol;

public class AsistentController {

	private static double TVA = 0.19;

	@FXML
	private TabPane tabPaneAsistent;

	@FXML
	private Pane creareConsultatiePane;
	@FXML
	private DatePicker dataConsultatie;

	@FXML
	private DatePicker dataConsultatieCautata;

	@FXML
	private TextArea detaliiTextArea;

	@FXML
	private ComboBox<Medic> comboBoxMedic;
	@FXML
	private ComboBox<Pacient> comboBoxPacient;
	@FXML
	private Label progLblNotification;
	@FXML
	private Hyperlink logout;

	@FXML
	private Button btnCautarePacient;

	@FXML
	private Tab tabGestiunePacienti;

	@FXML
	private Label messageLbl;

	@FXML
	private Label messageLabel;

	@FXML
	private Tab tabGestiuneReteta;

	@FXML
	private TableView tableView;

	@FXML
	private Button btnAnulareConsult;

	@FXML
	private Button btnListaConsultatii;

	@FXML
	private TextField cnpPacientCautatLabel;

	@FXML
	private VBox tableHolder;

	@FXML
	private Tab tabGestiuneFacturi;

	@FXML
	private Button btnCreareFactura;

	@FXML
	private TableView tableViewFacturi;

	@FXML
	private Button btnListaFacturi;

	@FXML
	private VBox tableHolderFacturi;

	@FXML
	private DatePicker dataFactura;

	@FXML
	private TextField valoareFactura;

	@FXML
	private Pane creazaFacturaPane;

	@FXML
	private ComboBox<Consultatie> comboBoxConsultatie;

	@FXML
	private Button btnInsertFactura;
	@FXML
	private Label msgLbl;

	// gestiune reteta
	@FXML
	private Button btnStergeReteta;

	@FXML
	private TextField medicamente;

	@FXML
	private ComboBox<Consultatie> cmbBoxConsultatieReteta;

	@FXML
	private DatePicker dataReteta;

	@FXML
	private Label mesajLblReteta;

	@FXML
	private Pane creareRetetaPane;

	@FXML
	private CheckBox compensat;

	@FXML
	private Button btnListaRetete;

	@FXML
	private Button btnCreareReteta;

	@FXML
	private TableView tableViewRetete;

	@FXML
	private VBox tableHolderRetete;

	@FXML
	public void initialize() {
		try {
			this.comboBoxConsultatie.setItems(FXCollections.observableArrayList(new ConsultatieDAO().getAllConsultatii()));
			this.comboBoxConsultatie.getSelectionModel().selectFirst();
			this.dataFactura.setValue(LocalDate.now());
			this.cmbBoxConsultatieReteta
					.setItems(FXCollections.observableArrayList(new ConsultatieDAO().getAllConsultatii()));
			this.cmbBoxConsultatieReteta.getSelectionModel().selectFirst();
			this.dataReteta.setValue(LocalDate.now());
			// force the field to be numeric only
			valoareFactura.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						valoareFactura.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});
			this.comboBoxMedic.setItems(FXCollections.observableArrayList(new MedicDAO().getAllMedici()));
			this.comboBoxPacient.setItems(FXCollections.observableArrayList(new PacientDAO().getAllPacienti()));
			this.dataConsultatie.setValue(LocalDate.now());
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	// Gestiune Pacienti

	@FXML
	void programareConsultatieShow(ActionEvent event) {
		creareConsultatiePane.setVisible(true);

	}

	@FXML
	void btnCreareConsultatieAction(ActionEvent event) {
		if (detaliiTextArea.getText().trim().equals("") || dataConsultatie.getValue() == null
				|| comboBoxMedic.getSelectionModel().getSelectedItem() == null
				|| comboBoxPacient.getSelectionModel().getSelectedItem() == null) {
			progLblNotification.setText("Completati toate campurile!");
		} else {
			Consultatie consultatie = new Consultatie(1, comboBoxPacient.getSelectionModel().getSelectedItem().getId(),
					comboBoxMedic.getSelectionModel().getSelectedItem().getId(), null, null, Status.IN_DESFASURARE,
					dataConsultatie.getValue(), detaliiTextArea.getText());
			new ConsultatieDAO().addConsultatie(consultatie);

			if (tableView != null & tableView.getItems() != null) {
				tableView.getItems().add(consultatie);
			}
		}
	}

	/*
	 * metoda pentru afisarea tuturor consultatiilor intr-un tabel
	 */
	@FXML
	void btnListaConsultatiiAction(ActionEvent event) {
		TableColumn idCol = new TableColumn("Id");
		TableColumn idPacientCol = new TableColumn("Id Pacient");
		TableColumn idMedicCol = new TableColumn("Id Medic");
		TableColumn idRetetaCol = new TableColumn("Id Reteta");
		TableColumn idFacturaCol = new TableColumn("Id Factura");
		TableColumn statusCol = new TableColumn("Status");
		TableColumn dataCol = new TableColumn("Data");
		TableColumn detaliiCol = new TableColumn("Detalii");

		idCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("id"));
		idPacientCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("idPacient"));
		idMedicCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("idMedic"));
		idRetetaCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("idReteta"));
		idFacturaCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("idFactura"));
		statusCol.setCellValueFactory(new PropertyValueFactory<Consultatie, String>("status"));
		dataCol.setCellValueFactory(new PropertyValueFactory<Consultatie, String>("data"));
		detaliiCol.setCellValueFactory(new PropertyValueFactory<Consultatie, String>("detalii"));

		tableView = new TableView<>();
		if (tableHolder.getChildren() != null) {
			tableHolder.getChildren().clear();
		}
		tableHolder.getChildren().add(tableView);
		tableView.getColumns().clear();

		tableView.getColumns().addAll(idCol, idPacientCol, idMedicCol, idRetetaCol, idFacturaCol, statusCol, dataCol,
				detaliiCol);
		messageLbl.setText("Consultatii pacienti");

		tableView.setItems(FXCollections.observableArrayList(new ConsultatieDAO().getAllConsultatii()));
	}
	/*
	 * cautarea si afisarea unui pacient in tabel dupa CNP-ul acestuia
	 */

	@FXML
	void btnCautarePacientAction(ActionEvent event) {
		TableColumn idCol = new TableColumn("Id");
		TableColumn idUserCol = new TableColumn("IdUser");
		TableColumn numeCol = new TableColumn("Nume");
		TableColumn prenumeCol = new TableColumn("Prenume");
		TableColumn cnpCol = new TableColumn("CNP");
		TableColumn sexCol = new TableColumn("Sex");
		TableColumn dataNasteriiCol = new TableColumn("Data nasterii");
		TableColumn telefonCol = new TableColumn("Telefon");
		TableColumn adresaCol = new TableColumn("Adresa");
		TableColumn greutateCol = new TableColumn("Greutate");
		TableColumn inaltimeCol = new TableColumn("Inaltime");

		idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
		idUserCol.setCellValueFactory(new PropertyValueFactory<User, Rol>("idUser"));
		numeCol.setCellValueFactory(new PropertyValueFactory<User, String>("nume"));
		prenumeCol.setCellValueFactory(new PropertyValueFactory<User, String>("prenume"));
		cnpCol.setCellValueFactory(new PropertyValueFactory<User, Long>("cnp"));
		sexCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("sex"));
		dataNasteriiCol.setCellValueFactory(new PropertyValueFactory<User, String>("dataNasterii"));
		telefonCol.setCellValueFactory(new PropertyValueFactory<User, Long>("telefon"));
		adresaCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("adresa"));
		greutateCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("greutate"));
		inaltimeCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("inaltime"));

		tableView = new TableView<>();
		if (tableHolder.getChildren() != null) {
			tableHolder.getChildren().clear();
		}
		tableHolder.getChildren().add(tableView);
		tableView.getColumns().clear();

		tableView.getColumns().addAll(idCol, idUserCol, numeCol, prenumeCol, cnpCol, sexCol, dataNasteriiCol,
				telefonCol, adresaCol, greutateCol, inaltimeCol);
		messageLbl.setText("Pacientul cautat este: ");

		if (cnpPacientCautatLabel.getText().equals("")) {
			messageLabel.setText("Introduceti cnp-ul pacientului cautat");
			return;

		} else {
			PacientDAO pDAO = new PacientDAO();
			Pacient pacient = pDAO.findByCnp(Long.parseLong(cnpPacientCautatLabel.getText()));
			messageLabel.setVisible(false);
		}
		tableView.setItems(FXCollections
				.observableArrayList(new PacientDAO().findByCnp(Long.parseLong(cnpPacientCautatLabel.getText()))));

	}

	@FXML
	void btnCautareConsultatiiAction(ActionEvent event) {

		TableColumn idCol = new TableColumn("Id");
		TableColumn idPacientCol = new TableColumn("Id Pacient");
		TableColumn idMedicCol = new TableColumn("Id Medic");
		TableColumn idRetetaCol = new TableColumn("Id Reteta");
		TableColumn idFacturaCol = new TableColumn("Id Factura");
		TableColumn statusCol = new TableColumn("Status");
		TableColumn dataCol = new TableColumn("Data");
		TableColumn detaliiCol = new TableColumn("Detalii");

		idCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("id"));
		idPacientCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("idPacient"));
		idMedicCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("idMedic"));
		idRetetaCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("idReteta"));
		idFacturaCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("idFactura"));
		statusCol.setCellValueFactory(new PropertyValueFactory<Consultatie, String>("status"));
		dataCol.setCellValueFactory(new PropertyValueFactory<Consultatie, String>("data"));
		detaliiCol.setCellValueFactory(new PropertyValueFactory<Consultatie, String>("detalii"));

		tableView = new TableView<>();
		if (tableHolder.getChildren() != null) {
			tableHolder.getChildren().clear();
		}
		tableHolder.getChildren().add(tableView);
		tableView.getColumns().clear();

		tableView.getColumns().addAll(idCol, idPacientCol, idMedicCol, idRetetaCol, idFacturaCol, statusCol, dataCol,
				detaliiCol);
		messageLbl.setText("Consultatii pacienti");

		if (dataConsultatieCautata.getValue() != null) {
			tableView.setItems(FXCollections
					.observableArrayList(new ConsultatieDAO().findByDate(dataConsultatieCautata.getValue())));
		} else {
			messageLabel.setText("Alegeti data");
		}

	}

	@FXML
	void btnAnulareConsultAction(ActionEvent event) {
		Consultatie consultatie = (Consultatie) tableView.getSelectionModel().getSelectedItem();
		if (consultatie != null) {
			if (consultatie.getIdFactura() != null) {
				new FacturaDAO().delete(consultatie.getIdFactura());
			}
			if (consultatie.getIdReteta() != null) {
				new RetetaDAO().delete(consultatie.getIdReteta());
			}
			new ConsultatieDAO().delete(consultatie.getId());
			tableView.getItems().remove(consultatie);

		}
	}

	@FXML
	void btnCreareFacturaAction(ActionEvent event) {
		creazaFacturaPane.setVisible(true);

	}

	/*
	 * afiseaza toate facturile intr-un tabel
	 */
	@FXML
	void btnListaFacturiAction(ActionEvent event) {

		TableColumn idCol = new TableColumn("Id");
		TableColumn idConsultatieCol = new TableColumn("IdConsultatie");
		TableColumn valoareCol = new TableColumn("Valoare");
		TableColumn dataCol = new TableColumn("Data");
		TableColumn tvaCol = new TableColumn("Tva");

		idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
		idConsultatieCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("idConsultatie"));
		valoareCol.setCellValueFactory(new PropertyValueFactory<User, Double>("valoare"));
		dataCol.setCellValueFactory(new PropertyValueFactory<User, String>("data"));
		tvaCol.setCellValueFactory(new PropertyValueFactory<User, Double>("tva"));

		tableViewFacturi = new TableView<>();
		if (tableHolderFacturi.getChildren() != null) {
			tableHolderFacturi.getChildren().clear();
		}
		tableHolderFacturi.getChildren().add(tableViewFacturi);
		tableViewFacturi.getColumns().clear();

		tableViewFacturi.getColumns().addAll(idCol, idConsultatieCol, valoareCol, dataCol, tvaCol);

		tableViewFacturi.setItems(FXCollections.observableArrayList(new FacturaDAO().getAllFacturi()));

	}

	/*
	 * crearea unei noi facturi
	 */
	@FXML
	void btnInsertFacturaAction(ActionEvent event) {
		if (this.valoareFactura.getText().equals("")) {
			msgLbl.setText("Competati toate campurile");
		} else {
			double valoarefactura = Double.parseDouble(valoareFactura.getText());
			Factura factura = new Factura(1, comboBoxConsultatie.getSelectionModel().getSelectedItem().getId(),
					Double.parseDouble(valoareFactura.getText()), dataFactura.getValue(), valoarefactura * TVA);
			new FacturaDAO().addFactura(factura);
			if (tableViewFacturi != null & tableViewFacturi.getItems() != null) {
				tableViewFacturi.getItems().add(factura);
			}
			msgLbl.setText("Factura creata");
		}
	}

	@FXML
	void btnCreareRetetaAction(ActionEvent event) {
		creareRetetaPane.setVisible(true);
	}

	@FXML
	void btnInsertRetetaAction(ActionEvent event) {
		if (this.medicamente.getText().equals("")) {
			mesajLblReteta.setText("Completati  toate campurile");
		} else {
			Reteta reteta = new Reteta(1, cmbBoxConsultatieReteta.getSelectionModel().getSelectedItem().getId(),
					compensat.isSelected(), dataReteta.getValue(), medicamente.getText());
			new RetetaDAO().addReteta(reteta);

			if (tableViewRetete != null & tableViewRetete.getItems() != null) {
				tableViewRetete.getItems().add(reteta);
			}

			mesajLblReteta.setText("Reteta creata");
		}
	}

	@FXML
	void btnListaReteteAction(ActionEvent event) {
		TableColumn idCol = new TableColumn("Id");
		TableColumn idConsultatieCol = new TableColumn("IdConsultatie");
		TableColumn denumireMedicamentCol = new TableColumn("Medicamente");
		TableColumn compensatCol = new TableColumn("Compensata");
		TableColumn dataCol = new TableColumn("Data");

		idCol.setCellValueFactory(new PropertyValueFactory<Reteta, Integer>("id"));
		idConsultatieCol.setCellValueFactory(new PropertyValueFactory<Reteta, Integer>("idConsultatie"));
		denumireMedicamentCol.setCellValueFactory(new PropertyValueFactory<Reteta, String>("medicamente"));
		compensatCol.setCellValueFactory(new PropertyValueFactory<Reteta, Boolean>("compensat"));
		dataCol.setCellValueFactory(new PropertyValueFactory<Reteta, LocalDate>("data"));

		tableViewRetete = new TableView<>();
		if (tableHolderRetete.getChildren() != null) {
			tableHolderRetete.getChildren().clear();
		}
		tableHolderRetete.getChildren().add(tableViewRetete);
		tableViewRetete.getColumns().clear();

		tableViewRetete.getColumns().addAll(idCol, idConsultatieCol, denumireMedicamentCol, compensatCol, dataCol);

		tableViewRetete.setItems(FXCollections.observableArrayList(new RetetaDAO().getAllRetete()));
	}

	@FXML
	void btnStergeRetetaAction(ActionEvent event) {
		Reteta reteta = (Reteta) tableViewRetete.getSelectionModel().getSelectedItem();
		if (reteta != null) {
			new RetetaDAO().delete(reteta.getId());
			tableViewRetete.getItems().remove(reteta);
		}
	}
	@FXML
	void btnStergeFacturaAction(ActionEvent event) {
		Factura factura = (Factura) tableViewFacturi.getSelectionModel().getSelectedItem();
		if (factura != null) {
			new FacturaDAO().delete(factura.getId());
			tableViewRetete.getItems().remove(factura);
		}
	}

	@FXML
	void actionLogout(ActionEvent event) {
		tabPaneAsistent.setVisible(false);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
			Stage loginStage = new Stage();
			Parent parent = loader.load();
			Scene loginScene = new Scene(parent, 431, 467);
			loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			loginStage.setScene(loginScene);
			loginStage.setTitle("Centru Medical");
			loginStage.show();
			WindowManager.setCurrentStage(loginStage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
