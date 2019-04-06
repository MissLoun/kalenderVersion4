package de.projects.janap.a04_kalender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Calendar;

public class Kalender_Steuerung extends AppCompatActivity {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Attribute
    /*-------------------------Darstellung--------------------------------------------------------*/
    private TextView txtMonatAnzeige;
    private TextView txtJahrAnzeige;
    private GridView tabelleAktuellerMonat;

    /*-------------------------Andere Variablen---------------------------------------------------*/
    private Monat monat = new Monat();
    private Calendar kalender = Calendar.getInstance(); //erstellt einen Kalender mit aktuellen Datum Angaben
    private int[] tageMax = new int[42]; //42 ist die maximale Anzahl der Zellen die die Tabelle brauchen koennte

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Methoden
    /*-------------------------Set Methoden-------------------------------------------------------*/
    public void setTxtMonatAnzeige(String pNeuerMonat){
        txtMonatAnzeige.setText(pNeuerMonat);
    }
    public void setTxtJahrAnzeige(int pJahrAnzeige) {
        txtJahrAnzeige.setText(""+pJahrAnzeige);
    }

    /*-------------------------Andere Methoden----------------------------------------------------*/
    public void aktualisiereKalender(){
        monat.setMonat(kalender);   //der neue Monat wird festgelegt durch die Angaben des Kalenders
        Kalender_Adapter adapterAktuellerMonat = new Kalender_Adapter(this,  tageMax, kalender); //KalenderAdapter um den Kalender in der Tabelle darzustellen

        setTxtJahrAnzeige(kalender.get(Calendar.YEAR)); //setzt die neue Jahresangabe fest
        setTxtMonatAnzeige("" + monat.getMonatsBezeichnung());  //setzt die neue Monatsangabe fest

        tabelleAktuellerMonat.setAdapter(adapterAktuellerMonat);    //Kalender wird dargestellt
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Erstellung
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender__gui);

        tabelleAktuellerMonat = findViewById(R.id.grid_Kalender_Tabelle_AktuellerMonat);

        txtMonatAnzeige = findViewById(R.id.txt_Kalender_Monat);
        txtJahrAnzeige = findViewById(R.id.txt_Kalender_Jahr);
        Button btnZuvor = findViewById(R.id.btn_Kalender_Zuvor);
        Button btnNaechts = findViewById(R.id.btn_Kalender_Weiter);


        aktualisiereKalender();     //der Monat wird mit den momentanen Daten des Kalenders dargestellt

        btnZuvor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kalender.add(Calendar.MONTH,-1);    //der Monat des Kalenders wird um eins reduziert
                aktualisiereKalender(); //der Monat wird mit den momentanen Daten des Kalenders dargestellt

            }
        });

        btnNaechts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kalender.add(Calendar.MONTH,1); //der Monat des Kalenders wird um eins addiert
                aktualisiereKalender(); //der Monat wird mit den momentanen Daten des Kalenders dargestellt

            }
        });

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
