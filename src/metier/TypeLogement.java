package metier;

public class TypeLogement {
 private int id;
 private String libelle;
 private double prixparnuit;
public TypeLogement(int id, String libelle, double prixparnuit) {
	this.id = id;
	this.libelle = libelle;
	this.prixparnuit = prixparnuit;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public double getPrixparnuit() {
	return prixparnuit;
}
public void setPrixparnuit(double prixparnuit) {
	this.prixparnuit = prixparnuit;
}
 


}
