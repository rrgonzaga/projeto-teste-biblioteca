-- SEQUENCE: public.usuario_codigo_seq

-- DROP SEQUENCE public.usuario_codigo_seq;

CREATE SEQUENCE public.usuario_codigo_seq;

ALTER SEQUENCE public.usuario_codigo_seq
    OWNER TO root;

GRANT ALL ON SEQUENCE public.usuario_codigo_seq TO root;


-- Table: public.usuario

-- DROP TABLE public.usuario;

CREATE TABLE public.usuario
(
    codigo bigint NOT NULL DEFAULT nextval('usuario_codigo_seq'::regclass),
    nome character varying(80) COLLATE pg_catalog."default" NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(120) COLLATE pg_catalog."default" NOT NULL,
    perfil_administrador boolean NOT NULL DEFAULT false,
    ativo boolean NOT NULL DEFAULT true,
    CONSTRAINT usuario_pkey PRIMARY KEY (codigo)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.usuario
    OWNER to root;