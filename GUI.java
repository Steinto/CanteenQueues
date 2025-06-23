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
    JPanel centerPanel;
    
    JLabel labelTitle;
    JLabel explanation;
    JLabel time;
    
    JButton nextStep;
    JButton pause;
    JButton close;
    
    private PQueue queue; 
    private int turn = 2;
    
    String studentImage = "Student.jpg";
    JLabel images;
    
    public GUI(PQueue queueInput){
        this.queue = queueInput;
        
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
        
        ImageIcon studentImage1 = new ImageIcon(studentImage);
        images = new JLabel(studentImage1);
        
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBackground(Color.decode("#B9DA8B"));
        this.add(centerPanel, BorderLayout.CENTER);
        

        
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
            case "next Step":
                
                displayQueue();
                break;
            
            
        }
        // createDialogExample();
    }
    
    public void nextStepLogic(){
        FileUtilities arrivals = new FileUtilities("arrivals - arrivals.csv");
        arrivals.readLine(2);
    }
    
    public void displayQueue(){
        ImageIcon studentImage1 = new ImageIcon(studentImage);
        System.out.println(this.queue.getLowPLength());
        centerPanel.removeAll();
        for(int i = 0; i < (this.queue.getLowPLength());i++){
            JLabel image = new JLabel(studentImage1);
            centerPanel.add(image);
            
        }
        
        this.pack();
    }
}
