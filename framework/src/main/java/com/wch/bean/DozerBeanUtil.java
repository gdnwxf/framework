package com.wch.bean;

import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DozerBeanUtil {
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	public <P> P clone(P base) {
		if (base == null) {
			return null;
		}
		return (P) this.dozerBeanMapper.map(base, base.getClass());
	}

	public <P> List<P> cloneList(List<P> baseList) {
		if (baseList == null) {
			return null;
		}
		List<P> targetList = new ArrayList();
		for (P p : baseList) {
			targetList.add(clone(p));
		}
		return targetList;
	}

	public <V, P> P convert(V base, P target) {
		if (base != null) {
			this.dozerBeanMapper.map(base, target);
			return target;
		}
		return target;
	}

	public <V, P> P convert(V base, Class<P> target) {
		if (base == null) {
			return null;
		}
		P p = this.dozerBeanMapper.map(base, target);
		return p;
	}

	public <V, P> List<P> convertList(List<V> baseList, Class<P> target) {
		if (baseList == null) {
			return null;
		}
		List<P> targetList = new ArrayList();
		for (V vo : baseList) {
			targetList.add(convert(vo, target));
		}
		return targetList;
	}

	public void setDozerBeanMapper(DozerBeanMapper dozerBeanMapper) {
		this.dozerBeanMapper = dozerBeanMapper;
	}
}
