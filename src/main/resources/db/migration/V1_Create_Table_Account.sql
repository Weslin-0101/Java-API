CREATE TABLE public.account (
                                id bigserial NOT NULL,
                                name varchar(100) NOT NULL,
                                email varchar(150) NOT NULL UNIQUE,
                                userName varchar(250) NOT NULL UNIQUE,
                                password varchar(150) NOT NULL,
                                account_non_expired BOOLEAN DEFAULT TRUE,
                                account_non_locked BOOLEAN DEFAULT TRUE,
                                credentials_non_expired BOOLEAN DEFAULT TRUE,
                                enabled BOOLEAN DEFAULT TRUE,
                                CONSTRAINT account_pkey PRIMARY KEY (id)
);

ALTER TABLE public.account ADD CONSTRAINT uk_account_id UNIQUE (id)