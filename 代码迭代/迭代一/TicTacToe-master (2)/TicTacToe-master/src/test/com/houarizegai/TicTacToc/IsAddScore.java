package test.com.houarizegai.TicTacToc;

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
@RunWith(JUnit4.class)
public class IsAddScore {//判断是否在连赢5盘后分加上了
    PlayWindow testObj = new PlayWindow(0);
    int score;
    public void Add(String str1,String str2,String str3,String str4,String str5,String str6,String str7,String str8,String str9) {
        this.testObj.buttonsXO[0].setText(str1);
        this.testObj.buttonsXO[1].setText(str2);
        this.testObj.buttonsXO[2].setText(str3);
        this.testObj.buttonsXO[3].setText(str4);
        this.testObj.buttonsXO[4].setText(str5);
        this.testObj.buttonsXO[5].setText(str6);
        this.testObj.buttonsXO[6].setText(str7);
        this.testObj.buttonsXO[7].setText(str8);
        this.testObj.buttonsXO[8].setText(str9);
    }
    @Before
    public void setUp() throws Exception {
        //this.testObj = new PlayWindow(0);//此测试为双人模式
    }

    @After
    public void tearDown() throws Exception {
        this.testObj = null;
    }
    @Test
    public void PlayChess() {
        Add("O","O","O","","","","","","");
        this.testObj.getResult(false);
        Assert.assertTrue(this.testObj.oScore-this.testObj.xScore==1);
        Add("O","","","O","","","O","","");
        this.testObj.getResult(false);
        Assert.assertTrue(this.testObj.oScore-this.testObj.xScore==2);
        Add("O","","","","O","","","","O");
        this.testObj.getResult(false);
        Assert.assertTrue(this.testObj.oScore-this.testObj.xScore==3);
        Add("","O","","","O","","","O","");
        this.testObj.getResult(false);
        Assert.assertTrue(this.testObj.oScore-this.testObj.xScore==4);
        Add("","","","","","","O","O","O");
        this.testObj.getResult(false);
        Assert.assertTrue(this.testObj.oScore-this.testObj.xScore==5);
    }
}
