package controleur;

import java.util.Date;

import accesBD.TousReservations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AfficheurReservationControleur {
	
	TousReservations reservations=new TousReservations();

    @FXML
    private TableView<TousReservations> reservation;

    @FXML
    private TableColumn<TousReservations, String> nomcl;

    @FXML
    private TableColumn<TousReservations, String> precl;

    @FXML
    private TableColumn<TousReservations, Date> datedeblog;

    @FXML
    private TableColumn<TousReservations, Date> datefinlog;

    @FXML
    private TableColumn<TousReservations, String> typelog;

    @FXML
    private TableColumn<TousReservations, Integer> numchambre;

    @FXML
    private Button chercher;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtprenom;
    
    @FXML
    private Button btntousres;

    @FXML
    private Button btnrescour;
    
    
    public void initialize() {
		nomcl.setCellValueFactory(new PropertyValueFactory<TousReservations,String>("nom"));
		precl.setCellValueFactory(new PropertyValueFactory<TousReservations,String>("prenom"));
		datedeblog.setCellValueFactory(new PropertyValueFactory<TousReservations,Date>("datedeb"));
		datefinlog.setCellValueFactory(new PropertyValueFactory<TousReservations,Date>("datefin"));
		typelog.setCellValueFactory(new PropertyValueFactory<TousReservations,String>("typel"));
		numchambre.setCellValueFactory(new PropertyValueFactory<TousReservations,Integer>("numch"));
		 reservation.getItems().setAll(reservations.getListReservationCourant());
		
	}
    
    @FXML
    void chercherres(ActionEvent event) {
    	reservation.getItems().clear();
    	nomcl.setCellValueFactory(new PropertyValueFactory<TousReservations,String>("nom"));
		precl.setCellValueFactory(new PropertyValueFactory<TousReservations,String>("prenom"));
		datedeblog.setCellValueFactory(new PropertyValueFactory<TousReservations,Date>("datedeb"));
		datefinlog.setCellValueFactory(new PropertyValueFactory<TousReservations,Date>("datefin"));
		typelog.setCellValueFactory(new PropertyValueFactory<TousReservations,String>("typel"));
		numchambre.setCellValueFactory(new PropertyValueFactory<TousReservations,Integer>("numch"));
		System.out.println(nomcl.getText());
		reservation.getItems().setAll(reservations.getListReservationParNom(txtnom.getText(),txtprenom.getText()));
    }
    
    @FXML
    void affrescour(ActionEvent event) {
    	reservation.getItems().clear();
    	nomcl.setCellValueFactory(new PropertyValueFactory<TousReservations,String>("nom"));
		precl.setCellValueFactory(new PropertyValueFactory<TousReservations,String>("prenom"));
		datedeblog.setCellValueFactory(new PropertyValueFactory<TousReservations,Date>("datedeb"));
		datefinlog.setCellValueFactory(new PropertyValueFactory<TousReservations,Date>("datefin"));
		typelog.setCellValueFactory(new PropertyValueFactory<TousReservations,String>("typel"));
		numchambre.setCellValueFactory(new PropertyValueFactory<TousReservations,Integer>("numch"));
		 reservation.getItems().setAll(reservations.getListReservationCourant());
    }

    @FXML
    void afftousres(ActionEvent event) {
    	reservation.getItems().clear();
    	nomcl.setCellValueFactory(new PropertyValueFactory<TousReservations,String>("nom"));
		precl.setCellValueFactory(new PropertyValueFactory<TousReservations,String>("prenom"));
		datedeblog.setCellValueFactory(new PropertyValueFactory<TousReservations,Date>("datedeb"));
		datefinlog.setCellValueFactory(new PropertyValueFactory<TousReservations,Date>("datefin"));
		typelog.setCellValueFactory(new PropertyValueFactory<TousReservations,String>("typel"));
		numchambre.setCellValueFactory(new PropertyValueFactory<TousReservations,Integer>("numch"));
		 reservation.getItems().setAll(reservations.getListReservation());
    }


}
