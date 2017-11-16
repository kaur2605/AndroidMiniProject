package dk.ruc.gkaur.androidminiproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dk.ruc.gkaur.androidminiproject.Common.Common;
import dk.ruc.gkaur.androidminiproject.Database.Database;
import dk.ruc.gkaur.androidminiproject.Model.Order;
import dk.ruc.gkaur.androidminiproject.Model.Request;
import dk.ruc.gkaur.androidminiproject.ViewHolder.CartAdapter;
import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference requests;
    TextView txttotalprice;
    FButton btnPlace;

    List<Order> cart = new ArrayList<>();

    CartAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        recyclerView = (RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txttotalprice= (TextView)findViewById(R.id.total);
        btnPlace = (FButton)findViewById(R.id.btncart);

         btnPlace.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 showAlertDailog();



             }
         });

        loadFoodList();

    }

    private void showAlertDailog() {
        final AlertDialog.Builder alertdailog = new AlertDialog.Builder(Cart.this);
        alertdailog.setTitle("One more step");
        alertdailog.setMessage("Please enter address");


        final EditText editAddress = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        editAddress.setLayoutParams(lp);
        alertdailog.setView(editAddress);
        alertdailog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        alertdailog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Request request = new Request(
                        Common.currentUser.getPhone(),
                        Common.currentUser.getPhone(),
                        editAddress.getText().toString(),
                        txttotalprice.getText().toString(),
                        cart

                );

                requests.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(request);

                new Database(getBaseContext()).cleancart();
                Toast.makeText(Cart.this, "Thank you , order placed", Toast.LENGTH_SHORT).show();

                finish();

            }
        });

        alertdailog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){


            }
        });

            alertdailog.show();

    }

    private void loadFoodList() {

        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);

        int total = 0;
        for(Order order:cart) total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txttotalprice.setText(fmt.format(total));

    }
}
