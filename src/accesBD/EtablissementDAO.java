package accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import metier.Client;
import metier.Etablissement;

public class EtablissementDAO {
	public boolean add(Etablissement et)
	{
		Connection cnx = SConnection.getInstance();
		try {
			PreparedStatement stp=cnx.prepareStatement("insert into etablissement values(?,?,?)");
			stp.setInt(1, et.getId());
			stp.setString(2, et.getNom());
			stp.setString(3, et.getType());
			int n = stp.executeUpdate();
			if(n==1){System.out.println("Etablissement ajouté"); return true;}
			else{System.out.println("Etablissement n'est pas ajouté"); return false;}
		} catch (SQLException e) {
			System.out.println("Problème d'ajout etablissement: "+e.getMessage());
		}
		return true;
	}
	
	public boolean delete(Etablissement et) throws SQLException {
		if(et==null) return false;
		Connection cnx= SConnection.getInstance();
		int n=0;
		try {
			PreparedStatement st= cnx.prepareStatement("delete from etablissement where id= ?");
			st.setInt(1, et.getId());
			n= st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public Collection<Integer> findAll() throws SQLException {
		Collection<Integer> etablissements= new ArrayList<>();
		Connection cnx= SConnection.getInstance();
		int etablissement;
		try {
			PreparedStatement st= cnx.prepareStatement("select* from etablissement");
			ResultSet res= st.executeQuery();
			while (res.next()) {
				etablissement=res.getInt(1);
				System.out.println(res.getRow());
				etablissements.add(etablissement);
				}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return etablissements;//la collection retournée peut être vide
	}
	
	public Collection<Etablissement> findAllEtab() throws SQLException {
		Collection<Etablissement> etablissements= new ArrayList<>();
		Connection cnx= SConnection.getInstance();
		Etablissement etablissement;
		try {
			PreparedStatement st= cnx.prepareStatement("select * from etablissement");
			ResultSet res= st.executeQuery();
			while (res.next()) {
				etablissement = new Etablissement(res.getInt(1),res.getString(2), res.getString(3));
				System.out.println(res.getRow());
				etablissements.add(etablissement);
				}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return etablissements;//la collection retournée peut être vide
	}
	
	public Collection<String> find(String s) throws SQLException {
		Collection<String> etablissements= new ArrayList<>();
		Connection cnx= SConnection.getInstance();
		String etablissement;
		try {
			PreparedStatement st= cnx.prepareStatement("select* from etablissement where type=?");
			st.setString(1,s);
			ResultSet res= st.executeQuery();
			while (res.next()) {
				etablissement=res.getString(2);
				System.out.println(res.getRow());
				etablissements.add(etablissement);
				}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return etablissements;//la collection retournée peut être vide
	}
	
}
