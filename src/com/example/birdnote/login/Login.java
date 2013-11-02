package com.example.birdnote.login;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.birdnote.MainActivity;
import com.example.birdnote.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	private final static String myurl = "http://birdnote.heroku.com/app_session";
	private SharedPreferences mPreferences;
	private String mUserEmail;
	private String mUserPassword;
	private JSONObject json = new JSONObject();
	private JSONObject jsonResponse;
	private ProgressDialog dialog;
	private Boolean value;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.login);

	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		System.out.println(mPreferences.getString(Register.PASSWORD, "Not Found"));
		System.out.println(mPreferences.getString(Register.USERID, "Not Found"));
	}
	
	public void login(View button) {
	    EditText userEmailField = (EditText) findViewById(R.id.userEmail);
	    mUserEmail = userEmailField.getText().toString();
	    EditText userPasswordField = (EditText) findViewById(R.id.userPassword);
	    mUserPassword = userPasswordField.getText().toString();

	    if (mUserEmail.length() == 0 || mUserPassword.length() == 0) {
	        // input fields are empty
	        Toast.makeText(this, "Please complete all the fields",
	            Toast.LENGTH_LONG).show();
	        return;
	    } else {
	        LoginTask loginTask = new LoginTask();
	        loginTask.execute();
	    }
	}
	
	private class LoginTask extends AsyncTask<Void, Void, Object> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			dialog = ProgressDialog.show(Login.this, "",
					"Logging in...", true);
			dialog.show();
		}
		
		@Override
		protected Object doInBackground(Void... params) {
			try {

				URL url = new URL(myurl);

				//json.put("success", true);
				json.put("email", mUserEmail);
				json.put("password", mUserPassword);

				HttpClient httpclient = new DefaultHttpClient();
				HttpParams myParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(myParams, 10000);
				HttpConnectionParams.setSoTimeout(myParams, 10000);

				try {
					HttpPost httppost = new HttpPost(url.toString());
					httppost.setHeader("Accept", "application/json");
					httppost.setHeader("Content-type", "application/json");

					StringEntity se = new StringEntity(json.toString());
					httppost.setEntity(se);
					HttpResponse response = httpclient.execute(httppost);
					
					String temp = EntityUtils.toString(response.getEntity());
					jsonResponse = new JSONObject(temp);
					
					System.out.println(jsonResponse.toString());
					
					Log.i("tag", temp);
					value = true;
				} catch (ClientProtocolException e) {

				} catch (IOException e) {

				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} finally {

			}
			return value;
		}
		
		@SuppressWarnings("unused")
		protected void onPostExecute(Boolean value) {

			dialog.dismiss();
			if (jsonResponse.has("id")) {
				// everything is ok
//					SharedPreferences.Editor editor = mPreferences.edit();
//					// save the returned password and id into the SharedPreferences
//					
//					editor.putString(USERID, jsonResponse.getString("id"));
//					editor.putString(PASSWORD,
//							jsonResponse.getString("password_digest"));
//					editor.commit();
			}
			else
				System.out.println("Something went wrong!");

//			System.out.println(mPreferences.getString(PASSWORD, "Not Found"));
//			System.out.println(mPreferences.getString(USERID, "Not Found"));

			Toast.makeText(Login.this, "You are now logged in.", Toast.LENGTH_SHORT)
					.show();
			if (value) {
				Intent intent = new Intent(getApplicationContext(), Login.class);
				startActivity(intent);
				finish();
			} else
				Toast.makeText(Login.this,
						"You don't have internet connection", Toast.LENGTH_LONG)
						.show();
		}

		
	}
}
