package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;

public interface NegocioServicio {

    void crearNegocio();

    void actualizarNegocio();

    void eliminarNegocio(String idNegocio);

    void buscarNegocios();

    void filtrarPorEstado();

    void listarNegociosPropietario();

    void cambiarEstado();

    void registrarRevision();
}
