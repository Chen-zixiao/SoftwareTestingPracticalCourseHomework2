package test.com.houarizegai.TicTacToc;

import com.houarizegai.TicTacToc.PlayWindow;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * 该类测试玩家对战中的Reset功能
 */
@RunWith(Parameterized.class)
public class PlayersResetTest {
    static PlayWindow testObj;
    boolean exp, player1Win, judge;
    String[] strList;

    public PlayersResetTest(String str0, String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean exp_, boolean player1Win_, boolean judge_) {
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
        testObj = new PlayWindow(0);
    }

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < 9; i++) {
            testObj.buttonsXO[i].setText(strList[i]);
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void desWindow() throws Exception {
        testObj = null;
    }

    @Parameterized.Parameters()
    public static Collection testDataset() {
        return Arrays.asList(new Object[][]{
                /* 无子时，无分数记录 重置 */
                {"", "", "", "", "", "", "", "", "", false, true, true},
                /* 得分，2：1 */
                {"X", "X", "X", "O", "O", "", "", "", "", false, true, false},
                {"X", "X", "X", "O", "O", "", "", "", "", false, true, false},
                {"X", "X", "", "O", "O", "O", "X", "", "", false, false, false},
                /* 无子时，有分数记录 */
                {"X", "", "O", "", "X", "", "", "O", "", false, true, true},
                /* 得分，2：1 */
                {"X", "X", "X", "O", "O", "", "", "", "", false, true, false},
                {"X", "X", "X", "O", "O", "", "", "", "", false, true, false},
                {"X", "X", "", "O", "O", "O", "X", "", "", false, false, false},
                /* 有子未满时，有分数记录 */
                {"O", "X", "X", "X", "O", "O", "O", "X", "", false, true, true},
                /* 得分，2：1 */
                {"X", "X", "X", "O", "O", "", "", "", "", false, true, false},
                {"X", "X", "X", "O", "O", "", "", "", "", false, true, false},
                {"X", "X", "", "O", "O", "O", "X", "", "", false, false, false},
                /* 有子全满时，有分数记录 */
                {"O", "X", "X", "X", "O", "O", "O", "X", "X", false, true, true}
        });
    }

    @Test
    public void PlayChess() {
        testObj.getResult(player1Win);
        if(judge == true) {
            testObj.resetScore();
            Assert.assertTrue(testObj.getxScore() == 0 && testObj.getoScore() == 0);
            for (int i = 0; i < 9; i++) {
                Assert.assertTrue(testObj.buttonsXO[i].getText().equals(""));
            }
        }
    }
}
