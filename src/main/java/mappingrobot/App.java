package mappingrobot;

import lejos.remote.ev3.RMIRemoteKeys;
import lejos.remote.ev3.RMIRemoteKey;
import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RMIRemoteRegulatedMotor;
import java.rmi.Remote;
import java.rmi.RemoteException;


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

    Thread current = new Thread();

    try {
      final RemoteEV3 ev3 = new RemoteEV3("10.0.1.1");
      ev3.setDefault();

      do {
        switch (ev3.getKeys().waitForAnyPress()) {
          case RMIRemoteKey.RIGHT:
            System.out.println("Right");
            break;

          case RMIRemoteKey.LEFT:
            System.out.println("Left");

            current = new Thread(new Runnable() {
              public void run() {
                try {
                  start(ev3);
                } catch (Exception  e) {
                  System.out.println("Feil: "+e);
                }
              }});

            current.start();
            break;

          case RMIRemoteKey.DOWN:
            System.out.println("Down");
            current.interrupt();
            break;
        }
      } while (true);
    } catch (RemoteException e) {
      System.out.println(e);
      throw e;
    }
  }

  /**
   * Starter selve legoroboten.
   * @param mainSensor Hovedfargesensor. Står midt på fronten på roboten..
   * @param correctionSensor Korrigeringssensor. Står til høyre for hovedfargesensor.
   * @param robot Hjelpeklasse for motorene.
   * @see ColorSensor
   * @see Robot
   */
  public static void start(RemoteEV3 ev3) throws Exception {
    if (motor == null) { motor = ev3.createRegulatedMotor("A", 'L'); }

    System.out.println("Trying motor");
    System.out.println(motor.getMaxSpeed());

    motor.forward();
    Thread.sleep(1000);
    motor.stop(true);
  }
}
