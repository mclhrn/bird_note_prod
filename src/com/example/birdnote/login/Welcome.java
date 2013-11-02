package com.example.birdnote.login;

import com.example.birdnote.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Welcome extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.welcome);

	    findViewById(R.id.registerButton).setOnClickListener(
	        new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                // No account, load new account view
	                Intent intent = new Intent(Welcome.this,
	                    Register.class);
	                startActivityForResult(intent, 0);
	            }
	        });

	    findViewById(R.id.loginButton).setOnClickListener(
	        new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                // Existing Account, load login view
	                Intent intent = new Intent(Welcome.this,
	                    Login.class);
	                startActivityForResult(intent, 0);
	            }
	        });
	}

//	public void onBackPressed() {
//	    Intent startMain = new Intent(Intent.ACTION_MAIN);
//	    startMain.addCategory(Intent.CATEGORY_HOME);
//	    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	    startActivity(startMain);
//	    finish();
//	}
}
