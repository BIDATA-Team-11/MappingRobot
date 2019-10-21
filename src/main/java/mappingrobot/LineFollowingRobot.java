/**
 * Implementation for a line following robot. Turn angles can be adjusted for the type of path it should follow.
 * @author Stian Selvåg
 * @author Herman Aagaard
 * @author Henrik Hafsø
 * @author Joakim Skogø Langvand
 * @author Erling Sletta
 * @author Torbjørn Øverås
 * @author Gruppe 11, dataingeniør NTNU, første semester.
 * @version 1.0.0
 */

package team11.mappingrobot;

import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.WheeledChassis;
import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.LineFollowingMoveController;
import lejos.robotics.RegulatedMotor;

public class LineFollowingRobot implements Robot {
  private Wheel wheel1;
  private Wheel wheel2;
  private Chassis chassis;
  private MovePilot pilot;
  private Direction activeDirectionState = Direction.STOP;
  private Direction newDirectionState = Direction.STOP;
  private LineFollowingMoveController lineFollower;
  private int turnRadius = 90;

  /**
  * Constructs a Robot with differential steering.
  * @param wheelOffset Wheel offset from the X axis.
  * @param wheelSize Wheel diameter in cm.
  */
  LineFollowingRobot(float wheelOffset, double wheelSize) {
    this.wheel1 = LejosApiBugs.modelWheel(Motor.A, wheelSize).offset(-wheelOffset);
    this.wheel2 = LejosApiBugs.modelWheel(Motor.B, wheelSize).offset(wheelOffset);
    this.chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
    this.pilot = new MovePilot(chassis);
  }

  /**
   * {@inheritDoc}
   */
  @Override public Direction getCurrentDirectionState() {
    return this.activeDirectionState;
  }

  /**
   * {@inheritDoc}
   */
  @Override public void setDirectionState(Direction state) {
    this.activeDirectionState = state;
  }

  /**
   * {@inheritDoc}
   */
  @Override public int getTurnRadius() {
    return this.turnRadius;
  }

  /**
   * {@inheritDoc}
   */
  @Override public void setTurnRadius(int turnRadius) {
    if (turnRadius < 1 || turnRadius > 100) {
      throw new IllegalArgumentException
        ("turnRadius must be an int in the range [1, 100]");
    } else {
      this.turnRadius = turnRadius;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override public void update() {
      switch (this.activeDirectionState) {
        case FORWARD: lineFollower.steer(0); break;
        case LEFT: lineFollower.steer(-this.turnRadius); break;
        case RIGHT: lineFollower.steer(this.turnRadius); break;
      }
  }
}
