package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Time {

    private JLabel label;
    Timer countdownTimer;
    int timeRemaining;

    public Time(JLabel passedLabel) {
        setCountdownTimer(1000);
        setLabel(passedLabel);
        setTimeRemaining();
    }

	private void setTimeRemaining() {
		this.timeRemaining = Main.timeRemaining;
	}

	private void setCountdownTimer(int delay) {
		this.countdownTimer = new Timer(delay, new CountdownTimerListener());
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
            int minutes, seconds;
            if (timeRemaining > 0) {
                minutes = getMinutes();
                seconds = getSeconds();
                setTextIfTimeRemains(minutes, seconds);
                timeRemaining--;
            } else {
                setTextIfTimeIsUp();
                resetTimer();
                startTimer();
                Main.Mainboard.changeChance();
            }
        }

		void setTextIfTimeIsUp() {
			label.setText("Time's up!");
		}

		void setTextIfTimeRemains(int minutes, int seconds) {
			label.setText(String.valueOf(minutes) + ":" + (seconds >= 10 ? String.valueOf(seconds) : "0" + String.valueOf(seconds)));
		}

		private int getSeconds() {
			return timeRemaining % 60;
		}

		private int getMinutes() {
			return timeRemaining / 60;
		}
    }
}
