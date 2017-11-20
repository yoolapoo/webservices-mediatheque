DROP TABLE IF EXISTS token_store;
CREATE TABLE token_store
(
  uuid character varying(255) NOT NULL,
  token text,
  expire_at timestamp without time zone,
  CONSTRAINT token_store_pkey PRIMARY KEY (uuid)
)
WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS media;
CREATE TABLE media
(
  id_media text,
  type text,
  author text,
  title text,
  creation text,
  genre text,
  CONSTRAINT music_pkey PRIMARY KEY(id_media)
)
WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
  id_user text,
  username text,
  email text,
  salt text,
  passhash text,
  CONSTRAINT media_pkey PRIMARY KEY(id_user)
)
WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS loan;
CREATE TABLE loan
(
  id_loan text,
  id_media text REFERENCES media (id_media),
  id_user text REFERENCES users (id_user),
  CONSTRAINT loan_pkey PRIMARY KEY (id_media,id_user)
)
WITH (
OIDS=FALSE
);

INSERT INTO media (id_media, type, author, title, creation, genre)
VALUES('1','movie','Steven Spielberg','E.T','1982/12/01','Science-fiction');