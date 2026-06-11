-- public.users definição

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
  id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
  user_name varchar(100) NOT NULL,
  "password" varchar(255) NOT NULL,
  create_at timestamptz DEFAULT now() NOT NULL,
  "role" varchar(55) NULL,
  CONSTRAINT unique_user_name UNIQUE (user_name),
  CONSTRAINT users_pkey PRIMARY KEY (id)
);


-- public.tasks definição

-- Drop table

-- DROP TABLE public.tasks;

CREATE TABLE public.tasks (
    id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
    "name" varchar(100) NOT NULL,
    done bool DEFAULT false NOT NULL,
    favorite bool DEFAULT false NOT NULL,
    create_at timestamptz DEFAULT now() NOT NULL,
    user_id int4 NOT NULL,
    CONSTRAINT tasks_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id)
);