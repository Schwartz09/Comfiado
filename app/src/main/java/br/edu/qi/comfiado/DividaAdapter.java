package br.edu.qi.comfiado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.edu.qi.comfiado.modelo.Divida;
import br.edu.qi.comfiado.modelo.Usuario;

public class DividaAdapter extends BaseAdapter {

    private Context contexto;
    private LayoutInflater inflater;

    private List<Map.Entry<Usuario, Divida>> data;

    private int corFonteValor;


    public DividaAdapter(Context contexto, HashMap<Usuario, Divida> map, int corFonteValor) {
        this.contexto = contexto;
        this.inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.data = new ArrayList<Map.Entry<Usuario, Divida>>();
        this.data.addAll(map.entrySet());

        this.corFonteValor = corFonteValor;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Map.Entry<Usuario, Divida> getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        final View result;

        if (view == null) {
            result = this.inflater.inflate(R.layout.divida_list_item, parent, false);
        } else {
            result = view;
        }

        Map.Entry<Usuario, Divida> item = getItem(i);

        TextView txtNome = result.findViewById(R.id.txtNome);
        TextView txtValor = result.findViewById(R.id.txtValor);

        if (item.getKey() == null) {
            txtNome.setText(item.getValue().getCodigo());
            txtNome.setTextColor(ContextCompat.getColor(this.contexto, R.color.vermelho));
        } else {
            txtNome.setText(item.getKey().getNome());

        }
        txtValor.setText(this.formatarParaDinheiro(item.getValue().getValor()));
        txtValor.setTextColor(this.corFonteValor);

        return result;
    }

    private String formatarParaDinheiro(float v) {

        Locale locale = new Locale("pt", "BR");

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        return currencyFormatter.format(v);

    }
}
