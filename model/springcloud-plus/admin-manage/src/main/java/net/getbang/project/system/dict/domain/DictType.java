package net.getbang.project.system.dict.domain;

import net.getbang.framework.web.domain.BaseEntity;

/**
 * 字典类型对象 sys_dict_type
 * 
 * @author ruoyi
 */
public class DictType extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 字典主键 */
    private Long dictId;
    /** 字典名称 */
    private String dictName;
    /** 字典类型 */
    private String dictType;
    /** 状态（0正常 1禁用） */
    private int status;

    public Long getDictId()
    {
        return dictId;
    }

    public void setDictId(Long dictId)
    {
        this.dictId = dictId;
    }

    public String getDictName()
    {
        return dictName;
    }

    public void setDictName(String dictName)
    {
        this.dictName = dictName;
    }

    public String getDictType()
    {
        return dictType;
    }

    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "DictType [dictId=" + dictId + ", dictName=" + dictName + ", dictType=" + dictType + ", status=" + status + "]";
    }

}
