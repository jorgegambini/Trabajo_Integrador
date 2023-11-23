package ar.com.jg.services;

import ar.com.jg.model.OperadorMesaAyuda;
import ar.com.jg.model.Tecnico;
import ar.com.jg.repositories.OperadorMesaAyudaRepository;
import ar.com.jg.repositories.TecnicoRepository;
import ar.com.jg.repositories.CrudRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class TecnicoServiceImpl implements TecnicoService{

    private EntityManager em;
    private CrudRepository<Tecnico> tr;

    public TecnicoServiceImpl(EntityManager em) {

        this.em = em;
        tr = new TecnicoRepository(em);

    }

    @Override
    public List<Tecnico> listarTecnicos() {

        return tr.listar();

    }

    @Override
    public Optional<Tecnico> buscarTecnicoPorId(Long id) {

        return Optional.ofNullable(tr.buscarPorId(id));

    }

    public Optional<Tecnico> loginTecnico(String user, String pass) {

        return Optional.ofNullable(((TecnicoRepository)tr).login(user, pass));

    }

    @Override
    public void guardarTecnico(Tecnico tecnico) {

        try {

            em.getTransaction().begin();
            tr.guardar(tecnico);
            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace(System.out);

        }

    }

    @Override
    public void eliminarTecnico(long id) {

        try {

            em.getTransaction().begin();
            tr.eliminar(id);
            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace(System.out);

        }

    }
}
