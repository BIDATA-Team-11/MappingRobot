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

package team11.mappingrobot;

import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.Port;

class Ultrasonic implements DistanceMeasure {
  private SampleProvider distance;
  private float[] sample;

  /**
   * @param port The physical port where the sensor is connected.
   */
  Ultrasonic(Port port) {
    this.distance = new NXTUltrasonicSensor(port).getDistanceMode();
    this.sample[this.distance.sampleSize()] = 0;
  }

  /** {@inheritDoc} */
  @Override public boolean objectInRange() {
    this.distance.fetchSample(this.sample, 0);
    return this.sample[0] != Float.POSITIVE_INFINITY;
  }

  /** {@inheritDoc} */
  @Override public float getDistance() {
    return this.sample[0];
  }
}
