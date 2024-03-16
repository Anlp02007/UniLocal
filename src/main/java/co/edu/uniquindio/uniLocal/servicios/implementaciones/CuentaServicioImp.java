package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.dto.CambioPasswordDTO;
import co.edu.uniquindio.uniLocal.dto.SesionDTO;
import co.edu.uniquindio.uniLocal.servicios.interfaces.CuentaServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service

public class CuentaServicioImp implements CuentaServicio {
    @Override
    public void iniciarSesion(SesionDTO sesionDTO) throws Exception {

    }

    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {

    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {

    }
}
