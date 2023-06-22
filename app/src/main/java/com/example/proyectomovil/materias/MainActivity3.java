package com.example.proyectomovil.materias;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.proyectomovil.R;
import com.example.proyectomovil.db.DbMaterias;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    EditText txtNombre;
    EditText txtPeriodo;
    EditText txtDias;
    EditText txtHoraInicio;
    EditText txtHoraFin;
    Button btnGuardarMat;
    Button btnSeleccionarDias;
    Button btnHoraInicio;
    Button btnHoraFin;
    Calendar calendar;

    List<String> selectedDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txtNombre = findViewById(R.id.txtNombre);
        txtPeriodo = findViewById(R.id.txtPeriodo);
        txtDias = findViewById(R.id.txtDias);
        txtHoraInicio = findViewById(R.id.txtHoraInicio);
        txtHoraFin = findViewById(R.id.txtHoraFin);
        btnGuardarMat = findViewById(R.id.btnGuardarMat);
        btnSeleccionarDias = findViewById(R.id.btnSeleccionarDias);
        btnHoraInicio = findViewById(R.id.btnHoraInicio);
        btnHoraFin = findViewById(R.id.btnhoraFin);

        calendar = Calendar.getInstance();
        selectedDays = new ArrayList<>();

        btnSeleccionarDias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(txtHoraInicio);
            }
        });

        btnHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(txtHoraFin);
            }
        });

        btnGuardarMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMateria();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity3.this);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDay = getDayOfWeek(year, month, dayOfMonth);
                if (selectedDays.size() < 5 && !selectedDays.contains(selectedDay)) {
                    selectedDays.add(selectedDay);
                    updateSelectedDaysText();
                } else {
                    Toast.makeText(MainActivity3.this, "No puedes seleccionar más de 5 días o duplicar días", Toast.LENGTH_LONG).show();
                }
            }
        });
        datePickerDialog.show();
    }

    private String getDayOfWeek(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String[] daysOfWeek = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        return daysOfWeek[dayOfWeek - 1];
    }

    private void updateSelectedDaysText() {
        StringBuilder daysText = new StringBuilder();
        for (String day : selectedDays) {
            daysText.append(day).append(", ");
        }
        if (daysText.length() > 0) {
            daysText.delete(daysText.length() - 2, daysText.length());
        }
        txtDias.setText(daysText.toString());
    }

    private void showTimePickerDialog(final EditText timeEditText) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity3.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeEditText.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, hour, minute, true);

        timePickerDialog.show();
    }

    private void saveMateria() {
        String nombre = txtNombre.getText().toString();
        String periodo = txtPeriodo.getText().toString();
        String horaInicio = txtHoraInicio.getText().toString();
        String horaFin = txtHoraFin.getText().toString();

        if (nombre.isEmpty() || periodo.isEmpty() || selectedDays.isEmpty() || horaInicio.isEmpty() || horaFin.isEmpty()) {
            Toast.makeText(MainActivity3.this, "Llene todos los campos", Toast.LENGTH_LONG).show();
        } else {
            String dias = getSelectedDaysText();
            DbMaterias dbMaterias = new DbMaterias(MainActivity3.this);
            long id = dbMaterias.insertaMateria(nombre, periodo, dias, horaInicio, horaFin);
            if (id > 0) {
                Toast.makeText(MainActivity3.this, "Registro guardado", Toast.LENGTH_LONG).show();
                limpiar();
            } else {
                Toast.makeText(MainActivity3.this, "Error al guardar el registro", Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getSelectedDaysText() {
        StringBuilder daysText = new StringBuilder();
        for (String day : selectedDays) {
            daysText.append(day.charAt(0)).append(", ");
        }
        if (daysText.length() > 0) {
            daysText.delete(daysText.length() - 2, daysText.length());
        }
        return daysText.toString();
    }

    private void limpiar() {
        txtNombre.setText("");
        txtPeriodo.setText("");
        txtDias.setText("");
        txtHoraInicio.setText("");
        txtHoraFin.setText("");
        selectedDays.clear();
    }
}

