import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SubjectAndDate extends JFrame implements ActionListener{
    JComboBox acdYear,y1,y2,y3,y4,section;
    JButton back,submit;
    JLabel msg,selectParameter;

    String textFile;
    String year,sec,sub;

    boolean selectedAll=false;
//    static  String[] year;

    SubjectAndDate()
    {
        this.getContentPane().setBackground(new Color(245, 233, 193));
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(500,500);
        this.setBounds(500,120,500,500);
        this.setLayout(null);
        String[] year=years();
        String[] year1=subject(year[0]);
        String[] year2=subject(year[1]);
        String[] year3=subject(year[2]);
        String[] year4=subject(year[3]);
        String[] sec={"A","B","C"};
        //-----subject--------//
        y1=new JComboBox(year1);
        y2=new JComboBox(year2);
        y3=new JComboBox(year3);
        y4=new JComboBox(year4);
        y1.setBackground(new Color(0x99E1E7));
        y2.setBackground(new Color(0x99E1E7));
        y3.setBackground(new Color(0x99E1E7));
        y4.setBackground(new Color(0x99E1E7));

        y1.addActionListener(this);
        y2.addActionListener(this);
        y3.addActionListener(this);
        y4.addActionListener(this);

        //----section----------//
        section=new JComboBox(sec);
        section.setBackground(new Color(0x99E1E7));
        section.addActionListener(this);
        //------year----------//
        acdYear=new JComboBox(year);
        acdYear.setBackground(new Color(0x99E1E7));
        acdYear.setBounds(10,130,150,50);
        acdYear.addActionListener(this);
        //----msg-----//
        msg=new JLabel("Select Year,Subject and Section");
        msg.setForeground(Color.BLUE);
        msg.setFont(new Font("Times New Roman",Font.BOLD,30));
        msg.setBounds(20,50,450,100);
        //------msg2--------//
        selectParameter=new JLabel("Select all Parameter");
        selectParameter.setForeground(Color.BLUE);
        selectParameter.setFont(new Font("Times New Roman",Font.BOLD,20));

        //--------back button-----//
        back=new JButton("Back");
        back.setBackground(new Color(0x99E1E7));
        back.addActionListener(this);
        back.setFocusable(false);
        back.setBounds(110,300,100,50);
        //-------submit button----//
        submit=new JButton("Submit");
        submit.setBackground(new Color(0x99E1E7));
        submit.addActionListener(this);
        submit.setFocusable(false);
        submit.setBounds(220,300,100,50);
        //---adding----//
        this.add(back);
        this.add(submit);
        this.add(msg);
        this.add(acdYear);
        this.add(selectParameter);
        //---visibility to true---//
        this.validate();
        setVisible(true);

    }
//-----action performed by buttons-----//
    @Override
    public void actionPerformed(ActionEvent e) {
        //------drop down menu year-----//
        this.setBackground(new Color(227, 225, 209));

        if(e.getSource()==acdYear)
        {
            textFile=acdYear.getSelectedItem().toString();
            //System.out.println(textFile);
            acdYear.setEnabled(false);
            if(acdYear.getSelectedIndex()==0)
            {
                year="1st_year";
                y1.setBounds(165,130,150,50);
                this.add(y1);

            }
            if(acdYear.getSelectedIndex()==1)
            {
                year="2nd_year";
                y2.setBounds(165,130,150,50);
                this.add(y2);
            }
            if(acdYear.getSelectedIndex()==2)
            {
                year="3rd_year";
                y3.setBounds(165,130,150,50);
                this.add(y3);

            }
            if(acdYear.getSelectedIndex()==3)
            {
                year="4th_year";
                y4.setBounds(165,130,150,50);
                this.add(y4);
            }
        }
        //------drop down menu subject year1-----//
        if(e.getSource()==y1) {
            textFile = textFile + "_" + y1.getSelectedItem().toString();
            y1.setEnabled(false);
            acdYear.setEnabled(false);
            section.setBounds(320, 130, 150, 50);
            sub=y1.getSelectedItem().toString();
            this.add(section);
        }
        //------drop down menu subject year2-----//
        if(e.getSource()==y2) {
            textFile = textFile + "_" + y2.getSelectedItem().toString();
            y2.setEnabled(false);
            acdYear.setEnabled(false);
            section.setBounds(320, 130, 150, 50);
            sub=y2.getSelectedItem().toString();//-------------------selected subject-----------//
            this.add(section);
        }
        //------drop down menu subject year3-----//
        if(e.getSource()==y3) {
            textFile = textFile + "_" + y3.getSelectedItem().toString();
            y3.setEnabled(false);
            acdYear.setEnabled(false);
            section.setBounds(320, 130, 150, 50);
            sub=y3.getSelectedItem().toString();//-------------------selected subject-----------//
            this.add(section);
        }
        //------drop down menu subject year4-----//
        if(e.getSource()==y4) {
            textFile = textFile + "_" + y4.getSelectedItem().toString();
            y4.setEnabled(false);
            acdYear.setEnabled(false);
            section.setBounds(320, 130, 150, 50);
            sub=y4.getSelectedItem().toString();//---------------selected subject-----------//
            this.add(section);
        }
        //-----drop down menu for sections A B C------//
        if(e.getSource()==section)
        {
            sec=section.getSelectedItem().toString();
            textFile=textFile+"_"+section.getSelectedItem();
            section.setEnabled(false);
            selectedAll=true;
        }
        //-----submit button-----//
        if(e.getSource()==submit)
        {
            if(selectedAll==false)
            {
                selectParameter.setBounds(30,350,400,100);

            }
            else
            {
                new attendancePage(textFile,year,sec,sub);
                this.dispose();
            }
        }
        //------back button-----//
        if(e.getSource()==back)
        {
            new login();
            this.dispose();
        }
    }
    protected String[] years()
    {
        try {

            database_connector c = new database_connector();
            Connection con = c.connection();
            String query="select * from year";
            Statement st =con.createStatement();
            ResultSet rs=st.executeQuery(query);
            int co=0;
            while(rs.next())
            {
                co++;
            }
            String[] lst=new String[co];
            rs=st.executeQuery(query);
            int i=0;
            while (rs.next())
            {
                lst[i++]=rs.getString(1);
                //System.out.println(rs.getString(1));
            }
            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return new String[0];
    }
    protected  String[] subject(String year)
    {
      try {
          database_connector c=new database_connector();
          Connection con=c.connection();
          String query="select * from subjects where year='"+year+"'";
          Statement st =con.createStatement();
          ResultSet rs=st.executeQuery(query);
          int co=0;
          while(rs.next())
          {
              co++;
          }
          String[] lst=new String[co];
          rs=st.executeQuery(query);
          int i=0;
          while (rs.next())
          {
              lst[i++]=rs.getString(2);
              //System.out.println(rs.getString(2));
          }
          return lst;

      }
      catch (Exception e)
      {
          System.out.println(e);
      }
        return new String[0];
    }
}
