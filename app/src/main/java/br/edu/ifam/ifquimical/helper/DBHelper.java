package br.edu.ifam.ifquimical.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String DB_NAME = "db_ifquimical";
    public static String TABLE_QUIMICAL_INFORMATION = "quimical_information";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Script SQL.
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUIMICAL_INFORMATION +
                " (name TEXT NOT NULL PRIMARY KEY)," +
                " (formula TEXT NOT NULL)," +
                " (firstAidActions TEXT NOT NULL)," +
                " (fireSafety TEXT NOT NULL)," +
                " (handlingAndStorage TEXT NOT NULL)," +
                " (exposureControlAndPersonalProtection TEXT NOT NULL)," +
                " (spillOrLeak TEXT NOT NULL)," +
                " (stabilityAndReactivity TEXT NOT NULL)";

        // Executa o script SQL.
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            Log.i("INFO BD", "Falha na criação da Tabela do Banco de Dados");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
