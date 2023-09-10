import java.awt.*;

public class SBlock extends CurrentBlock {

    SBlock() { super(); }




    //-- -- changing direction -- --//

    public void changeOrientation(int spin) {

        if(blockOrientation == 0 && Round.y1 > 17) return;
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
            ZeroSBlockInMatrix(1);
            ZeroPaintSBlock(Color.GREEN);
        }
        else {

            OneFallingControls();
            OneSBlockInMatrix(1);
            OnePaintSBlock(Color.GREEN);
        }
    }

    public void deleteBlock() {

        if(blockOrientation == 0) {

            ZeroSBlockInMatrix(0);
            ZeroPaintSBlock(Color.LIGHT_GRAY);
        }
        else {

            OneSBlockInMatrix(0);
            OnePaintSBlock(Color.LIGHT_GRAY);
        }
    }

    public void controlLateralMovementBlock(String direction) {

        if(blockOrientation == 0) { ZeroControlLateralMovementSBlock(direction); }
        else { OneControlLateralMovementSBlock(direction); }
    }




    //-- -- lateral movement controls -- --//

    public static void ZeroControlLateralMovementSBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 8) return;
            if(Round.heights[(int) Round.x1 + 2][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 3][(int) Round.y1 + 1] == 0) {

                Round.x1increase();
                Round.x2increase();

                ZeroFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 < 1) return;
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1][(int) Round.y1 + 1] == 0) {

                Round.x1decrease();
                Round.x2decrease();

                ZeroFallingControls();
            }
        }
    }


    //      !! previous problems of the functions fixed !!       //
    public static void OneControlLateralMovementSBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            System.out.println("x1:" + Round.x1);
            if(Round.x1 > 7) return;
            if(Round.heights[(int) Round.x1 + 2][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] == 0) {

                Round.x1increase();
                Round.x2increase();

                OneFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 == 0) return;
            if(Round.heights[(int) Round.x1][(int) Round.y1] == 0 &&
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

        if(Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] != 0) {

            Round.y1++;
            Round.y2++;

            ZeroSBlockInMatrix(1);
            ZeroPaintSBlock(Color.GREEN);

            SBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void OneFallingControls() {

        if(Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1 + 1] != 0) {

            Round.y1++;
            Round.y2++;

            OneSBlockInMatrix(1);
            OnePaintSBlock(Color.GREEN);

            SBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }




    //-- -- painting the block -- --//

    public static void ZeroPaintSBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 1, Round.x1 + 2, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y1 + 1, Round.x1 + 3, Round.y1 + 2, color);
    }

    public static void OnePaintSBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 1, Round.x1 + 2, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 2, Round.x1 + 1, Round.y1 + 3, color);
    }




    //-- -- Modifying matrix -- --//

    public static void ZeroSBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] = value;
        }
        catch(IndexOutOfBoundsException e) {

            /*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }

    public static void OneSBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
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
