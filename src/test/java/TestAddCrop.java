package test.java;

import org.junit.*;

public class TestAddCrop {
    LandDAO landdao;

    @Before
    public void setUp(){
        landdao = new LandDAO();
    }
    @Test
    public void TestAddCrop(){
        // test user login
        assert.assertTrue(landdao.addCrop("Amy",123));

        // test wrong username/password
        assert.assertFalse(log.LoginLogic("Apple",123));
        assert.assertFalse(log.LoginLogic("Amy",234));

    }
}