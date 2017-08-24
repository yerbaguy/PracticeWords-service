package com.yerbaguy;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//import org.codehaus.jettison.json.JSONException;
//import org.codehaus.jettison.json.JSONObject;




import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;



@Path("/mymethods")
//@RestController("/mymethods")
public class MyMethods {
	
	//@POST
	//@Path("/getinfo")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	@RequestMapping(value = "/getinfo", method = RequestMethod.GET, headers = "Accept = application/json")
//	public String getInfo() {
		
//		String name = "info";
		
//		System.out.println(name);
		
//		return name;
		
//	}
	
	
	
	
	@GET
	@Produces("application/json")
	@Path("/getinfo")
	//public String getInfo() throws JSONException, SQLException {
	public String getinfo() throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		
		int countrand = 1;
		//String name = "lkjasd";
		
		String name = this.selectedPlWord();
		
		jsonObject.put("name", name);
		
		System.out.println("getInfo - name:" + name);
		
		//whetherRandPlIsEmptyOrNot();
		//whetherRandPlIsEmptyOrNot(countrand);
		
		String result = "@Produces(\"application/json\")" + jsonObject;
		
		return result;
		//return name;
	}
	
	
	
	
	@GET
	@Produces("application/json")
	@Path("/getsolutionofcomparingwords")
	public String getSolutionOfComparingWords(@QueryParam("result") String result) throws SQLException {
		
		//String englishword = "";
		//int getidofplword = 0;
		
		//String positive_result = "CORRECT";
		//String negative_result = "INCORRECT";
		
		//String result = "";
		
		//int englishwordresult = Integer.parseInt(this.getIdOfEngWord(englishword));
		
		//int polishwordresult = this.getResultsOfPlWord(getidofplword);
		
		//if (englishwordresult == polishwordresult) {
		//	return positive_result;
		//}  else {
		//	return negative_result;
		//}
		

		String englishword = "";
		
		JSONObject jsonObject = new JSONObject();
		
		int countrand = 0;
		//String name = "lkjasd";
		
		//String name = this.selectedPlWord();
		
		//String name = this.getIdOfEngWord(englishword);
		
		String name = result;
		
		jsonObject.put("name", name);
		
		System.out.println("getInfo - name:" + name);
		
		//whetherRandPlIsEmptyOrNot();
		//whetherRandPlIsEmptyOrNot(countrand);
		
		 result = "@Produces(\"application/json\")" + jsonObject;
		
		return result;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	@Path("/countwords")
	//@RequestMapping(value = "/countwords", method = RequestMethod.GET, headers = "Accept = application/json")
	public int countRows() throws SQLException, ClassNotFoundException {
		
		ResultSet resultSet = null;
		//Connection dbConn = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		
		//int x = 0;
		
		int result = 0;
		
		try {
		//	try {
				
				//dbConn = DBConnection.createConnection();
				
				 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PracticeWords","root","90BARTEK90");
				
		//	} catch (Exception e) {
				
				
		//		e.printStackTrace();
		//	}
			
			
			
	//		Statement stmt = dbConn.createStatement();
			
			String query = "SELECT COUNT(*) FROM Word";
			
			pstmt = con.prepareStatement(query);
			
			//ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) AS count FROM user");
			
		    resultSet  = pstmt.executeQuery();
			
			
			while (resultSet.next()) {
				
				result  = resultSet.getInt(1);
			}
		//	dbConn.close();
			
			con.close();
			
		    //result  = resultSet.getInt("count");
			
			
			
			System.out.println("Count rows:" + result);
			
			
			
			
		} catch (SQLException sqle) {
			
			throw sqle;
			
		} catch (Exception e) {
			
			//if (dbConn != null) {
			if (con != null) {
			//	dbConn.close();
				con.close();
			}
			throw e;
		} finally {
			
		//	if ( dbConn != null) {
			if (con != null) {	
		//		dbConn.close();
				con.close();
			}
		}
		
		
		return result; 
	}
	
	
	@Path("/countrand")
//	@RequestMapping(value = "/countrand", method = RequestMethod.GET, headers = "Accept = application/json")
//	public int countRand() throws SQLException {
    public int countRand() throws Exception {
//    public void countRand() throws Exception {
		
		Random rand = new Random();
		
		int countrows = this.countRows();
		
		int min = 1;
		
		
		int countrandom = rand.nextInt((countrows - min) + 1) + min;
		
	//	whetherRandPlIsEmptyOrNot(countrandom); this
	//	updateRandPlWord(countrandom);
		
	    System.out.println("Count Random:" + countrandom);
		
		
		return countrandom;
	}
	
	
	
	@Path("/selectedplword")
	//public String selectedPlWord() throws SQLException {
	public String selectedPlWord() throws Exception {
		
         //Connection dbConn = null;
         Connection con = null;
         PreparedStatement preparedStatement = null;
         
         int getidofplword = 1;
        
         //int countrandom = 0;
         
         int countrand = this.countRand();
         
        // int countrand = 1;
         
         
         //int countrand = this.getResultsOfPlWord(getidofplword);
         
         String query = "SELECT plword FROM Word WHERE id = ? ";
        		 
        		 
		//int x = 0;
		
		int result = 0;
		String plw = "";
		
		try {
		//	try {
				
				//dbConn = DBConnection.createConnection();
				
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PracticeWords","root","90BARTEK90");
				
				//preparedStatement = dbConn.prepareStatement(query);
				preparedStatement = con.prepareStatement(query);
				
				preparedStatement.setInt(1, countrand);
				
		//	} catch (Exception e) {
				
				
		//		e.printStackTrace();
		//	}
			
			
			
			//Statement stmt = dbConn.createStatement();
			
			//String query = "SELECT COUNT(*) FROM Word";
			
			
			
			//ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) AS count FROM user");
			
			//ResultSet resultSet  = stmt.executeQuery(query);
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			
			while (resultSet.next()) {
				
				//result  = resultSet.getInt(1);
				plw = resultSet.getString("plword").toString();
				
				//System.out.println("Count rows:" + result);
				System.out.println("plword:" + plw);
			}
			//dbConn.close();
			con.close();
			
		    //result  = resultSet.getInt("count");
			
			
			
			
			//whetherRandPlIsEmptyOrNot(countrandom);	
			whetherRandPlIsEmptyOrNot(countrand);
			
			
			
			
		} catch (SQLException sqle) {
			
			throw sqle;
			
		} catch (Exception e) {
			
		//	if (dbConn != null) {
			if (con != null) {	
		//		dbConn.close();
				con.close();
			}
			throw e;
		} finally {
			
		//	if ( dbConn != null) {
			if ( con != null) {	
		//		dbConn.close();
				con.close();
			}
		}
		
		
		return plw; 
		
		
		
		
	}
	
	
	@GET
	@Path("/getresultofengword/{engwordid}")
	public int getResultsOfEngWord(@QueryParam("engwordid") int getidofengword) throws SQLException {
		
		//String englishword = "";
		
		//getidofengword = this.getIdOfEngWord(englishword);
		
		//int idofengword = Integer.parseInt(getidofengword);
		
		//return idofengword;
		
		System.out.println("getidofengword:" + getidofengword);
		
		return getidofengword;
		
	}
	
	
	
	
	
	
	
	@GET
	@Path("/randplemptyornot/{counrand}")
	//public int whetherRandPlIsEmptyOrNot() throws SQLException {
	//public boolean whetherRandPlIsEmptyOrNot() throws Exception {
	public boolean whetherRandPlIsEmptyOrNot(@PathParam("countrand") int countrand) throws Exception {	
	    boolean randplwordres;
		
		//Connection dbConn = null;
		
		Connection con = null;
		
		//PreparedStatement preparedStatement = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		//String zero = "NULL";
		
		String englishword = "";
		
		int randplword = 0, randplwordr = 0;
		
		
		//String query = "Select randPlWord from RandPl where randPlWord = ?";
		String query = "Select randPlWord from RandPl where id = 1";
		
		
		try {
			
		//	try {
				
				  // dbConn = DBConnection.createConnection();
				   
				   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PracticeWords","root","90BARTEK90");
				   
				   //preparedStatement = dbConn.prepareStatement(query);
				   stmt = con.createStatement();
				   //preparedStatement.setString(1, zero);
				   //preparedStatement.setInt(1, randplword);
				   
				   resultSet = stmt.executeQuery(query);
				
		//	} catch (Exception e) {
				
		//		e.printStackTrace();
		//	}
		       
			
			 //ResultSet resultSet = preparedStatement.executeQuery();
			 
			 
			    resultSet.next();
			// while (resultSet.next()) {
				 
				 randplword = resultSet.getInt("randPlWord");
				 System.out.println("randplemptyornot:" + randplword);
			// }
			
	//		 updateRandPlWord();
			
		} catch (SQLException sqle) {
			
			throw sqle;
		} catch (Exception e) {
			
			if ( con != null) {
				
				 con.close();
			} 
			throw e;
		} finally {
			
			if ( con != null) {
				
				 con.close();
			}
		}
		
		
			
			
			 //  System.out.println("OK");
		   randplwordres = true;
		   
		   updateRandPlWord(countrand);
		   
	//	} else {                  switched off
	//	   randplwordres = false; switched off
		   
	//	   insertIntoRandPlWord(); switched off
		   
	//	}                          switched off
		
		
	//	getIdOfEngWord(englishword, countrandom);
		
		System.out.println("randplwordres:" + randplwordres);
		
		return randplwordres;
		
		//	 else 
				 
			 // System.out.println("NO");
	//	return false;
				 
				 
		//return randplwordr;
		
	}
	
	
	
	
	
	
	
	@GET
	@Path("/getresultofplword/{getidofplword}")
	public int getResultsOfPlWord(@QueryParam("getidofplword") int getidofplword) throws SQLException {
		
		//getidofplword = this.countRand();
		
		//System.out.println(getidofplword);
		
		//return getidofplword;
		
		
	    //Connection dbConn = null;
	    Connection con = null;
        PreparedStatement preparedStatement = null;
        
        //int countrand = this.countRand();
        
        int randplwordid = 1;
        
        int randplword = 0;
        
        //String query = "SELECT plword FROM Word WHERE id = ? ";
        String query = "SELECT randPlWord FROM RandPl WHERE id = ?";
       		 
       		 
		//int x = 0;
		
		int result = 0;
		String plw = "";
		
		try {
		//	try {
				
				//dbConn = DBConnection.createConnection();
				
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PracticeWords","root","90BARTEK90");
				
				preparedStatement = con.prepareStatement(query);
				
				//preparedStatement.setInt(1, countrand);
				preparedStatement.setInt(1, randplwordid);
				//preparedStatement.setInt(1, getidofplword);
				
		//	} catch (Exception e) {
				
				
		//		e.printStackTrace();
		//	}
			
			
			
			//Statement stmt = dbConn.createStatement();
			
			//String query = "SELECT COUNT(*) FROM Word";
			
			
			
			//ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) AS count FROM user");
			
			//ResultSet resultSet  = stmt.executeQuery(query);
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			
			while (resultSet.next()) {
				
				//result  = resultSet.getInt(1);
				//plw = resultSet.getString("plword").toString();
				
				randplword = resultSet.getInt(1);
				
				//System.out.println("Count rows:" + result);
				//System.out.println("plword:" + plw);
				System.out.println("randplword:" + randplword);
			}
			con.close();
			
		    //result  = resultSet.getInt("count");
			
			
			
			
			
			
			
			
			
		} catch (SQLException sqle) {
			
			throw sqle;
			
		} catch (Exception e) {
			
		//	if (dbConn != null) {
			if (con != null) {
				//dbConn.close();
				con.close();
			}
			throw e;
		} finally {
			
		//	if ( dbConn != null) {
			if ( con != null) {
				//dbConn.close();
			    con.close();
			}
		}
		
		
		//return plw;
		
		return randplword;
		
		
		
		
	}
	
	
	
	
	@GET
	@Path("/updaterandplword/{countrandom}")
	//public void updateRandPlWord() throws Exception {
	public void updateRandPlWord(@PathParam("countrandom") int countrandom) throws Exception {	
		//Connection dbConn = null;
		
		Connection con = null;
		
		
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		
		
	//	int countrand = this.countRand();
		
	//	int countrand = 1;
		
		String query = "update RandPl set randPlWord = ?";
		
		try {
		//	try {
				
				//dbConn = DBConnection.createConnection();
				
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PracticeWords","root","90BARTEK90");
				
				
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, countrandom);
				
		//	} catch (Exception e) {
				
		//		e.printStackTrace();
		//	}
			
			
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException sqle) {
			
			throw sqle;
		} catch (Exception e) {
			
			if (con != null) {
				
				con.close();
		
			}
			throw e;
		} finally {
			
			if (con != null) {
				
				con.close();
			}
		}
		
	
		
		//return result;
	}
	
	
	
	@Path("/insertintorandplword")
	//public boolean insertIntoRandPlWord() throws SQLException, Exception {
	public boolean insertIntoRandPlWord() throws SQLException, Exception {	
		//Connection dbConn = null;
		
		Connection con = null;
		
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		
		//boolean name;
		boolean inserted;
             
		//inserted = false;
		
		int countrand = this.countRand();
		
		//int countrand = 1;
		
		String query = "insert into RandPl (randplword) values (?)";
		
		try {
			
     	//	dbConn = DBConnection.createConnection();
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PracticeWords","root","90BARTEK90");
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, countrand);
			
		     if (preparedStatement.execute()) {
		    	 
		    	 inserted = true;
		    	 
		     } else {
		    
		    	 inserted = false;
		     }
		     
		     
		     System.out.println(inserted);
			
			
		} catch (SQLException sqle) {
			
			throw sqle;
		} catch (Exception e) {
			
		   if ( con != null) {
			    con.close();
		   }
		   throw e;
		} finally {
			
			if ( con != null) {
				
				 con.close();
			}
		}
	
		return inserted;
		//name = true;
		
		//System.out.println(name);
		
		//return name;
		
		
	//	try {
		
	//		String name = "lkjads";
			
	//		System.out.println(name);
			
			
			
	//	} catch (Exception e) {
			
	//		e.printStackTrace();
	//	}
		
		//return inserted;
		//return name;
	}
	
	
	
	
	
	
	
	
	
	
}
