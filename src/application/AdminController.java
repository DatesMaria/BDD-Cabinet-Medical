package application;

import java.time.LocalDate;

import database.AsistentDAO;
import database.ConsultatieDAO;
import database.FacturaDAO;
import database.MedicDAO;
import database.PacientDAO;
import database.UserDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LongStringConverter;
import model.cabinetMedical.Consultatie;
import model.cabinetMedical.Consultatie.Status;
import model.cabinetMedical.Factura;
import model.user.Asistent;
import model.user.Medic;
import model.user.Pacient;
import model.user.User;
import model.user.User.Rol;

public class AdminController {

	@FXML
	private Hyperlink logout;

	@FXML
	private Button btnAsistentiList;

	@FXML
	private Pane adminPane;

	@FXML
	private Label afisareTabel;

	@FXML
	private Button btnPacientiList;

	@FXML
	private Button btnMediciList;

	@FXML
	private Button btnUtilizatoriList;

	@FXML
	private TableView tableView;

	@FXML
	private VBox tableHolder;

	@FXML
	private Label messageLbl;

	@FXML
	private Button btnConsultatii;

	@FXML
	private Button btnFacturi;

	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;

	@FXML
	void btnPacientiListAction(ActionEvent event) {
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

		idCol.setCellValueFactory(new PropertyValueFactory<Pacient, Integer>("id"));

		idUserCol.setCellValueFactory(new PropertyValueFactory<Pacient, Rol>("idUser"));
		idUserCol.setCellFactory(TextFieldTableCell.<Pacient, Integer>forTableColumn(new IntegerStringConverter()));
		idUserCol.setOnEditCommit(new EventHandler<CellEditEvent<Pacient, Integer>>() {
		    @Override
		    public void handle(CellEditEvent<Pacient, Integer> t) {
		        ((Pacient) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setIdUser(t.getNewValue()); 
		    }
		} );

		numeCol.setCellValueFactory(new PropertyValueFactory<Pacient, String>("nume"));
		numeCol.setCellFactory(TextFieldTableCell.<Pacient>forTableColumn());
		numeCol.setOnEditCommit(new EventHandler<CellEditEvent<Pacient, String>>() {
		    @Override
		    public void handle(CellEditEvent<Pacient, String> t) {
		        ((Pacient) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setNume(t.getNewValue()); 
		    }
		} );

		prenumeCol.setCellValueFactory(new PropertyValueFactory<Pacient, String>("prenume"));
		prenumeCol.setCellFactory(TextFieldTableCell.<Pacient>forTableColumn());
		prenumeCol.setOnEditCommit(new EventHandler<CellEditEvent<Pacient, String>>() {
		    @Override
		    public void handle(CellEditEvent<Pacient, String> t) {
		        ((Pacient) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setPrenume(t.getNewValue()); 
		    }
		} );

		cnpCol.setCellValueFactory(new PropertyValueFactory<Pacient, Long>("cnp"));
		cnpCol.setCellFactory(TextFieldTableCell.<Pacient, Long>forTableColumn(new LongStringConverter()));
		cnpCol.setOnEditCommit(new EventHandler<CellEditEvent<Pacient, Long>>() {
		    @Override
		    public void handle(CellEditEvent<Pacient, Long> t) {
		        ((Pacient) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setCnp(t.getNewValue()); 
		    }
		} );

		sexCol.setCellValueFactory(new PropertyValueFactory<Pacient, String>("sex"));
		sexCol.setCellFactory(TextFieldTableCell.<Pacient>forTableColumn());
		sexCol.setOnEditCommit(new EventHandler<CellEditEvent<Pacient, String>>() {
		    @Override
		    public void handle(CellEditEvent<Pacient, String> t) {
		        ((Pacient) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setSex(t.getNewValue()); 
		    }
		} );

		dataNasteriiCol.setCellValueFactory(new PropertyValueFactory<Pacient, LocalDate>("dataNasterii"));
		dataNasteriiCol
				.setCellFactory(TextFieldTableCell.<Pacient, LocalDate>forTableColumn(new LocalDateStringConverter()));
		dataNasteriiCol.setOnEditCommit(new EventHandler<CellEditEvent<Pacient, LocalDate>>() {
		    @Override
		    public void handle(CellEditEvent<Pacient, LocalDate> t) {
		        ((Pacient) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setDataNasterii(t.getNewValue()); 
		    }
		} );

		telefonCol.setCellValueFactory(new PropertyValueFactory<Pacient, Long>("telefon"));
		telefonCol.setCellFactory(TextFieldTableCell.<Pacient, Long>forTableColumn(new LongStringConverter()));
		telefonCol.setOnEditCommit(new EventHandler<CellEditEvent<Pacient, Long>>() {
		    @Override
		    public void handle(CellEditEvent<Pacient, Long> t) {
		        ((Pacient) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setTelefon(t.getNewValue()); 
		    }
		} );

		adresaCol.setCellValueFactory(new PropertyValueFactory<Pacient, String>("adresa"));
		adresaCol.setCellFactory(TextFieldTableCell.<Pacient>forTableColumn());
		adresaCol.setOnEditCommit(new EventHandler<CellEditEvent<Pacient, String>>() {
		    @Override
		    public void handle(CellEditEvent<Pacient, String> t) {
		        ((Pacient) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setAdresa(t.getNewValue()); 
		    }
		} );

		greutateCol.setCellValueFactory(new PropertyValueFactory<Pacient, Float>("greutate"));
		greutateCol.setCellFactory(TextFieldTableCell.<Pacient, Float>forTableColumn(new FloatStringConverter()));
		greutateCol.setOnEditCommit(new EventHandler<CellEditEvent<Pacient, Float>>() {
		    @Override
		    public void handle(CellEditEvent<Pacient, Float> t) {
		        ((Pacient) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setGreutate(t.getNewValue()); 
		    }
		} );

		inaltimeCol.setCellValueFactory(new PropertyValueFactory<Pacient, Float>("inaltime"));
		inaltimeCol.setCellFactory(TextFieldTableCell.<Pacient, Float>forTableColumn(new FloatStringConverter()));
		inaltimeCol.setOnEditCommit(new EventHandler<CellEditEvent<Pacient, Float>>() {
		    @Override
		    public void handle(CellEditEvent<Pacient, Float> t) {
		        ((Pacient) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setInaltime(t.getNewValue()); 
		    }
		} );

		tableView = new TableView<>();
		tableView.setEditable(true);
		if (tableHolder.getChildren() != null) {
			tableHolder.getChildren().clear();
		}
		tableHolder.getChildren().add(tableView);
		tableView.getColumns().clear();

		tableView.getColumns().addAll(idCol, idUserCol, numeCol, prenumeCol, cnpCol, sexCol, dataNasteriiCol,
				telefonCol, adresaCol, greutateCol, inaltimeCol);
		messageLbl.setText("pacienti");

		tableView.setItems(FXCollections.observableArrayList(new PacientDAO().getAllPacienti()));
	}

	@FXML
	void btnMediciListAction(ActionEvent event) {
		TableColumn idCol = new TableColumn("Id");
		TableColumn idSpecializareCol = new TableColumn("Id specializare");
		TableColumn idUserCol = new TableColumn("Id user");
		TableColumn numeCol = new TableColumn("Nume");
		TableColumn prenumeCol = new TableColumn("Prenume");
		TableColumn dataNasteriiCol = new TableColumn("Data nasterii");

		idCol.setCellValueFactory(new PropertyValueFactory<Medic, Integer>("id"));
		
		idSpecializareCol.setCellValueFactory(new PropertyValueFactory<Medic, String>("idSpecializare"));
		idSpecializareCol
				.setCellFactory(TextFieldTableCell.<Medic, Integer>forTableColumn(new IntegerStringConverter()));
		idSpecializareCol.setOnEditCommit(new EventHandler<CellEditEvent<Medic, Integer>>() {
		    @Override
		    public void handle(CellEditEvent<Medic, Integer> t) {
		        ((Medic) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setIdSpecializare(t.getNewValue()); 
		    }
		} );

		idUserCol.setCellValueFactory(new PropertyValueFactory<Medic, Integer>("idUser"));
		idUserCol.setCellFactory(TextFieldTableCell.<Medic, Integer>forTableColumn(new IntegerStringConverter()));
		idUserCol.setOnEditCommit(new EventHandler<CellEditEvent<Medic, Integer>>() {
		    @Override
		    public void handle(CellEditEvent<Medic, Integer> t) {
		        ((Medic) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setIdUser(t.getNewValue()); 
		    }
		} );

		numeCol.setCellValueFactory(new PropertyValueFactory<Medic, String>("nume"));
		numeCol.setCellFactory(TextFieldTableCell.<Medic>forTableColumn());
		numeCol.setOnEditCommit(new EventHandler<CellEditEvent<Medic, String>>() {
		    @Override
		    public void handle(CellEditEvent<Medic, String> t) {
		        ((Medic) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setNume(t.getNewValue()); 
		    }
		} );

		prenumeCol.setCellValueFactory(new PropertyValueFactory<Medic, String>("prenume"));
		prenumeCol.setCellFactory(TextFieldTableCell.<Medic>forTableColumn());
		prenumeCol.setOnEditCommit(new EventHandler<CellEditEvent<Medic, String>>() {
		    @Override
		    public void handle(CellEditEvent<Medic, String> t) {
		        ((Medic) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setPrenume(t.getNewValue()); 
		    }
		} );

		dataNasteriiCol.setCellValueFactory(new PropertyValueFactory<Medic, String>("dataNasterii"));
		dataNasteriiCol
				.setCellFactory(TextFieldTableCell.<Medic, LocalDate>forTableColumn(new LocalDateStringConverter()));
		dataNasteriiCol.setOnEditCommit(new EventHandler<CellEditEvent<Medic, LocalDate>>() {
		    @Override
		    public void handle(CellEditEvent<Medic, LocalDate> t) {
		        ((Medic) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setDataNasterii(t.getNewValue()); 
		    }
		} );

		tableView = new TableView<>();
		tableView.setEditable(true);
		if (tableHolder.getChildren() != null) {
			tableHolder.getChildren().clear();
			messageLbl.setText("");
		}
		tableHolder.getChildren().add(tableView);
		tableView.getColumns().clear();

		tableView.getColumns().addAll(idCol, idSpecializareCol, idUserCol, numeCol, prenumeCol, dataNasteriiCol);
		messageLbl.setText("medici");

		tableView.setItems(FXCollections.observableArrayList(new MedicDAO().getAllMedici()));
	}

	@FXML
	void btnAsistentiListAction(ActionEvent event) {
		TableColumn idCol = new TableColumn("Id");
		TableColumn idUserCol = new TableColumn("Id user");
		TableColumn numeCol = new TableColumn("Nume");
		TableColumn prenumeCol = new TableColumn("Prenume");

		idCol.setCellValueFactory(new PropertyValueFactory<Asistent, Integer>("id"));

		idUserCol.setCellValueFactory(new PropertyValueFactory<Asistent, Integer>("idUser"));
		idUserCol.setCellFactory(TextFieldTableCell.<Asistent, Integer>forTableColumn(new IntegerStringConverter()));
		idUserCol.setOnEditCommit(new EventHandler<CellEditEvent<Asistent, Integer>>() {
		    @Override
		    public void handle(CellEditEvent<Asistent, Integer> t) {
		        ((Asistent) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setIdUser(t.getNewValue()); 
		    }
		} );

		numeCol.setCellValueFactory(new PropertyValueFactory<Asistent, String>("nume"));
		numeCol.setCellFactory(TextFieldTableCell.<Asistent>forTableColumn());
		numeCol.setOnEditCommit(new EventHandler<CellEditEvent<Asistent, String>>() {
		    @Override
		    public void handle(CellEditEvent<Asistent, String> t) {
		        ((Asistent) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setNume(t.getNewValue()); 
		    }
		} );

		prenumeCol.setCellValueFactory(new PropertyValueFactory<Asistent, String>("prenume"));
		prenumeCol.setCellFactory(TextFieldTableCell.<Asistent>forTableColumn());
		prenumeCol.setOnEditCommit(new EventHandler<CellEditEvent<Asistent, String>>() {
		    @Override
		    public void handle(CellEditEvent<Asistent, String> t) {
		        ((Asistent) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setPrenume(t.getNewValue()); 
		    }
		} );

		tableView = new TableView<>();
		tableView.setEditable(true);

		if (tableHolder.getChildren() != null) {
			tableHolder.getChildren().clear();
			messageLbl.setText("");
		}
		tableHolder.getChildren().add(tableView);
		tableView.getColumns().clear();

		tableView.getColumns().addAll(idCol, idUserCol, numeCol, prenumeCol);
		messageLbl.setText("asistenti");

		tableView.setItems(FXCollections.observableArrayList(new AsistentDAO().getAllAsistenti()));
	}

	@FXML
	void btnUtilizatoriListAction(ActionEvent event) {

		TableColumn idCol = new TableColumn("Id");
		TableColumn usernameCol = new TableColumn("Username");
		TableColumn passwordCol = new TableColumn("Parola");
		TableColumn rolCol = new TableColumn("Rol");

		idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
		
		usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("nume_user"));
		usernameCol.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
		    @Override
		    public void handle(CellEditEvent<User, String> t) {
		        ((User) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setNume_user(t.getNewValue()); 
		    }
		} );
		
		
		usernameCol.setCellFactory(TextFieldTableCell.<User>forTableColumn());

		passwordCol.setCellValueFactory(new PropertyValueFactory<User, String>("parola"));
		
		rolCol.setCellValueFactory(new PropertyValueFactory<User, Rol>("rol"));
		rolCol.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(Rol.values())));
		rolCol.setOnEditCommit(new EventHandler<CellEditEvent<User, Rol>>() {
		    @Override
		    public void handle(CellEditEvent<User, Rol> t) {
		        ((User) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setRol(t.getNewValue()); 
		    }
		} );

		tableView = new TableView<>();
		tableView.setEditable(true);
		if (tableHolder.getChildren() != null) {
			tableHolder.getChildren().clear();
			messageLbl.setText("");
		}
		tableHolder.getChildren().add(tableView);
		tableView.getColumns().clear();

		tableView.getColumns().addAll(idCol, usernameCol, passwordCol, rolCol);
		messageLbl.setText("utilizatori");

		tableView.setItems(FXCollections.observableArrayList(new UserDAO().getAllUsers()));

	}

	@FXML
	void btnConsultatiiShow(ActionEvent event) {
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
		idPacientCol
				.setCellFactory(TextFieldTableCell.<Consultatie, Integer>forTableColumn(new IntegerStringConverter()));
		idPacientCol.setOnEditCommit(new EventHandler<CellEditEvent<Consultatie, Integer>>() {
		    @Override
		    public void handle(CellEditEvent<Consultatie, Integer> t) {
		        ((Consultatie) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setIdPacient(t.getNewValue()); 
		    }
		} );

		idMedicCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("idMedic"));
		idMedicCol
				.setCellFactory(TextFieldTableCell.<Consultatie, Integer>forTableColumn(new IntegerStringConverter()));
		idMedicCol.setOnEditCommit(new EventHandler<CellEditEvent<Consultatie, Integer>>() {
		    @Override
		    public void handle(CellEditEvent<Consultatie, Integer> t) {
		        ((Consultatie) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setIdMedic(t.getNewValue()); 
		    }
		} );

		idRetetaCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("idReteta"));
		idRetetaCol
				.setCellFactory(TextFieldTableCell.<Consultatie, Integer>forTableColumn(new IntegerStringConverter()));
		idRetetaCol.setOnEditCommit(new EventHandler<CellEditEvent<Consultatie, Integer>>() {
		    @Override
		    public void handle(CellEditEvent<Consultatie, Integer> t) {
		        ((Consultatie) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setIdReteta(t.getNewValue()); 
		    }
		} );

		idFacturaCol.setCellValueFactory(new PropertyValueFactory<Consultatie, Integer>("idFactura"));
		idFacturaCol
				.setCellFactory(TextFieldTableCell.<Consultatie, Integer>forTableColumn(new IntegerStringConverter()));
		idFacturaCol.setOnEditCommit(new EventHandler<CellEditEvent<Consultatie, Integer>>() {
		    @Override
		    public void handle(CellEditEvent<Consultatie, Integer> t) {
		        ((Consultatie) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setIdFactura(t.getNewValue()); 
		    }
		} );

		statusCol.setCellValueFactory(new PropertyValueFactory<Consultatie, String>("status"));
		statusCol.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(Status.values())));
		statusCol.setOnEditCommit(new EventHandler<CellEditEvent<Consultatie, Status>>() {
		    @Override
		    public void handle(CellEditEvent<Consultatie, Status> t) {
		        ((Consultatie) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setStatus(t.getNewValue()); 
		    }
		} );

		dataCol.setCellValueFactory(new PropertyValueFactory<Consultatie, LocalDate>("data"));
		dataCol.setCellFactory(TextFieldTableCell.<Consultatie, LocalDate>forTableColumn(new LocalDateStringConverter()));
		dataCol.setOnEditCommit(new EventHandler<CellEditEvent<Consultatie, LocalDate>>() {
		    @Override
		    public void handle(CellEditEvent<Consultatie, LocalDate> t) {
		        ((Consultatie) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setData(t.getNewValue()); 
		    }
		} );

		detaliiCol.setCellValueFactory(new PropertyValueFactory<Consultatie, String>("detalii"));
		detaliiCol.setCellFactory(TextFieldTableCell.<Consultatie>forTableColumn());
		detaliiCol.setOnEditCommit(new EventHandler<CellEditEvent<Consultatie, String>>() {
		    @Override
		    public void handle(CellEditEvent<Consultatie,String> t) {
		        ((Consultatie) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setDetalii(t.getNewValue()); 
		    }
		} );

		tableView = new TableView<>();
		tableView.setEditable(true);
		if (tableHolder.getChildren() != null) {
			tableHolder.getChildren().clear();
		}
		tableHolder.getChildren().add(tableView);
		tableView.getColumns().clear();

		tableView.getColumns().addAll(idCol, idPacientCol, idMedicCol, idRetetaCol, idFacturaCol, statusCol, dataCol,
				detaliiCol);
		messageLbl.setText("consultatii");
		tableView.setItems(FXCollections.observableArrayList(new ConsultatieDAO().getAllConsultatii()));

	}

	@FXML
	void btnFacturiShow(ActionEvent event) {
		TableColumn idCol = new TableColumn("Id");
		TableColumn idConsultatieCol = new TableColumn("Id consultatie");
		TableColumn valoareCol = new TableColumn("Valoare");
		TableColumn dataCol = new TableColumn("Data");
		TableColumn tvaCol = new TableColumn("TVA");

		idCol.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("id"));
		
		idConsultatieCol.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("idConsultatie"));
		idConsultatieCol.setCellFactory(TextFieldTableCell.<Factura, Integer>forTableColumn(new IntegerStringConverter()));
		idConsultatieCol.setOnEditCommit(new EventHandler<CellEditEvent<Factura, Integer>>() {
		    @Override
		    public void handle(CellEditEvent<Factura, Integer> t) {
		        ((Factura) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setIdConsultatie(t.getNewValue()); 
		    }
		} );
		

		valoareCol.setCellValueFactory(new PropertyValueFactory<Factura, Double>("valoare"));
		valoareCol.setCellFactory(TextFieldTableCell.<Factura, Double>forTableColumn(new DoubleStringConverter()));
		valoareCol.setOnEditCommit(new EventHandler<CellEditEvent<Factura, Double>>() {
		    @Override
		    public void handle(CellEditEvent<Factura, Double> t) {
		        ((Factura) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setValoare(t.getNewValue()); 
		    }
		} );

		dataCol.setCellValueFactory(new PropertyValueFactory<Factura, LocalDate>("data"));
		dataCol.setCellFactory(TextFieldTableCell.<Factura, LocalDate>forTableColumn(new LocalDateStringConverter()));
		dataCol.setOnEditCommit(new EventHandler<CellEditEvent<Factura, LocalDate>>() {
		    @Override
		    public void handle(CellEditEvent<Factura, LocalDate> t) {
		        ((Factura) t.getTableView().getItems().get(
		            t.getTablePosition().getRow())
		            ).setData(t.getNewValue()); 
		    }
		} );

		tvaCol.setCellValueFactory(new PropertyValueFactory<Factura, Double>("tva"));
		tvaCol.setCellFactory(TextFieldTableCell.<Factura, Double>forTableColumn(new DoubleStringConverter()));

		tableView = new TableView<>();
		tableView.setEditable(true);
		if (tableHolder.getChildren() != null) {
			tableHolder.getChildren().clear();
		}
		tableHolder.getChildren().add(tableView);
		tableView.getColumns().clear();

		tableView.getColumns().addAll(idCol, idConsultatieCol, valoareCol, dataCol, tvaCol);
		messageLbl.setText("facturi");

		tableView.setItems(FXCollections.observableArrayList(new FacturaDAO().getAllFacturi()));

	}

	@FXML
	void btnUpdateAction(ActionEvent event) {
		Object selectedItem = tableView.getSelectionModel().getSelectedItem();
		System.out.println(selectedItem);
		if(selectedItem == null)
		{
			return;
		}
		if (selectedItem instanceof Pacient) {
			new PacientDAO().update(((Pacient)selectedItem));
		} else if (selectedItem instanceof Medic) {
			new MedicDAO().update(((Medic)selectedItem));

		} else if (selectedItem instanceof Asistent) {
			new AsistentDAO().update(((Asistent)selectedItem));

		} else if (selectedItem instanceof User) {
			new UserDAO().update(((User)selectedItem));
			
		}
		else if (selectedItem instanceof Consultatie) {
			new ConsultatieDAO().update(((Consultatie)selectedItem));
			
		}else if (selectedItem instanceof Factura) {
			new FacturaDAO().update(((Factura)selectedItem));
			
		}
		tableView.refresh();

	}

	@FXML
	void btnDeleteAction(ActionEvent event) {

		Object selectedItem = tableView.getSelectionModel().getSelectedItem();
		if(selectedItem == null)
		{
			return;
		}
		if (selectedItem instanceof Pacient) {
			new PacientDAO().delete(((Pacient)selectedItem).getId());
		} else if (selectedItem instanceof Medic) {
			new MedicDAO().delete(((Medic)selectedItem).getId());

		} else if (selectedItem instanceof Asistent) {
			new AsistentDAO().delete(((Asistent)selectedItem).getId());

		} else if (selectedItem instanceof User) {
			new UserDAO().delete(((User)selectedItem).getId());
			
		}
		else if (selectedItem instanceof Consultatie) {
			new ConsultatieDAO().delete(((Consultatie)selectedItem).getId());
			
		}else if (selectedItem instanceof Factura) {
			new FacturaDAO().delete(((Factura)selectedItem).getId());
			
		}
		tableView.getItems().remove(selectedItem);
	}

	@FXML
	void actionLogout(ActionEvent event) {
		adminPane.setVisible(false);
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
