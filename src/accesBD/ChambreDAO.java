package accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import metier.Couple;
import metier.Individuel;
import metier.TypeLogement;

public class ChambreDAO {
	public Couple findFirstCouple(int b) throws SQLException {
		Collection<Couple> couples= new ArrayList<>();
		Connection cnx= SConnection.getInstance();
		Couple couple = null;
		try {
			PreparedStatement st= cnx.prepareStatement("SELECT * FROM chambre LEFT JOIN reservation ON chambre.num = reservation.numch WHERE reservation.numch IS NULL and nbrlits=0 and salledebain=?");
			st.setInt(1, b);
			ResultSet res= st.executeQuery();
			if(res.next()) {
				couple=new Couple(res.getInt(1), res.getInt(2),res.getInt(4));
				System.out.println(res.getRow());
				}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return couple;//la collection retournée peut être vide
	}
	
	public Individuel findFirstInd(int b) throws SQLException {
		Collection<Individuel> individuels= new ArrayList<>();
		Connection cnx= SConnection.getInstance();
		Individuel individuel= null;;
		try {
			PreparedStatement st= cnx.prepareStatement("SELECT * FROM chambre LEFT JOIN reservation ON chambre.num = reservation.numch WHERE reservation.numch IS NULL and nbrlits<>0 and salledebain=?");
			st.setInt(1, b);
			ResultSet res= st.executeQuery();
			res.next();
			individuel = new Individuel (res.getInt(1), res.getInt(2), res.getInt(3));
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return individuel;//la collection retournée peut être vide
	}
	
	public Couple findCou(int num){
		Connection cnx =SConnection.getInstance();
		Couple c=null;
		try {PreparedStatement stp1=cnx.prepareStatement("select * from reservation where numch=? and nbrlits=0");
			stp1.setInt(1, num);
			ResultSet n= stp1.executeQuery();
			while(n.next()){
				c= new Couple(n.getInt(1), n.getInt(2),n.getInt(4));
			}
			cnx.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return c;

	}
	
}
