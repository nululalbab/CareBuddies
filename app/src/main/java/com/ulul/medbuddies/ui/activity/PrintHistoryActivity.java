package com.ulul.medbuddies.ui.activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.ulul.medbuddies.R;
import com.ulul.medbuddies.adapter.PrintAdapter;
import com.ulul.medbuddies.contract.ScheduleContract;
import com.ulul.medbuddies.model.Medicine;
import com.ulul.medbuddies.model.Schedule;
import com.ulul.medbuddies.presenter.SchedulePresenter;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintHistoryActivity extends AppCompatActivity implements ScheduleContract.View {
    SchedulePresenter presenter;
    RecyclerView recyclerView;
    PrintAdapter adapter;
    ProgressDialog dialog;
    private Bitmap bitmap;
    ConstraintLayout cl_print;
    FloatingActionButton fab_save;
    HashMap<String, List<Schedule>> hashMap = new HashMap<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_history);
        fab_save = (FloatingActionButton)findViewById(R.id.fab_save);
        cl_print = (ConstraintLayout) findViewById(R.id.cl_print);

        dialog = new ProgressDialog(PrintHistoryActivity.this);
        dialog.setMessage("Loading. Please wait...");

        recyclerView = findViewById(R.id.recycler_view_print);

        adapter = new PrintAdapter(new ArrayList<Schedule>());
        presenter = new SchedulePresenter(this);
        presenter.setContext(this);
        presenter.getListScheduleDone();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("size"," "+cl_print.getWidth() +"  "+cl_print.getWidth());
                bitmap = loadBitmapFromView(cl_print, cl_print.getWidth(), cl_print.getHeight());

                createPdf();
            }
        });
        // cl the layout for this fragment

    }

    @Override
    public void listSchedule(List<Schedule> list) {

    }

    @Override
    public void informationStatusAll(List<Schedule> finish, List<Schedule> unfinish) {

    }

    @Override
    public void informatinoStatusOne(List<Schedule> finish, List<Schedule> unfinish) {

    }

    @Override
    public void listMedicine(List<Medicine> list) {

    }

    @Override
    public void listScheduleByPatient(HashMap<String, List<Schedule>> list) {
        this.hashMap = list;
        Log.e("test isi",String.valueOf(list.size()));
        Calendar c = Calendar.getInstance();
        Date now = new Date();
        c.setTime(now);
        c.add(Calendar.DATE, -1);
        now = c.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Map.Entry<String, List<Schedule>> h : list.entrySet()){
            Log.e("for by patient", h.getValue().toString());
            for (Schedule value: h.getValue()){
                if (value.getStatus() == 0){
                    try {
                        Date dateS = formatter.parse(value.getJadwal());
                        if (now.after(dateS)){
                            adapter.putList(value);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    adapter.putList(value);
                }
            }
        }
//        adapter.updateList(list);
        adapter.notifyDataSetChanged();
        Log.e("test bro","masuk gak");

    }

    @Override
    public void listScheduleSuccess(List<Schedule> list) {

    }

    @Override
    public void listScheduleFailure(List<Schedule> list) {

    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void message(String msg) {

    }
    public static Bitmap loadBitmapFromView(View v, int width, int height) {

        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void createPdf(){

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels*1.41f ;

        final int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf =  this.getFilesDir() + "pdffromlayout.pdf";
        File startFile;
        startFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "");
        File filePath = new File(startFile + File.separator, "resume_history.pdf");
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show();

        openGeneratedPDF();

    }

    private void openGeneratedPDF(){
//        String targetPdf =  this.getFilesDir() + "pdffromlayout.pdf";
//        File file = new File(getBaseContext().getFilesDir(), "pdffromlayout.pdf");

        File startFile;
        startFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "");
        File file = new File(startFile + File.separator, "resume_history.pdf");
        if (file.exists())
        {
            if(Build.VERSION.SDK_INT>=24){
                try{
                    Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                    m.invoke(null);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            try
            {
                Intent intent=new Intent(Intent.ACTION_VIEW);
//            intent.setPackage("com.adobe.reader");
                Uri uri = Uri.fromFile(file);
                intent.setDataAndType(uri, "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                Toast.makeText(this, "Try Catch", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(this, "No Application Available to View PDF", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File doesn't exists", Toast.LENGTH_SHORT).show();
        }
    }


}
