package android.anna.firstapp;

import android.anna.firstapp.helpers.DBHelper;
import android.anna.firstapp.model.Client;
import android.anna.firstapp.model.IModel;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntryActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    TableLayout table;

    DBHelper dbHelper;
    SQLiteDatabase db;
    static String LOG_TAG = "EntryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        name = (EditText) findViewById(R.id.nameEdit);
        email = (EditText) findViewById(R.id.emailEdit);
        table = (TableLayout) findViewById(R.id.table);
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

    public void onButtonReadClick(View view) {
        Log.d(LOG_TAG, "onButtonReadClick");
        Cursor cursor = db.query("clients", null, null, null, null, null, null);
        List<Client> clients = new ArrayList<Client>();
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex("id");
            int name = cursor.getColumnIndex("name");
            int email = cursor.getColumnIndex("email");

            do {
                Client client = new Client();
                client.setId(cursor.getInt(id));
                client.setName(cursor.getString(name));
                client.setEmail(cursor.getString(email));
                Log.d(LOG_TAG, client.toString());
                clients.add(client);
            } while (cursor.moveToNext());
        }
        fillTable(table, clients);
    }

    private static void fillTable(TableLayout table, List<? extends IModel> list) {
        Log.d(LOG_TAG, "onButtonReadClick");
        table.removeAllViews();
        if (list.isEmpty()) return;

        int columnNumber = list.get(0).getFieldNumber();
        for (int i = 0; i < list.size(); i++) {

            TableRow tableRow = new TableRow(table.getContext());
            tableRow.setPadding(5,5,5,5);
            String[] data = list.get(i).getDataForTable();
            for (int j = 0; j < columnNumber; j++) {
                TextView textView = new TextView(table.getContext());
                textView.setText(data[j]);
                textView.setPadding(5,5,5,5);

                textView.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
                tableRow.addView(textView, j);
            }

            table.addView(tableRow, i);
        }
    }

    @Override
    protected void onStop() {
        dbHelper.close();
        db.close();
        super.onStop();
    }
}
