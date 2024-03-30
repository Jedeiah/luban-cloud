package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import java.util.List;

/**
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
    public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
    * ${table.entityName!}详情
    * @param
    * @return
    */
    ${table.entityName} get${table.entityName}( Integer id);

    /**
    * ${table.entityName!}详情
    * @param
    * @return
    */
    List<${table.entityName}> getAll${table.entityName}();

    /**
    * ${table.entityName!}新增
    * @param ${table.entityName?uncap_first} 根据需要进行传值
    * @return
    */
    void add(${entity} ${table.entityName?uncap_first});

    /**
    * ${table.entityName!}修改
    * @param ${table.entityName?uncap_first} 根据需要进行传值
    * @return
    */
    int modify(${entity} ${table.entityName?uncap_first});

    /**
    * ${table.entityName!}删除
    * @param ids
    * @return
    */
    void remove(String ids);
    }

</#if>

