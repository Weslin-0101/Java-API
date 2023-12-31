CREATE TABLE IF NOT EXISTS public.user_permission (
    id_user BIGSERIAL NOT NULL,
    id_permission BIGSERIAL NOT NULL,
    CONSTRAINT pk_user_permission PRIMARY KEY (id_user),
    CONSTRAINT uk_user_permission UNIQUE (id_permission),
    CONSTRAINT fk_user_permission FOREIGN KEY (id_user) REFERENCES public.account (id),
    CONSTRAINT fk_user_permission_permission FOREIGN KEY (id_permission) REFERENCES public.permission (id)
)