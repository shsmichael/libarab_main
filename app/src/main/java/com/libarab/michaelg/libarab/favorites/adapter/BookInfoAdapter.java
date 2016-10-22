package com.libarab.michaelg.libarab.favorites.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.libarab.michaelg.libarab.R;
import com.libarab.michaelg.libarab.favorites.bean.TagItem;

import java.util.List;

public class BookInfoAdapter extends BaseAdapter {
    private List<TagItem> list;
    private LayoutInflater inflater;
    private Context context;

    public BookInfoAdapter(Context context, List<TagItem> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookInfoViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fav_fragment_book_info_item, null);
            holder = new BookInfoViewHolder();
            holder.tvTag = (TextView) convertView.findViewById(R.id.tag);
            holder.tvContent = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(holder);
        }

        holder = (BookInfoViewHolder) convertView.getTag();
        TagItem data = list.get(position);

        holder.tvTag.setText(data.getTag());
        holder.tvContent.setText(data.getContent());

        return convertView;
    }
}

class BookInfoViewHolder {
    TextView tvTag;
    TextView tvContent;
}
