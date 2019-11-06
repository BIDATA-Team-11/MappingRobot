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
    // this.ev3 = ev3;
    this.MHøyre = ev3.createRegulatedMotor("C", 'L');
    this.MVenstre = ev3.createRegulatedMotor("A", 'L');
  }

  private void createLeft() { this.MVenstre = this.ev3.createRegulatedMotor("A", 'L'); }
  private void createRight() { this.MHøyre = this.ev3.createRegulatedMotor("C", 'L'); }

  public void close() throws RemoteException {
    this.MVenstre.close();
    this.MHøyre.close();
  }

  public void stop() throws RemoteException {
    try {
      this.MVenstre.stop(true);
      this.MHøyre.stop(true);
    } catch(RemoteException e) {
      throw e;
    }
  }

  public void left() throws RemoteException {
    try {
      createRight();
      
      this.MHøyre.backward();

      Thread.sleep(2000);

      this.MHøyre.close();

      if (Thread.interrupted()) {
        this.MVenstre.close();
        this.MHøyre.close();
      }

    } catch (InterruptedException e) {
      this.MVenstre.close();
      this.MHøyre.close();
    } catch (RemoteException e) {
      throw e;
    }
  }

  public void right() throws RemoteException {
    try {
      createLeft();

      this.MVenstre.backward();
      Thread.sleep(2000);
      this.MVenstre.close();

      if (Thread.interrupted()) {
        this.MVenstre.close();
        this.MHøyre.close();
      }

    } catch (InterruptedException e) {
      this.MVenstre.close();
      this.MHøyre.close();
    } catch (RemoteException e) {
      throw e;
    }
  }

  public void backward() throws RemoteException {
    try {
      createRight();
      createLeft();

      this.MVenstre.forward();
      this.MHøyre.forward();

      Thread.sleep(2000);
      this.MVenstre.close();
      this.MHøyre.close();

      if (Thread.interrupted()) {
        this.MVenstre.close();
        this.MHøyre.close();
      }

    } catch (InterruptedException e) {
      this.MVenstre.close();
      this.MHøyre.close();
    } catch (RemoteException e) {
      throw e;
    }
  }

  public void forward() throws RemoteException {
    try {
      // createRight();
      // createLeft();

      this.MVenstre.backward();
      this.MHøyre.backward();

      Thread.sleep(2000);

      this.MVenstre.close();
      this.MHøyre.close();

      if (Thread.interrupted()) {
        this.MVenstre.close();
        this.MHøyre.close();
      }

    } catch (InterruptedException e) {
      this.MVenstre.close();
      this.MHøyre.close();
    } catch (RemoteException e) {
      throw e;
    }
  }
}
