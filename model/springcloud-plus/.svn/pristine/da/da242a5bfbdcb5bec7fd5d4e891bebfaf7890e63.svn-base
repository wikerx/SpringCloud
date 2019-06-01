package net.getbang.entity.paltform;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
/**
 * 平台实体
 * @author Administrator
 *
 */
public class Platform extends Model<Platform>{

	  /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	
	
	private String pName;
	
	private Integer pType;
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getpName() {
		return pName;
	}



	public void setpName(String pName) {
		this.pName = pName;
	}



	public Integer getpType() {
		return pType;
	}



	public void setpType(Integer pType) {
		this.pType = pType;
	}



	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}

	
	
	
	
}
