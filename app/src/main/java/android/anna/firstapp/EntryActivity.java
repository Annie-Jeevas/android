package android.anna.firstapp;

import android.anna.firstapp.helpers.DBHelper;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EntryActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    DBHelper dbHelper;
    SQLiteDatabase db;
    String LOG_TAG = "EntryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        name = (EditText) findViewById(R.id.nameEdit);
        email = (EditText) findViewById(R.id.emailEdit);
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    public void onButtonOKClick(View view) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name.getText().toString());
        contentValues.put("email", email.getText().toString());

        long rowId = db.insert("clients", null, contentValues);
        Log.d(LOG_TAG, "insert row with ID " + rowId);
    }

    @Override
    protected void onStop() {
        dbHelper.close();
        db.close();
        super.onStop();
    }
}
