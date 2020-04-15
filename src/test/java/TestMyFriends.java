package test.java;

import org.junit.*;
public class TestMyFriends {
    MyFriends mf;

    @Before
    public void setUp(){
        mf = new MyFriends();
    }

    @Test
    public void TestMyFriends(){
        // test request friends
        assert.assertTrue(mf.friendRequest("Amy","Andy"));
        assert.assertTrue(mf.friendRequest("Andy","Allen"));
        assert.assertTrue(mf.friendRequest("Andy","Bob"));

        assert.assertFalse(mf.friendRequest("Amy","Aldric"));

        // test 
        // assert.
    }
}