package com.example.android.sanrakshan.Fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.sanrakshan.Adapters.AllContactsAdapter;
import com.example.android.sanrakshan.DBUtils.ContactDetails;
import com.example.android.sanrakshan.DBUtils.DBHelper;
import com.example.android.sanrakshan.Interfaces.OnDelete;
import com.example.android.sanrakshan.R;

import java.util.ArrayList;

/**
 * Created by HP on 07-Oct-17.
 */

public class ContactsFragment extends Fragment {

    RecyclerView allContactsRv;
    public static OnDelete onDelete;

    public AllContactsAdapter allContactsAdapter;


    DBHelper mydbHelper;
    public static ArrayList<ContactDetails> allContactsArrayList;

    Context ctx;
    public static final String TAG = "Yolo";
    static final int PICK_CONTACT = 1001;
    static final int REQ_CODE = 1991;

    public ContactsFragment(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mydbHelper = new DBHelper(ctx);
        allContactsArrayList = new ArrayList<>();
        allContactsArrayList.clear();
        Cursor res = mydbHelper.getAllData();
        while (res.moveToNext()) {
            Log.d(TAG, "onClick: " + res.getString(1));
            Log.d(TAG, "onClick: " + res.getString(2));
            Log.d(TAG, "onClick: " + "-----------------------");
            allContactsArrayList.add(new ContactDetails(res.getString(1), res.getString(2), null));
        }
        allContactsAdapter  = new AllContactsAdapter(ctx,allContactsArrayList);





    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_contacts,container,false);


        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });

        allContactsRv = rootView.findViewById(R.id.rvList);
        allContactsRv.setLayoutManager(new LinearLayoutManager(ctx));
        allContactsRv.setAdapter(allContactsAdapter);

        onDelete = new OnDelete() {
            @Override
            public void onDel() {
                displayContacts(rootView);
            }
        };


        return  rootView;
    }


    public static OnDelete getOnDelete(){
        return onDelete;
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {

                    Uri contactData = data.getData();
                    ContentResolver cr = getContext().getContentResolver();

                    Cursor c = cr.query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {

                        String cNumber = null;
                        String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        

//                        if (hasPhone.equalsIgnoreCase("1")) {
//                            Cursor phones = cr.query(
//                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
//                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
//                                    null, null);
//                            phones.moveToFirst();
//                            cNumber = phones.getString(phones.getColumnIndex("data1"));
//                            Log.d(TAG, "onActivityResult: " + cNumber);
//                        }
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        Log.d(TAG, "onActivityResult: " + name);

                        boolean x = mydbHelper.insertData(name, cNumber, null);
                        allContactsArrayList.add(new ContactDetails(name, cNumber, null));
                        allContactsAdapter.notifyDataSetChanged();
                        if (!x){
                            Toast.makeText(ctx, "Contact Not Available", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
    }


    void displayContacts(View rootView){
        allContactsArrayList.clear();
        Cursor res = mydbHelper.getAllData();
        while (res.moveToNext()) {
            Log.d(TAG, "onClick: " + res.getString(1));
            Log.d(TAG, "onClick: " + res.getString(2));
            Log.d(TAG, "onClick: " + "-----------------------");
            allContactsArrayList.add(new ContactDetails(res.getString(1), res.getString(2), null));
        }
        allContactsRv = (RecyclerView) rootView.findViewById(R.id.rvList);
        allContactsAdapter = new AllContactsAdapter(ctx, allContactsArrayList);
        allContactsRv.setLayoutManager(new LinearLayoutManager(ctx));
        allContactsRv.setAdapter(allContactsAdapter);

        if (res.getCount() == 0){
            Toast.makeText(ctx, "No Numbers Added", Toast.LENGTH_SHORT).show();
        }

    }

    public static ArrayList<ContactDetails> getAllContactsArrayList(){
        return allContactsArrayList;
    }
}
