package application;

import java.time.LocalDate;

import org.jasypt.util.password.BasicPasswordEncryptor;

import database.AdministratorDAO;
import database.AsistentDAO;
import database.MedicDAO;
import database.PacientDAO;
import database.SpecializareDAO;
import database.UserDAO;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.user.Administrator;
import model.user.Asistent;
import model.user.Medic;
import model.user.Pacient;
import model.user.Specializare;
import model.user.User;
import model.user.User.Rol;

public class LoginController {
	// pane inregistrare admin

	private final String CNP_REGEX = "^[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d$";

	@FXML
	private Pane registrationAdminPane;

	@FXML
	private Button btnRegistAdmin;

	@FXML
	private Label messageLblAdmin;

	@FXML
	private TextField prenumeRegistAdmin;

	@FXML
	private TextField userRegistAdmin;

	@FXML
	private PasswordField passRegistAdmin;

	@FXML
	private TextField nameRegistAdmin;

	@FXML
	private Hyperlink btnBacktoContinueFromAdmin;

	// pane inregistrare Asistent

	@FXML
	private PasswordField passRegistAsist;

	@FXML
	private TextField userRegistAsist;

	@FXML
	private TextField nameRegistAsist;

	@FXML
	private Button btnRegistAsistent;

	@FXML
	private TextField prenumeRegistAsist;

	@FXML
	private Hyperlink btnBacktoContinueFromAsist;

	@FXML
	private Label messLabelForAsist;

	// pane inregistrare Medic
	@FXML
	private Pane registrationMedicPane;

	@FXML
	private Hyperlink linkBackToMainFromMedic;

	@FXML
	private PasswordField passRegistMedic;

	@FXML

	private TextField prenumeRegistrMedic;

	@FXML
	private TextField userRegistMedic;

	@FXML
	private DatePicker dateRegistrMedic;

	@FXML
	private Button btnRegistMedic;

	@FXML
	private TextField nameRegistMedic;

	@FXML
	private ComboBox<Specializare> comboboxSpecializare;

	@FXML
	private Label messLabelForMedic;

	// pane inregistrare Pacient
	@FXML
	private Button btnRegistr;

	@FXML
	private Label messageLabel;

	@FXML
	private Pane registrationPane;

	// date pacient
	@FXML
	private TextField prenumeRegistr;

	@FXML
	private TextField inaltimeRegistr;

	@FXML
	private TextField userRegistr;

	@FXML
	private TextField cnpRegistr;

	@FXML
	private TextField greutateRegistr;

	@FXML
	private DatePicker dateRegistr;
	@FXML
	private TextField sexRegistr;

	@FXML
	private PasswordField passRegistr;

	@FXML
	private TextField adresaRegistr;

	@FXML
	private TextField nameRegistr;

	@FXML
	private TextField telRegistr;

	// login
	@FXML
	private Pane loginPane;

	@FXML
	private Label messLabel;

	@FXML
	private TextField userLogin;

	@FXML
	private Button btnLogin;

	@FXML
	private PasswordField userPass;

	@FXML
	private Hyperlink linkBackToMain1;

	@FXML
	private Label loginLabel;

	// continue Pane
	@FXML
	private ComboBox<Rol> comboUsers;

	@FXML
	private Button btnIntregistrare;

	@FXML
	private Pane continuePane;

	@FXML
	private Pane registrationAsistentPane;

	@FXML
	private AnchorPane loginAnchorPane;

	@FXML
	private Label accessKeyMessage;

	@FXML
	private PasswordField accessKey;

	@FXML
	private Label notificationLbl;

	String accessPassword = "JLLAdRoJqymtdKLYpKJFdKk72QZbGMJa";

	@FXML
	public void initialize() {
		this.comboUsers.setItems(User.ROLURI);
		this.comboUsers.getSelectionModel().selectFirst();
		this.dateRegistr.setValue(LocalDate.now());
	}
	/*
	 * metoda pt password encryption
	 */

	String encryptPassword(String parola) {
		// BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		String encryptedPassword = new BasicPasswordEncryptor().encryptPassword(parola);

		return encryptedPassword;
	}

	boolean isValidPassword(String password, String hash) {
		return new BasicPasswordEncryptor().checkPassword(password, hash);
	}

