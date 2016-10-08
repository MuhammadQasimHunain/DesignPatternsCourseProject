package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Time {

    private JLabel label;
    Timer countdownTimer;
    int Timerem;

    public Time(JLabel passedLabel) {
        setCountdownTimer(1000);
        setLabel(passedLabel);
        setTimeRemaining();
    }

	private void setTimeRemaining() {
		Timerem = Main.timeRemaining;
	}

	private void setCountdownTimer(int delay) {
		countdownTimer = new Timer(delay, new CountdownTimerListener());
	}

	private void setLabel(JLabel passedLabel) {
		this.label = passedLabel;
	}

    public void startTimer() {
        countdownTimer.start();
    }

    public void resetTimer() {
        setTimeRemaining();
    }

    //A function that is called after every second. It updates the timer and takes other necessary actions
    class CountdownTimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int min, sec;
            if (Timerem > 0) {
                min = getMinutes();
                sec = getSeconds();
                label.setText(String.valueOf(min) + ":" + (sec >= 10 ? String.valueOf(sec) : "0" + String.valueOf(sec)));
                Timerem--;
            } else {
                label.setText("Time's up!");
                resetTimer();
                startTimer();
                Main.Mainboard.changeChance();
            }
        }

		private int getSeconds() {
			return Timerem % 60;
		}

		private int getMinutes() {
			return Timerem / 60;
		}
    }
}
