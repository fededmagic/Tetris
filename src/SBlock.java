import java.awt.*;

public class SBlock {

    public static void drawNewSBlock() {

        //System.out.println("x1:" + x1 + ", y1:" + y1);
        //System.out.println("Round.heights[(int) x1][(int) y1]: " + Round.heights[(int) x1][(int) y1]);

        if(Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.x1 + 1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y2 - 1] != 0) {

            Round.y1++;
            Round.y2++;

            Round.heights[(int) Round.x1][(int) Round.y1] = 1;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = 1;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = 1;
            Round.heights[(int) Round.x1 + 2][(int) Round.y2 - 1] = 1;

            paintSBlock(Color.GREEN);

            Round.anew = 0;
            return;
        }

        Round.heights[(int) Round.x1][(int) Round.y1] = 1;
        Round.heights[(int) Round.x1 + 1][(int) Round.y1] = 1;
        Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = 1;
        Round.heights[(int) Round.x1 + 2][(int) Round.y2 - 1] = 1;

        paintSBlock(Color.GREEN);
    }

    public static void deleteSBlock() {

        Round.heights[(int) Round.x1][(int) Round.y1] = 0;
        Round.heights[(int) Round.x1 + 1][(int) Round.y1] = 0;
        Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = 0;
        Round.heights[(int) Round.x1 + 2][(int) Round.y2 - 1] = 0;

        paintSBlock(Color.WHITE);
    }

    public static void paintSBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y2 - 1, Round.x1 + 2, Round.y2, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y2 - 1, Round.x2, Round.y2, color);
    }
}
