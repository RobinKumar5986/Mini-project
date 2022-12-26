import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
public class attendancePage extends JFrame implements ActionListener {
    boolean submitted=false;
    JButton present,absent,submit;
    //JTextField usn;
    String[] studentList;
    int[] totalPresent;
    int prIndex=0;
    int count=0,msgSowCount=1,tableCount=0,presentCount=0,absentCount=0;
    DefaultTableModel studentTable;
    //-----menu bar-----//
    JMenuBar menu;

    JMenu newSub;
    JMenu studAtd;
    JMenu help;
    JMenuItem fileNew;
    JMenuItem fileExit;
    JMenuItem totalAtd;
    //-----------------------//
    JTable table;
    JPanel buttonFrame,sidePanel,totalAtdPanel;
    JLabel usnDisplay,presentAbsentLabel,yearSection;
    attendancePage(String textFile, String year, String sec, String sub)
    {
        System.out.println("year: "+year+"section:"+sec);
        this.getContentPane().setBackground(new Color(245, 233, 193, 219));
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        //------current time---------//
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat timeOnly = new SimpleDateFormat("HH:mm:ss");
        //System.out.println(timeOnly.format(cal.getTime()));
        String check=year+sec;
        String yearUsnFile=year+"_"+"USN"+"_"+sec;
        //------date string-------//
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String s=dtf.format(now);
        //System.out.println("date :"+s);
        textFile=textFile+"_"+s;
        //System.out.println(textFile);
        //-----J frame----//
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500,120,550,550);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new GridLayout(1,2));
        //this.setLayout(null);
//        this.setLayout(new CardLayout());
        //-------menu bar-----//
        menu=new JMenuBar();
        newSub=new JMenu("File");
        studAtd=new JMenu("Attendance");
        help=new JMenu("Help");
        //----KEY BORD SHORTCUT-----//
        newSub.setMnemonic(KeyEvent.VK_F);
        studAtd.setMnemonic(KeyEvent.VK_A);
        help .setMnemonic(KeyEvent.VK_H);
        //----
        newSub.setForeground(Color.BLACK);
        newSub.setFont(new Font("Agency FB",Font.BOLD,15));
        fileNew=new JMenuItem("New");
        fileExit=new JMenuItem("Exit");
        //------key-bord Shortcut-------//
        fileNew.setMnemonic(KeyEvent.VK_N);
        fileExit.setMnemonic(KeyEvent.VK_ESCAPE);
        //----action Listener---//
        fileNew.addActionListener(this);
        fileExit.addActionListener(this);
        //----------------------//

        newSub.add(fileNew);
        newSub.add(fileExit);
        //----
        studAtd.setForeground(Color.BLACK);
        studAtd.setFont(new Font("Agency FB",Font.BOLD,15));
        totalAtd=new JMenuItem("Total Attendance");
        totalAtd.addActionListener(this);
        studAtd.add(totalAtd);
        //----
        help.setForeground(Color.BLACK);
        help.setFont(new Font("Agency FB",Font.BOLD,15));
        //---------------//
        menu.add(newSub);
        menu.add(studAtd);
        menu.add(help);
        menu.setBackground(Color.GRAY);
//------------------------------------------------//
        //-----file reader---//

