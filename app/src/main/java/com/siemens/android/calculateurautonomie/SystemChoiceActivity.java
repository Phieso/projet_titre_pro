package com.siemens.android.calculateurautonomie;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SystemChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_choice);

        final Button btn_systeme1 = findViewById(R.id.systeme1);
        final Button btn_systeme2 = findViewById(R.id.systeme2);
        final Button btn_systeme3 = findViewById(R.id.systeme3);
        final Button btn_systeme4 = findViewById(R.id.systeme4);

        final ImageButton btn_info1 = findViewById(R.id.info1);
        final ImageButton btn_info2 = findViewById(R.id.info2);
        final ImageButton btn_info3 = findViewById(R.id.info3);
        final ImageButton btn_info4 = findViewById(R.id.info4);

        final Dialog dialog = new Dialog(SystemChoiceActivity.this); // création d'une instance de Dialog pour l'affichage des infos
        dialog.setContentView(R.layout.popupview); //popupview --> layout crée pour la popup
        final TextView txt_popup = dialog.findViewById(R.id.textbox); // récupération de la textView située dans popupview
        final ImageView img_close = dialog.findViewById(R.id.img_close); // récupération de l'ImageView située dans popupview

        btn_systeme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent systeme1Intent = new Intent(SystemChoiceActivity.this, systeme1Activity.class);
                SystemChoiceActivity.this.startActivity(systeme1Intent);

            }
        });

        btn_systeme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent systeme2Intent = new Intent(SystemChoiceActivity.this, systeme2Activity.class);
                SystemChoiceActivity.this.startActivity(systeme2Intent);
            }
        });

        btn_systeme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent systeme3Intent = new Intent(SystemChoiceActivity.this, systeme3Activity.class);
                SystemChoiceActivity.this.startActivity(systeme3Intent);
            }
        });

        btn_systeme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent systeme4Intent = new Intent(SystemChoiceActivity.this, systeme4Activity.class);
                SystemChoiceActivity.this.startActivity(systeme4Intent);
            }
        });

        // Pour afficher une boîte de dialogue au clic
        btn_info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_popup.setText(getString(R.string.msg_info1)); // set le texte de la popup avec msg_info1 récupéré dans res/values/strings.xml

                // au clic sur img_close
                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss(); // fermer la popup
                    }
                });
                dialog.show(); // afficher la popup
            }
        });

        btn_info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_popup.setText(getString(R.string.msg_info2));

                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        btn_info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_popup.setText(getString(R.string.msg_info3));

                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        btn_info4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_popup.setText(getString(R.string.msg_info4));

                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}
