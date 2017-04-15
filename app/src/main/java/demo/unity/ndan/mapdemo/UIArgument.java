package demo.unity.ndan.mapdemo;


import java.io.Serializable;

/**
 * Created by Admin on 4/15/2017.
 */

public class UIArgument implements Serializable {
    String mbutton1Text, mButton2Text;
    public UIArgument(String button1, String button2) {
        mbutton1Text = button1;
        mButton2Text = button2;
    }
}
