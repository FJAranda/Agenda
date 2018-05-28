package example.com.agenda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.agenda.R;
import example.com.agenda.data.db.pojo.Contacto;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.ViewHolder>{
    private AgendaAdapter.OnItemClickListener listener;
    private ArrayList<Contacto> contactos;

    @Override
    public AgendaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AgendaAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface OnItemClickListener {
        void onItemClick(Contacto contacto);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre;
        private TextView tvNumero;
        private TextView tvFecha;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvNumero = itemView.findViewById(R.id.tvNumero);
            tvFecha = itemView.findViewById(R.id.tvFecha);
        }

        public void bind(final Contacto contacto, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(contacto);
                }
            });

        }
    }
}
