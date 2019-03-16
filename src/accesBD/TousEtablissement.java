package accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.*;

public class TousEtablissement {
	
		private  SimpleIntegerProperty idd;
		private  SimpleStringProperty nom;
		private  SimpleStringProperty typee;

		public TousEtablissement(){}

		public TousEtablissement(int id, String nom, String type){
		    this.idd = new SimpleIntegerProperty(id);
		    this.nom = new SimpleStringProperty(nom);
		    this.typee = new SimpleStringProperty(type);	

		   
		  }  
		  

		
		  
		  public List<TousEtablissement> getListEtablissement(){
			  List <TousEtablissement>etablissements= new LinkedList();
				Connection cnx= SConnection.getInstance();
				String sql = "select * from etablissement";
				
				try {
					PreparedStatement st= cnx.prepareStatement(sql);
					ResultSet res= st.executeQuery();
					while (res.next()) {
							int id = res.getInt(1);
						    String nom = res.getString(2);
						    String type = res.getString(3);
					        etablissements.add(new TousEtablissement(id,nom,type));
						
						}
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return etablissements;
		  }
		  

		public SimpleIntegerProperty iddProperty() {
			return idd;
		}
		
		 public final int getIdd() {
		        return iddProperty().get();
		    }

		public void setIdd(SimpleIntegerProperty id) {
			this.idd = id;
		}

		public SimpleStringProperty nomProperty() {
			return nom;
		}
		
		 public final String getNom() {
		        return nomProperty().get();
		    }

		public void setNom(SimpleStringProperty nom) {
			this.nom = nom;
		}
		
		public SimpleStringProperty typeeProperty() {
			return typee;
		}
		
		 public final String getTypee() {
		        return typeeProperty().get();
		    }

		public void setTypee(SimpleStringProperty type) {
			this.typee = type;
		}




}
