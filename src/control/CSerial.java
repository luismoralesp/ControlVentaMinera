/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import hardware.HSerial;
import java.io.IOException;

/**
 *
 * @author Exile Backend
 */
public class CSerial {
    private static CSerial instacia = null;

    private CSerial() {
    }
    
    public static CSerial getInstacia() {
        if (instacia == null){
            instacia = new CSerial();
        }
        return instacia;
    }
    
    public void initialize(){
        HSerial.getInstacia().initialize();
    }
    
    public void send(int comm) throws IOException{
        HSerial.getInstacia().send(comm);
    }

    public String read(){
        return HSerial.getInstacia().read();
    }
    
    public void close(){
        HSerial.getInstacia().close();
    }
}
