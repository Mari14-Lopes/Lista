package gouvea.lopes.mariana.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import gouvea.lopes.mariana.lista.R;
import gouvea.lopes.mariana.lista.model.NewItemActivityViewModel;

//escolher e obter imagem da galeria
public class NewItemActivity extends AppCompatActivity {
    static int PHOTO_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);

        //obtendo o endereço URI guardado dentro do ViewModel
        Uri selectPhotoLocation = vm.getSelelectPhotoLocation();
        //se o usuario tiver escolhido uma imagem antes de roacionar a tela
        if(selectPhotoLocation != null){
            //setando a ImageView e quardando dentro do ViewModel p endereco URI da imagem escolhida
            ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
            imvfotoPreview.setImageURI(selectPhotoLocation);
        }

        //botao de adicionar imagem
        ImageButton imgCI = findViewById(R.id.imgCI);
        //ouvidor de cliques
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //criacao da intent implicito do tipo ACTION_OPEN_DOCUMENT (abrir documento)
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                /*setando no intent que estamos interessados apenas em documentos
                com mimetype “image/*” (qualquer tipo de imagem)*/
                photoPickerIntent.setType("image/*");
                //executando intent
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        //botao de adicionar adicionar item
        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri selectPhotoLocation = vm.getSelelectPhotoLocation();
                //verificamos se os campos foram preenchidos pelo usuário
                if(selectPhotoLocation == null){
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if(title.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if(description.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                /*Esse Intent serve unicamente para guardar
                os dados a serem retornados para MainActivity*/
                Intent i = new Intent();
                //Uri da imagem
                i.setData(selectPhotoLocation);
                i.putExtra("title", title);
                i.putExtra("description", description);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }


    /*
    requestCode -> inteiro que indica a qual chamada de startActivityForResult essa resposta se refere;
    resultCode -> código que indica se a Activity de destino retornou com sucesso ou não;
    data -> Intent que contém os dados retornados pela Activity de destino.
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verificamos se requestCode é referente ao fornecido na chamada de startActiviyForResult com id PHOTO_PICKER_REQUEST
        if(requestCode == PHOTO_PICKER_REQUEST) {
            //verificamos se resultCode é um código de sucesso
            if(resultCode == Activity.RESULT_OK) {
                Uri photoSelected = data.getData();
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                imvfotoPreview.setImageURI(photoSelected);

                //obtendo o ViewModel
                NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);
                //guardando dentro do ViewModel o endereço URI da imagem escolhida
                vm.setSelelectPhotoLocation(photoSelected);

                }
            }

    }
}