package GUI;

import AI.AIManger;
import game.Field;

/**
 * Created by kurnakovik on 02.12.2015.
 */
public class GameManager {
    public static void main(String args[]){
        Field field = new Field(10);
        Display disp = new Display();
        System.out.println("Исходное поле:");
        disp.printFieldConsole(field);
        AIManger computerPlayer = new AIManger(field);
        System.out.println("После размещения кораблей:");
        disp.printFieldConsole(computerPlayer.getMyField());
    }
}
