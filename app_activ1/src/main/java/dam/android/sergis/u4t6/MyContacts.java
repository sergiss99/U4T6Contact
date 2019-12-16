package dam.android.sergis.u4t6;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class MyContacts {
    private ArrayList<ContactItem> myDataSet;
    private Context context;


    public MyContacts(Context context) {
        this.context = context;
        this.myDataSet = getContacts();

    }

    private ArrayList<ContactItem> getContacts() {
        ContactItem contIt = null;
        ArrayList<ContactItem> contactList = new ArrayList<>();

        ContentResolver contentResolver = context.getContentResolver();

        //TODO Ex1: Anyadimos a la consulta mas datos(CONTACT_ID, LOOKUP_KEY, RAW_CONTACT_ID, TYPE, y PHOTO_THUMBNAIL_URI )
        String[] projection = new String[]{ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.Data.CONTACT_ID,
                ContactsContract.Data.LOOKUP_KEY, ContactsContract.Data.RAW_CONTACT_ID, ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.Contacts.PHOTO_THUMBNAIL_URI};

        String selectionFilter = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " +
                ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        Cursor contactsCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                projection,
                selectionFilter,
                null,
                ContactsContract.Data.DISPLAY_NAME + " ASC");

        if (contactsCursor != null) {

            //TODO Ex1: obtenemos la columna de cada campo
            int nameIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME);
            int numberIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int id = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone._ID);
            int contId = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.CONTACT_ID);
            int key = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.LOOKUP_KEY);
            int raw = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.RAW_CONTACT_ID);
            int type = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.TYPE);
            int image = contactsCursor.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI);

            //TODO Ex1: Recorremos los contactos y añadimos sus valores a el ContactItem
            while (contactsCursor.moveToNext()) {
                contIt = new ContactItem();
                contIt.setName(contactsCursor.getString(nameIndex));
                contIt.setNumber(contactsCursor.getString(numberIndex));
                contIt.setId2(contactsCursor.getString(id));
                contIt.setContactID(contactsCursor.getString(contId));
                contIt.setKey2(contactsCursor.getString(key));
                contIt.setRawId(contactsCursor.getString(raw));
                contIt.setTypeCo(contactsCursor.getString(type));

                if (contactsCursor.getString(image) != null) {
                    contIt.setImage(contactsCursor.getString(image));
                }

                //añadimos el ContactItem al arrayList
                contactList.add(contIt);

            }
            contactsCursor.close();
        }
        return contactList;
    }

    public ContactItem getContactData(int position) {
        return myDataSet.get(position);
    }

    public int getCount() {

        return myDataSet.size();
    }
}
