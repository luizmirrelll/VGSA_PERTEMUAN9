package id.dicoding.mirel.vgsa_pertemuan9;

import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class SplashScreen extends AppCompatActivity {
public static String FILENAME = "login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isLogin()){
                    Intent intent= new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },3000);
    }
    boolean isLogin(){
        File sdcard = getFilesDir();{
            File file = new File(sdcard,FILENAME);
            if (file.exists()){
                return true;
            }else {
                return false;
            }
        }
    }
}