import java.awt.*;

public class Round {

    private static int time = 0;
    private static int anew = 0;

    private static double x1 = 0;
    private static double x2 = 0;
    private static double y1 = 0;
    private static double y2 = 0;

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

    public Round() {}
    public static void next() {

        updateTime();
        if(anew == 0) {

            anew = 1;
            x1 = 5.0;
            x2 = 6.0;
            y1 = 19.0;
            y2 = 20.0;

            Round.drawNewSBlock();
        }
        else {

            FrmTetris.graph.clean();

            y1--;
            y2--;
            Round.drawNewSBlock();
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
}
