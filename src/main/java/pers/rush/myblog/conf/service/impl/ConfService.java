package pers.rush.myblog.conf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.dozermapper.core.Mapper;

import pers.rush.myblog.common.util.DozerWarpper;
import pers.rush.myblog.conf.dao.entity.ConfEntity;
import pers.rush.myblog.conf.logic.ConfLogic;
import pers.rush.myblog.conf.service.IConfService;
import pers.rush.myblog.conf.service.vo.ConfVO;

@RestController
@RequestMapping(value = "/conf/conf")
public class ConfService implements IConfService {
	
	@Autowired
	private Mapper mapper;
	@Autowired
	private ConfLogic confLogic;

	@Override
	@RequestMapping(value = "/ConfIndex")
	public ModelAndView page() {
		return new ModelAndView("/conf/ConfIndex.html");
	}
	
	@Override
	@RequestMapping(value = "/find")
	public List<ConfVO> findByWhere(ConfVO sv) {
		// VO转化到Entity
		ConfEntity se = mapper.map(sv, ConfEntity.class);
		//分页查询
		List<ConfEntity> sel = confLogic.findByWhere(se);
		// Entity转化到VO
		return DozerWarpper.toVOList(sel, ConfVO.class);
	}
}
