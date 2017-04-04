package com.example.niranjan.collectorapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewArea3 extends Activity {
	ListView listView;
	Connection conn;
	Double lat,lon;
	String username;
	protected LocationManager mlocManager;
	String lati,longi,loginname,areaname,areaname1;
	double lativalueup,lativaluedown,logivalueleft,logivalueright;
	String sendername;
	HashMap<String,String> usersList1 = null;
	ArrayList<HashMap<String,String>> usersList2 = new ArrayList<HashMap<String,String>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_area_list3);
		 listView = (ListView) findViewById(R.id.listView1);


		//SharedPreferences preferences=getSharedPreferences("area", Context.MODE_PRIVATE);
		//area=preferences.getString("area",null);
		//SharedPreferences preferences1=getSharedPreferences("username", Context.MODE_PRIVATE);
		//username=preferences1.getString("username",null);




		try{
			 

		
		new QuerySQL().execute();
		}
		catch (Exception e){
			  System.out.println("NumberFormatException: " + e.getMessage());
			  }
	}

//	@SuppressLint("NewApi")
//	private Connection CONN()
//	{
//
//		Connection conn = null;
//		String ConnURL = null;
//		try {
//		
//			
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://ec2-23-21-211-172.compute-1.amazonaws.com:3306/eshadow","eshadowroot","password");		
//		} catch (SQLException se) {
//			Log.e("ERRO1",se.getMessage());
//		} catch (ClassNotFoundException e) {
//			Log.e("ERRO2",e.getMessage());
//		} catch (Exception e) {
//		    Log.e("ERRO3",e.getMessage());
//		}
//		return conn;
//	}
	
//	public void QuerySQL(double lativalueup,double lativaluedown,double logivalueleft,double logivalueright,String sendername){
//
//		HashMap<String,String> usersList1 = null;
//		usersList2 = new ArrayList<HashMap<String,String>>();
//		ResultSet rs;
//		try {
//		String COMANDOSQL="select * from register where latitude<="+lativalueup+" && latitude>="+lativaluedown+"&& longitude>="+logivalueleft+" && longitude<="+logivalueright+" && name!='"+sendername+"'";
//			Statement statement = conn.createStatement();
//			rs = statement.executeQuery(COMANDOSQL);
//		while(rs.next()){
//		
//			usersList1  = new HashMap<String,String>();				
//            usersList1.put("name",rs.getString("name"));                                    
//            usersList1.put("mobile",rs.getString("mobile"));				
//            Log.d("",usersList1.toString());
//            
//            usersList2.add(usersList1);
//			
//		
//		}
//			
//		listView.setAdapter(new CustomBaseAdapter(FriendList.this, usersList2));
//		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> parent, View v,
//					int position, long id) {
//				
//				Intent intent = new Intent(
//						FriendList.this,
//						Message.class);
//				intent.putExtra("name", usersList2.get(position)
//						.get("name"));
//				intent.putExtra("mobile",
//						usersList2.get(position).get("mobile"));
//				intent.putExtra("sendername",loginname);
//				startActivity(intent);
//			}
//		});
//
//	
//			} catch (Exception e) {
//        Log.e("ERRO",e.getMessage());
//		}
//		
//	}

	public class QuerySQL extends AsyncTask<Object, Void, Boolean> {

		ProgressDialog pDialog ;
		Exception error;
		
		
		ResultSet rs;
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        
	        pDialog = new ProgressDialog(ViewArea3.this);
	        pDialog.setTitle("Official Complaints");
	        pDialog.setMessage("Getting official complaints...");
	        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(false);
	        pDialog.show();
	    }

	    @Override
	    protected Boolean doInBackground(Object... args) {
	    	

			try {
				
				
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://103.10.235.220:3306/corpcomplaint","root","password");
			} catch (SQLException se) {
				Log.e("ERRO1",se.getMessage());
			} catch (ClassNotFoundException e) {
				Log.e("ERRO22",e.getMessage());
			} catch (Exception e) {
			    Log.e("ERRO3",e.getMessage());
			}
			

			try {
				String processed = "completed";
				String COMANDOSQL="select * from official ORDER BY `id` DESC";
				Statement statement = conn.createStatement();
				rs = statement.executeQuery(COMANDOSQL);
			while(rs.next()){
				usersList1 = new HashMap<String, String>();
			//	usersList1.put("uname",rs.getString("name"));
			//	usersList1.put("username",rs.getString(2));
				usersList1.put("name",rs.getString(1));
			//	usersList1.put("area",rs.getString(4));
			//	usersList1.put("landmark",rs.getString(5));
			//	usersList1.put("description",rs.getString(6));
			//	usersList1.put("date1",rs.getString(7));
			//	usersList1.put("status",rs.getString(8));
	            Log.d("Friend List Map :",usersList1.toString());
	            
	            usersList2.add(usersList1);
				
			
			}


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
                
			
					
//					System.out.println("ELSE(JSON) LOOP EXE");
					try {// try3 open
						
						listView.setAdapter(new AreaAdapter3(ViewArea3.this, usersList2));
						listView.setOnItemClickListener(new OnItemClickListener() {

							public void onItemClick(AdapterView<?> parent, View v,
													int position, long id) {

								Intent intent = new Intent(
										ViewArea3.this,
										Message3.class);
								intent.putExtra("name", usersList2.get(position)
										.get("name"));

								//intent.putExtra("sendername",loginname);
								startActivity(intent);
							}
						});
						
					} catch (Exception e1) {
						Toast.makeText(getBaseContext(), e1.toString(),
								Toast.LENGTH_LONG).show();

					}					
				
            
	    	}else
	    	{
	    		if(error!=null)
	    		{
	    			Toast.makeText(getBaseContext(),error.getMessage().toString() ,Toast.LENGTH_LONG).show();
	    		}
	    	}
	    	super.onPostExecute(result1);
	    }
	}
	
}
