package test.com.houarizegai.TicTacToc;

import com.houarizegai.TicTacToc.PlayWindow;

import java.awt.event.ActionEvent;

public class IsExchange {
    PlayWindow testObj = null;
    public IsExchange() {
    }

    public void createObject() {
        this.testObj = new PlayWindow(0);//此测试为双人模式
    }

    public void destroyObject() {
        this.testObj = null;
    }

    public boolean verify(boolean expect, boolean actual) {
        return expect == actual;
    }

    public String recordTestExchange(boolean testResult, boolean expect, boolean actual) {
        String output = "";
        if (testResult) {
            output = output + "PASS.";
        } else {
            output = output + "FAIL. 下棋人：" + "，预期：" + expect + "，实际输出：" + actual;
        }

        return output;
    }

    public void testExchange1() throws InterruptedException {
        this.createObject();
        boolean actual=true;
        boolean expected=true;
        boolean testResult=true;
        while (true) {
            Thread.sleep(100);
            if (this.testObj.nextplay == false && this.testObj.whowin == 1)//进入下一局且玩家一赢
            {
                System.out.println("111");
                actual = this.testObj.player1;
                expected = false;
                testResult = this.verify(expected, actual);
                String output = this.recordTestExchange(testResult, expected, actual);
                System.out.println(output);
                this.destroyObject();
                break;
            }
        }
    }
    public void testExchange2() throws InterruptedException {
        this.createObject();
        boolean actual=true;
        boolean expected=true;
        boolean testResult=true;
        while (true) {
            Thread.sleep(100);
            if (this.testObj.nextplay == false && this.testObj.whowin == 2)//进入下一局且玩家2赢
            {
                actual = this.testObj.player1;
                expected = true;
                testResult = this.verify(expected, actual);
                String output = this.recordTestExchange(testResult, expected, actual);
                System.out.println(output);
                this.destroyObject();
                break;
            }
        }
    }
    public void testExchange3() throws InterruptedException {
        this.createObject();
        boolean actual=true;
        boolean expected=true;
        boolean testResult=true;
        while (true) {
            Thread.sleep(100);
            if (this.testObj.nextplay == false && this.testObj.whowin == 3)//进入下一局且平局
            {
                actual = this.testObj.player1;
                expected = false;
                testResult = this.verify(expected, actual);
                String output = this.recordTestExchange(testResult, expected, actual);
                System.out.println(output);
                this.destroyObject();
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IsExchange test = new IsExchange();
        //test.testExchange1();
        //test.testExchange2();
        //test.testExchange3();
    }
}
