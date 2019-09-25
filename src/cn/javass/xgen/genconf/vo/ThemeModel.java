package cn.javass.xgen.genconf.vo;

import java.util.HashMap;
import java.util.Map;

public class ThemeModel {
	private String id;
	private String location;
	
	private Map<String,GenTypeModel> mapGenTypes = new HashMap<String,GenTypeModel>();
	
	private Map<String,String> mapGenOutTypes = new HashMap<String,String>();
	
	private Map<String,String> mapProviders = new HashMap<String,String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Map<String, GenTypeModel> getMapGenTypes() {
		return mapGenTypes;
	}

	public void setMapGenTypes(Map<String, GenTypeModel> mapGenTypes) {
		this.mapGenTypes = mapGenTypes;
	}

	public Map<String, String> getMapGenOutTypes() {
		return mapGenOutTypes;
	}

	public void setMapGenOutTypes(Map<String, String> mapGenOutTypes) {
		this.mapGenOutTypes = mapGenOutTypes;
	}

	public Map<String, String> getMapProviders() {
		return mapProviders;
	}

	public void setMapProviders(Map<String, String> mapProviders) {
		this.mapProviders = mapProviders;
	}

	@Override
	public String toString() {
		return "ThemeModel [id=" + id + ", location=" + location
				+ ", mapGenTypes=" + mapGenTypes + ", mapGenOutTypes="
				+ mapGenOutTypes + ", mapProviders=" + mapProviders + "]";
	}
	
	
}
