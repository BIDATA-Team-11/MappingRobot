package mappingrobot;

import lejos.remote.ev3.RMIRemoteKey;
import lejos.remote.ev3.RemoteEV3;
//import lejos.remote.ev3.RMIRegulatedMotor;
import java.rmi.RemoteException;
import java.lang.InterruptedException;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
//import java.util.HashMap;

/**
 * LejOS Client for Legorobotprosjekt 2019
 *
 * @author Stian Selvåg
 * @author Herman Aagaard
 * @author Henrik Hafsø
 * @author Joakim Skogø Langvand
 * @author Erling Sletta
 * @author Torbjørn Øverås
 * @author Group 11, dataingeniør NTNU, første semester.
 * @version 2.0.0
 */
public class App {
  static RemoteRobot bot = null;

  /**
   * Main-method for the client. You get the choice to print out the colors
   * the ColorSensor detects, calibrate the ColorSensor or make the robot drive.
   *
   * @param args Arguments will be ignored.
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
   * @param ColorSensor      Located in the center of the robotcar.
   * @param robot            Assisting class for the motors.
   * @see ColorSensor
   * @see Robot
   */
  public static void start(final RemoteEV3 ev3) throws Exception {
    final LinkedBlockingQueue<Integer> vinkel = new LinkedBlockingQueue<Integer>();
    final LinkedBlockingQueue<Melding> klar = new LinkedBlockingQueue<Melding>();


    Thread kjør = new Thread(new Runnable() {
      float offset_left = 0.30f;
      float offset_right = 0.50f;
      float offset_front = 0.50f;

      boolean hinder = false;

      public void run() {
        try(Motor motor = new Motor(ev3)) {
          while(true) {
            if (Thread.interrupted()) {
              motor.close();
            } else {
              Melding melding = klar.take();

              if (melding.vinkel == 90) {
                if (melding.distance < offset_left) {
                  motor.right();
                } else if ((melding.distance > offset_right) && !hinder) {
                  motor.left();
                } else {
                  motor.forward();
                }
              } else if (melding.vinkel == 0) {
                if (melding.distance < offset_front) {
                  hinder = true;
                  motor.right();
                } else {
                  hinder = false;
                  motor.forward();
                }
              }

              // motor.forward();
            }
          }
        } catch (RemoteException e) { }
        catch (InterruptedException e) {}
      }
    });


    Thread motor = new Thread(new Runnable() {
      public void run() {
        try(SimpleMotor motor = new SimpleMotor(ev3, "B")) {
          boolean done = false;

          while(true) {
            if (Thread.interrupted()) {
              motor.rotate(0);
              motor.close();
            } else {
              if (done) {
                motor.rotate(0);
                vinkel.add(0);
                done = false;
              } else {
                motor.rotate(-90);
                vinkel.add(90);
                done = true;
              }
            }
          }
        } catch(RemoteException e) {
        // } catch (InterruptedException e) {
          // motor.rotate(0);
          // motor.close();
        }
      }
    });

    Thread radar = new Thread(new Runnable() {
      public void run() {
        try(Ultrasonic sonic = new Ultrasonic(ev3, "S1")) {
          Float distance;
          while(true) {
            if (Thread.interrupted()) {
              sonic.close();
            } else {
              Integer v = vinkel.take();
              if (v.intValue() == 90 || v.intValue() == 0) {
                distance = Float.valueOf(sonic.getDistance());
                System.out.println(distance);
                if (!distance.isInfinite()) {
                  klar.add(new Melding(v, distance));
                } else {
                  klar.add(new Melding(v, Float.valueOf(2.0f)));
                }
              }
              // Thread.sleep(1000);
            }
          }
        } catch(RemoteException e) {
        // } catch (InterruptedException e) {
        } catch (IOException e) {
        } catch (Exception e) {
        }
      }
    });


    Thread gyro = new Thread(new Runnable() {
      public void run() {
        try(Gyro gyro = new Gyro(ev3, "S2")) {
          float angle;
          while(true) {
            if (Thread.interrupted()) {
              gyro.close();
            } else {
              angle = gyro.getAngle();
              System.out.println(angle);
              Thread.sleep(1000);
            }
          }
        } catch(RemoteException e) {
        } catch (IOException e) {
        } catch (InterruptedException e) {
        } catch (Exception e) {
        }
      }
    });

    Thread farge = new Thread(new Runnable() {
      public void run() {
        try(Farge farge = new Farge(ev3, "S3")) {
          float colorID;
          while(true) {
            if (Thread.interrupted()) {
              farge.close();
            } else {
              colorID = farge.getColor();
              System.out.println(colorID);
              Thread.sleep(1000);
            }
          }
        } catch(RemoteException e) {
        } catch (IOException e) {
        } catch (InterruptedException e) {
        } catch (Exception e) {
        }
      }
    });

    try {
      // radar.start();
      // motor.start();
      // kjør.start();
      gyro.start();
      farge.start();

      // radar.join();
      // motor.join();
      // kjør.join();
      gyro.join();
      farge.join();
    } catch (InterruptedException e) {
      // kjør.interrupt();
      // radar.interrupt();
      // motor.interrupt();
      gyro.interrupt();
      farge.interrupt();
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
