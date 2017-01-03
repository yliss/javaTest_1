import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ApplicationManager {
	BufferedReader br;
	String optionSelected;
	Connection conn = null;
	Statement stmt = null;
	
	
	public ApplicationManager (){
		
	}
	
	
	public void start(){
		try {
			printMenu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	public void printMenu() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//do{
			
		
		System.out.println("  Menu");
		System.out.println("1.Counts");
		System.out.println("0.Exit");
		optionSelected=br.readLine();
		
		createDataBase();
		createTable();
		insertData();
		//}while (optionSelected.equals("0"));
	}
	
	
	public void createDataBase(){
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn=DriverManager.getConnection("jdbc:sqlite:"+ "C:/EspaciosTrabajo/CommandLineApplication/counts.db");
			
			
		} catch (Exception  e) {
			  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		} System.out.println("Opened database successfully");
	}
	
	public void createTable(){
		try {
			stmt = conn.createStatement();
			String sql = "CREATE TABLE COUNTS (ID INT PRIMARY KEY NOT NULL, TITULAR TEXT NOT NULL)";
			
			stmt.executeUpdate(sql);
			stmt.close();
			//conn.close();
			
		} catch  ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}System.out.println("Table created successfully");
		
	}

	public void insertData (){
		
		try{
			stmt=conn.createStatement();
			
			String sql = "INSERT INTO COUNTS (ID,TITULAR) VALUES (1, 'Juan sandoval')";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO COUNTS (ID,TITULAR) VALUES (2, 'Ana maria bonilla')";
			stmt.executeUpdate(sql);
		}
		catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
		System.out.println("Records created successfully");
	}
}
