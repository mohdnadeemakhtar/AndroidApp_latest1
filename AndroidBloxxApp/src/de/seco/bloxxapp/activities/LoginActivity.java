package de.seco.bloxxapp.activities;

import de.seco.bloxxapp.general.BloxxResources;
import de.seco.bloxxapp.general.LoginTask;
import de.seco.bloxxapp.generated.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements OnClickListener {
	private Button loginButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.loginButton) {
			saveEmail();
			savePassword();
			loginAndShowHomeActivity();
		}
	}
	
	private void saveEmail() {
		EditText email = (EditText) findViewById(R.id.email);
		BloxxResources.USER_EMAIL = email.getText().toString();
	}
	
	private void savePassword() {
		EditText password = (EditText) findViewById(R.id.password);
		BloxxResources.USER_PASSWORD = password.getText().toString();
	}
	
	private void loginAndShowHomeActivity() {
		LoginTask loginTask = new LoginTask(this);
		loginTask.execute();
	}
}