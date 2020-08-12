package pers.rush.myblog.conf.service.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import pers.rush.myblog.conf.dao.entity.ConfEntity;
import pers.rush.myblog.conf.service.IConfService;
import pers.rush.myblog.conf.service.vo.ConfVO;

@RestController
@RequestMapping(value = "/conf/conf")
public class ConfService implements IConfService {

	@Override
	@RequestMapping(value = "/ConfIndex")
	public ModelAndView page() {
		return new ModelAndView("/conf/ConfIndex.html");
	}
	
	@Override
	@RequestMapping(value = "/find")
	public IPage<ConfVO> findByWhere(Page<ConfVO> svp, ConfVO sv) {
		// VO转化到Entity
		ConfEntity se = ObjectFactory.toEntity(sv, ConfEntity.class);
		//分页查询
		List<ConfEntity> sel = confLogic.findByWhere(svp, se);
		// Entity转化到VO
		svp.setRecords(ObjectFactory.toVOList(sel, ConfVO.class));
		return svp;
	}
}
