package android.anna.firstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {
        Path path;
        Paint paint;

        public DrawView(Context context) {
            super(context);
            path = new Path();
            paint = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            float mid = getWidth() / 2;
            float min = Math.min(getWidth(), getHeight());
            float half = min / 2;
            mid = mid - half;
            path.reset();
            path.moveTo(mid + half * 0.5f, half * 0.84f);
            path.lineTo(mid + half * 1.5f, half * 0.84f);
            path.lineTo(mid + half * 0.68f, half * 1.45f);
            path.lineTo(mid + half * 1.0f, half * 0.5f);
            path.lineTo(mid + half * 1.32f, half * 1.45f);
            path.close();
            canvas.drawPath(path, paint);
        }
    }
}
