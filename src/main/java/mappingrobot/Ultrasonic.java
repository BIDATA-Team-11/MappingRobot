package mappingrobot;

import java.io.IOException;
import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RMISampleProvider;
import java.rmi.RemoteException;
import java.lang.InterruptedException;
// import lejos.hardware.port.Port;

/**
 * NXTUltrasonicSensor implementation of the DistanceMeasure interface.
 *
 * @author Stian Selvåg
 * @author Herman Aagaard
 * @author Henrik Hafsø
 * @author Joakim Skogø Langvand
 * @author Erling Sletta
 * @author Torbjørn Øverås
 * @author Gruppe 11, dataingeniør NTNU, første semester.
 * @version 1.0.0
 */
public class Ultrasonic implements AutoCloseable {
  private RMISampleProvider sampleProvider;
  private RemoteEV3 ev3;
  private String port;

  /**
   * Construct new ultrasonic object using default EV3 brick.
   *
   * @param port Physical port where the sensor is connected.
   */
  public Ultrasonic(RemoteEV3 ev3, String port) {
    this.port = port;
    this.ev3 = ev3;
    // Port ev3Port = ev3.getPort(port);
    // this.distance = new NXTUltrasonicSensor(port).getDistanceMode();
    // this.sampleProvider = ev3.createSampleProvider(port,
    // "lejos.hardware.sensor.EV3UltrasonicSensor", "Distance");
    // this.sample = sampleProvider.fetchSample();
    this.sampleProvider = ev3.createSampleProvider(this.port, "lejos.hardware.sensor.EV3UltrasonicSensor", "Distance");
  }

  // private void createSonic() {
  //   this.sampleProvider = ev3.createSampleProvider(this.port, "lejos.hardware.sensor.EV3UltrasonicSensor", "Distance");
  // }

  public float[] getSample() throws RemoteException {
    // createSonic();
    float[] sample = null; 
    try {
      sample = this.sampleProvider.fetchSample();
      Thread.sleep(10);
    } catch (InterruptedException e) {
      this.sampleProvider.close();
    }
    return sample;
  }

  // /** {@inheritDoc} */
  // @Override
  // public boolean objectInRange() {
  // this.distance.fetchSample(this.sample, 0);
  // return this.sample[0] != Float.POSITIVE_INFINITY;
  // }

  public float getDistance() throws RemoteException { return getSample()[0]; }

  @Override
  public void close() throws IOException { this.sampleProvider.close(); }
}
