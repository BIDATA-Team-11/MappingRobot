package mappingrobot;
/**
 * @author Stian Selv�g
 * @author Herman Aagaard
 * @author Henrik Hafs�
 * @author Joakim Skog�l Langvand
 * @author Erling Sletta
 * @author Torbj�rn �ver�s
 * @author Group 11, dataingeni�r NTNU, f�rste semester.
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
