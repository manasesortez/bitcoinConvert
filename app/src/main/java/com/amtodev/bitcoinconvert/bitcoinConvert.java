package com.amtodev.bitcoinconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class bitcoinConvert extends AppCompatActivity {


    String[] opciones = {
            "Convertir de Bitcoin a: ",
            "Dolares",
            "Lempiras",
            "Quetzales",
            "Cordobas",
            "Colones Costarricenses",
            "Balboa Panameño"
            };
    Spinner objSpinner;
    DecimalFormat format = new DecimalFormat("#,###,###,###,##0.00");
    DecimalFormat formatDecimal = new DecimalFormat("#.###################");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin_convert);

        objSpinner = (Spinner) findViewById(R.id.listaConversion);
        ArrayAdapter<String> elAdaptador = new ArrayAdapter<String>(this, R.layout.spinner_iteam, opciones);
        objSpinner.setAdapter(elAdaptador);
        Button elBoton = (Button)findViewById(R.id.btnCalcular);

        elBoton.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view){
                int opcionSeleccionada;
                opcionSeleccionada = objSpinner.getSelectedItemPosition();

                //get Object IDS from My Layout bitcoinConvert
                EditText objPrecio = (EditText)findViewById(R.id.txtPrecio);
                EditText objCantidad = (EditText)findViewById(R.id.txtCantidad);
                TextView objLabel = (TextView) findViewById(R.id.lblResultados);

                //DATA
                Double cantidad = 0.0, resultado = 0.0, precio = 0.0;

                //COUNTRYS
                Double honduras = 0.0, guatemala = 0.0, nicaragua= 0.0, panama = 0.0, costaRica = 0.0;

                //PARSE DATA
                cantidad = Double.parseDouble(objCantidad.getText().toString());
                precio = Double.parseDouble(objPrecio.getText().toString());

                //ABREVIATURA
                String abreviatura = "";

                // getCountry Information from dollar to Currency Local
                guatemala = precio * 3.87;
                honduras = precio * 23.87;
                nicaragua = precio * 35.10;
                costaRica = precio * 620.76;
                panama = precio * 1.00;

                switch (opcionSeleccionada){
                    case 0:
                        objLabel.setText("No has Agregado Ningun Valor");
                        break;
                    case 1:
                        resultado = precio * cantidad;
                        abreviatura = " USD";
                        break;
                    case 2:
                        resultado = honduras * cantidad;
                        abreviatura = " L";
                        break;
                    case 3:
                        resultado = guatemala * cantidad;
                        abreviatura = " Q";
                        break;
                    case 4:
                        resultado = nicaragua * cantidad;
                        abreviatura = " C$";
                        break;
                    case 5:
                        resultado = costaRica * cantidad;
                        abreviatura = " ₡";
                        break;
                    case 6:
                        resultado = panama * cantidad;
                        abreviatura = " ฿";
                        break;
                    default:
                        objLabel.setText("No has Agregado Ningun Valor");
                        break;
                }
                objLabel.setText(formatDecimal.format(cantidad) + " Btc = " + format.format(resultado) + abreviatura);
            }
        });

    }
}