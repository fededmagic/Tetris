import java.awt.*;

public class IBlock extends CurrentBlock {

    IBlock() { super(); }




    //-- -- changing direction -- --//

    public void changeOrientation(int spin) {

        if(blockOrientation == 0 && Round.y1 > 16) return;

        if(blockOrientation == 1 && Round.x1 < 2) Round.x1 = 1;
        if(blockOrientation == 1 && Round.x1 > 7) Round.x1 = 7;

        if((IBlock.blockOrientation == 1) && (Round.heights[(int) Round.x1 - 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y1] != 0)) {

            return;
        }

        if((blockOrientation == 0) && (Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1 + 1] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1 + 2] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1 + 3] != 0)) {

            return;
        }

        blockOrientation = (blockOrientation + 1) % 2;

        if(blockOrientation == 0) {

            Round.x1decrease();
            Round.x2decrease();
        }

        if(blockOrientation == 1) {

            Round.x1increase();
            Round.x2increase();
        }
    }




    //-- -- general functions -- --//

    public void drawNewBlock() {

        if(blockOrientation == 0) {

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

    public void deleteBlock() {

        if(blockOrientation == 0) {

            ZeroIBlockInMatrix(0);
            ZeroPaintIBlock(Color.LIGHT_GRAY);
        }
        else {

            OneIBlockInMatrix(0);
            OnePaintIBlock(Color.LIGHT_GRAY);
        }
    }

    public void controlLateralMovementBlock(String direction) {

        if(blockOrientation == 0) { ZeroControlLateralMovementIBlock(direction); }
        else { OneControlLateralMovementIBlock(direction); }
    }




    //-- -- lateral movement controls -- --//

    public static void ZeroControlLateralMovementIBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            System.out.println("x1:" + Round.x1);
            if(Round.x1 > 5) return;
            if(Round.heights[(int) Round.x1 + 4][(int) Round.y1] == 0) {

                Round.x1increase();
                Round.x2increase();

                OneFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 == 0) return;
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0) {

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
            if(Round.heights[(int) Round.x1 + 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 3] == 0) {

                Round.x1increase();
                Round.x2increase();

                OneFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 == 0) return;
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 2] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 3] == 0) {

                Round.x1decrease();
                Round.x2decrease();

                OneFallingControls();
            }
        }
    }




    //-- -- falling controls -- --//

    public static void ZeroFallingControls() {


        if(Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 3][(int) Round.y1] != 0) {

            Round.y1++;
            Round.y2++;

            ZeroIBlockInMatrix(4);
            ZeroPaintIBlock(Color.CYAN);

            IBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void OneFallingControls() {

        if(Round.heights[(int) Round.x1][(int) Round.y1] != 0) {

            Round.y1++;
            Round.y2++;

            OneIBlockInMatrix(4);
            OnePaintIBlock(Color.CYAN);

            IBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }




    //-- -- painting the block -- --//

    public static void ZeroPaintIBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y1, Round.x1 + 3, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 3, Round.y1, Round.x1 + 4, Round.y1 + 1, color);
    }

    public static void OnePaintIBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 2, Round.x1 + 1, Round.y1 + 3, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 3, Round.x1 + 1, Round.y1 + 4, color);
    }




    //-- -- Modifying matrix -- --//

    public static void ZeroIBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 2][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 3][(int) Round.y1] = value;
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
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1 + 2] = value;
            Round.heights[(int) Round.x1][(int) Round.y1 + 3] = value;
        }
        catch(IndexOutOfBoundsException e) {
/*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }
}
