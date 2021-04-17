package test.com.houarizegai.TicTacToc;

import com.houarizegai.TicTacToc.PlayWindow;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TurnTest {
    static int choixLevel;
    static PlayWindow testObj;
    /** 期望结果 */
    boolean exp;
    /** 控制赢家是哪一方（原游戏逻辑就是这样，将就用） */
    boolean player1Win;
    /** 是否执行判定（单纯为了增加比分的时候不执行判定） */
    boolean judge;
    String[] strList;

    public TurnTest(int CHOIX_LEVEL, String str0, String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean exp_, boolean player1Win_, boolean judge_) {
        choixLevel = CHOIX_LEVEL;
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
        testObj = new PlayWindow(choixLevel);
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
                {1, "", "", "", "", "", "", "", "", "", true, true, true},
                /* 简单人机在玩家下子后能够下子 */
                {1, "X", "", "", "", "", "", "", "", "", false, true, false},
                {1, "X", "", "", "O", "", "", "X", "", "", false, false, false},
                /* 中等人机在玩家下子后能够下子 */
                {2, "X", "", "", "", "", "", "", "", "", false, true, false},
                {2, "X", "", "", "O", "", "", "X", "", "", false, false, false},
                /* 困难人机在玩家下子后能够下子 */
                {3, "X", "", "", "", "", "", "", "", "", false, true, false},
                {3, "X", "", "", "O", "", "", "X", "", "", false, false, false},
                /* 玩家和人机能够轮流下子 */
                {1, "", "", "", "", "", "", "", "", "", true, true, true},
                {1, "X", "", "", "", "", "", "", "", "", false, true, false}
        });
    }

    @Test
    public void PlayChess() {
        Assert.assertTrue(PlayWindow.isPlayerTurn() == exp);
    }
}
