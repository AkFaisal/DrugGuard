package com.example.drugguard.MyLogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drugguard.MainActivity;
import com.example.drugguard.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView email;
    TextView privateKey;
    Button login;

    //private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView( R.layout.activity_login_);
        privateKey=findViewById(R.id.password);
        email=findViewById(R.id.userName);
        login=findViewById(R.id.button);

        login.setOnClickListener( this );


    }

    @Override
    public void onClick(View v) {

        if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(privateKey.getText().toString())){
            Toast.makeText( LoginActivity.this,"Username / Password Required", Toast.LENGTH_LONG).show();
        }else{
            //proceed to login
            login();
        }



//        IApiService iApiService = ApiClint.getClint().create(IApiService.class);
//        Call<User> call = iApiService.login(email.getText().toString(), privateKey.getText().toString());
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if(response.code() == 200){
////                    User user = response.body();
////                    Toast.makeText(MainActivity.this,"successfull",Toast.LENGTH_LONG).show();
//                    if (response.isSuccessful()){
//                        Login();
//                        Intent intent = new Intent(MainActivity.this,Gallary.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
//                        startActivity( intent );
//                        Toast.makeText(MainActivity.this,"successfull",Toast.LENGTH_LONG).show();
//
//                    }else {
//                        Toast.makeText(MainActivity.this,"successfull",Toast.LENGTH_LONG).show();
//
//                    }
//
//                }else {
//                    Toast.makeText(MainActivity.this,"Not found",Toast.LENGTH_LONG).show();
//
//                }
////                Toast.makeText(MainActivity.this,"successfull",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });



        }

    private void login() {
//       HashMap<String, String> map =new HashMap<>();
//        map.put( "email" ,email.getText().toString() );
//        map.put( "private_key" ,privateKey.getText().toString() );



        User user = new User( );
        user.setEmail( email.getText().toString() );
        user.setPrivate_key( privateKey.getText().toString() );


        Call<User> call = ApiClint.getUser().login(user);
     call.enqueue( new Callback<User>() {
         @Override
         public void onResponse(Call<User> call, Response<User> response) {

             if(response.code() == 200){
                   User user = response.body();
                       user.getEmail();
                       user.getPrivate_key();

                        Intent intent = new Intent( LoginActivity.this, MainActivity.class);
                        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity( intent );
                        Toast.makeText( LoginActivity.this,"successfull", Toast.LENGTH_LONG).show();

                    }

             else if(response.code() == 404){
                 Toast.makeText( LoginActivity.this,"Login Failed", Toast.LENGTH_LONG).show();

             }


         }

         @Override
         public void onFailure(Call<User> call, Throwable t) {

            Toast.makeText( LoginActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

         }
     } );

    }


}

