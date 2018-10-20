package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class ViewBill extends AppCompatActivity {

    String invoiceNumber =  "";
    public static int white = 0xFFFFFFFF;
    public static int black = 0xFF000000;
    public final static int WIDTH=500;
    ImageView imgView ;
    TextView txtInvoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bill);
        invoiceNumber = getIntent().getStringExtra("detail");
        imgView = findViewById(R.id.img_bill);
        txtInvoice = findViewById(R.id.txt_invoice);
        txtInvoice.setText("Bill Details : \n"+invoiceNumber);

        try
        {
            Bitmap bmp =  encodeAsBitmap(invoiceNumber);
            imgView.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public  void onCloseClick(View v)
    {
        onBackPressed();
    }

    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        Bitmap bitmap=null;
        try
        {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);

            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? black:white;
                }
            }
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, 500, 0, 0, w, h);
        } catch (Exception iae) {
            iae.printStackTrace();
            return null;
        }
        return bitmap;
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(ViewBill.this,MenuActivity.class);
        startActivity(in);
        finish();
    }
}
