package cn.sitedev.entity;

/**
 * 流程定义实体
 * 
 * @author qchen
 * 
 */
public class ProcDef {
	// 流程定义id
	private String id;
	// 流程名称
	private String name;
	// 流程定义key
	private String key;
	// 版本
	private Integer version;

	/**
	 * 无参构造器
	 */
	public ProcDef() {
		super();
	}

	/**
	 * 含参构造器
	 * 
	 * @param id
	 * @param name
	 * @param key
	 * @param version
	 */
	public ProcDef(String id, String name, String key, Integer version) {
		super();
		this.id = id;
		this.name = name;
		this.key = key;
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "ProcDef [id=" + id + ", name=" + name + ", key=" + key
				+ ", version=" + version + "]";
	}

}
