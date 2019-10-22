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

import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.LineFollowingMoveController;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.RegulatedMotor;

public class LineFollowingRobot implements Robot {
  private boolean lineIsBetweenSensors = false;
  private Chassis chassis;
  private ColorSensor mainSensor;
  private ColorSensor correctionSensor;
  private Direction activeDirectionState = Direction.STOP;
  private Direction nextDirectionState = Direction.STOP;
  private int turnRadius = 90;
  private LineFollowingMoveController lineFollower;
  private MovePilot pilot;
  private Wheel wheel1;
  private Wheel wheel2;

  /**
   * Constructs a Robot with differential steering.
   * @param wheelOffset Wheel offset from the X axis.
   * @param wheelSize Wheel diameter in cm.
   */
  LineFollowingRobot(float wheelOffset, double wheelSize,
  String mainSensor, String correctionSensor) {
    this.wheel1 =
      LejosApiBugs.modelWheel(Motor.A, wheelSize).offset(-wheelOffset);
    this.wheel2 =
      LejosApiBugs.modelWheel(Motor.B, wheelSize).offset(wheelOffset);
    this.chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 },
      WheeledChassis.TYPE_DIFFERENTIAL);
    this.pilot = new MovePilot(chassis);
    this.mainSensor = new ColorSensor("S2");
    this.correctionSensor = new ColorSensor("S4");
  }

  /** {@inheritDoc} */
  @Override public Direction getActiveDirectionState() {
    return this.activeDirectionState;
  }

  /** {@inheritDoc} */
  @Override public void setDirectionState(Direction state) {
    this.nextDirectionState = state;
  }

  /**
   * Get current turn radius. Turn radius is given as an int in the range
   * [1, 100]. Higher number means a sharper turn.
   * @return int Returns the current turn radius.
   * @see #setTurnRadius
   */
  public int getTurnRadius() {
    return this.turnRadius;
  }

  /**
   * Set current turn radius. Turn radius is given as an int in the range
   * [1, 100]. Higher number means a sharper turn.
   * @param turnRadius New turn radius. Must be an int in the range [1, 100].
   * @throws IllegalArgumentException Throws an exception when turnRadius is
   * out of range ([1, 100]).
   * @see #getTurnRadius
   */
  public void setTurnRadius(int turnRadius) {
    if (turnRadius > 1 || turnRadius < 100) {
      this.turnRadius = turnRadius;
    } else {
      throw new IllegalArgumentException
        ("turnRadius must be an int in the range [1, 100]");
    }
  }

  /**
   * Set direction to follow line based on color sensor inputs. Call
   * {@link #update} for the changes to take effect.
   * @return boolean True if direction has been updated.
   * @see #update
   */
  public boolean updateDirection() {
    if (mainSensor.lostLine()) {
      if (this.getActiveDirectionState() ==
      Direction.FORWARD || this.correctionSensor.hasLine()) {
        if (this.correctionSensor.hasLine()) {
          this.setDirectionState(Direction.RIGHT);
        } else if (!lineIsBetweenSensors) {
          this.setDirectionState(Direction.LEFT);
        }
      }
    }

    if (mainSensor.lostLine() && this.correctionSensor.hasLine()) {
      this.setDirectionState(Direction.RIGHT);
      lineIsBetweenSensors = true;
    }

    if (mainSensor.hasLine()) {
      this.setDirectionState(Direction.FORWARD);
      lineIsBetweenSensors = false;
    }

    return this.nextDirectionState == this.getActiveDirectionState() ?
      false : true;
  }

  /** {@inheritDoc} */
  @Override public void update() {
    if(this.activeDirectionState != this.nextDirectionState) {
      this.activeDirectionState = this.nextDirectionState;
      switch (this.activeDirectionState) {
        case FORWARD: lineFollower.steer(0); break;
        case LEFT: lineFollower.steer(-this.turnRadius); break;
        case RIGHT: lineFollower.steer(this.turnRadius); break;
      }
    }
  }
}
