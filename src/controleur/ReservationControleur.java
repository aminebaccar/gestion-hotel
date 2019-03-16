package controleur;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import accesBD.ChambreDAO;
import accesBD.ClientDAO;
import accesBD.EtablissementDAO;
import accesBD.LogementDAO;
import accesBD.ReservationDAO;
import accesBD.TypeLogementDAO;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import metier.Client;
import metier.Etablissement;
import metier.Logement;
import metier.ReservationCou;
import metier.ReservationInd;
import metier.TypeLogement;

public class ReservationControleur {

	private ClientDAO bd = new ClientDAO();
	private ReservationDAO bd0 = new ReservationDAO();
	private ChambreDAO bd1 = new ChambreDAO();
	private LogementDAO bd2 = new LogementDAO();
	private EtablissementDAO bd3 = new EtablissementDAO();
	
    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField nationalite;

    @FXML
    private DatePicker datedebut;

    @FXML
    private TextField numtel;

    @FXML
    private DatePicker datefin;
	
    @FXML
    private RadioButton couple;
    
    @FXML
    private RadioButton litss;

    @FXML
    private RadioButton douche;

    @FXML
    private RadioButton bain;

    @FXML
    private TextField nbrlits;

    @FXML
    private RadioButton standard;

    @FXML
    private RadioButton inclusive;

    @FXML
    private RadioButton vip;

    @FXML
    private Button valider;

    @FXML
    private Button anuuler;
    private ToggleGroup group = new ToggleGroup(); 
    private ToggleGroup group1 = new ToggleGroup();
    private ToggleGroup group2 = new ToggleGroup();
    
    @FXML
	private void isClicked(ActionEvent event){
	   
    }
    
