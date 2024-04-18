package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.HistorialRevisionDTO.HistorialRevisionDTO;
import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.modelo.entidades.HistoriaRevicion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoNegocio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class ModeradorServicioTest {

    @Autowired
    private ModeradorServicio moderadorServicio;

    @Test
    void revisionNegocioAprobadoSucces() throws Exception{
        HistoriaRevicion historiaRevicion = new HistoriaRevicion(
                LocalDate.now(),
                "Se Realiza la revision al negocio Moderador_1",
                EstadoNegocio.APROBADO,
                "Moderador_1"
        );
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO("Negocio2",
                null,null,null,null,null
        ,null,null,null);

        HistoriaRevicion historiaRevicion1 = moderadorServicio.revisionNegocio(itemNegocioDTO,historiaRevicion);
        Assertions.assertEquals(historiaRevicion1.getCodigoModerador(),"Moderador_1");
        Assertions.assertEquals(historiaRevicion1.getEstado().name(),"APROBADO");
    }

    @Test
    void revisionNegocioRechazadoSucces() throws Exception{
        HistoriaRevicion historiaRevicion = new HistoriaRevicion(
                LocalDate.now(),
                "Se Realiza la revision al negocio _negocio4",
                EstadoNegocio.RECHAZADO,
                "Moderador_1"
        );
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO("Negocio5",
                null,null,null,null,null
                ,null,null,null);

        HistoriaRevicion historiaRevicion1 = moderadorServicio.revisionNegocio(itemNegocioDTO,historiaRevicion);
        Assertions.assertEquals(historiaRevicion1.getCodigoModerador(),"Moderador_1");
        Assertions.assertEquals(historiaRevicion1.getEstado().name(),"RECHAZADO");
    }

    @Test
    void revisionNegocioPendienteSucces() throws Exception{
        HistoriaRevicion historiaRevicion = new HistoriaRevicion(
                LocalDate.now(),
                "Se Realiza la revision al negocio Negocio4",
                EstadoNegocio.PENDIENTE,
                "Moderador_1"
        );
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO("Negocio4",
                null,null,null,null,null
                ,null,null,null);

        HistoriaRevicion historiaRevicion1 = moderadorServicio.revisionNegocio(itemNegocioDTO,historiaRevicion);
        Assertions.assertEquals(historiaRevicion1.getCodigoModerador(),"Moderador_1");
        Assertions.assertEquals(historiaRevicion1.getEstado().name(),"PENDIENTE");
    }

    @Test
    void eliminarCuentaTest() throws Exception{

        String moderador = "Moderador_1";
        moderadorServicio.eliminarCuenta(moderador);
    }

    @Test
    void enviarRecuperacionTest() throws Exception{

        String email = "Jul@gmail.com";
        moderadorServicio.enviarLinkRecuperacion(email);
    }

    @Test
    void hacerRevisionTest() throws Exception{

        HistorialRevisionDTO historialRevisionDTO = new HistorialRevisionDTO(
                LocalDate.now(),
                "Esta mal",
                EstadoNegocio.RECHAZADO,
                "Moderador_1",
                "Negocio4"
        );
        moderadorServicio.registrarRevision(historialRevisionDTO);
    }
}
