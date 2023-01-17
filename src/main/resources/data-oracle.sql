CREATE TABLE sim
( id number(10) NOT NULL,
  operator_code number(10) NOT NULL,
  country VARCHAR(30) NOT NULL,
  status VARCHAR(30) NOT NULL,
  CONSTRAINT sim_pk PRIMARY KEY (id)
);

CREATE TABLE device
( id number(10) NOT NULL,
  is_ready NUMBER(1,0) NOT NULL,
  sim number(10),
  temperature number(10) NOT NULL,
  CONSTRAINT device_pk PRIMARY KEY (id),
  CONSTRAINT sim_fk FOREIGN KEY (sim) REFERENCES sim(id)
);

INSERT INTO sim (id, operator_code, country, status) VALUES (1, 1, 'Italy', 'ACTIVE');
INSERT INTO sim (id, operator_code, country, status) VALUES (2, 2, 'Italy', 'ACTIVE');
INSERT INTO sim (id, operator_code, country, status) VALUES (3, 3, 'Italy', 'ACTIVE');
INSERT INTO sim (id, operator_code, country, status) VALUES (4, 1, 'Spain', 'WAITING FOR ACTIVATION');
INSERT INTO sim (id, operator_code, country, status) VALUES (5, 2, 'Spain', 'WAITING FOR ACTIVATION');
INSERT INTO sim (id, operator_code, country, status) VALUES (6, 3, 'Spain', 'WAITING FOR ACTIVATION');
INSERT INTO sim (id, operator_code, country, status) VALUES (7, 1, 'France', 'BLOCKED');
INSERT INTO sim (id, operator_code, country, status) VALUES (8, 2, 'France', 'BLOCKED');
INSERT INTO sim (id, operator_code, country, status) VALUES (9, 3, 'France', 'BLOCKED');
INSERT INTO sim (id, operator_code, country, status) VALUES (10, 1, 'UK', 'DEACTIVATED');
INSERT INTO sim (id, operator_code, country, status) VALUES (11, 2, 'UK', 'DEACTIVATED');
INSERT INTO sim (id, operator_code, country, status) VALUES (12, 3, 'UK', 'DEACTIVATED');

INSERT INTO device (id, is_ready, sim, temperature) VALUES (1, 1, 1, 10);
INSERT INTO device (id, is_ready, sim, temperature) VALUES (2, 0, null, -20);
INSERT INTO device (id, is_ready, sim, temperature) VALUES (3, 0, 5, 10);
INSERT INTO device (id, is_ready, sim, temperature) VALUES (4, 0, null, -20);
INSERT INTO device (id, is_ready, sim, temperature) VALUES (5, 1, 2, 10);
INSERT INTO device (id, is_ready, sim, temperature) VALUES (6, 1, 3, -20);
