package sk.best.newtify.web.gui.component.widget;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sk.best.newtify.api.NamedaysApi;
import sk.best.newtify.api.dto.NameDayDTO;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marek Urban
 * Copyright © 2022 BEST Technická univerzita Košice.
 * All rights reserved.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class NameDayWidgetComponent extends FlexLayout {

    private static final long              serialVersionUID    = 1414727226197592073L;

    private final NamedaysApi namedaysApi;

    public NameDayWidgetComponent(NamedaysApi namedaysApi) {
        this.namedaysApi = namedaysApi;
    }

    @PostConstruct
    private void init() {
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);
        NameDayDTO data = namedaysApi.retrieveNameDay(currentMonth, currentDay).getBody();

        createWidgetIcon();
        createDatePart(currentYear, currentMonth, currentDay);
        createNameDayPart(data);

        this.getStyle()
                .set("background", "var(--lumo-contrast-10pct)")
                .set("border-radius", "1em");
        this.setFlexDirection(FlexDirection.COLUMN);
        this.setAlignItems(Alignment.CENTER);
        this.setWidthFull();
    }

    private void createWidgetIcon() {
        Icon calendarIcon = VaadinIcon.CALENDAR_USER.create();
        calendarIcon.setSize("5em");
        calendarIcon.setColor("var(--lumo-contrast-color)");

        this.add(calendarIcon);
    }

    private void createDatePart(int currentYear, int currentMonth, int currentDay) {
        H4 todayDateTitle = new H4("Today is");
        todayDateTitle.getStyle()
                .set("color", "var(--lumo-contrast-color)");

        Map<Long, String> ordinalNumbers = new HashMap<>(42);
        ordinalNumbers.put(1L, "1st");
        ordinalNumbers.put(2L, "2nd");
        ordinalNumbers.put(3L, "3rd");
        ordinalNumbers.put(21L, "21st");
        ordinalNumbers.put(22L, "22nd");
        ordinalNumbers.put(23L, "23rd");
        ordinalNumbers.put(31L, "31st");
        for (long d = 1; d <= 31; d++) {
            ordinalNumbers.putIfAbsent(d, "" + d + "th");
        }
        DateTimeFormatter DAY_TIME_FORMATTER = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH, ordinalNumbers)
                .appendPattern(" MMM")
                .appendPattern(" yyyy")
                .toFormatter();
        H3 todayDateValue = new H3(DAY_TIME_FORMATTER.format(
                LocalDate.of(currentYear, currentMonth, currentDay))
        );
        todayDateValue.getStyle()
                .set("color", "white")
                .set("font-style", "italic")
                .set("margin", "0");

        this.add(todayDateTitle, todayDateValue);
    }

    private void createNameDayPart(NameDayDTO data) {
        Label nameDayLabel = new Label("Name day has");
        nameDayLabel.getStyle()
                .set("color", "var(--lumo-contrast-color)")
                .set("margin", "1em 0 0 0");

        H3 nameDayValue = new H3(data != null ? data.getName() : "Unknown");
        nameDayValue.getStyle()
                .set("color", "white")
                .set("font-style", "italic")
                .set("margin", "0 0 1em 0");

        this.add(nameDayLabel, nameDayValue);
    }

}