package accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import metier.Client;



public class ClientDAO {
	public Client find(String nom, String prenom){
		Connection cnx =SConnection.getInstance();
		Client c=null;
		try {PreparedStatement stp1=cnx.prepareStatement("select * from client where nomcl=? and prenomcl=?");
			stp1.setString(1, nom);
			stp1.setString(2, prenom);
			ResultSet n= stp1.executeQuery();
			while(n.next()){
				c= new Client(n.getString(2),n.getString(3),n.getString(4),n.getString(5));
			}
			cnx.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return c;

	}
	
	
	public int findNum (String nom, String prenom){
		Connection cnx =SConnection.getInstance();
		int c=0;
		try {PreparedStatement stp1=cnx.prepareStatement("select * from client where nomcl=? and prenomcl=?");
			stp1.setString(1, nom);
			stp1.setString(2, prenom);
			ResultSet n= stp1.executeQuery();
			while(n.next()){
				c= n.getInt(1);
			}
			cnx.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return c;

	}
	
	public boolean add(Client c)
	{
		Connection cnx = SConnection.getInstance();
		try {
			PreparedStatement stp=cnx.prepareStatement("insert into client values(6,?,?,?,?)");
			stp.setString(1, c.getNom());
			stp.setString(2, c.getPrénom());
			stp.setString(3, c.getNationalité());
			stp.setString(4, c.getNumtel());
			int n = stp.executeUpdate();
			if(n==1){System.out.println("Client ajouté"); return true;}
			else{System.out.println("Client n'est pas ajouté"); return false;}
		} catch (SQLException e) {
			System.out.println("Problème d'ajout client: "+e.getMessage());
		}
		return true;
	}
	
}
