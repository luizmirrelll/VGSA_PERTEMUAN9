package id.dicoding.mirel.vgsa_pertemuan9;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private static final String FILENAME = "login";
    EditText editUsername, editPassword, editEmail, editNamaLengkap, editAsalSekolah, editAlamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editEmail = findViewById(R.id.editEmail);
        editNamaLengkap = findViewById(R.id.editNamaLengkap);
        editAsalSekolah = findViewById(R.id.editAsalSekolah);
        editAlamat = findViewById(R.id.editAlamat);

    }
    public void button(View v){
        if(v.getId() == R.id.simpan){
            if(input()){
                simpan();
            }else{
                toas("Semua Data Harus Diisi!");
            }
        }

    }

    private void simpan() {
        String dataFile = editUsername.getText()+";"+editPassword.getText()+";"+editEmail.getText()+";"+editNamaLengkap.getText()+";"+editAsalSekolah.getText()+";"+editAlamat.getText();
        File file = new File(getFilesDir(),FILENAME);
        FileOutputStream output;

        try {
            file.createNewFile();
            output = new FileOutputStream(file,false);
            output.write(dataFile.getBytes());
            output.flush();
            output.close();
            toas("berhasil register silahkan Login");
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean input (){
        return !editUsername.getText().toString().equals("") && !editPassword.getText().toString().equals("") && !editEmail.getText().toString().equals("") &&
                !editNamaLengkap.getText().toString().equals("") && !editAsalSekolah.getText().toString().equals("") && !editAlamat.getText().toString().equals("");
    }
    void toas(String isi){
        Toast.makeText(getApplicationContext(),isi,Toast.LENGTH_SHORT).show();
    }
}
