package team11.mappingrobot;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.port.Port;
import lejos.robotics.navigation.MoveController;

public final class RobotDescription {
  public static final String ultraSonicPort = "S2";
  public static final double wheelSize = MoveController.WHEEL_SIZE_EV3;
  public static final float wheelOffset = 7.0f;
  public static final String colorSensor1 = "S3";
  public static final String colorSensor2 = "S4";
}
