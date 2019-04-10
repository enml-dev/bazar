package com.enml.bazar.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enml.bazar.R;
import com.enml.bazar.deleteafter.Book;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends BaseAdapter {

    private final Context mContext;
    private final Book[] books;

    public CategoryAdapter(Context context, Book[] books) {
        this.mContext = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        final Book book = books[position];

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.category_item, null);
        }

        // 3
        final CircleImageView imageView = convertView.findViewById(R.id.category_cover_art);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.category_name);
        final TextView authorTextView = (TextView)convertView.findViewById(R.id.category_extra);
        final ImageView imageViewFavorite = (ImageView)convertView.findViewById(R.id.imageview_favorite);

        // 4
        imageView.setImageResource(book.getImageResource());
        nameTextView.setText(mContext.getString(book.getName()));
        authorTextView.setText(mContext.getString(book.getAuthor()));

        return convertView;
    }
}
