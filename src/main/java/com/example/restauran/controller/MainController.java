package com.example.restauran.controller;

import com.example.restauran.converters.UsersConverter;
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

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping
public class MainController {

    private final DishService dishService;
    private final OrderService orderService;
    private final UsersService usersService;
    private final UsersConverter usersConverter;


    @GetMapping(value = "/")
    public String mainPage(Model model){
        return mainPagePaging(1, model);
    }


    @GetMapping(value = "page/{pageNo}")
    public String mainPagePaging(@PathVariable("pageNo") int pageNo, Model model) {
        int pageSize = 9;
        Page<Dishes> dishesPage = dishService.findPaginated(pageNo,pageSize);
        List<Dishes> dishes = dishesPage.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", dishesPage.getTotalPages());
        model.addAttribute("totalItems", dishesPage.getTotalElements());
        model.addAttribute("dishes", dishes);
        return "main";
    }

    @GetMapping(value = "/task")
    public String taskList(ModelMap model) {
        model.put("users", usersService.findAll());
        return "task";
    }
}