/*        if(check.equals("1A")) {
            //------counting the number of student from 0 to n-1-----//
            try {

                FileReader usn = new FileReader("1_USN_A.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                while ((line = br.readLine())!= null) {

                    count++;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
            //------loading the student lint into the array-----//
            try {
                FileReader usn = new FileReader("1_USN_A.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                //System.out.println("---------------------");
                studentList = new String[count+1];
                //-------total Present---------//

                int i = 0;
                while (i < count) {
                    //System.out.println(line);
                    line = br.readLine();
                    studentList[i++] = line;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
        }
        if(check.equals("1B")) {
            //------counting the number of student from 0 to n-1-----//
            try {

                FileReader usn = new FileReader("1_USN_B.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                while ((line = br.readLine()) != null) {
                    //1System.out.println(line);
                    count++;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
            //------loading the student lint into the array-----//
            try {
                FileReader usn = new FileReader("1_USN_B.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                //System.out.println("---------------------");
                studentList = new String[count+1];
                int i = 0;
                //-------total Present---------//
                //totalPresent=new int[count+1];
                while (i < count) {
                    //System.out.println(line);
                    line = br.readLine();
                    studentList[i++] = line;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
        }
        if(check.equals("1C")) {
            //------counting the number of student from 0 to n-1-----//
            try {

                FileReader usn = new FileReader("1_USN_C.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                while ((line = br.readLine()) != null) {
                    //1System.out.println(line);
                    count++;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
            //------loading the student lint into the array-----//
            try {
                FileReader usn = new FileReader("1_USN_C.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                //System.out.println("---------------------");
                studentList = new String[count];
                //-------total Present---------//
                //totalPresent=new int[count+1];
                int i = 0;
                while (i < count) {
                    //System.out.println(line);
                    line = br.readLine();
                    studentList[i++] = line;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }

        }
        if(check.equals("2A")) {
            //------counting the number of student from 0 to n-1-----//
            try {

                FileReader usn = new FileReader("2_USN_A.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                while ((line = br.readLine()) != null) {
                    //1System.out.println(line);
                    count++;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
            //------loading the student lint into the array-----//
            try {
                FileReader usn = new FileReader("2_USN_A.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                //System.out.println("---------------------");
                studentList = new String[count];
                int i = 0;
                //-------total Present---------//
                //totalPresent=new int[count+1];
                while (i < count) {
                    //System.out.println(line);
                    line = br.readLine();
                    studentList[i++] = line;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }

        }
        if(check.equals("2B")) {
            //------counting the number of student from 0 to n-1-----//
            try {

                FileReader usn = new FileReader("2_USN_B.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                while ((line = br.readLine()) != null) {
                    //1System.out.println(line);
                    count++;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
            //------loading the student lint into the array-----//
            try {
                FileReader usn = new FileReader("2_USN_B.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                //System.out.println("---------------------");
                studentList = new String[count];
                int i = 0;
                //-------total Present---------//
               // totalPresent=new int[count+1];
                while (i < count) {
                    //System.out.println(line);
                    line = br.readLine();
                    studentList[i++] = line;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }

        }
        if(check.equals("2C")) {
            //------counting the number of student from 0 to n-1-----//
            try {

                FileReader usn = new FileReader("2_USN_C.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                while ((line = br.readLine()) != null) {
                    count++;
                }
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
            //------loading the student lint into the array-----//
            try {
                FileReader usn = new FileReader("2_USN_C.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                studentList = new String[count];
                //-------total Present---------//
                //totalPresent=new int[count+1];
                int i = 0;
                while (i < count) {
                    line = br.readLine();
                    studentList[i++] = line;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
        }
        if(check.equals("3A")) {
            //------counting the number of student from 0 to n-1-----//
            try {

                FileReader usn = new FileReader("3_USN_A.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                while ((line = br.readLine()) != null) {
                    //1System.out.println(line);
                    count++;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
            //------loading the student lint into the array-----//
            try {
                FileReader usn = new FileReader("3_USN_A.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                //System.out.println("---------------------");
                studentList = new String[count];
                //-------total Present---------//
                //totalPresent=new int[count+1];
                int i = 0;
                while (i < count) {
                    //System.out.println(line);
                    line = br.readLine();
                    studentList[i++] = line;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }

        }
        if(check.equals("3B")) {
            //------counting the number of student from 0 to n-1-----//
            try {

                FileReader usn = new FileReader("3_USN_B.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                while ((line = br.readLine()) != null) {
                    //1System.out.println(line);
                    count++;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
            //------loading the student lint into the array-----//
            try {
                FileReader usn = new FileReader("3_USN_B.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                //System.out.println("---------------------");
                studentList = new String[count];
                //-------total Present---------//
               // totalPresent=new int[count+1];
                int i = 0;
                while (i < count) {
                    //System.out.println(line);
                    line = br.readLine();
                    studentList[i++] = line;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
        }
        if(check.equals("3C")) {
            //------counting the number of student from 0 to n-1-----//
            try {

                FileReader usn = new FileReader("3_USN_C.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                while ((line = br.readLine()) != null) {
                    //1System.out.println(line);
                    count++;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
            //------loading the student lint into the array-----//
            try {
                FileReader usn = new FileReader("3_USN_C.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                //System.out.println("---------------------");
                studentList = new String[count];
                //-------total Present---------//
              //  totalPresent=new int[count+1];
                int i = 0;
                while (i < count) {
                    //System.out.println(line);
                    line = br.readLine();
                    studentList[i++] = line;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
        }
        if(check.equals("4A")) {
            //------counting the number of student from 0 to n-1-----//
            try {

                FileReader usn = new FileReader("4_USN_A.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                while ((line = br.readLine()) != null) {
                    //1System.out.println(line);
                    count++;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
            //------loading the student lint into the array-----//
            try {
                FileReader usn = new FileReader("4_USN_A.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                //System.out.println("---------------------");
                studentList = new String[count];
                int i = 0;
                //-------total Present---------//
               // totalPresent=new int[count+1];
                while (i < count) {
                    //System.out.println(line);
                    line = br.readLine();
                    studentList[i++] = line;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }

        }
        if(check.equals("4B")) {
            //------counting the number of student from 0 to n-1-----//
            try {

                FileReader usn = new FileReader("4_USN_B.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                while ((line = br.readLine()) != null) {
                    //1System.out.println(line);
                    count++;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
            //------loading the student lint into the array-----//
            try {
                FileReader usn = new FileReader("4_USN_B.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                //System.out.println("---------------------");
                studentList = new String[count];
                int i = 0;
                //-------total Present---------//
                //totalPresent=new int[count+1];
                while (i < count) {
                    //System.out.println(line);
                    line = br.readLine();
                    studentList[i++] = line;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }

        }
        if(check.equals("4C")) {
            //------counting the number of student from 0 to n-1-----//
            try {

                FileReader usn = new FileReader("4_USN_C.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                while ((line = br.readLine()) != null) {
                    //1System.out.println(line);
                    count++;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }
            //------loading the student lint into the array-----//
            try {
                FileReader usn = new FileReader("4_USN_C.txt");
                BufferedReader br = new BufferedReader(usn);
                String line;
                //System.out.println("---------------------");
                studentList = new String[count];
                int i = 0;
                //-------total Present---------//
              //  totalPresent=new int[count+1];
                while (i < count) {
                    //System.out.println(line);
                    line = br.readLine();
                    studentList[i++] = line;
                }
                //System.out.println(count);
                usn.close();
            } catch (Exception excp) {
                System.out.println("not able to open file" + excp);
            }

        }
        //*/

        studentList = stdLst(year,sec);
        count =studentList.length;


        //-----present and absent button------//
        present=new JButton("Present");
        absent=new JButton("Absent");
        buttonFrame=new JPanel();
        //buttonFrame.setBackground(new Color(245, 233, 193, 219));
        buttonFrame.setLayout(null);
        present.setBounds(10+20,220+50,100,50);
        absent.setBounds(120+20,220+50,100,50);
        present.setFocusable(false);
        absent.setFocusable(false);
        present.addActionListener(this);//action listener
        absent.addActionListener(this);//action listener
        buttonFrame.setBackground(new Color(245, 233, 193));
        buttonFrame.setBounds(0,200,500,200);
        buttonFrame.add(present);
        buttonFrame.add(absent);
        //-----submit button----//
        submit=new JButton("Submit");
        submit.setBounds(50+20,300+50,130,50);
        submit.setFocusable(false);
        submit.setEnabled(false);
        //String finalTextFile = textFile;
        //String finalTextFile1 = textFile;
        String tmpRef=textFile.substring(0,textFile.length()-11);
       submit.addActionListener(e->submit(sec,sub,s,tmpRef,yearUsnFile,timeOnly.format(cal.getTime())));
        buttonFrame.add(submit);
        //-----msg-------//
        usnDisplay = new JLabel();
        usnDisplay.setBounds(30, 130, 200, 50);
        usnDisplay.setForeground(Color.blue);
        usnDisplay.setFont(new Font("Times New Roman",Font.BOLD,25));
        usnDisplay.setText(studentList[msgSowCount-1]);
        usnDisplay.validate();
        buttonFrame.add(usnDisplay);
        //-------total Present---------//
        //totalPresent=new int[msgSowCount];
        totalPresent=new int[count];
        //System.out.println("count near :"+count);
        //--------creating table-------//
        String[] col={"USN","Present/Absent"};
        String[][] row={{s,"Number of Student "+(count)}};
        studentTable=new DefaultTableModel(row,col);
        table=new JTable(studentTable);
        table.getTableHeader().setReorderingAllowed(false);//stops table from dragging
        table.setEnabled(false);
        table.setBackground(new Color(171, 211, 227));
        //----present Absent Label----//
        presentAbsentLabel=new JLabel();
        presentAbsentLabel.setBounds(10, 60, 200, 50);
        presentAbsentLabel.setForeground(Color.blue);
        presentAbsentLabel.setFont(new Font("Times New Roman",Font.BOLD,18));
        buttonFrame.add(presentAbsentLabel);
        //-----year section Label----//
        yearSection=new JLabel();
        yearSection.setBounds(5, 20, 200, 50);
        yearSection.setForeground(Color.blue);
        yearSection.setFont(new Font("Times New Roman",Font.BOLD,25));
        String yearSec;
        {if(year.equals("1"))
            yearSec="1st";
        else if(year.equals("2"))
            yearSec="2nd";
        else if (year.equals("3"))
            yearSec="3rd";
        else
            yearSec="4th";}
        yearSection.setText(yearSec+" Year "+sec+" Section");
        buttonFrame.add(yearSection);
        //--------black Panel-----//
