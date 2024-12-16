package com.example.visitazaragozaapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private final double LAT_PILAR = 41.656389;
    private final double LONG_PILAR = -0.878611;
    private final double LAT_ALJAFERIA = 41.65649;
    private final double LONG_ALJAFERIA = -0.89708;
    private final double LAT_TORRE = 41.6704;
    private final double LONG_TORRE = -0.907528;
    private final double LAT_PARQUE= 41.63369;
    private final double LONG_PARQUE = -0.89521;
    private double latitude;
    private double longitude;
    private SharedPreferences shared;
    private Button pilar, aljaferia, torre, parque;
    private boolean visitedPilar, visitedAljaferia, visitedTorre, visitedParque;



    private LocationManager locationManager;
    private LocationListener locationListener;

    public void solicitarPermiso(String permiso, int codigoPermiso) {
        if (ContextCompat.checkSelfPermission(this, permiso) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permiso}, codigoPermiso);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            } else {

            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        pilar = findViewById(R.id.bPilar);
        aljaferia = findViewById(R.id.bAljaferia);
        torre = findViewById(R.id.bTorre);
        parque = findViewById(R.id.bParque);

        shared = getSharedPreferences("VISITAS", Context.MODE_PRIVATE);
        visitedPilar = shared.getBoolean("PILAR", false);
        visitedAljaferia = shared.getBoolean("ALJAFERIA", false);
        visitedTorre = shared.getBoolean("TORRE", false);
        visitedParque = shared.getBoolean("PARQUE", false);


        actualizarBotones();

        solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION, 1);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                if (cercaPilar() && !visitedPilar) {
                    mostrarDialogoPilar();
                }

                if (cercaAljaferia() && !visitedAljaferia) {
                    mostrarDialogoAljaferia();
                }

                if (cercaTorre() && !visitedTorre) {
                    mostrarDialogoTorre();
                }

                if (cercaParque() && !visitedParque) {
                    mostrarDialogoParque();
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            @Override
            public void onProviderEnabled(String provider) {}
            @Override
            public void onProviderDisabled(String provider) {}
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    public boolean cercaPilar () {
        boolean cerca= false;
        Location ubicacionPilar = new Location("ubicacionFija");
        ubicacionPilar.setLatitude(LAT_PILAR);
        ubicacionPilar.setLongitude(LONG_PILAR);

        Location ubicacionMovil = new Location("ubicacionGps");
        ubicacionMovil.setLatitude(latitude);
        ubicacionMovil.setLongitude(longitude);
        double distancia = ubicacionPilar.distanceTo(ubicacionMovil);

        if (distancia <= 100) {
            cerca = true;
        }
        return cerca;
    }

    public boolean cercaAljaferia () {
        boolean cerca= false;
        Location ubicacionAljaferia = new Location("ubicacionFija");
        ubicacionAljaferia.setLatitude(LAT_ALJAFERIA);
        ubicacionAljaferia.setLongitude(LONG_ALJAFERIA);

        Location ubicacionMovil = new Location("ubicacionGps");
        ubicacionMovil.setLatitude(latitude);
        ubicacionMovil.setLongitude(longitude);
        double distancia = ubicacionAljaferia.distanceTo(ubicacionMovil);

        if (distancia <= 100) {
            cerca = true;
        }
        return cerca;
    }

    public boolean cercaTorre () {
        boolean cerca= false;
        Location ubicacionTorre = new Location("ubicacionFija");
        ubicacionTorre.setLatitude(LAT_TORRE);
        ubicacionTorre.setLongitude(LONG_TORRE);

        Location ubicacionMovil = new Location("ubicacionGps");
        ubicacionMovil.setLatitude(latitude);
        ubicacionMovil.setLongitude(longitude);
        double distancia = ubicacionTorre.distanceTo(ubicacionMovil);

        if (distancia <= 100) {
            cerca = true;
        }
        return cerca;
    }

    public boolean cercaParque () {
        boolean cerca= false;
        Location ubicacionParque = new Location("ubicacionFija");
        ubicacionParque.setLatitude(LAT_PARQUE);
        ubicacionParque.setLongitude(LONG_PARQUE);

        Location ubicacionMovil = new Location("ubicacionGps");
        ubicacionMovil.setLatitude(latitude);
        ubicacionMovil.setLongitude(longitude);
        double distancia = ubicacionParque.distanceTo(ubicacionMovil);

        if (distancia <= 100) {
            cerca = true;
        }
        return cerca;
    }


    private void mostrarDialogoPilar() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Plaza del Pilar");
        dialogo.setMessage("Has llegado, ¿deseas ver un video de lo que te encontrarás aquí?");
        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogo, int id) {

                Toast.makeText(MainActivity.this, "Abriendo el video", Toast.LENGTH_SHORT).show();
                String ruta="android.resource://"+getPackageName()+"/"+R.raw.plazadelpilar;

                Intent intento = new Intent(getApplicationContext(), ActividadVideo.class);
                intento.putExtra("ruta", ruta);
                startActivity(intento);

            }
        });
        dialogo.setNegativeButton("Cancelar", null);
        dialogo.show();


        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean("PILAR", true);
        editor.apply();

        visitedPilar = true; //
        actualizarBotones(); //
    }

    private void mostrarDialogoAljaferia() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Palacio de la Aljafería");
        dialogo.setMessage("Has llegado, ¿deseas ver un video de lo que te encontrarás aquí?");
        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogo, int id) {
                Toast.makeText(MainActivity.this, "Abriendo el video", Toast.LENGTH_SHORT).show();
                String ruta = "android.resource://"+getPackageName()+"/"+R.raw.aljaferia;

                Intent intento = new Intent(getApplicationContext(), ActividadVideo.class);
                intento.putExtra("ruta", ruta);
                startActivity(intento);
            }
        });
        dialogo.setNegativeButton("Cancelar", null);
        dialogo.show();

        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean("ALJAFERIA", true);
        editor.apply();

        visitedAljaferia = true;
        actualizarBotones();
    }

    private void mostrarDialogoTorre() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Torre del Agua");
        dialogo.setMessage("Has llegado, ¿deseas ver un video de lo que te encontrarás aquí?");
        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogo, int id) {

                Toast.makeText(MainActivity.this, "Abriendo el video", Toast.LENGTH_SHORT).show();
                String ruta = "android.resource://"+getPackageName()+"/"+R.raw.torredelagua;

                Intent intento = new Intent(getApplicationContext(), ActividadVideo.class);
                intento.putExtra("ruta", ruta);
                startActivity(intento);
            }
        });
        dialogo.setNegativeButton("Cancelar", null);
        dialogo.show();

        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean("TORRE", true);
        editor.apply();

        visitedTorre = true;
        actualizarBotones();
    }

    private void mostrarDialogoParque() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Parque Grande Zaragoza");
        dialogo.setMessage("Has llegado, ¿deseas ver un video de lo que te encontrarás aquí?");
        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogo, int id) {

                Toast.makeText(MainActivity.this, "Abriendo el video", Toast.LENGTH_SHORT).show();
                String ruta = "android.resource://"+getPackageName()+"/"+R.raw.parquegrande;

                Intent intento = new Intent(getApplicationContext(), ActividadVideo.class);
                intento.putExtra("ruta", ruta);
                startActivity(intento);
            }
        });
        dialogo.setNegativeButton("Cancelar", null);
        dialogo.show();

        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean("PARQUE", true);
        editor.apply();

        visitedParque = true;
        actualizarBotones();
    }

    public void actualizarBotones() {
        if (visitedPilar) {
            pilar.setVisibility(View.VISIBLE);

        } else {
            pilar.setVisibility(View.GONE);
        }

        if (visitedAljaferia) {
            aljaferia.setVisibility(View.VISIBLE);

        } else {
            aljaferia.setVisibility(View.GONE);
        }

        if (visitedTorre) {
            torre.setVisibility(View.VISIBLE);

        } else {
            torre.setVisibility(View.GONE);
        }

        if (visitedParque) {
            parque.setVisibility(View.VISIBLE);

        } else {
            parque.setVisibility(View.GONE);
        }

    }

    public void verPilar(View view) {
        String uri = String.format("geo:%f,%f?q=%f,%f(Ubicación de %s)", LAT_PILAR, LONG_PILAR,LAT_PILAR, LONG_PILAR, "Plaza del Pilar");
        Uri gmmIntentUri=Uri.parse(uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    public void verAljarefia(View view) {
        String uri = String.format("geo:%f,%f?q=%f,%f(Ubicación de %s)", LAT_ALJAFERIA, LONG_ALJAFERIA, LAT_ALJAFERIA, LONG_ALJAFERIA," La Aljafería");
        Uri gmmIntentUri=Uri.parse(uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    public void verTorre(View view) {
        String uri = String.format("geo:%f,%f?q=%f,%f(Ubicación de %s)", LAT_TORRE, LONG_TORRE, LAT_TORRE, LONG_TORRE, "Torre del Agua");
        Uri gmmIntentUri=Uri.parse(uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    public void verParque(View view) {
        String uri = String.format("geo:%f,%f?q=%f,%f(Ubicación de %s)", LAT_PARQUE, LONG_PARQUE, LAT_PARQUE, LONG_PARQUE, "Parque Grande");
        Uri gmmIntentUri=Uri.parse(uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }


    public void videoPilar(View view) {
        String ruta="android.resource://"+getPackageName()+"/"+R.raw.plazadelpilar;

        Intent intento = new Intent(getApplicationContext(), ActividadVideo.class);
        intento.putExtra("ruta", ruta);
        startActivity(intento);
    }


    public void videoAljaferia(View view) {
        String ruta = "android.resource://"+getPackageName()+"/"+R.raw.aljaferia;

        Intent intento = new Intent(getApplicationContext(), ActividadVideo.class);
        intento.putExtra("ruta", ruta);
        startActivity(intento);
    }

    public void videoParque(View view) {
        String ruta = "android.resource://"+getPackageName()+"/"+R.raw.parquegrande;

        Intent intento = new Intent(getApplicationContext(), ActividadVideo.class);
        intento.putExtra("ruta", ruta);
        startActivity(intento);
    }

    public void videoTorre(View view) {
        String ruta = "android.resource://"+getPackageName()+"/"+R.raw.torredelagua;

        Intent intento = new Intent(getApplicationContext(), ActividadVideo.class);
        intento.putExtra("ruta", ruta);
        startActivity(intento);
    }
}