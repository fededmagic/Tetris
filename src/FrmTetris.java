import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmTetris extends JFrame implements ActionListener {

    public static Timer timer = null;
    public static JCartesian graph = null;
    public static JLabel lblCounter = null;
    public static JLabel lblStatus = null;
    public static JLabel lblLine = null;
    public static JButton btnStartStop = null;
    private static int dotcounter = 0;
    private static int commacounter = 0;
    private static int vcounter = 0;

    public FrmTetris() {

        setSize(610, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Tetris");

        initUI();
        timer = new Timer(1000, e -> {

            Round.next();
            Round.updateTime();
        });


        Round.heights = new int[10][20];
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 20; j++)
                Round.heights[i][j] = 0;

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {

            if(e.getKeyChar() == '.') {

                dotcounter++;
                if(dotcounter == 3) {

                    dotcounter = 0;
                    Round.deleteCurrent();

                    Round.controlLateralMovement("increase");

                    Round.drawCurrent();
                    Round.drawGrid();
                }
            }

            if(e.getKeyChar() == ',') {

                commacounter++;
                if(commacounter == 3) {

                    commacounter = 0;
                    Round.deleteCurrent();

                    Round.controlLateralMovement("decrease");

                    Round.drawCurrent();
                    Round.drawGrid();
                }
            }

            if(e.getKeyChar() == 'v') {

                vcounter++;

                if(vcounter == 3) {

                    vcounter = 0;

                    Round.falldown();
                    Round.next();
                }
            }

            return false;
        });

        setVisible(true);
    }

    private void initUI() {

        pnlNorth();
        pnlCenter();
        pnlSouth();
    }

    private void pnlNorth() {

        JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTetris = new JLabel("PLAY TETRIS!");
        pnlNorth.add(lblTetris);
        this.add(pnlNorth, BorderLayout.NORTH);
    }

    private void pnlCenter() {

        graph = new JCartesian(600, 600, 0.0, 0.0, 10.0, 20.0);
        Round.drawGrid();
        this.add(graph, BorderLayout.CENTER);
    }

    private void pnlSouth() {

        JPanel pnlSouth = new JPanel(new GridLayout(1, 2));

        JPanel pnlCounter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblCounter = new JLabel("0:00");
        lblCounter.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        pnlCounter.add(lblCounter);

        JPanel pnlStatus = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblStatus = new JLabel("");
        lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        pnlStatus.add(lblStatus);

        JPanel pnlLine = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblLine = new JLabel("Score: 0");
        lblLine.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        pnlLine.add(lblLine);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        FrmTetris.btnStartStop = new JButton("START");
        pnlButtons.add(FrmTetris.btnStartStop);

        pnlSouth.add(pnlCounter);
        pnlSouth.add(pnlStatus);
        pnlSouth.add(pnlLine);
        pnlSouth.add(pnlButtons);

        this.add(pnlSouth, BorderLayout.SOUTH);

        FrmTetris.btnStartStop.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == FrmTetris.btnStartStop) {

            if (timer.isRunning()) {

                timer.stop();
                FrmTetris.btnStartStop.setText("START");
            }
            else if(FrmTetris.btnStartStop.getText().compareTo("RESTART") == 0) {

                Round.restartGame();
            }
            else {

                timer.start();
                FrmTetris.btnStartStop.setText("STOP");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FrmTetris::new);
    }

}