package com.coder.hms.ui.currency;

import javax.swing.*;
import java.text.NumberFormat;

public abstract class Currency {

    public abstract void update(NumberFormat formatter, JFormattedTextField priceField);
}
