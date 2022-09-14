import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;



public class SpillPanel extends JFrame implements ActionListener{

    JFrame hovedVindu = new JFrame("SNAKE");
    JLabel poeng = new JLabel();
    JLabel[][] ruter = new JLabel[12][12];
    JPanel hovedpanel, kontrollpanel, knapper, spillpanel;
    JButton opp, ned, høyre, venstre, avslutt;
    char vei = 'H';
    ArrayList<JLabel> slange = new ArrayList<>();;
    private int høyde = 600;
    private int lengde = 600;

    ImageIcon snake = new ImageIcon("icons/snake.png");
    ImageIcon slangeIkon = new ImageIcon("icons/snakeIcon.png");
    ImageIcon mat = new ImageIcon("icons/eple.png");

    SpillPanel(){
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch(Exception e){
            System.out.println("UIManger funket ikke!");
            System.exit(0);
        }

        //Setter opp hovedVindu
        hovedVindu.setPreferredSize(new Dimension(lengde,høyde));
        hovedVindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hovedVindu.setIconImage(slangeIkon.getImage());

        //Setter opp main panel
        hovedpanel = new JPanel();
        hovedpanel.setLayout(new BorderLayout());


        kontrollpanel = new JPanel();
        kontrollpanel.setLayout(new BorderLayout(2,2));
        kontrollpanel.setPreferredSize(new Dimension(400,100));
        kontrollpanel.setBounds(0,0,400,100);
        kontrollpanel.setBackground(Color.red);
        kontrollpanel.setBorder(BorderFactory.createLineBorder(Color.black));

        opp = new JButton();
        opp.setFocusable(false);
        opp.setText("OPP");
        opp.addActionListener(this);
        opp.setPreferredSize(new Dimension(50,20));

        ned = new JButton();
        ned.setFocusable(false);
        ned.setText("NED");
        ned.addActionListener(this);

        venstre = new JButton();
        venstre.setFocusable(false);
        venstre.setText("VENSTRE");
        venstre.addActionListener(this);
        venstre.setPreferredSize(new Dimension(200,20));


        høyre = new JButton();
        høyre.setFocusable(false);
        høyre.setText("HØYRE");
        høyre.addActionListener(this);
        høyre.setPreferredSize(new Dimension(200,20));

        avslutt = new JButton();
        avslutt.setFocusable(false);
        avslutt.setText("AVSLUTT");
        avslutt.addActionListener(this);
        avslutt.setPreferredSize(new Dimension(200,20));

        knapper = new JPanel();
        knapper.setLayout(new BorderLayout(2,2));
        knapper.setPreferredSize(new Dimension(50,50));
        knapper.setBounds(0,0,50,50);
        knapper.add(opp, BorderLayout.NORTH);
        knapper.add(ned, BorderLayout.SOUTH);
        knapper.add(høyre, BorderLayout.EAST);
        knapper.add(venstre, BorderLayout.WEST);
        knapper.add(avslutt, BorderLayout.CENTER);

        spillpanel = new JPanel();
        spillpanel.setLayout(new GridLayout(12,12));
        spillpanel.setPreferredSize(new Dimension(600,450));
        spillpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        for (int rad = 0; rad < 12; rad++){
            for (int kolonne = 0; kolonne<12;kolonne++){
                JLabel rute = new JLabel();
                rute.setText(" ");
                rute.setOpaque(true);
                rute.setBorder(BorderFactory.createLineBorder(Color.black));
                ruter[rad][kolonne] = rute;
                spillpanel.add(rute);
            }
        }

        poeng.setText("POENG: ");
        poeng.setHorizontalAlignment(JLabel.CENTER);
        poeng.setFont(new Font(null, Font.BOLD,14));

        kontrollpanel.add(knapper);
        hovedpanel.add(kontrollpanel, BorderLayout.NORTH);
        hovedpanel.add(poeng, BorderLayout.CENTER);
        hovedpanel.add(spillpanel, BorderLayout.SOUTH);

        
        hovedVindu.add(hovedpanel);
        hovedVindu.setLocationRelativeTo(null);
        hovedVindu.pack();
        hovedVindu.setResizable(false);
        hovedVindu.setVisible(true);
    }

    /*public void leggTilSlangeHode(int rad, int kolonne) { // Adds snake at specific rad,kolonne:
        /*Image bilde = snake.getImage();
        Image nySnake = bilde.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        snake = new ImageIcon(nySnake);
        ruter[rad][kolonne].setBackground(new Color(0, 140, 0));
        ruter[rad][kolonne].setText("+");
        ruter[rad][kolonne].setHorizontalAlignment(JLabel.CENTER);
        slange.add(ruter[rad][kolonne]);
    }*/

    public void leggTilSlangeHale(int rad, int kolonne){
        ruter[rad][kolonne].setText("+");
        ruter[rad][kolonne].setBackground(Color.green);
        ruter[rad][kolonne].setHorizontalAlignment(JLabel.CENTER);
        slange.add(0,ruter[rad][kolonne]);

    }

    public void leggTilMat(int rad, int kolonne){
        Image eple = mat.getImage();
        Image nyMat = eple.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        mat = new ImageIcon(nyMat);
        //ruter[rad][kolonne].setBackground(Color.red);
        ruter[rad][kolonne].setIcon(mat);
        ruter[rad][kolonne].setText("$");
        ruter[rad][kolonne].setHorizontalAlignment(JLabel.CENTER);
    }

    public void fjernMat(int rad, int kolonne){
        ruter[rad][kolonne].setIcon(null);
        ruter[rad][kolonne].setText("");

    }

    public void fjernSlange(int rad, int kolonne){
        if (ruter[rad][kolonne].getText().equals("+")){
            ruter[rad][kolonne].setText("");
            ruter[rad][kolonne].setBackground(null);
            slange.remove(ruter[rad][kolonne]);
        }

    }

    public void oppdaterPoeng(int p){
        poeng.setText("POENG: " + p);
    }

    public boolean kollisjon(int rad, int kolonne, char vei){
        if (rad == 0 && vei == 'O' || kolonne == 0 && vei == 'V' || rad == 11 && vei == 'N' || kolonne == 11 && vei =='H'){
            return true;
        }
        else if (vei == 'O'){
            return ruter[rad-1][kolonne].getText().equals("+");
        }
        else if (vei == 'N'){
            return ruter[rad+1][kolonne].getText().equals("+");
        }
        else if (vei == 'H'){
            return ruter[rad][kolonne+1].getText().equals("+");
        }
        else if (vei == 'V'){
            return ruter[rad][kolonne-1].getText().equals("+");
        }
        else{
            return false;
        }
    }

    public boolean kollisjonMat(int rad, int kolonne, char vei){
        if (vei == 'O'){
            return ruter[rad-1][kolonne].getText().equals("$");
        }
        else if (vei == 'N'){
            return ruter[rad+1][kolonne].getText().equals("$");
        }
        else if (vei == 'H'){
            return ruter[rad][kolonne+1].getText().equals("$");
        }
        else if (vei == 'V'){
            return ruter[rad][kolonne-1].getText().equals("$");
        }
        else{
            return false;
        }
    }
    


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == opp && vei != 'N'){
            vei = 'O';
            /*StartSpill spill = new StartSpill();
            spill.start();*/
        }
        else if(e.getSource() == ned && vei != 'O'){
            vei = 'N';
        }
        else if(e.getSource() == høyre && vei != 'V'){
            vei = 'H';
        }
        else if(e.getSource() == venstre && vei != 'H'){
            vei = 'V';
        }
        else if (e.getSource() == avslutt){
            System.exit(0);            
            
        }
    }
}