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
public class ClearTest {
    PlayWindow testObj;
    boolean exp;
    String[] strList;
    int choixLevel;

    public ClearTest(int CHOIX_LEVEL, String str0, String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean exp_) {
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
        for (int i = 0; i < 9; i++) {
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
                /* 无子时清空 */
                {0, "", "", "", "", "", "", "", "", "", false},
                /* 四子时清空 */
                {0, "X", "", "O", "", "X", "", "", "O", "", false},
                /* 全满时清空 */
                {0, "O", "X", "X", "X", "O", "O", "O", "X", "X", false}
        });
    }

    @Test
    public void PlayChess() {
        testObj.clear();

        for(int i = 0; i < 9; i++) {
            Assert.assertTrue(testObj.buttonsXO[i].getText().equals(""));
        }
    }
}
