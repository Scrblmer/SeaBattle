package game;

/**
 * Created by kurnakovik on 02.12.2015.
 */
public class FieldCell {
    public FieldCell(){
        this.cellStatus = Statuses.CellInformation.UNKNOWN;
        this.ship = new Ship();
    }
    public Statuses.CellInformation getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(Statuses.CellInformation cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    private Statuses.CellInformation cellStatus;
    private Ship ship;
}
