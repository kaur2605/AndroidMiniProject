package dk.ruc.gkaur.androidminiproject.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dk.ruc.gkaur.androidminiproject.Interface.ItemClickListener;
import dk.ruc.gkaur.androidminiproject.R;

/**
 * Created by Ricky on 11/5/2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

public TextView txtmenuname;
    public ImageView imageview;
    private ItemClickListener itemClickListener;
    public MenuViewHolder(View itemView) {
        super(itemView);
txtmenuname = (TextView)itemView.findViewById(R.id.menu_name);
        imageview = (ImageView) itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
