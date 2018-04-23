package com.siemens.android.calculateurautonomie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class UserAreaActivity extends Activity {

    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
    private Button btn_calcul;
    private Button btn_historique;
    private Button btn_profil;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        txtName = findViewById(R.id.name);
        txtEmail = findViewById(R.id.email);
        btn_calcul = findViewById(R.id.btn_calcul);
        btn_historique = findViewById(R.id.btn_historique);
        btn_profil = findViewById(R.id.btn_profil);
        btnLogout = findViewById(R.id.btnLogout);

        btn_calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calculIntent = new Intent(UserAreaActivity.this, SystemChoiceActivity.class);
                UserAreaActivity.this.startActivity(calculIntent);
            }
        });

        btn_historique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historiqueIntent = new Intent(UserAreaActivity.this, historiqueActivity.class);
                UserAreaActivity.this.startActivity(historiqueIntent);
            }
        });

        btn_profil.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {

                Intent profileIntent = new Intent(UserAreaActivity.this, ProfileActivity.class);
                UserAreaActivity.this.startActivity(profileIntent);

                }
        });

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(UserAreaActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
