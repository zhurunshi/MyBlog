package pers.rush.myblog.conf.service;

import org.springframework.web.servlet.ModelAndView;

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
	
	public IPage<ConfVO> findByWhere(Page<ConfVO> svp, ConfVO sv);
}
