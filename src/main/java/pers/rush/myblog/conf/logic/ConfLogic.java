package pers.rush.myblog.conf.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import pers.rush.myblog.conf.dao.entity.ConfEntity;

/**
 * 配置逻辑
 * @author Rush
 *
 */
@Component
public class ConfLogic {
	
	private List<ConfEntity> list = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		String key = "PARAM";
		String name = "参数";
		String type = "String";
		String dtal = "备注";
		for (int i = 1; i <= 10; ++i) {
			ConfEntity confEntity = new ConfEntity();
			confEntity.setConfKey(key + i);
			confEntity.setConfName(name + i);
			confEntity.setType(type);
			confEntity.setValue(String.valueOf(i * 100));
			confEntity.setDtal(dtal + i);
			list.add(confEntity);
		}
		
	}

	public List<ConfEntity> findByWhere(ConfEntity se) {
		return list;
	}
	
}
