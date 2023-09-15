import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Round {

    public enum Block {

        SBLOCK,         //   ##
                        //  ##

        ZBLOCK,         //  ##
                        //   ##

        LBLOCK,         //  ###
                        //  #

        JBLOCK,         //  #
                        //  ###

        SQUAREBLOCK,    //  ##
                        //  ##

        IBLOCK,         //  ####
                        //

        TBLOCK,         //  ###
                        //   #
    }

    private static int removedLines = 0;
    private static int time = 0;
    public static int anew = 0;
    public static CurrentBlock curr = null;
    public static double x1 = 0;
    public static double y1 = 0;
    public static int[][] heights = null;
    public static final double X1_SV = 4.0;
    public static final double Y1_SV = 18.0;


    public Round() {}
    public static void next() {

        Round.showMatrix();

        if(anew == 0) {

            //if(x1 == 17) //you loose (?)

            Block[] list = {Block.SBLOCK, Block.SQUAREBLOCK, Block.ZBLOCK, Block.IBLOCK, Block.TBLOCK};
            anew = 1;
            Random random = new Random();
            Block choose = list[random.nextInt(5)];

            //choose = Block.TBLOCK;
            switch (choose) {
                case SBLOCK -> curr = new SBlock();
                case SQUAREBLOCK -> curr = new SquareBlock();
                case ZBLOCK -> curr = new ZBlock();
                case IBLOCK -> curr = new IBlock();
                case TBLOCK -> curr = new TBlock();
            }

            x1 = X1_SV;
            y1 = Y1_SV;

            if(choose == Block.IBLOCK) { y1++; x1--; }
        }
        else {

            curr.deleteBlock();

            y1controlDecrease();

            lineControl();
        }

        curr.drawNewBlock();

        drawGrid();
    }

    public static void falldown() {

        while(anew == 1) {

            curr.deleteBlock();
            drawGrid();

            y1controlDecrease();

            curr.drawNewBlock();
            drawGrid();
        }

        resetOrientation();
    }

    public static void resetOrientation() {

        CurrentBlock.setBlockOrientation(0);
    }

    private static void lineControl() {


        ArrayList<Integer> removedLines = new ArrayList<>();
        for(int j = 0; j < 20; j++) {

            int full = 0;
            for(int i = 0; i < 10; i++) {

                if(Round.heights[i][j] != 0) full++;
                if(full == 10) {

                    Round.removedLines++;
                    FrmTetris.lblLine.setText("Score: " + Round.removedLines);
                    removedLines.add(j);
                }
            }
        }
        removeLine(removedLines);
    }

    private static void removeLine(ArrayList<Integer> removedLines) {

        for(int position: removedLines) {

            for (int i = position; i < 19; i++)
                for (int j = 0; j < 10; j++)
                    Round.heights[j][i] = Round.heights[j][i + 1];

            for (int j = 0; j < 10; j++)
                Round.heights[j][19] = 0;
        }
        redraw();
    }

    private static void redraw() {

        System.out.println("REDRAW");
        for(int i = 19; i >= 0; i--)
            for(int j = 0; j < 10; j++)
                FrmTetris.graph.fillRect((double) j, (double) -(i - 19), j + 1,
                        (double) -(i - 19) + 1, getColor(Round.heights[j][-(i - 19)]));

        drawGrid();
    }

    private static Color getColor(int val) {

        Color[] list = {Color.LIGHT_GRAY, Color.GREEN,
                Color.YELLOW, Color.RED,
                Color.CYAN, Color.MAGENTA};
        return list[val];
    }

    public static void updateTime() {

        time++;

        int minutes, seconds = time;
        for(minutes = 0; seconds >= 60; minutes++)
            seconds -= 60;

        String zero = "";
        if(seconds < 10) zero = "0";
        FrmTetris.lblCounter.setText(minutes + ":" + zero + seconds);
    }

    public static void restartGame() {

        FrmTetris.graph.clean();
        drawGrid();

        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 20; j++)
                Round.heights[i][j] = 0;

        Round.anew = 0;
        Round.time = 0;
        Round.removedLines = 0;

        FrmTetris.btnStartStop.setText("START");
        FrmTetris.lblStatus.setText("");
        FrmTetris.lblLine.setText("Score: 0");
        FrmTetris.lblCounter.setText("0:00");

        FrmTetris.timer.restart();
    }

    public static void drawGrid() {

        for(double i = 1; i < 10; i++)
            FrmTetris.graph.drawLine(i, 0, i, 20, Color.GRAY);
        for(double i = 1; i < 20; i++)
            FrmTetris.graph.drawLine(0, i,10, i, Color.GRAY);
    }

    public static void x1increase() {

        if(x1 > 9) return;
        x1++;
    }

    public static void x1decrease() {

        if(x1 < 1) return;
        x1--;
    }

    public static void y1controlDecrease() {

        if(y1 < 1) {

            Round.anew = 0;
            return;
        }
        y1--;
    }

    public static void showMatrix() {

        for(int i = 19; i >= 0; i--) {
            for(int j = 0; j < 10; j++)
                System.out.print(Round.heights[j][i] + " ");
            System.out.print("\n");
        }

        System.out.println("-----------------------------");
    }
}
