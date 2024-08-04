CREATE TABLE inventory.user_profile (
                                        id_user uuid NOT NULL,
                                        id_profile uuid NOT NULL
);

ALTER TABLE inventory.user_profile ADD CONSTRAINT fk_profile FOREIGN KEY (id_profile) REFERENCES inventory.profile(profile_id);
ALTER TABLE inventory.user_profile ADD CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES inventory."user"(user_id);