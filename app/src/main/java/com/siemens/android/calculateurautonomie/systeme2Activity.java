package com.siemens.android.calculateurautonomie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class systeme2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systeme2);

        final GetterSetter_systeme2 donnees = new GetterSetter_systeme2();
        final TextView Systeme = findViewById(R.id.titre);
        final EditText I_veille = findViewById(R.id.I_veille);
        final EditText I_alarme = findViewById(R.id.I_alarme);

        final Button btn_calculer = findViewById(R.id.btn_calculer);
        final TextView resultat = findViewById(R.id.resultat);
        final Button sauvegarder = findViewById(R.id.btn_sauvegarder);

        btn_calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donnees.setSystem(Systeme.getText().toString());
                donnees.setI_veille(Float.parseFloat(I_veille.getText().toString())); // on récupère le texte entré dans le champ I_veille

                donnees.setI_alarme(Float.parseFloat(I_alarme.getText().toString()));

                double n; // n coefficient de peukert appliqué dans la formule de calcul
                if (donnees.getI_veille()<= 1 && donnees.getI_alarme() <= 1) {
                    n = 1;
                } else {
                    n = 1.22;
                }

                double result = ((Math.pow(donnees.getI_veille(), n))* 12 + (Math.pow(donnees.getI_alarme(), n)) * 1) * 1.25;
                resultat.setText("Autonomie = " + String.valueOf(result) + " Ah", EditText.BufferType.NORMAL);
                // conversion du résultat en string pour l'afficher dans le textView resultat
            }
        });
    }
}

