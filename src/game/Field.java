package game;

import java.util.ArrayList;

/**
 * Created by kurnakovik on 02.12.2015.
 */
public class Field {
    public Field(int size)
    {
        this.size = size;
        this.field = new ArrayList<ArrayList<FieldCell>>();
        for (int i = 0; i < size; i++) {
            ArrayList<FieldCell> row = new ArrayList<FieldCell>();
            for (int j = 0; j < size; j++){
                row.add(new FieldCell());
            }
            field.add(row);
        }
    }
    private int size;
    private ArrayList<ArrayList<FieldCell>> field;

    public int getSize() {
        return size;
    }
    public ArrayList<ArrayList<FieldCell>> getField() {
        return field;
    }
    public void placeShip(Ship ship){
        //TODO написать логику размещения кораблей Перенесено в Ai
    }
    private boolean isValidShipPlace(int fieldSize, Ship shipToValidate){
        //todo написать валидатор. Края карты, другие корабли. Перенесено в Ai
        return false;
    }
}
