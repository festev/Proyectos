package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigDB {

    private String host, port, user, password, name;

    public ConfigDB() {
        try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("config.properties");
            props.load(fis);

            this.host = props.getProperty("db.host");
            this.port = props.getProperty("db.port");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
            this.name = props.getProperty("db.name");

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

}
