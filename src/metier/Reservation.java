package metier;

import java.util.Date;

public class Reservation {
	
private Client client;
private Date datedeb;
private Date datefin;
private TypeLogement typel;
public Reservation(Client client, Date datedeb, Date datefin, TypeLogement typel) {
	this.client = client;
	this.datedeb = datedeb;
	this.datefin = datefin;
	this.typel = typel;
}
public Client getClient() {
	return client;
}
public void setClient(Client client) {
	this.client = client;
}
public Date getDatedeb() {
	return datedeb;
}
public void setDatedeb(Date datedeb) {
	this.datedeb = datedeb;
}
public Date getDatefin() {
	return datefin;
}
public void setDatefin(Date datefin) {
	this.datefin = datefin;
}
public TypeLogement getTypel() {
	return typel;
}
public void setTypel(TypeLogement typel) {
	this.typel = typel;
}


}
