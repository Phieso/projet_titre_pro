package com.siemens.android.calculateurautonomie;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class systeme1Activity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systeme1);

        final TextView Systeme = findViewById(R.id.titre);
        final EditText I_veille = findViewById(R.id.I_veille);
        final EditText I_alarme = findViewById(R.id.I_alarme);
        final EditText T_veille = findViewById(R.id.T_veille);
        final EditText T_alarme = findViewById(R.id.T_alarme);

        final Button btn_calculer = findViewById(R.id.btn_calculer);
        final TextView resultat = findViewById(R.id.resultat);
        final Button btn_sauvegarder = findViewById(R.id.btn_sauvegarder);

        final Dialog dialog = new Dialog(systeme1Activity.this); // création d'une instance de Dialog pour l'affichage d'une popup
        dialog.setContentView(R.layout.popupview); //popupview --> layout crée pour la popup
        final TextView txt_popup = dialog.findViewById(R.id.textbox); // récupération de la textView située dans popupview
        final ImageView img_close = dialog.findViewById(R.id.img_close); // récupération de l'ImageView située dans popupview


        btn_calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final float I_veille_value = Float.parseFloat(I_veille.getText().toString()); // on récupère la valeur entrée dans le champ I_veille
                final float I_alarme_value = Float.parseFloat(I_alarme.getText().toString());
                final float T_veille_value = Float.parseFloat(T_veille.getText().toString());
                final float T_alarme_value = Float.parseFloat(T_alarme.getText().toString());

                double n; // n coefficient de peukert appliqué dans la formule de calcul
                if (I_veille_value <= 1 && I_alarme_value <= 1) {
                    n = 1;
                } else {
                    n = 1.22;
                }

                double result = ((Math.pow(I_veille_value, n))* T_veille_value + (Math.pow(I_alarme_value, n)) * T_alarme_value) * 1.25;
                resultat.setText("Autonomie = " + String.valueOf(result) + " Ah", EditText.BufferType.NORMAL);
                // conversion du résultat en string pour l'afficher dans le textView resultat

            }

        });
    }
}
