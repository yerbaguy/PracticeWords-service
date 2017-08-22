package com.yerbaguy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/insert")
public class DemoService {
	
    //fiinal static String url = "jdbc:mysql://localhost:3306/PracticeWords";
    //final static String user = "root";
    //final static String pass = "";
 
        
 
    @POST
    @Path("/doinsert")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String register(@FormParam("engword") String engword, @FormParam("plword") String plword){
        String result="false";
        int x = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection(url, engword, plword);
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PracticeWords","root","");
                        
            PreparedStatement ps = con.prepareStatement("insert into Word(engword, plword) values(?,?)");
            ps.setString(1, engword);
            //ps.setString(1, "lkjasd");
            ps.setString(2, plword);
            //ps.setString(2, "lkjsd");
            
            x = ps.executeUpdate();
            
            if(x==1){
                result = "true";
            }
            
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println(result);
        
        return result;
    }

}
