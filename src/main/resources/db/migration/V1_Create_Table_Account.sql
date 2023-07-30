CREATE TABLE public.account (
                                id bigserial NOT NULL,
                                name varchar(100) NOT NULL,
                                email varchar(150) NOT NULL UNIQUE,
                                password varchar(150) NOT NULL,
                                CONSTRAINT account_pkey PRIMARY KEY (id)
);