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
    private static int anew = 0;
    private static Block choose = Block.SBLOCK;
    private static double x1 = 0;
    private static double x2 = 0;
    private static double y1 = 0;
    private static double y2 = 0;


    public Round() {}
    public static void next() {

        updateTime();

        System.out.println(choose);
        if(anew == 0) {

            Block[] list = {Block.SBLOCK, Block.ZBLOCK, Block.LBLOCK,
                    Block.JBLOCK, Block.SQUAREBLOCK, Block.IBLOCK, Block.TBLOCK};
            anew = 1;
            Random random = new Random();
            choose = list[random.nextInt(7)];

            x1 = 5.0;
            x2 = 6.0;
            y1 = 19.0;
            y2 = 20.0;
        }
        else {

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

            FrmTetris.graph.clean();
            y1--;
            y2--;
        }

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

    private static void updateTime() {

        time++;

        int minutes, seconds = time;
        for(minutes = 0; seconds >= 60; minutes++)
            seconds -= 60;

        String zero = "";
        if(seconds < 10) zero = "0";
        FrmTetris.lblCounter.setText(minutes + ":" + zero + seconds);
    }


    public static void drawNewSBlock() {

        FrmTetris.graph.fillRect(x1 - 1, y1 - 1, x2 - 1, y2 - 1, Color.GREEN);
        FrmTetris.graph.fillRect(x1, y1 - 1, x2, y2 - 1, Color.GREEN);
        FrmTetris.graph.fillRect(x1, y1, x2, y2, Color.GREEN);
        FrmTetris.graph.fillRect(x1 + 1, y1, x2 + 1, y2, Color.GREEN);
    }

    public static void deleteSBlock() {

        FrmTetris.graph.fillRect(x1 - 1, y1 - 1, x2 - 1, y2 - 1, Color.LIGHT_GRAY);
        FrmTetris.graph.fillRect(x1, y1 - 1, x2, y2 - 1, Color.LIGHT_GRAY);
        FrmTetris.graph.fillRect(x1, y1, x2, y2, Color.LIGHT_GRAY);
        FrmTetris.graph.fillRect(x1 + 1, y1, x2 + 1, y2, Color.LIGHT_GRAY);
    }
}
