package com.fole;

import javax.swing.*;

public class FirstOrderLogicEditor {

    public static void main(String[] args) {
        JFrame frame = new JFrame("First Order Logic Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(new Gui().getView());

        frame.pack();
        frame.setVisible(true);
    }

}
