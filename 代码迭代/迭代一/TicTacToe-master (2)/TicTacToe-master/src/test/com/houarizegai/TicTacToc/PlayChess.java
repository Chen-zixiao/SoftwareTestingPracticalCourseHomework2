package test.com.houarizegai.TicTacToc;
import com.houarizegai.TicTacToc.MainWindow;
import com.houarizegai.TicTacToc.PlayWindow;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PlayChess {//测试用例5，6
    PlayWindow testObj = null;
    @Before
    public void setUp() throws Exception {
        this.testObj = new PlayWindow(0);//此测试为双人模式
    }

    @After
    public void tearDown() throws Exception {
        this.testObj = null;
    }

    @Test
    public void getChess() {//点击有子棋子无效，根据逻辑，printXOForFriend函数会返回haveChess[index]=1;
        if (this.testObj.buttonsXO[1].getText().equals("")) {
            Assert.assertTrue(this.testObj.haveChess[1] == 0);
        }
        else Assert.assertTrue(this.testObj.haveChess[1] == 0);
        if (this.testObj.buttonsXO[2].getText().equals("")) {
            Assert.assertTrue(this.testObj.haveChess[2] == 0);
        }
        else Assert.assertTrue(this.testObj.haveChess[2] == 0);
        if (this.testObj.buttonsXO[3].getText().equals("")) {
            Assert.assertTrue(this.testObj.haveChess[3] == 0);
        }
        else Assert.assertTrue(this.testObj.haveChess[3] == 0);
        if (this.testObj.buttonsXO[4].getText().equals("")) {
            Assert.assertTrue(this.testObj.haveChess[4] == 0);
        }
        else Assert.assertTrue(this.testObj.haveChess[4] == 0);
        if (this.testObj.buttonsXO[5].getText().equals("")) {
            Assert.assertTrue(this.testObj.haveChess[5] == 0);
        }
        else Assert.assertTrue(this.testObj.haveChess[5] == 0);
        if (this.testObj.buttonsXO[6].getText().equals("")) {
            Assert.assertTrue(this.testObj.haveChess[6] == 0);
        }
        else Assert.assertTrue(this.testObj.haveChess[6] == 0);
        if (this.testObj.buttonsXO[7].getText().equals("")) {
            Assert.assertTrue(this.testObj.haveChess[7] == 0);
        }
        else Assert.assertTrue(this.testObj.haveChess[7] == 0);
        if (this.testObj.buttonsXO[8].getText().equals("")) {
            Assert.assertTrue(this.testObj.haveChess[8] == 0);
        }
        else Assert.assertTrue(this.testObj.haveChess[8] == 0);
        if (this.testObj.buttonsXO[0].getText().equals("")) {
            Assert.assertTrue(this.testObj.haveChess[0] == 0);
        }
        else Assert.assertTrue(this.testObj.haveChess[0] == 0);
    }

}



