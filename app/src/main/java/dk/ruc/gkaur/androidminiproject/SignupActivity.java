package dk.ruc.gkaur.androidminiproject;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import dk.ruc.gkaur.androidminiproject.Model.User;

public class SignupActivity extends AppCompatActivity {

EditText editPhone, editName, editPassword;
    Button btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editName = (MaterialEditText) findViewById(R.id.editName);
        editPhone = (MaterialEditText) findViewById(R.id.editPhone);
        editPassword = (MaterialEditText) findViewById(R.id.editPassword);
        btnsignup = (Button)findViewById(R.id.btnsignup);

        // inital firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mdailog = new ProgressDialog(SignupActivity.this);
                mdailog.setMessage("Please wait");
                mdailog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(editPhone.getText().toString()).exists()){

                            mdailog.dismiss();
                            Toast.makeText(SignupActivity.this, "Phone number already registered", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            mdailog.dismiss();
                            User user = new User(editName.getText().toString(),editPassword.getText().toString());
                            table_user.child(editPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignupActivity.this, "Sigup Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }


}
