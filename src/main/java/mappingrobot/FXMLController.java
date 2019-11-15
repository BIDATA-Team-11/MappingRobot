package mappingrobot;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;

import lejos.remote.ev3.RMIRemoteKey;
import lejos.remote.ev3.RemoteEV3;
import java.rmi.RemoteException;
import java.lang.InterruptedException;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class FXMLController implements Initializable {
    
    @FXML
    private LineChart linechart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // label.setText("Hello, JavaFX");
    }    

    // @FXML
    // private void add() {
    // }



}
