------------------------------
-- POSTGRES DB CREATE SCRIPT
------------------------------
CREATE ROLE "clover_db" LOGIN
  PASSWORD 'development'
  SUPERUSER NOINHERIT CREATEROLE
   VALID UNTIL 'infinity';
UPDATE pg_authid SET rolcatupdate=true WHERE rolname='clover_db';

CREATE DATABASE clover_db
		WITH OWNER = clover_db
		TEMPLATE template0
		ENCODING = 'UTF8'
		TABLESPACE = pg_default
		 LC_COLLATE  'C'
		LC_CTYPE  'C'
		CONNECTION LIMIT  -1;

GRANT ALL ON DATABASE clover_db TO clover_db;
\c clover_db
CREATE SCHEMA clover_db AUTHORIZATION clover_db;
drop SCHEMA public;
\disconnect DEFAULT;
