import java.awt.*;
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

    private static int time = 0;
    public static int anew = 0;
    private static Block choose = Block.SBLOCK;
    public static double x1 = 0;
    public static double x2 = 0;
    public static double y1 = 0;
    public static double y2 = 0;
    public static int[][] heights = null;


    public Round() {}
    public static void next() {

        //System.out.println("x1:" + x1 + ", x2:" + x2);
        Round.showMatrix();
        updateTime();

        if(anew == 0) {

            Block[] list = {Block.SBLOCK, Block.ZBLOCK, Block.LBLOCK,
                    Block.JBLOCK, Block.SQUAREBLOCK, Block.IBLOCK, Block.TBLOCK};
            anew = 1;
            Random random = new Random();
            choose = list[random.nextInt(7)];

            x1 = 3.0;
            x2 = 6.0;
            y1 = 18.0;
            y2 = 20.0;
        }
        else {

            deleteCurrent();

            y1controlDecrease();
            y2controlDecrease();
        }

        drawCurrent();

        drawGrid();
    }

    public static void falldown() {

        Round.deleteCurrent();

        int pos = 0;
        while(heights[(int) x1][pos] == 0) pos++;
        heights[(int) x1][pos] = 1;

        pos = 0;
        while(heights[(int) x1 + 1][pos] == 0) pos++;
        heights[(int) x1 + 1][pos] = 1;
        heights[(int) x1 + 1][pos + 1] = 1;

        pos = 0;
        while(heights[(int) x2][pos] == 0) pos++;
        heights[(int) x2][pos] = 1;

        for(int i = 0; i < 20; i++) Round.y1controlDecrease();
        for(int i = 0; i < 20; i++) Round.y2controlDecrease();

        Round.drawNewSBlock();
        Round.drawGrid();

        Round.anew = 0;
    }

    private static void updateTime() {

        time++;

        int minutes, seconds = time;
        for(minutes = 0; seconds >= 60; minutes++)
            seconds -= 60;

        String zero = "";
        if(seconds < 10) zero = "0";
        FrmTetris.lblCounter.setText(minutes + ":" + zero + seconds);
    }

    public static void drawGrid() {

        for(double i = 1; i < 10; i++)
            FrmTetris.graph.drawLine(i, 0, i, 20, Color.GRAY);
        for(double i = 1; i < 20; i++)
            FrmTetris.graph.drawLine(0, i,10, i, Color.GRAY);
    }

    public static void drawCurrent() {

        switch (choose) {
            case SBLOCK -> Round.drawNewSBlock();
            case ZBLOCK -> Round.drawNewSBlock();
            case LBLOCK -> Round.drawNewSBlock();
            case JBLOCK -> Round.drawNewSBlock();
            case SQUAREBLOCK -> Round.drawNewSBlock();
            case IBLOCK -> Round.drawNewSBlock();
            case TBLOCK -> Round.drawNewSBlock();
            default -> Round.drawNewSBlock();
        }
    }

    public static void deleteCurrent() {

        switch (choose) {
            case SBLOCK -> Round.deleteSBlock();
            case ZBLOCK -> Round.deleteSBlock();
            case LBLOCK -> Round.deleteSBlock();
            case JBLOCK -> Round.deleteSBlock();
            case SQUAREBLOCK -> Round.deleteSBlock();
            case IBLOCK -> Round.deleteSBlock();
            case TBLOCK -> Round.deleteSBlock();
            default -> Round.deleteSBlock();
        }
    }

    public static void drawNewSBlock() {

        Round.heights[(int) x1][(int) y1] = 1;
        Round.heights[(int) x1 + 1][(int) y1] = 1;
        Round.heights[(int) x1 + 1][(int) y1 + 1] = 1;
        Round.heights[(int) x1 + 2][(int) y2 - 1] = 1;

        FrmTetris.graph.fillRect(x1, y1, x1 + 1, y1 + 1, Color.GREEN);
        FrmTetris.graph.fillRect(x1 + 1, y1, x1 + 2, y1 + 1, Color.GREEN);
        FrmTetris.graph.fillRect(x1 + 1, y2 - 1, x1 + 2, y2, Color.GREEN);
        FrmTetris.graph.fillRect(x1 + 2, y2 - 1, x2, y2, Color.RED);
    }

    public static void deleteSBlock() {

        Round.heights[(int) x1][(int) y1] = 0;
        Round.heights[(int) x1 + 1][(int) y1] = 0;
        Round.heights[(int) x1 + 1][(int) y1 + 1] = 0;
        Round.heights[(int) x1 + 2][(int) y2 - 1] = 0;

        FrmTetris.graph.fillRect(x1, y1, x1 + 1, y1 + 1, Color.WHITE);
        FrmTetris.graph.fillRect(x1 + 1, y1, x1 + 2, y1 + 1, Color.WHITE);
        FrmTetris.graph.fillRect(x1 + 1, y2 - 1, x1 + 2, y2, Color.WHITE);
        FrmTetris.graph.fillRect(x1 + 2, y2 - 1, x2, y2, Color.WHITE);
    }

    public static void x1increase() {

        if(x1 > 6) return;
        x1++;
    }

    public static void x2increase() {

        if(x2 > 9) return;
        x2++;
    }

    public static void y1increase() {

        if((y1 + 1) > 18) return;
        y1++;
    }

    public static void y2increase() {

        if((y2 + 1) > 19) return;
        y2++;
    }

    public static void x1decrease() {

        if(x1 < 1) return;
        x1--;
    }

    public static void x2decrease() {

        if(x2 < 4) return;
        x2--;
    }

    public static void y1decrease() {

        if((y1 - 1) < 1) return;
        y1--;
    }

    public static void y2decrease() {

        if((y2 - 1) < 2) return;
        y2--;
    }

    public static void y1controlDecrease() {

        //if(Round.heights[(int) x1] >= y1) return;
        y1--;
    }

    public static void y2controlDecrease() {

        //if(Round.heights[(int) x2] >= y2) return;
        y2--;
        Round.heights[(int) x1][(int) y1] = 1;
    }

    public static void showMatrix() {

        for(int i = 19; i > 0; i--) {
            for(int j = 9; j > 0; j--)
                System.out.print(Round.heights[j][i] + " ");
            System.out.print("\n");
        }

        System.out.println("-----------------------------");
    }
}
