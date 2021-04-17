package test.com.houarizegai.TicTacToc;

import com.houarizegai.TicTacToc.PlayWindow;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IsDialog2 {   //弹出对话框的三个用例之玩家2胜利
    PlayWindow testObj = new PlayWindow(0);
    String exp;
    int score;
    public IsDialog2(String str1,String str2,String str3,String str4,String str5,String str6,String str7,String str8,String str9,String exp,int score) {
        this.testObj.buttonsXO[0].setText(str1);
        this.testObj.buttonsXO[1].setText(str2);
        this.testObj.buttonsXO[2].setText(str3);
        this.testObj.buttonsXO[3].setText(str4);
        this.testObj.buttonsXO[4].setText(str5);
        this.testObj.buttonsXO[5].setText(str6);
        this.testObj.buttonsXO[6].setText(str7);
        this.testObj.buttonsXO[7].setText(str8);
        this.testObj.buttonsXO[8].setText(str9);
        this.exp = exp;
        this.score=score;
    }
    @Before
    public void setUp() throws Exception {
        //this.testObj = new PlayWindow(0);//此测试为双人模式
    }

    @After
    public void tearDown() throws Exception {
        this.testObj = null;
    }
    @Parameterized.Parameters()
    public static Collection testDataset() {
        return Arrays.asList(new Object[]{"O","O","O","","","","","","","玩家2获胜",-1},new Object[]{"O","","","O","","","O","","","玩家2获胜",-1},new Object[]{"O","","","","O","","","","O","玩家2获胜",-1});
    }
    @Test
    public void PlayChess() {
        this.testObj.getResult(false);
        Assert.assertTrue(this.testObj.mes.equals(this.exp));
        Assert.assertTrue(this.testObj.xScore-this.testObj.oScore==this.score);
    }
}

