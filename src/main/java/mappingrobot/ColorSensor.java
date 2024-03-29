package mappingrobot;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.Port;

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
public class ColorSensor {
  private EV3ColorSensor sensor;

  /**
   * Constructs a new color sensor connected to specific brick.
   * @param port Port object representing physical port on EV3.
   */
  public ColorSensor(Port port) {
    sensor = new EV3ColorSensor(port);
  }

  /**
   * Get RGB color as a float array. TODO: Chect sanity of #fetchSample.
   * @return float[] containing RGB sample.
   */
  public float[] getColorID() {
    SampleProvider colorSample = this.sensor.getColorIDMode();
    float[] sample = new float[colorSample.sampleSize()];
    colorSample.fetchSample(sample, 0);
    return sample;
  }

  /**
   * Get color description as text. Useful for debugging.
   * @return String description of color.
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
}
