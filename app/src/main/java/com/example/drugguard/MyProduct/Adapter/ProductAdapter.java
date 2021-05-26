package com.example.drugguard.MyProduct.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugguard.MainActivity;
import com.example.drugguard.MyProduct.Model.Product_Model;
import com.example.drugguard.MyCart.CartActivity;
import com.example.drugguard.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context mContext; 
    private List<Product_Model> productModelList;


    //for MyCart..........
    public static ArrayList<Product_Model> cartModels = new ArrayList<>();
    private HomeCallBack homeCallBack;
    public static Product_Model cartModel;
    public static Callback<List<Product_Model>> mCallBackus;


    public ProductAdapter(Context mContext, List<Product_Model> productModelList, HomeCallBack homeCallBack) {
        this.mContext = mContext;
        this.productModelList = productModelList;
       this.homeCallBack =  homeCallBack;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater=LayoutInflater.from(mContext);
        v = inflater.inflate( R.layout.product_medicne_sample_layout ,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.drugName.setText( productModelList.get( position ).getName() );
        holder.price.setText( productModelList.get( position ).getPrice()+"Taka" );
        //using  Picasso library to display the image
        Picasso.get().load("http://820243d8cdf9.ngrok.io/" + productModelList.get(position).getImg()).into(holder.image);
       // System.out.println( productModelList.get(position).getImg() );


        //For MyCart..............
        holder.buybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Dialog dialog = new Dialog(mContext);
                // Include dialog.xml file
                dialog.setContentView(R.layout.dialog_item_quantity_update);
                // Set dialog title
                dialog.setTitle("Custom Dialog");
                 ImageView cartDecrement = dialog.findViewById(R.id.cart_decrement);
                ImageView cartIncrement = dialog.findViewById(R.id.cart_increment);
                ImageView closeDialog = dialog.findViewById(R.id.imageView_close_dialog_cart);
                TextView updateQtyDialog = dialog.findViewById(R.id.update_quantity_dialog);
                TextView viewCartDialog = dialog.findViewById(R.id.view_cart_button_dialog);
                TextView quantity = dialog.findViewById(R.id.cart_product_quantity_tv);
                quantity.setText(String.valueOf(1));
               final int[] cartCounter = {1};//{(arrayListImage.get(position).getStocks())};
                cartDecrement.setEnabled(false);
//                cartDecrement.setOnClickListener( new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (cartCounter[1] == 1) {
//                            Toast.makeText(mContext, "cant add less than 0", Toast.LENGTH_SHORT).show();
//                        } else {
//                            cartCounter[1] -= 1;
//                            quantity.setText(String.valueOf(cartCounter[1]));
//
//                        }
//                }
//                });
//                cartIncrement.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        cartDecrement.setEnabled(true);
//                        cartCounter[0] += 1;
//                        quantity.setText(String.valueOf(cartCounter[0]));
//
//
//                    }
//                });
                viewCartDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mContext.startActivity(new Intent(mContext, CartActivity.class));
                    }
                });

               //dialog.show();
//               updateQtyDialog.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
                        Toast.makeText(mContext, String.valueOf(cartCounter[0]) + "", Toast.LENGTH_SHORT).show();

                        // from these line of code we add items in cart
                        cartModel = new Product_Model();
                        cartModel.setProductQuantity((cartCounter[0]));
                       // cartModel.setProductImage(productList.get(position).getImagePath());
                       // Picasso.get().load(productList.get(position).getImg()).into(holder.image)
                        cartModel.setPrice( productModelList.get(position).getPrice());
                        cartModel.setName( productModelList.get(position).getName());
               // Picasso.get().load(productList.get(position).getImg())
                        cartModel.setImg( productModelList.get(position).getImg());
                        cartModel.setTotalCash(cartCounter[0] *
                                Integer.parseInt( productModelList.get(position).getPrice()));
                        Log.d("pos", String.valueOf(position));

                        cartModels.add(cartModel);

//
                        // from these lines of code we update badge count value
                        MainActivity.cart_count = 0;
                        for (int i = 0; i < cartModels.size(); i++) {
                            for (int j = i + 1; j < cartModels.size(); j++) {
                                if (cartModels.get(i).getImg().equals(cartModels.get(j).getImg())) {
                                    cartModels.get(i).setProductQuantity(cartModels.get(j).getProductQuantity());
                                    cartModels.get(i).setTotalCash(cartModels.get(j).getTotalCash());
                                    //          cartModels.get(i).setImageIdSlide(cartModels.get(j).getImageIdSlide());
                                    cartModels.remove(j);
                                    j--;
                                    Log.d("remove", String.valueOf(cartModels.size()));

                                }
                            }
                        }
                        MainActivity.cart_count = cartModels.size();

                        // from this interface method calling we show the updated value of cart cout in badge
                        homeCallBack.updateCartCount(mContext);
                        dialog.dismiss();
//                    }
//
//                });

                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.cart_count = 0;
                        for (int i = 0; i < cartModels.size(); i++) {
                            for (int j = i + 1; j < cartModels.size(); j++) {
                                if (cartModels.get(i).getImg().equals(cartModels.get(j).getImg())) {
                                    cartModels.get(i).setProductQuantity(cartModels.get(j).getProductQuantity());
                                    cartModels.get(i).setTotalCash(cartModels.get(j).getTotalCash());
                                    //          cartModels.get(i).setImageIdSlide(cartModels.get(j).getImageIdSlide());
                                    cartModels.remove(j);
                                    j--;
                                    Log.d("remove", String.valueOf(cartModels.size()));

                                }
                            }
                        }


                        MainActivity.cart_count = cartModels.size();
                        homeCallBack.updateCartCount(mContext);
                        dialog.dismiss();
                    }
                });


            }


        });





    }

    @Override
    public int getItemCount() {

        return productModelList.size();
    }


    public  static class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView drugName;
        TextView price;
        ImageView image;
        Button buybutton;
        TextView productName;

         public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            //id= itemView.findViewById( R.id.cardViewID );
             drugName= itemView.findViewById( R.id.productTitle_nameID );
             price= itemView.findViewById( R.id.productPrice_ID );
             image= itemView.findViewById( R.id.product_Image_id );
             buybutton = itemView.findViewById(R.id.BuyNow_Id);

        }
    }


//For MyCart..........

    public interface CallBackUs {
        void addCartItemView();
    }
    // this interface creates for call the invalidateoptionmenu() for refresh the menu item
    public interface HomeCallBack {

        void updateCartCount(Context context);
    }



}
