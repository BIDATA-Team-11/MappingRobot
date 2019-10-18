package team11.mappingrobot;

import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.robotics.navigation.MoveController;

class RobotDescription {
  private Brick brick;
  public Port ultraSonicPort;
  public final double wheelSize = MoveController.WHEEL_SIZE_EV3;

  RobotDescription() {
    this.brick = BrickFinder.getDefault();
    this.ultraSonicPort = brick.getPort("S2");
  }
}
