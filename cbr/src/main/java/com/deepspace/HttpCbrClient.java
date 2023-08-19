package com.deepspace;

import generated.ValCurs;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Implementation of <a href="www.cbr.ru">Russian Central Bank</a> API call.
 */
@Log
public class HttpCbrClient {
    
    private static final DateTimeFormatter CBR_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private static final String API_URL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=%s";
    
    /**
     * Request CBR daily course for common values.
     *
     * @param date          requested date
     * @return              {@link ValCurs} array
     * @throws IOException
     */
    public byte[] requestDaily(LocalDate date) throws IOException {
        String targetUrl = String.format(API_URL, date.format(CBR_DATE_FORMAT));
        URL url = URI.create(targetUrl).toURL();
        
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        
        //Get Response
        InputStream is = con.getInputStream();
        return is.readAllBytes();
    }
}
