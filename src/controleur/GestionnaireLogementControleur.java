package controleur;

import java.sql.SQLException;
import java.util.List;

import accesBD.LogementDAO;
import accesBD.TypeLogementDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import metier.Logement;

public class GestionnaireLogementControleur {

	LogementDAO bd = new LogementDAO();
	TypeLogementDAO bd1 = new TypeLogementDAO();
    @FXML
    private CheckBox rs;

    @FXML
    private CheckBox rs1;

    @FXML
    private CheckBox rs2;

    @FXML
    private CheckBox rs3;

    @FXML
    private CheckBox rs4;

    @FXML
    private CheckBox rs5;

    @FXML
    private CheckBox rs11;

    @FXML
    private CheckBox rs21;

    @FXML
    private CheckBox rs31;

    @FXML
    private CheckBox rs41;

    @FXML
    private CheckBox rs6;

    @FXML
    private CheckBox rs12;

    @FXML
    private CheckBox rs22;

    @FXML
    private CheckBox rs32;

    @FXML
    private CheckBox rs42;

    @FXML
    private Button app;

    
    public void initialize() throws SQLException{
    	List<Logement> lg = bd.findAll();
    	
    	System.out.println(lg.get(0));
    	System.out.println(lg.get(1));
    	System.out.println(lg.get(2));
    	
    	rs.setSelected(lg.get(0).isRestoinc());
    	rs1.setSelected(lg.get(0).isRestocarte());
    	rs2.setSelected(lg.get(0).isCafe());
    	rs3.setSelected(lg.get(0).isBoite());
    	rs4.setSelected(lg.get(0).isBar());
    	
    	rs5.setSelected(lg.get(1).isRestoinc());
    	rs11.setSelected(lg.get(1).isRestocarte());
    	rs21.setSelected(lg.get(1).isCafe());
    	rs31.setSelected(lg.get(1).isBoite());
    	rs41.setSelected(lg.get(1).isBar());
    	
    	rs6.setSelected(lg.get(2).isRestoinc());
    	rs12.setSelected(lg.get(2).isRestocarte());
    	rs22.setSelected(lg.get(2).isCafe());
    	rs32.setSelected(lg.get(2).isBoite());
    	rs42.setSelected(lg.get(2).isBar());
    	
    }
    @FXML
    void appliquer(ActionEvent event) throws SQLException {

    	Logement l = new Logement(bd1.find(1),rs.isSelected(),rs1.isSelected(),rs2.isSelected(),rs3.isSelected(),rs4.isSelected());
    	Logement l1 = new Logement(bd1.find(2),rs5.isSelected(),rs11.isSelected(),rs21.isSelected(),rs31.isSelected(),rs41.isSelected());
    	Logement l2 = new Logement(bd1.find(3),rs6.isSelected(),rs12.isSelected(),rs22.isSelected(),rs32.isSelected(),rs42.isSelected());
    	bd.update(l);
    	bd.update(l1);
    	bd.update(l2);
    }

}
