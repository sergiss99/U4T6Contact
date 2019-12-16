package dam.android.sergis.u4t6;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private MyContacts myContacts;
    private OnItemClickListener listener;
    private OnItemLongClick listener2;

    public interface OnItemClickListener {
        void onItemClick(ContactItem contactIte);
    }

    public interface OnItemLongClick {
        boolean onItemLongClick(ContactItem contactIte);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView nombre;
        TextView numero;
        ImageView ima;
        ConstraintLayout conLay;

        public MyViewHolder(View itemView) {
            super(itemView);
            //TODO Ex1: obtenemos los diferentes campos de la vista

            id = itemView.findViewById(R.id.tvId);
            nombre = itemView.findViewById(R.id.tvNombre);
            numero = itemView.findViewById(R.id.tvNumero);
            ima = itemView.findViewById(R.id.imImage);
            conLay = itemView.findViewById(R.id.conslay);
        }

        public void bind(final ContactItem contactData, OnItemClickListener listener, OnItemLongClick listener2) {
            //TODO Ex1: establecemos los valores a cada textview e imageView

            this.id.setText(contactData.getId2());
            this.nombre.setText(contactData.getName());
            this.numero.setText(contactData.getNumber());

            if (contactData.getImage() != null) {
                this.ima.setImageURI(Uri.parse(contactData.getImage()));
            }
            this.conLay.setOnClickListener(v -> {
                listener.onItemClick(contactData);
            });
            this.conLay.setOnLongClickListener(v -> {
                return listener2.onItemLongClick(contactData);
            });


        }
    }

    MyAdapter(MyContacts contacts, OnItemClickListener listener, OnItemLongClick listener2) {
        this.myContacts = contacts;
        this.listener = listener;
        this.listener2 = listener2;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tv = LayoutInflater.from(parent.getContext()).inflate(R.layout.constraint_layout, parent, false);
        return new MyViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(myContacts.getContactData(position), listener, listener2);
    }

    @Override
    public int getItemCount() {
        return myContacts.getCount();
    }


}
