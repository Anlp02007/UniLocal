package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.uniLocal.dto.EmailDTO;
import co.edu.uniquindio.uniLocal.dto.LoginDTO;
import co.edu.uniquindio.uniLocal.dto.TokenDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.modelo.documento.Moderador;
import co.edu.uniquindio.uniLocal.modelo.enums.Ciudad;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.repositorios.ClienteRepo;
import co.edu.uniquindio.uniLocal.repositorios.ModeradorRepo;
import co.edu.uniquindio.uniLocal.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.uniLocal.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.uniLocal.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor

public class AutenticacionServicioImp implements AutenticacionServicio {

    private final ClienteRepo clienteRepo;
    private final ModeradorRepo moderadorRepo;
    private final EmailServicio emailServicio;
    private final ClienteServicio clienteServicio;
    private final JWTUtils jwtUtils;
    private final ModeradorServicio moderadorServicio;
    @Override
    public TokenDTO iniciarSesionCliente(LoginDTO loginDTO) throws Exception  {


        moderadorServicio.inactivarNegocios();

        Optional<Cliente> clienteOptional = clienteRepo.findByEmail(loginDTO.email());
        if (clienteOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado");
        }

        if(clienteOptional.get().getEstadoRegistro() == EstadoRegistro.INACTIVO)
            throw new Exception("El cliente esta inactivo");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Cliente cliente = clienteOptional.get();
        System.out.println("Login: " + loginDTO.password());
        System.out.println("Moderador: " + cliente.getPassword());
        System.out.println("Comparacion: " + passwordEncoder.matches(loginDTO.password(), cliente.getPassword()));
        if( !passwordEncoder.matches(loginDTO.password(), cliente.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "CLIENTE");
        map.put("nombre", cliente.getNombre());
        map.put("id", cliente.getCodigoCliente());
        return new TokenDTO( jwtUtils.generarToken(cliente.getEmail(), map) );
    }

    public TokenDTO iniciarSesionModerador(LoginDTO loginDTO) throws Exception  {


        moderadorServicio.inactivarNegocios();

        Optional<Moderador> moderadorOptional = moderadorRepo.findByEmail(loginDTO.email());
        if (moderadorOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Moderador moderador = moderadorOptional.get();
        System.out.println("Login: " + loginDTO.password());
        System.out.println("Moderador: " + moderador.getPassword());
        System.out.println("Comparacion: " + passwordEncoder.matches(loginDTO.password(), moderador.getPassword()));
        if( !loginDTO.password().equals(moderador.getPassword()))  {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "MODERADOR");
        map.put("nombre", moderador.getNombre());
        map.put("id", moderador.getCodigoModerador());
        return new TokenDTO( jwtUtils.generarToken(moderador.getEmail(), map) );
    }

    public Boolean ValidateUser(String token, String ClienteDTO) throws Exception{
        Map<String,String> tokendes = jwtUtils.desencriptarToken(token);
        //tokendes.forEach((key, value) -> System.out.println("Clave: " + key + ", Valor: " + value));
        return ClienteDTO.equals(tokendes.get("id"));
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

        Optional<Cliente> cliente = clienteRepo.findByEmail(email);
        String id = "";
        if (cliente.isEmpty()){

            Optional<Moderador> moderador = moderadorRepo.findByEmail(email);

            if(moderador.isEmpty())
                throw  new Exception("El email dado no esta asociado a ningun usuario");

            id = moderador.get().getCodigoModerador();

        }else{
            id = cliente.get().getCodigoCliente();
        }

        emailServicio.enviarCorreo(new EmailDTO(
                "Recuperación de contraseña",
                "Ingrese a la siguiente ruta para cambiar su contraseña: XXXXXX?id="+id,
                email
        ));
    }

    @Override
    public String registrarse(RegistroClienteDTO registroClienteDTO) throws Exception {

        if(existeEmail(registroClienteDTO.email())){
            throw new Exception("El email ya esta en uso por otra persona");
        }

        if(existeNickname(registroClienteDTO.nickname())){
            throw new Exception("El nickname ya esta en uso por otra persona ");
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

    @Override
    public List<Ciudad> listarCiudades() {
        return Arrays.stream(Ciudad.values()).toList();
    }

    private boolean existeNickname(String nickname) {

        Cliente clienten = clienteRepo.buscarPorNickname(nickname);
        return clienten != null;
    }

    private boolean existeEmail(String email) {

        Cliente clienten = clienteRepo.buscarPorEmail(email);
        return clienten != null;
    }

}