	/**
	 * 
	 * metoda pentru logare utilizatori
	 */
	@FXML
	void loginAction(ActionEvent event) {
		if (userLogin.getText().equals("") || userPass.getText().equals("")) {
			messageLabel.setText("Completati toate campurile");

		} else {
			UserDAO userDAO = new UserDAO();
			User user = userDAO.findByUsername(userLogin.getText());

			if (user != null) {
				if (isValidPassword(userPass.getText(), user.getParola())) {
					int width = -1, height = -1;
					String view = null;

					switch (user.getRol()) {
					case PACIENT: {
						width = 800;
						height = 700;
						view = "PacientView.fxml";

					}
						break;
					case MEDIC: {
						width = 800;
						height = 700;
						view = "MedicView.fxml";

					}
						break;
					case ASISTENT: {
						width = 800;
						height = 720;
						view = "AsistentView.fxml";

					}
						break;
					case ADMIN: {
						width = 800;
						height = 720;
						view = "AdminView.fxml";
					}
						break;

					}

					messageLabel.setText("Login reusit");
					UserManager.currentUser = user;
					if (width != -1 && height != -1 && view != null) {
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
							Stage primaryStage = new Stage();
							Parent parent = loader.load();
							Scene loginScene = new Scene(parent, width, height);
							loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							primaryStage.setScene(loginScene);
							primaryStage.setTitle("Centru Medical");
							// primaryStage.getIcons().add(new Image("/images/logo.png"));
							// primaryStage.setResizable(false);
							primaryStage.show();
							
							
							WindowManager.setCurrentStage(primaryStage);
							// Stage stageToClose = (Stage) loginAnchorPane.getScene().getWindow();
							// stageToClose.close();
							// LoginController controller = loader.getController();
							// controller.populateComboBox();

						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						messageLabel.setText("A aparut o eroare!");
					}

				} else {
					messageLabel.setText("Parola este introdusa gresit");

				}
			} else {
				messageLabel.setText("User este inexistent");
			}
		}

	}

	/**
	 * deschiderea unui pane pentru inregistrare in functie de rolul utilzatorului
	 * 
	 * @param event
	 */
	@FXML
	void showRegisterPane(ActionEvent event) {

		switch (comboUsers.getSelectionModel().getSelectedItem()) {
		case PACIENT: {
			this.continuePane.setVisible(false);
			this.registrationPane.setVisible(true);
		}
			break;
		case MEDIC: {
			if (!(new BasicPasswordEncryptor().checkPassword(accessKey.getText(), accessPassword))) {
				notificationLbl.setText("Access key incorect!");
				return;
			}
			this.continuePane.setVisible(false);
			this.registrationMedicPane.setVisible(true);
			SpecializareDAO sDAO = new SpecializareDAO();
			this.comboboxSpecializare.setItems(FXCollections.observableArrayList(sDAO.getAllSpecializari()));
			this.comboboxSpecializare.getSelectionModel().selectFirst();
		}
			break;
		case ASISTENT: {
			if (!(new BasicPasswordEncryptor().checkPassword(accessKey.getText(), accessPassword))) {
				notificationLbl.setText("Access key incorect!");
				return;
			}
			this.continuePane.setVisible(false);
			this.registrationAsistentPane.setVisible(true);
		}
			break;
		case ADMIN: {
			if (!(new BasicPasswordEncryptor().checkPassword(accessKey.getText(), accessPassword))) {
				notificationLbl.setText("Access key incorect!");
				return;
			}
			this.continuePane.setVisible(false);
			this.registrationAdminPane.setVisible(true);
		}
			break;

		}

		accessKey.setText("");
		notificationLbl.setText("");

	}

