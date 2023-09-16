import java.awt.*;

public class LBlock extends CurrentBlock {

    LBlock() { super(); }




    //-- -- changing direction -- --//

    public void changeOrientation(int spin) {

        //spin = 0 -> right --- spin = 1 -> left

        if(blockOrientation == 0 && Round.y1 > 17) return;

        if(spin == 0 && Round.x1 > 7 && (blockOrientation == 1 || blockOrientation == 3)) Round.x1--;

        if(spin == 0) blockOrientation = (blockOrientation + 1) % 4;
        else blockOrientation = (blockOrientation - 1 + 4) % 4;

    }




    //-- -- general functions -- --//

    public void drawNewBlock() {

        if(blockOrientation == 0) {

            ZeroFallingControls();
            ZeroLBlockInMatrix(6);
            ZeroPaintLBlock(Color.ORANGE);
        }
        else if(blockOrientation == 1) {

            OneFallingControls();
            OneLBlockInMatrix(6);
            OnePaintLBlock(Color.ORANGE);
        }
        else if(blockOrientation == 2) {

            TwoFallingControls();
            TwoLBlockInMatrix(6);
            TwoPaintLBlock(Color.ORANGE);
        }
        else {

            ThreeFallingControls();
            ThreeLBlockInMatrix(6);
            ThreePaintLBlock(Color.ORANGE);
        }
    }

    public void deleteBlock() {

        if(blockOrientation == 0) {

            ZeroLBlockInMatrix(0);
            ZeroPaintLBlock(Color.LIGHT_GRAY);
        }
        else if(blockOrientation == 1) {

            OneLBlockInMatrix(0);
            OnePaintLBlock(Color.LIGHT_GRAY);
        }
        else if(blockOrientation == 2) {

            TwoLBlockInMatrix(0);
            TwoPaintLBlock(Color.LIGHT_GRAY);
        }
        else {

            ThreeLBlockInMatrix(0);
            ThreePaintLBlock(Color.LIGHT_GRAY);
        }
    }

    public void controlLateralMovementBlock(String direction) {

        if(blockOrientation == 0) { ZeroControlLateralMovementLBlock(direction); }
        else if(blockOrientation == 1) { OneControlLateralMovementLBlock(direction); }
        else if(blockOrientation == 2) { TwoControlLateralMovementLBlock(direction); }
        else { ThreeControlLateralMovementLBlock(direction); }
    }




    //-- -- lateral movement controls -- --//

    public static void ZeroControlLateralMovementLBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 6) return;
            if(Round.heights[(int) Round.x1 + 3][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 3][(int) Round.y1 + 1] == 0) {

                Round.x1increase();
                ZeroFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 < 1) return;
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] == 0) {

                Round.x1decrease();
                ZeroFallingControls();
            }
        }
    }

    public static void OneControlLateralMovementLBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 7) return;
            if(Round.heights[(int) Round.x1 + 2][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] == 0) {

                Round.x1increase();
                OneFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 == 0) return;
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 2] == 0) {

                Round.x1decrease();
                OneFallingControls();
            }
        }
    }

    public static void TwoControlLateralMovementLBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 6) return;
            if(Round.heights[(int) Round.x1 + 3][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1] == 0) {

                Round.x1increase();
                TwoFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 == 0) return;
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 1] == 0) {

                Round.x1decrease();
                TwoFallingControls();
            }
        }
    }

    public static void ThreeControlLateralMovementLBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 7) return;
            if(Round.heights[(int) Round.x1 + 2][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 2] == 0) {

                Round.x1increase();
                ThreeFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 == 0) return;
            if(Round.heights[(int) Round.x1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 2] == 0) {

                Round.x1decrease();
                ThreeFallingControls();
            }
        }
    }



    //-- -- falling controls -- --//

    public static void ZeroFallingControls() {

        if(Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y1] != 0) {

            Round.y1++;

            ZeroLBlockInMatrix(6);
            ZeroPaintLBlock(Color.ORANGE);

            LBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void OneFallingControls() {

        if(Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0) {

            Round.y1++;

            OneLBlockInMatrix(6);
            OnePaintLBlock(Color.ORANGE);

            LBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void TwoFallingControls() {

        if(Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] != 0) {

            Round.y1++;

            TwoLBlockInMatrix(6);
            TwoPaintLBlock(Color.ORANGE);

            LBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void ThreeFallingControls() {

        if(Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1 + 2] != 0) {

            Round.y1++;

            ThreeLBlockInMatrix(6);
            ThreePaintLBlock(Color.ORANGE);

            LBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }




    //-- -- painting the block -- --//

    public static void ZeroPaintLBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y1, Round.x1 + 3, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y1 + 1, Round.x1 + 3, Round.y1 + 2, color);
    }

    public static void OnePaintLBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 2, Round.x1 + 1, Round.y1 + 3, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
    }

    public static void TwoPaintLBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 1, Round.x1 + 2, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y1 + 1, Round.x1 + 3, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
    }

    public static void ThreePaintLBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 1, Round.x1 + 2, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 2, Round.x1 + 2, Round.y1 + 3, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 2, Round.x1 + 1, Round.y1 + 3, color);
    }




    //-- -- Modifying matrix -- --//

    public static void ZeroLBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 2][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] = value;
        }
        catch(IndexOutOfBoundsException e) {

            /*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }

    public static void OneLBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1 + 2] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
        }
        catch(IndexOutOfBoundsException e) {
/*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }

    public static void TwoLBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
        }
        catch(IndexOutOfBoundsException e) {
/*
            FrmTetris.timer.stop();
            FrmTetris.lblStatus.setText("YOU LOOSE!");
            FrmTetris.btnStartStop.setText("RESTART");*/
        }
    }

    public static void ThreeLBlockInMatrix(int value) {

        try {
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] = value;
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
