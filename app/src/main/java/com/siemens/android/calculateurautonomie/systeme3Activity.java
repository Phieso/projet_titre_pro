package com.siemens.android.calculateurautonomie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class systeme3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systeme3);

        final EditText I_veille = findViewById(R.id.I_veille);
        final EditText I_alarme = findViewById(R.id.I_alarme);
        final EditText I_delestage = findViewById(R.id.I_delestage);
        final EditText T_delestage = findViewById(R.id.T_delestage);

        final Button btn_calculer = findViewById(R.id.btn_calculer);
        final TextView resultat = findViewById(R.id.resultat);
        final Button sauvegarder = findViewById(R.id.btn_sauvegarder);

        btn_calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String I_veille_str = I_veille.getText().toString(); // on récupère le texte entré dans le champ I_veille
                double I_veille_value = Double.valueOf(I_veille_str); // on le convertit en double

                String I_alarme_str = I_alarme.getText().toString();
                double I_alarme_value = Double.valueOf(I_alarme_str);

                String I_delestage_str = I_delestage.getText().toString();
                double I_delestage_value = Double.valueOf(I_delestage_str);

                String T_delestage_str = T_delestage.getText().toString();
                double T_delestage_value = Double.valueOf(T_delestage_str);

                double n; // n coefficient de peukert appliqué dans la formule de calcul
                if (I_veille_value <= 1 && I_alarme_value <= 1) {
                    n = 1;
                } else {
                    n = 1.22;
                }

                double result = ((Math.pow(I_veille_value, n)) * T_delestage_value + (Math.pow(I_delestage_value, n)) * (12 - T_delestage_value) + (Math.pow(I_alarme_value, n)) * 1) * 1.25;
                resultat.setText("Autonomie = " + String.valueOf(result) + " Ah", EditText.BufferType.NORMAL);
                // conversion du résultat en string pour l'afficher dans le textView resultat



            }
        });
    }
}
