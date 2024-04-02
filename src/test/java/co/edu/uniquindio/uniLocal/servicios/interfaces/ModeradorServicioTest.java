package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.modelo.entidades.HistoriaRevicion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoNegocio;
import co.edu.uniquindio.uniLocal.repositorios.ModeradorRepo;
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
                "Se Realiza la revision al negocio _negocio2",
                EstadoNegocio.APROBADO,
                "_moderador1"
        );
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO("_negocio2",
                null,null,null,null,null
        ,null,null,null);

        HistoriaRevicion historiaRevicion1 = moderadorServicio.revisionNegocio(itemNegocioDTO,historiaRevicion);
        Assertions.assertEquals(historiaRevicion1.getCodigoModerador(),"_moderador1");
        Assertions.assertEquals(historiaRevicion1.getEstado().name(),"APROBADO");
    }

    @Test
    void revisionNegocioRechazadoSucces() throws Exception{
        HistoriaRevicion historiaRevicion = new HistoriaRevicion(
                LocalDate.now(),
                "Se Realiza la revision al negocio _negocio4",
                EstadoNegocio.RECHAZADO,
                "_moderador1"
        );
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO("_negocio4",
                null,null,null,null,null
                ,null,null,null);

        HistoriaRevicion historiaRevicion1 = moderadorServicio.revisionNegocio(itemNegocioDTO,historiaRevicion);
        Assertions.assertEquals(historiaRevicion1.getCodigoModerador(),"_moderador1");
        Assertions.assertEquals(historiaRevicion1.getEstado().name(),"RECHAZADO");
    }

    @Test
    void revisionNegocioPendienteSucces() throws Exception{
        HistoriaRevicion historiaRevicion = new HistoriaRevicion(
                LocalDate.now(),
                "Se Realiza la revision al negocio _negocio4",
                EstadoNegocio.PENDIENTE,
                "_moderador1"
        );
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO("_negocio4",
                null,null,null,null,null
                ,null,null,null);

        HistoriaRevicion historiaRevicion1 = moderadorServicio.revisionNegocio(itemNegocioDTO,historiaRevicion);
        Assertions.assertEquals(historiaRevicion1.getCodigoModerador(),"_moderador1");
        Assertions.assertEquals(historiaRevicion1.getEstado().name(),"PENDIENTE");
    }
}
