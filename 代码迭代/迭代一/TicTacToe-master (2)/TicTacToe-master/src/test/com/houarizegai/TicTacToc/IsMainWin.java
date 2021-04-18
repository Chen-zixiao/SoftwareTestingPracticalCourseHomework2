package test.com.houarizegai.TicTacToc;

import com.houarizegai.TicTacToc.MainWindow;
import com.houarizegai.TicTacToc.PlayWindow;

public class IsMainWin {  //测试用例4
    MainWindow testObj = null;
    public IsMainWin() {
    }

    public void createObject() {
        this.testObj = new MainWindow();//此测试为双人模式
    }

    public void destroyObject() {
        this.testObj = null;
    }

    public boolean verify(boolean expect, boolean actual) {
        return expect == actual;
    }

    public String recordTestMainWin(boolean testResult, boolean expect, boolean actual) {
        String output = "";
        if (testResult) {
            output = output + "PASS.";
        } else {
            output = output + "FAIL. 出现游戏界面结果：" + "，预期：" + expect + "，实际输出：" + actual;
        }

        return output;
    }

    public void testMainWin() throws InterruptedException {
        this.createObject();
        boolean actual=true;
        boolean expected=true;
        boolean testResult=true;
        while (true) {
            Thread.sleep(1000);
            if (this.testObj.isOn == true)
            {
                actual = this.testObj.isOpen;
                expected = true;
                testResult = this.verify(expected, actual);
                String output = this.recordTestMainWin(testResult, expected, actual);
                System.out.println(output);
                this.destroyObject();
                break;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        IsMainWin test = new IsMainWin();
        test.testMainWin();
    }
}
