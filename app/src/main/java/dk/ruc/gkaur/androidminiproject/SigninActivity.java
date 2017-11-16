package dk.ruc.gkaur.androidminiproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import dk.ruc.gkaur.androidminiproject.Common.Common;
import dk.ruc.gkaur.androidminiproject.Model.User;

public class SigninActivity extends AppCompatActivity {
EditText editPhone, editPassword;
    Button btnsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        editPhone = (MaterialEditText) findViewById(R.id.editPhone);
        editPassword= (MaterialEditText) findViewById(R.id.editPassword);
        btnsignin = (Button)findViewById(R.id.btnsignin);

        // inital firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDailog = new ProgressDialog(SigninActivity.this);
                mDailog.setMessage("Please wait");
                mDailog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(editPhone.getText().toString()).exists())
                        {
                            mDailog.show();
                            User user = dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                            user.setPhone(editPhone.getText().toString());
                            if (user.getPassword().equals(editPassword.getText().toString())){

                                {
                                    Intent intent = new Intent(SigninActivity.this,Home.class);
                                    Common.currentUser = user;
                                    startActivity(intent);
                                    finish();
                                }
                                }
                                else {
                                Toast.makeText(SigninActivity.this, " Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                            }

                        else{
                            mDailog.dismiss();
                            Toast.makeText(SigninActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
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