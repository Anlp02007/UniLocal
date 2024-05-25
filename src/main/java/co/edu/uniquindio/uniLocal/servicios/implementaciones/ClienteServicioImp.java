package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.dto.CambioPasswordDTO;
import co.edu.uniquindio.uniLocal.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.uniLocal.dto.ClienteDTO.FavoritosClienteDTO;
import co.edu.uniquindio.uniLocal.dto.ClienteDTO.ItemClienteDTO;
import co.edu.uniquindio.uniLocal.dto.DetalleClienteDTO;
import co.edu.uniquindio.uniLocal.dto.EmailDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.NegocioGetDTO;
import co.edu.uniquindio.uniLocal.dto.SesionDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.modelo.entidades.Horario;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoNegocio;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.repositorios.ClienteRepo;
import co.edu.uniquindio.uniLocal.repositorios.NegocioRepo;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.uniLocal.servicios.interfaces.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class ClienteServicioImp implements ClienteServicio {
   private final EmailServicio emailServicio ;

    private final ClienteRepo clienteRepo;

    private final NegocioRepo negocioRepo;




    private boolean existeNickname(String nickname) {

        Cliente clienten = clienteRepo.buscarPorNickname(nickname);
        return clienten != null;
    }

    private boolean existeEmail(String email) {

      Cliente clienten = clienteRepo.buscarPorEmail(email);
       return clienten != null;
    }


    @Override
    public void editarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception {

        Optional<Cliente> clienteOptional = clienteRepo.findById(actualizarClienteDTO.id());

        if (clienteOptional.isEmpty()){
            throw new Exception("Ecliente no esta registrado");

        }
        if(clienteOptional.get().getEstadoRegistro() == EstadoRegistro.INACTIVO)
            throw new Exception("El cliente esta inactivo");



        Cliente cliente = clienteOptional.get();
        cliente.setNombre(actualizarClienteDTO.nombre());
        cliente.setCiudad(actualizarClienteDTO.ciudadResidencia());
        cliente.setEmail(actualizarClienteDTO.email());
        cliente.setFotoPerfil(actualizarClienteDTO.fotoPerfil());

        clienteRepo.save(cliente);



    }

    @Override
    public DetalleClienteDTO getCliente(String codiString) throws Exception {
        Optional<Cliente> cl = clienteRepo.findById(codiString);
        return DetalleClienteDTO.builder()
                .codigoCliente(cl.get().getCodigoCliente())
                .ciudad(cl.get().getCiudad())
                .nombre(cl.get().getNombre())
                .nickname(cl.get().getNickname())
                .fotoPerfil(cl.get().getFotoPerfil())
                .email(cl.get().getEmail())
                .build();
    }

    @Override
    public List<ItemClienteDTO> findAllClients() throws Exception{
        return clienteRepo.findAll()
                .stream()
                .filter(cliente -> EstadoRegistro.ACTIVO.equals(cliente.getEstadoRegistro()))
                .map(ClienteServicioImp::convertClienteEntitytoDTO)
                .collect(Collectors.toList());
    }



    private static ItemClienteDTO convertClienteEntitytoDTO(Cliente cliente){
        return new ItemClienteDTO(
                cliente.getCodigoCliente(),
                cliente.getNombre(),
                cliente.getFotoPerfil(),
                cliente.getNickname(),
                cliente.getCiudad());
    }


    @Override
    public void iniciarSesion(SesionDTO sesionDTO) throws Exception {

    }

    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {
        //Buscar el cliente que se quiere eliminar
        Optional<Cliente> clienteOptional = clienteRepo.findById(idCuenta);

            if (clienteOptional.isEmpty()){
                throw new Exception("Non se encontro el cliente a eliminar");
            }

        if(clienteOptional.get().getEstadoRegistro() == EstadoRegistro.INACTIVO)
            throw new Exception("El cliente ya fue eliminado");

        Cliente cliente = clienteOptional.get();
        cliente.setEstadoRegistro(EstadoRegistro.INACTIVO);

        clienteRepo.save(cliente);
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

        Optional<Cliente> cliente = clienteRepo.findByEmail(email);



        if (cliente.isEmpty()){
            throw  new Exception("El email dado no esta asociado a ningun cliente");
        }

        if(cliente.get().getEstadoRegistro() == EstadoRegistro.INACTIVO)
            throw new Exception("El cliente no esta activo");

        emailServicio.enviarCorreo(new EmailDTO(
                "Recuperación de contraseña",
                "Ingrese a la siguiente ruta para cambiar su contraseña: XXXXXX?id="+cliente.get().getCodigoCliente(),
                email
        ));
    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {

    }


    public DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception{

        Optional<Cliente>optionalCliente = clienteRepo.findById(idCuenta);

        if (optionalCliente.isEmpty()){
            throw new Exception("El id no existe");

        }
        Cliente cliente = optionalCliente.get();

        return new DetalleClienteDTO(

                cliente.getCodigoCliente(),
                cliente.getNombre(),
                cliente.getFotoPerfil(),
                cliente.getNickname(),
                cliente.getEmail(),
                cliente.getCiudad()
        );

    }

    public List<ItemClienteDTO> ListarClientes(){

        List<Cliente> clientes = clienteRepo.findAll();

        return (List<ItemClienteDTO>) clientes.stream()
                .map(cliente ->
                new ItemClienteDTO(
                        cliente.getCodigoCliente(),
                        cliente.getNombre(),
                        cliente.getFotoPerfil(),
                        cliente.getNickname(),
                        cliente.getCiudad())
        ).toList();
    }

    public List<ItemClienteDTO> listarClientesActivos(){

        List<Cliente> clientes = clienteRepo.findByEstado(EstadoRegistro.ACTIVO);
        return (List<ItemClienteDTO>) clientes.stream().map(cliente ->
                new ItemClienteDTO(
                        cliente.getCodigoCliente(),
                        cliente.getNombre(),
                        cliente.getFotoPerfil(),
                        cliente.getNickname(),
                        cliente.getCiudad())
        ).toList();
    }

    public List<ItemClienteDTO> listarClientesInactivos(){

        List<Cliente> clientes = clienteRepo.findByEstado(EstadoRegistro.INACTIVO);
        return (List<ItemClienteDTO>) clientes.stream().map(cliente ->
                new ItemClienteDTO(
                        cliente.getCodigoCliente(),
                        cliente.getNombre(),
                        cliente.getFotoPerfil(),
                        cliente.getNickname(),
                        cliente.getCiudad())
        ).toList();
    }

    @Override
    public void agregarNegocioToFavoritos(FavoritosClienteDTO favoritoDTO) throws Exception{

        Cliente cliente = clienteRepo.findyById(favoritoDTO.codigoCliente());
        Negocio negocio = negocioRepo.findByCodigoNegocio(favoritoDTO.codigoNegocio());

        if(cliente == null)
            throw new Exception("Este cliente no esta registrado");

        if(cliente.getEstadoRegistro() == EstadoRegistro.INACTIVO)
            throw new Exception("El cliente no esta activo");

        if(negocio == null)
            throw new Exception("Este negocio no esta registrado");

        if(negocio.getEstadoRegistros() == EstadoRegistro.INACTIVO)
            throw new Exception("El negocio esta inactivo");

        if(negocio.getEstadoNegocio() != EstadoNegocio.APROBADO)
            throw new Exception("El negocio debe estar aprobado");

        if(cliente.getFavoritos() == null)
            cliente.setFavoritos(new ArrayList<>());

        for (Negocio neg : cliente.getFavoritos()) {
            if (neg.getCodigoNegocio().equalsIgnoreCase(favoritoDTO.codigoNegocio())) {
                throw new Exception("Este negocio ya fue agregado");
            }
        }
        cliente.getFavoritos().add(negocio);
        clienteRepo.save(cliente);
    }

    @Override
    public List<NegocioGetDTO> listarFavoritos(String idCliente) throws Exception{

        Cliente cliente = clienteRepo.findyById(idCliente);

        if(cliente == null)
            throw new Exception("Este cliente no esta registrado");

        if(cliente.getEstadoRegistro() == EstadoRegistro.INACTIVO)
            throw new Exception("El cliente no esta activo");

        if(cliente.getFavoritos().isEmpty())
            throw new Exception("No tiene negocios en la lista de favoritos");

        return (List<NegocioGetDTO>) cliente.getFavoritos().stream()
                .filter(negocio -> EstadoRegistro.ACTIVO.equals(negocio.getEstadoRegistros()))
                .map(negocio ->

                new NegocioGetDTO(
                        negocio.getCodigoNegocio(),
                        negocio.getNombre(),
                        negocio.getUbicacion(),
                        negocio.getHorario(),
                        negocio.getImagen(),
                        negocio.getDescripcion(),
                        negocio.getTipoNegocio(),
                        negocio.getTelefono(),
                        negocio.getEstadoRegistros(),
                        negocio.getEstadoNegocio(),
                        verificarSiEstaAbierto(negocio.getHorario())
                )
        ).toList();

    }

    @Override
    public String eliminarNegocioFavoritos(FavoritosClienteDTO favoritosDTO) throws Exception {

        Cliente cliente = clienteRepo.findyById(favoritosDTO.codigoCliente());
        boolean eliminado = false;

        if(cliente == null)
            throw new Exception("Este cliente no esta registrado");

        if(cliente.getEstadoRegistro() == EstadoRegistro.INACTIVO)
            throw new Exception("El cliente no esta activo");

        for (Negocio negocio : cliente.getFavoritos()) {
            if (negocio.getCodigoNegocio().equalsIgnoreCase(favoritosDTO.codigoNegocio())) {
                cliente.getFavoritos().remove(negocio);
                eliminado = true;
                break;
            }
        }

        if(eliminado){
            clienteRepo.save(cliente);
            return favoritosDTO.codigoNegocio();
        }

        throw new Exception("El negocio no esta en la lista");
    }


    @Override
    public void agregarNegocioToRecomendaciones(String idCliente, String idNegocio) throws Exception{

        Cliente cliente = clienteRepo.findyById(idCliente);
        Negocio negocio = negocioRepo.findByCodigoNegocio(idNegocio);

        if(cliente == null)
           return;

        if(cliente.getEstadoRegistro() == EstadoRegistro.INACTIVO)
            return;

        if(negocio.getEstadoRegistros() == EstadoRegistro.INACTIVO)
            return;
        if(negocio.getEstadoNegocio() != EstadoNegocio.APROBADO)
            return;


        if(cliente.getRecomendaciones() == null)
            cliente.setRecomendaciones(new ArrayList<>());



        for (Negocio neg : cliente.getRecomendaciones()) {
            if (neg.getCodigoNegocio().equalsIgnoreCase(negocio.getCodigoNegocio())) {
                return;
            }
        }
        cliente.getRecomendaciones().add(negocio);
        clienteRepo.save(cliente);
    }


    @Override
    public List<NegocioGetDTO> listarRecomendaciones(String idCliente) throws Exception{

        Cliente cliente = clienteRepo.findyById(idCliente);

        if(cliente == null)
            throw new Exception("Este cliente no esta registrado");

        if(cliente.getEstadoRegistro() == EstadoRegistro.INACTIVO)
            throw new Exception("Este cliente esta inactivo");


        if(cliente.getRecomendaciones() == null || cliente.getRecomendaciones().isEmpty())
            throw new Exception("No tiene negocios en la lista de recomendaciones");

        return (List<NegocioGetDTO>) cliente.getRecomendaciones().stream()
                .filter(negocio -> EstadoRegistro.ACTIVO.equals(negocio.getEstadoRegistros())).
                map(negocio ->
                new NegocioGetDTO(
                        negocio.getCodigoNegocio(),
                        negocio.getNombre(),
                        negocio.getUbicacion(),
                        negocio.getHorario(),
                        negocio.getImagen(),
                        negocio.getDescripcion(),
                        negocio.getTipoNegocio(),
                        negocio.getTelefono(),
                        negocio.getEstadoRegistros(),
                        negocio.getEstadoNegocio(),
                        verificarSiEstaAbierto(negocio.getHorario())
                )
        ).toList();

    }

    @Override
    public String eliminarNegocioRecomendaciones(String idCliente, String idNegocio) throws Exception {

        Cliente cliente = clienteRepo.findyById(idCliente);
        Negocio negocio = negocioRepo.findByCodigoNegocio(idNegocio);
        boolean eliminado = false;

        if(cliente == null)
            throw new Exception("Este cliente no esta registrado");

        if(cliente.getEstadoRegistro() == EstadoRegistro.INACTIVO)
            throw new Exception("Este cliente esta inactivo");

        if(negocio.getEstadoRegistros() == EstadoRegistro.INACTIVO)
            throw new Exception("Este negocio esta inactivo");



        for (Negocio neg : cliente.getRecomendaciones()) {
            if (neg.getCodigoNegocio().equalsIgnoreCase(negocio.getCodigoNegocio())) {
                cliente.getRecomendaciones().remove(neg);
                eliminado = true;
                break;
            }
        }

        if(eliminado){
            clienteRepo.save(cliente);
            return negocio.getCodigoNegocio();
        }

        throw new Exception("El negocio no esta en la lista");
    }

    public boolean verificarSiEstaAbierto(List<Horario> horarios){

        boolean abierto = false;
        LocalTime horaActual = LocalTime.now();
        int diaActual = LocalDate.now().getDayOfWeek().getValue();

        for (Horario horario: horarios){
            int diaHorario = Integer.parseInt(horario.getDia());
            if(diaHorario == diaActual ){
                if( horaActual.isAfter(horario.getHoraInicio()) && horaActual.isBefore(horario.getHoraFin()))
                {
                    abierto = true;
                    break;
                }
            }
        }
        return abierto;
    }
}
