import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class StartSpill {
    SpillPanel spill;
    Kontroller kontroll;
    int fart;

    JFrame frame = new JFrame("Vindu 1");
    JLabel txt = new JLabel();
    JLabel poeng = new JLabel();
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JButton avlsutt, prøvIgjen;


    StartSpill(){
        spill = new SpillPanel();
        kontroll = new Kontroller(spill);
    }

    public void start(){
        kontroll.leggTilMatStart();
        kontroll.kjørerTråd = new Thread();
        
        fart = kontroll.fart;
        spill.oppdaterPoeng(0);
        kontroll.kjører = true;
        
        Tråd a = new Tråd();
        a.run();

        if (kontroll.kjører==false){
            kontroll.kjørerTråd.interrupt();
            System.out.println("Slutt");
            kontroll.hentPoeng();
            int gameOver = JOptionPane.showConfirmDialog(null, "Game Over! Vil du starte på nytt?\nPoeng: " + kontroll.hentPoeng(), "Game over", JOptionPane.YES_NO_OPTION);
            if (gameOver == JOptionPane.YES_OPTION){
                
                new StartSpill().start();
            } else{
                System.exit(0);
            }
            //new GameOver();
        }
    }

    class Tråd implements Runnable{
        @Override
        public void run() {
            while(kontroll.kjører){
                try {
                    Thread.sleep(fart);
                } catch (Exception e) {
                    System.out.println(e);
                }
                beveg();
            }
        }
    }

    /*public class GameOver implements ActionListener{
        GameOver() {
            txt.setBounds(75, 5, 200, 25);
            txt.setLayout(null);
            txt.setForeground(Color.black);
            txt.setText("Game Over!");
            txt.setFont(new Font(null, 0, 20));
            // txt.addActionListener(this);

            poeng.setBounds(75, 150, 200, 25);
            poeng.setLayout(null);
            poeng.setForeground(Color.black);
            poeng.setText("Poeng: " + kontroll.hentPoeng());
            poeng.setFont(new Font(null, 0, 20));
    
            panel.setBounds(15, 5, 200, 30);
            panel.setLayout(null);
            // panel.setBackground(Color.black);
            panel.add(txt);
    
            // panel2.setBounds(15,5,200,50);
            panel2.setBounds(0, 40, 300, 30);
            panel2.setLayout(new FlowLayout());

            panel3.setBounds(0, 70, 300, 30);
            panel3.setLayout(new FlowLayout());
    
            panel3.add(poeng);


            avlsutt = new JButton("Avslutt");
            prøvIgjen = new JButton("Prøv Igjen");
            avlsutt.setPreferredSize(new Dimension(100, 20));
            prøvIgjen.setPreferredSize(new Dimension(100, 20));
            avlsutt.setFont(new Font(null, Font.BOLD, 12));
            prøvIgjen.setFont(new Font(null, Font.BOLD, 10));
            avlsutt.setFocusable(false);
            prøvIgjen.setFocusable(false);
    
            prøvIgjen.addActionListener(this);
            avlsutt.addActionListener(this);
    
            panel2.add(prøvIgjen);
            panel2.add(avlsutt);
    
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(300, 150));
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
            frame.setResizable(false);
    
            frame.add(panel);
            frame.add(panel2);
            frame.add(panel3);
    
            // frame.add(txt);
            frame.setVisible(true);
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == prøvIgjen) {
                System.exit(0);
            } else if (e.getSource() == avlsutt) {
                System.exit(0);
            }
        }
        

    }
    

    /*public void startPåNytt(){
        spill2 = new SpillPanel();
        kontroll2 = new Kontroller(spill2);

        kontroll2.leggTilMatStart();
        kontroll2.kjørerTråd = new Thread();
        kontroll2.kjører = true;
        fart = kontroll2.fart;
        spill.oppdaterPoeng(0);
        while(kontroll2.kjører){
            try {
                Thread.sleep(fart);
            } catch (Exception e) {
                System.out.println(e);
            }
            beveg();
        }
    }*/

    /*public void settTrue(){
        spill = new SpillPanel();
        kontroll = new Kontroller(spill);
        kontroll.kjører = true;
    }*/
  

    public void beveg(){
        kontroll.beveg();
    }
}

