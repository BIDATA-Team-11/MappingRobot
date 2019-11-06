package mappingrobot;

import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import lejos.hardware.port.Port;
import java.net.MalformedURLException;
import lejos.robotics.chassis.Chassis;

class RemoteRobot {
  private Port DistanceMeasurePort = null;
  private DistanceMeasure distanceMeasureSensor = null;
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
  private Chassis chassis = null;
  private Motor movement = null;

  public static enum Direction {
    /** Turn right while driving. */
    RIGHT,

    /** Stop, then turn right. */
    SHARP_RIGHT,

    /** Turn left while driving. */
    LEFT,

    /** Stop, then turn left. */
    SHARP_LEFT,

    /** Go forward. */
    FORWARD,

    /** Movements are carried out int backwards direction. */
    REVERSE, BACKWARD,

    /** Stop. */
    STOP
  }

  RemoteRobot() throws RemoteException, MalformedURLException, NotBoundException {
    RemoteEV3 ev3 = new RemoteEV3("10.0.1.1");
    leftMotor = ev3.createRegulatedMotor(leftMotorPort, 'L');
    rightMotor = ev3.createRegulatedMotor(rightMotorPort, 'L');
    movement = new Motor(ev3);
    // distanceMeasureSensor = new Ultrasonic();
  }

  public void setDistanceSensorPort(String port) {
    // this.DistanceMeasurePort = port;
  }

  public void setWheelSizeInCm(double wheelSize) {
    this.wheelSize = wheelSize * Units.cm;
  }

  public void setMovement(Direction direction) {
    switch (direction) {
    case FORWARD:
      movement.forward();
      break;
    case REVERSE:
    case BACKWARD:
      movement.backward();
      break;
    case LEFT:
    case SHARP_LEFT:
      movement.left();
      break;
    case RIGHT:
    case SHARP_RIGHT:
      movement.right();
      break;
    default:
    }
    movement.close();
  }

  /*
   * RemoteRobot() throws RemoteException, Exception { try { this.ev3 = new
   * RemoteEV3("10.0.0.1"); } catch(RemoteException e) { throw new
   * RemoteException(e.getMessage()); } catch (Exception e) { throw new
   * Exception(e.getMessage()); }
   *
   * colorSensorPort = ev3.getPort(colorSensor1PortName);
   *
   * leftMotor = ev3.createRegulatedMotor("A",'1'); rightMotor =
   * ev3.createRegulatedMotor("C",'1'); }
   */

  public RemoteEV3 getEV3() {
    return this.ev3;
  }

  public RMIRegulatedMotor getLeftMotor() {
    return this.leftMotor;
  }

  public RMIRegulatedMotor getRightMotor() {
    return this.rightMotor;
  }

  public void moveForward() {

  }
}
