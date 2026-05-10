package com.resume.controller;

// ===== 导入所需的类 =====
// CrossOrigin: 解决跨域问题的注解（前端和后端端口不同时需要）
import org.springframework.web.bind.annotation.CrossOrigin;
// GetMapping: 处理 GET 请求的注解
import org.springframework.web.bind.annotation.GetMapping;
// RequestMapping: 设置这个Controller的URL前缀
import org.springframework.web.bind.annotation.RequestMapping;
// RestController: 声明这是一个REST风格的控制器，返回值自动转为JSON
import org.springframework.web.bind.annotation.RestController;
// HashMap: 键值对集合，用来存放JSON数据
import java.util.HashMap;
// Map: HashMap的接口类型，Java中推荐用接口类型声明变量
import java.util.Map;
// Arrays: 数组工具类，这里用它的 asList() 方法快速创建列表
import java.util.Arrays;
// List: 列表接口，用来存放多条数据（比如多段工作经历）
import java.util.List;

/**
 * =====================================================
 * ResumeController - 简历数据接口控制器
 * =====================================================
 *
 * 这个类的职责：
 *   接收前端发来的HTTP请求，返回对应的简历数据（JSON格式）
 *
 * 什么是Controller？
 *   在SpringBoot的MVC架构中，Controller是"交通警察"的角色：
 *   前端发来请求 → Controller负责接收 → 处理后返回数据给前端
 *
 * 注解说明：
 *   @RestController
 *     = @Controller + @ResponseBody 的组合
 *     @Controller    : 告诉Spring这个类是控制器
 *     @ResponseBody  : 返回值不是跳转页面，而是直接返回数据（JSON）
 *
 *   @RequestMapping("/api/resume")
 *     这个类下所有接口的URL都以 /api/resume 开头
 *     例如：getPersonalInfo() 的完整URL = /api/resume/info
 *
 *   @CrossOrigin(origins = "*")
 *     解决"跨域"问题
 *     什么是跨域？前端运行在 localhost:5173，后端在 localhost:8080
 *     端口不同 = 跨域，浏览器默认会阻止跨域请求
 *     加上这个注解，后端就会在响应头里告诉浏览器"允许访问"
 *     origins = "*" 表示允许所有来源（开发阶段这样用，上线后要改成具体域名）
 */
@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "*")
public class ResumeController {

    /**
     * -----------------------------------------------
     * 接口1：获取个人基本信息
     * -----------------------------------------------
     * HTTP方法 : GET
     * 完整URL  : http://localhost:8080/api/resume/info
     *
     * 什么是GET请求？
     *   HTTP协议中用来"获取数据"的请求方式
     *   就像在浏览器地址栏输入网址打开页面一样
     *   特点：只读取数据，不修改数据
     *
     * 返回值类型：Map<String, Object>
     *   Map = 键值对集合，类似Python的字典
     *   SpringBoot会自动把Map转成JSON格式返回给前端
     *   例如：{"name": "张三", "title": "Java Developer"}
     * -----------------------------------------------
     */
    @GetMapping("/info")
    public Map<String, Object> getPersonalInfo() {

        // HashMap：Map接口的实现类，用来存储键值对
        // new HashMap<>() 中的 <> 是Java泛型，省略重复类型声明
        Map<String, Object> info = new HashMap<>();

        // put(key, value) : 向Map中添加数据
        // key   = 字符串类型，前端用这个key来取值
        // value = Object类型，可以放字符串、数字、列表等任何类型

        // ===== 请把下面的内容替换成你自己的真实信息！=====
        info.put("name", "小林万益");           // 姓名
        info.put("title", "JAVAバックエンドエンジニア"); // 职位头衔
        info.put("email", "mayaoxi1@icloud.com");     // 联系邮箱
        info.put("location", "Tokyo, Japan");    // 所在城市
        info.put("github", "https://github.com/Kobayashi-Mayoshi"); // GitHub主页
        // 个人简介：用一两句话介绍自己的技术背景和目标
        info.put("summary", "Java开发工程师，目前在日本工作。");

        // return：把这个Map返回给前端
        // SpringBoot的 @RestController 会自动把它序列化成JSON字符串
        return info;
    }

    /**
     * -----------------------------------------------
     * 接口2：获取技能列表
     * -----------------------------------------------
     * HTTP方法 : GET
     * 完整URL  : http://localhost:8080/api/resume/skills
     *
     * 返回值类型：Map<String, Object>
     *   这里的value是List（列表），所以前端收到的JSON长这样：
     *   {
     *     "backend":  ["Java", "SpringBoot", ...],
     *     "frontend": ["HTML", "CSS", ...],
     *     "other":    ["Git", "MySQL", ...]
     *   }
     * -----------------------------------------------
     */
    @GetMapping("/skills")
    public Map<String, Object> getSkills() {

        Map<String, Object> skills = new HashMap<>();

        // Arrays.asList() : 快速把若干个元素创建成一个List列表
        // 比直接 new ArrayList() 然后一个个add()要简洁很多
        List<String> backendSkills = Arrays.asList(
            "Java", "SpringBoot", "Maven", "RESTful API","MYBATIS"
        );

        List<String> frontendSkills = Arrays.asList(
            "HTML", "CSS", "JavaScript", "Vue3"
        );

        List<String> otherSkills = Arrays.asList(
            "Git", "MySQL"
        );

        // 把三个技能分类分别放入Map
        // 前端可以通过 skills.backend / skills.frontend / skills.other 来取值
        skills.put("backend", backendSkills);
        skills.put("frontend", frontendSkills);
        skills.put("other", otherSkills);

        return skills;
    }

    /**
     * -----------------------------------------------
     * 接口3：获取工作/实习经历
     * -----------------------------------------------
     * HTTP方法 : GET
     * 完整URL  : http://localhost:8080/api/resume/experience
     *
     * 返回值类型：List<Map<String, Object>>
     *   List里面装着多个Map，每个Map代表一段工作经历
     *   前端收到的JSON长这样：
     *   [
     *     {"company": "XX公司", "position": "开发工程师", ...},
     *     {"company": "YY公司", "position": "实习生", ...}
     *   ]
     *   注意最外层是 [] 数组，而不是 {} 对象
     * -----------------------------------------------
     */
    @GetMapping("/experience")
    public List<Map<String, Object>> getExperience() {

        // 第一段工作经历
        Map<String, Object> exp1 = new HashMap<>();
        exp1.put("company", "第一上班公司在三祥");       // 公司名
        exp1.put("position", "JAVAバックエンドエンジニア"); // 职位
        exp1.put("period", "2023.04 - 2023.7");     // 在职时间
        exp1.put("description", "写后端业务."); // 工作内容

        // 第二段工作经历（实习）
        Map<String, Object> exp2 = new HashMap<>();
        exp2.put("company", "某公司");
        exp2.put("position", "某某实习生");
        exp2.put("period", "2024.07 - 2024.10");
        exp2.put("description", "Java后端实习生");

        // Arrays.asList(exp1, exp2) : 把两个Map打包成一个List返回
        // 如果以后有更多经历，直接在括号里继续加 exp3, exp4...
        return Arrays.asList(exp1, exp2);
    }
}