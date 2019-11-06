package mappingrobot;

import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.lang.InterruptedException;

public class Motor {

  RemoteEV3 ev3;
  RMIRegulatedMotor MVenstre;
  RMIRegulatedMotor MHøyre;

  public Motor(RemoteEV3 ev3) {
    this.ev3 = ev3;
    this.MHøyre = ev3.createRegulatedMotor("C", 'L');
    this.MVenstre = ev3.createRegulatedMotor("A", 'L');
  }

  private void createLeft() { this.MVenstre = ev3.createRegulatedMotor("A", 'L'); }
  private void createRight() { this.MHøyre = ev3.createRegulatedMotor("C", 'L'); }

  public void close() throws RemoteException {
    this.MVenstre.close();
    this.MHøyre.close();
  }

  public void stop() throws RemoteException {
    try {
      MVenstre.stop(true);
      MHøyre.stop(true);
    } catch(RemoteException e) {
      throw e;
    }
  }

  public void left() throws RemoteException {
    try {
      createRight();
      
      MHøyre.backward();

      Thread.sleep(2000);

      MHøyre.close();

      if (Thread.interrupted()) {
        MVenstre.close();
        MHøyre.close();
      }

    } catch (InterruptedException e) {
      MVenstre.close();
      MHøyre.close();
    } catch (RemoteException e) {
      throw e;
    }
  }

  public void right() throws RemoteException {
    try {
      createLeft();

      MVenstre.backward();
      Thread.sleep(2000);
      MVenstre.close();

      if (Thread.interrupted()) {
        MVenstre.close();
        MHøyre.close();
      }

    } catch (InterruptedException e) {
      MVenstre.close();
      MHøyre.close();
    } catch (RemoteException e) {
      throw e;
    }
  }

  public void backward() throws RemoteException {
    try {
      createRight();
      createLeft();

      MVenstre.forward();
      MHøyre.forward();

      Thread.sleep(2000);
      MVenstre.close();
      MHøyre.close();

      if (Thread.interrupted()) {
        MVenstre.close();
        MHøyre.close();
      }

    } catch (InterruptedException e) {
      MVenstre.close();
      MHøyre.close();
    } catch (RemoteException e) {
      throw e;
    }
  }

  public void forward() throws RemoteException {
    try {
      createRight();
      createLeft();

      MVenstre.backward();
      MHøyre.backward();

      Thread.sleep(2000);

      MVenstre.close();
      MHøyre.close();

      if (Thread.interrupted()) {
        MVenstre.close();
        MHøyre.close();
      }

    } catch (InterruptedException e) {
      MVenstre.close();
      MHøyre.close();
    } catch (RemoteException e) {
      throw e;
    }
  }
}
