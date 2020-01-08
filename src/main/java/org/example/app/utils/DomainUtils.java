package org.example.app.utils;

import ch.qos.logback.core.db.dialect.DBUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.app.model.NoteEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DomainUtils {

    private static Integer minDomainLength;

    private static Integer maxDomainLength;

    @Value("${application.domain.length.min}")
    public void setMinDomainLength(Integer length){
        DomainUtils.minDomainLength = length;
    }

    @Value("${application.domain.length.max}")
    public void setMaxDomainLength(Integer length){
        DomainUtils.maxDomainLength = length;
    }

    /**
     * Generates and Returns Random Domain
     * @return randomDomain: Random Generated Domain
     */
    public static String createNewDomain(){

        boolean foundDomain = false;
        String randomDomain = "";

        //Loop till right domain is generated.
        while(!foundDomain) {

            //Generates Random String for New Domain.
            randomDomain = generateRandomDomain();

            //Check if Domain already exists.
            NoteEntity domainList = DBUtils.getNoteEntityByDomain(randomDomain);
            if(domainList == null){

                //Break the loop, since domain does not exists.
                foundDomain =true;
            }
        }

        //Update Domain in the Database.
        DBUtils.updateDomainInDB(randomDomain);

        return randomDomain;
    }

    /**
     * Generate random domain.
     * @return
     */
    public static String generateRandomDomain(){
        return RandomStringUtils.randomAlphabetic(minDomainLength, minDomainLength + (new Random().nextInt(maxDomainLength)));
    }



    /**
     * Checks and Get Note from the domain.
     * @param domain
     * @return
     */
    public static String checkAndGetNoteFromDomain(String domain){
        String note = "";

        //Check for the Domain.
        if(!checkNewOrOldDomain(domain)){
                note = getNoteFromDomain(domain);
        }

        return note;
    }

    /**
     * Returns Note in the Domain.
     * @param domain
     * @return
     */
    public static String getNoteFromDomain(String domain){

        String note = "";

        NoteEntity noteEntity = DBUtils.getNoteEntityByDomain(domain);

        return noteEntity.getNote();
    }

    /**
     * Returns Note persist in domain DOMAIN.
     * @param domain
     * @return
     */
    public static boolean checkNewOrOldDomain(String domain) {

        boolean newDomain = false;

        NoteEntity noteEntity = DBUtils.getNoteEntityByDomain(domain);

        //Check if Domain Exists
        if(noteEntity == null){

            //Since it is new Domain.
            newDomain = true;

            //Update the domain in DB
            DBUtils.updateDomainInDB(domain);
        }
        return newDomain;
    }

    public static void saveNoteInDomain(String domain, String note){
        DBUtils.saveNoteInDomain(domain, note);
    }
}
