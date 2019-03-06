package br.edu.ifam.ifquimical.helper;

import java.util.List;

import br.edu.ifam.ifquimical.model.QuimicalInformation;

public interface IHistoricDAO {

    boolean save(QuimicalInformation qi);
    boolean delete(QuimicalInformation qi);
    boolean update(QuimicalInformation qi);
    List<QuimicalInformation> list();
}
