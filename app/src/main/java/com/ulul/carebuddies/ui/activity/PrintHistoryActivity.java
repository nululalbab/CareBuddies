package com.ulul.carebuddies.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.ulul.carebuddies.R;
import com.ulul.carebuddies.adapter.HistoryAdapter;
import com.ulul.carebuddies.contract.ScheduleContract;
import com.ulul.carebuddies.model.Medicine;
import com.ulul.carebuddies.model.Schedule;
import com.ulul.carebuddies.presenter.HistoryPresenter;

import java.util.HashMap;
import java.util.List;

public class PrintHistoryActivity extends AppCompatActivity implements ScheduleContract.View {
    HistoryPresenter presenter;
    TextView countSucccess, countFailure;
    LinearLayout card_success;
    MaterialCalendarView calendarView;
    Button btn_print_resume;
    HistoryAdapter adapter;
    RecyclerView recyclerView;
    ProgressDialog dialog;
    List<Schedule> dataList;
    private Bitmap bitmap;
    ConstraintLayout cl_print;
    FloatingActionButton fab_save;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_history);
        fab_save = (FloatingActionButton)findViewById(R.id.fab_save);
        cl_print = (ConstraintLayout) findViewById(R.id.cl_print);

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
        float width = displaymetrics.widthPixels ;

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
        String targetPdf = "/sdcard/pdffromlayout.pdf";
        File filePath;
        filePath = new File(targetPdf);
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
        File file = new File("/sdcard/pdffromlayout.pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {

            }
        }
    }


}
