package pers.rush.myblog.conf.logic;

import java.util.List;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import pers.rush.myblog.conf.dao.entity.ConfEntity;
import pers.rush.myblog.conf.service.vo.ConfVO;

/**
 * 配置逻辑
 * @author Rush
 *
 */
@Component
public class ConfLogic {

	public List<ConfEntity> findByWhere(Page<ConfVO> svp, ConfEntity se) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
