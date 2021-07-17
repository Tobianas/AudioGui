package com.company;


import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class MyFrame extends JFrame {

    //inicializácia
    JButton Play, Pause, Stop;
    AudioLogic logic;
    JPanel gif;
    JPanel Bottom;
    JPanel PlayStopPause;
    JPanel SetTime;
    JPanel SetVolume;
    JSlider volume;
    JSlider time;
    //JProgressBar progress;


    MyFrame() {

        try {
            logic = new AudioLogic();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        this.setSize(1280, 720);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //BackgroundImage
        ImageIcon icon = new ImageIcon("music.jpg");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        JLabel label = new JLabel();
        label.setIcon(scaledIcon);

        //Prvky
        gif = new JPanel();
        Bottom = new JPanel();
        Bottom.setLayout(new BorderLayout(2, 0));
        PlayStopPause = new JPanel();
        SetTime = new JPanel();
        SetVolume = new JPanel();
        volume = new JSlider(-6, 80, 38);
        volume.setOpaque(false);
        volume.setInverted(true);
        time = new JSlider(0, 100, 0);
        time.setOpaque(false);
        Play = new JButton();
        Pause = new JButton();
        Stop = new JButton();

        //Buttons
        Play.setFocusable(false);
        Play.setText("▶");
        Play.setFont(new Font("Helvetica", Font.BOLD, 7));
        Pause.setFocusable(false);
        Pause.setText("▐ ▌");
        Pause.setFont(new Font("Helvetica", Font.BOLD, 7));
        Stop.setFocusable(false);
        Stop.setText("▋");
        Stop.setFont(new Font("Helvetica", Font.BOLD, 7));


        //Background Color
        PlayStopPause.setBackground(Color.DARK_GRAY);
        SetVolume.setBackground(Color.DARK_GRAY);
        SetTime.setBackground(Color.GRAY);
        gif.setBackground(Color.BLACK);

        //BorderLayout

        //PlayStopPause.setLayout(new BorderLayout());
        SetTime.setLayout(new BorderLayout(5, 5));
        SetVolume.setLayout(new BorderLayout(5, 5));

        Bottom.add(SetTime, BorderLayout.CENTER);
        Bottom.add(SetVolume, BorderLayout.EAST);
        Bottom.add(PlayStopPause, BorderLayout.WEST);
        PlayStopPause.add(Play, BorderLayout.WEST);
        PlayStopPause.add(Pause, BorderLayout.CENTER);
        PlayStopPause.add(Stop, BorderLayout.EAST);
        gif.add(label);
        SetVolume.add(volume);
        SetTime.add(time);


        //preffered size
        gif.setPreferredSize(new Dimension(500, this.getHeight()));
        PlayStopPause.setPreferredSize(new Dimension(100, 50));
        SetVolume.setPreferredSize(new Dimension(100, 50));
        Bottom.setPreferredSize(new Dimension(100, 50));
        // progress = new JProgressBar(0,100);
        // progress.setValue(10);
        //SetTime.add(progress);
        //progress.setLayout(new BorderLayout());
        //progress.add(time);
        //time.setOpaque(false);

        //ActionListener
        Play.addActionListener(evt -> {
            System.out.println("Start");
            logic.Play();

        });
        Pause.addActionListener(evt -> {
            System.out.println("Pause");
            logic.Pause();

        });
        Stop.addActionListener(evt -> {
            logic.Stop();
            System.out.println("Stop");
        });
        volume.addChangeListener(evt -> {
            logic.SetVolume(volume.getValue());
        });
        time.addChangeListener(evt -> {
            logic.SetTime(time.getValue());
        });

        this.add(Bottom, BorderLayout.SOUTH);
        this.add(gif, BorderLayout.NORTH);
        this.setVisible(true);

    }


}

