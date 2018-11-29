package com.example.pc_2018.housing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.pc_2018.housing.Adapter.MessageAdapter;
import com.example.pc_2018.housing.Adapter.OwnerHouseListAdapter;
import com.example.pc_2018.housing.Models.HouseMod;
import com.example.pc_2018.housing.Models.MessageMod;

import java.util.ArrayList;

public class Messages extends AppCompatActivity {


    ListView listView;
    private MessageAdapter messageAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        listView = (ListView) findViewById(R.id.list_view);


        listView = (ListView) findViewById(R.id.list_view);
        ArrayList<MessageMod> messagesList = new ArrayList<>();
        messagesList.add(new MessageMod(1, "السلام عليكم ورحمة الله ي ريت يتم قبول طلبي لحجز المنزل بالرقم المذكور ، في امس الحوجة له " , "Amjad","1102"));
        messagesList.add(new MessageMod(1, "ليش المنزل غالي لهذه الدرجة راح اقبله بنصف سعر اذا بتوافق  " , "ياسمين","2201"));
        messagesList.add(new MessageMod(1, "لا ما يصح ان المنزل ما فيه دفايات نحن بالشتاءاذا بتريد قلل السعر  " , "عثمان","89323"));


        messageAdapter = new MessageAdapter(Messages.this, messagesList);

        listView.setAdapter(messageAdapter);






    }
}
