//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame {
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        String time;
        String day;
        String date;

        JButton localGMT;
        JButton militaryTime;
        boolean isMilitary = false;
        boolean isGMT = false;

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(350, 220);
            this.setResizable(false);
    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            militaryTime = new JButton("Military/Standard");
            militaryTime.addActionListener(this :: timeZone);
    
    
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.add(militaryTime);
            this.add(localGMT);
            this.setVisible(true);
    
            setTimer();
        }

        private void timeZone(ActionEvent actionEvent){
            if(isGMT){
                timeFormat.setTimeZone(TimeZone.getDefault());
                isGMT = false;
            } else {
                timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                isGMT = true;
            }
        }

        public void militaryStandard(ActionEvent actionEvent){
            if(isMilitary){
                timeFormat = new SimpleDateFormat("hh:mm:ss a");
                isMilitary = false;
            } else {
                timeFormat = new SimpleDateFormat("HH:mm:ss ");
                isMilitary = true;
            }
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);
        }
    
        public void setTimer() {
            while (true) {
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);
    
                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);
    
                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);
    
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        public static void main(String[] args) {
            new SimpleClock();
        }
    }
