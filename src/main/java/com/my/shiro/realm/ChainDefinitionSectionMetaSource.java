package com.my.shiro.realm;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.my.shiro.dao.ResourceDao;
import com.my.shiro.security.Resource;

public class ChainDefinitionSectionMetaSource implements
		FactoryBean<Ini.Section> {

	@Autowired  
    private ResourceDao resourceDao;
	
	private String filterChainDefinitions;

	/**
	 * Ĭ��premission�ַ���
	 */
	public static final String PREMISSION_STRING = "perms[\"{0}\"]";

	/**
	 * ͨ��filterChainDefinitions��Ĭ�ϵ�url���˶���
	 * 
	 * @param filterChainDefinitions
	 *            Ĭ�ϵ�url���˶���
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	public Section getObject() throws Exception {
		//��ȡ����Resource  
        List<Resource> list = resourceDao.getResources();  
  
        Ini ini = new Ini();  
        //����Ĭ�ϵ�url  
        ini.load(filterChainDefinitions);  
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);  
        //ѭ��Resource��url,�����ӵ�section�С�section����filterChainDefinitionMap,  
        //����ļ���������URL,ֵ���Ǵ���ʲô�������ܷ��ʸ�����  
        for (Iterator<Resource> it = list.iterator(); it.hasNext();) {  
  
            Resource resource = it.next();  
            //�����Ϊ��ֵ��ӵ�section��  
            if(StringUtils.isNotEmpty(resource.getValue()) && StringUtils.isNotEmpty(resource.getPermission())) {  
                section.put(resource.getValue(),  MessageFormat.format(PREMISSION_STRING,resource.getPermission()));  
            }
        }
		return section;
	}

	public Class<?> getObjectType() {
		return this.getClass();
	}

	public boolean isSingleton() {
		return false;
	}

}
