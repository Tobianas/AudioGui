package com.company;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class AudioLogic {

    Clip clip1;
    AudioInputStream aduioStream1;
    FloatControl gainControl1;

    AudioLogic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        //File Reading

        File file1 = new File("The Weeknd - Save Your Tears (online-audio-converter.com).wav");
        aduioStream1 = AudioSystem.getAudioInputStream(file1);
        clip1 = AudioSystem.getClip();
        clip1.open(aduioStream1);
        gainControl1 = (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl1.setValue(-35f); // gain nastavený na -35db aby nikomu nezničil omylom uši pri zapnutí

    }


    public void Play() {
        clip1.start();
    }
    public void Pause() {
        clip1.stop();
    }
    public void Stop() {
        clip1.setMicrosecondPosition(0);
        clip1.stop();
    }
    public void SetVolume(int volume){
        //Ovládanie hlasitosti je nelineárne
        float Gain = volume * -1;
        gainControl1.setValue(Gain);
    }
    public void SetTime(int value) {
        clip1.setMicrosecondPosition(clip1.getMicrosecondLength()/100*value);
    }

    //Toto malo byť na Update pozícii slidera podľa toho, kde sa Pieseň nachádza... nevedel som to spraviť lebo vždy keď
    //som updatol slider tak aplikácia prešla do "volume.addChangeListener()" čo spôsobilo sekanie piesni (zacyklenie)
/*
    public int SetProgress() {
        return (int) clip1.getMicrosecondLength();
    }


    Timer t = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(clip1.getMicrosecondPosition()>0) {
                //time.setValue((int) (100 * clip.getMicrosecondPosition() / clip.getMicrosecondLength()));
            }
        }
    });
        t.start();
        */

}
