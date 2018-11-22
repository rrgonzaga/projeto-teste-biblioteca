-- SEQUENCE: public.autor_codigo_seq

-- DROP SEQUENCE public.autor_codigo_seq;

CREATE SEQUENCE public.autor_codigo_seq;

ALTER SEQUENCE public.autor_codigo_seq
    OWNER TO root;

GRANT ALL ON SEQUENCE public.autor_codigo_seq TO root;



-- Table: public.autor

-- DROP TABLE public.autor;

CREATE TABLE public.autor
(
    codigo bigint NOT NULL DEFAULT nextval('autor_codigo_seq'::regclass),
    nome character varying(120) COLLATE pg_catalog."default" NOT NULL,
    descricao character varying(250) COLLATE pg_catalog."default",
    CONSTRAINT autor_pkey PRIMARY KEY (codigo)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.autor
    OWNER to root;