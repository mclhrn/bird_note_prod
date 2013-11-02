package com.example.birdnote.login;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
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

public class Register extends Activity {

	private SharedPreferences mPreferences;
	public static final String USERID = "ID";
	public static final String PASSWORD = "PASSWORD";
	
	private String mRealName;
	private String mUserEmail;
	private String mUserName;
	private String mUserPassword;
	private String mUserPasswordConfirmation;
	private String myurl = "http://birdnote.herokuapp.com/app_registration";
	private JSONObject json = new JSONObject();
	private JSONObject jsonResponse;
	private ProgressDialog dialog;
	private Boolean value;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
	}

	public void registerNewAccount(View button) {
		EditText realNameField = (EditText) findViewById(R.id.realName);
		mRealName = realNameField.getText().toString();
		EditText userNameField = (EditText) findViewById(R.id.userName);
		mUserName = userNameField.getText().toString();
		EditText userEmailField = (EditText) findViewById(R.id.userEmail);
		mUserEmail = userEmailField.getText().toString();
		EditText userPasswordField = (EditText) findViewById(R.id.userPassword);
		mUserPassword = userPasswordField.getText().toString();
		EditText userPasswordConfirmationField = (EditText) findViewById(R.id.userPasswordConfirmation);
		mUserPasswordConfirmation = userPasswordConfirmationField.getText()
				.toString();

		if (mRealName.length() == 0 || mUserEmail.length() == 0
				|| mUserName.length() == 0 || mUserPassword.length() == 0
				|| mUserPasswordConfirmation.length() == 0) {
			// input fields are empty
			Toast.makeText(this, "Please complete all the fields",
					Toast.LENGTH_LONG).show();
			return;
		} else {
			if (!mUserPassword.equals(mUserPasswordConfirmation)) {
				// password doesn't match confirmation
				Toast.makeText(
						this,
						"Your password doesn't match confirmation, check again",
						Toast.LENGTH_LONG).show();
				return;
			} else {
				// everything is ok!
				RegisterTask registerTask = new RegisterTask();
				registerTask.execute();
			}
		}
	}

	// async inner class here
	private class RegisterTask extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			dialog = ProgressDialog.show(Register.this, "",
					"Creating account...", true);
			dialog.show();
		}

		@Override
		protected Boolean doInBackground(String... urls) {
			try {

				URL url = new URL(myurl);

				json.put("name", mRealName);
				json.put("email", mUserEmail);
				json.put("nickname", mUserName);
				json.put("password", mUserPassword);
				json.put("password_confirmation", mUserPasswordConfirmation);

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

		@Override
		protected void onPostExecute(Boolean value) {

			dialog.dismiss();
			try {
				if (jsonResponse.has("id")) {
					// everything is ok
					SharedPreferences.Editor editor = mPreferences.edit();
					// save the returned password and id into the SharedPreferences
					
					editor.putString(USERID, jsonResponse.getString("id"));
					editor.putString(PASSWORD,
							jsonResponse.getString("password_digest"));
					editor.commit();
				}
				else
					System.out.println("Something went wrong!");
			} catch (JSONException e) {
				e.printStackTrace();
			}

			System.out.println(mPreferences.getString(PASSWORD, "Not Found"));
			System.out.println(mPreferences.getString(USERID, "Not Found"));

			Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT)
					.show();
			if (value) {
				Intent intent = new Intent(getApplicationContext(), Login.class);
				startActivity(intent);
				finish();
			} else
				Toast.makeText(Register.this,
						"You don't have internet connection", Toast.LENGTH_LONG)
						.show();
		}
	}
}
