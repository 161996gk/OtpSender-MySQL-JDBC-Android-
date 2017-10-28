package com.keep.otpsender;
import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Gaurav on 03-09-2017.
 */

public class Asycn extends AsyncTask<Void,Void,Void> {//extract phone no from otp table
    ArrayList<Temp> al;
    public Asycn()
    {
        al=new ArrayList<Temp>();
    }
    @Override
    protected Void doInBackground(Void... params) {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String loc="jdbc:mysql://192.168.54.211:3306/prac";
            String username="root";
            String pass="";
            Connection con= DriverManager.getConnection(loc,username,pass);
            String query="Select phoneno,flag from userdetails where flag=0";
            PreparedStatement pst=con.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                Temp p=new Temp();
                p.phoneno=rs.getLong("phoneno");
                al.add(p);
            }
        }
        catch(ClassNotFoundException ae)
        {
            System.out.println("Class Not Found");
        }
        catch(SQLException ae)
        {
            ae.printStackTrace();
            System.out.println("SQL");
        }
        return null;
    }
    public ArrayList<Temp> toWhomOtp()
    { System.out.print(al.size());
        return al;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity obj=new MainActivity();
        obj.al=al;
        obj.sendMessage();
    }
}
class Async extends AsyncTask<ArrayList<Temp>,Void,Void> {//update otp table
    @Override
    protected Void doInBackground(ArrayList<Temp>... params) {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String loc="jdbc:mysql://192.168.54.211:3306/prac";
            String username="root";
            String pass="";
            Connection con= DriverManager.getConnection(loc,username,pass);
            String up="Update userdetails set flag=?,otp=? where phoneno=?";
            PreparedStatement pst=con.prepareStatement(up);
            for(Temp ob:params[0]) {
                pst.setInt(1, ob.flag);
                pst.setInt(2, ob.otp);
                pst.setLong(3, ob.phoneno);
                int i = pst.executeUpdate();
            }

        }
        catch(ClassNotFoundException ae)
        {
            System.out.println("Class Not Found");
        }
        catch(SQLException ae)
        {
            ae.printStackTrace();
            System.out.println("SQL");
        }
        return null;
    }
}
class Temp{
    Long phoneno;
    int flag;
    int otp;
}
