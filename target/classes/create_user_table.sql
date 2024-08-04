CREATE TABLE inventory."user" (
                                  user_id uuid NOT NULL,
                                  active bool NOT NULL,
                                  cpf varchar(255) NULL,
                                  created_at timestamp(6) NULL,
                                  email varchar(255) NULL,
                                  login varchar(255) NULL,
                                  "password" varchar(255) NULL,
                                  CONSTRAINT user_pkey PRIMARY KEY (user_id)
);