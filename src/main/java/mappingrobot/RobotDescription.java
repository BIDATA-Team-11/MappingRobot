package team11.mappingrobot;

import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.robotics.navigation.MoveController;

class RobotDescription {
  private Brick brick;
  public Port ultraSonicPort;
  public final double wheelSize;
  public final float wheelOffset;

  RobotDescription() {
    this.brick = BrickFinder.getDefault();
    this.ultraSonicPort = brick.getPort("S2");
    this.wheelSize = MoveController.WHEEL_SIZE_EV3;
    this.wheelOffset = 7.0f;
  }
}
