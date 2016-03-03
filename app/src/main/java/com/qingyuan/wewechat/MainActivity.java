package com.qingyuan.wewechat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        ImageView right = (ImageView) findViewById(R.id.right);
        ImageView left = (ImageView) findViewById(R.id.left);
        Bitmap bpRight = BitmapFactory.decodeResource(getResources(),R.mipmap.big_meinv);
        Bitmap bpLeft = BitmapFactory.decodeResource(getResources(),R.mipmap.small_meinv);
        right.setImageBitmap(canvasTriangle(bpRight, 0));
        left.setImageBitmap(canvasTriangle(bpLeft,1));

    }

    /**
     * 绘制成微信聊天效果
     * @param bitmapimg
     * @param direct
     * @return
     */
    public static Bitmap canvasTriangle(Bitmap bitmapimg, int direct) {
        Bitmap output = Bitmap.createBitmap(bitmapimg.getWidth(),
                bitmapimg.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(output);
        //设置默认背景颜色
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmapimg.getWidth(),
                bitmapimg.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        //右边
        if (direct == 0) {
            canvas.drawRect(0, 0, bitmapimg.getWidth() - 15, bitmapimg.getHeight(), paint);
            Path path = new Path();
            path.moveTo(bitmapimg.getWidth() - 15, 10);
            path.lineTo(bitmapimg.getWidth(), 20);
            path.lineTo(bitmapimg.getWidth() - 15, 30);
            path.lineTo(bitmapimg.getWidth() - 15, 10);
            canvas.drawPath(path, paint);
        }
        //左边
        if (direct == 1) {
            canvas.drawRect(15, 0, bitmapimg.getWidth(), bitmapimg.getHeight(), paint);
            Path path = new Path();
            path.moveTo(15, 10);
            path.lineTo(0, 20);
            path.lineTo(15, 30);
            path.lineTo(15, 10);
            canvas.drawPath(path, paint);
        }
        //两层绘制交集。显示上层
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapimg, rect, rect, paint);
        return output;
    }
}
