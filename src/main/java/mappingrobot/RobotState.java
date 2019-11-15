package mappingrobot;

import java.awt.Point;

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

  public Point getRobotPosition() {
    return new Point((int) positionX, (int) positionY);
  }

  public Point getAbsolutePointFromReading(float radarDistance, float radarAngle) {
    Point robotPosition = getRobotPosition();
    Point radarPoint = new Point((int) (radarDistance * Math.cos(radarAngle)),
        (int) (radarDistance * Math.sin(radarAngle)));

    return null;
  }

  public Point absolutePoint(double radarAngle, double robotAngle, double distance, Point robotPosition) {
    Point temp = new Point((int) (distance * (Math.cos(radarAngle) + Math.cos(robotAngle))),
        (int) (distance * (Math.sin(radarAngle) + Math.sin(robotAngle))));
    temp.translate((int) robotPosition.getX(), (int) robotPosition.getY());
    return temp;
  }

  public void updatePosition(int tachoCount) {
    Point temp = getRobotPosition();
    temp.translate((int) (tachoCount * Motor.wheelCircumference * Math.cos(robotAngle)),
        (int) (tachoCount * Motor.wheelCircumference * Math.sin(robotAngle)));
    this.positionX = temp.getX();
    this.positionY = temp.getY();
  }
}
