package metier;

public class Client {
private int num;
private String nom;
private String pr�nom;
private String nationalit�;
private String numtel;

public Client(String nom, String pr�nom, String nationalit�, String numtel) {
	this.nom = nom;
	this.pr�nom = pr�nom;
	this.nationalit� = nationalit�;
	this.numtel = numtel;
}

public Client(int num, String nom, String pr�nom, String nationalit�, String numtel) {
	this.num=num;
	this.nom = nom;
	this.pr�nom = pr�nom;
	this.nationalit� = nationalit�;
	this.numtel = numtel;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getPr�nom() {
	return pr�nom;
}

public void setPr�nom(String pr�nom) {
	this.pr�nom = pr�nom;
}

public String getNationalit�() {
	return nationalit�;
}

public void setNationalit�(String nationalit�) {
	this.nationalit� = nationalit�;
}

public String getNumtel() {
	return numtel;
}

public void setNumtel(String numtel) {
	this.numtel = numtel;
}

@Override
public String toString() {
	return "Client [nom=" + nom + ", pr�nom=" + pr�nom + ", nationalit�=" + nationalit� + ", numtel=" + numtel + "]";
}



}
