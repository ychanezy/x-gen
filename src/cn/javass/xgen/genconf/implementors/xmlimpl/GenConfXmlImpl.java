package cn.javass.xgen.genconf.implementors.xmlimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.implementors.GenConfImplementor;
import cn.javass.xgen.genconf.implementors.ThemeImplementer;
import cn.javass.xgen.genconf.vo.NeedGenModel;
import cn.javass.xgen.genconf.vo.ThemeModel;
import cn.javass.xgen.util.readxml.Context;
import cn.javass.xgen.util.readxml.Parser;
import cn.javass.xgen.util.readxml.ReadXmlExpression;

public class GenConfXmlImpl implements GenConfImplementor{
//1:通过builder来获取 用于取值的   字符串
//2：使用这些字符串  通过parser去获取到配置的数据 
//3：然后把这些数据组装成 GenConfImplementor所需要的样子
	
	
	@Override
	public List<NeedGenModel> getNeedGens() {
		return readNeedGends();
	}

	@Override
	public List<ThemeModel> getThemes() {
		return readThemes();
	}

	@Override
	public Map<String, String> getMapConstants() {
		return readMapConstants();
	}
	
	////////////////////////////
	private Context getContext(){
		Context c = null;
		try {
			c = Context.getInstance(
					new GenConfBuilder().addXmlFilePre().addGenConf().addDot().addXml().build()
					);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return c;
	}
////////////////////////////readMapConstants///////////////////////////////
	private Map<String,String> readMapConstants(){
		Map<String,String> map = new HashMap<String,String>();
		Context c = this.getContext();
		
		String [] ids = parseConstantIds(c);
		String [] values = parseConstantValues(c);
		
		for(int i=0;i<ids.length;i++){
			map.put(ids[i], values[i]);
		}
		return map;
	}
	private String[] parseConstantValues(Context c){
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addConstants().addSeparator()
				.addConstant().addDollar()
				.build()
				);
		return re.interpret(c);
	}
	private String[] parseConstantIds(Context c){
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addConstants().addSeparator()
				.addConstant().addDollar()
				.addDot().addId().addDollar()
				.build()
				);
		return re.interpret(c);
	}
	
////////////////////////////readThemes///////////////////////////////
	
	private  List<ThemeModel>  readThemes(){
		List<ThemeModel> retList = new ArrayList<ThemeModel>();
		Context c = getContext();
		
		String [] ids = parseThemeIds(c);
		String [] locations = parseThemeLocations(c);
		
		for(int i=0;i<ids.length;i++){
			ThemeModel tm = new ThemeModel();
			
			ThemeImplementer themeImpl = new ThemeXmlImpl();
			
			Map<String,String> params = new HashMap<String,String>();
			params.put(ExpressionEnum.location.getExpr(),locations[i]);
			
			tm.setId(ids[i]);
			tm.setLocation(locations[i]);
			tm.setMapGenOutTypes(themeImpl.getMapGenOutTypes(ids[i], params));
			tm.setMapGenTypes(themeImpl.getMapGenTypes(ids[i], params));
			tm.setMapProviders(themeImpl.getMapProviders(ids[i], params));
			
			retList.add(tm);
		}
		
		return retList;
	}
	private String[] parseThemeLocations(Context c){
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addThemes().addSeparator()
				.addTheme().addDollar()
				.build()
				);
		return re.interpret(c);
	}
	private String[] parseThemeIds(Context c){
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addThemes().addSeparator()
				.addTheme().addDollar()
				.addDot().addId().addDollar()
				.build()
				);
		return re.interpret(c);
	}
	
	
	////////////////////////////readNeedGends///////////////////////////////
	private List<NeedGenModel>  readNeedGends(){
		List<NeedGenModel> retList = new ArrayList<NeedGenModel>();
		Context c = getContext();
		//先分别获取相应的数据
		String [] needGenIds = parseNeedGenIds(c);
		String [] needGenProviders = parseNeedGenProviders(c);
		String [] needGenThemes = parseNeedGenThemes(c);
		
		//开始组装
		for(int i=0;i<needGenIds.length;i++){
			NeedGenModel ngm = new NeedGenModel();
			
			ngm.setId(needGenIds[i]);
			ngm.setProvider(needGenProviders[i]);
			ngm.setTheme(needGenThemes[i]);
			
			//去获取参数的值
			String [] paramIds = parseParamIds(c,ngm.getId());
			String [] paramValues = parseParamValues(c,ngm.getId());
			
			//组装param
			Map<String,String> mapParams = new HashMap<String,String>();
			
			for(int j=0;j<paramIds.length;j++){
				mapParams.put(paramIds[j],paramValues[j]);
			}
			
			ngm.setMapParams(mapParams);
			
			//把ngm加入到返回的list里面
			retList.add(ngm);
		}
		
		return retList;
	}
	private String[] parseParamValues(Context c,String needGenId){
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
				.addNeedGen().addDollar()
				.addOpenBracket().addId().addEqual().addOtherValue(needGenId).addCloseBracket()
				.addSeparator().addParams().addDollar().addSeparator().addParam().addDollar()
				.build()
				);
		return re.interpret(c);
	}
	private String[] parseParamIds(Context c,String needGenId){
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
				.addNeedGen().addDollar()
				.addOpenBracket().addId().addEqual().addOtherValue(needGenId).addCloseBracket()
				.addSeparator().addParams().addDollar().addSeparator().addParam().addDollar()
				.addDot().addId().addDollar()
				.build()
				);
		return re.interpret(c);
	}
	private String[] parseNeedGenThemes(Context c){
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
				.addNeedGen().addDollar().addDot().addThemeId().addDollar()
				.build()
				);
		return re.interpret(c);
	}
	private String[] parseNeedGenProviders(Context c){
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
				.addNeedGen().addDollar().addDot().addProvider().addDollar()
				.build()
				);
		return re.interpret(c);
	}
	private String[] parseNeedGenIds(Context c){
		c.init();
		//genconf/needgens/needgen$.id$
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
				.addNeedGen().addDollar().addDot().addId().addDollar()
				.build()
				);
		return re.interpret(c);
	}
	

}
