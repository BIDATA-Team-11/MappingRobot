/**
 * LejOS Klient for Legorobotprosjekt 2019
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

import lejos.hardware.Button;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Stopwatch;

public class App {

  /**
   * Main-metode for klienten. Her får man valget om å printe ut de fargene sensorene ser, kalibrere fargesensorene eller å få roboten til å kjøre.
   * @param args Argumenter blir ignorert. Heh.
   * @throws Exception Gir EV3 mulighet til å catche eventuelle feil.
   */
  public static void main (String[] args) throws Exception {

    System.out.println("2.0.0-awesomebot");
    System.out.println("");
    System.out.println("Enter:  Start");

    int key;
    RobotDescription robotDescription = new RobotDescription();
    Robot robot = new LineFollowingRobot(
      robotDescription.wheelOffset, RobotDescription.wheelSize,
      "S1", "S4");

    do {
      key = Button.waitForAnyPress();

      if (key == Button.ID_RIGHT) {
      } else if (key == Button.ID_LEFT) {
        start(robot);
      }
    } while (true);
    /*
    * TODO: Bruker while-løkke her så det kan gjøres mulig å legge inn en escape i hovedløkka,
    *       sånn at det kan bli mulig å stoppe og starte roboten uten å drepe programmet.
    */
  }

  /**
   * Starter selve legoroboten.
   * @param mainSensor Hovedfargesensor. Står midt på fronten på roboten..
   * @param correctionSensor Korrigeringssensor. Står til høyre for hovedfargesensor.
   * @param robot Hjelpeklasse for motorene.
   * @see ColorSensor
   * @see Robot
   */
  public static void start(Robot robot) {

    /*
     * "Hovedløkka" i programmet. Denne kjører til vi dreper den.
     *
     * Her ligger logikken som styrer retning - fram, sving til venstre, sving til høyre.
     */
    while (true) {
      if (robot.updateDirection()) {
        robot.update();
      }
    }
  }
}
