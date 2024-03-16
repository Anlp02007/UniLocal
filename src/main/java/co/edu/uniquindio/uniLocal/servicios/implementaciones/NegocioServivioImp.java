package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.servicios.interfaces.NegocioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class NegocioServivioImp implements NegocioServicio {
    @Override
    public void crearNegocio() {

    }

    @Override
    public void actualizarNegocio() {

    }

    @Override
    public void eliminarNegocio(String idNegocio) {

    }

    @Override
    public void buscarNegocios() {

    }

    @Override
    public void filtrarPorEstado() {

    }

    @Override
    public void listarNegociosPropietario() {

    }

    @Override
    public void cambiarEstado() {

    }

    @Override
    public void registrarRevision() {

    }
}
