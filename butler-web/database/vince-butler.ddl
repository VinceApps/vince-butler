BEGIN;

DROP TABLE module_instance_param_value;
DROP TABLE module_instance;
DROP TABLE module_parameter;
DROP TABLE module;

CREATE TABLE module
(
  uid  SERIAL      NOT NULL,
  name VARCHAR(50) NOT NULL UNIQUE,
  url  TEXT        NOT NULL UNIQUE,
  PRIMARY KEY (uid)
);

CREATE TABLE module_parameter
(
  uid            SERIAL      NOT NULL,
  module_uid     INTEGER     NOT NULL,
  parameter_name VARCHAR(50) NOT NULL,
  parameter_type VARCHAR(50) NOT NULL,
  mandatory      BOOL        NOT NULL,
  PRIMARY KEY (uid)
);

ALTER TABLE module_parameter ADD CONSTRAINT uc_module_parameter_key UNIQUE (module_uid, parameter_name);
ALTER TABLE module_parameter ADD CONSTRAINT fk_module_parameter_module FOREIGN KEY (module_uid) REFERENCES module (uid);

CREATE TABLE module_instance
(
  uid        SERIAL      NOT NULL,
  name       VARCHAR(50) NOT NULL UNIQUE,
  module_uid INTEGER     NOT NULL,
  PRIMARY KEY (uid)
);

ALTER TABLE module_instance ADD CONSTRAINT fk_module_instance_module FOREIGN KEY (module_uid) REFERENCES module (uid);

CREATE TABLE module_instance_param_value
(
  uid                  SERIAL  NOT NULL,
  module_instance_uid  INTEGER NOT NULL,
  module_parameter_uid INTEGER NOT NULL,
  parameter_value      TEXT,
  PRIMARY KEY (uid)
);

ALTER TABLE module_instance_param_value ADD CONSTRAINT uc_module_instance_param_value_key UNIQUE (module_instance_uid, module_parameter_uid);
ALTER TABLE module_instance_param_value ADD CONSTRAINT fk_module_instance_param_value_instance FOREIGN KEY (module_instance_uid) REFERENCES module_instance (uid);
ALTER TABLE module_instance_param_value ADD CONSTRAINT fk_module_instance_param_value_parameter FOREIGN KEY (module_parameter_uid) REFERENCES module_parameter (uid);

END;