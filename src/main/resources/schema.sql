CREATE TABLE IF NOT EXISTS documents (
  id INT PRIMARY KEY AUTO_INCREMENT,
  content CLOB,
  type VARCHAR(115),
  name VARCHAR(115),
  index VARCHAR(115),
  parent INT,
  foreign key (parent) references documents(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS attributes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(115),
  type VARCHAR(115),
  value VARCHAR(115),
  value_int INT,
  doc_id INT NOT NULL,
  foreign key (doc_id) references documents(id) ON DELETE CASCADE
);

-- test records
INSERT INTO documents(id, name, type, parent) VALUES(0, 'test1', 'doc type 1', null);
INSERT INTO documents(id, name, type, parent) VALUES(1, 'test2', 'doc type 2', null);
INSERT INTO documents(id, name, type, parent) VALUES(2, 'test3', 'doc type 2', 0);
INSERT INTO documents(id, name, type, parent) VALUES(3, 'test4', 'doc type 2', 0);
INSERT INTO documents(id, name, type, parent) VALUES(4, 'test5', 'doc type 2', 1);