package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mim.entryapp.R;
import com.mim.entryapp.models.Data;
import com.mim.entryapp.models.SaveAddressModel;
import com.mim.entryapp.models.AddressResult;
import com.mim.entryapp.retrofit.RetrofitClient;
import com.mim.entryapp.utils.SharedPrefsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mim.entryapp.Activities.CommunityHallActivity.BASE_URL;

public class AddHomeActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    private EditText et_location,et_select_city,et_search_society,et_tower,et_flat_no,
            et_upload2,et_file_name2, et_upload_file2,et_upload3,et_file_name3,et_upload_file3;
    private AutoCompleteTextView et_ownership_status;
    private ImageButton backBtn;
    private Button btn_add_flat;
    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";
    private TextView tv_upload_doc,tv_add_more_doc,tv_add_more_doc1;
    String docDesc = "Adhar card";
    String document1 = "";
    String phone;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    ImageView imageView;
    Bitmap bitmap;
    private static final int REQUEST_CODE_READ_EXTERNAL_PERMISSION = 2;
    String image_name;
    String imagePath;
    private String encodedImage;

    String[] languages = { "Owner", "Rented" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home);

        sharedPreferences = getSharedPreferences("login",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        init();
        listener();

        phone = SharedPrefsUtils.getStringPreference(this,"mobile");

        String[] tower = { "Rented", "Owner" };
        et_ownership_status =  findViewById(R.id.et_ownership_status);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddHomeActivity.this, android.R.layout.simple_spinner_item, tower);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        et_ownership_status.setAdapter(adapter);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());

        tv_add_more_doc1 = findViewById(R.id.tv_add_more_doc1);

        tv_add_more_doc = findViewById(R.id.tv_add_more_doc);
        tv_add_more_doc.setOnClickListener(v -> {
            findViewById(R.id.et_doc_name_2).setVisibility(View.VISIBLE);
            findViewById(R.id.et_doc_num_2).setVisibility(View.VISIBLE);
            findViewById(R.id.et_upload_doc_2).setVisibility(View.VISIBLE);
            findViewById(R.id.tv_add_more_doc).setVisibility(View.INVISIBLE);
            findViewById(R.id.tv_add_more_doc1).setVisibility(View.VISIBLE);

            tv_add_more_doc1.setOnClickListener(v1 -> {
                findViewById(R.id.et_upload_doc_3).setVisibility(View.VISIBLE);
                findViewById(R.id.et_doc_name_3).setVisibility(View.VISIBLE);
                findViewById(R.id.et_doc_num_3).setVisibility(View.VISIBLE);
                findViewById(R.id.tv_add_more_doc1).setVisibility(View.INVISIBLE);

            });
        });

    }

    private void init() {
        context = this;
        btn_add_flat = findViewById(R.id.btn_add_flat);
        et_location = findViewById(R.id.et_location);
        et_select_city = findViewById(R.id.et_select_city);
        et_search_society = findViewById(R.id.et_search_society);
        et_tower = findViewById(R.id.et_tower);
        et_flat_no = findViewById(R.id.et_flat_no);
        tv_upload_doc = findViewById(R.id.tv_upload_doc);
//        et_ownership_status = findViewById(R.id.et_ownership_status);

    }

    private void listener() {
        btn_add_flat.setOnClickListener(this);
        et_location.setOnClickListener(this);
        et_select_city.setOnClickListener(this);
        et_search_society.setOnClickListener(this);
        et_flat_no.setOnClickListener(this);
        et_tower.setOnClickListener(this);
//        et_ownership_status.setOnClickListener(this);
        tv_upload_doc.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.btn_add_flat:

                postAddHomeApi();

                final String city = et_select_city.getText().toString();
                if (city.isEmpty()) {
                    et_select_city.setError("Enter a valid city");
                }

                final String society = et_search_society.getText().toString();
                if (society.isEmpty()) {
                    et_search_society.setError("Enter society name");
                }

                final String tower = et_tower.getText().toString();
                if (tower.isEmpty()) {
                    et_tower.setError("Enter Tower/Villa name");
                }

                final String flat = et_flat_no.getText().toString();
                if (flat.isEmpty()){
                    et_flat_no.setError("Enter a flat no");
                }

//                final String owner_status = et_ownership_status.getText().toString();
//                if (owner_status.isEmpty()){
//                    et_ownership_status.setError("Enter ownership status");
//                }

//            SaveAddressModel saveAddressModel = new SaveAddressModel();
//            saveAddressModel.setApiKey(api_key);
//            saveAddressModel.setMobileNo(phone);
//            saveAddressModel.setSocietyCode(et_search_society.getText().toString());
//            saveAddressModel.setTowerName(et_tower.getText().toString());
//            saveAddressModel.setFlatNo(et_flat_no.getText().toString());
//            saveAddressModel.setOwnershipStatus(et_ownership_status.getText().toString());
//
//            saveAddressApi(saveAddressModel);

//            case R.id.tv_upload_doc:
//                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(galleryIntent, 0);


        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//            // When an Image is picked
//            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
//                // Get the Image from data
//                Uri selectedImage = data.getData();
//                Bitmap selectedImageBitmap = null;
//
//                try {
//                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                imageView.setImageBitmap(selectedImageBitmap);
////                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
////                selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
////                byte[] byteArrayImage = byteArrayOutputStream.toByteArray();
////                encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
//
//
////                String selectedFilePath = FilePath.getPath(this,selectedImage);
////                String[] filePathColumn = {MediaStore.Images.Media.DATA};
////                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
////                assert cursor != null;
////                cursor.moveToFirst();
////                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
////                imagePath = cursor.getString(columnIndex);
////                str1.setText(mediaPath);
//                // Set the Image in ImageView for Previewing the Media
////                imageView.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
////                cursor.close();
////                uploadDocumentApi();
//            } // When a Video is picked
//        } catch (Exception e) {
//            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
//        }
//    }

    //image converted to base64
    public static String file2Base64(String filePath) {
        FileInputStream fis = null;
        String base64String = "";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            fis = new FileInputStream(filePath);
            byte[] buffer = new byte[1024 * 100];
            int count = 0;
            while ((count = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, count);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        base64String = Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT);
        return base64String;

    }

//    private void saveAddressApi(SaveAddressModel saveAddressModel){
//        RetrofitClient.getApiService().saveAddress(saveAddressModel,"application/json").enqueue(new Callback<AddressResult>() {
//            @Override
//            public void onResponse(Call<AddressResult> call, Response<AddressResult> response) {
//                if (response.isSuccessful()){
//                    Data addressData = response.body().getData();
//                    if (addressData.getResult() == "success")
//                        Log.i("sushant", "onResponse: "+response.errorBody());
//                    startActivity(new Intent(AddHomeActivity.this, HomeAddressActivity.class));
//                    Toast.makeText(context, "Details have been saved", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AddressResult> call, Throwable t) {
//                Log.i("sushant", "onFailure: "+t.getLocalizedMessage());
//                Toast.makeText(context, "Data is not saved", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

    private void uploadDocumentApi(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading Image...");
        pd.setCancelable(false);
        pd.show();
        //creating a file
        File file = new File(imagePath);
    }

    private void postAddHomeApi(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("apiKey",api_key);
            jsonObject.put( "mobileNo",phone);
            jsonObject.put( "societyCode",et_search_society.getText().toString());
            jsonObject.put( "towerName",et_tower.getText().toString());
            jsonObject.put( "flatNo",et_flat_no.getText().toString());
            jsonObject.put( "ownershipStatus",et_ownership_status.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post( BASE_URL+ "api/societyApp/SaveAddressDetail")
                .addJSONObjectBody(jsonObject)
                .setTag("Sushant")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("res",response.toString());
                        Toast.makeText(context, "Request sent to Admin", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("res",anError.getResponse().toString());
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();

                    }

                });

    }


//    private void postAddHomeApi(){
//
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("apiKey",api_key);
//            jsonObject.put( "mobile",phone);
//            jsonObject.put( "societyCode",et_search_society.getText().toString());
//            jsonObject.put( "towerName",et_tower.getText().toString());
//            jsonObject.put( "flatNo",et_flat_no.getText().toString());
//            jsonObject.put( "ownershipStatus",et_ownership_status.getText().toString());
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        AndroidNetworking.post( BASE_URL+ "api/societyApp/SaveAddressDetail")
//                .addJSONObjectBody(jsonObject)
//                .setTag("Sushant")
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.e("res",response.toString());
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Log.e("res",anError.getResponse().toString());
//
//                    }
//
//                });
//    }

        }
