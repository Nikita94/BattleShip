package com.battleship.Client.groundandship;

/**
 * Created by nikita on 27.11.2015.
 */
public class SettingShips {
    static boolean labels[];
    int fourDeckerLength;

    SettingShips() {
       labels = new boolean[100];
        for (int i = 0; i < 100; i++) labels[i] = false;
        fourDeckerLength = 0;
    }
   boolean setFourDecker (int number){
       if (fourDeckerLength == 4) {
           return false;
       }
       else {
           return true;
       }
   }
}
