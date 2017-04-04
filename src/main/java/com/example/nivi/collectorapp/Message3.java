package com.example.niranjan.collectorapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Message3 extends Activity {

	String recvname="";
	String mobile="";
String sendername="";
	Connection conn;
	EditText edmessage;
	String complaint,area,landmark,description,date1,status;
	Button sendmsg;
	ImageButton template;
	TextView t1,t2,t3,t4,t5,t6,t7;
	Button b1,b2;
	HashMap<String,String> usersList1 = null;
	ArrayList<HashMap<String,String>> usersList2 = new ArrayList<HashMap<String,String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messsagelist3);
		Intent intent=getIntent();
		recvname=intent.getStringExtra("name");


		Log.d("Namemessage",recvname);
		//Log.d("Mobilemessage",mobile);

		t1 = (TextView) findViewById(R.id.textView_compname);
		t2 = (TextView) findViewById(R.id.textView_area);
		t3 = (TextView) findViewById(R.id.textView_landmark);
		t4 = (TextView) findViewById(R.id.textView_des);
		t5 = (TextView) findViewById(R.id.textView_date);


		new MessageDisp().execute(recvname);
		

	
	}


	public class MessageDisp extends AsyncTask<String, Void, Boolean> {

		ProgressDialog pDialog ;
		Exception error;
		
		
		ResultSet rs;
		
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        
	        pDialog = new ProgressDialog(Message3.this);
	        pDialog.setTitle("Complaints");
	        pDialog.setMessage("View Complaints...");
	        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }

	    @Override
	    protected Boolean doInBackground(String... args) {
	    	
	    	String receiver = new String(args[0]);
	//    	String sender = new String(args[1]);
	    	
	    	
			
			try {


				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://103.10.235.220:3306/corpcomplaint","root","password");
			} catch (SQLException se) {
				Log.e("ERRO1",se.getMessage());
			} catch (ClassNotFoundException e) {
				Log.e("ERRO2",e.getMessage());
			} catch (Exception e) {
			    Log.e("ERRO3",e.getMessage());
			}
			

			try {
				String COMANDOSQL="select * from official where name='"+recvname+"'";
				Statement statement = conn.createStatement();
				rs = statement.executeQuery(COMANDOSQL);
				while(rs.next()){

						complaint = rs.getString(5);
						area = rs.getString(1);
						landmark = rs.getString(2);
						description = rs.getString(3);
						date1=rs.getString(4);


				}
			/*	String query = "update complaints set status = ? where complaintname = ?";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, "processed");
				preparedStmt.setString(2, recvname);

				// execute the java preparedstatement
				preparedStmt.executeUpdate();*/
				return true;

				
				// Toast.makeText(getBaseContext(),
				// "Successfully Inserted.", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				error = e;
				return false;
//				Toast.makeText(getBaseContext(),"Successfully Registered...", Toast.LENGTH_LONG).show();
			}


	    }

	    @SuppressLint("NewApi")
		@Override
	    protected void onPostExecute(Boolean result1) {
	    	pDialog.dismiss ( ) ;
	    	if(result1)
	    	{

				t1.setText(complaint);
				t2.setText(area);
				t3.setText(landmark);
				t4.setText(description);
				t5.setText(date1);

					
//					System.out.println("ELSE(JSON) LOOP EXE");
					try {// try3 open
						

						
					} catch (Exception e1) {
						Toast.makeText(getBaseContext(), e1.toString(),
								Toast.LENGTH_LONG).show();

					}					
				
            
	    	}else
	    	{
	    		if(error!=null)
	    		{
	    			//Toast.makeText(getBaseContext(),error.getMessage().toString() ,Toast.LENGTH_LONG).show();
	    		}
	    		else
	    		{
	    			Toast.makeText(getBaseContext(),"No Message!!!" ,Toast.LENGTH_LONG).show();
	    		}
	    	}
	    	super.onPostExecute(result1);
	    }
	}
	

}
