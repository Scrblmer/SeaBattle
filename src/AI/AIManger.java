package AI;

import com.sun.media.sound.EmergencySoundbank;
import game.Field;
import game.FieldCell;
import game.Ship;
import game.Statuses;

import java.util.Random;

/**
 * Created by kurnakovik on 03.12.2015.
 */
public class AIManger {
    public AIManger(Field field){
        this.myField = field;
        placeShips(myField);
        this.opponentField = new Field(field.getSize());
    }
    private Field myField;
    private Field opponentField;
    private ShotCalculation gunMan;
    private int[] lastShotXY; //запоминаем куда стреляли, чтобы заполнить поле после прихода к нам ответа

    //можно ли в приватных методах работать с приватными переменнами того же класса? Это нормально с точки зрения "хорошего тона"?
    private void placeShips(Field fieldToWork){
        //todo написать внятное размещение кораблей. С логикой, а не тупо случайным образом. Стоит еще решить сколько кораблей у нас все же будет какого типа
        int shipsDeck4;
        int shipsDeck3;
        int shipsDeck2;
        int shipsDeck1;
        /* посчитаем сколько каких кораблей нужно. Исходим из
         идеи, что размеПоля = количество кораблей. Из них
        (размерПоля\2 - 1) - однопалубные
        (однопалубные-1) - двухпалубные
        (двухпалубные-1) - трехпалубные
        (трехпалубные-1) - четырехпалубные
          */
        shipsDeck1 = (int) fieldToWork.getSize() / 2 - 1;
        shipsDeck2 = shipsDeck1 - 1;
        shipsDeck3 = shipsDeck2 - 1;
        shipsDeck4 = shipsDeck3 - 1;
        //почти повторяющийся код, возможно стоит обудмать, как решить это по-другому.
        for (int i = 0; i < shipsDeck4; i++) {
            placeShip(4, fieldToWork);
        }
        for (int i = 0; i < shipsDeck3; i++) {
            placeShip(3 ,fieldToWork);
        }
        for (int i = 0; i < shipsDeck2; i++) {
            placeShip(2 ,fieldToWork);
        }
        for (int i = 0; i < shipsDeck1; i++) {
            placeShip(1 ,fieldToWork);
        }

    }

    //todo пока что корабли только горизонтальные. надо переделать для общего случая будет.(буду делать через isVertical)
    private void placeShip(int numOfDecks, Field fieldToPlace){
        Random rnd = new Random();
        int[] shipPlacement = new int[2];
        do {
            shipPlacement[0] = rnd.nextInt(fieldToPlace.getSize()-1);
            shipPlacement[1] = rnd.nextInt(fieldToPlace.getSize()-1);
        }
        while (!isValidPlace(shipPlacement, fieldToPlace, numOfDecks));
        for (int i = shipPlacement[0]; i <shipPlacement[0]+numOfDecks; i++) {
            FieldCell tmpFieldCell = fieldToPlace.getField().get(i).get(shipPlacement[1]);
            if(i!=0&&fieldToPlace.getField().get(i-1).get(shipPlacement[1]).getCellStatus()!= Statuses.CellInformation.SHIP){
                fieldToPlace.getField().get(i-1).get(shipPlacement[1]).setCellStatus(Statuses.CellInformation.RESERVED);
            }
            if(i!=fieldToPlace.getSize()-1&&fieldToPlace.getField().get(i+1).get(shipPlacement[1]).getCellStatus()!= Statuses.CellInformation.SHIP){
                fieldToPlace.getField().get(i+1).get(shipPlacement[1]).setCellStatus(Statuses.CellInformation.RESERVED);
            }
            if(shipPlacement[1]!=0&&fieldToPlace.getField().get(i).get(shipPlacement[1]-1).getCellStatus()!= Statuses.CellInformation.SHIP){
                fieldToPlace.getField().get(i).get(shipPlacement[1]-1).setCellStatus(Statuses.CellInformation.RESERVED);
            }
            if(shipPlacement[1]!=fieldToPlace.getSize()-1&&fieldToPlace.getField().get(i).get(shipPlacement[1]+1).getCellStatus()!= Statuses.CellInformation.SHIP){
                fieldToPlace.getField().get(i).get(shipPlacement[1]+1).setCellStatus(Statuses.CellInformation.RESERVED);
            }
            tmpFieldCell.setShip(new Ship(numOfDecks, shipPlacement[0], shipPlacement[1]));
            tmpFieldCell.setCellStatus(Statuses.CellInformation.SHIP);
        }

    }
    //todo пока что корабли только горизонтальные. надо переделать для общего случая будет.
    private boolean isValidPlace(int[] coordXY, Field fieldToValidate, int numOfDecks){
        if (coordXY[0]+(numOfDecks-1)>= fieldToValidate.getSize() || coordXY[1]+(numOfDecks-1)>= fieldToValidate.getSize()){
            return false;
        }
        for (int i = 0; i <numOfDecks ; i++) {
            FieldCell currentFieldCell = fieldToValidate.getField().get(i+coordXY[0]).get(coordXY[1]);
            if (currentFieldCell.getCellStatus() != Statuses.CellInformation.UNKNOWN){
                return false;
            }
        }
        return true;
    }
    public Field getOpponentField() {
        return opponentField;
    }

    public Field getMyField() {
        return myField;
    }

    public int[] makeTurn(){
        return gunMan.fireItUp(getOpponentField());
    }
}
