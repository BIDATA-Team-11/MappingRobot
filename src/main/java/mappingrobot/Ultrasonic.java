package team11.mappingrobot;

import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.Port;

class Ultrasonic {
  SampleProvider distance;
  float[] sample;

  Ultrasonic(Port port) {
    this.distance = new NXTUltrasonicSensor(port).getDistanceMode();
    this.sample[this.distance.sampleSize()] = 0;
  }

  public boolean canSeeObject() {
    this.distance.fetchSample(this.sample, 0);
    return this.sample[0] == Float.POSITIVE_INFINITY ? false : true;
  }

  public float getDistance() {
    return this.sample[0];
  }
}
