package br.edu.ifam.ifquimical.helper;

import java.util.List;

import br.edu.ifam.ifquimical.model.QuimicalInformation;

public interface IQuimicalInformationDAO {

    boolean save(QuimicalInformation qi);

    /**
     * Talvez este método não precise ser chamado.
     *
     * @param qi
     * @return
     */
    boolean delete(QuimicalInformation qi);

    /**
     * Talvez este método não precise ser chamado.
     *
     * @param qi
     * @return
     */
    boolean update(QuimicalInformation qi);

    List<QuimicalInformation> list();
}
