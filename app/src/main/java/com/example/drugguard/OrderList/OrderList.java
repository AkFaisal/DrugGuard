package com.example.drugguard.OrderList;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugguard.OrderList.Adapter.OrderListAdapter;
import com.example.drugguard.OrderList.Common.OrderListCommon;
import com.example.drugguard.OrderList.CompleteOrder.Get.CO_GetApiService;
import com.example.drugguard.OrderList.CompleteOrder.Get.CompleteOrderCommon;
import com.example.drugguard.OrderList.CompleteOrder.Get.Complete_OrderList_Model;
import com.example.drugguard.OrderList.CompleteOrder.Post.CO_ApiClint;
import com.example.drugguard.OrderList.Interface.RetrofitServiceOrderList;
import com.example.drugguard.OrderList.Model.OrderList_Model;
import com.example.drugguard.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderList extends AppCompatActivity implements OrderListAdapter.OnOrderListListener {


    private ArrayList<OrderList_Model> orderlist;
    private ArrayList<Complete_OrderList_Model> complete_orderList_modelArrayList;
    private  OrderListAdapter orderListAdapter;

    RecyclerView recyclerOrderList;
    RetrofitServiceOrderList mService;
    CO_GetApiService co_Get_apiService;
    RecyclerView.LayoutManager layoutManager;
    OrderListAdapter adapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_order_list );
        orderlist=new ArrayList<>();
        complete_orderList_modelArrayList=new ArrayList<>();
        mToolbar =  findViewById(R.id.toolbar);
       // setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Order List");

       // mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_arrow));
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                // these lines of code for show the same  cart for future refrence
//                grandTotalplus = 0;
//                for (int i = 0; i < temparraylist.size(); i++) {
//
//                }
//                cartModels.addAll(temparraylist);
//                MainActivity.cart_count = (temparraylist.size());
//////                addItemInCart.clear();
//                Intent intent = new Intent( OrderList.this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
//                startActivity( intent );
//                finish();
//            }
//        });


        // for product view
        mService = OrderListCommon.getOrderList();
        co_Get_apiService =CompleteOrderCommon.getCompleteOrderList(); //for confirm alert dialog at order list
        recyclerOrderList = findViewById(R.id.orderList_recylerviewID);
        recyclerOrderList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerOrderList.setLayoutManager(layoutManager);

        getAllOrderList();//show all order in Order List
    }

    private void getAllOrderList() {

        mService.getOrderList().enqueue(new Callback<List<OrderList_Model>>() {
            @Override
            public void onResponse(Call<List<OrderList_Model>> call, Response<List<OrderList_Model>> response) {
                adapter = new OrderListAdapter(getBaseContext(), response.body() );
                adapter.notifyDataSetChanged();
                recyclerOrderList.setAdapter(adapter);
                adapter.setOnItemClickListener( OrderList.this );
                Toast.makeText(OrderList.this, "Order List", Toast.LENGTH_SHORT).show();
                orderlist= (ArrayList<OrderList_Model>) response.body();
//                content();
            }

            @Override
            public void onFailure(Call<List<OrderList_Model>> call, Throwable t) {

                Toast.makeText(OrderList.this, "Failed order list", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemClick(int position) {

        OrderList_Model clickItem = orderlist.get( position );
       // System.out.println(clickItem.get_id() );

        co_Get_apiService.getCompleteOrderList(clickItem.get_id()).enqueue( new Callback<List<Complete_OrderList_Model>>() {
            @Override
            public void onResponse(Call<List<Complete_OrderList_Model>> call, Response<List<Complete_OrderList_Model>> response) {
                Complete_OrderList_Model complete_orderList_model=new Complete_OrderList_Model();
                complete_orderList_model.get_id();
                complete_orderList_model.getRole();

            }
            @Override
            public void onFailure(Call<List<Complete_OrderList_Model>> call, Throwable t) {

            }

        });


        new AlertDialog.Builder( OrderList.this )
                .setIcon(R.drawable.alert_hand_24  )
                .setTitle( "Confirm" )
                .setMessage( "Are you sure?" )
                .setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        co_Get_apiService.getCompleteOrderList(clickItem.get_id()).enqueue( new Callback<List<Complete_OrderList_Model>>() {
                            @Override
                            public void onResponse(Call<List<Complete_OrderList_Model>> call, Response<List<Complete_OrderList_Model>> response) {

                                Yes(clickItem.get_id()); //Yes method calling

                            }

                            @Override
                            public void onFailure(Call<List<Complete_OrderList_Model>> call, Throwable t) {
                                Toast.makeText(OrderList.this, "Failed to confirm Deliver order" + "", Toast.LENGTH_SHORT).show();

                            }

                        });


                    }
                } ).setNegativeButton( "Cancel" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
            }).show();

       // Toast.makeText(this, "gsdsdsdsdsdsdsdsdsdsdsdsdsdsdsd" + "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLongItemClick(int position) {

    }

    //This methode use for confirm delivary aleart dialog
    private void Yes(String id) {
        Complete_OrderList_Model complete_orderList_model = new Complete_OrderList_Model( );
        complete_orderList_model.setRole("Pharmacist");
        complete_orderList_model.set_id( id );
        Call<List<Complete_OrderList_Model>> call = CO_ApiClint.getCO_Post().role( complete_orderList_model );
        call.enqueue( new Callback<List<Complete_OrderList_Model>>() {
            @Override
            public void onResponse(Call<List<Complete_OrderList_Model>> call, Response<List<Complete_OrderList_Model>> response) {
                Toast.makeText(OrderList.this, "Delivery Successful", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<List<Complete_OrderList_Model>> call, Throwable t) {
                Toast.makeText(OrderList.this, t.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        } );

    }


//    public  void content(){
//        refresh(1);
//    }
//
//    private void refresh(int milliseconds){
//
//        final Handler handler =new Handler();
//        final Runnable runnable= new Runnable() {
//            @Override
//            public void run() {
//                content();
//            }
//        };
//        handler.postDelayed( runnable,milliseconds );
//
//
//    }

}