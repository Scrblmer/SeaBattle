package game;

/**
 * Created by kurnakovik on 02.12.2015.
 */
public class Ship {
    public Ship(){}

    public Ship(int deckCount, int xCoord, int yCoord){
        this.deckCount = deckCount;
        this.Status = Statuses.ShipStatus.ALIVE;
        this.decksStatuses = new Statuses.ShipStatus[this.deckCount];
        for (int i = 0; i < this.deckCount; i++) {
            this.decksStatuses[i] = Statuses.ShipStatus.ALIVE;
        }
        this.health = deckCount;
        isVertical = true;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    private int deckCount;
    private Statuses.ShipStatus Status;
    private Statuses.ShipStatus [] decksStatuses;
    private int health;
    private boolean isVertical;
    private int xCoord;
    private int yCoord;

    public int getYCoord() {
        return yCoord;
    }

    public int getXCoord() {
        return xCoord;
    }

    public int getDeckCount() {
        return deckCount;
    }

    public Statuses.ShipStatus[] getDecksStatuses() {
        return decksStatuses;
    }

    public void setDecksStatuses(Statuses.ShipStatus[] decksStatuses) {
        this.decksStatuses = decksStatuses;
    }

    public Statuses.ShipStatus deadOrAlive(){
        boolean alive = false;
        for (int i = 0; i < this.decksStatuses.length; i++) {
            String strStatus = decksStatuses[i].toString();
            if(Statuses.ShipStatus.valueOf(strStatus)==Statuses.ShipStatus.ALIVE){
                alive = true;
            }
        }
        return alive ? Statuses.ShipStatus.ALIVE : Statuses.ShipStatus.DEAD;
    }
}
