package com.example.testapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.app.MyApp;
import com.example.testapp.constants.Constants;
import com.example.testapp.entity.Book;
import com.example.testapp.entity.Books;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Call<List<Book>> call = MyApp.getApiClient().findAllBook();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()){
                    List<Book> bookList = response.body();
                    MyAdapter myAdapter = new MyAdapter(BookActivity.this, bookList);
                    recyclerView.setAdapter(myAdapter);
                    return;
                }

            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(BookActivity.this, Constants.REST_ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });

    }

//    public void getBooks(View view, Book book) {
//
//        final Call<List<Book>> call = MyApp.getApiClient().findAllBook();
//        call.enqueue(new Callback<List<Book>>() {
//            @Override
//            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
//                    List<Book> bookList =response.body();
//                    lview1.setAdapter(new MyAdapter(BookActivity.this, bookList));
//            }
//
//            @Override
//            public void onFailure(Call<List<Book>> call, Throwable t) {
//
//            }
//        });
//    }
}
