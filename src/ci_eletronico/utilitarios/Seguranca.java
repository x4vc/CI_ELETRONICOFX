/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.utilitarios;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author victorcmaf
 */
public class Seguranca {
    public static String encriptar(String Data) throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedString = encoder.encodeToString(Data.getBytes(StandardCharsets.UTF_8) );
        
        return encodedString;
        
    }
    
    public static String desencriptar(String Data) throws Exception {
        String decodedString;
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(Data);
        decodedString = new String(decodedByteArray);
        return decodedString;
        
    }
    
}
