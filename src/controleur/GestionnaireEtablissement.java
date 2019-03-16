package controleur;

import java.sql.SQLException;

import accesBD.EtablissementDAO;
import accesBD.TousEtablissement;
import accesBD.TousReservations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import metier.Etablissement;

public class GestionnaireEtablissement {

	EtablissementDAO bd = new EtablissementDAO();
	TousEtablissement etablissements=new TousEtablissement();
	
    @FXML
    private TableView<TousEtablissement> tbl;

    @FXML
    private TableColumn<TousEtablissement, Integer> id;

    @FXML
    private TableColumn<TousEtablissement, String> lib;

    @FXML
    private TableColumn<TousEtablissement,String> type;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtlib;

    @FXML
    private ComboBox<String> cbxtype;

    @FXML
    private Button btnajt;
    
    public void initialize(){
    	id.setCellValueFactory(new PropertyValueFactory<TousEtablissement,Integer>("idd"));
    	lib.setCellValueFactory(new PropertyValueFactory<TousEtablissement,String>("nom"));
		type.setCellValueFactory(new PropertyValueFactory<TousEtablissement,String>("typee"));
		tbl.getItems().setAll(etablissements.getListEtablissement());
    	cbxtype.getItems().addAll("restocarte","restoinc","bar","boite","cafe");
    	cbxtype.getSelectionModel().select(1);
    }

    @FXML
    void ajoutetab(ActionEvent event) throws SQLException {
    	if(txtid.getText().isEmpty() || txtlib.getText().isEmpty()){
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Erreur Champs");
    		alert.setHeaderText("Remplissez tous les champs!");
    		alert.showAndWait().ifPresent(rs -> {
    		    if (rs == ButtonType.OK) {
    		        System.out.println("Pressed OK.");
    		    }
    		});
    	}
    	Etablissement et = new Etablissement(Integer.parseInt(txtid.getText()),txtlib.getText(),cbxtype.getValue());
    	if(bd.findAll().contains(et.getId())){
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Erreur Ajout");
    		alert.setHeaderText("Identifiant Existant!");
    		alert.showAndWait().ifPresent(rs -> {
    		    if (rs == ButtonType.OK) {
    		        System.out.println("Pressed OK.");
    		    }
    		});
    	}
    	bd.add(et);
    	tbl.getItems().setAll(etablissements.getListEtablissement());
    }

    @FXML
    void suppretab(ActionEvent event) {
    	TousEtablissement te = tbl.getSelectionModel().getSelectedItem();
    	Etablissement et = new Etablissement(te.getIdd(),te.getNom(),te.getTypee());
    	try {
			bd.delete(et);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	tbl.getItems().setAll(etablissements.getListEtablissement());
    }

}
