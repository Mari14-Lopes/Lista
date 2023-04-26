package gouvea.lopes.mariana.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import gouvea.lopes.mariana.lista.R;
import gouvea.lopes.mariana.lista.adapter.MyAdapter;
import gouvea.lopes.mariana.lista.model.MainActivityViewModel;
import gouvea.lopes.mariana.lista.model.MyItem;
import gouvea.lopes.mariana.lista.model.Util;

public class MainActivity extends AppCompatActivity {
    //criando novo atributo
    static int NEW_ITEM_REQUEST = 1;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtendo botao(+)
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        //ouvidor de cliques
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            //metodo para abrir a nova pagina
            @Override
            public void onClick(View view) {
                //criacao de Intent explícito
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                /*executando intent
                NEW_ITEM_REQUEST -> número inteiro que representa o identificar da chamada*/
                startActivityForResult(i, NEW_ITEM_REQUEST);

            }
        });

        // obtemos o RecycleView
        RecyclerView rvItens = findViewById(R.id.rvItens);

        //ViewModel referente a MainActivity é obtido
        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        //Lista de itens é obtida a partir do ViewModel e repassada para o Adapter
        List<MyItem> itens = vm.getItens();

        //criado o MyAdapter e ele é setado no RecycleView
        myAdapter = new MyAdapter(this,itens);
        rvItens.setAdapter(myAdapter);

        //setHasFixedSize indica ao RecycleView que não há variação de tamanho entre os itens da lista
        rvItens.setHasFixedSize(true);

        //criamos um gerenciador de layout do tipo linear e o setamos no RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

        //criamos um decorador para a lista, que consiste apenas em uma linha separando cada
        //item e o setamos no RecycleView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verificamos se as condições de retorno foram cumpridas
        if(requestCode == NEW_ITEM_REQUEST) {
            if(resultCode == Activity.RESULT_OK) {
                //obtemos os dados retornados por NewItemActivity e os guardamos dentro de myItem
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                Uri selectedPhotoURI = data.getData();

                try{
                    //Essa função carrega a imagem e a guarda dentro de um Bitmap, criando assim uma copia da imagem
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoURI, 100, 100);
                    //Guardando o Bitmap da imagem dentro de um objeto do tipo MyItem
                    myItem.photo = photo;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                //ViewModel referente a MainActivity é obtido
                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
                //Lista de itens é obtida a partir do ViewModel e repassada para o Adapter
                List<MyItem> itens  = vm.getItens();

                //adicionamos o item a uma lista de itens
                itens.add(myItem);
                //notificando o  adapter para que o novo item seja mostrado no RecycleView
                myAdapter.notifyItemInserted(itens.size()-1);
                }
            }
        }
}