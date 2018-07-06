
--DROP TABLE users IF EXISTS;

CREATE TABLE users (
  id         INTEGER PRIMARY KEY,
  name VARCHAR(30),
  email  VARCHAR(50)
);
CREATE TABLE pokemons(
id INTEGER auto_increment PRIMARY KEY ,
name VARCHAR (30),
weight VARCHAR(5),
speciesUrl VARCHAR(100),
speciesName VarChar(30)
);