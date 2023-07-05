import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmTetris extends JFrame implements ActionListener {

    private Timer timer = null;
    public static JCartesian graph = null;
    public static JLabel lblCounter = null;
    private JButton btnStartStop = null;

    public FrmTetris() {

        setSize(600, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Tetris");

        initUI();

        this.timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Round.next();
            }
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

        this.graph = new JCartesian(600, 600, 0.0, 0.0, 10.0, 20.0);
        this.add(graph, BorderLayout.CENTER);
    }

    private void pnlSouth() {

        JPanel pnlSouth = new JPanel(new GridLayout(1, 2));

        JPanel pnlCounter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.lblCounter = new JLabel("0:00");
        this.lblCounter.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        pnlCounter.add(this.lblCounter);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.btnStartStop = new JButton("START");
        pnlButtons.add(this.btnStartStop);

        pnlSouth.add(pnlCounter);
        pnlSouth.add(pnlButtons);

        this.add(pnlSouth, BorderLayout.SOUTH);

        this.btnStartStop.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnStartStop) {

            if (this.timer.isRunning()) {

                timer.stop();
                this.btnStartStop.setText("START");
            }
            else {

                timer.start();
                this.btnStartStop.setText("STOP");

            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmTetris());
    }

}