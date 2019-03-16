package accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import metier.ConvIntToBool;
import metier.Logement;
import metier.TypeLogement;

public class LogementDAO {
	TypeLogementDAO bd = new TypeLogementDAO();
	
	
	 public List<Logement> findAll(){
		  List <Logement>logements= new LinkedList();
			Connection cnx= SConnection.getInstance();
			String sql = "select * from logement";
			try {
				PreparedStatement st= cnx.prepareStatement(sql);
				ResultSet res= st.executeQuery();
				while (res.next()) {
						TypeLogement tl = bd.find(res.getInt(1));
					    int restoinc = res.getInt(2);
					    int restocarte = res.getInt(3);
					    int boite = res.getInt(5);
					    int cafe = res.getInt(4);
					    int bar = res.getInt(6);
					    logements.add(new Logement(tl,ConvIntToBool.boolToInt(restoinc),ConvIntToBool.boolToInt
					    		(restocarte),ConvIntToBool.boolToInt(boite),ConvIntToBool.boolToInt(cafe),ConvIntToBool.boolToInt(bar)));
					
					}
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return logements;
	  }
	 
	 public boolean update(Logement l) throws SQLException {
			if(l==null) return false;
			Connection cnx= SConnection.getInstance();
			int n=0;
			try {
				PreparedStatement st= cnx.prepareStatement("update logement set restoinc = ?, restocarte = ?, boite = ?, cafe = ?, bar=? WHERE typelogement=?");
				st.setInt(6, l.getTp().getId());
				st.setInt(1, ConvIntToBool.intToBool(l.isRestoinc()));
				st.setInt(2, ConvIntToBool.intToBool(l.isRestocarte()));
				st.setInt(4, ConvIntToBool.intToBool(l.isBoite()));
				st.setInt(3, ConvIntToBool.intToBool(l.isCafe()));
				st.setInt(5, ConvIntToBool.intToBool(l.isBar()));
				n= st.executeUpdate();//le livre et son historique sont supprimés 
									  // grâce à la contrainte d'intégrité référentielle
				                       //et sa clause ON DELETE CASCADE
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return n>0;
		}
}
