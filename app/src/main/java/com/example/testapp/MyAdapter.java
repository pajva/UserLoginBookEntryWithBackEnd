package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.entity.Book;
import com.example.testapp.entity.Books;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PostViewHolder> {

    List<Book> booklist;
    Context context;

    public MyAdapter(Context context, List<Book> books){
        this.context=context;
        booklist=books;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.indi_view,null);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Book book = booklist.get( position);
        holder.bkid.setText("ID : "+book.getId());
        holder.bkname.setText("NAME : "+book.getName());
        holder.bkprice.setText("PRICE : "+book.getPrice());
    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        TextView bkid, bkname, bkprice;

       public PostViewHolder(@NonNull View itemView) {
           super(itemView);

           bkid = itemView.findViewById(R.id.id);
           bkname = itemView.findViewById(R.id.name);
           bkprice = itemView.findViewById(R.id.price);
       }
   }

}
