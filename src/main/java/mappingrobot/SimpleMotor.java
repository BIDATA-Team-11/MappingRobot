
package mappingrobot;

import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.lang.InterruptedException;

/**
 * Class that implements the simplemotor used for the NXTUltrasonicSensor.
 *
 *@author Stian Selvåg
 *@author Herman Aagaard
 *@author Henrik Hafsø
 *@author Joakim Skogø Langvand
 *@author Erling Sletta
 *@author Torbjørn Øverås
 *@author Gruppe 11, dataingeniør NTNU, første semester.
 */
public class SimpleMotor implements AutoCloseable {

  private RemoteEV3 ev3;
  private RMIRegulatedMotor motor;
  private String port;

  /**
   * Construct new simplemotor object using default Ev3 brick.
   *
   * @param port physical port where the motor is connected.
   */
  public SimpleMotor(RemoteEV3 ev3, String port) { 
    this.ev3 = ev3; this.port = port; 
    this.motor = this.ev3.createRegulatedMotor(this.port, 'M'); 
  }

  /**
   * Method for connecting motor to the physical port on the Ev3 brick.
   */
  private void createMotor() { this.motor = this.ev3.createRegulatedMotor(this.port, 'M'); }

  /**
   * Method for closing the simplemotor port.
   *
   * @throws RemoteException Exception is thrown if an error occurs.
   */
  @Override
  public void close() throws RemoteException {
    try { this.motor.close(); }
    catch (IOException e) { e.printStackTrace(); }
  }

  /**
   * Method for stopping the simplemotor.
   *
   * @throws RemoteException Exception is thrown if an error occurs.
   */
  public void stop() throws RemoteException { this.motor.stop(true); }
  // public void rotate(int angle) throws RemoteException { createMotor(); this.motor.rotateTo(angle); }

  /**
   * Method for rotating the simple motor by angle degrees.
   * @param angle int for the amount of degrees the simplemotor should rotate.
   * @throws RemoteException Exception is thrown if an error occurs.
   */
  public void rotate(int angle) throws RemoteException { this.motor.rotateTo(angle); }

  /**
   * Method for making the simplemotor rotate towards left.
   * @throws RemoteException Exception is thrown if an error occurs.
   */
  public void left() throws RemoteException {
    // try {
      createMotor();

      this.motor.backward();

      // Thread.sleep(2000);

    // } catch (InterruptedException e) {
    //   close();
    // }
  }

  /**
   * Method for making the simplemotor rotate towards the right.
   * @throws RemoteException Exception is thrown if an error occurs.
   */
  public void right() throws RemoteException {
    // try {
      createMotor();

      this.motor.forward();

      // Thread.sleep(2000);

    // } catch (InterruptedException e) {
    //   close();
    // }
  }
}
