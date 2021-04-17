package test.com.houarizegai.TicTacToc;

import com.houarizegai.TicTacToc.PlayWindow;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TurnTest {
    PlayWindow testObj;
    /** 期望结果 */
    boolean exp;
    /** 控制赢家是哪一方（原游戏逻辑就是这样，将就用） */
    boolean player1Win;
    /** 是否执行判定（单纯为了增加比分的时候不执行判定） */
    boolean judge;
    String[] strList;

    public TurnTest(String str0, String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean exp_, boolean player1Win_, boolean judge_) {
        // 初始化测试对象
        strList = new String[9];
        strList[0] = str0;
        strList[1] = str1;
        strList[2] = str2;
        strList[3] = str3;
        strList[4] = str4;
        strList[5] = str5;
        strList[6] = str6;
        strList[7] = str7;
        strList[8] = str8;
        exp = exp_;
        player1Win = player1Win_;
        judge = judge_;
    }

    @BeforeClass
    public static void initWindow(){
        ;
    }

    public void setUp() throws Exception {
        for (int i = 0; i < 9; i++) {
            testObj.buttonsXO[i].setText(strList[i]);
        }
    }

    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void desWindow() throws Exception {
        ;
    }

    @Parameterized.Parameters()
    public static Collection testDataset() {
        return Arrays.asList(new Object[][]{
                /* 游戏初始化，玩家先手 */
                {"", "", "", "", "", "", "", "", "", true, true, true},
                /* 简单人机在玩家下子后能够下子 *//*
                {1, "X", "", "", "", "", "", "", "", "", false, true, false},
                {1, "X", "", "", "O", "", "", "X", "", "", false, false, false},
                /* 中等人机在玩家下子后能够下子 *//*
                {2, "X", "", "", "", "", "", "", "", "", false, true, false},
                {2, "X", "", "", "O", "", "", "X", "", "", false, false, false},
                /* 困难人机在玩家下子后能够下子 *//*
                {3, "X", "", "", "", "", "", "", "", "", false, true, false},
                {3, "X", "", "", "O", "", "", "X", "", "", false, false, false},
                /* 玩家和人机能够轮流下子 *//*
                {1, "", "", "", "", "", "", "", "", "", true, true, true},
                {1, "X", "", "", "", "", "", "", "", "", false, true, false}*/
        });
    }

    /**
     * 玩家先手下棋测试
     * @throws Exception
     */
    @Test
    public void PlayerFirstTest() {
        /* 游戏初始化，玩家先手 */
        for(int i = 0; i < 9; i++) {
            Assert.assertTrue(testObj.buttonsXO[i].getText().equals(""));
        }
    }

    /**
     * 简单人机下棋测试
     * @throws Exception
     */
    @Test
    public void EasyPCAfterPlayerTest() throws Exception {
        testObj = new PlayWindow(1);
        this.setUp();
        /* 玩家下子之后人机能够下子，模拟玩家下子 */
        exp = false;
        ActionEvent e = new ActionEvent(testObj.buttonsXO[0],0,"test");
        testObj.actionPerformed(e);
        /* 模拟一次下子之后，PC在actionPerformed中会下子，所以o和x的数量都会是1 */
        int oNum,xNum;
        oNum=xNum=0;
        // 统计o和x的数量
        for(int i = 0; i < 9; i++){
            String s = testObj.buttonsXO[i].getText();
            if(testObj.buttonsXO[i].getText().equals("<html><font color='green'>X</font></html>")){
                xNum++;
            }
            else if(testObj.buttonsXO[i].getText().equals("<html><font color='blue'>O</font></html>")){
                oNum++;
            }
            else{
                continue;
            }
        }
        Assert.assertTrue(oNum == 1 && xNum == 1);
    }

    /**
     * 中等人机下棋测试
     * @throws Exception
     */
    @Test
    public void MediumPCAfterPlayerTest() throws Exception {
        testObj = new PlayWindow(2);
        this.setUp();
        /* 玩家下子之后人机能够下子，模拟玩家下子 */
        exp = false;
        ActionEvent e = new ActionEvent(testObj.buttonsXO[0],0,"test");
        testObj.actionPerformed(e);
        /* 模拟一次下子之后，PC在actionPerformed中会下子，所以o和x的数量都会是1 */
        int oNum,xNum;
        oNum=xNum=0;
        // 统计o和x的数量
        for(int i = 0; i < 9; i++){
            String s = testObj.buttonsXO[i].getText();
            if(testObj.buttonsXO[i].getText().equals("<html><font color='green'>X</font></html>")){
                xNum++;
            }
            else if(testObj.buttonsXO[i].getText().equals("<html><font color='blue'>O</font></html>")){
                oNum++;
            }
            else{
                continue;
            }
        }
        Assert.assertTrue(oNum == 1 && xNum == 1);
    }

    /**
     * 困难人机下棋测试
     * @throws Exception
     */
    @Test
    public void HardPCAfterPlayerTest() throws Exception {
        testObj = new PlayWindow(3);
        this.setUp();
        /* 玩家下子之后人机能够下子，模拟玩家下子 */
        exp = false;
        ActionEvent e = new ActionEvent(testObj.buttonsXO[0],0,"test");
        testObj.actionPerformed(e);
        /* 模拟一次下子之后，PC在actionPerformed中会下子，所以o和x的数量都会是1 */
        int oNum,xNum;
        oNum=xNum=0;
        // 统计o和x的数量
        for(int i = 0; i < 9; i++){
            String s = testObj.buttonsXO[i].getText();
            if(testObj.buttonsXO[i].getText().equals("<html><font color='green'>X</font></html>")){
                xNum++;
            }
            else if(testObj.buttonsXO[i].getText().equals("<html><font color='blue'>O</font></html>")){
                oNum++;
            }
            else{
                continue;
            }
        }
        Assert.assertTrue(oNum == 1 && xNum == 1);
    }

    /**
     * 轮流下棋测试
     * @throws Exception
     */
    @Test
    public void TakeTurnsTest() throws Exception {
        testObj = new PlayWindow(1);
        this.setUp();
        /* 玩家下子之后人机能够下子，模拟玩家下子 */
        ActionEvent e = new ActionEvent(testObj.buttonsXO[0],0,"test");
        testObj.actionPerformed(e);
        /* 模拟一次下子之后，PC在actionPerformed中会下子，所以o和x的数量都会是1 */
        int oNum,xNum;
        oNum=xNum=0;
        // 统计o和x的数量
        for(int i = 0; i < 9; i++){
            String s = testObj.buttonsXO[i].getText();
            if(testObj.buttonsXO[i].getText().equals("<html><font color='green'>X</font></html>")){
                xNum++;
            }
            else if(testObj.buttonsXO[i].getText().equals("<html><font color='blue'>O</font></html>")){
                oNum++;
            }
            else{
                continue;
            }
        }
        Assert.assertTrue(oNum == 1 && xNum == 1);

        /* 模拟玩家下子 */
        int index = 0;
        /* 寻找可下子的地方 */
        for(index = 0; index < 9; index++){
            if(testObj.buttonsXO[index].getText().equals("")) {
                break;
            }
        }
        e.setSource(testObj.buttonsXO[index]);
        testObj.actionPerformed(e);
        // 统计o和x的数量
        xNum = oNum = 0;
        for(int i = 0; i < 9; i++){
            String s = testObj.buttonsXO[i].getText();
            if(testObj.buttonsXO[i].getText().equals("<html><font color='green'>X</font></html>")){
                xNum++;
            }
            else if(testObj.buttonsXO[i].getText().equals("<html><font color='blue'>O</font></html>")){
                oNum++;
            }
            else{
                continue;
            }
        }
        Assert.assertTrue(oNum == 2 && xNum == 2);
    }
}