//        sidePanel=new JPanel();
//        sidePanel.setBackground(new Color(9, 8, 8));
//        sidePanel.setSize(275,1080);
//        sidePanel.setAlignmentX(JFrame.RIGHT_ALIGNMENT);
    //-----setting visible------//
//        buttonFrame.add(sidePanel);
        this.add(new JScrollPane(table));
        this.add(new JScrollPane(buttonFrame));
        this.setJMenuBar(menu);
//        this.add(totalAtdPanel);
        this.setVisible(true);
    }

    private void submit(String sec,String sub,String date ,String tmpRef,String yearUsnFile,String time) {

        try {
            submit.setEnabled(false);
            submitted = true;
            //System.out.println(yearUsnFile+" year usn file");
            //FileWriter fa = new FileWriter("C:\\Users\\sachi\\Desktop\\attandancr management\\ooc_project_1\\Attendance Data\\" + tmpRef + ".txt", true);
            //System.out.println(tmpRef+"  tenpref");
            int one_time = 0;
            //int counter = 0;
            for (int i = 1; i < table.getRowCount(); i++) {
                for (int j = 1; j < table.getColumnCount(); j++) {
                    //fa.write(table.getValueAt(0, j) + "\t");
                    //counter++;
                    //System.out.println( table.getValueAt(i, 0) +"  " + table.getValueAt(i, 1));
                    String u=table.getValueAt(i, 0).toString();
                    String p_a=table.getValueAt(i, 1).toString();
                    database_connector c=new database_connector();
                    Connection con=c.connection();
                    Statement st=con.createStatement();
                    String query1="select atd from student_data where usn='"+u+"'";
                    String query2="select total_atd from student_data where usn='"+u+"'";
                    ResultSet rs=st.executeQuery(query1);
                    rs.next();
                    int atd=rs.getInt(1);
                    //System.out.println("atd :"+atd);
                    rs=st.executeQuery(query2);
                    rs.next();
                    int total_atd=rs.getInt(1);
                    total_atd++;
                    if(p_a.matches("Present")){atd++;}
                    String query3="update student_data set " +
                            "dates='"+date+"',atd=? ,subject_code=? ,total_atd=? " +
                            "where usn=? and section=?;";
                    PreparedStatement ps=con.prepareStatement(query3);
                    ps.setInt(1,atd);
                    ps.setString(2,sub);
                    ps.setInt(3,total_atd);
                    ps.setString(4,u);
                    ps.setString(5,sec);
                    int rows=ps.executeUpdate();
                    //System.out.println("updated rows : "+rows);
                }
            }
//                while (one_time < 1) {
//                    fa.write(table.getValueAt(0, 0) + "    " + time);
//                    one_time++;
////                    System.out.println("time:"+time);
//                }
//                fa.write("\n");
//            }
//
//            fa.close();
//            //-----input in main Fail(total Attendance)-----//
//            FileReader usn = new FileReader("C:\\Users\\sachi\\Desktop\\attandancr management\\ooc_project_1\\total attandance data\\" + yearUsnFile + "_total.txt");
//            //System.out.println(yearUsnFile+"year usn file ");
//            //FileWriter fr=new FileWriter(yearUsnFile+".txt");
//            BufferedReader br = new BufferedReader(usn);
//            String line;
//            String[] dataHolder = new String[count];//hold the data of the new
//            int xyx = 0;
//            while ((line = br.readLine()) != null) {
//                int n = line.length();
//                char ch = line.charAt(n - 3);
//                int a = ch - '0';
//                a = a + totalPresent[xyx];
//                String subLine = line.substring(0, line.length() - 3);
//                //String str= String.valueOf(line.charAt(line.length()-3));
//                String temp = subLine + a;
//                dataHolder[xyx++] = temp;
////                System.out.println(temp);
//            }
//            //System.out.println(count);
//            usn.close();
//            writer wr = new writer();
////            System.out.println(counter+"counter "+count);
//            wr.writerFunction(dataHolder, yearUsnFile, count);
        } catch (Exception exc) {
            System.out.println(exc);
        }
        //dataUpdate(check);
       //for(int e:totalPresent)
        //{
           // System.out.println(e);
        //}

    }
    //public void dataUpdate(String check)
