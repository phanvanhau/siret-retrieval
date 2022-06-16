package com.vphan.microservices.siret.repository;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractFileRepository<T> {
  public static final String COLUMN_SEPARATOR = ";";
  protected List<T> entities;
  protected String dbFileLocation;
  public void loadDB(String dbFileLocation) {
    this.dbFileLocation = dbFileLocation;
    this.entities = new ArrayList<>();

//    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//    InputStream is = classloader.getResourceAsStream(getTableName());
//    this.entities = new ArrayList<>();
//    InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
//    BufferedReader reader = new BufferedReader(streamReader);
    try {
      BufferedReader reader = new BufferedReader(new FileReader(dbFileLocation));
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
    Append the DB file with the serialized entity
    Note: there must be better approach to update DB instead of appending
    But for simplicity, I keep doing that
   */
  public void writeDB(T entity) throws IOException {
    RandomAccessFile stream = new RandomAccessFile(this.dbFileLocation, "rw");
    FileChannel channel = stream.getChannel();

    FileLock lock;
    while(true) {
      try {
        lock = channel.tryLock();
        stream.seek(stream.length());
        stream.writeChars("\n");
        stream.writeChars(serializeEntity(entity));

        // Release locked file
        lock.release();
        stream.close();
        channel.close();
        break;
      } catch (final OverlappingFileLockException e) {
        continue;
      } catch (IOException e) {
        stream.close();
        channel.close();
        return;
      }
    }
  }
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
    } else {
      this.entities.add(entity);
    }
    try {
      writeDB(entity);
    } catch (IOException e) {
      log.error("Could not write entity {}", e);
    }
  }

  public List<T> all() {
    return this.entities;
  }
}
