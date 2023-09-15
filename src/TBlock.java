import java.awt.*;

public class TBlock extends CurrentBlock {

    TBlock() { super(); }




    //-- -- changing direction -- --//

    public void changeOrientation(int spin) {

        //spin = 0 -> right --- spin = 1 -> left

        if(blockOrientation == 0 && Round.y1 > 17) return;

        if(spin == 0 && Round.x1 > 7 && (blockOrientation == 1 || blockOrientation == 3)) Round.x1--;

        if(spin == 0) blockOrientation = (blockOrientation + 1) % 4;
        else blockOrientation = (blockOrientation - 1 + 4) % 4;

        System.out.println("X1:" + Round.x1);
        //if(Round.x1 == 0 && blockOrientation == 1) Round.x1--;
    }




    //-- -- general functions -- --//

    public void drawNewBlock() {

        if(blockOrientation == 0) {

            ZeroFallingControls();
            ZeroTBlockInMatrix(5);
            ZeroPaintTBlock(Color.MAGENTA);
        }
        else if(blockOrientation == 1) {

            OneFallingControls();
            OneTBlockInMatrix(5);
            OnePaintTBlock(Color.MAGENTA);
        }
        else if(blockOrientation == 2) {

            TwoFallingControls();
            TwoTBlockInMatrix(5);
            TwoPaintTBlock(Color.MAGENTA);
        }
        else {

            ThreeFallingControls();
            ThreeTBlockInMatrix(5);
            ThreePaintTBlock(Color.MAGENTA);
        }
    }

    public void deleteBlock() {

        if(blockOrientation == 0) {

            ZeroTBlockInMatrix(0);
            ZeroPaintTBlock(Color.LIGHT_GRAY);
        }
        else if(blockOrientation == 1) {

            OneTBlockInMatrix(0);
            OnePaintTBlock(Color.LIGHT_GRAY);
        }
        else if(blockOrientation == 2) {

            TwoTBlockInMatrix(0);
            TwoPaintTBlock(Color.LIGHT_GRAY);
        }
        else {

            ThreeTBlockInMatrix(0);
            ThreePaintTBlock(Color.LIGHT_GRAY);
        }
    }

    public void controlLateralMovementBlock(String direction) {

        if(blockOrientation == 0) { ZeroControlLateralMovementTBlock(direction); }
        else if(blockOrientation == 1) { OneControlLateralMovementTBlock(direction); }
        else if(blockOrientation == 2) { TwoControlLateralMovementTBlock(direction); }
        else { ThreeControlLateralMovementTBlock(direction); }
    }




    //-- -- lateral movement controls -- --//

    public static void ZeroControlLateralMovementTBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 6) return;
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

    public static void OneControlLateralMovementTBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 7) return;
            if(Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] == 0) {

                Round.x1increase();
                Round.x2increase();

                OneFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 == 0) return;
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 2] == 0) {

                Round.x1decrease();
                Round.x2decrease();

                OneFallingControls();
            }
        }
    }

    public static void TwoControlLateralMovementTBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 6) return;
            if(Round.heights[(int) Round.x1 + 3][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 + 2][(int) Round.y1] == 0) {

                Round.x1increase();
                Round.x2increase();

                OneFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 == 0) return;
            if(Round.heights[(int) Round.x1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1][(int) Round.y1] == 0) {

                Round.x1decrease();
                Round.x2decrease();

                OneFallingControls();
            }
        }
    }

    public static void ThreeControlLateralMovementTBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

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
                Round.heights[(int) Round.x1 + 2][(int) Round.y1] != 0) {

            Round.y1++;
            Round.y2++;

            ZeroTBlockInMatrix(5);
            ZeroPaintTBlock(Color.MAGENTA);

            TBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void OneFallingControls() {

        if(Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] != 0) {

            Round.y1++;
            Round.y2++;

            OneTBlockInMatrix(5);
            OnePaintTBlock(Color.MAGENTA);

            TBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void TwoFallingControls() {

        if(Round.heights[(int) Round.x1][(int) Round.y1 + 1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] != 0) {

            Round.y1++;
            Round.y2++;

            TwoTBlockInMatrix(5);
            TwoPaintTBlock(Color.MAGENTA);

            TBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void ThreeFallingControls() {

        if(Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1 + 1] != 0) {

            Round.y1++;
            Round.y2++;

            ThreeTBlockInMatrix(5);
            ThreePaintTBlock(Color.MAGENTA);

            TBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }




    //-- -- painting the block -- --//

    public static void ZeroPaintTBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y1, Round.x1 + 3, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 1, Round.x1 + 2, Round.y1 + 2, color);
    }

    public static void OnePaintTBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 2, Round.x1 + 1, Round.y1 + 3, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 1, Round.x1 + 2, Round.y1 + 2, color);
    }

    public static void TwoPaintTBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 1, Round.x1 + 2, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y1 + 1, Round.x1 + 3, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
    }

    public static void ThreePaintTBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 1, Round.x1 + 2, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 2, Round.x1 + 2, Round.y1 + 3, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
    }




    //-- -- Modifying matrix -- --//

    public static void ZeroTBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 2][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
        }
        catch(IndexOutOfBoundsException e) {

            /*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }

    public static void OneTBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1 + 2] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
        }
        catch(IndexOutOfBoundsException e) {
/*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }

    public static void TwoTBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
        }
        catch(IndexOutOfBoundsException e) {
/*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }

    public static void ThreeTBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] = value;
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
