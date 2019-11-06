package mappingrobot;

import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.lang.InterruptedException;

public class Motor {

  private RemoteEV3 ev3;
  private RMIRegulatedMotor MVenstre;
  private RMIRegulatedMotor MHøyre;

  public Motor(RemoteEV3 ev3) {
    this.ev3 = ev3;
  }

  private void createLeft() { this.MVenstre = this.ev3.createRegulatedMotor("A", 'L'); }
  private void createRight() { this.MHøyre = this.ev3.createRegulatedMotor("C", 'L'); }

  public void close() throws RemoteException {
    try {
      this.MVenstre.close();
      this.MHøyre.close();
    } catch(RemoteException e) {
      throw e;
    }
  }

  public void stop() throws RemoteException {
    try {
      this.MVenstre.stop(true);
      this.MHøyre.stop(true);
      close();
    } catch(RemoteException e) {
      throw e;
    }
  }

  public void left() throws RemoteException {
    try {
      createRight();
      createLeft();
      
      this.MHøyre.backward();
      this.MVenstre.stop(true);

      Thread.sleep(2000);

      close();

    } catch (InterruptedException e) {
      close();
    } catch (RemoteException e) {
      throw e;
    }
  }

  public void right() throws RemoteException {
    try {
      createRight();
      createLeft();

      this.MVenstre.backward();
      this.MHøyre.stop(true);

      Thread.sleep(2000);

      close();

    } catch (InterruptedException e) {
      close();
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

      close();

    } catch (InterruptedException e) {
      close();
    } catch (RemoteException e) {
      throw e;
    }
  }

  public void forward() throws RemoteException {
    try {
      createRight();
      createLeft();

      this.MVenstre.backward();
      this.MHøyre.backward();

      Thread.sleep(2000);
      close();

    } catch (InterruptedException e) {
      close();
    } catch (RemoteException e) {
      throw e;
    }
  }
}
