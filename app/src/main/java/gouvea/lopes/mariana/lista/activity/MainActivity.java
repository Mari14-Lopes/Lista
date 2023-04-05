package gouvea.lopes.mariana.lista.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import gouvea.lopes.mariana.lista.R;
import gouvea.lopes.mariana.lista.model.MyItem;

public class MainActivity extends AppCompatActivity {
    //criando novo atributo
    static int NEW_ITEM_REQUEST = 1;
    List<MyItem> itens = new ArrayList<>();
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

        @Override
        protected  void onCreate(Bundle savedInstanceState){
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_ITEM_REQUEST) {
            if(resultCode == Activity.RESULT_OK) {
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                myItem.photo = data.getData();

                itens.add(myItem);
                }
            }
        }
}