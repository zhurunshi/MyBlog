package pers.rush.myblog.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dozermapper.core.Mapper;

@Component
public class DozerWrapper {
	
	@Autowired
	private Mapper mapper;
	
	private static Mapper staMapper;
	
	private DozerWrapper() {}
	
	@PostConstruct
	public void init() {
		staMapper = mapper;
	}
	
	public static <S, T> List<T> toVOList(List<S> srcList, Class<T> targetClass) {
		List<T> targetList = new ArrayList<T>();
		for (S s : srcList) {
			targetList.add(staMapper.map(s, targetClass));
		}
		return targetList;
	}
}
