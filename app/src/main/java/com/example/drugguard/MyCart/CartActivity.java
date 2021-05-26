package com.example.drugguard.MyCart;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugguard.MainActivity;
import com.example.drugguard.MyCart.Adapter.CartAdapter;
import com.example.drugguard.MyOrder.ApiClint;
import com.example.drugguard.MyOrder.Model.Order_Model;
import com.example.drugguard.MyProduct.Model.Product_Model;
import com.example.drugguard.OrderList.OrderList;
import com.example.drugguard.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.drugguard.MyProduct.Adapter.ProductAdapter.cartModels;





public class CartActivity extends AppCompatActivity {

    public static TextView grandTotal;
    public static int grandTotalplus;
    // create a temp list and add cartitem list
    public static ArrayList<Product_Model> temparraylist;
    RecyclerView cartRecyclerView;
     CartAdapter cartAdapter;
    LinearLayout proceedToBook;
    Context context;
    private Toolbar mToolbar;
    Button orderPlacedButton;

    //for order
    public static TextView orderAddressEditText;
    public static TextView orderPaymentTypeRadiobutton;
    RadioGroup radioGroup;
    RadioButton radioButton;





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        context = this;
        //cart layout
        orderPlacedButton= findViewById(R.id.orderPlacedId);
        orderAddressEditText=findViewById( R.id.cartAddressId );
       // orderPaymentTypeRadiobutton=findViewById( R.id.radioGroupID );
         //radio=findViewById( R.id.radioGroupID );


        temparraylist = new ArrayList<>();
        mToolbar =  findViewById(R.id.toolbar);
        proceedToBook = findViewById(R.id.proceed_to_book);
        grandTotal = findViewById(R.id.grand_total_cart);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Cart");


        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_arrow));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // these lines of code for show the same  cart for future refrence
                grandTotalplus = 0;
                for (int i = 0; i < temparraylist.size(); i++) {

                }
                cartModels.addAll(temparraylist);
                MainActivity.cart_count = (temparraylist.size());
//                addItemInCart.clear();
                finish();
            }
        });
        MainActivity.cart_count = 0;

        //addInCart();

        Log.d("sizecart_1", String.valueOf(temparraylist.size()));
        Log.d("sizecart_2", String.valueOf(cartModels.size()));

        // from these lines of code we remove the duplicacy of cart and set last added quantity in cart
        // for replace same item
        for (int i = 0; i < cartModels.size(); i++) {
            for (int j = i + 1; j < cartModels.size(); j++) {
                if (cartModels.get(i).getImg().equals(cartModels.get(j).getImg())) { //..................................getimage................
                    cartModels.get(i).setProductQuantity(cartModels.get(j).getProductQuantity());
                    cartModels.get(i).setName(cartModels.get(j).getName());
                    cartModels.get(i).setTotalCash(cartModels.get(j).getTotalCash());
                    cartModels.remove(j);
                    j--;
                    Log.d("remove", String.valueOf(cartModels.size()));

                }
            }

        }
        temparraylist.addAll(cartModels);
        cartModels.clear();
        Log.d("sizecart_11", String.valueOf(temparraylist.size()));
        Log.d("sizecart_22", String.valueOf(cartModels.size()));
        // this code is for get total cash
        for (int i = 0; i < temparraylist.size(); i++) {
            grandTotalplus = grandTotalplus + temparraylist.get(i).getTotalCash();
        }
        grandTotal.setText(String.valueOf(grandTotalplus)+"Taka");
        cartRecyclerView = findViewById(R.id.recycler_view_cart);
        cartAdapter = new CartAdapter(temparraylist, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(mLayoutManager);
        cartRecyclerView.setAdapter(cartAdapter);
        //order placed button............
        orderPlacedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    placedOrder();
            }
            private void placedOrder() {
                //for radio button select Payment option
               radioGroup=findViewById( R.id.radioGroupID );
                int radioid = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioid);

                Order_Model order = new Order_Model( );
                order.setAddress( orderAddressEditText.getText().toString() );
                order.setPaymentType( radioButton.getText().toString() );
                  order.setDrugs( temparraylist );
                Call<Order_Model> call = ApiClint.getOrder().placedOrder(order);
                call.enqueue( new Callback<Order_Model>() {
                    @Override
                    public void onResponse(Call<Order_Model> call, Response<Order_Model> response) {

                        if(response.code() == 200){
                            Intent intent = new Intent(CartActivity.this, OrderList.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
                            startActivity( intent );
                            Toast.makeText(CartActivity.this,"order placed",Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 404){
                            Toast.makeText(CartActivity.this,"Order Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Order_Model> call, Throwable t) {

                        Toast.makeText(CartActivity.this,"Order Failed", Toast.LENGTH_LONG).show();
                    }
                } );
                //Toast.makeText( CartActivity.this, "order placed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        grandTotalplus = 0;
        for (int i = 0; i < temparraylist.size(); i++) {
            MainActivity.cart_count = (temparraylist.size());

        }
        cartModels.addAll(temparraylist);
        //cartModels.clear();
    }


}