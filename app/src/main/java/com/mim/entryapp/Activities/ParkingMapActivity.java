package com.mim.entryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mim.entryapp.R;
import com.mim.entryapp.models.AddressResult;
import com.mim.entryapp.models.Data;
import com.mim.entryapp.models.SaveVehicleModel;
import com.mim.entryapp.retrofit.RetrofitClient;
import com.mim.entryapp.utils.SharedPrefsUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParkingMapActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private Button btn_add_vehicle;
    private EditText et_reg_num, et_car_model, et_car_parking_level, et_car_parking_num;
    private TextView tv_upload_doc;

    private String api_key = "FB4E63A6EE314B8B9485FAC4A2B71FCF";
    String phone;
    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    Context context;

    private ImageView photoview2;
    float[] lastEvent = null;
    float d = 0f;
    float newRot = 0f;
    private boolean isZoomAndRotate;
    private boolean isOutSide;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private PointF start = new PointF();
    private PointF mid = new PointF();
    float oldDist = 1f;
    private float xCoOrdinate, yCoOrdinate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_map);

        context = this;

        sharedPreferences = getSharedPreferences("login",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        phone = SharedPrefsUtils.getStringPreference(this,"mobile");

        et_reg_num = findViewById(R.id.et_reg_num);
        et_car_model = findViewById(R.id.et_car_model);
        et_car_parking_level = findViewById(R.id.et_car_parking_level);
        et_car_parking_num = findViewById(R.id.et_car_parking_num);
        tv_upload_doc = findViewById(R.id.tv_upload_doc);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            onBackPressed();
        });

        btn_add_vehicle = findViewById(R.id.btn_add_vehicle);
        btn_add_vehicle.setOnClickListener(v -> {
            SaveVehicleModel saveVehicleModel = new SaveVehicleModel();
            saveVehicleModel.setApiKey(api_key);
            saveVehicleModel.setMobileNo(phone);
            saveVehicleModel.setRegistrationNumber(et_reg_num.getText().toString());
            saveVehicleModel.setModel(et_car_model.getText().toString());
            saveVehicleModel.setParkingLevel(et_car_parking_level.getText().toString());
            saveVehicleModel.setParkingNumber(et_car_parking_num.getText().toString());
            saveVehicleModel.setDocument(tv_upload_doc.getText().toString());

            postAddVehicleApi(saveVehicleModel);
        });

//        photoview2 = findViewById(R.id.imageView);
//        photoview2.setOnTouchListener((v, event) -> {
//            ImageView view = (ImageView) v;
//            view.bringToFront();
//            viewTransformation(view, event);
//            return true;
//        });
//
//    }
//
//    private void viewTransformation(ImageView view, MotionEvent event) {
//        switch (event.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_DOWN:
//                xCoOrdinate = view.getX() - event.getRawX();
//                yCoOrdinate = view.getY() - event.getRawY();
//
//                start.set(event.getX(), event.getY());
//                isOutSide = false;
//                mode = DRAG;
//                lastEvent = null;
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                oldDist = spacing(event);
//                if (oldDist > 10f) {
//                    midPoint(mid, event);
//                    mode = ZOOM;
//                }
//
//                lastEvent = new float[4];
//                lastEvent[0] = event.getX(0);
//                lastEvent[1] = event.getX(1);
//                lastEvent[2] = event.getY(0);
//                lastEvent[3] = event.getY(1);
//                d = rotation(event);
//                break;
//            case MotionEvent.ACTION_UP:
//                isZoomAndRotate = false;
//                if (mode == DRAG) {
//                    float x = event.getX();
//                    float y = event.getY();
//                }
//            case MotionEvent.ACTION_OUTSIDE:
//                isOutSide = true;
//                mode = NONE;
//                lastEvent = null;
//            case MotionEvent.ACTION_POINTER_UP:
//                mode = NONE;
//                lastEvent = null;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (!isOutSide) {
//                    if (mode == DRAG) {
//                        isZoomAndRotate = false;
//                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
//                    }
//                    if (mode == ZOOM && event.getPointerCount() == 2) {
//                        float newDist1 = spacing(event);
//                        if (newDist1 > 10f) {
//                            float scale = newDist1 / oldDist * view.getScaleX();
//                            view.setScaleX(scale);
//                            view.setScaleY(scale);
//                        }
//                        if (lastEvent != null) {
//                            newRot = rotation(event);
//                            view.setRotation((float) (view.getRotation() + (newRot - d)));
//                        }
//                    }
//                }
//                break;
//        }
//    }
//    private float rotation(MotionEvent event) {
//        double delta_x = (event.getX(0) - event.getX(1));
//        double delta_y = (event.getY(0) - event.getY(1));
//        double radians = Math.atan2(delta_y, delta_x);
//        return (float) Math.toDegrees(radians);
//    }
//
//    private float spacing(MotionEvent event) {
//        float x = event.getX(0) - event.getX(1);
//        float y = event.getY(0) - event.getY(1);
//        return (int) Math.sqrt(x * x + y * y);
//    }
//
//    private void midPoint(PointF point, MotionEvent event) {
//        float x = event.getX(0) + event.getX(1);
//        float y = event.getY(0) + event.getY(1);
//        point.set(x / 2, y / 2);
//    }

    }

    private void postAddVehicleApi(SaveVehicleModel saveVehicleModel){
        RetrofitClient.getApiService().saveVehicle(saveVehicleModel,"application/json").enqueue(new Callback<AddressResult>() {
            @Override
            public void onResponse(Call<AddressResult> call, Response<AddressResult> response) {
                if (response.isSuccessful()){
                    Data data2 = response.body().getData();
                    if (data2.getResult() == "success")
                        Log.i("sushant", "onResponse: "+response.errorBody());
//                            startActivity(new Intent(AddVisitorActivity.this, HomeFragment.class));
                    Toast.makeText(context, "Details have been saved", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddressResult> call, Throwable t) {
                Log.i("sushant", "onFailure: "+t.getLocalizedMessage());
                Toast.makeText(context, "Data is not saved", Toast.LENGTH_SHORT).show();
            }
        });

    }
}