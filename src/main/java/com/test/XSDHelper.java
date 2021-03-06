package com.test;

/**
 * Created by leena on 1/10/17.
 */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;package com.test;

import com.test.schema.ContactType;
import com.test.schema.ObjectFactory;
import com.test.schema.PhoneType;
import com.test.schema.PhonelistType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

public class XSDHelper {

    public static void main(String[] argv){
        XSDHelper xsdHelper = new XSDHelper();
        ContactType contactType = xsdHelper.parseXML("/home/leena/workspace/SampleProject/src/main/resources/contact.xml");
        System.out.println("Parsed XML " + contactType.getFirstname() +" " + contactType.getLastname());

        ContactType leena = new ContactType();
        leena.setContactid(2);
        leena.setFirstname("Leena");
        leena.setLastname("Patil");
        leena.setAge(35);

        PhonelistType phonelistType = new PhonelistType();
        leena.setPhonelist(phonelistType);
        PhoneType phoneType = new PhoneType();
        phoneType.setPhonenumber(5108941329L);
        phonelistType.getPhone().add(phoneType);
        System.out.println(xsdHelper.convertToXML(leena));
    }

    public ContactType parseXML(String filePath){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ContactType.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            ContactType contactType = (ContactType)unmarshaller.unmarshal(new File(filePath));
            return contactType;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public ContactType parseString(String string){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ContactType.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            ContactType contactType = (ContactType)unmarshaller.unmarshal(new StringReader(string));
            return contactType;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public String convertToXML(ContactType contactType){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ContactType.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(contactType,stringWriter);
            return stringWriter.toString();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}


