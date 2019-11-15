package mappingrobot;

import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.lang.InterruptedException;

public class Motor implements AutoCloseable {

  private RemoteEV3 ev3;
  private RMIRegulatedMotor leftMotor;
  private RMIRegulatedMotor rightMotor;

  public static final float wheelCircumference = 2.0f * 3.14159265f * 1.5f;

  public Motor(RemoteEV3 ev3) {
    this.ev3 = ev3;
    this.leftMotor = this.ev3.createRegulatedMotor("A", 'L');
    this.rightMotor = this.ev3.createRegulatedMotor("C", 'L');

  }

  private void createLeft() {
    this.leftMotor = this.ev3.createRegulatedMotor("A", 'L');
  }

  private void createRight() {
    this.rightMotor = this.ev3.createRegulatedMotor("C", 'L');
  }

  @Override
  public void close() throws RemoteException {
    try {
      this.leftMotor.close();
      this.rightMotor.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void stop() throws RemoteException {
    this.leftMotor.stop(true);
    this.rightMotor.stop(true);
  }

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
