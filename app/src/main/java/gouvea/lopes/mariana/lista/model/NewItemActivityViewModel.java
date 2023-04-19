package gouvea.lopes.mariana.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

//ViewModel que irá guardar os dados referentes a NewItemActivity;
public class NewItemActivityViewModel extends ViewModel {
    //guardando o endereço URI da foto escolhida
    Uri selelectPhotoLocation = null;

    //metodo para obter a lista de itens
    public Uri getSelelectPhotoLocation(){
        return selelectPhotoLocation;
    }

    //metodo para setar o endereço URI dentro do ViewModel
    public void setSelelectPhotoLocation(Uri selectPhotoLocation){
        this.selelectPhotoLocation = selectPhotoLocation;
    }
}
