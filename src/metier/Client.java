package metier;

public class Client {
private int num;
private String nom;
private String prénom;
private String nationalité;
private String numtel;

public Client(String nom, String prénom, String nationalité, String numtel) {
	this.nom = nom;
	this.prénom = prénom;
	this.nationalité = nationalité;
	this.numtel = numtel;
}

public Client(int num, String nom, String prénom, String nationalité, String numtel) {
	this.num=num;
	this.nom = nom;
	this.prénom = prénom;
	this.nationalité = nationalité;
	this.numtel = numtel;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getPrénom() {
	return prénom;
}

public void setPrénom(String prénom) {
	this.prénom = prénom;
}

public String getNationalité() {
	return nationalité;
}

public void setNationalité(String nationalité) {
	this.nationalité = nationalité;
}

public String getNumtel() {
	return numtel;
}

public void setNumtel(String numtel) {
	this.numtel = numtel;
}

@Override
public String toString() {
	return "Client [nom=" + nom + ", prénom=" + prénom + ", nationalité=" + nationalité + ", numtel=" + numtel + "]";
}



}
