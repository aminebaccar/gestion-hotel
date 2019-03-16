package accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import metier.Client;
import metier.TypeLogement;

public class TypeLogementDAO {
	public TypeLogement find(int id){
		Connection cnx =SConnection.getInstance();
		TypeLogement c=null;
		try {PreparedStatement stp1=cnx.prepareStatement("select * from typelogement where id=?");
			stp1.setInt(1, id);
			ResultSet n= stp1.executeQuery();
			while(n.next()){
				c= new TypeLogement(n.getInt(1),n.getString(2),n.getDouble(3));
			}
			//cnx.close();
			n.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return c;

	}
}
