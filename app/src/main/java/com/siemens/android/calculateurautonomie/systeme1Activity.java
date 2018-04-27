package com.siemens.android.calculateurautonomie;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class systeme1Activity extends AppCompatActivity {
    private static final String TAG = systeme1Activity.class.getSimpleName();
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systeme1);

        final GetterSetter_systeme1 donnees = new GetterSetter_systeme1();
        final TextView Systeme = findViewById(R.id.titre);
        final EditText I_veille = findViewById(R.id.I_veille);
        final EditText I_alarme = findViewById(R.id.I_alarme);
        final EditText T_veille = findViewById(R.id.T_veille);
        final EditText T_alarme = findViewById(R.id.T_alarme);

        final Button btn_calculer = findViewById(R.id.btn_calculer);
        final TextView resultat = findViewById(R.id.resultat);
        final Button btn_sauvegarder = findViewById(R.id.btn_sauvegarder);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        final Dialog dialog = new Dialog(systeme1Activity.this); // création d'une instance de Dialog pour l'affichage d'une popup
        dialog.setContentView(R.layout.popupview); //popupview --> layout crée pour la popup
        final TextView txt_popup = dialog.findViewById(R.id.textbox); // récupération de la textView située dans popupview
        final ImageView img_close = dialog.findViewById(R.id.img_close); // récupération de l'ImageView située dans popupview


        btn_calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                donnees.setI_veille(Float.parseFloat(I_veille.getText().toString()));
                ; // on récupère la valeur entrée dans le champ I_veille
                donnees.setI_alarme(Float.parseFloat(I_alarme.getText().toString()));
                donnees.setT_veille(Float.parseFloat(T_veille.getText().toString()));
                donnees.setT_alarme(Float.parseFloat(T_alarme.getText().toString()));

                double n; // n coefficient de peukert appliqué dans la formule de calcul
                if (donnees.getI_veille() <= 1 && donnees.getI_alarme() <= 1) {
                    n = 1;
                } else {
                    n = 1.22;
                }

                double result = ((Math.pow(donnees.getI_veille(), n)) * donnees.getI_veille() + (Math.pow(donnees.getI_alarme(), n)) * donnees.getT_alarme()) * 1.25;
                resultat.setText("Autonomie = " + String.valueOf(result) + " Ah", EditText.BufferType.NORMAL);
                // conversion du résultat en string pour l'afficher dans le textView resultat
                donnees.setResult(result);

            }

        });

        btn_sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Systeme_value;
                float I_veille_value, I_alarme_value, T_veille_value, T_alarme_value;
                double result_value;

                Systeme_value = donnees.getSystem();
                I_veille_value = donnees.getI_veille();
                I_alarme_value = donnees.getI_alarme();
                T_veille_value = donnees.getT_veille();
                T_alarme_value = donnees.getT_alarme();
                result_value = donnees.getResult();

                if (I_veille_value != 0.0f && I_alarme_value != 0.0f && T_veille_value != 0.0f && T_alarme_value != 0.0f && result_value != 0.00) {
                    storeEntry1(Systeme_value, I_veille_value, I_alarme_value, T_veille_value, T_alarme_value, result_value);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Veuillez compléter tous les champs", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });
    }
        /**
         * Function to store system 1 entry in MySQL database will post params(tag, system,
         * i_veille, i_alarme, t_veille, t_alarme, result) to STORE_RESULT1 url
         * */
        private void storeEntry1 (String Systeme_value,float I_veille_value, float I_alarme_value, float T_veille_value,
        float T_alarme_value, double result_value){
            // Tag used to cancel the request
            String tag_string_req = "req_save";

            pDialog.setMessage("Sauvegarde en cours ...");
            showDialog();

            StringRequest strReq = new StringRequest(com.android.volley.Request.Method.POST,
                    AppConfig.URL_STORE_RESULT1, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Saving Response: " + response.toString());
                    hideDialog();

                    try {
                        JSONObject jObj = new JSONObject(response);
                        boolean error = jObj.getBoolean("error");
                        if (!error) {
                            // Entry successfully stored in MySQL

                            // Now store the user in sqlite
                            /*String uid = jObj.getString("uid");

                            JSONObject user = jObj.getJSONObject("user");
                            String name = user.getString("name");
                            String email = user.getString("email");
                            String created_at = user
                                    .getString("created_at");

                            // Inserting row in users table
                            db.addUser(name, email, uid, created_at);
                            */

                            Toast.makeText(getApplicationContext(), "Résultat sauvegardé avec succès !", Toast.LENGTH_LONG).show();

                            // Launch login activity
                            Intent intent = new Intent(
                                    systeme1Activity.this,
                                    systeme1Activity.class);
                            startActivity(intent);
                            finish();
                        } else {

                            // Error occurred in registration. Get the error
                            // message
                            String errorMsg = jObj.getString("error_msg");
                            Toast.makeText(getApplicationContext(),
                                    errorMsg, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Erreur de sauvegarde: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();
                    hideDialog();
                }
            });

            /*
            {

                @Override
                protected Map<String, String> getParams() {
                    // Posting params to STORE_RESULT1 url
                    Map<String, String> params = new HashMap<>();
                    params.put("system", system);
                    params.put("email", email);
                    params.put("password", password);

                    return params;
                }

            };
            */

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        }
        private void showDialog() {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideDialog() {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }


