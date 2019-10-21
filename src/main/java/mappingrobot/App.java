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

import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.port.Port;
import lejos.utility.Stopwatch;

import lejos.hardware.Button;

public class App {

  /**
  * Main-metode for klienten. Her får man valget om å printe ut de fargene sensorene ser, kalibrere fargesensorene eller å få roboten til å kjøre.
  * @param args Argumenter blir ignorert. Heh.
  * @throws Exception Gir EV3 mulighet til å catche eventuelle feil.
  */
  public static void main (String[] args) throws Exception {
    Brick brick = BrickFinder.getDefault();

    /*
    * Oppsett av fargesensorer.
    */
    Port mainSensorPort = brick.getPort("S2");
    Port correctionSensorPort = brick.getPort("S4");
    ColorSensor mainSensor = new ColorSensor(mainSensorPort);
    ColorSensor correctionSensor = new ColorSensor(correctionSensorPort);

    DistanceMeasure distanceSensor = new Ultrasonic(brick.getPort("S3"));

    System.out.println("2.0.0-awesomebot");
    System.out.println("");
    System.out.println("Enter:  Start");

    RobotDescription robotDescription = new RobotDescription();

    Robot robot = new Robot(robotDescription.wheelOffset, Dimensions.wheelSize);

    do {
      int knapp = Button.waitForAnyPress();

      if (knapp == Button.ID_RIGHT) {
      } else if (knapp == Button.ID_LEFT) {
        start(mainSensor, correctionSensor, robot);
      } else if (knapp == Button.ID_DOWN) {
        mainSensor.printFargeID();
        correctionSensor.printFargeID();
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
  public static void start(ColorSensor mainSensor, ColorSensor correctionSensor, Robot robot) {

    /*
    * Flagg som indikerer at linja befinner seg mellom sensorene. Dette løser en del problemer
    * med hunting ved at vi unngår å svinge tilbake mot venstre så snart korrigeringssensoren
    * mister linja. Flagget settes med en gang korrigeringssensoren ser linja, og fjernes så
    * snart hovedsensoren finner linja igjen.
    */
    boolean lineIsBetweenSensors = false;

    int button;

    /*
    * "Hovedløkka" i programmet. Denne kjører til vi dreper den.
    *
    * Her ligger logikken som styrer retning - fram, sving til venstre, sving til høyre.
    */
    while (true) {
      if (mainSensor.lostLine()) {
        if (robot.getCurrentDirectionState() == Robot.Direction.FORWARD || correctionSensor.hasLine()) {
          if (correctionSensor.hasLine()) {
            robot.setDirectionState(Robot.Direction.RIGHT);
          } else if (!lineIsBetweenSensors) {
            robot.setDirectionState(Robot.Direction.LEFT);
          }
        }
      }

      if (mainSensor.lostLine() && correctionSensor.hasLine()) {
        robot.setDirectionState(Robot.Direction.RIGHT);
        lineIsBetweenSensors = true;
      }

      if (mainSensor.hasLine()) {
        robot.setDirectionState(Robot.Direction.FORWARD);
        lineIsBetweenSensors = false;
      }

      robot.update();
    }
  }
}
