package gouvea.lopes.mariana.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gouvea.lopes.mariana.lista.R;
import gouvea.lopes.mariana.lista.activity.MainActivity;
import gouvea.lopes.mariana.lista.model.MyItem;

//classe MyAdapter será responsável por construir e preencher um item da lista do RecycleView
public class MyAdapter extends RecyclerView.Adapter{
    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    /*onCreateViewHolder -> responsável por criar os elementos de interface
    para um item. Esses elementos são guardados em uma classe container do
    tipo ViewHolder.*/
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        /*inflador de layouts que será
        usado para ler o arquivo xml de layout do item e
        então criar os elementos de interface propriamente ditos*/
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        //esse objeto do tipo View (v) é guardado dentro de um objeto do tipo MyViewHolder
        View v = inflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
    }

    /*onBindViewHolder -> recebe o ViewHolder criado por
    onCreateViewHolder e preenche os elementos de UI com os dados do item;
    holder -> objeto do tipo ViewHolder que guarda os itens de interface criados na
    execução de onCreateViewHolder;
●   position -> indica qual elemento da lista deve ser usado para preencher o item.*/
    @Override
    public  void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // obtemos o item que será usado para preencher a UI
        MyItem myItem = itens.get(position);

        //obtemos o objeto do tipo View que está guardado dentro do ViewHolder
        View v = holder.itemView;

        //preenchendo a UI
        ImageView imvphoto = v.findViewById(R.id.imvPhoto);
        imvphoto.setImageBitmap(myItem.photo);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvDesc = v.findViewById(R.id.tvDesc);
        tvDesc.setText(myItem.description);
    }

    //getItemCount -> informa quantos elementos a lista possui;
    @Override
    public  int getItemCount(){
        return itens.size();
    }
}
