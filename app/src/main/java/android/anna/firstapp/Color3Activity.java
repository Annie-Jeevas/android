package android.anna.firstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Color3Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {
        Paint p;
        RectF rectf;
        float[] points;
        float[] points1;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            rectf = new RectF(700, 100, 800, 150);
            points = new float[]{100, 50, 150, 100, 150, 200, 50, 200, 50, 100};
            points1 = new
                    float[]{300, 200, 600, 200, 300, 300, 600, 300, 400, 100, 400, 400, 500, 100, 500, 400};
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);
            p.setColor(Color.RED);
            p.setStrokeWidth(10);
            canvas.drawPoints(points, p);
            canvas.drawLines(points1, p);
            p.setColor(Color.GREEN);
            canvas.drawRoundRect(rectf, 20, 20, p);
            rectf.offset(0, 150);
            canvas.drawOval(rectf, p);
            rectf.offsetTo(900, 100);
            rectf.inset(0, -25);
            canvas.drawArc(rectf, 90, 270, true, p);
            rectf.offset(0, 150);
            canvas.drawArc(rectf, 90, 270, false, p);
            p.setStrokeWidth(3);
            canvas.drawLine(150, 450, 150, 600, p);
            p.setColor(Color.BLUE);
            p.setTextSize(30);
            canvas.drawText("text left", 150, 500, p);
            p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("text center", 150, 525, p);
            p.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("text right", 150, 550, p);
        }
    }
}
