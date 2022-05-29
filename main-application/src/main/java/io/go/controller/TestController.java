package io.go.controller;

import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    @GetMapping("/routines")
    public String routines(@RequestParam(value = "theme", required = false) String theme, Model model) {
        model.addAttribute("theme", Objects.requireNonNullElse(theme, "light"));
        return "routine/main";
    }

    @GetMapping("/records")
    public String records(@RequestParam(value = "theme", required = false) String theme, Model model) {
        model.addAttribute("theme", Objects.requireNonNullElse(theme, "light"));
        return "record/main";
    }

    @GetMapping("/statistics")
    public String statistics(@RequestParam(value = "theme", required = false) String theme, Model model) {
        model.addAttribute("theme", Objects.requireNonNullElse(theme, "light"));
        return "statistic/main";
    }

    @GetMapping("/record-exercise")
    public String exercise(@RequestParam(value = "theme", required = false) String theme, Model model) {
        model.addAttribute("theme", Objects.requireNonNullElse(theme, "light"));
        return "exercise/record";
    }
}
