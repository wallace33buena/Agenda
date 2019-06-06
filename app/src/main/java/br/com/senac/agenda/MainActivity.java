package br.com.senac.agenda;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.senac.agenda.dao.AgendaDAO;
import br.com.senac.agenda.modelo.Agenda;

public class MainActivity extends AppCompatActivity {
    private FormularioHelper helper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new FormularioHelper(this);
        Intent intent = getIntent();
        final Agenda agenda = (Agenda) intent.getSerializableExtra("agenda");

        if(agenda != null){
            helper.alterform(agenda);
            Toast.makeText(MainActivity.this, "esta tudo certo ", Toast.LENGTH_LONG).show();
        }

        Button botaosalvar = (Button) findViewById(R.id.botaosalvar);
        botaosalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Agenda agenda = helper.getAgenda();
                AgendaDAO dao = new AgendaDAO(MainActivity.this);
                if (agenda.getId() != null){
                    dao.altera(agenda);
                    Toast.makeText(MainActivity.this, "Alterado " + agenda.getNome(), Toast.LENGTH_LONG).show();
                }else {
                    dao.inserirAgenda(agenda);
                    Toast.makeText(MainActivity.this, "Salvo " + agenda.getNome(), Toast.LENGTH_LONG).show();
                }



                dao.close();

                Toast.makeText(MainActivity.this, "Agenda" + agenda.getNome() + " Salvo", Toast.LENGTH_SHORT).show();





            }
        });
        Button botaolista = (Button) findViewById(R.id.botaolista);
        botaolista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linklista = new Intent(MainActivity.this, ListActivity.class);
                startActivity(linklista);
            }
        });

    }




}

