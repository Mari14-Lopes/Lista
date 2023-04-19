package gouvea.lopes.mariana.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

//ViewModel que irá guardar os dados referentes a MainActivity;
public class MainActivityViewModel extends ViewModel {
    //lista de itens cadastrados
    List<MyItem> itens = new ArrayList<>();

    //metodo que obetem a lista de itens
    public List<MyItem> getItens(){
        return itens;
    }
}
