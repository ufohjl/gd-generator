package io.gd.generator.meta.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisXmlMeta {

	private String mapperName;
	private String model;
	private String table;
	private String simpleName;
	private String query;
	private String version;
	private Map<String, String> querys = new HashMap<>();
	private boolean hasQueryModel;

	private List<MybatisMappingMeta> mappingMetas = new ArrayList<>();

	private List<String> otherMappings = new ArrayList<>();

	public boolean isHasQueryModel() {
		return hasQueryModel;
	}

	public void setHasQueryModel(boolean hasQueryModel) {
		this.hasQueryModel = hasQueryModel;
	}

	public List<String> getOtherMappings() {
		return otherMappings;
	}

	public void setOtherMappings(List<String> otherMappings) {
		this.otherMappings = otherMappings;
	}

	public String getMapperName() {
		return mapperName;
	}

	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Map<String, String> getQuerys() {
		return querys;
	}

	public void setQuerys(Map<String, String> querys) {
		this.querys = querys;
	}

	public List<MybatisMappingMeta> getMappingMetas() {
		return mappingMetas;
	}

	public void setMappingMetas(List<MybatisMappingMeta> mappingMetas) {
		this.mappingMetas = mappingMetas;
	}

	public static class MybatisMappingMeta {
		private String column;
		private String property;
		private String enumHander;
		private String javaType;

		public String getColumn() {
			return column;
		}

		public void setColumn(String column) {
			this.column = column;
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

		public String getEnumHander() {
			return enumHander;
		}

		public void setEnumHander(String enumHander) {
			this.enumHander = enumHander;
		}

		public String getJavaType() {
			return javaType;
		}

		public void setJavaType(String javaType) {
			this.javaType = javaType;
		}

	}


}
