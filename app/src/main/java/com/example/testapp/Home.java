package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapp.app.MyApp;
import com.example.testapp.constants.Constants;
import com.example.testapp.entity.Book;
import com.example.testapp.entity.RegisterResponseUser;
import com.example.testapp.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {
    EditText id, name, price;
    Button  save, find_all, update, delete, search, find_by_price;
    Book book = new Book();
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        id=(EditText) findViewById(R.id.book_id);
        name=(EditText)findViewById(R.id.book_name);
        price=(EditText)findViewById(R.id.book_price);
        save=(Button) findViewById(R.id.save_book);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (TextUtils.isEmpty(id.getText().toString()))
//                {
//                    id.setError("Enter id");
//                }
                if (TextUtils.isEmpty(name.getText().toString()))
                {
                    name.setError("Enter name");
                }
                else if (TextUtils.isEmpty(price.getText().toString()))
                {
                    price.setError("Enter price");
                }
                else{
                    try{
//                        book.setId(id.getText().toString());
                        book.setName(name.getText().toString());
                        book.setPrice(price.getText().toString());
                        getServerData(book);
                    } catch (Exception e) {
                        Toast.makeText(Home.this, "Please enter data"+e, Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        find_all=(Button)findViewById(R.id.find_all_book);
        find_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, BookActivity.class);
                startActivity(i);
            }
        });
        update = (Button)findViewById(R.id.update_book);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(id.getText().toString()))
                {
                    id.setError("Enter id");
                }
                    if (TextUtils.isEmpty(name.getText().toString()))
                    {
                        name.setError("Enter name");
                    }
                    else if (TextUtils.isEmpty(price.getText().toString()))
                    {
                        price.setError("Enter price");
                    }
                    else{
                        try{
                            book.setId(id.getText().toString());
                            book.setName(name.getText().toString());
                            book.setPrice(price.getText().toString());
                            getUpdateResponse(book);
                        } catch (Exception e) {
                            Toast.makeText(Home.this, "Please enter data"+e, Toast.LENGTH_SHORT).show();
                        }


                    }
            }
        });

        delete = (Button)findViewById(R.id.delete_book);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(id.getText().toString()))
                {
                    id.setError("Enter id");
                }
                else{
                    try{
                        book.setId(id.getText().toString());
                        getDeleteResponse(book);
                    } catch (Exception e) {
                        Toast.makeText(Home.this, "Please enter data"+e, Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

        search=(Button)findViewById(R.id.search_book);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(id.getText().toString()))
                {
                    id.setError("Enter id");
                }
                else{
                    try{
                        book.setId(id.getText().toString());
                        getSearchResponse(book);
                    } catch (Exception e) {
                        Toast.makeText(Home.this, "Please enter data"+e, Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });



        find_by_price=(Button)findViewById(R.id.find_book_by_price);
        find_by_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(price.getText().toString()))
                {
                    price.setError("Enter price");
                }
                else{
                    try{
                        book.setPrice(price.getText().toString());
                        getPriceResponse(book);
                    } catch (Exception e) {
                        Toast.makeText(Home.this, "Please enter data"+e, Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });


    }

    public void getPriceResponse(final Book book){

        final ProgressDialog ringProgressDialog=ProgressDialog.show(Home.this,"Please Wait","Loading",true);
        ringProgressDialog.setCancelable(true);
        final Call<List<Book>> call= MyApp.getApiClient().findBookByPrice(Integer.parseInt(book.getPrice()));
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                List<Book> bookList = response.body();
                int i = 0;
                Book book5 = bookList.get(i);
                String Id = book5.getId();
                String Name = book5.getName();
                String Price = book5.getPrice();
                Toast.makeText(Home.this, "Searched Price"+"\n"+" ID = "+Id+" NAME = "+Name+" PRICE = "+Price+"\n", Toast.LENGTH_SHORT).show();
                ringProgressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(Home.this, Constants.REST_ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                ringProgressDialog.dismiss();
            }
        });
    }

    public void getSearchResponse(final Book book){

        final ProgressDialog ringProgressDialog=ProgressDialog.show(Home.this,"Please Wait","Loading",true);
        ringProgressDialog.setCancelable(true);
        final Call<Book> call= MyApp.getApiClient().searchBook(Integer.parseInt(book.getId()));
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()){
                    Book book1 = response.body();
                    String Id = book1.getId();
                    String Name = book1.getName();
                    String Price = book1.getPrice();
                    Toast.makeText(Home.this, "Searched ID"+"\n"+" ID = "+Id+" NAME = "+Name+" PRICE = "+Price, Toast.LENGTH_LONG).show();
                    ringProgressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(Home.this, Constants.REST_ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                ringProgressDialog.dismiss();
            }
        });
    }


    public void getDeleteResponse(final Book book){

        final ProgressDialog ringProgressDialog=ProgressDialog.show(Home.this,"Please Wait","Loading",true);
        ringProgressDialog.setCancelable(true);
        final Call<Void> call= MyApp.getApiClient().deleteBook(Integer.parseInt(book.getId()));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(Home.this, "Delete Successfull", Toast.LENGTH_SHORT).show();
                    ringProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Home.this, Constants.REST_ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                ringProgressDialog.dismiss();
            }
        });
    }

    public void getUpdateResponse(final Book book){

        final ProgressDialog ringProgressDialog=ProgressDialog.show(Home.this,"Please Wait","Loading",true);
        ringProgressDialog.setCancelable(true);
        final Call<Void> call= MyApp.getApiClient().updateBook(book);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()){
                    //  Book book1 = response.body();
                    ringProgressDialog.dismiss();
//                    if(book1==null){
//                        Toast.makeText(Home.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//                    else{
                    Toast.makeText(Home.this, "update book successfull", Toast.LENGTH_SHORT).show();
                    // }
                }
                else{
                    Toast.makeText(Home.this, Constants.REST_ERROR_API_ERROR, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Home.this, Constants.REST_ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                ringProgressDialog.dismiss();
            }
        });
    }

    public void getServerData(final Book book) {

        final ProgressDialog ringProgressDialog=ProgressDialog.show(Home.this,"Please Wait","Loading",true);
        ringProgressDialog.setCancelable(true);
        final Call<Void> call= MyApp.getApiClient().saveBook(book);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                  //  Book book1 = response.body();
                    ringProgressDialog.dismiss();
//                    if(book1==null){
//                        Toast.makeText(Home.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//                    else{
                        Toast.makeText(Home.this, "Add book successfull", Toast.LENGTH_SHORT).show();
                   // }
                }
                else{
                    Toast.makeText(Home.this, Constants.REST_ERROR_API_ERROR, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Home.this, Constants.REST_ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                ringProgressDialog.dismiss();
            }
        });

    }
}
