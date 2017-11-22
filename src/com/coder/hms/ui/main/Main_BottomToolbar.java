/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.entities.Hotel;
import com.coder.hms.utils.GetLiveWeather;

public class Main_BottomToolbar {

    private final JToolBar toolBar;
    private final JLabel userIconLabel, userLabel, dateIconLabel, dateLabel, currencyUsdIcon,
            currencyUsdLabel, weatherLabel, currencyEuroIcon, currencyEuroLabel, currencyPoundIcon,
            currencyPoundLabel, hotelIconLabel, hotelNameLabel, weatherIconLabel;
    private final GetLiveWeather liveWeather;
    private final JCheckBox checkBox;

    public Main_BottomToolbar() {

        toolBar = new JToolBar();
        toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        toolBar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        toolBar.setPreferredSize(new Dimension(1200, 25));
        toolBar.setMinimumSize(new Dimension(800, 25));
        toolBar.setAutoscrolls(true);
        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        userIconLabel = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_user.png")));
        toolBar.add(userIconLabel);

        //initialize weather api for use.
        liveWeather = new GetLiveWeather();
        
        userLabel = new JLabel();
        userLabel.setMaximumSize(new Dimension(160, 19));
        userLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
        userLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        userLabel.setHorizontalAlignment(SwingConstants.LEFT);
        toolBar.add(userLabel);
        toolBar.addSeparator();

        dateIconLabel = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_calendar.png")));
        toolBar.add(dateIconLabel);

        dateLabel = new JLabel("");
        dateLabel.setMaximumSize(new Dimension(160, 19));
        dateLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
        dateLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
        toolBar.add(dateLabel);
        toolBar.addSeparator();

        currencyUsdIcon = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_currency.png")));
        toolBar.add(currencyUsdIcon);

        currencyUsdLabel = new JLabel("");
        currencyUsdLabel.setMaximumSize(new Dimension(160, 19));
        currencyUsdLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        currencyUsdLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currencyUsdLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
        toolBar.add(currencyUsdLabel);
        toolBar.addSeparator();

        currencyEuroIcon = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_currency_euro.png")));
        toolBar.add(currencyEuroIcon);

        currencyEuroLabel = new JLabel("");
        currencyEuroLabel.setMaximumSize(new Dimension(160, 19));
        currencyEuroLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        currencyEuroLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currencyEuroLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
        toolBar.add(currencyEuroLabel);
        toolBar.addSeparator();

        currencyPoundIcon = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_currency_pound.png")));
        toolBar.add(currencyPoundIcon);

        currencyPoundLabel = new JLabel("");
        currencyPoundLabel.setMaximumSize(new Dimension(160, 19));
        currencyPoundLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        currencyPoundLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currencyPoundLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
        toolBar.add(currencyPoundLabel);
        toolBar.addSeparator();

        hotelIconLabel = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/login_hotel.png")));
        toolBar.add(hotelIconLabel);

        hotelNameLabel = new JLabel("");
        hotelNameLabel.setMaximumSize(new Dimension(160, 19));
        hotelNameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        hotelNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        hotelNameLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
        toolBar.add(hotelNameLabel);
        toolBar.addSeparator();

        checkBox = new JCheckBox("");
        checkBox.setToolTipText("Enable local weather");
        checkBox.addItemListener(showWeather());
        toolBar.add(checkBox);

        weatherIconLabel = new JLabel(new ImageIcon(getClass().getResource("/com/coder/hms/icons/toolbar_weather.png")));
        toolBar.add(weatherIconLabel);

        weatherLabel = new JLabel("");
        weatherLabel.setPreferredSize(new Dimension(160, 19));
        weatherLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        weatherLabel.setHorizontalAlignment(SwingConstants.LEFT);
        weatherLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
        weatherLabel.setEnabled(false);
        toolBar.add(weatherLabel);

    }

    private ItemListener showWeather() {
        final ItemListener listener = new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBox.isSelected() == true) {

                    final HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
                    final Hotel hotel = hotelDaoImpl.getHotel();
                    final String city = liveWeather.getCurrentLocationWeather(hotel.getCity());
                    weatherLabel.setEnabled(true);
                    weatherLabel.setText(city);

                } else {
                    weatherLabel.setEnabled(false);
                    weatherLabel.setText("");
                }

            }
        };

        return listener;
    }

    protected JToolBar getToolBar() {
        return toolBar;
    }

    protected void setUserLabelText(String yourText) {

        userLabel.setText(" " + yourText);
    }

    protected void setDateLabelText(String yourText) {

        if (yourText.length() <= 0 || yourText.equals("")) {
            final LocalDate today = LocalDate.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY EEEE");

            dateLabel.setText(" " + dtf.format(today));
        } else {
            dateLabel.setText(yourText);
        }
    }

    protected void sethotelNameLabelText(String hoteName) {
        hotelNameLabel.setText(" " + hoteName);
    }

    protected void setUsdLabelText(String rate) {
        this.currencyUsdLabel.setText(rate);
    }

    protected void setEuroLabelText(String rate) {
        this.currencyEuroLabel.setText(rate);
    }

    protected void setPoundLabelText(String rate) {
        this.currencyPoundLabel.setText(rate);
    }

}
