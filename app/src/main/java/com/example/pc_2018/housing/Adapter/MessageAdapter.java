package com.example.pc_2018.housing.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc_2018.housing.EditHouse;
import com.example.pc_2018.housing.HouseList;
import com.example.pc_2018.housing.Messages;
import com.example.pc_2018.housing.Models.HouseMod;
import com.example.pc_2018.housing.Models.MessageMod;
import com.example.pc_2018.housing.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends ArrayAdapter<MessageMod> {

    private Context mContext;
    private ArrayList<MessageMod> messageList = new ArrayList<>();




    public MessageAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<MessageMod> list) {
        super(context, 0 , list);
        mContext = context;
        messageList = list;
    }



    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.message_item,parent,false);

        MessageMod message = messageList.get(position);


       // image.setImageResource(currentMovie.getmImageDrawable());

        //TextView name = (TextView) listItem.findViewById(R.id.textView_name);
      //  name.setText(currentMovie.getmName());


        Button deleteBtn = (Button) listItem.findViewById(R.id.deleteBtn);
        TextView userLbl = (TextView) listItem.findViewById(R.id.userLbl);
        TextView priceLbl = (TextView) listItem.findViewById(R.id.priceLbl);
        TextView houseNumber = (TextView)listItem.findViewById(R.id.houseNumber);
        TextView messageLbl = (TextView) listItem.findViewById(R.id.messageLbl);


        userLbl.setText(message.getUsername());
        houseNumber.setText(message.getHouse_number());
        messageLbl.setText(message.getMessage());


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,String.valueOf(position),Toast.LENGTH_LONG).show();
            }
        });





        return listItem;
    }
}