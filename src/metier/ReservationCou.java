package metier;

import java.util.Date;

public class ReservationCou extends Reservation {

private Couple couple;
public ReservationCou(Client client, Date datedeb, Date datefin, TypeLogement typel, Couple couple) {
	super(client, datedeb, datefin, typel);
	this.couple=couple;
	// TODO Auto-generated constructor stub
	
}
public Couple getCouple() {
	return couple;
}
public void setCouple(Couple couple) {
	this.couple = couple;
}
@Override
public String toString() {
	return "ReservationCou [couple=" + couple + "]";
}



}
