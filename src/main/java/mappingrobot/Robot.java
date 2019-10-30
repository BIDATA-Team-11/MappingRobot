package mappingrobot;

import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.LineFollowingMoveController;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.RegulatedMotor;

/**
 * Interface for a generic wheeled robot.
 * @author Stian Selvåg
 * @author Herman Aagaard
 * @author Henrik Hafsø
 * @author Joakim Skogø Langvand
 * @author Erling Sletta
 * @author Torbjørn Øverås
 * @author Gruppe 11, dataingeniør NTNU, første semester.
 * @version 1.0.0
 */
public interface Robot {
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
    REVERSE,

    /** Stop. */
    STOP
  }

  /**
   * Get active direction state.
   * @return Current direction state.
   * @see #setDirectionState
   */
  public Direction getActiveDirectionState();

  /**
   * Set new direction. Will not become active before {@link #update} is called.
   * @param state New direction.
   * @see #update
   */
  public void setDirectionState(Direction state);

  /**
   * Update direction based on sensor input or other implementation specific
   * criteria. Call {@link #update} for the changes to take effect.
   * @return boolean True if direction has been updated.
   * @see #update
   */
  public boolean updateDirection();

  /**
   * If set direction has changed, activate it. Does nothing if not.
   * @see #setDirectionState
   * @see #getCurrentDirectionState
   */
  public void update();
}
