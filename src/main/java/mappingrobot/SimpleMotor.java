
package mappingrobot;

import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.lang.InterruptedException;

public class SimpleMotor implements AutoCloseable {

  private RemoteEV3 ev3;
  private RMIRegulatedMotor motor;
  private String port;

  public SimpleMotor(RemoteEV3 ev3, String port) { 
    this.ev3 = ev3; this.port = port; 
    this.motor = this.ev3.createRegulatedMotor(this.port, 'M'); 
  }
  private void createMotor() { this.motor = this.ev3.createRegulatedMotor(this.port, 'M'); }

  @Override
  public void close() throws RemoteException {
    try { this.motor.close(); }
    catch (IOException e) { e.printStackTrace(); }
  }

  public void stop() throws RemoteException { this.motor.stop(true); }
  // public void rotate(int angle) throws RemoteException { createMotor(); this.motor.rotateTo(angle); }
  public void rotate(int angle) throws RemoteException { this.motor.rotateTo(angle); }

  public void left() throws RemoteException {
    // try {
      createMotor();

      this.motor.backward();

      // Thread.sleep(2000);

    // } catch (InterruptedException e) {
    //   close();
    // }
  }

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
