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
    JPanel sidePanel;
    JPanel CenterPanel;
    
    JLabel labelTitle;
    JLabel explanation;
    JLabel time;
    
    JButton nextStep;
    JButton pause;
    JButton close;
    
    private PQueue Queue; 
    
    public GUI(PQueue Queue){
        setTitle("Canteen Queues Simulation");
        this.getContentPane().setPreferredSize(new Dimension(600, 600));
        
        addMouseListener(this);
        

        
        labelTitle = new JLabel("WELCOME TO THE SIMULATION");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitle.setAlignmentX(CENTER_ALIGNMENT);
        
        explanation = new JLabel("Top queue is students and bottom queue is staff");
        explanation.setAlignmentX(CENTER_ALIGNMENT);
        
        time = new JLabel("Minute: 1");
        
        
        nextStep = new JButton("next Step");
        nextStep.addActionListener(this);
        
        pause = new JButton("pause");
        pause.addActionListener(this);
        
        close = new JButton("file read");
        close.addActionListener(this);
        
        
        CenterPanel = new JPanel();
        CenterPanel.setLayout(new BoxLayout(CenterPanel, BoxLayout.PAGE_AXIS));
        CenterPanel.setBackground(Color.decode("#B9DA8B"));
        this.add(CenterPanel, BorderLayout.CENTER);
        
        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
        titlePanel.setBackground(Color.pink);
        this.add(titlePanel, BorderLayout.NORTH);
        
        titlePanel.add(labelTitle);
        titlePanel.add(explanation);
        
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
        sidePanel.setBackground(Color.green);
        this.add(sidePanel, BorderLayout.LINE_START);
        
        sidePanel.add(time);
        sidePanel.add(nextStep);
        sidePanel.add(pause);
        sidePanel.add(close);
        
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
            case "file read":
                FileUtilities arrivals = new FileUtilities("arrivals - arrivals.csv");
                arrivals.readLine(2);
                break;
            
            
        }
        // createDialogExample();
    }
    
    public void displayQueue(){
            
        
        
    }
}
