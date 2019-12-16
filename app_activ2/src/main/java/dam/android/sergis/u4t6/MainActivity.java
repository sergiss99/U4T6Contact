package dam.android.sergis.u4t6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener, View.OnTouchListener, MyAdapter.OnItemLongClick {
    MyContacts myContacts;
    RecyclerView recyclerView;
    TextView infor;
    LinearLayout liText;

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
        recyclerView.setAdapter(new MyAdapter(myContacts, this, this));

        if (myContacts.getCount() > 0) findViewById(R.id.tvEnptyList).setVisibility(View.INVISIBLE);

    }

    private void setUI() {
        recyclerView = findViewById(R.id.recyclerViewContacts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnTouchListener(this);
        liText = findViewById(R.id.liText);
        infor = findViewById(R.id.info);
        infor.setVisibility(View.INVISIBLE);
        liText.setVisibility(View.INVISIBLE);
        LinearLayoutManager lMa = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(lMa);

        //TODO Ex1: Creacion del separador entre filas.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                lMa.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);


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
            } else {
                Toast.makeText(this, getString(R.string.contacts_read_required), Toast.LENGTH_LONG).show();
            }
        } else {

            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //TODO Ex2: Al hacer clik en un item, mostramos su informacion en un textView
    @Override
    public void onItemClick(ContactItem contactIte) {
        infor.setVisibility(View.VISIBLE);
        liText.setVisibility(View.VISIBLE);

        infor.setText(contactIte.toString());
    }

    //TODO Ex2: Tras realizar cualquier accion sobre la lista, escondemos el textView
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {
            case R.id.recyclerViewContacts:
                infor.setVisibility(View.INVISIBLE);
                liText.setVisibility(View.INVISIBLE);
                break;
        }
        return false;
    }


    //TODO Ex2: Tras una pulsacion larga, abrimos la informacion del contacto
    @Override
    public boolean onItemLongClick(ContactItem contactIte) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(contactIte.getContactID()));
        intent.setData(uri);
        startActivityForResult(intent, 1);
        infor.setVisibility(View.INVISIBLE);
        liText.setVisibility(View.INVISIBLE);
        return true;
    }

    //TODO Ex2: Tras obtener el resultado de la activity que muestra la informacion del contacto, actualizamos la lista.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (checkPermissions()) setListAdapter();
    }

}
