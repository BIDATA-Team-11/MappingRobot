package mappingrobot;

import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.lang.InterruptedException;

/**
 * Class for implementing NXTRegulatedMotor.
 *
 * @author Stian Selvåg
 * @author Herman Aagaard
 * @author Henrik Hafsø
 * @author Joakim Skogø Langvand
 * @author Erling Sletta
 * @author Torbjørn Øverås
 * @author Gruppe 11, dataingeniør NTNU, første semester.
 */
public class Motor implements AutoCloseable {

  private RemoteEV3 ev3;
  private RMIRegulatedMotor leftMotor;
  private RMIRegulatedMotor rightMotor;

  public static final float wheelCircumference = 2.0f * 3.14159265f * 1.5f;

  /**
   * Constructer for setting value of ev3.
   * It also pairs left and right motor with ports.
   * @param ev3
   */
  public Motor(RemoteEV3 ev3) {
    this.ev3 = ev3;
    this.leftMotor = this.ev3.createRegulatedMotor("A", 'L');
    this.rightMotor = this.ev3.createRegulatedMotor("C", 'L');

  }

  /**
   * Method for pairing left motor with port A.
   */
  private void createLeft() {
    this.leftMotor = this.ev3.createRegulatedMotor("A", 'L');
  }

  /**
   * Method for pairing right motor with port C.
   */
  private void createRight() {
    this.rightMotor = this.ev3.createRegulatedMotor("C", 'L');
  }

  /**
   * Method for closing port A and C.
   * @throws RemoteException - Exception is thrown if an error occurss.
   */
  @Override
  public void close() throws RemoteException {
    try {
      this.leftMotor.close();
      this.rightMotor.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method for stopping both motors.
   * @throws RemoteException - Exception is thrown if an error occurss.
   */
  public void stop() throws RemoteException {
    this.leftMotor.stop(true);
    this.rightMotor.stop(true);
  }

  /**
   * Method got making the car turn left.
   * @throws RemoteException - Exception is thrown if an error occurss.
   */
  public void left() throws RemoteException {
    try {
      // createRight();
      // createLeft();

      this.rightMotor.backward();
      this.leftMotor.stop(true);

      Thread.sleep(10);

    } catch (InterruptedException e) {
      close();
    }
  }

  /**
   * Method for making the car turn right.
   * @throws RemoteException - Exception is thrown if an error occurss.
   */
  public void right() throws RemoteException {
    try {
      // createRight();
      // createLeft();

      this.leftMotor.backward();
      this.rightMotor.stop(true);
      // close();

      // Thread.sleep(2000);
      Thread.sleep(10);

    } catch (InterruptedException e) {
      close();
    }
  }

  /**
   * Method for making the motors drive backwards.
   * @throws RemoteException - Exception is thrown if an error occurss.
   */
  public void backward() throws RemoteException {
    try {
      // createRight();
      // createLeft();

      this.leftMotor.forward();
      this.rightMotor.forward();

      Thread.sleep(10);

    } catch (InterruptedException e) {
      close();
    }
  }

  /**
   * Method for making the motors drive forwards.
   * @throws RemoteException - Exception is thrown if an error occurss.
   */
  public void forward() throws RemoteException {
    try {
      // createRight();
      // createLeft();

      this.leftMotor.backward();
      this.rightMotor.backward();
      // close();

      // Thread.sleep(2000);
      Thread.sleep(10);

    } catch (InterruptedException e) {
      close();
    }
  }
}
