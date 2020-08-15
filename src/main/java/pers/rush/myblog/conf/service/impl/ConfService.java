package pers.rush.myblog.conf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.dozermapper.core.Mapper;

import pers.rush.myblog.common.pojo.R;
import pers.rush.myblog.common.util.DozerWrapper;
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
	public List<ConfVO> findByWhere(ConfVO vo) {
		// VO转化到Entity
		ConfEntity entity = mapper.map(vo, ConfEntity.class);
		//分页查询
		List<ConfEntity> entitys = confLogic.findByWhere(entity);
		// Entity转化到VO
		return DozerWrapper.toVOList(entitys, ConfVO.class);
	}
}
