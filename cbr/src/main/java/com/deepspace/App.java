package com.deepspace;

import generated.ValCurs;
import jakarta.xml.bind.JAXBException;
import lombok.extern.java.Log;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Test exercise to call <a href="www.cbr.ru">Russian Central Bank</a> API which produces response in XML format using XSD.
 */
@Log
public class App {
    
    public static void main(String[] args) throws IOException, JAXBException {
        if(args.length != 2) {
            log.severe("Incorrect parameters");
            return;
        }
        String code = args[0];
        LocalDate date = LocalDate.parse(args[1]);
        
        
        HttpCbrClient cbrClient = new HttpCbrClient();
        byte[] response = cbrClient.requestDaily(date);
        
        ValCursUnmarshaller unmarshaller = new ValCursUnmarshaller();
        ValCurs valCurs = unmarshaller.unmarshal(response);
        
        ValCurs.Valute valute = valCurs.getValute().stream()
                .filter(item -> code.equals(item.getCharCode()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Course for %s not found", code)));
        
        System.out.println("CharCode " + valute.getCharCode());
        System.out.println("value " + valute.getValue());
        System.out.println("name " + valute.getName());
    }
}
