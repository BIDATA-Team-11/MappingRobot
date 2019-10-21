/**
 * Interface for å måle avstand til objekter.
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

 public interface DistanceMeasure {

   /**
    * Get distance to the closest object within range.
    * @return float Distance in cm.
    */
   public float getDistance();

   /**
    * Checks whether there is any object within range.
    * @return boolean True if the sensor can see an object.
    */
   public boolean objectInRange();
 }
