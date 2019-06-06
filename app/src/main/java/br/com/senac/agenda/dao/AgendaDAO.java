package br.com.senac.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.senac.agenda.modelo.Agenda;

public class AgendaDAO extends SQLiteOpenHelper {
    public AgendaDAO(Context context){
        super(context, "bdagenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tbagenda(id INTEGER PRIMARY KEY,nome TEXT NOT NULL,data TEXT,horario TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tbagenda";
        db.execSQL(sql);
    }
    public void inserirAgenda(Agenda agenda){
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues dados = gtdados(agenda);
        writableDatabase.insert("tbagenda",null,dados);
    }
    public List<Agenda> listaagenda(){
        String sql = "Select * From tbagenda;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Agenda>  agendas = new ArrayList<Agenda>();

        while (c.moveToNext()){
            Agenda agenda = new Agenda();

            agenda.setId(c.getLong(c.getColumnIndex("id")));
            agenda.setNome(c.getString(c.getColumnIndex("nome")));
            agenda.setData(c.getString(c.getColumnIndex("data")));
            agenda.setHorario(c.getString(c.getColumnIndex("horario")));


            agendas.add(agenda);
        }
        c.close();
        return agendas;
    }
    public void deleta (Agenda agenda){
        SQLiteDatabase db = getWritableDatabase();
        String [] params = {agenda.getId().toString()};
        db.delete("tbagenda", "id = ?",params );

    }
    public void altera (Agenda agenda){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = gtdados(agenda);
        String[] params = {agenda.getId().toString()};
        db.update("tbagenda",dados,"id = ?",params);
    }

    private ContentValues gtdados(Agenda agenda) {
        ContentValues dados = new ContentValues();
        dados.put("nome", agenda.getNome());
        dados.put("data",agenda.getData());
        dados.put("horario",agenda.getHorario());
        return dados;
    }
}
