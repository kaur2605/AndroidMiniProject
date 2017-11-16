    package dk.ruc.gkaur.androidminiproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

    public class MainActivity extends AppCompatActivity {

    Button btnsignup, btnsignin;
        TextView txtslogan;
final static String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"");
        Log.i(TAG, "");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsignin = (Button) findViewById(R.id.btnsignin);
        btnsignup = (Button) findViewById(R.id.btnsignup);
        txtslogan = (TextView) findViewById(R.id.txtslogan);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signup = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(signup);


            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signin = new Intent(MainActivity.this,SigninActivity.class);
                startActivity(signin);


            }
        });
    }
}
