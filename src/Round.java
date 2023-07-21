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
    public static final double X1_SV = 3.0;
    public static final double X2_SV = 6.0;
    public static final double Y1_SV = 18.0;
    public static final double Y2_SV = 20.0;


    public Round() {}
    public static void next() {

        Round.showMatrix();
        updateTime();

        if(anew == 0) {

            Block[] list = {Block.SBLOCK, Block.ZBLOCK, Block.LBLOCK,
                    Block.JBLOCK, Block.SQUAREBLOCK, Block.IBLOCK, Block.TBLOCK};
            anew = 1;
            Random random = new Random();
            choose = list[random.nextInt(7)];

            x1 = X1_SV;
            x2 = X2_SV;
            y1 = Y1_SV;
            y2 = Y2_SV;
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

        while(anew == 1) {

            deleteCurrent();
            drawGrid();

            y1controlDecrease();
            y2controlDecrease();

            drawCurrent();
            drawGrid();
        }
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
            case SBLOCK -> SBlock.drawNewSBlock();
            case ZBLOCK -> SBlock.drawNewSBlock();
            case LBLOCK -> SBlock.drawNewSBlock();
            case JBLOCK -> SBlock.drawNewSBlock();
            case SQUAREBLOCK -> SBlock.drawNewSBlock();
            case IBLOCK -> SBlock.drawNewSBlock();
            case TBLOCK -> SBlock.drawNewSBlock();
            default -> SBlock.drawNewSBlock();
        }
    }

    public static void deleteCurrent() {

        switch (choose) {
            case SBLOCK -> SBlock.deleteSBlock();
            case ZBLOCK -> SBlock.deleteSBlock();
            case LBLOCK -> SBlock.deleteSBlock();
            case JBLOCK -> SBlock.deleteSBlock();
            case SQUAREBLOCK -> SBlock.deleteSBlock();
            case IBLOCK -> SBlock.deleteSBlock();
            case TBLOCK -> SBlock.deleteSBlock();
            default -> SBlock.deleteSBlock();
        }
    }

    public static void x1increase() {

        if(x1 > 6) return;
        x1++;
    }

    public static void x2increase() {

        if(x2 > 9) return;
        x2++;
    }

    public static void x1decrease() {

        if(x1 < 1) return;
        x1--;
    }

    public static void x2decrease() {

        if(x2 < 4) return;
        x2--;
    }

    public static void y1controlDecrease() {

        if(y1 < 1) {

            Round.anew = 0;
            return;
        }
        y1--;
    }

    public static void y2controlDecrease() {

        if(y2 < 3) {

            Round.anew = 0;
            return;
        }
        y2--;
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
