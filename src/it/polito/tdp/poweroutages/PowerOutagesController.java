package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Po;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

 //public class PleaseProvideControllerClassName {
	public class PowerOutagesController {

	Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiseBox;

    @FXML
    private TextField maxAnni;

    @FXML
    private TextField maxOre;

    @FXML
    private Button button;

    @FXML
    private TextArea Risultato;

    @FXML
    void initialize() {
    	assert choiseBox != null : "fx:id=\"choiseBox\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert maxAnni != null : "fx:id=\"maxAnni\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert maxOre != null : "fx:id=\"maxOre\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert Risultato != null : "fx:id=\"Risultato\" was not injected: check your FXML file 'PowerOutages.fxml'.";

    }
    
    @FXML
    void doCalcolaRisultato(ActionEvent event) {
    this.Risultato.clear();
    String Nerc= this.choiseBox.getValue();
    int idNerc=0;
    for(  Nerc n :model.getNercList()) {
    	if (n.getValue().equals(Nerc)) idNerc=n.getId();
	}
    int maxY=0;
    int maxH=0;
    try {
        maxY=Integer.parseInt(this.maxAnni.getText());
        maxH=Integer.parseInt(this.maxOre.getText());
    } catch (NumberFormatException e) {
    	this.Risultato.appendText("Invalid number");
    	return;
    }
    List <Po> risultato= model.calcolaRisultato(idNerc,maxY,maxH);
    for (Po p :risultato) {
    	this.Risultato.appendText(p.toString());
    }
    }
    

   public void setModel (Model model) {
	this.model=model;
	for(  it.polito.tdp.poweroutages.model.Nerc n :model.getNercList()) {
		this.choiseBox.getItems().add(n.getValue());
	}
  }
}