    public void initialize() {
    	
    	datedebut.setValue(LocalDate.now());
    	datefin.setValue(LocalDate.now().plus(1, ChronoUnit.DAYS));
    	couple.setToggleGroup(group);
    	couple.setSelected(true);
    	litss.setToggleGroup(group);
    	douche.setToggleGroup(group1);
    	bain.setToggleGroup(group1);
    	douche.setSelected(true);
    	nbrlits.disableProperty().bind(couple.selectedProperty());
    	standard.setToggleGroup(group2);
    	inclusive.setToggleGroup(group2);
    	vip.setToggleGroup(group2);
    	standard.setSelected(true);
    }
    
    
    @FXML
    void confirmer(ActionEvent event) throws SQLException {
    	
    	LocalDate db = datedebut.getValue();
    	Calendar c =  Calendar.getInstance();
    	c.set(db.getYear(), db.getMonthValue(), db.getDayOfMonth());
    	c.add(Calendar.MONTH, -1);
    	Date date = c.getTime();
    	
    	LocalDate df = datefin.getValue();
    	Calendar c1 =  Calendar.getInstance();
    	c1.set(df.getYear(), df.getMonthValue(), df.getDayOfMonth());
    	c1.add(Calendar.MONTH, -1);
    	Date date1 = c1.getTime();
    	
    	int b;
    	if(douche.isSelected())
    		b=0;
    	else
    		b=1;
    	
    	
    	if (nom.getText().isEmpty()||prenom.getText().isEmpty()||nationalite.getText().isEmpty()||numtel.getText().isEmpty())
    	{
    		Alert alert1 = new Alert(AlertType.INFORMATION);
    		alert1.setTitle("Erreur Champs Vides");
    		alert1.setHeaderText("Remplissez tous les champs!");
    		alert1.showAndWait().ifPresent(rs -> {
    		    if (rs == ButtonType.OK) {
    		        System.out.println("Pressed OK.");
    		    }
    		});
    	}
    	
    	else if(datedebut.getValue().isBefore(LocalDate.now()) || c.after(c1)|| c.equals(c1) )
    	{
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Erreur Date");
    		alert.setHeaderText("Choisissez une date valide!");
    		alert.showAndWait().ifPresent(rs -> {
    		    if (rs == ButtonType.OK) {
    		        System.out.println("Pressed OK.");
    		    }
    		});
    	}
    	else {
    	if(bd.find(nom.getText(), prenom.getText())==null)
    	{
    	Client clt = new Client(nom.getText(), prenom.getText(), nationalite.getText(), numtel.getText());
    	bd.add(clt);
    	System.out.println(bd.find(clt.getNom(), clt.getPrénom()));
    	System.out.println(bd.findNum(clt.getNom(), clt.getPrénom()));
    	}
    	TypeLogement tl= null;
    	if (standard.isSelected())
    		tl= new TypeLogement(1, "Standard", 68.5);
    	else if (inclusive.isSelected())
    		tl= new TypeLogement(2, "All Inclusive", 165.33);
    	else if (vip.isSelected())
    		tl= new TypeLogement(3, "VIP", 201.25) ;
    	
    	if(couple.isSelected()){
    		System.out.println(bd1.findFirstCouple(b));
    		if(bd1.findFirstCouple(b)==null){
    			Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Erreur Chambre");
        		alert.setHeaderText("Il n'y a plus de chambres couple vides!");
        		alert.showAndWait().ifPresent(rs -> {
        		    if (rs == ButtonType.OK) {
        		        System.out.println("Pressed OK.");
        		    }
        		});
    		}
		ReservationCou rc = new ReservationCou(bd.find(nom.getText(), prenom.getText()),date, date1,tl,bd1.findFirstCouple(b));
		System.out.println(rc);
		//if(bd0.findCou(rc).getDatefin().after(date1)))
		if(bd0.addCou(bd.findNum(nom.getText(), prenom.getText()), rc)==false){
			Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Erreur Reservation");
    		alert.setHeaderText("Il existe une reservation courante!");
    		alert.showAndWait().ifPresent(rs -> {
    		    if (rs == ButtonType.OK) {
    		        System.out.println("Pressed OK.");
    		    }
    		});
		}
		else
		{
			long dif = (date1.getTime() -date.getTime());
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Reservation ajouté");
			alert.setHeaderText("Reservation ajouté avec succès!");
			alert.setContentText("Arrête la somme de logement à " + TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS )*tl.getPrixparnuit()*1.5+" Dinars Tunisiens");
			alert.showAndWait().ifPresent(rs -> {
			    if (rs == ButtonType.OK) {
			        System.out.println("Pressed OK.");
			    }
			});
			Alert alerts = new Alert(AlertType.CONFIRMATION);
			alerts.setTitle("Impression résérvation");
			alerts.setHeaderText("Voulez vous imprimer les données de résérvation?");

			Optional<ButtonType> result = alerts.showAndWait();
			if (result.get() == ButtonType.OK){
				Document doc = new Document();
			      try {
					PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\AMINE\\desktop\\test.pdf"));
					doc.open();
					
					Image img = Image.getInstance("C:\\Users\\AMINE\\workspace_tp_java\\GestionHotel\\src\\resources\\immp.png");
					img.setAlignment(Image.ALIGN_CENTER);
					doc.add(img);
					
					PdfPTable table = new PdfPTable(6);
					table.setWidthPercentage(100);
					
					PdfPCell cell;
					cell= new PdfPCell(new Phrase("Nom Client", FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell= new PdfPCell(new Phrase("Prenom Client", FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell= new PdfPCell(new Phrase("Date de début logement", FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell= new PdfPCell(new Phrase("Date de fin logement", FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell= new PdfPCell(new Phrase("Type de logement", FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell= new PdfPCell(new Phrase("Numéro de chambre", FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell= new PdfPCell(new Phrase(rc.getClient().getNom(), FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.WHITE);
					table.addCell(cell);
					
					cell= new PdfPCell(new Phrase(rc.getClient().getPrénom(), FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.WHITE);
					table.addCell(cell);
					
					cell= new PdfPCell(new Phrase(rc.getDatedeb().toLocaleString(), FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.WHITE);
					table.addCell(cell);
					
					cell= new PdfPCell(new Phrase(rc.getDatefin().toLocaleString(), FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.WHITE);
					table.addCell(cell);
					
					cell= new PdfPCell(new Phrase(rc.getTypel().getLibelle(), FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.WHITE);
					table.addCell(cell);
					String kk="";
					cell= new PdfPCell(new Phrase(kk+= rc.getCouple().getNum(), FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.WHITE);
					table.addCell(cell);
					
					doc.add(new Paragraph(" "));
					doc.add(new Paragraph(" "));
					doc.add(new Paragraph(" "));
					doc.add(table);
					doc.add(new Paragraph(" "));
					doc.add(new Paragraph(" "));
					doc.add(new Paragraph(" "));
					doc.add(new Paragraph("Vous avez accès a: "));
					List<Logement> qp = bd2.findAll();
					Logement l = qp.get(rc.getTypel().getId()-1);
					Collection<String> pq = null;
					@SuppressWarnings("rawtypes")
					Iterator it;
					if(l.isRestoinc()==true){
						pq = bd3.find("restoinc");
						it = pq.iterator();
						while (it.hasNext())
						doc.add(new Paragraph(it.next().toString()));}
					if(l.isRestocarte()==true){
						pq = bd3.find("restocarte");
						it = pq.iterator();
						while (it.hasNext())
						doc.add(new Paragraph(it.next().toString()));}
					if(l.isBoite()==true){
						pq = bd3.find("boite");
						it = pq.iterator();
						while (it.hasNext())
						doc.add(new Paragraph(it.next().toString()));
					}
					if(l.isBar()==true){
						pq = bd3.find("bar");
						it = pq.iterator();
						while (it.hasNext())
						doc.add(new Paragraph(it.next().toString()));
					}
					if(l.isCafe()==true){
						pq = bd3.find("cafe");
						it = pq.iterator();
						while (it.hasNext())
						doc.add(new Paragraph(it.next().toString()));
					}
					
					doc.close();
					Desktop.getDesktop().open(new File ("C:\\Users\\AMINE\\desktop\\test.pdf"));
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      
			} else {
			    // ... user chose CANCEL or closed the dialog
			}
			
		}
    	}
    	
    	else if (litss.isSelected()){
    		if(nbrlits.getText().isEmpty())
    		{
    			Alert alert1 = new Alert(AlertType.INFORMATION);
        		alert1.setTitle("Erreur Nombre de Lits");
        		alert1.setHeaderText("Specifiez le nombre de lits!");
        		alert1.showAndWait().ifPresent(rs -> {
        		    if (rs == ButtonType.OK) {
        		        System.out.println("Pressed OK.");
        		    }
        		});
    		}
    		else if (nbrlits.getText().matches("[a-zA-Z]+"))
    		{
    			Alert alert1 = new Alert(AlertType.INFORMATION);
        		alert1.setTitle("Erreur Nombre de Lits");
        		alert1.setHeaderText("Saisissez un numèro!");
        		alert1.showAndWait().ifPresent(rs -> {
        		    if (rs == ButtonType.OK) {
        		        System.out.println("Pressed OK.");
        		    }
        		
        		});
    		}
    		else if (Integer.parseInt(nbrlits.getText())>4 || Integer.parseInt(nbrlits.getText())<0){
    			Alert alert1 = new Alert(AlertType.INFORMATION);
        		alert1.setTitle("Erreur Nombre de Lits");
        		alert1.setHeaderText("Le nombre de lits doit être entre 1 et 4!");
        		alert1.showAndWait().ifPresent(rs -> {
        		    if (rs == ButtonType.OK) {
        		        System.out.println("Pressed OK.");
        		    }
        		});
    		}
    		else
    		{
    			ReservationInd ri = new ReservationInd(bd.find(nom.getText(), prenom.getText()),date, date1,tl,bd1.findFirstInd(b));
    		System.out.println(ri);
        	
    		if(bd0.addInd(bd.findNum(nom.getText(), prenom.getText()), ri)==false){
    			Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Erreur Reservation");
        		alert.setHeaderText("Il existe une reservation courante!");
        		alert.showAndWait().ifPresent(rs -> {
        		    if (rs == ButtonType.OK) {
        		        System.out.println("Pressed OK.");
        		    }
        		});
    		}	
    		
    		else
    		{long dif = (date1.getTime() -date.getTime());
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Reservation ajouté"); 
    			alert.setHeaderText("Reservation ajouté avec succès!");
    			alert.setContentText("Arrête la somme de logement à " + TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS )*tl.getPrixparnuit()*Integer.parseInt(nbrlits.getText())+" Dinars Tunisiens, le numéro de chambre est");
    			alert.showAndWait().ifPresent(rs -> {
    			    if (rs == ButtonType.OK) {
    			        System.out.println("Pressed OK.");
    			    }
    			});
    			Alert alerts = new Alert(AlertType.CONFIRMATION);
    			alerts.setTitle("Impression résérvation");
    			alerts.setHeaderText("Voulez vous imprimer les données de résérvation?");

    			Optional<ButtonType> result = alerts.showAndWait();
    			if (result.get() == ButtonType.OK){
    				Document doc = new Document();
    			      try {
    					PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\AMINE\\desktop\\test.pdf"));
    					doc.open();
    					
    					Image img = Image.getInstance("C:\\Users\\AMINE\\workspace_tp_java\\GestionHotel\\src\\resources\\immp.png");
    					img.setAlignment(Image.ALIGN_CENTER);
    					doc.add(img);
    					
    					PdfPTable table = new PdfPTable(6);
    					table.setWidthPercentage(100);
    					
    					PdfPCell cell;
    					cell= new PdfPCell(new Phrase("Nom Client", FontFactory.getFont("Comic Sans MS",11)));
    					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    					cell.setBackgroundColor(BaseColor.GRAY);
    					table.addCell(cell);
    					
    					cell= new PdfPCell(new Phrase("Prenom Client", FontFactory.getFont("Comic Sans MS",11)));
    					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    					cell.setBackgroundColor(BaseColor.GRAY);
    					table.addCell(cell);
    					
    					cell= new PdfPCell(new Phrase("Date de début logement", FontFactory.getFont("Comic Sans MS",11)));
    					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    					cell.setBackgroundColor(BaseColor.GRAY);
    					table.addCell(cell);
    					
    					cell= new PdfPCell(new Phrase("Date de fin logement", FontFactory.getFont("Comic Sans MS",11)));
    					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    					cell.setBackgroundColor(BaseColor.GRAY);
    					table.addCell(cell);
    					
    					cell= new PdfPCell(new Phrase("Type de logement", FontFactory.getFont("Comic Sans MS",11)));
    					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    					cell.setBackgroundColor(BaseColor.GRAY);
    					table.addCell(cell);
    					
    					cell= new PdfPCell(new Phrase("Numéro de chambre", FontFactory.getFont("Comic Sans MS",11)));
    					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    					cell.setBackgroundColor(BaseColor.GRAY);
    					table.addCell(cell);
    					
    					cell= new PdfPCell(new Phrase(ri.getClient().getNom(), FontFactory.getFont("Comic Sans MS",11)));
    					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    					cell.setBackgroundColor(BaseColor.WHITE);
    					table.addCell(cell);
    					
    					cell= new PdfPCell(new Phrase(ri.getClient().getPrénom(), FontFactory.getFont("Comic Sans MS",11)));
    					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    					cell.setBackgroundColor(BaseColor.WHITE);
    					table.addCell(cell);
    					
    					cell= new PdfPCell(new Phrase(ri.getDatedeb().toLocaleString(), FontFactory.getFont("Comic Sans MS",11)));
    					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    					cell.setBackgroundColor(BaseColor.WHITE);
    					table.addCell(cell);
    					
    					cell= new PdfPCell(new Phrase(ri.getDatefin().toLocaleString(), FontFactory.getFont("Comic Sans MS",11)));
    					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    					cell.setBackgroundColor(BaseColor.WHITE);
    					table.addCell(cell);
    					
    					cell= new PdfPCell(new Phrase(ri.getTypel().getLibelle(), FontFactory.getFont("Comic Sans MS",11)));
    					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    					cell.setBackgroundColor(BaseColor.WHITE);
    					table.addCell(cell);
    					String kk="";
    					cell= new PdfPCell(new Phrase(kk+= ri.getIndividuel().getNum(), FontFactory.getFont("Comic Sans MS",11)));
    					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    					cell.setBackgroundColor(BaseColor.WHITE);
    					table.addCell(cell);
    					
    					doc.add(new Paragraph(" "));
    					doc.add(new Paragraph(" "));
    					doc.add(new Paragraph(" "));
    					doc.add(table);
    					doc.add(new Paragraph(" "));
    					doc.add(new Paragraph(" "));
    					doc.add(new Paragraph(" "));
    					doc.add(new Paragraph("Vous avez accès a: "));
    					List<Logement> qp = bd2.findAll();
    					Logement l = qp.get(ri.getTypel().getId()-1);
    					Collection<String> pq = null;
    					@SuppressWarnings("rawtypes")
    					Iterator it;
    					if(l.isRestoinc()==true){
    						pq = bd3.find("restoinc");
    						it = pq.iterator();
    						while (it.hasNext())
    						doc.add(new Paragraph(it.next().toString()));}
    					if(l.isRestocarte()==true){
    						pq = bd3.find("restocarte");
    						it = pq.iterator();
    						while (it.hasNext())
    						doc.add(new Paragraph(it.next().toString()));}
    					if(l.isBoite()==true){
    						pq = bd3.find("boite");
    						it = pq.iterator();
    						while (it.hasNext())
    						doc.add(new Paragraph(it.next().toString()));
    					}
    					if(l.isBar()==true){
    						pq = bd3.find("bar");
    						it = pq.iterator();
    						while (it.hasNext())
    						doc.add(new Paragraph(it.next().toString()));
    					}
    					if(l.isCafe()==true){
    						pq = bd3.find("cafe");
    						it = pq.iterator();
    						while (it.hasNext())
    						doc.add(new Paragraph(it.next().toString()));
    					}
    					
    					doc.close();
    					Desktop.getDesktop().open(new File ("C:\\Users\\AMINE\\desktop\\test.pdf"));
    					
    				} catch (FileNotFoundException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (DocumentException e) { 
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (MalformedURLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			      
    			} else {
    			    // ... user chose CANCEL or closed the dialog
    			}
    		  }
    		}
    		}
    	}
    	
    	}
    	

    
    
    @FXML
    void remiseazero(ActionEvent event) {
    	nom.setText("");
    	prenom.setText("");
    	nationalite.setText("");
    	numtel.setText("");
    	datefin.setValue(LocalDate.now().plus(1, ChronoUnit.DAYS));
    	couple.setSelected(true);
    	nbrlits.setText("");
    	standard.setSelected(true);
    }
    

}
