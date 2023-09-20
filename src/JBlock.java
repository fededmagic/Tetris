import java.awt.*;

public class JBlock extends CurrentBlock {

    JBlock() { super(); }




    //-- -- changing direction -- --//

    public void changeOrientation(int spin) {

        //spin = 0 -> right --- spin = 1 -> left

        int previous = blockOrientation;

        if(blockOrientation == 0 && Round.y1 > 17) return;

        if(Round.x1 > 7 && (blockOrientation == 1 || blockOrientation == 3)) Round.x1--;

        if(spin == 0) blockOrientation = (blockOrientation + 1) % 4;
        else blockOrientation = (blockOrientation - 1 + 4) % 4;

        rotationControls(previous);
    }




    //-- -- general functions -- --//

    public void drawNewBlock() {

        if(blockOrientation == 0) {

            ZeroFallingControls();
            ZeroJBlockInMatrix(7);
            ZeroPaintJBlock(Color.BLUE);
        }
        else if(blockOrientation == 1) {

            OneFallingControls();
            OneJBlockInMatrix(7);
            OnePaintJBlock(Color.BLUE);
        }
        else if(blockOrientation == 2) {

            TwoFallingControls();
            TwoJBlockInMatrix(7);
            TwoPaintJBlock(Color.BLUE);
        }
        else {

            ThreeFallingControls();
            ThreeJBlockInMatrix(7);
            ThreePaintJBlock(Color.BLUE);
        }
    }

    public void deleteBlock() {

        if(blockOrientation == 0) {

            ZeroJBlockInMatrix(0);
            ZeroPaintJBlock(Color.LIGHT_GRAY);
        }
        else if(blockOrientation == 1) {

            OneJBlockInMatrix(0);
            OnePaintJBlock(Color.LIGHT_GRAY);
        }
        else if(blockOrientation == 2) {

            TwoJBlockInMatrix(0);
            TwoPaintJBlock(Color.LIGHT_GRAY);
        }
        else {

            ThreeJBlockInMatrix(0);
            ThreePaintJBlock(Color.LIGHT_GRAY);
        }
    }

    public void controlLateralMovementBlock(String direction) {

        if(blockOrientation == 0) { ZeroControlLateralMovementJBlock(direction); }
        else if(blockOrientation == 1) { OneControlLateralMovementJBlock(direction); }
        else if(blockOrientation == 2) { TwoControlLateralMovementJBlock(direction); }
        else { ThreeControlLateralMovementJBlock(direction); }
    }




    //-- -- lateral movement controls -- --//

    public static void ZeroControlLateralMovementJBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 6) return;
            if(Round.heights[(int) Round.x1 + 3][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] == 0) {

                Round.x1increase();
                ZeroFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 < 1) return;
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 1] == 0) {

                Round.x1decrease();
                ZeroFallingControls();
            }
        }
    }

    public static void OneControlLateralMovementJBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 7) return;
            if(Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 2] == 0 &&
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

    public static void TwoControlLateralMovementJBlock(String direction) {

        if(direction.compareTo("increase") == 0) {

            if(Round.x1 > 6) return;
            if(Round.heights[(int) Round.x1 + 3][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1 + 3][(int) Round.y1] == 0) {

                Round.x1increase();
                TwoFallingControls();
            }
        }
        else if(direction.compareTo("decrease") == 0) {

            if(Round.x1 == 0) return;
            if(Round.heights[(int) Round.x1 + 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1 - 1][(int) Round.y1 + 1] == 0) {

                Round.x1decrease();
                TwoFallingControls();
            }
        }
    }

    public static void ThreeControlLateralMovementJBlock(String direction) {

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
            if(Round.heights[(int) Round.x1 - 1][(int) Round.y1] == 0 &&
                    Round.heights[(int) Round.x1][(int) Round.y1 + 1] == 0 &&
                    Round.heights[(int) Round.x1][(int) Round.y1 + 2] == 0) {

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

            ZeroJBlockInMatrix(7);
            ZeroPaintJBlock(Color.BLUE);

            JBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void OneFallingControls() {

        if(Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] != 0) {

            Round.y1++;

            OneJBlockInMatrix(7);
            OnePaintJBlock(Color.BLUE);

            JBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void TwoFallingControls() {

        if(Round.heights[(int) Round.x1 + 2][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1 + 1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] != 0) {

            Round.y1++;

            TwoJBlockInMatrix(7);
            TwoPaintJBlock(Color.BLUE);

            JBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }

    public static void ThreeFallingControls() {

        if(Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1] != 0) {

            Round.y1++;

            ThreeJBlockInMatrix(7);
            ThreePaintJBlock(Color.BLUE);

            JBlock.blockOrientation = 0;
            Round.anew = 0;
        }
    }




    // -- -- rotation controls -- -- //

    public static void rotationControls(int previous) {

        if((blockOrientation == 0) && (Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1 + 1] != 0)) blockOrientation = previous;

        else if((blockOrientation == 1) && (Round.heights[(int) Round.x1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1 + 1] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1 + 2] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] != 0)) blockOrientation = previous;

        else if((blockOrientation == 2) && (Round.heights[(int) Round.x1][(int) Round.y1 + 1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] != 0 ||
                Round.heights[(int) Round.x1 + 2][(int) Round.y1] != 0)) blockOrientation = previous;

        else if((blockOrientation == 3) && (Round.heights[(int) Round.x1 + 1][(int) Round.y1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] != 0 ||
                Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] != 0 ||
                Round.heights[(int) Round.x1][(int) Round.y1] != 0)) blockOrientation = previous;
    }




    //-- -- painting the block -- --//

    public static void ZeroPaintJBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y1, Round.x1 + 3, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
    }

    public static void OnePaintJBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 2, Round.x1 + 1, Round.y1 + 3, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 2, Round.x1 + 2, Round.y1 + 3, color);
    }

    public static void TwoPaintJBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1, Round.y1 + 1, Round.x1 + 1, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 1, Round.x1 + 2, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y1 + 1, Round.x1 + 3, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 2, Round.y1, Round.x1 + 3, Round.y1 + 1, color);
    }

    public static void ThreePaintJBlock(Color color) {

        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1, Round.x1 + 2, Round.y1 + 1, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 1, Round.x1 + 2, Round.y1 + 2, color);
        FrmTetris.graph.fillRect(Round.x1 + 1, Round.y1 + 2, Round.x1 + 2, Round.y1 + 3, color);
        FrmTetris.graph.fillRect(Round.x1, Round.y1, Round.x1 + 1, Round.y1 + 1, color);
    }




    //-- -- Modifying matrix -- --//

    public static void ZeroJBlockInMatrix(int value) {

            Round.heights[(int) Round.x1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 2][(int) Round.y1] = value;
            Round.heights[(int) Round.x1][(int) Round.y1 + 1] = value;
    }

    public static void OneJBlockInMatrix(int value) {

        Round.heights[(int) Round.x1][(int) Round.y1] = value;
        Round.heights[(int) Round.x1][(int) Round.y1 + 1] = value;
        Round.heights[(int) Round.x1][(int) Round.y1 + 2] = value;
        Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] = value;
    }

    public static void TwoJBlockInMatrix(int value) {

        Round.heights[(int) Round.x1][(int) Round.y1 + 1] = value;
        Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
        Round.heights[(int) Round.x1 + 2][(int) Round.y1 + 1] = value;
        Round.heights[(int) Round.x1 + 2][(int) Round.y1] = value;
    }

    public static void ThreeJBlockInMatrix(int value) {

            Round.heights[(int) Round.x1 + 1][(int) Round.y1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 1] = value;
            Round.heights[(int) Round.x1 + 1][(int) Round.y1 + 2] = value;
            Round.heights[(int) Round.x1][(int) Round.y1] = value;
    }
}
