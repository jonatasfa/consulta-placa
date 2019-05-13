package br.com.i2assessoria.consultaplaca.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "log")
public class Log {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_log")
    @SequenceGenerator(name = "sequence_log", sequenceName = "log_id_seq", allocationSize = 1)
    private Long id=null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", referencedColumnName = "ID", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "ID", nullable = false)
    private Usuario usuario;

    @Column(name = "dt_consulta")
    private Date dt_consulta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDt_consulta() {
        return dt_consulta;
    }

    public void setDt_consulta(Date dt_consulta) {
        this.dt_consulta = dt_consulta;
    }
}
