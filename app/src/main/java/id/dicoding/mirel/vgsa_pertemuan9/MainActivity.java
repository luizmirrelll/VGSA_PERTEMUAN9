package id.dicoding.mirel.vgsa_pertemuan9;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText editUsername;
    String editPassword;
    EditText editEmail;
    EditText editNamaLengkap;
    EditText editAsalSekolah;
    EditText editAlamat;
    Button btnSimpan;

    public static final String FILENAME = "login";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Halaman Depan");
        btnSimpan = findViewById(R.id.btnSave);
        editUsername = findViewById(R.id.inputUser);
        editEmail = findViewById(R.id.inputEmail);
        editNamaLengkap = findViewById(R.id.inputNama);
        editAsalSekolah = findViewById(R.id.inputSekolah);
        editAlamat = findViewById(R.id.inputAlamat);
        masukanData();
    }

    private void masukanData() {
        File file = new File(getFilesDir(),FILENAME);
        if(file.exists()){
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                String[] data = text.toString().split(";");
                br.close();
                editUsername.setText(data[0]);
                editPassword = data[1];
                editEmail.setText(data[2]);
                editNamaLengkap.setText(data[3]);
                editAsalSekolah.setText(data[4]);
                editAlamat.setText(data[5]);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }else{
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.editmenu:
                profiledit();
                break;
            case R.id.hapusmenu:
                hapus();
                break;
            case R.id.logoutmenu:
                logout();
                break;
        }
        return true;
    }
    private void hapus() {
        new AlertDialog.Builder(this).setTitle("Hapus Data Profile")
                .setMessage("Apkah Anda Yakin untuk Menghapus Data Profile? data yang sudah di hapus tidak dapat di kembalikan!")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialog, int which) {
                                hapusData();
                            }
                        }).setNegativeButton(android.R.string.no,null).show();
    }
    private  void  hapusData(){
        File file = new File(getFilesDir(),FILENAME);
        if(file.exists()){
            file.delete();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        }
    }
    private void logout() {
        new AlertDialog.Builder(this).setTitle("Logout")
                .setMessage("Apkah Anda Yakin untuk Logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        }).setNegativeButton(android.R.string.no,null).show();
    }

    private void profiledit() {
        editUsername.setEnabled(true);
        editEmail.setEnabled(true);
        editNamaLengkap.setEnabled(true);
        editAsalSekolah.setEnabled(true);
        editAlamat.setEnabled(true);
        btnSimpan.setVisibility(View.VISIBLE);
    }

    public void btn(View view) {
        if(view.getId() == btnSimpan.getId()){
            update();
        }
    }
    void  update(){
        new AlertDialog.Builder(this).setTitle("Update Profile")
                .setMessage("Apkah Anda Yakin untuk Mengedit Profile anda?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialog, int which) {
                                simpan();
                            }
                        }).setNegativeButton(android.R.string.no,null).show();
    }
    private void simpan() {
        String dataFile = editUsername.getText()+";"+editPassword+";"+editEmail.getText()+";"+editNamaLengkap.getText()+";"+editAsalSekolah.getText()+";"+editAlamat.getText();
        File file = new File(getFilesDir(),FILENAME);
        FileOutputStream output;
        try {

            output = new FileOutputStream(file,false);
            output.write(dataFile.getBytes());
            output.flush();
            output.close();
            Toast.makeText(getApplicationContext(),"Profile Sudah Di Update",Toast.LENGTH_SHORT).show();
            editUsername.setEnabled(false);
            editEmail.setEnabled(false);
            editNamaLengkap.setEnabled(false);
            editAsalSekolah.setEnabled(false);
            editAlamat.setEnabled(false);
            btnSimpan.setVisibility(View.GONE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}