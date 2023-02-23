package com.ugb.conversor_parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TabHost tbh;
    Button btn;
    TextView temp;
    Spinner spnDe, spnA;
    conversores miConversor = new conversores();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbhConversores);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("PIECUADRADO").setContent(R.id.tbhpiecuadrado));//"PIECUADRADO"
        tbh.addTab(tbh.newTabSpec("VARACUADRADA").setContent(R.id.tbhvaracuadrada).setIndicator("VARACUADRADA"));
        tbh.addTab(tbh.newTabSpec("YARDACUADRADA").setContent(R.id.tbhyardacuadrada).setIndicator("YARDACUADRADA"));

        btn = findViewById(R.id.btnConvertir);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int de = 0, a = 0;
                temp = (TextView) findViewById(R.id.txtcantidad);
                double cantidad = Double.parseDouble(temp.getText().toString());

                int opcion = 0;
                switch (tbh.getCurrentTab()) {
                    case R.id.tbhpiecuadrado:
                        spnDe = findViewById(R.id.spnDepiecuadrado);
                        spnA = findViewById(R.id.spnApiecuadrado);
                        opcion = 1;
                        break;
                    case R.id.tbhvaracuadrada:
                        opcion = 3;
                        break;
                    case R.id.tbhvaracuadrada:
                        spnDe = findViewById(R.id.spnDevaracuadrada);
                        spnA = findViewById(R.id.spnAvaracuadrada);
                        opcion = 0;
                        break;
                }
                de = spnDe.getSelectedItemPosition();
                a = spnA.getSelectedItemPosition();
                temp = findViewById(R.id.lblrespuesta);
                temp.setText("Respuesta: " + miConversor.convertir(opcion, de, a, cantidad));
            }
        });
    }
}
class conversores{
    double[][] valores = {
            {0.1111},//vara cuadrada
            {0.111111},//yarda cuadrada
            {0.092903},//metro cuadrado
            {9.2903e-6},//hectarea
            {0.00001317},//manzana
            {0.00014664656489}//tareas
    };
    public double convertir(int opcion, int de, int a, double cantidad){
        return valores[opcion][a] / valores[opcion][de] * cantidad;
    }
}