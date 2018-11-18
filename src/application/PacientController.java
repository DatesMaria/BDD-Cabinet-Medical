package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.ConsultatieDAO;
import database.FacturaDAO;
import database.PacientDAO;
import database.RetetaDAO;
import database.SpecializareDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.cabinetMedical.Consultatie;
import model.cabinetMedical.Factura;
import model.cabinetMedical.Reteta;
import model.user.Pacient;
import model.user.User;

public class PacientController {
	

    @FXML
    private Label notificationLabel;

	@FXML
	private TableView tableView;

	@FXML
	private Label messageLbl;

	@FXML
	private Pane pacientPane;

	@FXML
	private VBox tableHolder;

	@FXML
	private Button btnAnulareConsultatie;

	@FXML
	private Button btnConsultatiiLista;

	@FXML
	private Button btnListaRetete;

	@FXML
	private Button btnListaFacturi;
	
	@FXML
	private DatePicker dataConsultatieCautata;

	@FXML
	void btnConsultatiiListaAction(ActionEvent event) {
		btnAnulareConsultatie.setVisible(true);


		Pacient pacient = new PacientDAO().findByUserId(UserManager.currentUser.getId());
		if (pacient != null) {
			TableColumn idCol = new TableColumn("Id");
			TableColumn idMedicCol = new TableColumn("Id Medic");
			TableColumn statusCol = new TableColumn("Status");
			TableColumn dataCol = new TableColumn("Data");
			TableColumn detaliiCol = new TableColumn("Detalii");

			idCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("id"));
			idMedicCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("idMedic"));
			statusCol.setCellValueFactory(new PropertyValueFactory<Consultatie, String>("status"));
			dataCol.setCellValueFactory(new PropertyValueFactory<Consultatie, String>("data"));
			detaliiCol.setCellValueFactory(new PropertyValueFactory<Consultatie, String>("detalii"));

			tableView = new TableView<>();
			if (tableHolder.getChildren() != null) {
				tableHolder.getChildren().clear();
			}
			tableHolder.getChildren().add(tableView);
			tableView.getColumns().clear();

			tableView.getColumns().addAll(idCol, idMedicCol, statusCol, dataCol, detaliiCol);
			messageLbl.setText("Consultatii");

			tableView
					.setItems(FXCollections.observableArrayList(new ConsultatieDAO().findByIdPacient(pacient.getId())));
		}
	}

	@FXML
	void btnListaReteteShow(ActionEvent event) {

		btnAnulareConsultatie.setVisible(false);

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

		tableView = new TableView<>();
		if (tableHolder.getChildren() != null) {
			tableHolder.getChildren().clear();
		}
		tableHolder.getChildren().add(tableView);
		tableView.getColumns().clear();
		messageLbl.setText("Retete");

		tableView.getColumns().addAll(idCol, idConsultatieCol, denumireMedicamentCol, compensatCol, dataCol);

		List<Consultatie> consultatiiList = new ArrayList<>();
		Pacient pacient = new PacientDAO().findByUserId(UserManager.currentUser.getId());
		try {
			consultatiiList.addAll(new ConsultatieDAO().findByIdPacient(pacient.getId()));
		} catch (Exception e1) {
			System.out.println("Nu sunt consulatii");
			return;
		}
		List<Reteta> reteteList = new ArrayList<>();

		for (Consultatie cons : consultatiiList) {
			try {
				reteteList.addAll(new RetetaDAO().findByIdConsultatie(cons.getId()));
			} catch (Exception e) {
				continue;
			}
		}
		tableView.setItems(FXCollections.observableArrayList(reteteList));

	}

	@FXML
	void btnListaFacturiShow(ActionEvent event) {
		btnAnulareConsultatie.setVisible(false);

		TableColumn idCol = new TableColumn("Id");
		TableColumn idConsultatieCol = new TableColumn("IdConsultatie");
		TableColumn valoareCol = new TableColumn("Valoare");
		TableColumn dataCol = new TableColumn("Data");
		TableColumn tvaCol = new TableColumn("Tva");

		idCol.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("id"));
		idConsultatieCol.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("idConsultatie"));
		valoareCol.setCellValueFactory(new PropertyValueFactory<Factura, Double>("valoare"));
		dataCol.setCellValueFactory(new PropertyValueFactory<Factura, String>("data"));
		tvaCol.setCellValueFactory(new PropertyValueFactory<Factura, Double>("tva"));

		tableView = new TableView<>();
		if (tableHolder.getChildren() != null) {
			tableHolder.getChildren().clear();
		}
		tableHolder.getChildren().add(tableView);
		tableView.getColumns().clear();
		messageLbl.setText("Facturi");

		tableView.getColumns().addAll(idCol, idConsultatieCol, valoareCol, dataCol, tvaCol);

		List<Consultatie> consultatiiList = new ArrayList<>();
		Pacient pacient = new PacientDAO().findByUserId(UserManager.currentUser.getId());
		try {
			consultatiiList.addAll(new ConsultatieDAO().findByIdPacient(pacient.getId()));
		} catch (Exception e1) {
			System.out.println("Nu sunt consulatii");
			return;
		}
		List<Factura> facturiList = new ArrayList<>();

		for (Consultatie cons : consultatiiList) {
			try {
				facturiList.addAll(new FacturaDAO().findByIdConsultatie(cons.getId()));
			} catch (Exception e) {
				continue;
			}
			System.out.println(facturiList);
		}
		tableView.setItems(FXCollections.observableArrayList(facturiList));

	}

	@FXML
	void btnAnulareConsultatieAction(ActionEvent event) {

		Object selectedConsultatie = tableView.getSelectionModel().getSelectedItem();
		System.out.println(selectedConsultatie);
		
		if(selectedConsultatie != null)
		{
		    Consultatie consultatie = (Consultatie)selectedConsultatie;
		    List<Factura> facturi = new FacturaDAO().findByIdConsultatie(consultatie.getId());
		    List<Reteta> retete = new RetetaDAO().findByIdConsultatie(consultatie.getId());
			if( facturi != null & retete != null ) {
				if(facturi.isEmpty() && retete.isEmpty()) {
					new ConsultatieDAO().delete(consultatie.getId());
					tableView.getItems().remove(selectedConsultatie);
				}
			}
			else {
				this.notificationLabel.setText("Consultatia nu poate fi stearsa !");
			}
		}
	}

	  @FXML
	    void btnCautareConsultatiAction(ActionEvent event) {

	    }
	  
	   @FXML
	    void btncautareCnpAction(ActionEvent event) {

	    }

	@FXML
	void actionLogout(ActionEvent event) {
		pacientPane.setVisible(false);
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
