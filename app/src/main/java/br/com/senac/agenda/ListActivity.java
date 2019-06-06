package br.com.senac.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.senac.agenda.dao.AgendaDAO;
import br.com.senac.agenda.modelo.Agenda;

public class ListActivity extends AppCompatActivity {
    private ListView listaagenda;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        AgendaDAO dao = new AgendaDAO(this);
        List<Agenda> agenda = dao.listaagenda();

        listaagenda = (ListView) findViewById(R.id.listaagenda);

        listaagenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Agenda agenda = (Agenda) listaagenda.getItemAtPosition(position);
                Intent intentlk = new Intent(ListActivity.this,MainActivity.class);
                intentlk.putExtra("agenda", agenda);
                startActivity(intentlk);
            }
        });
        ArrayAdapter<Agenda> adapter = new ArrayAdapter<Agenda>(this,android.R.layout.simple_list_item_activated_1, agenda );
        listaagenda.setAdapter(adapter);

        Button novoagenda = findViewById (R.id.botaonovo);
        novoagenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlinkFormulario = new Intent(ListActivity.this,MainActivity.class );
                startActivity(intentlinkFormulario);
            }
        });
        registerForContextMenu(listaagenda);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo)menuInfo;
                Agenda agenda = (Agenda) listaagenda.getItemAtPosition(info.position);
                Toast.makeText(ListActivity.this, "DELETADO" + agenda.getNome(), Toast.LENGTH_LONG).show();

                AgendaDAO dao = new AgendaDAO(ListActivity.this);
                dao.deleta(agenda);
                dao.close();

                carregarLista();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
    private  void  carregarLista(){
        AgendaDAO dao = new AgendaDAO(this);
        List<Agenda> agendas = dao.listaagenda();
        dao.close();
        ArrayAdapter<Agenda> adapter = new ArrayAdapter<Agenda>(this,android.R.layout.simple_list_item_activated_1, agendas );
        listaagenda.setAdapter(adapter);
    }
}
