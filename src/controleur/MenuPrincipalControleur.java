package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPrincipalControleur {

    @FXML
    private Button btnajout;

    @FXML
    private Button btnaff;

    @FXML
    private Button btngest;

    @FXML
    private Button btnclt;
    
    @FXML
    private Button btnacc;

    @FXML
    void accetab(ActionEvent event) {

    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vue/GestionnaireLogement.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Gestionnaire d'accès établissements");
            stage.setScene(new Scene(root1));  
            stage.show();
          }
   	 catch(Exception e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void affres(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vue/AfficheurReservation.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Afficheur des reservations");
            stage.setScene(new Scene(root1));  
            stage.show();
          }
   	 catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void ajoutres(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vue/AjoutReservation.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Fiche de reservation");
            stage.setScene(new Scene(root1));  
            stage.show();
          }
   	 catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void affcli(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vue/AfficheurClient.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Afficheur des clients");
            stage.setScene(new Scene(root1));  
            stage.show();
          }
   	 catch(Exception e) {
			e.printStackTrace();
		}
    }
    

    @FXML
    void gestetab(ActionEvent event) {
    	try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vue/GestionnaireEtablissement.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Gestionnaire d'etablissement");
            stage.setScene(new Scene(root1));  
            stage.show();
          }
   	 catch(Exception e) {
			e.printStackTrace();
		}
    }
    }


