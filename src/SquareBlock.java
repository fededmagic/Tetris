import java.awt.*;

public class SquareBlock {

    public static int sblockOrientation = 0;   //horizontal = 0, vertical = 1




    //-- -- changing direction -- --//

    public static void changeSquareBlockOrientation() {

        return;
    }




    //-- -- general functions -- --//

    public static void drawNewSquareBlock() {

        FallingControls();
        SquareBlockInMatrix(2);
        PaintSquareBlock(Color.YELLOW);
    }

    public static void deleteSquareBlock() {

        SquareBlockInMatrix(0);
        PaintSquareBlock(Color.LIGHT_GRAY);
    }

    public static void controlLateralMovementSquareBlock(String direction) {

        ControlLateralMovementSquareBlock(direction);
    }




    //-- -- lateral movement controls -- --//

    public static void ControlLateralMovementSquareBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 7) return;
            if(Round.heights[(int) Round.x1 + 2][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] == 0) {

                Round.x1increase();
                Round.x2increase();

                FallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 < 1) return;
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 1] == 0) {

                Round.x1decrease();
                Round.x2decrease();

                FallingControls();
            }
        }
    }




    //-- -- falling controls -- --//

    public static void FallingControls() {

        if(Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0) {

            Round.y1++;
            Round.y2++;

            SquareBlockInMatrix(1);
            PaintSquareBlock(Color.GREEN);

            SquareBlock.sblockOrientation = 0;
            Round.anew = 0;
        }
    }




    //-- -- painting the block -- --//

    public static void PaintSquareBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y2 - 1, Round.x1 + 2, Round.y2, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y2 - 1, Round.x1 + 1, Round.y2, color);
    }




    //-- -- Modifying matrix -- --//

    public static void SquareBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1 + 1] = value;
        }
        catch(IndexOutOfBoundsException e) {

            /*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }

}
