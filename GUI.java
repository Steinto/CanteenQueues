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
    
    String teacherImage = "Teacher.png";
    
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
                nextStepLogic();
                break;
            case "next Step":
                // nextStepLogic();
                displayQueue();
                break;
            
            
        }
        // createDialogExample();
    }
    
    public void nextStepLogic(){
        FileUtilities arrivals = new FileUtilities("arrivals - arrivals.csv");
        System.out.println(arrivals.readLine(3));
        String[] data = arrivals.readLine(3).split(",");
        
        int students = Integer.parseInt(data[1]);
        int time = Integer.parseInt(data[0]);
        int staff = Integer.parseInt(data[2]);
        int served = Integer.parseInt(data[3]);
        
        for (int i = 0; i < students;i++){
            this.queue.enqueue(time, false);
        }
        
        for (int i = 0; i < staff;i++){
            this.queue.enqueue(time, true);
        }
        
        for (int i = 0; i < served;i++){
            this.queue.dequeue();
        }
        
                
    }
    
    public void displayQueue(){
        ImageIcon studentImage1 = new ImageIcon(studentImage);
        ImageIcon teacherImage1 = new ImageIcon(teacherImage);
        centerPanel.removeAll();
        
        // System.out.println(this.queue.getHighPLength());
        for(int i = 0; i < (this.queue.getHighPLength());i++){
            JLabel image = new JLabel(teacherImage1);
            centerPanel.add(image);
            
        }
        
        // System.out.println(this.queue.getLowPLength());
        for(int i = 0; i < (this.queue.getLowPLength());i++){
            JLabel image = new JLabel(studentImage1);
            centerPanel.add(image);
            
        }
        
        
        
        this.pack();
    }
}
