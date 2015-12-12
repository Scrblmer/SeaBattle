package GUI;

import game.Field;
import game.FieldCell;
import game.Statuses;

import java.util.ArrayList;

/**
 * Created by kurnakovik on 02.12.2015.
 */
class Display {
    public void printFieldConsole(Field field){
        String [][] strField = new String[field.getSize()][field.getSize()];
        ArrayList<ArrayList<FieldCell>> fieldCells;
        fieldCells = field.getField();
        System.out.printf("   ");
        for (int i = 0; i < field.getSize(); i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();
        for (int i = 0; i < field.getSize(); i++) {
            System.out.printf("%2d:", i);
            for (int j = 0; j < field.getSize(); j++) {
                Statuses.CellInformation eStatus = fieldCells.get(i).get(j).getCellStatus();
                String strStatus = eStatus.toString();
                        /*
                        у - убит
                        р - ранен
                        _ - не известно
                        * - зарезервированно
                        п - пусто
                        к - корабль
                        */
                switch (Statuses.CellInformation.valueOf(strStatus)) {
                    case UNKNOWN:
                        strField[i][j] = "_";
                        break;
                    case HIT:
                        strField[i][j] = "Р";
                        break;
                    case DEAD:
                        strField[i][j] = "У";
                        break;
                    case RESERVED:
                        strField[i][j] = "*";
                        break;
                    case EMPTY:
                        strField[i][j] = "П";
                        break;
                    case SHIP:
                        strField[i][j] = "К";
                        break;
                }
                System.out.printf("%4s", strField[i][j]);
            }
            System.out.println();
        }
    }
}
