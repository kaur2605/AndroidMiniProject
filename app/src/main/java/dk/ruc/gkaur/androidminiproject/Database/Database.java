package dk.ruc.gkaur.androidminiproject.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import dk.ruc.gkaur.androidminiproject.Model.Order;

/**
 * Created by Ricky on 11/9/2017.
 */

public class Database extends SQLiteAssetHelper {

private final static String DB_NAME ="Database3DB.db";
    private final static int  DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order>getCarts()
    {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductName, ProductId, Quantity,Price,Discount"};
        String sqlTable = "OrderDetail";
        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null,null,null, null);

        final List<Order> result =  new ArrayList<>();

        if(c.moveToFirst())
        {
            do {
                result.add(new Order(c.getString(c.getColumnIndex("ProductId")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount"))

                ));
            }while (c.moveToLast());

        } return result;
    }


    public void addToCart(Order order){

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount)values('%s','%s','%s','%s','%s')",
        order.getProductId(),order.getProductName(),order.getQuantity(),order.getPrice(),order.getDiscount());

        db.execSQL(query);

    }


    public void cleancart(){

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query);

    }
}



