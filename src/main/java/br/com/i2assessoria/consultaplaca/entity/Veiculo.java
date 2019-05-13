package br.com.i2assessoria.consultaplaca.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_veiculo")
    @SequenceGenerator(name = "sequence_veiculo", sequenceName = "veiculo_id_seq", allocationSize = 1)
    private Long id=null;

    @Column(name = "placa", length = 7, nullable = false)
    private String placa = null;

    @Column(name = "tipo_perda")
    private String tipoPerda = null;

    @Column(name = "marca")
    private String marca = null;

    @Column(name = "modelo")
    private String modelo = null;

    @Column(name = "modelo_no")
    private String modeloNo = null;

    @Column(name = "ano")
    private String ano = null;

    @Column(name = "cor")
    private String cor = null;

    @Column(name = "data_perda")
    private Date dataPerda = null;

    @Column(name = "loja_perda")
    private String lojaPerda = null;

    @Column(name = "uf_perda")
    private String ufPerda = null;

    @Column(name = "nome")
    private String nome = null;

    @Column(name = "cpf")
    private String cpf = null;

    @Column(name = "telefone_fixo")
    private String telefoneFixo = null;

    @Column(name = "telefone_celular")
    private String telefoneCelular = null;

    @Column(name = "email")
    private String email = null;

    @Column(name = "tipo_logradouro")
    private String tipoLogradouro = null;

    @Column(name = "endereco")
    private String endereco = null;

    @Column(name = "numero")
    private String numero = null;

    @Column(name = "bairro")
    private String bairro = null;

    @Column(name = "cidade")
    private String cidade = null;

    @Column(name = "uf")
    private String uf = null;

    @Column(name = "cep")
    private String cep = null;

    public Veiculo() {}

    public Veiculo(String placa, String tipoPerda, String marca, String modelo, String modeloNo, String ano, String cor, Date dataPerda, String lojaPerda, String ufPerda, String nome, String cpf, String telefoneFixo, String telefoneCelular, String email, String tipoLogradouro, String endereco, String numero, String bairro, String cidade, String uf, String cep) {
        this.placa = placa;
        this.tipoPerda = tipoPerda;
        this.marca = marca;
        this.modelo = modelo;
        this.modeloNo = modeloNo;
        this.ano = ano;
        this.cor = cor;
        this.dataPerda = dataPerda;
        this.lojaPerda = lojaPerda;
        this.ufPerda = ufPerda;
        this.nome = nome;
        this.cpf = cpf;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.email = email;
        this.tipoLogradouro = tipoLogradouro;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoPerda() {
        return tipoPerda;
    }

    public void setTipoPerda(String tipoPerda) {
        this.tipoPerda = tipoPerda;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getModeloNo() {
        return modeloNo;
    }

    public void setModeloNo(String modeloNo) {
        this.modeloNo = modeloNo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Date getDataPerda() {
        return dataPerda;
    }

    public void setDataPerda(Date dataPerda) {
        this.dataPerda = dataPerda;
    }

    public String getLojaPerda() {
        return lojaPerda;
    }

    public void setLojaPerda(String lojaPerda) {
        this.lojaPerda = lojaPerda;
    }

    public String getUfPerda() {
        return ufPerda;
    }

    public void setUfPerda(String ufPerda) {
        this.ufPerda = ufPerda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
