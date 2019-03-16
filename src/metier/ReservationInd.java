package metier;

import java.util.Date;

public class ReservationInd extends Reservation {
private Individuel individuel;

public ReservationInd(Client client, Date datedeb, Date datefin, TypeLogement typel, Individuel individuel) {
	super(client, datedeb, datefin, typel);
	this.individuel=individuel;
}

public Individuel getIndividuel() {
	return individuel;
}

public void setIndividuel(Individuel individuel) {
	this.individuel = individuel;
}

@Override
public String toString() {
	return "ReservationInd [individuel=" + individuel + ", getClient()=" + getClient() + ", getDatedeb()="
			+ getDatedeb() + ", getDatefin()=" + getDatefin() + ", getTypel()=" + getTypel() + "]";
}


}
