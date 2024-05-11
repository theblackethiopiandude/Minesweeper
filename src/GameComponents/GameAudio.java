package GameComponents;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
public abstract class GameAudio {
    private static final File FLAG_ADDED, FLAG_REMOVED;
    private static final File FIRST_CLICK;
    private static final File ONE, TWO, THREE, FOUR;
    private static final File FIVE, SIX, SEVEN, EIGHT;
    private static Clip CURRENT_CLIP;

    static {
        FLAG_ADDED = new File("assets/audios/Flag Sound/Flag Added.wav");
        FLAG_REMOVED = new File("assets/audios/Flag Sound/Flag Removed.wav");

        FIRST_CLICK = new File("assets/audios/Reveal Sound/First Click.wav");

        ONE = new File("assets/audios/Reveal Sound/1.wav");
        TWO = new File("assets/audios/Reveal Sound/2.wav");
        THREE = new File("assets/audios/Reveal Sound/3.wav");
        FOUR = new File("assets/audios/Reveal Sound/4.wav");
        FIVE = new File("assets/audios/Reveal Sound/5.wav");
        SIX = new File("assets/audios/Reveal Sound/6.wav");
        SEVEN = new File("assets/audios/Reveal Sound/7.wav");
        EIGHT = new File("assets/audios/Reveal Sound/8.wav");
    }

    public static void flagAdded(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(FLAG_ADDED);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static void flagRemoved(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(FLAG_REMOVED);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static void firstClick(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(FIRST_CLICK);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static void revealSound(int adjacent){
        File streamFile;

        if (adjacent == 1)
            streamFile = ONE;
        else if (adjacent == 2)
            streamFile = TWO;
        else if (adjacent == 3)
            streamFile = THREE;
        else if (adjacent == 4)
            streamFile = FOUR;
        else if (adjacent == 5)
            streamFile = FIVE;
        else if (adjacent == 6)
            streamFile = SIX;
        else if (adjacent == 7)
            streamFile = SEVEN;
        else
            streamFile = EIGHT;

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(streamFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static void winSound(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/audios/Sound After Winning/Sound After Winning.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            CURRENT_CLIP = clip;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void loseSound(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/audios/Sound After Failing/Sound After Failing.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            CURRENT_CLIP = clip;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void stopAudio(){
        if (CURRENT_CLIP != null && CURRENT_CLIP.isRunning())
            CURRENT_CLIP.stop();
    }
}
