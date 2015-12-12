package game;

/**
 * Created by kurnakovik on 02.12.2015.
 */
public class Statuses {
    public enum ShipStatus {INJURED, DEAD, ALIVE} //вынесены в два разных enum'a т.к. использоване в разных объектах.
    public enum FireResult {INJURED, DEAD, MISS}
    public enum CellInformation{UNKNOWN, HIT, DEAD, RESERVED, EMPTY, SHIP}
}
