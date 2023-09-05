import java.awt.*;

public class IBlock {

    public static int iblockOrientation = 0;   //horizontal = 0, vertical = 1




    //-- -- changing direction -- --//

    public static void changeIBlockOrientation() {

        if(iblockOrientation == 0 && Round.y1 > 17) return;
        iblockOrientation = (iblockOrientation + 1) % 2;


        if(iblockOrientation == 0) {

            Round.x1decrease();
            Round.x2decrease();
        }
        if(iblockOrientation == 1) {

            if(Round.x1 == 9) Round.x1 = 8;
            Round.x1increase();
            Round.x2increase();
        }
    }




    //-- -- general functions -- --//

    public static void drawNewIBlock() {

        if(iblockOrientation == 0) {

            ZeroFallingControls();
            ZeroIBlockInMatrix(4);
            ZeroPaintIBlock(Color.CYAN);
        }
        else {

            OneFallingControls();
            OneIBlockInMatrix(4);
            OnePaintIBlock(Color.CYAN);
        }
    }

    public static void deleteIBlock() {

        if(iblockOrientation == 0) {

            ZeroIBlockInMatrix(0);
            ZeroPaintIBlock(Color.LIGHT_GRAY);
        }
        else {

            OneIBlockInMatrix(0);
            OnePaintIBlock(Color.LIGHT_GRAY);
        }
    }

    public static void controlLateralMovementIBlock(String direction) {

        if(iblockOrientation == 0) { ZeroControlLateralMovementIBlock(direction); }
        else { OneControlLateralMovementIBlock(direction); }
    }




    //-- -- lateral movement controls -- --//

    public static void ZeroControlLateralMovementIBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            System.out.println("x1:" + Round.x1);
            if(Round.x1 > 6) return;
            if(Round.heights[(int) Round.x1 + 3][(int) Round.y1] == 0) {

                Round.x1increase();
                Round.x2increase();

                OneFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if((Round.x1 - 1) == 0) return;
            if(Round.heights[(int) Round.x1 - 2][(int) Round.y1] == 0) {

                Round.x1decrease();
                Round.x2decrease();

                OneFallingControls();
            }
        }
    }


    public static void OneControlLateralMovementIBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            System.out.println("x1:" + Round.x1);
            if(Round.x1 > 8) return;
            if(Round.heights[(int) Round.x1 + 1][(int) Round.y1 - 1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] == 0) {

                Round.x1increase();
                Round.x2increase();

                OneFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 == 0) return;
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1 - 1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 2] == 0) {

                Round.x1decrease();
                Round.x2decrease();

                OneFallingControls();
            }
        }
    }




    //-- -- falling controls -- --//

    public static void ZeroFallingControls() {


        if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y1] != 0) {

            Round.y1++;
            Round.y2++;

            ZeroIBlockInMatrix(4);
            ZeroPaintIBlock(Color.CYAN);

            IBlock.iblockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void OneFallingControls() {

        if(Round.y1 == 0 || Round.heights[(int) Round.x1][(int) Round.y1 - 2] != 0) {

            Round.y1++;
            Round.y2++;

            OneIBlockInMatrix(4);
            OnePaintIBlock(Color.CYAN);

            IBlock.iblockOrientation = 0;
            Round.anew = 0;
        }
    }




    //-- -- painting the block -- --//

    public static void ZeroPaintIBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1 - 1, Round.y1, Round.x1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y1, Round.x1 + 3, Round.y1 + 1, color);
    }

    public static void OnePaintIBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1 - 1, Round.x1 + 1, Round.y1, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 2, Round.x1 + 1, Round.y1 + 3, color);
    }




    //-- -- Modifying matrix -- --//

    public static void ZeroIBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1 - 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 2][(int) Round.y1] = value;
        }
        catch(IndexOutOfBoundsException e) {

            /*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }

    public static void OneIBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1][(int) Round.y1 - 1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1 + 2] = value;
        }
        catch(IndexOutOfBoundsException e) {
/*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }
}
