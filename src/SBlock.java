import java.awt.*;

public class SBlock {

    public static void drawNewSBlock() {

        System.out.println("x1:" + Round.x1 + ", y1:" + Round.y1);
        System.out.println("x2:" + Round.x2 + ", y1:" + Round.y2);
        System.out.println(Round.heights[(int) Round.x1][(int) Round.y1] + ";" +
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] + ";" +
                Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] + ";" +
                Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1]);

        fallingControls();

        SBlockInMatrix(1);

        paintSBlock(Color.GREEN);
    }

    public static void deleteSBlock() {

        SBlockInMatrix(0);
        paintSBlock(Color.LIGHT_GRAY);
    }

    public static void controlLateralMovementSBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x2 > 9) return;
            if(Round.heights[(int) Round.x1 + 2][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 3][(int) Round.y1 + 1] == 0) {

                Round.x1increase();
                Round.x2increase();

                fallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 < 1) return;
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1][(int) Round.y1 + 1] == 0) {

                Round.x1decrease();
                Round.x2decrease();

                fallingControls();
            }
        }
    }

    public static void fallingControls() {

        if(Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] != 0) {

            System.out.println("CONTROL");
            Round.y1++;
            Round.y2++;

            SBlockInMatrix(1);
            paintSBlock(Color.GREEN);

            Round.anew = 0;
        }
    }

    public static void paintSBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y2 - 1, Round.x1 + 2, Round.y2, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y2 - 1, Round.x2, Round.y2, color);
    }

    public static void SBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] = value;
        }
        catch(IndexOutOfBoundsException e) {

            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");
        }
    }
}
