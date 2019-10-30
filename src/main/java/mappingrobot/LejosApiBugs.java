package mappingrobot;

import lejos.robotics.chassis.WheeledChassis.Modeler;
import lejos.robotics.RegulatedMotor;

/**
 * Methods that for some reason are missing from the current version of Lejos API.
 * @author Lejos API
 * @version 1.0.0
 */
public abstract class LejosApiBugs {
  /**
   * Provides a modeler object to model a Holonomic motorized wheel on
   * the chassis.
   * @param motor The regulated motor that drives the wheel
   * @param diameter The diameter of the wheel in a unit of choice.
   * @return the modeler.
   */
  public static Modeler modelWheel(RegulatedMotor motor, double diameter) { return new Modeler(motor, diameter); }
}
