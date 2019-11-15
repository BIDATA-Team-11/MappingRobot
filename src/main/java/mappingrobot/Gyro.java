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
public class Gyro implements AutoCloseable {
  private RMISampleProvider sampleProvider;
  private RemoteEV3 ev3;
  private String port;

  /**
   * Constructor that requires and sets the EV3 and the port the sensor is connected to,
   * as well as creating a sample provider for the given port, with a sensor name and mode name.
   * @param ev3 The ev3 that's being input
   * @param port The port the sensor is connected to
   */
  public Gyro(RemoteEV3 ev3, String port) {
    this.port = port;
    this.ev3 = ev3;
    // Port ev3Port = ev3.getPort(port);
    // this.distance = new NXTUltrasonicSensor(port).getDistanceMode();
    // this.sampleProvider = ev3.createSampleProvider(port,
    // "lejos.hardware.sensor.EV3UltrasonicSensor", "Distance");
    // this.sample = sampleProvider.fetchSample();
    this.sampleProvider = ev3.createSampleProvider(this.port, "lejos.hardware.sensor.EV3GyroSensor", "Angle");
  }

  // private void createSonic() {
  //   this.sampleProvider = ev3.createSampleProvider(this.port, "lejos.hardware.sensor.EV3UltrasonicSensor", "Distance");
  // }

  /**
   * Method that fetches and returns a sample from the sensor
   * @return sample angle from sensor
   * @throws RemoteException Throws an exception if an error occurs
   */
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

  /**
   * Method that executes {@link #getSample()} and returns the first element in the array
   * @return The element in the array given by {link Gyro#getSample}
   * @throws RemoteException Throws a RemoteException if an error occurs
   */
  public float getAngle() throws RemoteException { return getSample()[0]; }

  /**
   * Override close method to close the sample provider
   * @throws IOException Throws an IOException if an error occurs
   */
  @Override
  public void close() throws IOException { this.sampleProvider.close(); }
}
