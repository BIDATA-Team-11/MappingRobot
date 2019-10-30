package mappingrobot;

import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import java.rmi.RemoteException;

public final class RemoteRobot {
  private String ultraSonicPort = "S2";
  private double wheelSize = 5.0 * Units.cm;
  private float wheelOffset = 7.0f * Units.cm;
  private String colorSensor1 = "S3";
  private String colorSensor2 = "S4";
  private String leftMotorPort = "A";
  private String rightMotorPort = "C";
  private RMIRegulatedMotor leftMotor = null;
  private RMIRegulatedMotor rightMotor = null;
  private RemoteEV3 ev3 = null;

  RemoteRobot() throws RemoteException, Exception {
    try {
      this.ev3 = new RemoteEV3("10.0.0.1");
    } catch(RemoteException e) {
      throw new RemoteException(e.getMessage());
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }

    leftMotor = ev3.createRegulatedMotor("A",'l');
    rightMotor = ev3.createRegulatedMotor("C",'l');
  }

  public RemoteEV3 getEV3() {
    return this.ev3;
  }

  public RMIRegulatedMotor getLeftMotor() {
    return this.leftMotor;
  }

  public RMIRegulatedMotor getRightMotor() {
    return this.rightMotor;
  }
}
