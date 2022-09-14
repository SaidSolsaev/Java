
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Velkommen implements ActionListener {

    JFrame frame = new JFrame("Vindu 1");
    JLabel txt = new JLabel();
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JButton avlsutt, prøvIgjen, start;

    Velkommen() {
        txt.setBounds(120, 5, 400, 25);
        txt.setLayout(null);
        txt.setForeground(Color.black);
        txt.setText("Game Over!");
        txt.setFont(new Font(null, 0, 20));
        // txt.addActionListener(this);

        panel.setBounds(15, 5, 400, 30);
        panel.setLayout(null);
        // panel.setBackground(Color.black);
        panel.add(txt);

        // panel2.setBounds(15,5,200,50);
        panel2.setBounds(0, 40, 400, 30);
        panel2.setLayout(new FlowLayout());

        avlsutt = new JButton("Avslutt");
        prøvIgjen = new JButton("Prøv Igjen");
        start = new JButton("Start");
        start.setPreferredSize(new Dimension(100, 20));
        start.setFont(new Font(null, Font.BOLD, 12));
        start.setFocusable(false);

        avlsutt.setPreferredSize(new Dimension(100, 20));
        prøvIgjen.setPreferredSize(new Dimension(100, 20));
        avlsutt.setFont(new Font(null, Font.BOLD, 12));
        prøvIgjen.setFont(new Font(null, Font.BOLD, 10));
        avlsutt.setFocusable(false);
        prøvIgjen.setFocusable(false);

        class StartAction implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                StartSpill s = new StartSpill();
                s.start();
            }
            
        }

        prøvIgjen.addActionListener(new StartAction());
        avlsutt.addActionListener(this);
        start.addActionListener(this);

        panel2.add(prøvIgjen);
        panel2.add(avlsutt);
        panel2.add(start);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 120));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        frame.add(panel);
        frame.add(panel2);

        // frame.add(txt);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == avlsutt) {
            //f.start();
            //f.settFalse();
            //frame.dispose();
        } else if (e.getSource() == avlsutt) {
            System.exit(0);
        } else if (e.getSource() == start){
            frame.repaint();
            StartSpill f = new StartSpill();
            f.start();
        }
    }
}
