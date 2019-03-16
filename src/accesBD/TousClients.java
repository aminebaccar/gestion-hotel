package accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TousClients {
	
	private  SimpleIntegerProperty numclt;
	private  SimpleStringProperty nomclt;
	private  SimpleStringProperty prenomclt;
	private  SimpleStringProperty natclt;
	private  SimpleStringProperty numtel;

	public TousClients(){}

	public TousClients(int numclt, String nomclt, String prenomclt,
			String natclt, String numtel) {
	
		this.numclt = new SimpleIntegerProperty(numclt);
		this.nomclt = new SimpleStringProperty(nomclt);
		this.prenomclt = new SimpleStringProperty(prenomclt);
		this.natclt = new SimpleStringProperty(natclt);
		this.numtel = new SimpleStringProperty(numtel);
	}

	  public List<TousClients> getListClients(){
		  List <TousClients>clients= new LinkedList();
			Connection cnx= SConnection.getInstance();
			String sql = "select * from client";
			try {
				PreparedStatement st= cnx.prepareStatement(sql);
				ResultSet res= st.executeQuery();
				while (res.next()) {
						int num = res.getInt(1);
					    String nom = res.getString(2);
					    String prenom = res.getString(3);
					    String nationalite = res.getString(4);
					    String numtel = res.getString(5);
				        clients.add(new TousClients(num,nom,prenom,nationalite,numtel));
					
					}
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return clients;
	  }

	  public List<TousClients> getListClientsNat(String nat){
		  List <TousClients>clients= new LinkedList();
			Connection cnx= SConnection.getInstance();
			String sql = "select * from client where nationalite=?";
			try {
				PreparedStatement st= cnx.prepareStatement(sql);
				st.setString(1,nat );
				ResultSet res= st.executeQuery();
				while (res.next()) {
						int num = res.getInt(1);
					    String nom = res.getString(2);
					    String prenom = res.getString(3);
					    String nationalite = res.getString(4);
					    String numtel = res.getString(5);
				        clients.add(new TousClients(num,nom,prenom,nationalite,numtel));		
					}
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return clients;
	  }
	
	public SimpleIntegerProperty numcltProperty() {
		return numclt;
	}
	
	public final int getNumclt() {
		return numcltProperty().get();
	}
	
	public void setNumclt(SimpleIntegerProperty numclt) {
		this.numclt = numclt;
	}
	
	public SimpleStringProperty nomProperty() {
		return nomclt;
	}
	
	public final String getNomclt() {
		return nomProperty().get();
	}

	public void setNomclt(SimpleStringProperty nomclt) {
		this.nomclt = nomclt;
	}

	public SimpleStringProperty prenomProperty() {
		return prenomclt;
	}
	
	public final String getPrenomclt() {
		return prenomProperty().get();
	}

	public void setPrenomclt(SimpleStringProperty prenomclt) {
		this.prenomclt = prenomclt;
	}
	
	public SimpleStringProperty natcltProperty() {
		return natclt;
	}
	public final String getNatclt() {
		return natcltProperty().get();
	}

	public void setNatclt(SimpleStringProperty natclt) {
		this.natclt = natclt;
	}
	public SimpleStringProperty numtelProperty() {
		return numtel;
	}
	
	public final String getNumtel() {
		return numtelProperty().get();
	}

	public void setNumtel(SimpleStringProperty numtel) {
		this.numtel = numtel;
	}
	
	/*public SimpleStringProperty typeeProperty() {
		return typee;
	}
	
	 public final String getTypee() {
	        return typeeProperty().get();
	    }

	public void setTypee(SimpleStringProperty type) {
		this.typee = type;
	}*/
	  

}
