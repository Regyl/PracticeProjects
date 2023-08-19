package com.deepspace;

import generated.ValCurs;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.ByteArrayInputStream;

/**
 * JAXB unmarshaller for {@link ValCurs} from string.
 */
public class ValCursUnmarshaller {
    
    private final JAXBContext context;
    
    public ValCursUnmarshaller() throws JAXBException {
        this.context = JAXBContext.newInstance(ValCurs.class);
    }
    
    /**
     * Convert bytes to {@link ValCurs}.
     *
     * @param bytes             byte response from CBR
     * @return                  converted response
     * @throws JAXBException    never?
     */
    public ValCurs unmarshal(byte[] bytes) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller(); //Because it is not thread-safe
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        
        return (ValCurs) unmarshaller.unmarshal(bais);
    }
}
