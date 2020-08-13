package pers.rush.myblog.conf.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import pers.rush.myblog.conf.service.vo.ConfVO;

/**
 * 配置管理控制器
 * @author Rush
 *
 */
public interface IConfService {
	
	/**
	 * 主页
	 * @return
	 */
	public ModelAndView page();
	
	public List<ConfVO> findByWhere(ConfVO sv);
}
