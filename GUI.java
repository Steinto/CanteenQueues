/**
 * Controlls the graphical user interface
 *
 * Toby Steiner
 * 16/6/2025
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class GUI extends JFrame implements ActionListener, MouseListener
{
    private Timer timer;

    JPanel titlePanel;
    JPanel sidePanel;
    JPanel centerPanel;

    JLabel labelTitle;
    JLabel explanation;
    JLabel time;
    JLabel staffWaitTime;
    JLabel studentWaitTime;
    JLabel staffInLine;
    JLabel studentsInLine;

    JButton nextStep;
    JButton pause;
    JButton close;
    JButton play;

    private PQueue queue; 

    String studentImage = "Student.jpg";
    JLabel images;

    String teacherImage = "Teacher.png";

    private double staffAverageTime;
    private double studentAverageTime;

    private int line = 2;
    private double staffServed;
    private double studentsServed;
    private ArrayList<Integer> studentTimes = new ArrayList<>();
    private ArrayList<Integer> staffTimes = new ArrayList<>();

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
        staffWaitTime = new JLabel("Staff average time waited");
        studentWaitTime = new JLabel("Student average time waited");
        staffInLine = new JLabel("Staff In The Queue: 0");
        studentsInLine = new JLabel("Students In The Queue: 0");

        nextStep = new JButton("next Step");
        nextStep.addActionListener(this);
        pause = new JButton("pause");
        pause.addActionListener(this);
        close = new JButton("file read");
        close.addActionListener(this);
        play = new JButton("play");
        play.addActionListener(this);

        ImageIcon studentImage1 = new ImageIcon(studentImage);
        images = new JLabel(studentImage1);

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0,2));
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
        centerPanel.add(nextStep);
        centerPanel.add(pause);
        centerPanel.add(close);
        centerPanel.add(play);
        sidePanel.add(staffWaitTime);
        sidePanel.add(studentWaitTime);
        centerPanel.add(staffInLine);
        centerPanel.add(studentsInLine);

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
            case "next Step":
                playLogic();
        }
    }
    // createDialogExample();

    public void averageTimeLogic(int inputTime, boolean teacher){
        int time = line - 1;
        double averageStudent = 0;
        double averageStaff = 0;

        if(teacher){
            this.staffTimes.add(time - inputTime);
            System.out.println(staffTimes.size());
            for (int i = 0; i < staffTimes.size(); i++){
                averageStaff = averageStaff + staffTimes.get(i);
            }
            averageStaff = (averageStaff / staffTimes.size());
            this.staffWaitTime.setText(Double.toString(averageStaff));
        }else{
            this.studentTimes.add(time - inputTime);
            System.out.println(studentTimes.size());
            for (int i = 0; i < studentTimes.size(); i++){
                averageStudent = averageStudent + studentTimes.get(i);
            }
            averageStudent = (averageStudent / studentTimes.size());
            this.studentWaitTime.setText(Double.toString(averageStudent));
        }

        
        
        // if (teacher){
        // if (this.staffServed == 0){
        // staffAverageWait = (time - inputTime);
        // this.staffServed++;
        // }else{
        // staffAverageWait = ((staffAverageWait + (time - inputTime))/2);
        // }
        // this.staffWaitTime.setText(Double.toString(staffAverageWait));
        // }else{
        // if (this.studentsServed == 0){
        // staffAverageWait = (time - inputTime);
        // this.studentsServed++;
        // }else{
        // studentAverageWait = ((studentAverageWait + (time - inputTime))/2);

        // }
        // this.studentWaitTime.setText(Double.toString(studentAverageWait));
        // }

        this.pack();
    }

    public void playLogic(){
        FileUtilities arrivals = new FileUtilities("arrivals - arrivals.csv");
        nextStepLogic(line);
        staffInLine.setText("Staff In The Queue: " + this.queue.getHighPLength());
        studentsInLine.setText("Students In The Queue: " + this.queue.getLowPLength());

        time.setText("Minute: " + (line-1));
        this.line++;
        this.pack();
    }

    public void nextStepLogic(int line){
        FileUtilities arrivals = new FileUtilities("arrivals - arrivals.csv");
        System.out.println(arrivals.readLine(line));
        try{
            String[] data = arrivals.readLine(line).split(",");
            int students = Integer.parseInt(data[1]);
            int time = Integer.parseInt(data[0]);
            int staff = Integer.parseInt(data[2]);
            int served = Integer.parseInt(data[3]);

            // System.out.println(students);

            for (int i = 0; i < students;i++){
                this.queue.enqueue(time, false);

            }
            for (int i = 0; i < staff;i++){
                this.queue.enqueue(time, true);

            }

            for (int i = 0; i < served;i++){
                if (this.queue.getHighPLength() > 0){
                    averageTimeLogic(this.queue.dequeue(), true);
                }else{
                    averageTimeLogic(this.queue.dequeue(), false);
                }

            }
        }catch(Exception e){
            System.out.println("no data left");
        }

        // System.out.println(this.queue.getLowPLength());
    }

    public void displayQueue(ImageIcon studentImage1, ImageIcon teacherImage1){

        centerPanel.removeAll();
        System.out.println("C");

        JLabel teacherImage2 = new JLabel(teacherImage1);
        System.out.println(this.queue.getHighPLength());
        for(int i = 0; i < (this.queue.getHighPLength());i++){
            System.out.print("0");
            // JLabel image = new JLabel(teacherImage1);
            centerPanel.add(teacherImage2);

        }

        JLabel studentImage2 = new JLabel(studentImage1);
        System.out.println(this.queue.getLowPLength());
        for(int i = 0; i < (this.queue.getLowPLength());i++){
            System.out.print("1");
            // JLabel image = new JLabel(studentImage1);
            centerPanel.add(studentImage2);

        }

        this.revalidate();
        this.repaint();
        this.pack();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
