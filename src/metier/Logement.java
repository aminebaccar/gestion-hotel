package metier;

public class Logement {

	private TypeLogement tp;
	private boolean restoinc;
	private boolean restocarte;
	private boolean boite;
	private boolean cafe;
	private boolean bar;
	public Logement(TypeLogement tp, boolean restoinc, boolean restocarte, boolean boite, boolean cafe, boolean bar) {
		this.tp = tp;
		this.restoinc = restoinc;
		this.restocarte = restocarte;
		this.boite = boite;
		this.cafe = cafe;
		this.bar = bar;
	}
	public TypeLogement getTp() {
		return tp;
	}
	public void setTp(TypeLogement tp) {
		this.tp = tp;
	}
	public boolean isRestoinc() {
		return restoinc;
	}
	public void setRestoinc(boolean restoinc) {
		this.restoinc = restoinc;
	}
	public boolean isRestocarte() {
		return restocarte;
	}
	public void setRestocarte(boolean restocarte) {
		this.restocarte = restocarte;
	}
	public boolean isBoite() {
		return boite;
	}
	public void setBoite(boolean boite) {
		this.boite = boite;
	}
	public boolean isCafe() {
		return cafe;
	}
	public void setCafe(boolean cafe) {
		this.cafe = cafe;
	}
	public boolean isBar() {
		return bar;
	}
	public void setBar(boolean bar) {
		this.bar = bar;
	}
	@Override
	public String toString() {
		return "Logement [tp=" + tp + ", restoinc=" + restoinc + ", restocarte=" + restocarte + ", boite=" + boite
				+ ", cafe=" + cafe + ", bar=" + bar + "]";
	}
	
	
}
