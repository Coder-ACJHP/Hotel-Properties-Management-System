package com.coder.hms.utils;

import com.coder.hms.ui.external.InformationFrame;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private String userName;
    private String password;
    private String hotelName;
    final InputStream inputStream;
    private static LoggingEngine logging;
    private final EncryptPassword passwordEncrypter;
    final static String FILE_PATH = "/com/coder/hms/languageFiles/Credentials.properties";

    public PropertiesReader() {
        
        logging = LoggingEngine.getInstance();
        passwordEncrypter = new EncryptPassword();
        final Properties properties = new Properties();

        inputStream = PropertiesReader.class.getResourceAsStream(FILE_PATH);
        try {
            
            properties.load(inputStream);
            
        } catch (IOException ex) {
            
            logging.setMessage("PropertiesReader : "+ ex.getLocalizedMessage());
            final InformationFrame frame = new InformationFrame();
            frame.setMessage(ex.getLocalizedMessage());
            frame.setVisible(true);
        }
        
        userName = properties.getProperty("system.username");
        password = properties.getProperty("system.password");
        hotelName = properties.getProperty("system.hotelname");
    }

    public boolean checkIsAdministrator(String inputName, String inputPwd) {
        logging.setMessage("PropertiesReader -> checking System moderator credentials...");
        return inputName.equals(userName) || passwordEncrypter.passwordIsMatch(inputPwd, password);
    }

    public String getHotelName() {
        return hotelName;
    }

}
