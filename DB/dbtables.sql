--------------------------------------
-- Sequence and tables
--------------------------------------

CREATE SEQUENCE clover_db.registration_expiry_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 555554
  START 1
  CACHE 1;
ALTER TABLE clover_db.registration_expiry_sequence
  OWNER TO clover_db;
GRANT ALL ON TABLE clover_db.registration_expiry_sequence TO clover_db;

--

CREATE SEQUENCE clover_db.user_details_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 555554
  START 1
  CACHE 1;
ALTER TABLE clover_db.user_details_sequence
  OWNER TO clover_db;
GRANT ALL ON TABLE clover_db.user_details_sequence TO clover_db;

--

CREATE TABLE clover_db.registration_expiry
(
  id bigint NOT NULL DEFAULT nextval('clover_db.registration_expiry_sequence'::regclass),
  exp_token character varying(50) NOT NULL,
  login_id character varying(50),
  password character varying(50),
  created_date_time timestamp without time zone NOT NULL,
  CONSTRAINT pk_registration_expiry_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE clover_db.registration_expiry
  OWNER TO clover_db;
GRANT ALL ON TABLE clover_db.registration_expiry TO clover_db;

--

CREATE TABLE clover_db.user_details
(
  id bigint NOT NULL DEFAULT nextval('clover_db.user_details_sequence'::regclass),
  login_id character varying(50) NOT NULL,
  name character varying(100) NOT NULL,
  password character varying(50),
  email character varying(50),
  pincode character varying(50),
  created_date timestamp without time zone NOT NULL,
  created_by character varying(50),
  CONSTRAINT pk_user_details_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE clover_db.user_details
  OWNER TO clover_db;
GRANT ALL ON TABLE clover_db.user_details TO clover_db;
ALTER TABLE clover_db.user_details
  ADD CONSTRAINT "UX_um_login_id" UNIQUE(login_id);

--


