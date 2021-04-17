package test.com.houarizegai.TicTacToc;

import com.houarizegai.TicTacToc.MainWindow;
import com.houarizegai.TicTacToc.PlayWindow;

public class DifficultySelection {//测试用例TC020301 TC020302 TC020303
    PlayWindow testObjEasy = null;
    PlayWindow testObjMedium = null;
    PlayWindow testObjHard = null;
    public DifficultySelection() {
    }

    public void createObject() {//此测试为双人模式
        this.testObjEasy = new PlayWindow(1);
        this.testObjMedium = new PlayWindow(2);
        this.testObjHard = new PlayWindow(3);
    }
    public void destroyObject() {
        this.testObjEasy = null;
        this.testObjMedium = null;
        this.testObjHard = null;
    }

    public String recordTestMainWin(boolean testResult, int expect, int actual) {
        String output = "";
        if (testResult) {
            output = output + "PASS.";
        } else {
            output = output + "FAIL. 出现游戏界面结果：" +actual+ "，预期：" + expect ;
        }

        return output;
    }

    public void testEasy() throws InterruptedException {//测试用例TC020301
        this.createObject();
        int actual;
        int expected = 1;
        boolean testResult=false;
        while (true) {
            Thread.sleep(1000);
            if (this.testObjEasy.CHOIX_FRIEND == 0)
            {
                actual = this.testObjEasy.CHOIX_EASY;
                expected = 1;
                if (actual == expected) testResult=true;
                String output = this.recordTestMainWin(testResult, expected, actual);
                System.out.println(output);
                this.destroyObject();
                break;
            }
        }
    }

    public void testMedium() throws InterruptedException {//测试用例TC020301
        this.createObject();
        int actual;
        int expected = 1;
        boolean testResult=false;
        while (true) {
            Thread.sleep(1000);
            if (this.testObjMedium.CHOIX_FRIEND == 0)
            {
                actual = this.testObjMedium.CHOIX_EASY;
                expected = 2;
                if (actual == expected) testResult=true;
                String output = this.recordTestMainWin(testResult, expected, actual);
                System.out.println(output);
                this.destroyObject();
                break;
            }
        }
    }

    public void testHard() throws InterruptedException {//测试用例TC020301
        this.createObject();
        int actual;
        int expected = 1;
        boolean testResult=false;
        while (true) {
            Thread.sleep(1000);
            if (this.testObjHard.CHOIX_FRIEND == 0)
            {
                actual = this.testObjHard.CHOIX_EASY;
                expected = 3;
                if (actual == expected) testResult=true;
                String output = this.recordTestMainWin(testResult, expected, actual);
                System.out.println(output);
                this.destroyObject();
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DifficultySelection test = new DifficultySelection();
        test.testEasy();
        test.testMedium();
        test.testHard();
    }
}
