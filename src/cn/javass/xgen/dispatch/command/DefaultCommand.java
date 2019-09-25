package cn.javass.xgen.dispatch.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javass.xgen.dispatch.executechain.DefaultHandler;
import cn.javass.xgen.dispatch.executechain.GenHandler;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

public class DefaultCommand implements GenCommand{
	/**
	 * ����Ҫ���ɵ�ģ������� model
	 */
	private ModuleConfModel moduleConf;
	
	public DefaultCommand(ModuleConfModel moduleConf){
		this.moduleConf = moduleConf;
	}
	@Override
	public void execute() {
		//Ӧ�õ��ú�����ģ��ȥʵ�������Ĺ���
		
		//������������̬��װ ְ������Ҳ���ǰ������ɵ����������������Ҫ���ɵĹ��� 
		List<String> listNeedGenTypes = new ArrayList<String>();
		//1���Ȱ����������õ���Ҫ���ɵĹ��ܵ����ͻ�ȡ����������List����
		for(String s : moduleConf.getMapNeedGendTypes().keySet()){
			listNeedGenTypes.add(s);
		}
		
		//2����list���������ת����һ��map�У�key����һ��˳��ֵ��value������Ҫ���ɵ����͵� ������
		Map<Integer,GenHandler> mapHandler = new HashMap<Integer,GenHandler>();
		for(int i=0;i<listNeedGenTypes.size();i++){
			mapHandler.put(i+1, new DefaultHandler(listNeedGenTypes.get(i)));
		}
		//3����map��������ݰ���˳���ȡ����������ְ����
		GenHandler h1 = mapHandler.get(1);
		for(int i=1;i<mapHandler.values().size();i++){
			mapHandler.get(i).setSuccessor(mapHandler.get(i+1));
		}
		
		//4��ִ������
		h1.handleRequest(moduleConf);
	}
}
