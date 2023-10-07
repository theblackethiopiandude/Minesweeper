package GameComponents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clock implements ActionListener {
    private int elapsedTime_millisecond = 0 , seconds = 0, minutes = 0, hour = 0;
    private final JLabel TIME_LABEL;
    private boolean minute_bound_updated = false, hour_bound_updated = false;
    private final static int ONE_HOUR_IN_MILLISECOND, ONE_MINUTE_IN_MILLISECOND, ONE_SECOND_IN_MILLISECOND;
    static {
        ONE_SECOND_IN_MILLISECOND = 1000;
        ONE_MINUTE_IN_MILLISECOND = 60000;
        ONE_HOUR_IN_MILLISECOND = 3600000;
    }

    public Clock(JLabel time_label){
        this.TIME_LABEL = time_label;
        Timer timer = new Timer(ONE_SECOND_IN_MILLISECOND, this);
        timer.start();
    }
    private void refreshLabel(){
        String seconds_string = String.format("%02d", seconds);
        String hours_string = String.format("%01d", hour);
        String minutes_string;
        if (minutes >= 10 || hour >= 1){
            minutes_string = String.format("%02d", minutes);
            if (!minute_bound_updated){
                TIME_LABEL.setBounds(TIME_LABEL.getBounds().x - 4, TIME_LABEL.getBounds().y, TIME_LABEL.getBounds().width, TIME_LABEL.getBounds().height);
                minute_bound_updated = true;
            }
        }else {
            minutes_string = String.format("%01d", minutes);
        }

        if (hour >= 1){
            TIME_LABEL.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
            if (!hour_bound_updated){
                TIME_LABEL.setBounds(TIME_LABEL.getBounds().x - 4, TIME_LABEL.getBounds().y, TIME_LABEL.getBounds().width, TIME_LABEL.getBounds().height);
                hour_bound_updated = true;
            }
        }else {
            TIME_LABEL.setText(minutes_string + ":" + seconds_string);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        elapsedTime_millisecond += ONE_SECOND_IN_MILLISECOND;
        hour = (elapsedTime_millisecond / ONE_HOUR_IN_MILLISECOND);
        minutes = (elapsedTime_millisecond / ONE_MINUTE_IN_MILLISECOND) % 60;
        seconds = (elapsedTime_millisecond / ONE_SECOND_IN_MILLISECOND) % 60;
        refreshLabel();
    }
}
