package cn.javass;

import cn.javass.xgen.dispatch.GenFacade;
import cn.javass.xgen.genconf.GenConfEbi;
import cn.javass.xgen.genconf.GenConfFactory;
import cn.javass.xgen.genconf.implementors.xmlimpl.GenConfXmlImpl;
import cn.javass.xgen.util.readxml.Context;
import cn.javass.xgen.util.readxml.Parser;

public class MyTest { 
	
	public static void main(String[] args) throws Exception{
		//1: ���ʽ ----�������﷨��====�������� 
		//2�������﷨��---������������ģʽȥ����ִ��
		
		
//		ElementExpression genConf = new ElementExpression("GenConf", "");
//		ElementExpression needGens = new ElementExpression("NeedGens", "");
//		ElementsExpression needGen = new ElementsExpression("NeedGen", "");
//		ElementsExpression params = new ElementsExpression("Params", "");
//		
//		ElementsTerminalExpression param = new ElementsTerminalExpression("Param", "id=fileName2");
//		
//		
//		//��װ�����﷨��
//		genConf.addEle(needGens);
//		needGens.addEle(needGen);
//		needGen.addEle(params);
//		params.addEle(param);
		
//		long a1 = System.currentTimeMillis();
//
//		Context ctx = Context.getInstance("cn/javass/xgenconfxml/GenConf.xml");
//		for(int i=0;i<1000;i++){
//			String [] ss = Parser.parse("GenConf/NeedGens/NeedGen/Params/Param$").interpret(ctx);
//			for(String s : ss){
//				System.out.println("ss=="+s);
//			}
//			String [] ss2 = Parser.parse("GenConf/NeedGens/NeedGen/Params/Param$.id$").interpret(ctx);
//		}
//		long a2 = System.currentTimeMillis();
//		System.out.println("now use time==="+(a2-a1));
		
//		for(String s : ss){
//			System.out.println("ss=="+s);
//		}
		
//-------------------------------------------------
//		GenConfEbi ebi = GenConfFactory.createGenConfEbi(new GenConfXmlImpl());
//
//		System.out.println("gm====="+ebi.getMapModuleConf());
		
//---------------------------------------------------------------------------------------
		GenFacade.generate();
	}

	
}
