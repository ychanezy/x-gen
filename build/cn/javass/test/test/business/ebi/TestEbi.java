package cn.javass.test.test.business.ebi;

import java.util.Collection;

import cn.javass.test.test.vo.*;

public interface TestEbi {
	public String create(TestModel obj);
	public void update(TestModel obj);
	public void delete(TestModel obj);
	
	public TestModel getByUuid(String uuid);
	public int getCount(TestQueryModel queryModel);
	public Collection<TestModel> getAll(boolean needPage,int start,int pageShow);
	public Collection<TestModel> getByCondition(TestQueryModel queryModel,boolean needPage,int start,int pageShow);
}