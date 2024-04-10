package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.dto.*;
import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.repositorios.ClienteRepo;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.uniLocal.servicios.interfaces.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class ClienteServicioImp implements ClienteServicio {
   private final EmailServicio emailServicio ;

    private final ClienteRepo clienteRepo;


    @Override
    public String registrarse(RegistroClienteDTO registroClienteDTO) throws Exception {

        if(existeEmail(registroClienteDTO.email())){
            throw new Exception("El email ya esta en uso por otra persona");
        }

        if(existeNickname(registroClienteDTO.nickname())){
            throw new Exception("El nickname ya ests en uso por otra persona ");
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(registroClienteDTO.nombre());
        cliente.setEmail(registroClienteDTO.email());
        cliente.setFotoPerfil(registroClienteDTO.fotoPerfil());
        cliente.setNickname(registroClienteDTO.nickname());
        cliente.setPassword(registroClienteDTO.password());
        cliente.setCiudad(registroClienteDTO.ciudadResidencia());
        cliente.setEstadoRegistro(EstadoRegistro.ACTIVO);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode( registroClienteDTO.password() );
        cliente.setPassword( passwordEncriptada );

        Cliente clienteGuardado = clienteRepo.save(cliente);

        return clienteGuardado.getCodigoCliente();


    }

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

        Cliente cliente = clienteRepo.findByEmailAndPassword(sesionDTO.email(),sesionDTO.password());

        if (cliente == null){
            throw new Exception("Cuenta invalida");
        }

    }

    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {
        //Buscar el cliente que se quiere eliminar
        Optional<Cliente> clienteOptional = clienteRepo.findById(idCuenta);

            if (clienteOptional.isEmpty()){
                throw new Exception("Non se encontro el cliente a eliminar");


            }

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

        return (List<ItemClienteDTO>) clientes.stream().map(cliente ->
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
}
