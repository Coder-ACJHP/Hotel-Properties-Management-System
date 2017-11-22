/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.utils;

import org.jasypt.digest.PooledStringDigester;

public class EncryptPassword {
    
    private final PooledStringDigester digester;

    public EncryptPassword() {
        //Initialize password degister
        digester = new PooledStringDigester();
        digester.setPoolSize(4);
        digester.setAlgorithm("SHA-1");
        digester.setIterations(5000);
    }
    
    //encrypt entered password 
    public String encryptPassword(String pwd) {
       return digester.digest(pwd);
    }
    
    //decriypt password and check is match.
    public boolean passwordIsMatch(String inputPwd, String encryptedPwd) {
        if(inputPwd.trim().length() > 0 && encryptedPwd.trim().length() > 0) {
           return digester.matches(inputPwd, encryptedPwd);
        }
        return false;
    }
}
