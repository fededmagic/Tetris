public class CurrentBlock {
    protected static int blockOrientation = 0;

    CurrentBlock() {}

    public void changeOrientation(int spin) {}

    public void drawNewBlock() {}

    public void deleteBlock() {}

    public void controlLateralMovementBlock(String direction) {}

    public static int getBlockOrientation() {
        return blockOrientation;
    }

    public static void setBlockOrientation(int blockOrientation) {
        CurrentBlock.blockOrientation = blockOrientation;
    }
}
