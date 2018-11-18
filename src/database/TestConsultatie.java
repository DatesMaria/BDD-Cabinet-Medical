package database;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.cabinetMedical.Consultatie;
import model.user.Administrator;
import model.user.User;

public class TestConsultatie {
	public static void main(String[] args) {
		
	/*	ConsultatieDAO cDAO = new ConsultatieDAO();

		Consultatie c1 = new Consultatie(1,2,1,1,2,"anulata",new Date(2017,03,15),"Consultatia dumneavoastra ");
		Consultatie c2 = new Consultatie(2,1,1,2,1,"realizata",new Date(2017,04,19),"Consultatia dumneavoastra ");
		
		cDAO.addConsultatie(c1);
		cDAO.addConsultatie(c2);// adauga o noua intrare
		cDAO.delete(1);// STERGE INTRAREA 1

		/*Employee foundEmp = cDAO.findById(1);
		foundEmp.setName("Cristina");
		cDAO.update(foundEmp);

		int nr = 0;
		for (int i = 0; i < eDAO.getAllEmployees().length; i++) {
			if (eDAO.getAllEmployees()[i].getName().compareTo("Gigi") == 0)
				nr++;
		}
		System.out.println("numele gigi apare in lista de:" + nr);
		*/
		UserDAO uDAO= new UserDAO();
		//User u1= new User(1,"CORNEL","1234","CORNEL@YAHOO.COM","administrator");
		//uDAO.addUser(u1);
		//System.out.println(u1.getId());
		
		/*AdministratorDAO aDAO= new AdministratorDAO();
		Administrator a1= new Administrator (1,1,"cornel","ioan");
		aDAO.addAdministrator(a1);*/
	}
}
