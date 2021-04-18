package test.houarizegai.TicTacToc;

import  com.houarizegai.TicTacToc.AboutWindow;
import com.sun.org.apache.xpath.internal.operations.Bool;

import static org.junit.Assert.*;

public class AboutWindowTest {
    AboutWindow testObj;
    @org.junit.Test
    public void testOpenURI_True() {
        int[] testData={0,1,2,3};
        testObj=new AboutWindow();
        for(int i=0;i<testData.length;i++){
            System.out.println("Run OpenURI_True Testcase: "+i);
            assertTrue(testObj.openURI(testData[i]));
        }
    }

    @org.junit.Test
    public void  testOpenURI_False(){
        int[] testData={-1,4};
        testObj=new AboutWindow();
        for(int i=0;i<testData.length;i++){
            System.out.println("Run OpenURI_False Testcase: "+i);
            assertFalse(testObj.openURI(testData[i]));
        }
    }
}