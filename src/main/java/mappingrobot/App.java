package mappingrobot;

//import lejos.hardware.Button;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Stopwatch;

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

    /*do {
      switch (Button.waitForAnyPress()) {
        case Button.ID_RIGHT:
          break;
        case Button.ID_LEFT:
          start();
          break;
        default:
          break;
      }
    } while (true); */
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
  public static void start() {
    Robot robot = new LineFollowingRobot(RobotDescription.wheelOffset, RobotDescription.wheelSize, "S1", "S4");

    robot.updateDirection();
    robot.update();

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
