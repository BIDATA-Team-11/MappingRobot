package mappingrobot;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.SampleProvider;

/**
 * NXTUltrasonicSensor implementation of the DistanceMeasure interface.
 * @author Stian Selvåg
 * @author Herman Aagaard
 * @author Henrik Hafsø
 * @author Joakim Skogø Langvand
 * @author Erling Sletta
 * @author Torbjørn Øverås
 * @author Gruppe 11, dataingeniør NTNU, første semester.
 * @version 1.0.0
 */
class Ultrasonic implements DistanceMeasure {
  private SampleProvider distance;
  private float[] sample;

  /**
   * Construct new ultrasonic object using provided EV3 brick.
   * @param port Physical port where the sensor is connected.
   */
  Ultrasonic(String port) {
    Brick brick = BrickFinder.getDefault();
    Port physicalPort = brick.getPort(port);
    this.distance = new NXTUltrasonicSensor(physicalPort).getDistanceMode();
    this.sample[this.distance.sampleSize()] = 0;
  }

  /**
   * Construct new ultrasonic object using default EV3 brick.
   * @param port Physical port where the sensor is connected.
   */
  Ultrasonic(String port, Brick brick) {
    Port physicalPort = brick.getPort(port);
    this.distance = new NXTUltrasonicSensor(physicalPort).getDistanceMode();
    this.sample[this.distance.sampleSize()] = 0;
  }

  /** {@inheritDoc} */
  @Override 
  public boolean objectInRange() {
    this.distance.fetchSample(this.sample, 0);
    return this.sample[0] != Float.POSITIVE_INFINITY;
  }

  /** {@inheritDoc} */
  @Override 
  public float getDistance() { return this.sample[0]; }
}
