package com.example.drugguard.OrderList.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugguard.OrderList.Model.OrderList_Model;
import com.example.drugguard.R;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {

    private Context mContext; 
    private List<OrderList_Model> orderListModelList;
    private OnOrderListListener onOrderListListener;

    public interface OnOrderListListener {

        void onItemClick(int position);

        void onLongItemClick(int position);

    }

    public  void  setOnItemClickListener(OnOrderListListener listener){
        onOrderListListener=listener;


    }

    public OrderListAdapter(Context mContext, List<OrderList_Model> orderListModelList) {
        this.mContext = mContext;
        this.orderListModelList = orderListModelList;
      // this.homeCallBack =  homeCallBack;
        this.onOrderListListener=  onOrderListListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater=LayoutInflater.from(mContext);
        v = inflater.inflate( R.layout.order_list_sample ,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.drugId.setText( orderListModelList.get( position ).get_id() );
        holder.drugTotalPrice.setText("&"+ orderListModelList.get( position ).getTotalCash());
        holder.drugStatus.setText( orderListModelList.get( position ).getStatus());
        holder.drugOrderDarNumber.setText( orderListModelList.get( position ).getDar());
        holder.drugOrderTime.setText( orderListModelList.get( position ).getCreatedAt());
//        holder.itemView.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(mContext, CartActivity.class);
//                mContext.startActivity( intent );
//
//
//            }
//        } );
        //using  Picasso library to display the image
        //Picasso.get().load( "http://192.168.1.104:3040/" + orderListModelList.get(position).getImg()).into(holder.image);
       // System.out.println( productModelList.get(position).getImg() );


        //For MyCart..............
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 Dialog dialog = new Dialog(mContext);
//                // Include dialog.xml file
//                dialog.setContentView(R.layout.dialog_item_quantity_update);
//                // Set dialog title
//                dialog.setTitle("Custom Dialog");
//                 ImageView cartDecrement = dialog.findViewById(R.id.cart_decrement);
//                ImageView cartIncrement = dialog.findViewById(R.id.cart_increment);
//                ImageView closeDialog = dialog.findViewById(R.id.imageView_close_dialog_cart);
//                TextView updateQtyDialog = dialog.findViewById(R.id.update_quantity_dialog);
//                TextView viewCartDialog = dialog.findViewById(R.id.view_cart_button_dialog);
//                TextView quantity = dialog.findViewById(R.id.cart_product_quantity_tv);
//                quantity.setText(String.valueOf(1));
//               final int[] cartCounter = {1};//{(arrayListImage.get(position).getStocks())};
//                cartDecrement.setEnabled(false);
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
//                viewCartDialog.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        mContext.startActivity(new Intent(mContext, CartActivity.class));
//                    }
//                });

               //dialog.show();
//               updateQtyDialog.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(mContext, String.valueOf(cartCounter[0]) + "", Toast.LENGTH_SHORT).show();
//
//                        // from these line of code we add items in cart
//                        cartModel = new OrderList_Model();
//                        cartModel.setProductQuantity((cartCounter[0]));
//                       // cartModel.setProductImage(productList.get(position).getImagePath());
//                       // Picasso.get().load(productList.get(position).getImg()).into(holder.image)
//                        cartModel.setPrice( orderListModelList.get(position).getPrice());
//                        cartModel.setName( orderListModelList.get(position).getName());
//               // Picasso.get().load(productList.get(position).getImg())
//                        cartModel.setImg( orderListModelList.get(position).getImg());
//                        cartModel.setTotalCash(cartCounter[0] *
//                                Integer.parseInt( orderListModelList.get(position).getPrice()));
//                        Log.d("pos", String.valueOf(position));
//
//                        cartModels.add(cartModel);

//
                        // from these lines of code we update badge count value
//                        MainActivity.cart_count = 0;
//                        for (int i = 0; i < cartModels.size(); i++) {
//                            for (int j = i + 1; j < cartModels.size(); j++) {
//                                if (cartModels.get(i).getImg().equals(cartModels.get(j).getImg())) {
//                                    cartModels.get(i).setProductQuantity(cartModels.get(j).getProductQuantity());
//                                    cartModels.get(i).setTotalCash(cartModels.get(j).getTotalCash());
//                                    //          cartModels.get(i).setImageIdSlide(cartModels.get(j).getImageIdSlide());
//                                    cartModels.remove(j);
//                                    j--;
//                                    Log.d("remove", String.valueOf(cartModels.size()));
//
//                                }
//                            }
//                        }
//                        MainActivity.cart_count = cartModels.size();
//
//                        // from this interface method calling we show the updated value of cart cout in badge
//                        homeCallBack.updateCartCount(mContext);
//                        dialog.dismiss();
//                    }
//
//                });
////
//                closeDialog.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        MainActivity.cart_count = 0;
//                        for (int i = 0; i < cartModels.size(); i++) {
//                            for (int j = i + 1; j < cartModels.size(); j++) {
//                                if (cartModels.get(i).getImg().equals(cartModels.get(j).getImg())) {
//                                    cartModels.get(i).setProductQuantity(cartModels.get(j).getProductQuantity());
//                                    cartModels.get(i).setTotalCash(cartModels.get(j).getTotalCash());
//                                    //          cartModels.get(i).setImageIdSlide(cartModels.get(j).getImageIdSlide());
//                                    cartModels.remove(j);
//                                    j--;
//                                    Log.d("remove", String.valueOf(cartModels.size()));
//
//                                }
//                            }
//                        }
//
//
//                        MainActivity.cart_count = cartModels.size();
//                        homeCallBack.updateCartCount(mContext);
//                        dialog.dismiss();
//                    }
//                });


//            }
//
//
//        });





    }

    @Override
    public int getItemCount() {

        return orderListModelList.size();
    }


    public class  MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView drugId;
        TextView drugTotalPrice;
        TextView drugStatus;
        TextView drugOrderTime;
        TextView drugOrderDarNumber;


        public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            //id= itemView.findViewById( R.id.cardViewID );
            drugId = itemView.findViewById( R.id.orderList_orderID_Id );
            drugTotalPrice = itemView.findViewById( R.id.orderList_orderTotalPrice_Id );
            drugStatus = itemView.findViewById( R.id.orderList_orderStatus_Id );
            drugOrderDarNumber = itemView.findViewById( R.id.orderList_DarNumber_Id );
            drugOrderTime = itemView.findViewById( R.id.orderList_orderTime_Id );


            itemView.setOnClickListener( this );
        }


        @Override
        public void onClick(View v) {


            if (onOrderListListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onOrderListListener.onItemClick( position );

                }

            }


        }
    }}
