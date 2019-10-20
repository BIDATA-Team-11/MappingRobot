/**
 * Enum for å bedre lesbarhet.
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

public enum Direction {
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
