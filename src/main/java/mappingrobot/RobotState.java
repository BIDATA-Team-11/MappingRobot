package mappingrobot;

import java.awt.Point;
/**
*  * LejOS SomethingSomething
*
* @author Stian Selvåg
* @author Herman Aagaard
* @author Henrik Hafsø
* @author Joakim Skogø Langvand
* @author Erling Sletta
* @author Torbjørn Øverås
* @author Group 11, dataingeniør NTNU, første semester.
* @version 2.0.0
*/
class PositionInfo {
  private float robotAngle;
  private float radarAngle;
  private double positionX;
  private double positionY;

  PositionInfo() {
    robotAngle = 0;
    radarAngle = 0;
    positionX = 0;
    positionY = 0;
  }
  /**
   * This method returns the x and y coordinates of the car which help plott its location.
   *
   * @return x and y Coordinates
   *
   */
  public Point getRobotPosition() {
    return new Point((int) positionX, (int) positionY);
  }
  /**
   *
   * This method marks a point the EV3 finds through the ultrasonic sensor. This
   * is done by taking its driven distance and adding it onto the distance the
   * ultrasonic sensor detects through pytagoras.   * @param  radarDistance
   * @param  radarAngle
   * @return
   */
  public Point getAbsolutePointFromReading(float radarDistance, float radarAngle) {
    Point robotPosition = getRobotPosition();
    Point radarPoint = new Point((int) (radarDistance * Math.cos(radarAngle)),
        (int) (radarDistance * Math.sin(radarAngle)));
    radarPoint.translate((int) robotPosition.getX(), (int) robotPosition.getY());
    return radarPoint;
  }
  /**
   * PointMaker
   *
   * This method marks a point the EV3 finds through the ultrasonic sensor. This
   * is done by taking its driven distance and adding it onto the distance the
   * ultrasonic sensor detects through pytagoras.
   *
   * @param  radarAngle
   * @param  robotAngle
   * @param  distance
   * @param  robotPosition
   * @return
   */
  public Point absolutePoint(double radarAngle, double robotAngle, double distance, Point robotPosition) {
    Point temp = new Point((int) (distance * (Math.cos(radarAngle) + Math.cos(robotAngle))),
        (int) (distance * (Math.sin(radarAngle) + Math.sin(robotAngle))));
    temp.translate((int) robotPosition.getX(), (int) robotPosition.getY());
    return temp;
  }
  /**
   * RPMCounter
   *
   * This method finds the driven distance through calculating the amount of rotations
   * the wheels have done since the last check.
   *
   * @param tachoCount
   */
  public void updatePosition(int tachoCount) {
    Point temp = getRobotPosition();
    temp.translate((int) (tachoCount * Motor.wheelCircumference * Math.cos(robotAngle)),
        (int) (tachoCount * Motor.wheelCircumference * Math.sin(robotAngle)));
    this.positionX = temp.getX();
    this.positionY = temp.getY();
  }
}
