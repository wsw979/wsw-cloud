package io.cloud.user.api.controller;


import io.cloud.data.annotation.WswRestController;
import io.cloud.data.primary.IdGenerator;
import io.cloud.exception.result.Result;
import io.cloud.exception.util.R;
import io.cloud.user.api.service.IApiRoleService;
import io.cloud.user.api.service.IApiUserService;
import io.cloud.user.common.entity.ApiRole;
import io.cloud.user.common.entity.ApiUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author wsw
 * @since 2020-06-10
 */
@Slf4j
@AllArgsConstructor
@WswRestController(path = "/api/user", api = "用户", tags = "用户")
public class ApiUserController {

    @Autowired
    private IApiUserService apiUserService;

    @PostMapping("/add")
    public Result add(){
        ApiUser user = new ApiUser();
        user.setIsVip(1);
        apiUserService.save(user);

        ApiUser user1 = new ApiUser();
        long id = IdGenerator.getId();
        user1.setIsVip(1);
        user1.setId(id);
        apiUserService.save(user1);

        return R.success();
    }

    @Autowired
    private IApiRoleService apiRoleService;

    @GetMapping("/select")
    public Result select(@RequestParam Long id){



        ApiUser byId = apiUserService.getById(id);

        ApiRole byId1 = apiRoleService.getById(id);


        return R.success(byId);
    }
}
