create table usuario
(
  id    bigserial    not null
    constraint usuario_id
    primary key,
  login varchar(10)  not null
    constraint usuario_login_key
    unique,
  senha varchar(200) not null,
  admin char,
  email varchar(50)
);

insert into usuario(id, login, senha, admin, email)
values (nextval('usuario_id_seq'), 'tanner', '$2a$10$zunlR7gvzGetOgD0.luhMOnKoGAucbl5o7tgekNUgRF1JEbSJG1Sm', 'S', 'gabrieltanner@gmail.com');

create table veiculo
(
  id               bigserial   not null
    constraint veiculo_id
    primary key,
  placa            varchar(10) not null
    constraint veiculo_placa_key
    unique,
  tipo_perda       varchar(50),
  marca            varchar(20),
  modelo           varchar(50),
  modelo_no        varchar(20),
  ano              varchar(9),
  cor              varchar(50),
  data_perda       timestamp,
  loja_perda       varchar(50),
  uf_perda         varchar(2),
  nome             varchar(50),
  cpf              varchar(14),
  telefone_fixo    varchar(20),
  telefone_celular varchar(20),
  email            varchar(50),
  tipo_logradouro  varchar(50),
  endereco         varchar(100),
  numero           varchar(10),
  bairro           varchar(50),
  cidade           varchar(50),
  uf               varchar(2),
  cep              varchar(9)
);

create table usuario
(
  id    bigserial    not null
    constraint usuario_id
    primary key,
  login varchar(10)  not null
    constraint usuario_login_key
    unique,
  senha varchar(200) not null,
  admin char,
  email varchar(50)
);

