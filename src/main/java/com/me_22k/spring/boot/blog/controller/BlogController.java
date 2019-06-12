package com.me_22k.spring.boot.blog.controller;

import java.util.List;

import com.me_22k.spring.boot.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.me_22k.spring.boot.blog.domain.User;
import com.me_22k.spring.boot.blog.domain.es.EsBlog;
import com.me_22k.spring.boot.blog.service.EsBlogService;
import com.me_22k.spring.boot.blog.vo.TagVO;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * 主页控制器.
 * 
 */
@Controller
@RequestMapping("/blogs")
public class BlogController {

	@Autowired
	private EsBlogService esBlogService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String listEsBlogs(
			@RequestParam(value = "order", required = false, defaultValue = "new") String order,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "async", required = false) boolean async,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			Model model) {

		Page<EsBlog> page = null;
		List<EsBlog> list = null;
		boolean isEmpty = true; // 系统初始化时，没有博客数据
		try {
			if (order.equals("hot")) { // 最热查询
				Sort sort = new Sort(Direction.DESC, "readSize", "commentSize", "voteSize", "createTime");
				Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
				page = esBlogService.listHotestEsBlogs(keyword, pageable);
			} else if (order.equals("new")) { // 最新查询
				Sort sort = new Sort(Direction.DESC, "createTime");
				Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
				page = esBlogService.listNewestEsBlogs(keyword, pageable);
			}

			isEmpty = false;
		} catch (Exception e) {
			Pageable pageable = new PageRequest(pageIndex, pageSize);
			page = esBlogService.listEsBlogs(pageable);
		}

		list = page.getContent();    // 当前所在页面数据列表


		model.addAttribute("order", order);
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", page);
		model.addAttribute("blogList", list);

		// 首次访问页面才加载
		if (!async && !isEmpty) {
			List<EsBlog> newest = esBlogService.listTop5NewestEsBlogs();
			model.addAttribute("newest", newest);
			List<EsBlog> hotest = esBlogService.listTop5HotestEsBlogs();
			model.addAttribute("hotest", hotest);
			List<TagVO> tags = esBlogService.listTop30Tags();
			model.addAttribute("tags", tags);
			List<User> users = esBlogService.listTop12Users();
			model.addAttribute("users", users);
		}

		return (async == true ? "/index :: #mainContainerRepleace" : "/index");
	}

	@GetMapping("/newest")
	public String listNewestEsBlogs(Model model) {
		List<EsBlog> newest = esBlogService.listTop5NewestEsBlogs();
		model.addAttribute("newest", newest);
		return "newest";
	}

	@GetMapping("/hotest")
	public String listHotestEsBlogs(Model model) {
		List<EsBlog> hotest = esBlogService.listTop5HotestEsBlogs();
		model.addAttribute("hotest", hotest);
		return "hotest";
	}

	@ResponseBody
	@RequestMapping("/CheckStatus")
	public String CheckStatus(@RequestParam String username) {
		String status = userRepository.findByStatus(username);
		return status;
	}

	/**
	 * 邮件激活
	 */
	@RequestMapping("/act")
	public String activate(@RequestParam String code, Model model, HttpSession httpSession) {
		String validateCodes = (String) httpSession.getAttribute("validateCodes");
		//把邮箱，密码以字符串数值形式接收过来
		String[] data=validateCodes.split(",");
		model.addAttribute("message","failure");
		//更新数据
		if(data[1].equals(code))
		{
			model.addAttribute("message","again");
			if(userRepository.findByStatus(data [0])!="1")
			{
				//修改数据库中的邮箱状态信息
				userRepository.updateStatus("1", data [0]);
				model.addAttribute("message","success");
				//清空服务端验证码
				httpSession.removeAttribute("validateCodes");
			}
		}
		return "act";
	}


}
