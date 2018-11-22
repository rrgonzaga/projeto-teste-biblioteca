-- SEQUENCE: public.livro_codigo_seq

-- DROP SEQUENCE public.livro_codigo_seq;

CREATE SEQUENCE public.livro_codigo_seq;

ALTER SEQUENCE public.livro_codigo_seq
    OWNER TO root;

GRANT ALL ON SEQUENCE public.livro_codigo_seq TO root;


-- Table: public.livro

-- DROP TABLE public.livro;

CREATE TABLE public.livro
(
    codigo bigint NOT NULL DEFAULT nextval('livro_codigo_seq'::regclass),
    isbn character varying(40) COLLATE pg_catalog."default" NOT NULL,
    titulo character varying(120) COLLATE pg_catalog."default" NOT NULL,
    codigo_autor bigint NOT NULL,
    editora character varying(50) COLLATE pg_catalog."default" NOT NULL,
    edicao character varying(50) COLLATE pg_catalog."default" NOT NULL,
    ano_publicacao character varying(4) COLLATE pg_catalog."default",
    descricao character varying(250) COLLATE pg_catalog."default",
    CONSTRAINT livro_pkey PRIMARY KEY (codigo),
    CONSTRAINT livro_codigo_autor_fkey FOREIGN KEY (codigo_autor)
        REFERENCES public.autor (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.livro
    OWNER to root;

-- Index: autor_fkey

-- DROP INDEX public.autor_fkey;

CREATE INDEX autor_fkey
    ON public.livro USING btree
    (codigo_autor)
    TABLESPACE pg_default;