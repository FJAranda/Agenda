package example.com.agenda.adapter;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import example.com.agenda.MainActivity;
import example.com.agenda.R;
import example.com.agenda.data.db.pojo.Contacto;
import example.com.agenda.ui.AgendaApplication;
import example.com.agenda.ui.addedit.AddEditContract;
import example.com.agenda.ui.addedit.AddEditFragment;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.ViewHolder>{
    private AgendaAdapter.OnItemClickListener listener;
    private AgendaAdapter.OnItemLongClickListener longClickListener;
    private ArrayList<Contacto> contactos;

    public AgendaAdapter(ArrayList<Contacto> agenda, OnItemClickListener listener, OnItemLongClickListener longClickListener){
        this.contactos = agenda;
        this.listener = listener;
        this.longClickListener = longClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AgendaAdapter.ViewHolder holder, int position) {
        Contacto contacto = contactos.get(position);
        holder.bind(contacto, listener, longClickListener);
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Contacto contacto);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Contacto contacto);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombre;
        private TextView tvNumero;
        private TextView tvFecha;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvNumero = itemView.findViewById(R.id.tvNumero);
            tvFecha = itemView.findViewById(R.id.tvFecha);
        }

        public void bind(final Contacto contacto, final OnItemClickListener listener, final OnItemLongClickListener longClickListener){
            tvNombre.setText(contacto.getNombre());
            tvNumero.setText(contacto.getTelefono());
            tvFecha.setText(contacto.getFecha().toString());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.onItemClick(contacto);
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (longClickListener != null){
                        longClickListener.onItemLongClick(contacto);
                    }
                    return true;
                }
            });

        }
    }
}
