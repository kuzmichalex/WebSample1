package com.htp.util;

import java.util.ResourceBundle;
/**
 * Класс для доступа к строкам database.properties getInstance - метод для получения одного
 * эеземпляра класса getProperty - метод для получения значения свойства из database.properties
 */
public class DatabaseConfiguration {

  // Константы для доступа к строкам файла database.properties
  public static final String BUNDLE_NAME = "database";
  public static final String DATABASE_DRIVER_NAME = "driverName";
  public static final String DATABASE_URL = "url";
  public static final String DATABASE_LOGIN = "login";
  public static final String DATABASE_PASSWORD = "password";
  public static final String DATABASE_POOL_SIZE = "initialSize";
  private static DatabaseConfiguration instance;
  private ResourceBundle resourceBundle;

  /**
   * Метод для получения экземпляра конфигурации Это всё очень напоминает шаблон проектирования
   * singleton
   *
   * @return DatabaseConfiguration
   */
  public static DatabaseConfiguration getInstance() {
    if (instance != null) return instance;
    instance = new DatabaseConfiguration();
    instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    return instance;
  }

  /** Метод для получения значений строк */
  public String getProperty(String sKey) {
    return resourceBundle.getString(sKey);
  }
}
