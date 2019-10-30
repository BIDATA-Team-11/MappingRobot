package mappingrobot;

import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import lejos.hardware.port.Port;
import java.net.MalformedURLException;

class RemoteRobot extends RemoteEV3 {
  private String ultraSonicPort = "S2";
  private double wheelSize = 5.0 * Units.cm;
  private float wheelOffset = 7.0f * Units.cm;
  private String colorSensor1PortName = "S3";
  private String colorSensor2PortName = "S4";
  private String leftMotorPort = "A";
  private String rightMotorPort = "C";
  private RMIRegulatedMotor leftMotor = null;
  private RMIRegulatedMotor rightMotor = null;
  private RemoteEV3 ev3 = null;
  private Port colorSensorPort = null;

  RemoteRobot() throws RemoteException, MalformedURLException, NotBoundException {
    super("10.0.1.1");
  }

  public void setUltrasonicSensorPort(String port) {
    this.ultraSonicPort = port;
  }

  public void setWheelSizeInCm(double wheelSize) {
    this.wheelSize = wheelSize * Units.cm;
  }

  /*RemoteRobot() throws RemoteException, Exception {
    try {
      this.ev3 = new RemoteEV3("10.0.0.1");
    } catch(RemoteException e) {
      throw new RemoteException(e.getMessage());
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }

    colorSensorPort = ev3.getPort(colorSensor1PortName);

    leftMotor = ev3.createRegulatedMotor("A",'1');
    rightMotor = ev3.createRegulatedMotor("C",'1');
  }*/



  public RemoteEV3 getEV3() {
    return this;
  }

  public RMIRegulatedMotor getLeftMotor() {
    return this.leftMotor;
  }

  public RMIRegulatedMotor getRightMotor() {
    return this.rightMotor;
  }
}
