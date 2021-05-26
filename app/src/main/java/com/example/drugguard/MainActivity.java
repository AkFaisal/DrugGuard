package com.example.drugguard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugguard.MyCart.CartActivity;
import com.example.drugguard.MyCart.Converter;
import com.example.drugguard.MyProduct.Adapter.ProductAdapter;
import com.example.drugguard.MyProduct.Common.Common;
import com.example.drugguard.MyProduct.Interface.RetrofitService;
import com.example.drugguard.MyProduct.Model.Product_Model;
import com.example.drugguard.OrderList.OrderList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ProductAdapter.CallBackUs, ProductAdapter. HomeCallBack  , NavigationView.OnNavigationItemSelectedListener {


    RecyclerView recyclerProduct;
    RetrofitService mService;
    RecyclerView.LayoutManager layoutManager;
    ProductAdapter adapter;
   ImageView orderlistbutton;
    DrawerLayout drawer;
    //cart....
    public static int cart_count = 0;
    public static ArrayList<Product_Model> arrayList = new ArrayList<>();
   // private List<Product> productList ;
    private AppBarConfiguration mAppBarConfiguration;
    private Object ActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );
        //For navigation Drawer
         drawer = findViewById( R.id.drawer_layout );
        NavigationView navigationView = findViewById( R.id.nav_view );
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow )
                .setDrawerLayout( drawer )
                .build();
        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
        NavigationUI.setupActionBarWithNavController( this, navController, mAppBarConfiguration );
        NavigationUI.setupWithNavController( navigationView, navController );
        navigationView.setNavigationItemSelectedListener( this );
        // for product view
        mService = Common.getProduct();
        recyclerProduct = findViewById(R.id.recylerview_layout_id);
        recyclerProduct.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        recyclerProduct.setLayoutManager(layoutManager);

        getAllProductList();
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
        return NavigationUI.navigateUp( navController, mAppBarConfiguration )
                || super.onSupportNavigateUp();
    }
    // for product view
    private void getAllProductList() {
        mService.getProducts().enqueue(new Callback<List<Product_Model>>() {
            @Override
            public void onResponse(Call<List<Product_Model>> call, Response<List<Product_Model>> response) {
                adapter = new ProductAdapter(getBaseContext(), response.body(), MainActivity.this::updateCartCount );
                adapter.notifyDataSetChanged();
                recyclerProduct.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product_Model>> call, Throwable t) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_icone_id);
        menuItem.setIcon( Converter.convertLayoutToImage(MainActivity.this, cart_count, R.drawable.cart_shopping_cart_24));
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.cart_icone_id:
                if (cart_count < 1) {
                    Toast.makeText(this, "there is no item in cart", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(MainActivity.this, CartActivity.class));
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
   //Navigation Drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.myOrder_navi_id:
                startActivity( new Intent( MainActivity.this, OrderList.class ) );
                Toast.makeText( this, "Order List", Toast.LENGTH_SHORT ).show();
                break;
        } switch (id) {
            case R.id.mycart_navi_id:
                startActivity( new Intent( MainActivity.this, CartActivity.class ) );
                Toast.makeText( this, "My cart", Toast.LENGTH_SHORT ).show();
                break;
        }

        drawer.closeDrawer( GravityCompat.START ); // for close navigation drawer
        return true;
    }
    @Override
    public void updateCartCount(Context context) {
        invalidateOptionsMenu();
    }

    @Override
    protected void onStart() {
        super.onStart();
        invalidateOptionsMenu();
    }

    @Override
    public void addCartItemView() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}