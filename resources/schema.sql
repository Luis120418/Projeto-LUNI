CREATE TABLE rodada (
    id IDENTITY PRIMARY KEY,
    startup_nome VARCHAR(100),
    numero_rodada INT,
    receita DOUBLE,
    caixa DOUBLE,
    receita_base DOUBLE
);

CREATE TABLE decisao (
    id IDENTITY PRIMARY KEY,
    startup_nome VARCHAR(100),
    numero_rodada INT,
    tipo_decisao VARCHAR(50)
);
