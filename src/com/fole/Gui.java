package com.fole;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Gui {
    public static final String NOT = "¬";
    public static final String IMPLIES = "⇒";
    public static final String OR = "∨";
    public static final String AND = "∧";
    public static final String FOR_ALL = "∀";
    public static final String EXISTS = "∃";

    private JTextArea textArea;
    private JButton notButton;
    private JButton impliesButton;
    private JPanel view;
    private JButton existsButton;
    private JButton forAllButton;
    private JButton andButton;
    private JButton orButton;
    private JButton copyButton;
    private JCheckBox replaceCheckBox;

    private boolean replaceMode;

    private Map<String, String> replaceDictionary = new HashMap<>();

    public Gui() {
        replaceDictionary.put("!!", NOT);
        replaceDictionary.put("=>", IMPLIES);
        replaceDictionary.put("||", OR);
        replaceDictionary.put("&&", AND);
        replaceDictionary.put("VV", FOR_ALL);
        replaceDictionary.put("EE", EXISTS);
        replaceMode = replaceCheckBox.isSelected();
        String replaceCheckBoxTooltip = "<html>Replace certain input by its symbol equivalent<br />";
        for (Map.Entry<String, String> entry : replaceDictionary.entrySet())
            replaceCheckBoxTooltip += entry.getKey() + " = " + entry.getValue() + "<br />";
        replaceCheckBoxTooltip += "</html>";
        replaceCheckBox.setToolTipText(replaceCheckBoxTooltip);

        notButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                appendToText(NOT);
            }
        });
        impliesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                appendToText(" " + IMPLIES + " ");
            }
        });
        orButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                appendToText(" " + OR + " ");
            }
        });
        andButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                appendToText(" " + AND + " ");
            }
        });
        forAllButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                appendToText(FOR_ALL);
            }
        });
        existsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                appendToText(EXISTS);
            }
        });
        copyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StringSelection stringSelection = new StringSelection(textArea.getText());
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            }
        });
        replaceCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                replaceMode = replaceCheckBox.isSelected();
            }
        });
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (replaceMode)
                    for (Map.Entry<String, String> s : replaceDictionary.entrySet())
                        if (textArea.getText().contains(s.getKey())) {
                            int caretPosition = textArea.getCaretPosition();
                            textArea.setText(textArea.getText().replace(s.getKey(), s.getValue()));
                            textArea.setCaretPosition(caretPosition - 1);
                        }
            }
        });
    }

    public JPanel getView() {
        return view;
    }

    private void appendToText(String appendText) {
        int caretPosition = textArea.getCaretPosition();
        textArea.insert(appendText, caretPosition);
        textArea.grabFocus();
        textArea.setCaretPosition(caretPosition + appendText.length());
    }


}
