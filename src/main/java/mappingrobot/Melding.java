package mappingrobot;
/**
 * @author Stian Selvåg
 * @author Herman Aagaard
 * @author Henrik Hafsø
 * @author Joakim Skogål Langvand
 * @author Erling Sletta
 * @author Torbjørn Øverås
 * @author Group 11, dataingeniør NTNU, første semester.
 * @version 2.0.0
 */
public class Melding {
  public final Integer vinkel;
  public final Float distance;
  /**
   * Method sends a message to main
   * @param vinkel
   * @param distance
   */
  public Melding(Integer vinkel, Float distance) {
    this.vinkel = vinkel;
    this.distance = distance;
  }
}