//    {
//        System.out.println(check);

//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //-------Present key--------//
        if(e.getSource()==present)
        {
            //System.out.println(count);
            //-------USN Display--------//
            if(tableCount<count) {
                studentTable.insertRow(msgSowCount, new Object[]{studentList[tableCount++],"Present" });
                if(msgSowCount<count){
                usnDisplay.setText(studentList[msgSowCount]);
                }
                totalPresent[prIndex++]=1;//present in list
                msgSowCount++;
                presentCount++;
                presentAbsentLabel.setText("Present:"+presentCount+" Absent:"+absentCount);
                if(msgSowCount==count+1)
                {
                    submit.setEnabled(true);
                }
            }
            else {
                usnDisplay.setText("  Empty");
            }
        }
        //-------absent key--------//
        if(e.getSource()==absent)
        {
            //-------USN Display--------//
            if(tableCount<count) {
                studentTable.insertRow(msgSowCount, new Object[]{studentList[tableCount++],"Absent" });
                if(msgSowCount<count){
                usnDisplay.setText(studentList[msgSowCount]);}
                totalPresent[prIndex++]=0;//Absent in list
                msgSowCount++;
                absentCount++;
                presentAbsentLabel.setText("Present:"+presentCount+" Absent:"+absentCount);
                if(msgSowCount==count+1)
                {
                    submit.setEnabled(true);
                }
            }
            else {
                usnDisplay.setText("  Empty");
//                //------------------------//
//                for(int i=0;i<table.getRowCount();i++)
//                {
//                    for(int j=0;j<table.getColumnCount();j++)
//                    {
//                        System.out.printf("%s\t",table.getValueAt(i,j));
//                    }
//                    System.out.println();
//                }
            }

        }
        //-------menu bar action listener-------//

        if(e.getSource()==fileNew)
        {
            new SubjectAndDate();
            this.dispose();
        }
        if(e.getSource()==fileExit)
        {
            System.exit(0);
        }
        if(e.getSource()==totalAtd)
        {
          new totalAttendance();
        }
    }
    protected String[] stdLst(String year,String sec)
    {
        try {
            database_connector c = new database_connector();
            Connection con = c.connection();
            String query1="select count(usn) from student_data where year='"+year+"' and section='"+sec+"';";
            Statement st=con.createStatement();
            ResultSet rs =st.executeQuery(query1);
            rs.next();
            int n=rs.getInt(1);
            System.out.println("n:"+n);
            String[] lst=new String[n];
            String query2="select usn from student_data where year='"+year+"' and section='"+sec+"';";
            //st=con.createStatement();
            ResultSet rk =st.executeQuery(query2);
            int i=0;
            while(rk.next())
            {
                lst[i]=rk.getString(1);
                //System.out.println(lst[i]);
                i++;

            }
            return lst;
        }
        catch (Exception e){
            System.out.println(e);
            return  new String[0];
        }
        //return  new String[0];
    }
}