package cn.lanedy.controller;

import cn.lanedy.entity.Role;
import cn.lanedy.entity.User;
import cn.lanedy.service.RoleService;
import cn.lanedy.service.UserService;
import cn.lanedy.utils.JsonVO;
import cn.lanedy.utils.TreeVO;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liyue
 * Time 2019-03-01 9:39
 */
@Controller
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 用户列表
     * @param model
     * @return
     */
    @RequestMapping(value = "findAll")
    public String findAll(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("userList",list);
        return "sys-user/user";
    }

    /**
     * responseBody一般是作用在方法上的，加上该注解表示该方法的返回结果直接写到Http response Body中，常用在ajax异步请求中，
     * 在RequestMapping中 return返回值默认解析为跳转路径，如果你此时想让Controller返回一个字符串或者对象到前台 就会报404 not response的错误。
     * 当加上@ResponseBody注解后不会解析成跳转地址 会解析成相应的json格式的对象 集合 字符串或者xml等直接返回给前台 可以通过 ajax 的“success”：fucntion(data){} data直接获取到。
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findByUsername")
    public User findByUsername(String username){
        User user = userService.findByName(username);
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "create")
    public JsonVO createUser(@RequestBody User user){
        JsonVO vo = new JsonVO(false,"创建失败");
        try {
            userService.create(user);
                vo.setSuccess(true);
                vo.setMsg("创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "update")
    public JsonVO updateUser(@RequestBody User user){
        JsonVO vo = new JsonVO(false,"修改失败");
        try {
            userService.update(user);
                vo.setMsg("修改成功");
                vo.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public JsonVO delete(@RequestParam("id") Long id){
        try{
            userService.delete(id);
            return new JsonVO(true,"删除用户数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new JsonVO(false,"发生未知错误");
        }
    }
    /**
     * 构建一棵用户角色的ZTree树，以JSON格式返回给页面
     * 可以给用户指定角色，但是并不负责指定角色的权限。也就是说：对用户，只需要指定用户拥有的角色，只需要维护：用户-角色表
     * 误点：不可能存在同一个角色拥有不同权限的情况，所以在用户层面上我们仅需要维护用户角色表，不需要维护角色-权限表
     * 格式：
     *
     * @return
     */
    
    @ResponseBody
    @RequestMapping("/getZTreeForUserRoles")
    public List<TreeVO> getTreeForUserRoles() {
        try {
            List<TreeVO> treeList = new ArrayList<TreeVO>();
            List<Role> roleList = roleService.findAll();

            for (Role role : roleList) {
                // 为tree树节点添加数据，节点pid为0的都是父节点，其他为子节点
                if(role.getPid() != null){
                    if (role.getPid() == 0) {
                        treeList.add(new TreeVO(role.getId(), role.getDescription(), true, (long) 0));
                    } else {
                        treeList.add(new TreeVO(role.getId(), role.getDescription(), false, role.getPid()));
                    }
                }
            }
            return treeList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 更新用户角色信息，包含以下参数：
     * <p>
     * id      当前用户的id
     * ids     当前用户的角色的id集合
     * parents 当前节点是否是父节点
     *
     * @param dataMap 以上参数数据的Map集合
     * @return <p>
     * 为什么要用Map接收？
     * 如果想要传递对象(如数组)参数，ajax就必须发送post请求，而post请求传递对象参数就要用JSON.stringify()格式化数据为JSON字符串才能传递；
     * 一旦使用了JSON.stringify()格式化数据，传递的就是JSON字符串，后端必须使用String类型接收，而我们传递的数据中包含了普通变量、数组等多种数据，所以使用使用Map接收，并指定泛型是String类型
     *
     * <p>
     * 前端传递进来的参数在Map中封装的数据结构类似：
     * {0:{key: id, value: {...}}, 1:{key: ids, value: {...}}, 2:{key:pids, value: {...}}, 3:{key:parents, value: {...}}}
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserRoles", method = RequestMethod.POST)
    public JsonVO updateUserRoles(@RequestBody Map<String, Object> dataMap) {
        try {
            // 依次获取到封装在Map中的不同对象的集合数据。每个数组的长度都是相通的，因为他们代表同一个节点的不同数据，
            Long id = Long.valueOf((String) dataMap.get("id")); //当前用户的id
            ArrayList ids = (ArrayList) dataMap.get("ids"); //当前用户的角色节点的id集合
            ArrayList parents = (ArrayList) dataMap.get("parents"); //当前用户角色是否是父节点判断的集合
            ArrayList names = (ArrayList) dataMap.get("names"); //当前用户角色的名称集合

            // 更新用户角色即需要维护用户-角色表，前端传来了什么数据？ 1、用户id；2、被选中的角色Id。
            // 如何更新用户角色？我们常会想到，根据表的主键update呀，但是，因为页面上展示的数据是后端构建的ZTree实体类JSON数据，其中并不包含表的主键值
            // 所以，这里就采取一种比较极端的方式，先删除此用户所有关联的角色id，再依次关联此用户当前调整的角色信息
            userService.deleteAllUserRoles(id);

            String role_id = ""; //初始化user表中role_id列数据

            //ids和parents的长度永远相同，所以遍历哪个都行
            if (ids.size() == 1){
                //说明只有一个节点，且这个节点被选中了，只能证明这个用户角色没有子节点，应该与用户关联
                userService.correlationRoles(id, Long.valueOf(String.valueOf(ids.get(0))));
                role_id = "[" + String.valueOf(names.get(0)) + "]";
            }else{
                for (int i = 0; i < ids.size(); i++) {
                    if (!(boolean) parents.get(i)) {
                        //不是父节点，才给此用户关联，用户关联的是子节点，不是父节点
                        userService.correlationRoles(id, Long.valueOf(String.valueOf(ids.get(i))));

                        //更新user表中role_id的数据
                        role_id += "[" + String.valueOf(names.get(i)) + "]";
                    }
                }
            }
            //单独更新user表中role_id列数据
            User user = new User();
            user.setRoleId(role_id);
            user.setId(id);
            user.setLocked(null);
            userService.update(user);

            System.out.println(role_id);
            System.out.println(dataMap);
            return new JsonVO(true, "更新用户角色信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonVO(false, "发生未知错误");
        }
    }

    /**
     * 根据用户名查找其角色
     *
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/findRoles")
//    @RequiresRoles(value = {"admin"}, logical = Logical.OR)
//    @RequiresPermissions(value = {"role:view", "role:*"}, logical = Logical.OR)
    public List<Role> findRoles(String username) {
        return userService.findRoles(username);
    }

}
