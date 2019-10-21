/**
 * Klasse for alle styringsfunksjoner for roboten. Det er meningen at denne skal være fleksibel og kan brukes til forskjellige typer roboter.
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

public class Robot {
  private Wheel wheel1;
  private Wheel wheel2;
  private Chassis chassis;
  private MovePilot pilot;
  private Direction currentDirectionState = Direction.STOP;
  private LineFollowingMoveController lineFollower;

  public static enum Mode {

    /** Line following type of navigation */
    LINE_FOLLOWING
  }

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

  Mode mode;

  /**
  * Constructs a Robot with differential steering.
  * @param wheelOffset Wheel offset from the X axis.
  * @param wheelSize Wheel diameter in cm.
  */
  Robot(float wheelOffset, double wheelSize) {
    this.wheel1 = LejosApiBugs.modelWheel(Motor.A, wheelSize).offset(-wheelOffset);
    this.wheel2 = LejosApiBugs.modelWheel(Motor.B, wheelSize).offset(wheelOffset);
    this.chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
    this.pilot = new MovePilot(chassis);
    this.mode = Mode.LINE_FOLLOWING;
  }

  /**
  * Sets the mode for steering.
  * @param mode Modus av typen {@link Robot.Mode}
  */
  public void setMode(Mode mode) {
    this.mode = mode;
  }

  /**
  * Henter gjeldende styringsmodus.
  * @return Gjeldende styringsmodus.
  */
  public Direction getCurrentDirectionState() {
    return this.currentDirectionState;
  }

  /**
  * Setter retning på roboten. Trer ikke i kraft før {@link #update} kalles.
  * @param state Retning av typen Direction
  */
  public void setDirectionState(Direction state) {
    this.currentDirectionState = state;
  }

  /**
  * Nye instrukser siten forrige update() trer i kraft.
  */
  public void update() {
    if (mode == Mode.LINE_FOLLOWING) {
      switch (currentDirectionState) {
        case FORWARD: lineFollower.steer(0); break;
        case LEFT: lineFollower.steer(-90); break;
        case RIGHT: lineFollower.steer(90); break;
      }
    }
  }
}
