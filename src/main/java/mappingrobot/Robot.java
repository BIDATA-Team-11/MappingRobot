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

package team11.mappingrobot;

import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.LineFollowingMoveController;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.RegulatedMotor;

public interface Robot {
  public static enum Direction {

      /** Turn right while driving */
      RIGHT,

      /** Stop, then turn right */
      SHARP_RIGHT,

      /** Turn left while driving */
      LEFT,

      /** Stop, then turn left */
      SHARP_LEFT,

      /** Go forward */
      FORWARD,

      /** Drive or turn backwards */
      REVERSE,

      /** Stop */
      STOP
  }

  /**
   * Get current direction. Not necessarily active if {@link #update} has not
   * been called since last call to {@link #setDirectionState}.
   * @return Current direction state.
   * @see #setDirectionState
   * @see #update
   */
  public Direction getCurrentDirectionState();

  /**
   * Set new direction. Will not become active before {@link #update} is called.
   * @param state New direction
   * @see #update
   */
  public void setDirectionState(Direction state);

  /**
   * Get current turn radius. Turn radius is given as an int in the range
   * [1, 100]. Higher number means a sharper turn.
   * @return int Returns the current turn radius.
   * @see #setTurnRadius
   */
  public int getTurnRadius();

  /**
   * Set current turn radius. Turn radius is given as an int in the range
   * [1, 100]. Higher number means a sharper turn.
   * @param turnRadius New turn radius. Must be an int in the range [1, 100].
   * @throws IllegalArgumentException Throws an exception when turnRadius is
   * out of range ([1, 100]).
   * @see #setTurnRadius
   */
  public void setTurnRadius(int turnRadius);

  /**
   * If set direction has changed, activate it.
   * @see #setDirectionState
   * @see #getCurrentDirectionState
   */
  public void update();
}
