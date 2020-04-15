package test.java;

import org.junit.*;

public class TestRegistration {
    Registration reg;

    @Before
    public void setUp(){
        reg = new Registration();
    }

    @Test
    public void testAddUser(){
        // test add user
        assert.assertTrue(reg.addUser("Amy","Amy Wong",123));
        assert.assertTrue(reg.addUser("Andy","Andy Ng",234));
        assert.assertTrue(reg.addUser("Allen","Allen Lee",345));
        assert.assertTrue(reg.addUser("Bob","Bob Tan",456));

        // test add duplicate user
        assert.assertFalse(reg.addUser("Bob","Bob Tan",456));

    }
}