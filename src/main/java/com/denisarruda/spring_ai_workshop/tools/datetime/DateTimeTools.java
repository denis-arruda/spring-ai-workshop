package com.denisarruda.spring_ai_workshop.tools.datetime;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;

public class DateTimeTools {

    @Tool(description = "Get the current date and time in the user's timezone.")
    public String getCurrentDateTime() {
        return java.time.LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }
}
