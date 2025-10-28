CREATE TABLE IF NOT EXISTS startup (
    nome VARCHAR(100) PRIMARY KEY,
    caixa DOUBLE,
    receita_base DOUBLE,
    reputacao INT,
    moral INT
);

CREATE TABLE IF NOT EXISTS rodada (
    id IDENTITY PRIMARY KEY,
    startup_nome VARCHAR(100),
    numero_rodada INT,
    receita DOUBLE,
    caixa DOUBLE,
    receita_base DOUBLE,
    FOREIGN KEY (startup_nome) REFERENCES startup(nome)
);

CREATE TABLE IF NOT EXISTS decisao (
    id IDENTITY PRIMARY KEY,
    startup_nome VARCHAR(100),
    numero_rodada INT,
    tipo_decisao VARCHAR(50),
    FOREIGN KEY (startup_nome) REFERENCES startup(nome)
);
