CREATE TABLE IF NOT EXISTS public.users (
    id BIGSERIAL NOT NULL,
    user_name VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    account_non_expired BOOLEAN,
    account_non_locked BOOLEAN,
    credentials_non_expired BOOLEAN,
    enabled BOOLEAN
);

ALTER TABLE public.users ADD CONSTRAINT uk_users_id UNIQUE (id);