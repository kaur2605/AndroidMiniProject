package dk.ruc.gkaur.androidminiproject.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dk.ruc.gkaur.androidminiproject.Interface.ItemClickListener;
import dk.ruc.gkaur.androidminiproject.Model.Order;
import dk.ruc.gkaur.androidminiproject.R;

/**
 * Created by Ricky on 11/10/2017.
 */


 class  CartViewHolder extends RecyclerView. ViewHolder implements View.OnClickListener{

    public TextView txt_cartName, txt_cartPrice;
    public ImageView imag_cartCount;

    private ItemClickListener itemClickListener;

    public void setTxt_cartName(TextView txt_cartName) {
        this.txt_cartName = txt_cartName;
    }

    public CartViewHolder(View itemView) {
        super(itemView);


        txt_cartName = (TextView)itemView.findViewById(R.id.Cart_ItemName);
        txt_cartPrice= (TextView)itemView.findViewById(R.id.Cart_ItemPrice);
        imag_cartCount= (ImageView)itemView.findViewById(R.id.cart_item_count);


    }

    @Override
    public void onClick(View view) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

private List<Order> listdata = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater  inflater = LayoutInflater.from(context);
        View itemview = inflater.inflate(R.layout.cartlayout,parent,false);
        return  new CartViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+listdata.get(position).getQuantity(), Color.RED);
        holder.imag_cartCount.setImageDrawable(drawable);

        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listdata.get(position).getPrice()))*(Integer.parseInt(listdata.get(position).getQuantity()));
        holder.txt_cartPrice.setText(fmt.format(price));
        holder.txt_cartName.setText(listdata.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }
}
