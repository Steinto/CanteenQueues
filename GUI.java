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
    JLabel staffWaitLable;
    JLabel staffWaitTime;
    JLabel studentWaitLable;
    JLabel studentWaitTime;
    JLabel staffInLine;
    JLabel studentsInLine;
    JLabel settings;
    JLabel split;
    JLabel pushingExplanation;
    JLabel pushingstate;

    JButton nextStep;
    JButton reset;
    JButton pushing;
    JLabel empty;
    JLabel data;

    private PQueue queue; 
    String studentImage = "Student.jpg";
    JLabel images;

    String teacherImage = "Teacher.png";

    private double staffAverageTime;
    private double studentAverageTime;

    private boolean pushingState = true;

    private int line = 2;
    private double staffServed;
    private double studentsServed;
    private ArrayList<Integer> studentTimes = new ArrayList<>();
    private ArrayList<Integer> staffTimes = new ArrayList<>();

    public GUI(PQueue queueInput){

        this.queue = queueInput;
        setTitle("Canteen Queues Simulation");
        this.getContentPane().setPreferredSize(new Dimension(600, 400));

        addMouseListener(this);

        labelTitle = new JLabel("WELCOME TO THE SIMULATION");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitle.setAlignmentX(CENTER_ALIGNMENT);
        labelTitle.setForeground(Color.decode("#ffffff"));
        // explanation = new JLabel("Top queue is students and bottom queue is staff");
        // explanation.setAlignmentX(CENTER_ALIGNMENT);
        // explanation.setForeground(Color.decode("#ffffff"));
        time = new JLabel("Minute: 1");
        staffWaitTime = new JLabel("0.0");
        staffWaitLable = new JLabel("Staff average time waited");
        studentWaitTime = new JLabel("0.0");
        studentWaitLable = new JLabel("Student average time waited");
        staffInLine = new JLabel("Staff In The Queue: 0");
        studentsInLine = new JLabel("Students In The Queue: 0");
        // settings = new JLabel("Settings");
        // split = new JLabel("----------------------------------------");
        empty = new JLabel(" ");
        data = new JLabel("Data");
        // pushingExplanation = new JLabel("<html>if this setting is on, staff will <br>push infront of students</html>");
        // pushingstate = new JLabel("pushling is currently: on");

        nextStep = new JButton("next Step");
        nextStep.addActionListener(this);
        reset = new JButton("reset");
        reset.addActionListener(this);
        pushing = new JButton("pushing?");
        pushing.addActionListener(this);

        ImageIcon studentImage1 = new ImageIcon(studentImage);
        images = new JLabel(studentImage1);

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0,2,10,10));
        // centerPanel.getLayout().setHgap(4);
        centerPanel.setBackground(Color.decode("#d5f2e3"));
        this.add(centerPanel, BorderLayout.CENTER);

        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
        titlePanel.setBackground(Color.decode("#545775"));
        this.add(titlePanel, BorderLayout.NORTH);

        titlePanel.add(labelTitle);
        // titlePanel.add(explanation);

        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
        sidePanel.setBackground(Color.decode("#73ba9b"));
        this.add(sidePanel, BorderLayout.LINE_START);

        // sidePanel.add(settings);
        // sidePanel.add(pushing);
        // sidePanel.add(pushingExplanation);
        // sidePanel.add(pushingstate);

        // sidePanel.add(split);

        sidePanel.add(data);
        sidePanel.add(time);
        centerPanel.add(nextStep);
        centerPanel.add(reset);
        sidePanel.add(staffWaitLable);
        sidePanel.add(staffWaitTime);
        sidePanel.add(studentWaitLable);
        sidePanel.add(studentWaitTime);

        centerPanel.add(staffInLine);
        centerPanel.add(studentsInLine);
        // centerPanel.add(empty);
        dialougeIntroduction();

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
                break;
            case "reset":
                resetLogic();
                break;
        }
        this.pack();
    }
    // createDialogExample();

    private void resetLogic(){
        line = 2;
        for (int i = 0; i < this.queue.getHighPLength(); i++){
            this.queue.dequeue();
        }

        int h = this.queue.getLowPLength();
        for (int i = 0; i < h; i++){
            // System.out.println(i);
            this.queue.dequeue();
        }

        
        
        staffInLine.setText("Staff In The Queue: 0");
        studentsInLine.setText("Students In The Queue: 0");
        time.setText("Minute: 0");
        // this.queue.dequeue();
        // this.queue.dequeue();
        // System.out.println(this.queue.getHighPLength());
        // System.out.println(this.queue.getLowPLength());
        studentTimes.clear();
        staffTimes.clear();
        this.staffWaitTime.setText("0.0");
        this.studentWaitTime.setText("0.0");
    }

    public void averageTimeLogic(int inputTime, boolean teacher){
        int time = line - 1;
        double averageStudent = 0;
        double averageStaff = 0;

        if(teacher){
            this.staffTimes.add(time - inputTime);
            // System.out.println(staffTimes.size());
            for (int i = 0; i < staffTimes.size(); i++){
                averageStaff = averageStaff + staffTimes.get(i);
            }
            averageStaff = (averageStaff / staffTimes.size());
            this.staffWaitTime.setText(Double.toString(averageStaff));
        }else{
            this.studentTimes.add(time - inputTime);
            // System.out.println(studentTimes.size());
            for (int i = 0; i < studentTimes.size(); i++){
                // System.out.println(averageStudent);
                // System.out.println(studentTimes.get(i));
                averageStudent = averageStudent + studentTimes.get(i);
            }
            averageStudent = (averageStudent / studentTimes.size());
            double temp = Math.round(averageStudent * 100);
            temp = temp / 100;
            this.studentWaitTime.setText(Double.toString(temp));
        }

        this.pack();
    }

    public void playLogic(){
        // FileUtilities arrivals = new FileUtilities("arrivals - arrivals.csv");
        nextStepLogic(line);
        staffInLine.setText("Staff In The Queue: " + this.queue.getHighPLength());
        studentsInLine.setText("Students In The Queue: " + this.queue.getLowPLength());

        time.setText("Minute: " + (line-1));
        this.line++;
        this.pack();
    }

    // public void allLogic(){
    // FileUtilities arrivals = new FileUtilities("arrivals - arrivals.csv");
    // for (int i = 0;i < 60;i++){
    // nextStepLogic(line);
    // staffInLine.setText("Staff In The Queue: " + this.queue.getHighPLength());
    // studentsInLine.setText("Students In The Queue: " + this.queue.getLowPLength());

    // time.setText("Minute: " + (line-1));
    // this.line++;
    // this.pack();
    // }
    // }

    public void nextStepLogic(int line){
        FileUtilities arrivals = new FileUtilities("arrivals - arrivals.csv");
        if(arrivals.isValidCsv()){
            System.out.println(arrivals.readLine(line));
            // if(arrivals.readLine
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
        }else{
            System.out.println("error");
        }
        // System.out.println(this.queue.getLowPLength());
    }

    public void dialougeIntroduction(){
        JDialog box = new JDialog(this);
        box.setBounds(400,400,450,150);

        TextArea boxInfo = new TextArea("Tutorial.\nSettings are on the left\nData is under setting on the left\nThe left button (next step) will go through the simulation step by step\nThe right button will go through the whole simulation at once");
        boxInfo.setEditable(false);
        box.add(boxInfo);

        box.toFront();
        box.setVisible(true);
        box.setTitle("Welcome");

    }
}
