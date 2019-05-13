package br.com.i2assessoria.consultaplaca.service;

import br.com.i2assessoria.consultaplaca.entity.Usuario;
import br.com.i2assessoria.consultaplaca.repository.UsuarioRepository;
import br.com.i2assessoria.consultaplaca.utils.SenhaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, EmailService emailService){
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
    }

    public Usuario save(Usuario usuario){

        String senha = SenhaUtil.gerarSenha();
        usuario.setSenha(SenhaUtil.criptografar(usuario.getLogin() + senha));

        emailService.sendEmailSenha(senha, usuario.getEmail());

        return this.usuarioRepository.save(usuario);
    }

    public Usuario findByLogin(String login){
        return usuarioRepository.findByLogin(login);
    }

    public void restartPassword(Long id){

        Usuario usuario = usuarioRepository.findById(id).get();

        if (usuario != null){
            String senha = SenhaUtil.gerarSenha();
            usuario.setSenha(SenhaUtil.criptografar(usuario.getLogin() + senha));
            this.usuarioRepository.save(usuario);

            emailService.sendEmailSenha(senha, usuario.getEmail());
        }

    }

    public Usuario getUsuarioLogado(){
        String login = getUsuarioLogadoSession();

        Usuario usuarioLogado = null;

        if (login != null){
            usuarioLogado = findByLogin(login);
        }

        return usuarioLogado;
    }

    private String getUsuarioLogadoSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String nome = null;

        if (principal instanceof UserDetails) {
            nome = ((UserDetails)principal).getUsername();
        } else {
            nome = principal.toString();
        }

        return nome;
    }
}
