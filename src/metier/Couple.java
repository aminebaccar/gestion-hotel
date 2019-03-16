package metier;

public class Couple extends Chambre {
private int taille ; //0 si standard 1 si royal


public Couple(int num, int salleDeBain, int taille) {
	super(num, salleDeBain);
	this.taille = taille;
}

public int getTaille() {
	return taille;
}

public void setTaille(int taille) {
	this.taille = taille;
}

@Override
public String toString() {
	return "Couple [taille=" + taille + " chambre=" + getNum() +"]";
}




}
