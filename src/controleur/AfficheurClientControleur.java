package controleur;

import accesBD.TousClients;
import accesBD.TousEtablissement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AfficheurClientControleur {

	TousClients clients=new TousClients();
	
    @FXML
    private TableView<TousClients> tbcli;

    @FXML
    private TableColumn<TousClients, Integer> numcli;

    @FXML
    private TableColumn<TousClients, String> nomcli;

    @FXML
    private TableColumn<TousClients, String> precli;

    @FXML
    private TableColumn<TousClients, String> natcli;
    
    @FXML
    private TableColumn<TousClients, String> numcltt;

    @FXML
    private TextField txtnat;

    @FXML
    private Button cherchnat;
   
    public void initialize(){
    numcli.setCellValueFactory(new PropertyValueFactory<TousClients,Integer>("numclt"));
	nomcli.setCellValueFactory(new PropertyValueFactory<TousClients,String>("nomclt"));
	precli.setCellValueFactory(new PropertyValueFactory<TousClients,String>("prenomclt"));
	natcli.setCellValueFactory(new PropertyValueFactory<TousClients,String>("natclt"));
	numcltt.setCellValueFactory(new PropertyValueFactory<TousClients,String>("numtel"));
	tbcli.getItems().setAll(clients.getListClients());}
    
    @FXML
    void chercher(ActionEvent event) {
    	String n = txtnat.getText();
        numcli.setCellValueFactory(new PropertyValueFactory<TousClients,Integer>("numclt"));
    	nomcli.setCellValueFactory(new PropertyValueFactory<TousClients,String>("nomclt"));
    	precli.setCellValueFactory(new PropertyValueFactory<TousClients,String>("prenomclt"));
    	natcli.setCellValueFactory(new PropertyValueFactory<TousClients,String>("natclt"));
    	numcltt.setCellValueFactory(new PropertyValueFactory<TousClients,String>("numtel"));
    	tbcli.getItems().setAll(clients.getListClientsNat(n));
    }

}
