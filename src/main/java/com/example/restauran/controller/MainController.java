package com.example.restauran.controller;

import com.example.restauran.entity.Dishes;
import com.example.restauran.service.DishService;
import com.example.restauran.service.OrderService;
import com.example.restauran.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping
public class MainController {

    private final DishService dishService;
    private final OrderService orderService;


    @GetMapping(value = "/")
    public String mainPage(Model model){
        return mainPagePaging(1,"name","asc", model);
    }


    @GetMapping(value = "page/{pageNo}")
    public String mainPagePaging(@PathVariable("pageNo") int pageNo,
                                 @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir,
                                 Model model) {
        int pageSize = 9;
        Page<Dishes> dishesPage = dishService.findPaginated(pageNo,pageSize, sortField, sortDir);
        List<Dishes> dishes = dishesPage.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", dishesPage.getTotalPages());
        model.addAttribute("totalItems", dishesPage.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("dishes", dishes);
        return "main";
    }

    @GetMapping(value = "/task")
    public String taskList(ModelMap model) {
        model.put("users", orderService.findAll());
        return "task";
    }
}
