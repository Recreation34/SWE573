package database;

import utils.Logger;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DataBaseInteractions {

    private Connection connection = null;
    private Statement statement = null;

    public List<Object> retrieve(String query, Object object) {
        Logger.info("Query = " + query);
        List<Object> data = new ArrayList<>();

        try {
            connection = Connect.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                for (Field field : object.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        field.set(object, rs.getObject(field.getName()));
                    } catch (Exception ignored) {
                    }
                }
                data.add(Class.forName(object.getClass().getName()).getConstructor(Object.class).newInstance(object));
            }
        } catch (Exception e) {
            Logger.info("Error @Interactions/retrieve");
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    public Object retrieve(String query) {
        Logger.info("Query = " + query);

        try {
            connection = Connect.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                return rs.getObject(1);
            }
        } catch (Exception e) {
            Logger.info("Error @Interactions/retrieve");
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public int executeQuery(String query, Object object) {
        try {
            Logger.info("Executing query : " + queryParser(query, object));
            connection = Connect.getConnection();
            statement = connection.createStatement();
            return statement.executeUpdate(queryParser(query, object));
        } catch (Exception e) {
            Logger.info("Error @Interactions/executeQuery");
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    private static String queryParser(String query, Object object) throws IllegalAccessException {
        if (query.contains("VALUES (")) {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(object) != null) {
                    query = query.replaceFirst("\\?", "'" + field.get(object).toString() + "'");
                } else {
                    query = query.replaceFirst("\\?", "NULL");
                }
            }
            return query;
        } else if (query.contains("SET") && query.contains("=")) {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (query.contains(field.getName() + " = ?")) {
                    if (field.get(object) != null) {
                        query = query.replace(field.getName() + " = ?", field.getName() + " = '" + field.get(object).toString() + "'");
                    } else {
                        query = query.replace(field.getName() + " = ?", field.getName() + " = NULL");
                    }
                }
            }
            return query;
        } else {
            Logger.info("Make sure query is properly formatted");
        }
        return query;
    }
}