package cn.codemao.research.refactor.controller;

import cn.codemao.research.refactor.domain.entity.User;
import cn.codemao.research.refactor.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author switch
 * @since 2020/11/12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getByUserId(@PathVariable("userId") Integer userId) {
        return userService.getByUserId(userId);
    }

    @GetMapping("/age/{min}/{max}")
    public List<User> getByAge(@PathVariable("min") Integer min, @PathVariable("max") Integer max) {
        return userService.getByAgeRange(min, max);
    }

    @GetMapping("/age/user_id/{min}/{max}")
    public List<Integer> getUserIdByAge(@PathVariable("min") Integer min, @PathVariable("max") Integer max) {
        return userService.getUserIdsByAgeRange(min, max);
    }
}
