package accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import metier.*;

public class ReservationDAO {
	TypeLogementDAO bd = new TypeLogementDAO();
	ChambreDAO bd1 = new ChambreDAO();
	ClientDAO bd2= new ClientDAO();
	public boolean addCou(int numcl, ReservationCou rc)
	{
		Connection cnx = SConnection.getInstance();
		try {
			Calendar c1 = GregorianCalendar.getInstance();
			Calendar c2 = GregorianCalendar.getInstance();
			c1.setTime(rc.getDatedeb());
			c2.setTime(rc.getDatefin());
			java.util.Date date1 = c1.getTime();
			java.util.Date date2 = c2.getTime();
			java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
			java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
			System.out.println(date2);
			
			PreparedStatement stp=cnx.prepareStatement("insert into reservation values(?,?,?,?,?)");
			stp.setInt(1, numcl);
			stp.setDate(2, sqlDate1);
			stp.setDate(3, sqlDate2);
			stp.setInt(4, rc.getTypel().getId());
			stp.setInt(5, rc.getCouple().getNum());
			int n = stp.executeUpdate();
			if(n==1){System.out.println("Reservation ajouté"); return true;}
			else{System.out.println("Reservation n'est pas ajouté"); return false;}
		} catch (SQLException e) {
			System.out.println("Problème d'ajout reservation: "+e.getMessage());
			return false;
		}
		
	}
	public boolean addInd(int numcl, ReservationInd ri)
	{
		Connection cnx = SConnection.getInstance();
		try {
			Calendar c1 = GregorianCalendar.getInstance();
			Calendar c2 = GregorianCalendar.getInstance();
			c1.setTime(ri.getDatedeb());
			c2.setTime(ri.getDatefin());
			java.util.Date date1 = c1.getTime();
			java.util.Date date2 = c2.getTime();
			java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
			java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		
			
			PreparedStatement stp=cnx.prepareStatement("insert into reservation values(?,?,?,?,?)");
			stp.setInt(1, numcl);
			stp.setDate(2, sqlDate1);
			stp.setDate(3, sqlDate2);
			stp.setInt(4, ri.getTypel().getId());
			stp.setInt(5, ri.getIndividuel().getNum());
			int n = stp.executeUpdate();
			if(n==1){System.out.println("Reservation ajouté"); return true;}
			else{System.out.println("Reservation n'est pas ajouté"); return false;}
		} catch (SQLException e) {
			System.out.println("Problème d'ajout reservation: "+e.getMessage());
			return false;
		}
		
	}
	
	public Reservation findCou(Reservation rc){
		Connection cnx =SConnection.getInstance();
		Calendar c1 = GregorianCalendar.getInstance();
		Calendar c2 = GregorianCalendar.getInstance();
		java.util.Date date1 = c1.getTime();
		java.util.Date date2 = c2.getTime();
		c1.setTime(rc.getDatedeb());
		c2.setTime(rc.getDatefin());
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
		java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		ReservationCou rcc=null;
		try {PreparedStatement stp1=cnx.prepareStatement("select * from reservation where numcl=? and datedeb=? and datefin=? order by datefin desc");
			stp1.setInt(1, bd2.findNum(rc.getClient().getNom(), rc.getClient().getPrénom()));
			stp1.setDate(2, sqlDate1);
			stp1.setDate(3, sqlDate2);
			ResultSet n= stp1.executeQuery();
			if(n.next()){
				rcc= new ReservationCou(bd2.find(rc.getClient().getNom(), rc.getClient().getPrénom()),n.getDate(2),n.getDate(3),bd.find(n.getInt(4)),bd1.findCou(n.getInt(5)));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rcc;

	}
	

}
