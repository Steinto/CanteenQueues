/**
 * Controlls the graphical user interface
 *
 * Toby Steiner
 * 16/6/2025
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI extends JFrame implements ActionListener, MouseListener
{
    JPanel titlePanel;
    
    JLabel labelTitle;
    JLabel explanation;
    
    public GUI(){
        setTitle("Canteen Queues Simulation");
        this.getContentPane().setPreferredSize(new Dimension(600, 600));
        
        addMouseListener(this);
        

        
        labelTitle = new JLabel("WELCOME TO THE SIMULATION");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        
        explanation = new JLabel("top queu is students and bottom queue is staff");
        
        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        titlePanel.setBackground(Color.pink);
        this.add(titlePanel, BorderLayout.NORTH);
        
        titlePanel.add(labelTitle);
        titlePanel.add(explanation);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.toFront();
        this.setVisible(true);
    }
    
    public void mouseEntered(MouseEvent e) {System.out.println("enter");}
    public void mouseExited(MouseEvent e) {System.out.println("exit");}
    public void mousePressed(MouseEvent e) {System.out.println("press");}
    public void mouseReleased(MouseEvent e) {System.out.println("release");}
    public void mouseClicked(MouseEvent e) {System.out.println("click");}
    
    public void actionPerformed(ActionEvent e){
        // System.out.println(e);
        String cmd = e.getActionCommand();
        System.out.println(cmd);
        switch(cmd){
            case "":
                
            
            
        }
        // createDialogExample();
    }
}
