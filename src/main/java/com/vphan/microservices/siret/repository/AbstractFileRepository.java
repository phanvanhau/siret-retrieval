package com.vphan.microservices.siret.repository;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractFileRepository<T> {
  public static final String COLUMN_SEPARATOR = ";";
  protected List<T> entities;
  public void loadDB() {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream(getTableName());
    this.entities = new ArrayList<>();
    InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader reader = new BufferedReader(streamReader);
    try {
      for (String line; (line = reader.readLine()) != null;) {
        T entity = deserializeEntity(line);
        if (entity != null) {
          this.entities.add(entity);
        }
      }
    } catch (IOException ex) {
      log.error("Could not connect to Database");
    }
  }
  /*
    Overide the DB file with current state of "this.entities"
   */
  public void writeDB() {
    // TODO
    // for each entity in this.entities
    // call serializeEntity(entity) to convert object to string with SEPARATOR based
    // write string to DB file
  }
  abstract public String getTableName();
  abstract public T deserializeEntity(String line);
  abstract public String serializeEntity(T entity);
  abstract public boolean isEqual(T entity1, T entity2);

  public void save(T entity) {
    int index = -1;
    for (int i = 0; i < this.entities.size(); i++) {
      if (isEqual(this.entities.get(i), entity)) {
        index = i;
      }
    }
    if (index > 0) {
      this.entities.set(index, entity);
      writeDB();
    }
  }

  public List<T> all() {
    return this.entities;
  }
}
