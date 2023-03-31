package gouvea.lopes.mariana.lista.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import gouvea.lopes.mariana.lista.R;
//escolher e obter imagem da galeria
public class NewItemActivity extends AppCompatActivity {
    static int PHOTO_PICKER_REQUEST = 1;
    //photoSelected guardará o endereço da foto selecionada pelo usuário, e não a foto em si
    Uri photoSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
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
    }

    /*
    requestCode -> inteiro que indica a qual chamada de startActivityForResult essa resposta se refere;
    resultCode -> código que indica se a Activity de destino retornou com sucesso ou não;
    data -> Intent que contém os dados retornados pela Activity de destino
    */
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verificamos se requestCode é referente ao fornecido na chamada de startActiviyForResult com id PHOTO_PICKER_REQUEST
        if(requestCode == PHOTO_PICKER_REQUEST) {
            //verificamos se resultCode é um código de sucesso
            if(resultCode == Activity.RESULT_OK) {
                photoSelected = data.getData();
                //obtemos o Uri da imagem escolhida e guardamos dentro do atributo de classe photoSelected
                ImageView imvfotoPreview =
                        findViewById(R.id.imvfotoPreview);
                //obtemos o ImageView e setamos o Uri nele para que a foto seja exibida em nossa app
                imvfotoPreview.setImageURI(photoSelected);

                }
            }

    }
}