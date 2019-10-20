/** Metoder som må hentes inn manuelt pga bugs i Lejos API.
 * @author Lejos API
 * @version 1.0.0
 */

 package team11.mappingrobot;

 import lejos.robotics.chassis.WheeledChassis.Modeler;
 import lejos.robotics.RegulatedMotor;

 public class LejosApiBugs {
   /** Provides a modeler object to model a Holonomic motorized wheel on the chassis
    * @param motor
    * The regulated motor that drives the wheel
    * @param diameter
    * The diameter of the wheel in a unit of choice.
    * @return the modeler
    */
   public static Modeler modelWheel(RegulatedMotor motor, double diameter) {
     return new Modeler(motor, diameter);
   }
 }
