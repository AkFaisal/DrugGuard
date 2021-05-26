package com.example.drugguard.MyCart.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.drugguard.MyProduct.Model.Product_Model;
import com.example.drugguard.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.drugguard.MyCart.CartActivity.grandTotal;
import static com.example.drugguard.MyCart.CartActivity.grandTotalplus;
import static com.example.drugguard.MyCart.CartActivity.temparraylist;


/**
 * Created by Deependra Singh Patel on 8/5/19.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<Product_Model> cartModelArrayList;
    Context context;

    public CartAdapter(ArrayList<Product_Model> cartModelArrayList, Context context) {
        this.context =  context;
        this.cartModelArrayList = cartModelArrayList;
    }


    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_sample_list_row, parent, false);
        return new CartAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // holder.productCartImage.setImageResource(R.drawable.burger);
        holder.productCartPrice.setText( String.valueOf(cartModelArrayList.get(position).getTotalCash()));
        holder.productCartName.setText(cartModelArrayList.get(position).getName());//...............
        holder.productCartQuantity.setText( String.valueOf(cartModelArrayList.get(position).getProductQuantity()));
        Picasso.get().load("http://820243d8cdf9.ngrok.io/"+cartModelArrayList.get(position).getImg()).into(holder.productCartImage); // http://192.168.0.107:3040/

//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.bag);
//        requestOptions.error(R.drawable.bag);
//        Log.d("imageurl", String.valueOf(cartModelArrayList.get(position).getImg()));
//        Glide.with(context)
//                .setDefaultRequestOptions(requestOptions)
//                .load(cartModelArrayList.get(position).getImg()).into(holder.productCartImage);

        //for remove single item in cart and update the total value and list
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cartModelArrayList.size() == 1) {
                    cartModelArrayList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cartModelArrayList.size());
                    grandTotalplus = 0;
                    grandTotal.setText( String.valueOf(grandTotalplus));
                }

                if (cartModelArrayList.size() > 0) {
                    cartModelArrayList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cartModelArrayList.size());
                    grandTotalplus = 0;
                    for (int i = 0; i < temparraylist.size(); i++) {
                        grandTotalplus = grandTotalplus + temparraylist.get(i).getTotalCash();
                    }

                    Log.d("totalcashthegun", String.valueOf(grandTotalplus));
                    grandTotal.setText( String.valueOf(grandTotalplus));

                } else {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // increment quantity and update quamtity and total cash
        holder.cartIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //total_cash=0;\
                Log.d("posthegun", String.valueOf(cartModelArrayList.get(position).getStocks()));

                grandTotalplus = 0;
                holder.cartDecrement.setEnabled(true);

                int cartUpdateCounter = (cartModelArrayList.get(position).getProductQuantity());
                Log.d("counterthegun", String.valueOf(cartModelArrayList.get(position).getProductQuantity()));

                holder.cartIncrement.setEnabled(true);
                cartUpdateCounter += 1;

                cartModelArrayList.get(position).setProductQuantity((cartUpdateCounter));
                int cash = (Integer.parseInt(cartModelArrayList.get(position).getPrice()) * (cartModelArrayList.get(position).getProductQuantity()));

                holder.productCartQuantity.setText( String.valueOf(cartModelArrayList.get(position).getProductQuantity()));

                cartModelArrayList.get(position).setTotalCash(cash);
                holder.productCartPrice.setText( String.valueOf(cash));

                for (int i = 0; i < temparraylist.size(); i++) {
                    grandTotalplus = grandTotalplus + temparraylist.get(i).getTotalCash();
                }
                Log.d("totalcashthegun", String.valueOf(grandTotalplus));
                grandTotal.setText( String.valueOf(grandTotalplus));

            }

        });

        // decrement quantity and update quamtity and total cash
        holder.cartDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //total_cash=0;
                grandTotalplus = 0;
                holder.cartIncrement.setEnabled(true);

                int cartUpdateCounter = (cartModelArrayList.get(position).getProductQuantity());
                Log.d("counterthegun", String.valueOf(cartModelArrayList.get(position).getProductQuantity()));


                if (cartUpdateCounter == 1) {
                    holder.cartDecrement.setEnabled(false);
                    Toast.makeText(context, "quantity can't be zero", Toast.LENGTH_SHORT).show();
                } else {
                    holder.cartDecrement.setEnabled(true);
                    cartUpdateCounter -= 1;
                    cartModelArrayList.get(position).setProductQuantity((cartUpdateCounter));
                    holder.productCartQuantity.setText( String.valueOf(cartModelArrayList.get(position).getProductQuantity()));
                    int cash = (Integer.parseInt(cartModelArrayList.get(position).getPrice()) * (cartModelArrayList.get(position).getProductQuantity()));

                    cartModelArrayList.get(position).setTotalCash(cash);
                    holder.productCartPrice.setText( String.valueOf(cash));
                    for (int i = 0; i < temparraylist.size(); i++) {
                        grandTotalplus = grandTotalplus + temparraylist.get(i).getTotalCash();
                    }

                    Log.d("totalcashthegun", String.valueOf(grandTotalplus));
                    grandTotal.setText( String.valueOf(grandTotalplus));

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("sizecart", String.valueOf(cartModelArrayList.size()));
        return cartModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productCartImage, cartIncrement, cartDecrement, deleteItem;
        TextView productCartName, productCartPrice, productCartQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            productCartImage = itemView.findViewById( R.id.list_image_cart);
            deleteItem = itemView.findViewById(R.id.delete_item_from_cart);
            productCartName = itemView.findViewById(R.id.product_name_Cart_id );
            productCartPrice = itemView.findViewById(R.id.product_cart_price);
            productCartQuantity = itemView.findViewById(R.id.cart_product_quantity_tv);
            cartDecrement = itemView.findViewById(R.id.cart_decrement);
            cartIncrement = itemView.findViewById(R.id.cart_increment);


        }
    }


}
