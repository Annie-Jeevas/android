package android.anna.firstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class ZoomActivity extends AppCompatActivity {

    private Drawable image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(new Draw(this));
    }
    public class Draw extends View {

        private Drawable image;
        private float scaleFactor = 1.0f;
        private ScaleGestureDetector scaleGestureDetector;

        public Draw(Context context) {
            super(context);
            image = context.getResources().getDrawable(R.drawable.big_red_button);
            setFocusable(true);

            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            scaleGestureDetector.onTouchEvent(event);
            invalidate();
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.save();
            canvas.scale(scaleFactor, scaleFactor);
            image.draw(canvas);
            canvas.restore();
        }

        private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

            @Override
            public boolean onScale(ScaleGestureDetector detector) {

                scaleFactor *= detector.getScaleFactor();
                scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
                invalidate();
                return true;
            }
        }
    }
}
