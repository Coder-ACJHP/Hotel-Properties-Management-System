package com.coder.hms.utils;

import com.coder.hms.ui.external.InformationFrame;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesReader {

    private String userName;
    private String password;
    private String hotelName;
    final InputStream inputStream;
    final static String FILE_PATH = "/com/coder/hms/languageFiles/Credentials.properties";

    public PropertiesReader() {

        final Properties properties = new Properties();

        inputStream = PropertiesReader.class.getResourceAsStream(FILE_PATH);
        try {
            properties.load(inputStream);
        } catch (IOException ex) {
            final InformationFrame frame = new InformationFrame();
            frame.setMessage("Cannot access to system file!");
            frame.setVisible(true);
        }
        
        userName = properties.getProperty("system.username");
        password = properties.getProperty("system.password");
        hotelName = properties.getProperty("system.hotelname");
    }

    public boolean checkIsAdministrator(String inputName, String inputPwd) {

        return inputName.equals(userName) || inputPwd.equals(password);
    }

    public String getHotelName() {
        return hotelName;
    }

}
