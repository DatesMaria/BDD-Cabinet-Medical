package application;

import database.ConsultatieDAO;
import database.MedicDAO;
import database.PacientDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.cabinetMedical.Consultatie;
import model.cabinetMedical.Consultatie.Status;
import model.user.Medic;
import model.user.Pacient;
import model.user.User;
import model.user.User.Rol;

public class MedicController {

	
    @FXML
    private Pane medicPane;
    
	@FXML
	private Hyperlink logout;
	

    @FXML
    private Button btnPacientiLista;

    @FXML
    private DatePicker dataConsultatieCautata;

    @FXML
    private VBox tableHolder;

    @FXML
    private Button btnReteteLista;

    @FXML
    private TableView tableView;

    @FXML
    private Button btnCautareConsultatii;

    @FXML
    private TextField cnpPacientCautat;

    @FXML
    private Button btnConsultatiiLista;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private Label messageLbl;


    @FXML
    void btnPacientiListaAction(ActionEvent event) {
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
		
		idCol.setCellValueFactory(
                new PropertyValueFactory<User, Integer>("id"));
		idUserCol.setCellValueFactory(
                new PropertyValueFactory<User, Rol>("idUser"));
		numeCol.setCellValueFactory(
                new PropertyValueFactory<User, String>("nume"));
		prenumeCol.setCellValueFactory(
               new PropertyValueFactory<User, String>("prenume"));
		cnpCol.setCellValueFactory(
                new PropertyValueFactory<User, Long>("cnp"));
		sexCol.setCellValueFactory(
                new PropertyValueFactory<User, String>("sex"));
		dataNasteriiCol.setCellValueFactory(
                new PropertyValueFactory<User, String>("dataNasterii"));
		telefonCol.setCellValueFactory(
                new PropertyValueFactory<User, Long>("telefon"));
		adresaCol.setCellValueFactory(
                new PropertyValueFactory<User, String>("adresa"));
		greutateCol.setCellValueFactory(
               new PropertyValueFactory<User, Float>("greutate"));
		inaltimeCol.setCellValueFactory(
                new PropertyValueFactory<User, Float>("inaltime"));
		


		tableView = new TableView<>();
		if (tableHolder.getChildren() != null) {
			tableHolder.getChildren().clear();
		}
		tableHolder.getChildren().add(tableView);
		tableView.getColumns().clear();

		tableView.getColumns().addAll(idCol, idUserCol, numeCol, prenumeCol, cnpCol, sexCol, dataNasteriiCol,
				telefonCol, adresaCol, greutateCol, inaltimeCol);
		messageLabel.setText("Lista pacienti");
		
		tableView.setItems(FXCollections.observableArrayList(new PacientDAO().getAllPacienti()));

	new MedicDAO().findByUserId(UserManager.currentUser.getId());
	
    }

    @FXML
    void btnConsultatiiListaAction(ActionEvent event) {
    	Medic medic = new MedicDAO().findByUserId(UserManager.currentUser.getId());
    	if(medic ==null)
    	{
    		return;
    	}
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

		
		tableView.setItems(FXCollections.observableArrayList(new ConsultatieDAO().findByIdMedic(medic.getId())));

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
		messageLabel.setText("Consultatii: ");

		if (dataConsultatieCautata.getValue() != null) {
			tableView.setItems(FXCollections
					.observableArrayList(new ConsultatieDAO().findByDate(dataConsultatieCautata.getValue())));
		} else {
			messageLbl.setText("Alegeti data");
		}

    }

    @FXML
    void btncautareCnpAction(ActionEvent event) {

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

	if (cnpPacientCautat.getText().equals("")) {
		messageLabel.setText("Introduceti cnp-ul pacientului cautat");
		return;

	} else {
		PacientDAO pDAO = new PacientDAO();
		Pacient pacient = pDAO.findByCnp(Long.parseLong(cnpPacientCautat.getText()));
		messageLabel.setVisible(false);
	}
	tableView.setItems(FXCollections
			.observableArrayList(new PacientDAO().findByCnp(Long.parseLong(cnpPacientCautat.getText()))));
    }
    
    @FXML
    void finalizareConsultatieAction(ActionEvent event) {
    	Object selectedItem = tableView.getSelectionModel().getSelectedItem();
    	
    	if(selectedItem != null) {
    		Consultatie consultatie = (Consultatie)selectedItem;
    		consultatie.setStatus(Status.FINALIZATA);
    		new ConsultatieDAO().update(consultatie);
    		tableView.refresh();
    	}

    }

    @FXML
	void actionLogout(ActionEvent event) {
		medicPane.setVisible(false);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
			Stage loginStage = new Stage();
			Parent parent = loader.load();
			Scene loginScene = new Scene(parent, 431,467);
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
