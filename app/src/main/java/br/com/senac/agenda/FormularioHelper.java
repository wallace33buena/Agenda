package br.com.senac.agenda;

import android.widget.EditText;

import br.com.senac.agenda.modelo.Agenda;

public class FormularioHelper {
    private final EditText camponome;
    private final EditText campodata;
    private final EditText campohorario;
    private Agenda agenda;
// classe auxiliar

    public FormularioHelper(MainActivity Activity) {

        camponome = Activity.findViewById(R.id.nome);
        campodata = Activity.findViewById(R.id.data);
        campohorario = Activity.findViewById(R.id.horario);
        agenda = new Agenda();
    }

    public Agenda getAgenda(){

        agenda.setNome(camponome.getText().toString());
        agenda.setData(campodata.getText().toString());
        agenda.setHorario(campohorario.getText().toString());


        return agenda;
    }

    public void alterform(Agenda agenda) {
        camponome.setText(agenda.getNome());
        campodata.setText(agenda.getData());
        campohorario.setText(agenda.getHorario());
        this.agenda = agenda;
    }
}
