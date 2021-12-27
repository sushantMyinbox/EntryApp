package com.mim.entryapp.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mim.entryapp.Adapter.SocietyListAdapter;
import com.mim.entryapp.R;
import com.mim.entryapp.models.SocietyListModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SocietyDataActivity extends AppCompatActivity {

    ImageView iv_options;
    private TextView tv_upload_doc;
    private static final int PICK_IMAGE = 1;

    private String filePath = null;
    private File sourceFile;

    private RecyclerView recyclerView_society_list;

    private ArrayList<SocietyListModel> societyListModelArrayList;

    private ProgressBar determinateBar;

    private int i = 0;
    private Handler hdlr = new Handler();

    private static final int FILE_SELECT_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_data);

        recyclerView_society_list = findViewById(R.id.recyclerView_society_list);

        // here we have created new array list and added data to it.
        societyListModelArrayList = new ArrayList<>();
        societyListModelArrayList.add(new SocietyListModel("Sushant","9999990909",
                "sushant@myinbox.com", "1230908907621239","XPHJ0973",
                "DLF Capital Flat No - 225, Tower - B, Sec 125, Noida , Pin-201301"));
        societyListModelArrayList.add(new SocietyListModel("Rocky","9999990909",
                "sushant@myinbox.com", "1230908907621239","XPHJ0973",
                "DLF Capital Flat No - 225, Tower - B, Sec 123, Noida , Pin-201301"));
        societyListModelArrayList.add(new SocietyListModel("John","9999990909",
                "sushant@myinbox.com", "1230908907621239","XPHJ0973",
                "DLF Capital Flat No - 225, Tower - B, Sec 125, Noida , Pin-201301"));

        // we are initializing our adapter class and passing our arraylist to it.
        SocietyListAdapter societyListAdapter = new SocietyListAdapter(this, societyListModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView_society_list.setLayoutManager(linearLayoutManager);
        recyclerView_society_list.setAdapter(societyListAdapter);

        tv_upload_doc = findViewById(R.id.tv_upload_doc);
        tv_upload_doc.setOnClickListener(v -> showFileChooser());


        iv_options = findViewById(R.id.iv_options);
        iv_options.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(SocietyDataActivity.this, iv_options);

            // Inflating popup menu from popup_menu.xml file
            popupMenu.getMenuInflater().inflate(R.menu.filter_option_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {

                // Toast message on menu item clicked
                Toast.makeText(SocietyDataActivity.this, "" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            });
            // Showing the popup menu
            popupMenu.show();
        });
    }

    private void showFileChooser(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Copy"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void showOptions(View view){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    File file = new File(uri.getPath());//create path from uri
                    final String[] split = file.getPath().split(":");//split the path.
                    filePath = split[1];//assign it to a string(your choice).

                    int maxBufferSize = 1 * 1024 * 1024;

                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        Log.e("InputStream Size","Size " + inputStream);
                        int  bytesAvailable = inputStream.available();
                        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        final byte[] buffers = new byte[bufferSize];

                        FileOutputStream outputStream = new FileOutputStream(file );
                        int read = 0;
                        while ((read = inputStream.read(buffers)) != -1) {
                            outputStream.write(buffers, 0, read);
                        }
                        Log.e("File Size","Size " + file.length());
                        inputStream.close();
                        outputStream.close();

                        file.getPath();
                        Log.e("File Path","Path " + file.getPath());
                        file.length();
                        Log.e("File Size","Size " + file.length());

                        if(file.length() > 0){

                            sourceFile = file;
                            saveFile(sourceFile);
                        }


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                    }

                } else {

                }

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveFile (File sourceFile) {

        try {

            File currentFile = sourceFile;

            Bitmap myBitmap = BitmapFactory.decodeFile(currentFile.getAbsolutePath());


            String path = currentFile.getAbsolutePath();
            String idStr = path.substring(path.lastIndexOf('/') + 1);
            File filepath = Environment.getExternalStorageDirectory();
            File dir = new File(filepath.getAbsolutePath() + "/" + "yourFolderName" + "/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = currentFile.getName();
            File file = new File(dir, fileName);
            if (file.exists()) file.delete();
            FileOutputStream fos = new FileOutputStream(file);
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 65, fos);
            fos.flush();
            fos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

//Copy from here...
        int itemId = item.getItemId();

        if(item.isChecked())
        {
            if(R.id.tower == itemId)     //Individual checkbox logic
            {   /*TODO unchecked Action*/}
            item.setChecked(false);                   //Toggles checkbox state.
        }
        else
        {
            if(R.id.block == itemId)    //Individual checkbox logic
            {/*TODO checked Action*/}
            item.setChecked(true);                   //Toggles checkbox state.
        }
//...To here in to your onOptionsItemSelected() method, then make sure your variables are all sweet.

        return super.onOptionsItemSelected(item);
    }
    }
