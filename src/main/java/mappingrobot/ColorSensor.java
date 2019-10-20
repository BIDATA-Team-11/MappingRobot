/**
 * Helper class for EV3ColorSensor.
 * @author Stian Selvåg
 * @author Herman Aagaard
 * @author Henrik Hafsø
 * @author Joakim Skogø Langvand
 * @author Erling Sletta
 * @author Torbjørn Øverås
 * @author Gruppe 11, dataingeniør NTNU, første semester.
 * @version 2.0.0
 */

package team11.mappingrobot;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.Port;
import lejos.robotics.Color;
import lejos.hardware.Button;

public class ColorSensor {
    private EV3ColorSensor sensor;

    /**
     * Konstuerer en ny EV3ColorSensor fra LejOS API.
     * @param port Fysisk port på EV3-maskinen.
     */
    public ColorSensor(Port port) {
        sensor = new EV3ColorSensor(port);
    }

    /**
     * Printer fargeverdi fra sensor.
     * @deprecated Erstattes med {@link #getColorIDString}.
     * @see #getColorIDString
     */
     public void printFargeID() {
         SampleProvider colorSample = this.sensor.getColorIDMode();
         float[] sample = new float[colorSample.sampleSize()];
         colorSample.fetchSample(sample, 0);
         int colorId = (int)sample[0];
         String colorName = "";
         switch(colorId){
             case Color.NONE: colorName = "NONE"; break;
             case Color.BLACK: colorName = "BLACK"; break;
             case Color.BLUE: colorName = "BLUE"; break;
             case Color.GREEN: colorName = "GREEN"; break;
             case Color.YELLOW: colorName = "YELLOW"; break;
             case Color.RED: colorName = "RED"; break;
             case Color.WHITE: colorName = "WHITE"; break;
             case Color.BROWN: colorName = "BROWN"; break;
         }
         System.out.println(colorId + " - " + colorName);
     }

     /**
      * Henter fargeverdi fra sensor.
      * @return Fargeverdi som tekststreng. Nyttig i testing og debugging.
      */
     public String getColorIDString() {
       SampleProvider colorSample = this.sensor.getColorIDMode();
       float[] sample = new float[colorSample.sampleSize()];
       colorSample.fetchSample(sample, 0);
       int colorId = (int)sample[0];
       String colorName = "";
       switch(colorId){
           case Color.NONE: colorName = "NONE"; break;
           case Color.BLACK: colorName = "BLACK"; break;
           case Color.BLUE: colorName = "BLUE"; break;
           case Color.GREEN: colorName = "GREEN"; break;
           case Color.YELLOW: colorName = "YELLOW"; break;
           case Color.RED: colorName = "RED"; break;
           case Color.WHITE: colorName = "WHITE"; break;
           case Color.BROWN: colorName = "BROWN"; break;
       }
       return colorName;
     }

     /**
      * Metode for å se om sensoren ser svart.
      * @return True hvis sensoren ser svart, false ellers.
      */
     public boolean hasLine() {
       SampleProvider colorSample = this.sensor.getColorIDMode();
       float[] sample = new float[colorSample.sampleSize()];
       colorSample.fetchSample(sample, 0);
       return (int)sample[0] == Color.BLACK ? true : false;
     }

     /**
      * Invers av hasLine(). Kun for å gjøre logikk for styring mer lesbar.
      * @return True hvis sensoren ikke ser svart, false ellers.
      */
     public boolean lostLine() {
       return !this.hasLine();
     }

     /**
      * Metode som forteller om sensoren ser noe annet enn svart eller hvitt.
      * @return boolean Positiv dersom sensoren ser noe annet enn svart eller hvitt.
      */
     public boolean notBlackNorWhite() {
       SampleProvider colorSample = this.sensor.getColorIDMode();
       float[] sample = new float[colorSample.sampleSize()];
       colorSample.fetchSample(sample, 0);
       int color = (int)sample[0];
       boolean notBlack = color == Color.BLACK ? false : true;
       boolean notWhite = color != Color.WHITE ? true : false;
       return notWhite && notBlack ? true : false;
     }
}
