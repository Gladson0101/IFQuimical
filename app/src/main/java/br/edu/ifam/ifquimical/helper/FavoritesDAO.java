package br.edu.ifam.ifquimical.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifam.ifquimical.model.QuimicalInformation;

public class FavoritesDAO implements IFavoritesDAO {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public FavoritesDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        write = dbHelper.getWritableDatabase();
        read = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean save(QuimicalInformation qi) {
        ContentValues cv = new ContentValues();
        cv.put("name", qi.getName());
        cv.put("formula", qi.getFormula());
        cv.put("firstAidActions", qi.getFirstAidActions());
        cv.put("fireSafety", qi.getFireSafety());
        cv.put("handlingAndStorage", qi.getHandlingAndStorage());
        cv.put("exposureControlAndPersonalProtection", qi.getExposureControlAndPersonalProtection());
        cv.put("spillOrLeak", qi.getSpillOrLeak());
        cv.put("stabilityAndReactivity", qi.getStabilityAndReactivity());

        try {
            write.insert(DBHelper.TABLE_FAVORITES, null, cv);
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    @Override
    public boolean delete(QuimicalInformation qi) {
        try {
            String[] args = {qi.getName()};
            write.delete(DBHelper.TABLE_FAVORITES, "name=?", args);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public List<QuimicalInformation> list() {
        List<QuimicalInformation> qiList = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TABLE_FAVORITES + " ;";
        Cursor c = read.rawQuery(sql, null);

        while (c.moveToNext()) {
            QuimicalInformation qi = new QuimicalInformation();

            // Definição de variáveis.
            String name = c.getString(c.getColumnIndex("name"));
            String formula = c.getString(c.getColumnIndex("formula"));
            String firstAidActions = c.getString(c.getColumnIndex("firstAidActions"));
            String fireSafety = c.getString(c.getColumnIndex("fireSafety"));
            String handlingAndStorage = c.getString(c.getColumnIndex("handlingAndStorage"));
            String exposureControlAndPersonalProtection = c.getString(c.getColumnIndex("exposureControlAndPersonalProtection"));
            String spillOrLeak = c.getString(c.getColumnIndex("spillOrLeak"));
            String stabilityAndReactivity = c.getString(c.getColumnIndex("stabilityAndReactivity"));

            // Set para as variáveis definidas.
            qi.setName(name);
            qi.setFormula(formula);
            qi.setFirstAidActions(firstAidActions);
            qi.setFireSafety(fireSafety);
            qi.setHandlingAndStorage(handlingAndStorage);
            qi.setExposureControlAndPersonalProtection(exposureControlAndPersonalProtection);
            qi.setSpillOrLeak(spillOrLeak);
            qi.setStabilityAndReactivity(stabilityAndReactivity);

            // Adiciona à lista.
            qiList.add(qi);
        }

        c.close();

        return qiList;
    }

    @Override
    public boolean update(QuimicalInformation qi) {
        return false;
    }
}
