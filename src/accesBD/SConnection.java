package accesBD;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class SConnection {
	private static String url="jdbc:oracle:thin:@localhost:1521:xe";
	private static String utilisateur="reception123";
	private static String mdp="manager";
	private static Connection cnx;
	
	public static Connection getInstance(){
		try {
			if(cnx==null || cnx.isClosed())
				cnx=DriverManager.getConnection(url,utilisateur,mdp);
		} catch (SQLException e) {
			System.out.println("Problème de connexion: "+e.getMessage());
		}
		return cnx;
	}
	
	public void close(){
		try {
			cnx.close();
		} catch (SQLException e) {
			
			System.out.println("Problème de fermeture connexion: "+e.getMessage());
		}
	}

}
	
/*    public static void main(String[] argv) {

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Pilote JDBC Oracle Invalide!");
            e.printStackTrace();
            return;

        }

        System.out.println("Pilote JDBC Oracle Enregistré!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "reception123", "manager");

        } catch (SQLException e) {

            System.out.println("Echec de Connection! Reflechissez le nom d'utilisateur et le mot de passe");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("Succès de Connection!");
        } else {
            System.out.println("Echec de Connection!");
        }
    }

}*/