	/**
	 * metoda pentru inregisrarea pacientilor
	 * 
	 * @param event
	 */
	@FXML
	void registrationAction(ActionEvent event) {
		try {
			if (this.userRegistr.getText().equals("") || this.passRegistr.getText().equals("")
					|| this.nameRegistr.getText().equals("") || this.prenumeRegistr.getText().equals("")
					|| this.cnpRegistr.getText().equals("") || this.sexRegistr.getText().equals("")
					|| this.telRegistr.getText().equals("") || this.adresaRegistr.getText().equals("")
					|| this.greutateRegistr.getText().equals("") || this.inaltimeRegistr.getText().equals("")) {
				messLabel.setText("Completati toate campurile");
			} else {

				if (!this.cnpRegistr.getText().matches(CNP_REGEX)) {
					messLabel.setText("CNP invalid!");
					return;
				}

				User user = new User(1, userRegistr.getText(), encryptPassword(passRegistr.getText()), null,
						this.comboUsers.getSelectionModel().getSelectedItem());
				UserDAO uDAO = new UserDAO();
				if (!uDAO.addUser(user)) {
					messLabel.setText("Username deja folosit!");
					return;
				}
				Pacient pacient = new Pacient(1, user.getId(), nameRegistr.getText(), prenumeRegistr.getText(),
						Long.parseLong(cnpRegistr.getText()), sexRegistr.getText(), dateRegistr.getValue(),
						Long.parseLong(telRegistr.getText()), adresaRegistr.getText(),
						Float.parseFloat(greutateRegistr.getText()), Float.parseFloat(inaltimeRegistr.getText()));
				PacientDAO pDAO = new PacientDAO();
				pDAO.addPacient(pacient);
				messLabel.setText("Ati fost inregistrat");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * metoda pentru inregisrarea medicilor
	 */
	@FXML
	void registrationMedic(ActionEvent event) {
		try {
			if (this.userRegistMedic.getText().equals("") || this.passRegistMedic.getText().equals("")
					|| this.nameRegistMedic.getText().equals("") || this.prenumeRegistrMedic.getText().equals("")) {
				messLabelForMedic.setText("Completati toate campurile");
			} else {
				User user = new User(1, userRegistMedic.getText(), encryptPassword(passRegistMedic.getText()), null,
						this.comboUsers.getSelectionModel().getSelectedItem());
				UserDAO uDAO = new UserDAO();
				uDAO.addUser(user);
				Medic medic = new Medic(1, 1, user.getId(), nameRegistMedic.getText(), prenumeRegistrMedic.getText(),
						dateRegistr.getValue());

				MedicDAO mDAO = new MedicDAO();
				mDAO.addMedic(medic);
				messLabelForMedic.setText("Ati fost inregistrat");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void registrationAsistent(ActionEvent event) {
		try {
			if (this.userRegistAsist.getText().equals("") || this.passRegistAsist.getText().equals("")
					|| this.nameRegistAsist.getText().equals("") || this.prenumeRegistAsist.getText().equals("")) {
				messLabelForAsist.setText("Completati toate campurile");
			} else {
				User user = new User(1, userRegistAsist.getText(), encryptPassword(passRegistAsist.getText()), null,
						this.comboUsers.getSelectionModel().getSelectedItem());
				UserDAO uDAO = new UserDAO();
				uDAO.addUser(user);
				Asistent asistent = new Asistent(1, user.getId(), nameRegistAsist.getText(),
						prenumeRegistAsist.getText());

				AsistentDAO aDAO = new AsistentDAO();
				aDAO.addAsistent(asistent);
				messLabelForAsist.setText("Ati fost inregistrat");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void registrationAdmin(ActionEvent event) {
		try {
			if (this.userRegistAdmin.getText().equals("") || this.passRegistAdmin.getText().equals("")
					|| this.nameRegistAdmin.getText().equals("") || this.prenumeRegistAdmin.getText().equals("")) {
				messageLblAdmin.setText("Completati toate campurile");
			} else {
				User user = new User(1, userRegistAdmin.getText(), encryptPassword(passRegistAdmin.getText()), null,
						this.comboUsers.getSelectionModel().getSelectedItem());
				UserDAO uDAO = new UserDAO();
				uDAO.addUser(user);
				Administrator admin = new Administrator(1, user.getId(), nameRegistAdmin.getText(),
						prenumeRegistAdmin.getText());

				new AdministratorDAO().addAdministrator(admin);
				messageLblAdmin.setText("Ati fost inregistrat");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void actionBackToContinueFromAdmin(ActionEvent event) {

		this.registrationAdminPane.setVisible(false);
		this.continuePane.setVisible(true);
	}

	@FXML
	void actionBackToMain(ActionEvent event) {
		this.loginPane.setVisible(false);
		this.continuePane.setVisible(true);

	}

	@FXML
	void actionBackToContinue(ActionEvent event) {
		this.registrationPane.setVisible(false);
		this.continuePane.setVisible(true);

	}

	@FXML
	void continueToLogin(ActionEvent event) {
		loginPane.setVisible(true);
		continuePane.setVisible(false);

	}

	@FXML
	void actionBackToContinueFromMedic(ActionEvent event) {
		this.registrationMedicPane.setVisible(false);
		this.continuePane.setVisible(true);

	}

	@FXML
	void actionBackToContinueFromAsistent(ActionEvent event) {
		this.registrationAsistentPane.setVisible(false);
		this.continuePane.setVisible(true);
	}

	@FXML
	void selectedItemChanged(ActionEvent event) {
		Rol rol = comboUsers.getSelectionModel().getSelectedItem();
		if (rol != Rol.PACIENT) {
			accessKeyMessage.setVisible(true);
			accessKey.setVisible(true);

		} else {
			accessKeyMessage.setVisible(false);
			accessKey.setVisible(false);

		}

	}

	@FXML
	void bc0d0d(ActionEvent event) {

	}

}
