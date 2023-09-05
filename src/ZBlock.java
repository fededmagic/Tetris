import java.awt.*;

public class ZBlock {

    public static int zblockOrientation = 0;   //horizontal = 0, vertical = 1




    //-- -- changing direction -- --//

    public static void changeZBlockOrientation() {

        if(zblockOrientation == 0 && Round.y1 > 17) return;
        zblockOrientation = (zblockOrientation + 1) % 2;


        if(zblockOrientation == 0) {

            Round.x1decrease();
            Round.x2decrease();
        }
        if(zblockOrientation == 1) {

            Round.x1increase();
            Round.x2increase();
        }
    }




    //-- -- general functions -- --//

    public static void drawNewZBlock() {

        if(zblockOrientation == 0) {

            ZeroFallingControls();
            ZeroZBlockInMatrix(3);
            ZeroPaintZBlock(Color.RED);
        }
        else {

            OneFallingControls();
            OneZBlockInMatrix(3);
            OnePaintZBlock(Color.RED);
        }
    }

    public static void deleteZBlock() {

        if(zblockOrientation == 0) {

            ZeroZBlockInMatrix(0);
            ZeroPaintZBlock(Color.LIGHT_GRAY);
        }
        else {

            OneZBlockInMatrix(0);
            OnePaintZBlock(Color.LIGHT_GRAY);
        }
    }

    public static void controlLateralMovementZBlock(String direction) {

        if(zblockOrientation == 0) { ZeroControlLateralMovementZBlock(direction); }
        else { OneControlLateralMovementZBlock(direction); }
    }




    //-- -- lateral movement controls -- --//

    public static void ZeroControlLateralMovementZBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 6) return;
            if(Round.heights[(int) Round.x1 + 3][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] == 0) {

                Round.x1increase();
                Round.x2increase();

                ZeroFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 < 1) return;
            if(Round.heights[(int) Round.x1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 1] == 0) {

                Round.x1decrease();
                Round.x2decrease();

                ZeroFallingControls();
            }
        }
    }


    //      !! previous problems of the functions fixed !!       //
    public static void OneControlLateralMovementZBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            System.out.println("x1:" + Round.x1);
            if(Round.x1 > 7) return;
            if(Round.heights[(int) Round.x1 + 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 2] == 0) {

                Round.x1increase();
                Round.x2increase();

                OneFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 == 0) return;
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1][(int) Round.y1 + 2] == 0) {

                Round.x1decrease();
                Round.x2decrease();

                OneFallingControls();
            }
        }
    }




    //-- -- falling controls -- --//

    public static void ZeroFallingControls() {

        if(Round.heights[(int) Round.x1][(int) Round.y1 + 1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y1] != 0) {

            Round.y1++;
            Round.y2++;

            ZeroZBlockInMatrix(1);
            ZeroPaintZBlock(Color.GREEN);

            ZBlock.zblockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void OneFallingControls() {

        if(Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] != 0) {

            Round.y1++;
            Round.y2++;

            OneZBlockInMatrix(1);
            OnePaintZBlock(Color.GREEN);

            ZBlock.zblockOrientation = 0;
            Round.anew = 0;
        }
    }




    //-- -- painting the block -- --//

    public static void ZeroPaintZBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 1, Round.x1 + 2, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y1, Round.x1 + 3, Round.y1 + 1, color);
    }

    public static void OnePaintZBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 2, Round.x1 + 2, Round.y1 + 3, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 1, Round.x1 + 2, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
    }




    //-- -- Modifying matrix -- --//

    public static void ZeroZBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1 + 2][(int) Round.y1] = value;
        }
        catch(IndexOutOfBoundsException e) {

            /*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }

    public static void OneZBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
        }
        catch(IndexOutOfBoundsException e) {
/*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }
}
