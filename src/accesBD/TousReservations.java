package accesBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TousReservations {
	private  SimpleStringProperty nom;
	private  SimpleStringProperty prenom;
	private ObjectProperty<Date> datedeb ;
	private ObjectProperty<Date> datefin ;
	private  SimpleStringProperty typel;
	private  SimpleIntegerProperty numch;


	public TousReservations(){}

	public TousReservations(String nom, String prenom, Date datedeb, Date datefin, String typel, int numch){
	    this.nom = new SimpleStringProperty(nom);
	    this.prenom = new SimpleStringProperty(prenom);
	    this.datedeb = new SimpleObjectProperty<Date>(datedeb);	
	    this.datefin = new SimpleObjectProperty<Date>(datefin);	
	    this.typel = new SimpleStringProperty(typel);
	    this.numch = new SimpleIntegerProperty(numch);

	   
	  }  
	  

	public List<TousReservations> getListReservationCourant(){
		  List <TousReservations>reservations= new LinkedList();
			Connection cnx= SConnection.getInstance();
			String sql = "select * from (reservation inner join client on reservation.numcl =client.NUMCL) join typelogement on reservation.TYPEID = typelogement.ID where datefin >=TRUNC(SYSDATE) and datedeb<=TRUNC(SYSDATE)";		
			try {
				PreparedStatement st= cnx.prepareStatement(sql);
				ResultSet res= st.executeQuery();
				while (res.next()) {
					    String nom = res.getString(7);
					    String prenom = res.getString(8);
					    Date datedeb = res.getDate(2);
				        Date datefin = res.getDate(3);
				        String typel = res.getString(12);
				        int numch = res.getInt(5);
				        reservations.add(new TousReservations( nom, prenom, datedeb, datefin, typel,numch));
					
					}
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return reservations;
	  }
	  
	  public List<TousReservations> getListReservation(){
		  List <TousReservations>reservations= new LinkedList();
			Connection cnx= SConnection.getInstance();
			String sql = "select * from (reservation inner join client on reservation.numcl =client.NUMCL) join typelogement on reservation.TYPEID = typelogement.ID";
			
			try {
				PreparedStatement st= cnx.prepareStatement(sql);
				ResultSet res= st.executeQuery();
				while (res.next()) {
					    String nom = res.getString(7);
					    String prenom = res.getString(8);
					    Date datedeb = res.getDate(2);
				        Date datefin = res.getDate(3);
				        String typel = res.getString(12);
				        int numch = res.getInt(5);
				        reservations.add(new TousReservations( nom, prenom, datedeb, datefin, typel,numch));
					
					}
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return reservations;
	  }
	  
	  public List<TousReservations> getListReservationParNom(String nomm, String prenomm){
		  List <TousReservations>reservations= new LinkedList();
			Connection cnx= SConnection.getInstance();
			String sql = "select * from (reservation inner join client on reservation.numcl =client.NUMCL) join typelogement on reservation.TYPEID = typelogement.ID where nomcl=? or prenomcl=?";
			
			try {
				PreparedStatement st= cnx.prepareStatement(sql);
				st.setString(1, nomm);
				st.setString(2, prenomm);
				ResultSet res= st.executeQuery();
				while (res.next()) {
					    String nom = res.getString(7);
					    String prenom = res.getString(8);
					    Date datedeb = res.getDate(2);
				        Date datefin = res.getDate(3);
				        String typel = res.getString(12);
				        int numch = res.getInt(5);
				        reservations.add(new TousReservations( nom, prenom, datedeb, datefin, typel,numch));
					
					}
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return reservations;
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

	public SimpleStringProperty prenomProperty() {
		return prenom;
	}
	
	 public final String getPrenom() {
	        return prenomProperty().get();
	    }

	public void setPrenom(SimpleStringProperty prenom) {
		this.prenom = prenom;
	}

	public ObjectProperty<Date> datedebProperty() {
		return datedeb;
	}

	 public final Date getDatedeb() {
	        return datedebProperty().get();
	    }
	
	public void setDatedeb(ObjectProperty<Date> datedeb) {
		this.datedeb = datedeb;
	}

	public ObjectProperty<Date> datefinProperty() {
		return datefin;
	}

	 public final Date getDatefin() {
	        return datefinProperty().get();
	    }
	
	public void setDatefin(ObjectProperty<Date> datefin) {
		this.datefin = datefin;
	}

	public SimpleStringProperty typelProperty() {
		return typel;
	}
	
	public final String getTypel() {
        return typelProperty().get();
    }

	public void setTypel(SimpleStringProperty typel) {
		this.typel = typel;
	}

	public SimpleIntegerProperty numchProperty() {
		return numch;
	}
	
	public final int getNumch() {
        return numchProperty().get();
    }

	public void setNumch(SimpleIntegerProperty numch) {
		this.numch = numch;
	}

	@Override
	public String toString() {
		return "TousReservations [nom=" + nom + ", prenom=" + prenom + ", datedeb=" + datedeb + ", datefin=" + datefin
				+ ", typel=" + typel + ", numch=" + numch + "]";
	}



}