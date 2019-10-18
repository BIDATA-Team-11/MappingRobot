package team11.mappingrobot;

import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.chassis.WheeledChassis.Modeler;
import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.RegulatedMotor;

class Robot {
  private Wheel wheel1;
  private Wheel wheel2;
  private Chassis chassis;
  MovePilot pilot;

  Robot(int wheelOffset) {
    this.wheel1 = modelWheel(Motor.A, Dimensions.wheelSize).offset(-wheelOffset);
    this.wheel2 = modelWheel(Motor.B, Dimensions.wheelSize).offset(wheelOffset);
    this.chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
    this.pilot = new MovePilot(chassis);
  }



  /*
   * Metoden nedenfor er hentet inn her på grunn av en bug i API.
   */

  /** Provides a modeler object to model a Holonomic motorized wheel on the chassis
   * @param motor
   * The regulated motor that drives the wheel
   * @param diameter
   * The diameter of the wheel in a unit of choice.
   * @return the modeler
   */
  Modeler modelWheel(RegulatedMotor motor, double diameter) {
    return new Modeler(motor, diameter);
  }
}
