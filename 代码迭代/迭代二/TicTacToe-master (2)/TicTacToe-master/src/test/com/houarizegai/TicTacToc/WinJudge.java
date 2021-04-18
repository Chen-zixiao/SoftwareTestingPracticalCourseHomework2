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

@RunWith(Parameterized.class)
public class WinJudge {   //胜利判定的三个用例,行列对角线胜利
    PlayWindow testObj;
    boolean exp;
    String[] strList;
    int choixLevel;

    public WinJudge(int CHOIX_LEVEL, String str0, String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean exp_) {
        choixLevel = CHOIX_LEVEL;
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
    }

    @Before
    public void setUp() throws Exception {
        // 初始化测试对象
        this.testObj = new PlayWindow(choixLevel);
        for(int i = 0; i < 9; i++){
            this.testObj.buttonsXO[i].setText(strList[i]);
        }
    }

    @After
    public void tearDown() throws Exception {
        this.testObj = null;
    }

    @Parameterized.Parameters()
    public static Collection testDataset() {
        return Arrays.asList(new Object[][]{
                /* 行三子判定 */
                {0, "X", "X", "X", "", "", "", "", "", "", false},
                {0, "O", "O", "O", "", "", "", "", "", "", false},
                {0, "", "", "", "X", "X", "X", "", "", "", false},
                {0, "", "", "", "O", "O", "O", "", "", "", false},
                {0, "", "", "", "", "", "", "X", "X", "X", false},
                {0, "", "", "", "", "", "", "O", "O", "O", false},

                /* 列三子判定 */
                {0, "X", "", "", "X", "", "", "X", "", "", false},
                {0, "O", "", "", "O", "", "", "O", "", "", false},
                {0, "", "X", "", "", "X", "", "", "X", "", false},
                {0, "", "O", "", "", "O", "", "", "O", "", false},
                {0, "", "", "X", "", "", "X", "", "", "X", false},
                {0, "", "", "O", "", "", "O", "", "", "O", false},

                /* 斜三子判定 */
                {0, "O", "", "", "", "O", "", "", "", "O", false},
                {0, "X", "", "", "", "X", "", "", "", "X", false},
                {0, "", "", "O", "", "O", "", "O", "", "", false},
                {0, "", "", "X", "", "X", "", "X", "", "", false},

                /* 至多1子相连，未胜利 */
                {0, "X", "", "", "", "", "", "", "", "", true},
                {0, "O", "", "", "", "", "", "", "", "", true},
                {0, "X", "", "X", "", "", "", "", "", "", true},
                {0, "O", "", "O", "", "", "", "", "", "", true},
                {0, "X", "", "", "", "", "X", "", "", "", true},
                {0, "O", "", "", "", "", "O", "", "", "", true},
                {0, "X", "", "", "", "", "", "", "", "X", true},
                {0, "O", "", "", "", "", "", "", "", "O", true},
                {0, "X", "", "", "", "", "", "", "", "", true},
                {0, "O", "", "", "", "", "", "", "", "", true},
                {0, "", "", "", "", "", "", "X", "", "", true},
                {0, "", "", "", "", "", "", "O", "", "", true},
                {0, "", "", "", "", "", "", "", "X", "", true},
                {0, "", "", "", "", "", "", "", "O", "", true},

                /* 至多2子相连，未胜利 */
                // 行
                {0, "X", "X", "O", "", "", "", "", "", "", true},
                {0, "O", "O", "X", "X", "", "", "", "", "", true},
                // 列
                {0, "X", "", "", "X", "", "", "O", "", "", true},
                {0, "O", "", "", "O", "", "", "X", "X", "", true},
                // 斜
                {0, "X", "", "", "", "X", "", "", "", "O", true},
                {0, "O", "", "", "", "O", "", "", "X", "X", true}
        });
    }

    @Test
    public void PlayChess() {
        Assert.assertTrue(testObj.getResult(true) == this.exp);
    }
}

