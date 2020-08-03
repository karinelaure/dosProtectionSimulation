package com.dos.protection;

import com.dos.protection.service.DosProtectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;


/**
 * @auther Karine Camhy
 * @since 8/2/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DosControllerTest {
    @Autowired
    private DosProtectionService service;


    @Test
    public void avaibleRequest()  throws Exception{
        assertTrue(service.isMaxRequestPerTime(50));
    }

    @Test
    public void maxRichedRequest()  throws Exception{
        for(int i=0; i < 7; i++)
            service.isMaxRequestPerTime(10);
        assertFalse(service.isMaxRequestPerTime(10));
    }

}
