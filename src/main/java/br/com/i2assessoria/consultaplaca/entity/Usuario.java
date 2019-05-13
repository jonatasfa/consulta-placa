package br.com.i2assessoria.consultaplaca.entity;

import br.com.i2assessoria.consultaplaca.utils.BooleanSimNaoConverter;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_usuario")
    @SequenceGenerator(name = "sequence_usuario", sequenceName = "usuario_id_seq", allocationSize = 1)
    private Long id=null;

    @Column(name = "login", length = 20, nullable = false)
    private String login = null;

    @Column(name = "senha", length = 20, nullable = false)
    private String senha = null;

    @Column(name = "admin", nullable = false)
    @Convert(converter = BooleanSimNaoConverter.class)
    private boolean admin = false;

    @Column(name = "email", nullable = false)
    private String email = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
