package br.edu.ifam.ifquimical.helper;

import java.util.List;

import br.edu.ifam.ifquimical.model.QuimicalInformation;

public interface IFavoritesDAO {

    boolean save(QuimicalInformation qi);
    boolean delete(QuimicalInformation qi);
    List<QuimicalInformation> list();
    boolean update(QuimicalInformation qi);
}
