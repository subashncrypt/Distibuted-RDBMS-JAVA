package ca.dal.distributed.dpg1.Controllers.UserInterfaceModule.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.io.FileWriter;

public class Register {

    Hashing encrypt = new Hashing();

    public void setRegister(String userName, String PassWord, String securityAnswer)
            throws IOException, NoSuchAlgorithmException {

        FileWriter registerDetails = new FileWriter("User_Profile.txt", true);
        BufferedWriter bw = new BufferedWriter(registerDetails);
        FileReader fr=new FileReader("User_Profile.txt");    
        BufferedReader br=new BufferedReader(fr);    
        if(br.readLine()!=null){
            bw.newLine();
        }        
        bw.write(encrypt.hash(userName)+" "+encrypt.hash(PassWord)+" "+encrypt.hash(securityAnswer));        
        bw.close();
    }

}
