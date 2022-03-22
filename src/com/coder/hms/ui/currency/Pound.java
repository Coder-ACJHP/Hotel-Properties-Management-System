package com.coder.hms.ui.currency;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.Locale;

public class Pound extends Currency{
    public void update(NumberFormat formatter, JFormattedTextField priceField){
        formatter.setCurrency(java.util.Currency.getInstance(Locale.UK));
        NumberFormatter nf = new NumberFormatter(formatter);
        DefaultFormatterFactory dfc = new DefaultFormatterFactory(nf);
        priceField.setFormatterFactory(dfc);
        priceField.revalidate();
        priceField.repaint();
    }
}
