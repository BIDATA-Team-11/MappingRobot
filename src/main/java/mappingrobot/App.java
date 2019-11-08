package mappingrobot;

import lejos.remote.ev3.RMIRemoteKey;
import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RMIRegulatedMotor;
import java.io.IOException;
import java.rmi.RemoteException;
import java.lang.InterruptedException;
import java.io.IOException;

/**
 * LejOS Klient for Legorobotprosjekt 2019
 *
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
  static RemoteRobot bot = null;

  /**
   * Main-metode for klienten. Her får man valget om å printe ut de fargene
   * sensorene ser, kalibrere fargesensorene eller å få roboten til å kjøre.
   *
   * @param args Argumenter blir ignorert. Heh.
   */
  public static void main(String[] args) {
    System.out.println("1.0.0-remotebot");

    Thread current = new Thread();

    try {
      // bot = new RemoteRobot();
      // final RemoteEV3 ev3 = bot.getEV3();
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
              } catch (RemoteException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Here's the stacktrace. You figure it out.\n");
                e.printStackTrace();
              } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Here's the stacktrace. You figure it out.\n");
                e.printStackTrace();
              }
            }
          });

          current.start();
          break;

        case RMIRemoteKey.DOWN:
          System.out.println("Down");
          current.interrupt();
          break;
        }
      } while (true);
    } catch (RemoteException e) {
      System.out.println("Error: " + e.getMessage());
      System.out.println("Here's the stacktrace. You figure it out.\n");
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      System.out.println("Here's the stacktrace. You figure it out.\n");
      e.printStackTrace();
    }
  }

  /**
   * Starter selve legoroboten.
   *
   * @param mainSensor       Hovedfargesensor. Står midt på fronten på roboten..
   * @param correctionSensor Korrigeringssensor. Står til høyre for
   *                         hovedfargesensor.
   * @param robot            Hjelpeklasse for motorene.
   * @see ColorSensor
   * @see Robot
   */
    publi sta


    Thread kjør = new Thread(new Runnable() {
      public void run() {
        try(Motor motor = new Motor(ev3)) {
          while(true) {
            if (Thread.interrupted()) {
              motor.close();


              motor.forward();
            }
          }
        }
        catch (RemoteException e) { }
      }
    });

    Thread radar = new Thread(new Runnable() {
      public void run() {
        try(Ultrasonic sonic = new Ultrasonic(ev3, "S1")) {
          float distance;
          while(true) {
            if (Thread.interrupted()) {
              sonic.close();
            } else {
              distance = sonic.getDistance();
              System.out.println(distance);
              Thread.sleep(1000);
            }
          }
        } catch(RemoteException e) {
        } catch (InterruptedException e) {
        } catch (IOException e) {
        } catch (Exception e) {
        }
      }
    });

    try {
      radar.start();
      kjør.start();
      radar.join();
      kjør.join();
    } catch (InterruptedException e) {
      kjør.interrupt();
      radar.interrupt();
    }

    // Ultrasonic sonic = new Ultrasonic(ev3, "S1");
    // float distance = sonic.getDistance();
    // System.out.println(distance);

    // try(Motor motor = new Motor(ev3)) {
    //   motor.forward();
    // }

    // try(SimpleMotor motor = new SimpleMotor(ev3)) {
    //   motor.left();
    //   Thread.sleep(2000);
    //   motor.close();
    //   motor.right();
    //   Thread.sleep(2000);
    //   motor.stop();
    //   motor.rotate(90);
    //   motor.close();
    //   motor.rotate(-90);
    //   motor.close();
    // }

//     Motor motor = new Motor(ev3);
//     motor.forward();
    // Motor motor = new Motor(ev3);

    // motor.forward();
    // motor.backward();
    // motor.right();
    // motor.left();
    // Thread.sleep(1000);
    // motor.stop();
  }
}
