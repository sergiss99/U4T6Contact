package dam.android.sergis.u4t6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyContacts myContacts;
    RecyclerView recyclerView;

    private static String[] PERMISSIONS_CONTACTS = {Manifest.permission.READ_CONTACTS};

    private static final int REQUEST_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();

        if (checkPermissions()) setListAdapter();
    }

    private void setListAdapter() {
        myContacts = new MyContacts(this);
        recyclerView.setAdapter(new MyAdapter(myContacts));

        if (myContacts.getCount() > 0) findViewById(R.id.tvEnptyList).setVisibility(View.INVISIBLE);

    }

    private void setUI() {
        recyclerView = findViewById(R.id.recyclerViewContacts);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }


    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, MainActivity.PERMISSIONS_CONTACTS, MainActivity.REQUEST_CONTACTS);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setListAdapter();
            }
            else {
                Toast.makeText(this, getString(R.string.contacts_read_required), Toast.LENGTH_LONG).show();
            }
        } else {

            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
