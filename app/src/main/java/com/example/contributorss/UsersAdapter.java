package com.example.contributorss;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsersAdapter extends ArrayAdapter<users> {
    private List<users> mUsers;
    int mResource;
    private Context mContext;

    public UsersAdapter(Context context, int resource, List<users> usersName){
        super(context, resource ,usersName);
        this.mContext = context;
        this.mUsers = usersName;
        mResource = resource;
    }

    @Override public int getCount() {
        return mUsers.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_users, null, false);

        TextView login = view.findViewById(R.id.login);
        ImageView image = view.findViewById(R.id.imageView);
        TextView cont = view.findViewById(R.id.contributions);

        login.setText(mUsers.get(position).getLogin());
        cont.setText(String.valueOf(mUsers.get(position).getContributions()));
        Glide.with(mContext)
                .load(mUsers.get(position).getAvatarUrl())
                .into(image);

        return view;
    }
}


