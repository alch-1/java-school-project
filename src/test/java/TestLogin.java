package test.java;

import org.junit.*;

public class TestLogin {
    Login log;

    @Before
    public void setUp(){
        log = new Login();
    }
    @Test
    public void testLoginLogic(){
        // test user login
        assert.assertTrue(log.LoginLogic("Amy",123));

        // test wrong username/password
        assert.assertFalse(log.LoginLogic("Apple",123));
        assert.assertFalse(log.LoginLogic("Amy",234));

    }
}