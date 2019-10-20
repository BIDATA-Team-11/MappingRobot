/**
 * Enheter som kan brukes for å forenkle lesbarhet. En kan for eksempel bruke 0.4*{@link .metres} eller {@link .minutes}.
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

public class Units {
  /** Millimeter */
  public static final float millimetres = 0.1f;

  /** Centimeter */
  public static final float centimetres = 1.0f;

  /** Alias for centimeter */
  public static final float cm = 1.0f;

  /** Meter */
  public static final float metres = 100.0f;

  /** Sekund */
  public static final float seconds = 1000.0f; // 1000 millisekund

  /** Minutt */
  public static final float miutes = 60000.0f; // 60000 millisekund
}
