package com.vladavekin;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleChatBot extends JFrame implements ActionListener {

    /**  Window  **/
    final String TITLE_OF_PROGRAM = "my Chat";  // название окна
    final int START_LOPCATION = 200;                // начальная позиция
    final int WINDOW_WIDTH = 350;                   // длина окна
    final int WINDOW_HEIGHT = 450;                  // висота окна

    /**    **/
    JTextArea dialogue; //
    JCheckBox ai;       //
    JTextField message; //
    SimpleBot sbot;  //


    public static void main(String[] args) {
        new SimpleChatBot();
    }

    SimpleChatBot(){
        setTitle(TITLE_OF_PROGRAM); // установка заголовка програмы
        setDefaultCloseOperation(EXIT_ON_CLOSE); // завершения програмы по нажанию на Х
        setBounds(START_LOPCATION, START_LOPCATION, WINDOW_WIDTH, WINDOW_HEIGHT); // установка коорденат и розмера окна

        // диалоговое окно
        dialogue = new JTextArea(); // создаем
        dialogue.setLineWrap(true); // строки будут переноситься
        JScrollPane scrollPane = new JScrollPane(dialogue); // создаем склор бар и заворачуем диалого

        // панель
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        ai = new JCheckBox("AI");
        //ai.doClick();

        message = new JTextField();
        message.addActionListener(this); // вклучаем пролушователь (Listener) actionPerformed

        JButton enter = new JButton("Enter");
        enter.addActionListener(this); // вклучаем пролушователь (Listener) actionPerformed

        // установка элементов в окно
        buttonPanel.add(ai);        // добавляем компонент на панель
        buttonPanel.add(message);   // добавляем компонент на панель
        buttonPanel.add(enter);     // добавляем компонент на панель

        add(BorderLayout.CENTER, scrollPane); // добавляем компонент в окно
        add(BorderLayout.SOUTH, buttonPanel);
        setVisible(true); // видемость окна

        /**  Чат бот  **/
        sbot = new SimpleBot(); // создание

    }

    /** Интерфейс Listener ( пролушователь ) **/
    @Override
    public void actionPerformed(ActionEvent event) {
        if (message.getText().trim().length() > 0) { // читаем текс | удаляем пробели | измеряем розмер
            dialogue.append(message.getText() + "\n"); //
            dialogue.append(TITLE_OF_PROGRAM.substring(0, 7) + ": " +
                            sbot.sayInReturn(message.getText(), ai.isSelected()) + "\n");
        }

        message.setText("");            //
        message.requestFocusInWindow(); //
    }

}
